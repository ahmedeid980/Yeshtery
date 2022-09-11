import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EndpointService } from 'src/app/services/endpoint.service';
import { StoreDataService } from 'src/app/services/storage/store-data.service';

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.scss']
})
export class AdministratorComponent implements OnInit {

  constructor(private store: StoreDataService, private router: Router, private endpoint: EndpointService) { }

  user: any;
  ngOnInit(): void {
    this.user = this.store.getStoreElement('systemUser');
    this.getProductsToAdministrator(this.user);
  }

  products: any;
  getProductsToAdministrator(user: any): void {
    this.endpoint.getProductToAdministratorPage(user).subscribe( (product: any) => {
      if(product.success == true) {
        this.products = product.data;
      } else {
        alert(this.products.message);
      }
    } );
  }

  openAdminUpdatePage(id: number) {
    this.router.navigate(['/Yeshtery/update.product/'+id]);
  }

}
