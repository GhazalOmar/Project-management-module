import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { FormComponent } from "./participants/participant-form/form.component";
import { ViewComponent } from "./participants/participant-view/view.component";
import { UpdateComponent } from "./participants/participant-update/update.component";
import { HomeComponent } from "./home/home.component";


const appRoutes: Routes = [
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'home',  component: HomeComponent},
    {path: 'participant-detail', component: FormComponent},
    {path: 'participant-view', component: ViewComponent},
    {path: 'participant-update/:code', component: UpdateComponent},
]

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes)
      ],
      exports: [RouterModule], 
})
export class AppRoutingModule{

}