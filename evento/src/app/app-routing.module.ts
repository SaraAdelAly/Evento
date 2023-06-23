import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { EventComponent } from './components/event/event.component';
import { EventDetailsComponent } from './components/event-details/event-info/event-details.component';
import { SignupComponent } from './components/signup/signup.component';
import {EventListComponent} from './components/event-list/event-list.component'
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AddEventComponent } from './components/admin/add-event/add-event.component';
import { AdminComponent } from './components/admin/admin.component';
import { DatePipe } from '@angular/common';


const routes: Routes = [
  {path:'home',component:HomeComponent},
  // {path:'event-details/:id/speakers', component: EventSpeakersComponent},
  { path: 'event-details/:id', component: EventDetailsComponent },
  {path:'404',component:NotFoundComponent},
  {path:'event/:id',component:EventDetailsComponent},
  {path:'login',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'events',component:EventListComponent},
  {path:'admin',component:AdminComponent},
  {path:'add-event',component:AddEventComponent},
  {path:'',component:HomeComponent},
  {path:'**',component:NotFoundComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [DatePipe]
})
export class AppRoutingModule { }

