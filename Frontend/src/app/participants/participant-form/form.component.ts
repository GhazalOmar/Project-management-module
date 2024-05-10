import { Component, OnInit} from '@angular/core';
import { Participant } from '../../../generated/openapi/src/model/participant'
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { ParticipantsControllerService } from '../../../generated/openapi/src/api/participantsController.service';
@Component({
  selector: 'app-participant-detail',
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent implements OnInit{
  participant: Participant = {
    code: '',
    bic: '',
    name: '',
    shortName: '',
    type: null,
    settlementBank: '',
    logo: ''
  };
  image: string;
  codes: string[];
  responseMessage: string;

  constructor(public dialog: MatDialog,
              private participantsControllerService: ParticipantsControllerService
  ) {}

  ngOnInit(): void {
      this.participantsControllerService.fetchDirectCodes()
      .subscribe({
        next: (response) => {
           this.codes = response;
         },
         error: (error) => {
           console.error('There was an error!', error);
         }
     });
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    const preview = document.querySelector('img') as HTMLImageElement;
    const reader = new FileReader();

      if(file){
        reader.readAsDataURL(file);
      }

      reader.onload = () => {
        const base64Image = reader.result as string;
        preview.src = base64Image;      
        this.image = base64Image;
      }
  }

  submitForm() {
    this.participant.logo = this.image;
    this.participantsControllerService.createParticipant(this.participant)
       .subscribe({
       next: (response) => {
          console.log('Form data sent successfully:', response);
          Swal.fire('Success', response.message , 'success');
        },
        error: (error) => {
          Swal.fire('Error', error.error.message , 'error');
          console.error('Error sending form data:', error);
        }     
  });
  }
  }