import { AfterViewInit, Component, OnInit, ViewChild } from "@angular/core";
import { Participant } from '../../../generated/openapi/src/model/participant'
import { ActivatedRoute, Params, Router } from "@angular/router";
import { MatTableDataSource } from "@angular/material/table";
import { MatPaginator } from "@angular/material/paginator";
import { ParticipantsControllerService } from "../../../generated/openapi/src/api/participantsController.service";

@Component({
    selector: 'app-participant-detail',
    templateUrl: './view.component.html',
    styleUrl: './view.component.css',
  })
  export class ViewComponent implements OnInit, AfterViewInit {
    displayedColumns: string[] = ['index' , 'code', 'bic', 'name', 'shortName', 'type', 'settlementBank', 'logo', 'actions'];
    dataSource = new MatTableDataSource<Participant>();
    constructor(private participantsControllerService: ParticipantsControllerService,
                private router: Router,
                private route: ActivatedRoute
    ){}

    @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;

    ngOnInit() {
      this.participantsControllerService.fetchAllParticipants()
      .subscribe({
       next: (response) => {
          this.dataSource.data = response;
        },
        error: (error) => {
          console.error('There was an error!', error);
        }
    });
    }

    ngAfterViewInit() {
     this.dataSource.paginator = this.paginator;
    }

    onUpdate(code: string): void{
      this.router.navigate([`participant-update/${code}`]);
    }

    applyFilter(event: Event) {
      const filterValue = (event.target as HTMLInputElement).value;
      this.dataSource.filter = filterValue.trim().toLowerCase();
    }
  }