import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonDetailsComponent } from './person/view/person-details/person-details.component';
import { PersonFormComponent } from './person/view/person-form/person-form.component';
import {CarDetailsComponent} from './car/view/car-details/car-details.component';
import {CarFormComponent} from './car/view/car-form/car-form.component';

export const routes: Routes = [
  { path: 'add', component: PersonFormComponent },
  { path: 'edit/:id', component: PersonFormComponent },
  { path: ':id', component: PersonDetailsComponent },
  { path: ':personId/car/:id', component: CarDetailsComponent },
  { path: ':personId/car/:carId/edit', component: CarFormComponent },
  { path: ':personId/add', component: CarFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
