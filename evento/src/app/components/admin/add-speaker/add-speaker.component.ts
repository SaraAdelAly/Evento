import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import th from '@mobiscroll/angular/dist/js/i18n/th';
import { eventSpeakersDTO } from 'src/app/models/event-speakers.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-add-speaker',
  templateUrl: './add-speaker.component.html',
  styleUrls: ['./add-speaker.component.css']
})
export class AddSpeakerComponent {
  constructor(private formBuilder: FormBuilder, private apiService: ApiService, private _router: Router) { }
  eventForm: FormGroup;
  dateTime: FormGroup;
  selectedImage: File;
  speaker:eventSpeakersDTO;
  ngOnInit() {
    this.eventForm = this.formBuilder.group({
      jobTitle: [''],
      description: [''],
      name: [''],
      age: [''],
      image: [""]

    });
    this.speaker = this.eventForm.value;
    this.dateTime = this.formBuilder.group({
      datetimeCtrl: new FormControl(''),
    });
  }
  onImageSelected(event: any) {
    this.selectedImage = event.target.files[0];
  }
  submitForm(){
    console.log(this.speaker);
    this.apiService.post("api/admin/speaker", this.speaker)
      .subscribe({
        next: (response: any) => {
           
          console.log("success")
          this._router.navigateByUrl('/events');

        },
        error: (error: any) => { }
      }
      );
  }
}