import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EndpointService } from 'src/app/services/endpoint.service';
import { StoreDataService } from 'src/app/services/storage/store-data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router, private store: StoreDataService, private endpoint: EndpointService) { }

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

  openAdminUpdatePage(id: number) {
    this.router.navigate(['/Yeshtery/update.product/'+id]);
  }

  

}
