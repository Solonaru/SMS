import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/front-office/login/login.component';
import { SessionGuard } from './providers/guards/session.guard';
import { AdminComponent } from './components/front-office/admin/admin.component';
import { AuthGuard } from './providers/guards/auth.guard';
import { ProductsComponent } from './components/front-office/products/products.component';
import { CategoryComponent } from './components/front-office/category/category.component';
import { ContactComponent } from './components/front-office/contact/contact.component';
import { AboutusComponent } from './components/front-office/aboutus/aboutus.component';
import { BoProductsComponent } from './components/back-office/bo-products/bo-products.component';
import { BoEmployeesComponent } from './components/back-office/bo-employees/bo-employees.component';
import { HeaderComponent } from './components/front-office/header/header.component';
import { AdmHomeComponent } from './components/back-office/adm-home/adm-home.component';
import { ProductChartMonthComponent } from './components/stats/product-chart-month/product-chart-month.component';
import { FooterComponent } from './components/front-office/footer/footer.component';
import { ProductPriceSalesChartComponent } from './components/stats/product-price-sales-chart/product-price-sales-chart.component';
import { ProductForecastMonthComponent } from './components/stats/product-forecast-month/product-forecast-month.component';
import { CategoryChartMonthComponent } from './components/stats/category-chart-month/category-chart-month.component';
import { BiComponent } from './components/back-office/bi/bi.component';
import { CategoryForecastMonthComponent } from './components/stats/category-forecast-month/category-forecast-month.component';

const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent, canActivate: [SessionGuard] },
    { path: 'admin', component: AdminComponent, canActivate: [AuthGuard] },
    { path: 'products', component: ProductsComponent },
    { path: 'category', component: CategoryComponent },
    { path: 'contact', component: ContactComponent },
    { path: 'aboutus', component: AboutusComponent },
    { path: 'bo_products', component: BoProductsComponent },
    { path: 'bo_employees', component: BoEmployeesComponent },
    { path: 'bi', component: BiComponent },
    { path: 'header', component: HeaderComponent },
    { path: 'adm', component: AdmHomeComponent },
    { path: 'category-chart-month', component: CategoryChartMonthComponent },
    { path: 'category-forecast-month', component: CategoryForecastMonthComponent },
    { path: 'product-chart-month', component: ProductChartMonthComponent },
    { path: 'product-price-sales-chart', component: ProductPriceSalesChartComponent },
    { path: 'product-forecast-month', component: ProductForecastMonthComponent },
    { path: 'footer', component: FooterComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }
