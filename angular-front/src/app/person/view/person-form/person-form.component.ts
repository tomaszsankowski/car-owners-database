import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PersonService } from '../../service/person.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonDetails } from '../../model/person-details.model';
import {v4 as uuidv4} from 'uuid';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-person-form',
  templateUrl: './person-form.component.html',
  imports: [
    ReactiveFormsModule,
    NgIf,
  ],
  styleUrls: ['./person-form.component.css']
})
export class PersonFormComponent implements OnInit {
  personForm: FormGroup;
  isEditMode: boolean = false;
  personId: string | null = null;

  constructor(
    private fb: FormBuilder,
    private personService: PersonService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.personForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(20)]],
      surname: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(20)]],
      age: ['', [Validators.required, Validators.min(1), Validators.max(100)]]
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.personId = params.get('id');
      this.isEditMode = !!this.personId;

      if (this.isEditMode && this.personId) {
        this.personService.getPerson(this.personId).subscribe((person: PersonDetails) => {
          this.personForm.patchValue(person);
        });
      }
    });
  }

  onSubmit(): void {
    if (this.personForm.valid) {
      if (this.isEditMode) {
        this.personService.updatePerson(this.personId!, this.personForm.value).subscribe(() => {
          this.router.navigate([`/${this.personId}`]);
        });
      } else {
        this.personService.addPerson(uuidv4(), this.personForm.value).subscribe(() => {
          this.router.navigate(['/']);
        });
      }
    }
  }
}
