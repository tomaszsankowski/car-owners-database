import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from '../../../environments/environment.development';
import {Persons} from '../model/persons.model';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  url:string = environment.apiBaseUrl + '/persons'
  constructor(private http: HttpClient) { }

  getPersons() {
    return this.http.get<Persons>(this.url)
  }
}
