import { Component, OnInit } from '@angular/core';
import * as CanvasJS from '../product-chart/canvasjs.min';

import { ItemService } from '../../../providers/services/item.service';
import { Item } from '../../../entities/item';
import { Router } from '@angular/router';
import { CartLineService } from '../../../providers/services/cartline.service';

@Component({
  selector: 'app-product-chart-month',
  templateUrl: './product-chart-month.component.html',
  styleUrls: ['./product-chart-month.component.css']
})
export class ProductChartMonthComponent implements OnInit {

  items: Item[];
  productsStatisticDataMonth: Map<Number, Number>;
  private dataPoints = [];

  item: Item;
  private selectedValue;

  constructor(private itemService: ItemService, private cartLineService: CartLineService, private router: Router) { }

  ngOnInit() {
    this.populateItems();
  }

  populateItems() {
    this.itemService.getItems().subscribe(data => { this.items = data; });
  }

  populateProductsStatisticDataMonth(productId: Number) {
    let y = 0;
    let x = 0;

    this.cartLineService.getProductsStatisticDataMonth(productId).subscribe(data => {
      this.productsStatisticDataMonth = data;

      for (var key in this.productsStatisticDataMonth) {
        if (this.productsStatisticDataMonth.hasOwnProperty(key)) {
          console.log(key + " -> " + this.productsStatisticDataMonth[key]);
          y = <any>this.productsStatisticDataMonth[key];
          x = <any>key;
          this.dataPoints.push({ x: x * 10, y: y });
        }
      };

      let chart = new CanvasJS.Chart("chartContainer", {
        zoomEnabled: true,
        animationEnabled: true,
        exportEnabled: true,
        title: {
          text: "Product Sales by Month"
        },
        subtitles: [{
          text: "Try Zooming and Panning"
        }],
        data: [
          {
            type: "spline",
            dataPoints: this.dataPoints
          }]
      });

      chart.render();
      this.dataPoints = [];
    });
  }

  changeItem() {
    this.itemService.getItemById(this.selectedValue).subscribe(data => {
      this.item = data;
      this.populateProductsStatisticDataMonth(this.item.id);
    });
  }

}
