import { Component, OnInit } from '@angular/core';
import * as CanvasJS from '../canvasjs.min';

import { Catalogue } from '../../../entities/catalogue';
import { Category } from '../../../entities/category';
import { CatalogueService } from '../../../providers/services/catalogue.service';
import { CategoryService } from '../../../providers/services/category.service';
import { CategoryStatsService } from '../../../providers/services/categoriesstats.service';
import { TopProductsDataRequest } from '../../../entities/helper-classes/top-products-data-request';
import { Month } from '../../../entities/enums/Month';

@Component({
  selector: 'app-top-products-by-quantity-chart',
  templateUrl: './top-products-by-quantity-chart.component.html',
  styleUrls: ['./top-products-by-quantity-chart.component.css']
})
export class TopProductsByQuantityChartComponent implements OnInit {

  topProductsDataRequest: TopProductsDataRequest;

  catalogues: Catalogue[];
  private selectedMonth = 9012;
  private selectedAmount = 3;

  categories: Category[];

  category: Category;
  private selectedCategory;

  private dataPoints = [];

  constructor(private catalogueService: CatalogueService, private categoryService: CategoryService, private categoryStatsService: CategoryStatsService) { }

  ngOnInit() {
    this.populateCatalogues();
    this.populateCategories();
  }

  populateCatalogues() {
    this.catalogueService.getCatalogues().subscribe(data => { this.catalogues = data; });
  }

  populateCategories() {
    this.categoryService.getNoParentCategories().subscribe(data => { this.categories = data; });
  }

  populateChart() {

    this.catalogueService.getCatalogueById(this.selectedMonth).subscribe(catalogue => {

      this.topProductsDataRequest = new TopProductsDataRequest();
      this.topProductsDataRequest.categoryId = this.category.id;
      this.topProductsDataRequest.month = catalogue.month;
      this.topProductsDataRequest.amount = this.selectedAmount;

      console.log(this.topProductsDataRequest);

      this.categoryStatsService.getTopProductsByCategoryBasedOnQuantityStatisticDataByMonth(this.topProductsDataRequest).subscribe(data => {
        this.generateData(data);
        this.generateChart();
      });
    });
  }

  generateChart() {
    let chart = new CanvasJS.Chart("chartContainer", {
      animationEnabled: true,

      title: {
        text: "Top products based on quantity sold"
      },
      axisX: {
        interval: 1
      },
      axisY2: {
        interlacedColor: "rgba(1,77,101,.2)",
        gridColor: "rgba(1,77,101,.1)",
        title: "Quantity"
      },
      data: [{
        type: "bar",
        name: "products",
        axisYType: "secondary",
        color: "#014D65",
        yValueFormatString: "### units",
        dataPoints: this.dataPoints
      }]
    });
    chart.render();
  }

  generateData(statData: Map<Number, String>) {
    let x = 0;
    let y = "";

    for (var key in statData) {
      if (statData.hasOwnProperty(key)) {
        x = parseInt(key, 10);
        y = <any>statData[key];

        this.dataPoints.push({ y: x, label: y });
      }
    };
  }

  changeCategory() {
    this.dataPoints = [];

    this.categoryService.getCategoryById(this.selectedCategory).subscribe(data => {
      this.category = data;
      this.populateChart();
    });
  }

  changeMonth() {
    if (this.category) {
      this.changeCategory();
    }
  }

  changeAmount() {
    if (this.category) {
      this.changeCategory();
    }
  }

}
