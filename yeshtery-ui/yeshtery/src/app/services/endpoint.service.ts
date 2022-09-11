import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EndpointService {

  constructor(private http: HttpClient) { }

  // login page endpoint
  login(email: string, password: string) {

    return this.http.post(environment.SERVICE_URL+'Login/'+email+'/'+password, null);
  }

  // this endpoint to save new product with it's image
  saveNewProduct(productObj: any, file: any) {
    const headerDict = {
    }

    const requestOptions = {
      headers: new HttpHeaders(headerDict),
    };
    let formData = new FormData();
    formData.append('file', file);
    formData.append('productObj', JSON.stringify( productObj ) );

    return this.http.post(environment.SERVICE_URL+'SaveNewProduct', formData, requestOptions);
  }

  saveNewUser(user: any) {

    return this.http.post(environment.SERVICE_URL+'SaveNewUser', user);
  }

  getProductToGeneralPage() {

    return this.http.post(environment.SERVICE_URL+'GetProductToGeneralPage', null);
  }

  getProductToAdministratorPage(userMustBeAdminstrator: any) {

    return this.http.post(environment.SERVICE_URL+'GetProductToAdministratorPage', userMustBeAdminstrator);
  }

  getProductToShow(productId: number) {

    return this.http.post(environment.SERVICE_URL+'GetProductToShow/'+productId, null);
  }

  getProductToUpdate(userMustBeAdminstrator: any, productId: number) {

    return this.http.post(environment.SERVICE_URL+'GetProductToUpdate/'+productId, userMustBeAdminstrator);
  }

  productUpdateToAccepted(userMustBeAdminstrator: any, productId: number) {

    return this.http.post(environment.SERVICE_URL+'ProductUpdateToAccepted/'+productId, userMustBeAdminstrator);
  }

  productUpdateToRejected(userMustBeAdminstrator: any, productId: number) {

    return this.http.post(environment.SERVICE_URL+'ProductUpdateToRejected/'+productId, userMustBeAdminstrator);
  }

}
