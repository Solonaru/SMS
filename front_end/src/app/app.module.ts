import { BrowserModule } from '@angular/platform-browser';
import { AgmCoreModule } from "@agm/core";
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/front-office/login/login.component';
import { AdminComponent } from './components/front-office/admin/admin.component';
import { ProductsComponent } from './components/front-office/products/products.component';
import { CategoryComponent } from './components/front-office/category/category.component';
import { AuthService } from './providers/services/auth.service';
import { DataService } from './providers/services/data.service';
import { AuthGuard } from './providers/guards/auth.guard';
import { SessionGuard } from './providers/guards/session.guard';
import { AboutusComponent } from './components/front-office/aboutus/aboutus.component';
import { ContactComponent } from './components/front-office/contact/contact.component';
import { BoProductsComponent } from './components/back-office/bo-products/bo-products.component';
import { BoEmployeesComponent } from './components/back-office/bo-employees/bo-employees.component';
import { HeaderComponent } from './components/front-office/header/header.component';
import { AdmHomeComponent } from './components/back-office/adm-home/adm-home.component';
import { ProductChartComponent } from './components/front-office/product-chart/product-chart.component';
import { ProductChartMonthComponent } from './components/front-office/product-chart-month/product-chart-month.component';
import { ProdChartComponent } from './components/front-office/prod-chart/prod-chart.component';
import { ProdChartYearsComponent } from './components/front-office/prod-chart-years/prod-chart-years.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent,
    ProductsComponent,
    CategoryComponent,
    AboutusComponent,
    ContactComponent,
    BoProductsComponent,
    BoEmployeesComponent,
    HeaderComponent,
    AdmHomeComponent,
    ProductChartComponent,
    ProductChartMonthComponent,
    ProdChartComponent,
    ProdChartYearsComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    HttpClientModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDry7EiIG3ytckPPkpRCV4HrxNz180q2JU'
    }),
    ChartsModule,
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
      },
      {
        path: 'contact',
        component: ContactComponent
      },
      {
        path: 'aboutus',
        component: AboutusComponent
      },
      {
        path: 'bo_products',
        component: BoProductsComponent
      },
      {
        path: 'bo_employees',
        component: BoEmployeesComponent
      },
      {
        path: 'header',
        component: HeaderComponent
      },
      {
        path: 'adm',
        component: AdmHomeComponent
      },
      {
        path: 'product-chart',
        component: ProductChartComponent
      },
      {
        path: 'product-chart-month',
        component: ProductChartMonthComponent
      },
      {
        path: 'prod-chart',
        component: ProdChartComponent
      },
      {
        path: 'prod-chart-years',
        component: ProdChartYearsComponent
      }
    ]),
  ],
  schemas:  [ CUSTOM_ELEMENTS_SCHEMA ],
  providers: [AuthService, DataService, AuthGuard, SessionGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
