import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EndpointService } from 'src/app/services/endpoint.service';
import { StoreDataService } from 'src/app/services/storage/store-data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private enpoint: EndpointService, private store: StoreDataService, private router: Router) { }

  formGroup = new FormGroup({
    'email': new FormControl(null, [Validators.required]),
    'password': new FormControl(null, [Validators.required])
  });

  ngOnInit(): void {
  }

  login() {
    if(this.formGroup.valid) {
      let email = this.formGroup.get('email')?.value;
      let password = this.formGroup.get('password')?.value;
      this.enpoint.login(email!, password!).subscribe( (user: any) => {
        if(user.success == true) {
          this.store.storeElement('systemUser', user.data);
          this.router.navigate(['/Yeshtery']);
        } else {
          alert(user.message);
        }
      });
    }
    
  }

}
