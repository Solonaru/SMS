import { Component } from '@angular/core';

import { ItemService } from '../../../providers/services/item.service';
import { Item } from '../../../entities/item';
import { Router } from '@angular/router';
import { ProductStatsService } from '../../../providers/services/productstats.service';

@Component({
  selector: 'app-prod-chart',
  templateUrl: './prod-chart.component.html',
  styleUrls: ['./prod-chart.component.css']
})
export class ProdChartComponent {

  items: Item[];
  productsStatisticDataMonth: Map<Number, Number>;

  item: Item;
  private selectedValue;

  constructor(private itemService: ItemService, private productStatsService: ProductStatsService, private router: Router) { }

  ngOnInit() {
    this.populateItems();
  }

  populateItems() {
    this.itemService.getItems().subscribe(data => { this.items = data; console.log(this.items); });
  }

  populateProductsStatisticDataMonth(productId: Number) {
    this.productStatsService.getProductsStatisticDataMonth(productId).subscribe(data => { this.productsStatisticDataMonth = data; console.log(this.productsStatisticDataMonth); })
  }

  changeItem() {
    let dataPoints = [];
    let x = 0;
    let y = 0;
    let months = [];

    this.itemService.getItemById(this.selectedValue).subscribe(data => { this.item = data });

    this.populateProductsStatisticDataMonth(this.item.id);

    for (var key in this.productsStatisticDataMonth) {
      if (this.productsStatisticDataMonth.hasOwnProperty(key)) {
        console.log(key + " -> " + this.productsStatisticDataMonth[key]);
        y = <any> this.productsStatisticDataMonth[key];
        x = <any> key;
        dataPoints.push(y);
        months.push(x);
        console.log(months);
        console.log(dataPoints);
      }
    }
    this.lineChartLabels = months;
    if(dataPoints.length != 0) {
      this.lineChartData.shift();
      this.lineChartData.push({data: dataPoints, label:this.item.name});
    }
  }

  // lineChart
  public lineChartData:Array<any> = [
    // Initial data
  ];
  public lineChartLabels:Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
  public lineChartOptions:any = {
    responsive: true
  };
  public lineChartColors:Array<any> = [
    { // grey
      backgroundColor: 'rgba(82, 179, 217, 0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
    { // dark grey
      backgroundColor: 'rgba(77,83,96,0.2)',
      borderColor: 'rgba(77,83,96,1)',
      pointBackgroundColor: 'rgba(77,83,96,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(77,83,96,1)'
    },
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];
  public lineChartLegend:boolean = true;
  public lineChartType:string = 'line';
}