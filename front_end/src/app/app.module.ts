import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { AdminComponent } from './components/admin/admin.component';
import { ProductsComponent } from './components/products/products.component';
import { CategoryComponent } from './components/category/category.component';
import { AuthService } from './providers/auth.service';
import { DataService } from './providers/data.service';
import { AuthGuard } from './providers/auth.guard';
import { SessionGuard } from './providers/session.guard';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    AdminComponent,
    ProductsComponent,
    CategoryComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      {
        path: '', redirectTo: '/login', pathMatch: 'full'
      },
      {
        path: 'login',
        component: LoginComponent,
        canActivate: [SessionGuard]
      },
      {
        path: 'admin',
        component: AdminComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'products',
        component: ProductsComponent
      },
      {
        path: 'category',
        component: CategoryComponent
      }
    ])
  ],
  providers: [AuthService, DataService, AuthGuard, SessionGuard, CategoryComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
