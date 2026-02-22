import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import {PersonListComponent} from './person/view/person-list/person-list.component';

@Component({
  selector: 'app-root',
  imports: [RouterModule, PersonListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  standalone: true
})
export class AppComponent {
  title = 'angular-front';
}
