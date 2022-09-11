import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EndpointService } from 'src/app/services/endpoint.service';
import { ASEEncryptDecryptService } from 'src/app/services/security/a-s-e-encrypt-decrypt.service';
import { StoreDataService } from 'src/app/services/storage/store-data.service';

@Component({
  selector: 'app-admin-update',
  templateUrl: './admin-update.component.html',
  styleUrls: ['./admin-update.component.scss']
})
export class AdminUpdateComponent implements OnInit {

  constructor(private route: ActivatedRoute, private endpoint: EndpointService, private security: ASEEncryptDecryptService, 
    private store: StoreDataService) { }

  user: any;
  product: any;
  ngOnInit(): void {
    this.user = this.store.getStoreElement('systemUser');
    this.route.paramMap.subscribe((param: any) => {
      let productId = param.get('product');
      if(this.user.roleCode === 1) {
        this.endpoint.getProductToUpdate(this.user, productId).subscribe( (data: any) => {
          if(data.success)
            this.product = data.data;
            else
              alert(data.message);
        });
      } else {
        this.endpoint.getProductToShow(productId).subscribe( (data: any) => {
          if(data.success)
            this.product = data.data;
            else
              alert(data.message);
        });
      }
    });

  }

  Acceptance(id: any) {
    let status = confirm('Are you sure you want to accept this product?');
    if(status) {
      this.endpoint.productUpdateToAccepted(this.user, id).subscribe( (data: any) => {
        if(data.success) {
          alert('product acceptance successfully');
        } else {
          alert(data.message);
        }
      });
    }
  }

  rejected(id: any) {
    let status = confirm('Are you sure you want to reject this product?');
    if(status) {
      this.endpoint.productUpdateToRejected(this.user, id).subscribe( (data: any) => {
        if(data.success) {
          alert('product rejected successfully');
        } else {
          alert(data.message);
        }
      });
    }
  }

}
