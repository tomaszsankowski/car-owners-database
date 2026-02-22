import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from '../../../environments/environment.development';
import { Persons } from '../model/persons.model';
import { Cars } from '../../car/model/cars.model';
import { PersonDetails } from '../model/person-details.model';
import {Observable} from 'rxjs';

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

  addPerson(id: string, person: PersonDetails) {
    return this.http.put<PersonDetails>(`${this.url}/${id}`, person);
  }

  updatePerson(id: string, person: PersonDetails) {
    return this.http.patch<PersonDetails>(`${this.url}/${id}`, person);
  }

  deletePerson(personID: string): Observable<void> {
    return this.http.delete<void>(`${this.url}/${personID}`);
  }

}
