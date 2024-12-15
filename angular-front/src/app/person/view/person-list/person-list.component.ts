import {Component, inject, OnInit} from '@angular/core';
import { PersonService } from '../../service/person.service';
import {Persons} from '../../model/persons.model';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-person-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './person-list.component.html',
  styleUrl: './person-list.component.css'
})
export class PersonListComponent implements OnInit{

  constructor(public service: PersonService) {

  }

  persons: Persons | undefined;

  ngOnInit(): void {
    this.service.getPersons().subscribe(persons => this.persons = persons);
  }
}
