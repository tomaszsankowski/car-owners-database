import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from '../../../environments/environment.development';
import { Persons } from '../model/persons.model';
import { Cars } from '../../car/model/cars.model';
import { PersonDetails } from '../model/person-details.model';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  url:string = environment.apiBaseUrl + '/persons'
  constructor(private http: HttpClient) { }

  getPerson(id: string) {
    return this.http.get<PersonDetails>(`${this.url}/${id}`);
  }

  getPersons() {
    return this.http.get<Persons>(this.url)
  }

  getPersonCars(id: string) {
    return this.http.get<Cars>(`${this.url}/${id}/cars`);
  }

  addPerson(person: PersonDetails) {
    return this.http.post<PersonDetails>(this.url, person);
  }

  updatePerson(id: string, person: PersonDetails) {
    return this.http.put<PersonDetails>(`${this.url}/${id}`, person);
  }
}
