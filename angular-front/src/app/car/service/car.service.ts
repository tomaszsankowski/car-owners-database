import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from '../../../environments/environment.development';
import {Cars} from '../model/cars.model';
import {CarDetails} from '../model/car-details.model';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  url:string = environment.apiBaseUrl + '/cars'
  constructor(private http: HttpClient) { }

  getCar(id: string) {
    return this.http.get<CarDetails>(`${this.url}/${id}`);
  }

  getCars() {
    return this.http.get<Cars>(this.url)
  }
}
