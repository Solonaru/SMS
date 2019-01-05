import { Component, OnInit } from '@angular/core';
import * as CanvasJS from '../canvasjs.min';
import { Category } from '../../../entities/category';
import { Item } from '../../../entities/item';
import { CategoryService } from '../../../providers/services/category.service';
import { ItemService } from '../../../providers/services/item.service';
import { ProductStatsService } from '../../../providers/services/productstats.service';

@Component({
  selector: 'app-product-price-sales-chart',
  templateUrl: './product-price-sales-chart.component.html',
  styleUrls: ['./product-price-sales-chart.component.css']
})
export class ProductPriceSalesChartComponent implements OnInit {

  categories: Category[];
  productsStatisticData: Map<Number, Number>;

  items: Item[];
  item: Item;
  private selectedCategory;
  private selectedValue;

  private dataPoints = [];
  private dataPoints2 = [];

  constructor(private categoryService: CategoryService, private itemService: ItemService, private productStatsService: ProductStatsService) { }

  ngOnInit() {
    this.populateCategories();
  }

  populateCategories() {
    this.categoryService.getCategories().subscribe(data => { this.categories = data });
  }

  populateChart(productId: Number) {
    this.productStatsService.getProductsStatisticDataBasedOnPrice(productId).subscribe(statData => {
      this.generateData(statData);
      this.generateChart();
    });
  }

  generateChart() {
    console.log("DATA POINTS: ");
    console.log(this.dataPoints);
    let chart = new CanvasJS.Chart("chartContainer", {
      zoomEnabled: true,
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Product Sales based on Price"
      },
      subtitles: [{

      }],
      toolTip: {
        shared: true
      },
      axisX: {
        title: "Price",
        crosshair: {
          enabled: true,
          snapToDataPoint: true
        }
      },
      axisY: {
        title: "Total sales",
        titleFontColor: "#4F81BC",
        crosshair: {
          enabled: true,
          snapToDataPoint: true
        },
        suffix: " lei",
        lineColor: "#4F81BC",
        tickColor: "#4F81BC"
      },
      axisY2: {
        title: "Quantity",
        titleFontColor: "#C0504E",
        suffix: " units",
        lineColor: "#C0504E",
        tickColor: "#C0504E"
      },
      data: [
        {
          name: "Total sales",
          type: "spline",
          markerType: "square",
          xValueFormatString: "#,##0.00 lei",
          yValueFormatString: "#,##0.00 lei",
          dataPoints: this.dataPoints
        },
        {
          name: "Quantity",
          visible: false,
          yValueFormatString: "### units",
          dataPoints: this.dataPoints2
        }
      ]
    });

    chart.render();
  }

  generateData(statData: Map<Number, Number>) {
    let y = 0;
    let x = 0;

    for (var key in statData) {
      if (statData.hasOwnProperty(key)) {
        y = <any>statData[key];
        x = <any>key;
        this.dataPoints.push({ x: x, y: y });
        this.dataPoints2.push({ x: x, y: y / x });
      }
    };
  }

  changeCategory() {
    this.itemService.getListedItemsByCategoryId(this.selectedCategory).subscribe(data => { this.items = data });
  }

  changeItem() {
    this.dataPoints = [];
    this.dataPoints2 = [];

    this.itemService.getItemById(this.selectedValue).subscribe(data => {
      this.item = data;
      this.populateChart(this.item.id);
    });
  }

}
