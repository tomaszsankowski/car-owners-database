import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {PersonListComponent} from './person/view/person-list/person-list.component';
import {PersonDetailsComponent} from './person/view/person-details/person-details.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, PersonListComponent, PersonDetailsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angular-front';
}
