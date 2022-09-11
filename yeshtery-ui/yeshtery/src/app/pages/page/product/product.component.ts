import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EndpointService } from 'src/app/services/endpoint.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  constructor(private endpoint:EndpointService) { }

  ngOnInit(): void {
  }

  formGroup = new FormGroup({
    'category': new FormControl(null, [Validators.required]),
    'description': new FormControl(null, [Validators.required])
  });

  // change player image
  selectedImage: any;
  addProductImage($event: any) {
    this.selectedImage = $event.target.files[0];
  }

  saveNewProduct() {
    if(this.selectedImage && this.formGroup.valid) {
      this.endpoint.saveNewProduct(this.formGroup.value, this.selectedImage).subscribe( (product: any) => {
        if(product.success) {
          alert('Product successfully added');
          this.selectedImage = '';
        } else {
          alert(product.message);
        }
      });
    } else {
      alert('Please fill the all fields');
    }
  }

}
