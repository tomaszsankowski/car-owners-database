import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from '../../../environments/environment.development';
import {Cars} from '../model/cars.model';
import {CarDetails} from '../model/car-details.model';
import {Observable} from 'rxjs';

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

  deleteCar(carId: string): Observable<void> {
    return this.http.delete<void>(`${this.url}/${carId}`);
  }

  addCar(ownerId: string, carId: string,car: CarDetails): Observable<CarDetails> {
    const putCarRequest = {
      brand: car.brand,
      model: car.model,
      productionYear: car.productionYear,
      power: car.power,
      plate: car.plate,
      owner: ownerId
    };
    return this.http.put<CarDetails>(`${this.url}/${carId}`, putCarRequest);
  }

  updateCar(carId: string, car: CarDetails): Observable<CarDetails> {
    const patchCarRequest = {
      brand: car.brand,
      model: car.model,
      productionYear: car.productionYear,
      power: car.power
    };
    return this.http.patch<CarDetails>(`${this.url}/${carId}`, patchCarRequest);
  }
}
