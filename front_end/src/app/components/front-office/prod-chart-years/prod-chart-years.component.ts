import { Component, OnInit } from '@angular/core';
import * as CanvasJS from '../product-chart/canvasjs.min';

import { ItemService } from '../../../providers/services/item.service';
import { Item } from '../../../entities/item';
import { Router } from '@angular/router';
import { CartLineService } from '../../../providers/services/cartline.service';

@Component({
  selector: 'app-prod-chart-years',
  templateUrl: './prod-chart-years.component.html',
  styleUrls: ['./prod-chart-years.component.css']
})
export class ProdChartYearsComponent implements OnInit {

  items: Item[];
  productsStatisticDataYear: Map<Number, Number>;

  item: Item;
  private selectedValue;

  constructor(private itemService: ItemService, private cartLineService: CartLineService, private router: Router) { }

  ngOnInit() {

    this.populateItems();

    let dataPoints = [];
    let chart = new CanvasJS.Chart("chartContainer", {
      zoomEnabled: true,
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Estimated Product Sales per Year"
      },
      subtitles: [{
        text: "Try Zooming and Panning"
      }],
      data: [
        {
          type: "spline",
          dataPoints: dataPoints
        }]
    });

    chart.render();

  }

  populateItems() {
    this.itemService.getItems().subscribe(data => { this.items = data; console.log(this.items); });
  }

  populateProductsStatisticDataYear(productId: Number) {
    this.cartLineService.getProductsStatisticDataYear(productId).subscribe(data => { this.productsStatisticDataYear = data; console.log(this.productsStatisticDataYear); })
  }

  changeItem() {
    let dataPoints = [];
    let y = 0;
    let x = 0;

    this.itemService.getItemById(this.selectedValue).subscribe(data => { this.item = data });

    this.populateProductsStatisticDataYear(this.item.id);

    for (var key in this.productsStatisticDataYear) {
      if (this.productsStatisticDataYear.hasOwnProperty(key)) {
        console.log(key + " -> " + this.productsStatisticDataYear[key]);
        y = <any> this.productsStatisticDataYear[key];
        x = <any> key;
        dataPoints.push({ x: x*10, y: y });
      }
    }

  }
}
