import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EndpointService } from 'src/app/services/endpoint.service';
import { StoreDataService } from 'src/app/services/storage/store-data.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
  constructor(private endpoint: EndpointService, private router: Router, private store: StoreDataService){}

  user: any;
  ngOnInit(): void {
    this.getProductsToShow();
    this.user = this.store.getStoreElement('systemUser');
    if(this.user && this.user.roleCode == 1) {
      this.adminStatus = true;
    } else {
       this.adminStatus = false;
    }
    if(this.user) {
      this.pageStatus = true;
    }
  }

  products: any;
  pageStatus: boolean = false;
  adminStatus: boolean = false;
  getProductsToShow(): void {
    this.endpoint.getProductToGeneralPage().subscribe( (product: any) => {
      if(product.success == true) {
        this.products = product.data;
      }
    } );
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/']);
    this.pageStatus = false;
    this.adminStatus = false;
  }

}
