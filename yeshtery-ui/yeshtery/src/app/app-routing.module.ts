import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './pages/layout/layout/layout.component';
import { AdminUpdateComponent } from './pages/page/admin-update/admin-update.component';
import { AdministratorComponent } from './pages/page/administrator/administrator.component';
import { HomeComponent } from './pages/page/home/home.component';
import { LoginComponent } from './pages/page/login/login.component';
import { ProductComponent } from './pages/page/product/product.component';
import { RegisterComponent } from './pages/page/register/register.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {

        path: 'Yeshtery',
        component: HomeComponent
      },
      {
        path: 'Yeshtery/administrator',
        component: AdministratorComponent
      }
    ]
  },
  {
    path: 'Yeshtery/login',
    component: LoginComponent
  },
  {
    path: 'Yeshtery/register',
    component: RegisterComponent
  },
  {
    path: 'Yeshtery/product',
    component: ProductComponent
  },
  {
    path: 'Yeshtery/update.product/:product',
    component: AdminUpdateComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
