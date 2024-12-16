import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CarService } from '../../service/car.service';
import { CarDetails } from '../../model/car-details.model';
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-car-form',
  templateUrl: './car-form.component.html',
  imports: [
    ReactiveFormsModule
  ],
  styleUrls: ['./car-form.component.css']
})
export class CarFormComponent implements OnInit {
  carForm: FormGroup;
  isEditMode: boolean = false;
  carId: string | null = null;
  ownerId: string | null = null;

  constructor(
    private fb: FormBuilder,
    private carService: CarService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.carForm = this.fb.group({
      brand: ['', Validators.required],
      model: ['', Validators.required],
      productionYear: ['', Validators.required],
      power: ['', Validators.required],
      plate: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      console.log("XD");
      this.carId = params.get('carId');
      this.ownerId = params.get('personId');
      this.isEditMode = !!this.carId;
      if (this.isEditMode && this.carId) {
        this.carService.getCar(this.carId).subscribe((car: CarDetails) => {
          this.carForm.patchValue(car);
        });
      }
    });
  }

  onSubmit(): void {
    if (this.carForm.valid) {
      if (this.isEditMode) {
        this.carService.updateCar(this.carId!, this.carForm.value).subscribe(() => {
          this.router.navigate(['/', this.ownerId]);
        });
      } else {
        this.carService.addCar(this.ownerId!, uuidv4(), this.carForm.value).subscribe(() => {
          this.router.navigate(['/', this.ownerId]);
        });
      }
    }
  }
}
