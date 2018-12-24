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
import { ProductChartComponent } from './components/front-office/product-chart/product-chart.component';
import { ProductChartMonthComponent } from './components/front-office/product-chart-month/product-chart-month.component';
import { ProdChartComponent } from './components/front-office/prod-chart/prod-chart.component';
import { ProdChartYearsComponent } from './components/front-office/prod-chart-years/prod-chart-years.component';

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
    { path: 'header', component: HeaderComponent },
    { path: 'adm', component: AdmHomeComponent },
    { path: 'product-chart', component: ProductChartComponent },
    { path: 'product-chart-month', component: ProductChartMonthComponent },
    { path: 'prod-chart', component: ProdChartComponent },
    { path: 'prod-chart-years', component: ProdChartYearsComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }