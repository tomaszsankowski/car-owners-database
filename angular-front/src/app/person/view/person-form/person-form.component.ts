import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PersonService } from '../../service/person.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonDetails } from '../../model/person-details.model';
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-person-form',
  templateUrl: './person-form.component.html',
  imports: [
    ReactiveFormsModule,
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
      name: ['', Validators.required],
      surname: ['', Validators.required],
      age: ['', Validators.required]
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
