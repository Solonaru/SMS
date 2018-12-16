import { Component, OnInit } from '@angular/core';
import * as CanvasJS from './canvasjs.min';

import { ItemService } from '../../../providers/services/item.service';
import { Item } from '../../../entities/item';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-chart',
  templateUrl: './product-chart.component.html',
  styleUrls: ['./product-chart.component.css']
})
export class ProductChartComponent implements OnInit {

  items: Item[];

  constructor(private itemService: ItemService, private router: Router) { }

  ngOnInit() {

    this.populateItems();

    let dataPoints = [];
    let chart = new CanvasJS.Chart("chartContainer", {
      zoomEnabled: true,
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Products Chart"
      },
      subtitles: [{
        text: "Try Zooming and Panning"
      }],
      data: [
        {
          type: "line",
          dataPoints: dataPoints
        }]
    });

    chart.render();

  }

  populateItems() {
    this.itemService.getItems().subscribe(data => { this.items = data; console.log(this.items); });
  }

  changeItem() {
    let dataPoints = [];
    let y = 0;
    
    for (var i = 1; i <= 11; i++) {
      y += Math.round(5 + Math.random() * (-5 - 5));
      dataPoints.push({ y: y });
    }

    let chart = new CanvasJS.Chart("chartContainer", {
      zoomEnabled: true,
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Product Chart"
      },
      subtitles: [{
        text: "Try Zooming and Panning"
      }],
      data: [
        {
          type: "line",
          dataPoints: dataPoints
        }]
    });

    chart.render();
  }

}
