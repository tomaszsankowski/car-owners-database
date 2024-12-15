import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PersonService } from '../../service/person.service';
import { Cars } from '../../../car/model/cars.model';
import { PersonDetails } from '../../model/person-details.model';
import { NgForOf, NgIf } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-person-details',
  templateUrl: './person-details.component.html',
  imports: [
    NgForOf,
    NgIf,
    RouterLink
  ],
  styleUrls: ['./person-details.component.css']
})
export class PersonDetailsComponent implements OnInit {

  personId: string | null = null;
  person: PersonDetails | undefined;
  cars: Cars | undefined;

  constructor(private route: ActivatedRoute, private personService: PersonService) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.personId = params['id'];
      this.loadPersonDetails();
    });
  }

  private loadPersonDetails(): void {
    if (this.personId) {
      this.personService.getPerson(this.personId).subscribe(person => this.person = person);
      this.personService.getPersonCars(this.personId).subscribe(cars => this.cars = cars);
    }
  }
}
