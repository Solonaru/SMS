import { Component, OnInit } from '@angular/core';
import * as CanvasJS from './canvasjs.min';

import { ItemService } from '../../../providers/services/item.service';
import { Item } from '../../../entities/item';
import { Router } from '@angular/router';
import { CartLineService } from '../../../providers/services/cartline.service';

@Component({
  selector: 'app-product-chart',
  templateUrl: './product-chart.component.html',
  styleUrls: ['./product-chart.component.css']
})
export class ProductChartComponent implements OnInit {

  items: Item[];
  productsStatisticData: Map<Date, Number>;

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
        text: "Product Sales by Day"
      },
      axisX: {
        valueFormatString: "MMM YYYY"
      },
      subtitles: [{
        text: "Try Zooming and Panning"
      }],
      data: [
        {
          type: "spline",
          name: "Product",
          showInLegend: true,
          markerSize: 0,
          yValueFormatString: "#,### lei",
          dataPoints: dataPoints
        }]
    });

    chart.render();

  }

  populateItems() {
    this.itemService.getItems().subscribe(data => { this.items = data; console.log(this.items); });
  }

  populateProductsStatisticData(productId: Number) {
    this.cartLineService.getProductsStatisticData(productId).subscribe(data => { this.productsStatisticData = data; console.log(this.productsStatisticData); })
  }

  changeItem() {
    let dataPoints = [];
    let y = 0;
    let x = null;

    this.itemService.getItemById(this.selectedValue).subscribe(data => { this.item = data });

    this.populateProductsStatisticData(this.item.id);

    for (var key in this.productsStatisticData) {
      if (this.productsStatisticData.hasOwnProperty(key)) {
        console.log(key + " -> " + this.productsStatisticData[key]);
        y = <any> this.productsStatisticData[key];
        x = new Date(<any>key);
        dataPoints.push({ x: x, y: y });
      }
    }

    let chart = new CanvasJS.Chart("chartContainer", {
      zoomEnabled: true,
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Product Sales by Day"
      },
      axisX: {
        valueFormatString: "MMM YYYY"
      },
      subtitles: [{
        text: "Try Zooming and Panning"
      }],
      data: [
        {
          type: "spline",
          name: "Product",
          showInLegend: true,
          markerSize: 0,
          yValueFormatString: "#,### lei",
          dataPoints: dataPoints
        }]
    });

    chart.render();
  }

}
