import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonDetailsComponent } from './person/view/person-details/person-details.component';
import { PersonFormComponent } from './person/view/person-form/person-form.component';

export const routes: Routes = [
  { path: 'add', component: PersonFormComponent },
  { path: 'edit/:id', component: PersonFormComponent },
  { path: ':id', component: PersonDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
