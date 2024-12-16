import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, RouterLink, Router } from '@angular/router';
import { CarService } from '../../service/car.service';
import { CarDetails } from '../../model/car-details.model';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-car-details',
  templateUrl: './car-details.component.html',
  imports: [
    NgIf,
    RouterLink
  ],
  styleUrls: ['./car-details.component.css']
})
export class CarDetailsComponent implements OnInit {
  carDetails: CarDetails | undefined;

  constructor(
    private route: ActivatedRoute,
    private carService: CarService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const carId = this.route.snapshot.paramMap.get('id');
    if (carId) {
      this.carService.getCar(carId).subscribe(details => this.carDetails = details);
    }
  }

  deleteCar(): void {
    if (this.carDetails?.id) {
      this.carService.deleteCar(this.carDetails.id).subscribe(() => {
        this.router.navigate(['/']);
      });
    }
  }
}
