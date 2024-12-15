import { Component, Input, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { PersonService } from '../../service/person.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonDetails } from '../../model/person-details.model';

@Component({
  selector: 'app-person-form',
  templateUrl: './person-form.component.html',
  imports: [
    ReactiveFormsModule
  ],
  styleUrls: ['./person-form.component.css']
})
export class PersonFormComponent implements OnInit {
  @Input() personId: string | null = null;
  personForm: FormGroup;
  isEditMode: boolean = false;

  constructor(
    private fb: FormBuilder,
    private personService: PersonService,
    private router: Router
  ) {
    this.personForm = this.fb.group({
      name: ['', Validators.required],
      surname: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    if (this.personId) {
      this.isEditMode = true;
      this.personService.getPerson(this.personId).subscribe((person: PersonDetails) => {
        this.personForm.patchValue(person);
      });
    }
  }

  onSubmit(): void {
    if (this.personForm.valid) {
      if (this.isEditMode) {
        this.personService.updatePerson(this.personId!, this.personForm.value).subscribe(() => {
          this.router.navigate(['/']);
        });
      } else {
        this.personService.addPerson(this.personForm.value).subscribe(() => {
          this.router.navigate(['/']);
        });
      }
    }
  }
}
