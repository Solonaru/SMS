import { Component, OnInit } from '@angular/core';
import * as CanvasJS from '../canvasjs.min';

import { ItemService } from '../../../providers/services/item.service';
import { Item } from '../../../entities/item';
import { Router } from '@angular/router';
import { ProductStatsService } from '../../../providers/services/productstats.service';

@Component({
  selector: 'app-product-chart-month',
  templateUrl: './product-chart-month.component.html',
  styleUrls: ['./product-chart-month.component.css']
})
export class ProductChartMonthComponent implements OnInit {

  items: Item[];
  productsStatisticDataMonth: Map<Number, Number>;

  private displayAverage: boolean = true;

  private averageValue = 0;
  private averageLabel = "";
  private dataPoints = [];

  item: Item;
  private selectedValue;

  constructor(private itemService: ItemService, private productStatsService: ProductStatsService, private router: Router) { }

  ngOnInit() {
    this.populateItems();
  }

  populateItems() {
    this.itemService.getItems().subscribe(data => { this.items = data; });
  }

  populateChart(productId: Number) {
    this.productStatsService.getProductsStatisticDataMonth(productId).subscribe(data => {
      this.productsStatisticDataMonth = data;

      this.productStatsService.getAverageFromStatisticData(this.productsStatisticDataMonth).subscribe(data => {
        console.log("Display average? " + this.displayAverage);

        if (this.displayAverage) {
          this.averageValue = data;
          this.averageLabel = "Average = " + this.averageValue;
        }

        this.generateChart();
      });
    });
  }

  generateChart() {
    let y = 0;
    let x = 0;
    let month = 0;
    let year = 0;

    for (var key in this.productsStatisticDataMonth) {
      if (this.productsStatisticDataMonth.hasOwnProperty(key)) {
        console.log(key + " -> " + this.productsStatisticDataMonth[key]);
        y = <any>this.productsStatisticDataMonth[key];
        x = <any>key;
        month = x % 100;
        year = Math.floor(x / 100);

        this.dataPoints.push({ x: new Date(year, month, 1), y: y });
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
      axisX: {
        interval: 1,
        intervalType: "month",
        valueFormatString: "MMM"
      },
      axisY: {
        stripLines: [{
          value: this.averageValue,
          label: this.averageLabel
        }]
      },
      data: [
        {
          type: "spline",
          xValueFormatString: "MMM, YYYY",
          dataPoints: this.dataPoints
        }]
    });

    chart.render();
    this.averageValue = 0;
    this.averageLabel = "";
    this.dataPoints = [];
  }

  changeItem() {
    this.itemService.getItemById(this.selectedValue).subscribe(data => {
      this.item = data;
      this.populateChart(this.item.id);
    });
  }

  onDisplayAverage() {
    this.changeItem();
  }

}
