import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EndpointService } from 'src/app/services/endpoint.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  formGroup = new FormGroup({
    'email': new FormControl(null, [Validators.required]),
    'name': new FormControl(null, [Validators.required]),
    'password': new FormControl(null, [Validators.required])
  });
  constructor(private endpoint: EndpointService) { }

  ngOnInit(): void {
  }

  registerUser() {
    if(this.formGroup.valid) {
      this.endpoint.saveNewUser(this.formGroup.value).subscribe( (user: any) =>  {
        if(user.success) {
          alert('user successfully added');
        } else {
          alert(user.message);
        }
      });
    } else {
      alert('please fill in the all fields');
    }
  }

}
