import { Component, OnInit } from '@angular/core';
import * as CanvasJS from '../canvasjs.min';

import { Router } from '@angular/router';
import { Category } from '../../../entities/category';
import { CategoryService } from '../../../providers/services/category.service';
import { CategoryStatsService } from '../../../providers/services/categoriesstats.service';
import { CommonStatsService } from '../../../providers/services/commonstats.service';
import { Catalogue } from '../../../entities/catalogue';
import { CatalogueService } from '../../../providers/services/catalogue.service';
import { Month } from '../../../entities/enums/Month';

@Component({
  selector: 'app-products-share-in-category',
  templateUrl: './products-share-in-category.component.html',
  styleUrls: ['./products-share-in-category.component.css']
})
export class ProductsShareInCategoryComponent implements OnInit {

  catalogues: Catalogue[];
  private selectedCatalogue = 9012;

  categories: Category[];

  category: Category;
  private selectedCategory;

  private dataPoints = [];

  constructor(private catalogueService: CatalogueService, private categoryService: CategoryService, private categoryStatsService: CategoryStatsService, private commonStatsService: CommonStatsService, private router: Router) { }

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

  populateChart(categoryId: Number, dataPoints) {
    this.catalogueService.getCatalogueById(this.selectedCatalogue).subscribe(catalogue => {
      this.categoryStatsService.getProductsShareInCategoryStatisticDataByMonth((<any>categoryId * 100) + <any>Month[catalogue.month]).subscribe(statData => {

        this.generateData(dataPoints, statData);
        this.generateChart();

      });
    });
  }

  generateChart() {
    let chart = new CanvasJS.Chart("chartContainer", {
      exportEnabled: true,
      animationEnabled: true,
      title: {
        text: "Products share in category sales"
      },
      data: [{
        type: "pie",
        toolTipContent: "{name}: <strong>{y}%</strong>",
        indexLabel: "{name} - {y}%",
        dataPoints: this.dataPoints
      }]
    });
    chart.render();
  }

  generateData(dataPoints, statData: Map<String, Number>) {
    let y = 0;
    let x = 0;

    for (var key in statData) {
      if (statData.hasOwnProperty(key)) {
        y = <any>statData[key];
        x = <any>key;

        dataPoints.push({ y: y, name: x });
      }
    };
  }

  changeCategory() {
    this.dataPoints = [];

    this.categoryService.getCategoryById(this.selectedCategory).subscribe(data => {
      this.category = data;
      this.populateChart(this.category.id, this.dataPoints);
    });
  }

  changeMonth() {
    if (this.category) {
      this.changeCategory();
    }
  }

}