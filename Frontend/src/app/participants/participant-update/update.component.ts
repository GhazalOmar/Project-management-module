import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import Swal from 'sweetalert2';
import { ParticipantsControllerService } from '../../../generated/openapi/src/api/participantsController.service';

@Component({
  selector: 'app-participant-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit, OnDestroy {
  participantForm: FormGroup;
  code: string;
  image: string;
  codes: string[];
  private subscriptions: Subscription = new Subscription();
  
  constructor(
    private participantsControllerService: ParticipantsControllerService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.participantForm = new FormGroup({
      'code': new FormControl({value: '', disabled: true}),
      'bic': new FormControl('', Validators.pattern(/^[A-Z0-9]{4}[A-Z]{2}[A-Z0-9]{2}([A-Z0-9]{3})?$/)),
      'name': new FormControl('', Validators.pattern(/^[a-zA-Z0-9]+$/)),
      'shortName': new FormControl('', Validators.pattern(/[A-Z]+/)),
      'type': new FormControl(''),
      'logo': new FormControl('',),
      'settlementBank': new FormControl('')
    });

    const paramsSubscription = this.route.params.subscribe(params => {
      this.code = params['code'];
      this.participantsControllerService.fetchParticipant(this.code);
    });

    this.subscriptions.add(paramsSubscription);

    const codesSubscription = this.participantsControllerService.fetchDirectCodes()
      .subscribe({
        next: (response) => {
           this.codes = response;
         },
         error: (error) => {
           console.error('There was an error!', error);
         }
     });
     this.subscriptions.add(codesSubscription);
  }

  ngOnDestroy() {
    this.subscriptions.unsubscribe();
  }

  fetchParticipant(code: string) {
    this.participantsControllerService.fetchParticipant(this.code)
    .subscribe({
      next: (response) => {
        this.populateForm(response.participant);
      },
      error: (error) => {
        console.error('Error fetching participant data:', error);
      }
    });
  }

  populateForm(participantData: any) {
    if (participantData) {
      this.participantForm.patchValue({
        code: participantData.code,
        bic: participantData.bic,
        name: participantData.name,
        type: participantData.type,
        shortName: participantData.shortName,
        settlementBank: participantData.settlementBank,
      });
    }
    this.image = participantData.logo;
  }
  
  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    const preview = document.querySelector('img') as HTMLImageElement;
    let base64Image = '';

      const reader = new FileReader();

      reader.onload = (loadEvent: any) => {
        this.image = loadEvent.target.result;
      }

      if(file){
        reader.readAsDataURL(file);
      }
      this.image = base64Image;
  }
 
  onSubmit() {
    this.participantForm.get('logo').setValue(this.image);
    if(this.participantForm.valid){
      const formData = this.participantForm.value;
 
      this.participantsControllerService.updateParticipant(this.code, formData)
      .subscribe({
        next: (response) => {
          console.log('Form data sent successfully:', response);
          Swal.fire('Success', response.message, 'success');
        },
        error: (error) => {
          console.error('Error sending form data:', error.error.message);
          Swal.fire('Error', error.error.message , 'error');
        }
    });
  }
}
   onCancel(){
    this.router.navigate(['/participant-view'], {relativeTo: this.route})
  }
}
