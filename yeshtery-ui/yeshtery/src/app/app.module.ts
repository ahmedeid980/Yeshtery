import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './pages/page/login/login.component';
import { LayoutComponent } from './pages/layout/layout/layout.component';
import { RegisterComponent } from './pages/page/register/register.component';
import { ProductComponent } from './pages/page/product/product.component';
import { AdministratorComponent } from './pages/page/administrator/administrator.component';
import { HomeComponent } from './pages/page/home/home.component';
import { AdminUpdateComponent } from './pages/page/admin-update/admin-update.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LayoutComponent,
    RegisterComponent,
    ProductComponent,
    AdministratorComponent,
    HomeComponent,
    AdminUpdateComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule, RouterModule, 
    AppRoutingModule, FormsModule, HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
