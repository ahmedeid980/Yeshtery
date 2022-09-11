import { Injectable } from '@angular/core';
import { ASEEncryptDecryptService } from 'src/app/services/security/a-s-e-encrypt-decrypt.service';

@Injectable({
  providedIn: 'root'
})
export class StoreDataService {

  constructor(private _encryptDecrypt: ASEEncryptDecryptService) { }

  storeElement(keyElement: string, valueElement: any) {
    const encryptStr = this._encryptDecrypt.encrypt(JSON.stringify(valueElement));
    localStorage.setItem(keyElement, encryptStr);
  }

  getStoreElement(keyElement: string): any {
    if (localStorage.getItem(keyElement)) {
      let storage = localStorage.getItem(keyElement);
      let element;
      if (storage) {
        element = this._encryptDecrypt.decrypt(storage);
      }
      if (element)
        return JSON.parse(element);
    }
  }

  deleteStorageElement(keyElement: string) {
    if (localStorage.getItem(keyElement)) {
      localStorage.removeItem(keyElement);
    }
  }

  storeElementWthoutSecret(keyElement: string, valueElement: any) {
    localStorage.setItem(keyElement, valueElement);
  }

  getElementWthoutSecret(keyElement: string): any {
    if (localStorage.getItem(keyElement)) {
      return localStorage.getItem(keyElement);
    }
    return null;
  }

}
