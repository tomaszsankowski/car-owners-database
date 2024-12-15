import {Component, EventEmitter, inject, OnInit, Output} from '@angular/core';
import { PersonService } from '../../service/person.service';
import {Persons} from '../../model/persons.model';
import {CommonModule} from '@angular/common';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-person-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './person-list.component.html',
  styleUrl: './person-list.component.css'
})
export class PersonListComponent implements OnInit{

  @Output() personSelected = new EventEmitter<string>();

  constructor(public service: PersonService, private router: Router) {

  }

  persons: Persons | undefined;

  ngOnInit(): void {
    this.service.getPersons().subscribe(persons => this.persons = persons);
  }

  selectPerson(personId: string): void {
    this.router.navigate([`/${personId}`]);
  }
}
