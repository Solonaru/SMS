import { BrowserModule } from '@angular/platform-browser';
import { AgmCoreModule } from "@agm/core";
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/front-office/login/login.component';
import { AdminComponent } from './components/front-office/admin/admin.component';
import { ProductsComponent } from './components/front-office/products/products.component';
import { CategoryComponent } from './components/front-office/category/category.component';
import { AboutusComponent } from './components/front-office/aboutus/aboutus.component';
import { ContactComponent } from './components/front-office/contact/contact.component';
import { BoProductsComponent } from './components/back-office/bo-products/bo-products.component';
import { BoEmployeesComponent } from './components/back-office/bo-employees/bo-employees.component';
import { HeaderComponent } from './components/front-office/header/header.component';
import { AdmHomeComponent } from './components/back-office/adm-home/adm-home.component';
import { ProductChartMonthComponent } from './components/stats/product-chart-month/product-chart-month.component';
import { AppRoutingModule } from './app-routing.module';
import { FooterComponent } from './components/front-office/footer/footer.component';
import { ProductPriceSalesChartComponent } from './components/stats/product-price-sales-chart/product-price-sales-chart.component';
import { ProductForecastMonthComponent } from './components/stats/product-forecast-month/product-forecast-month.component';
import { CategoryChartMonthComponent } from './components/stats/category-chart-month/category-chart-month.component';
import { BiComponent } from './components/back-office/bi/bi.component';
import { CategoryForecastMonthComponent } from './components/stats/category-forecast-month/category-forecast-month.component';
import { ProductsShareInCategoryComponent } from './components/stats/products-share-in-category/products-share-in-category.component';
import { TopProductsByQuantityChartComponent } from './components/stats/top-products-by-quantity-chart/top-products-by-quantity-chart.component';
import { TopProductsByPriceChartComponent } from './components/stats/top-products-by-price-chart/top-products-by-price-chart.component';
import { SlideshowModule } from 'ng-simple-slideshow';

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
    ProductChartMonthComponent,
    FooterComponent,
    ProductPriceSalesChartComponent,
    ProductForecastMonthComponent,
    CategoryChartMonthComponent,
    CategoryForecastMonthComponent,
    BiComponent,
    ProductsShareInCategoryComponent,
    TopProductsByQuantityChartComponent,
    TopProductsByPriceChartComponent
  ],

  imports: [
    FormsModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
	SlideshowModule,
    ChartsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDry7EiIG3ytckPPkpRCV4HrxNz180q2JU'
    }),
  ],

  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [],
  bootstrap: [AppComponent]

})

export class AppModule { }
