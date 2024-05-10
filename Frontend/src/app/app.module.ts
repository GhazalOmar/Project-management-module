import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { ParticipantsComponent } from './participants/participants.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-route.module';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { FormComponent } from './participants/participant-form/form.component';
import { UpdateComponent } from './participants/participant-update/update.component';
import { ViewComponent } from './participants/participant-view/view.component';
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { HomeComponent } from './home/home.component';
import { ToastrModule } from 'ngx-toastr';
import { ApiModule } from '../generated/openapi/src';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ParticipantsComponent,
    FormComponent,
    ViewComponent,
    UpdateComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule, 
    RouterModule,
    HttpClientModule,
    MatPaginatorModule, 
    MatTableModule,
    MatButtonModule,
    BrowserAnimationsModule,
    MatCardModule,
    ToastrModule.forRoot(),
    ApiModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
