import { Component, OnInit } from '@angular/core';
import * as CanvasJS from '../canvasjs.min';

import { Router } from '@angular/router';
import { Category } from '../../../entities/category';
import { CategoryService } from '../../../providers/services/category.service';
import { CategoryStatsService } from '../../../providers/services/categoriesstats.service';
import { CommonStatsService } from '../../../providers/services/commonstats.service';
import { CatalogueService } from '../../../providers/services/catalogue.service';
import { DataRequest } from '../../../entities/helper-classes/request';
import { Catalogue } from '../../../entities/catalogue';

@Component({
  selector: 'app-category-chart-month',
  templateUrl: './category-chart-month.component.html',
  styleUrls: ['./category-chart-month.component.css']
})
export class CategoryChartMonthComponent implements OnInit {

  dataRequest: DataRequest;
  catalogues: Catalogue[];
  private selectedMonthStart = 9001;
  private selectedMonthEnd = 9012;
  private selectedMinValue = "0";
  private selectedMaxValue = "50000";

  categories: Category[];

  category1: Category;
  private selectedCategory1;

  private displayAverage1: boolean = true;
  private averageValue1 = 0;
  private averageLabel1 = "";
  private dataPoints1 = [];

  category2: Category;
  private selectedCategory2;

  private displayAverage2: boolean = true;
  private averageValue2 = 0;
  private averageLabel2 = "";
  private dataPoints2 = [];

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

  populateChart(categoryId: Number, displayAverage, num, dataPoints) {

    this.catalogueService.getCatalogueById(this.selectedMonthStart).subscribe(catalogueStart => {
      this.catalogueService.getCatalogueById(this.selectedMonthEnd).subscribe(catalogueEnd => {
        this.dataRequest = new DataRequest();
        this.dataRequest.objectId = categoryId;
        this.dataRequest.monthStart = catalogueStart.month;
        this.dataRequest.monthEnd = catalogueEnd.month;
        this.dataRequest.minValue = parseInt(this.selectedMinValue, 10);
        this.dataRequest.maxValue = parseInt(this.selectedMaxValue, 10);

        this.categoryStatsService.getCategoriesComplexStatisticDataByMonth(this.dataRequest).subscribe(statData => {
          this.commonStatsService.getAverageFromStatisticData(statData).subscribe(average => {

            if (displayAverage) {
              switch (num) {
                case 1:
                  this.averageValue1 = average;
                  this.averageLabel1 = "" + Math.round(this.averageValue1);
                  break;
                case 2:
                  this.averageValue2 = average;
                  this.averageLabel2 = "" + Math.round(this.averageValue2);
                  break;
              }
            }

            this.generateData(dataPoints, statData);
            this.generateChart();
          });
        });
      });
    });
  }

  generateChart() {
    let chart = new CanvasJS.Chart("chartContainer", {
      zoomEnabled: true,
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Category Sales by Month"
      },
      axisX: {
        title: "Month",
        interval: 1,
        intervalType: "month",
        valueFormatString: "MMM"
      },
      axisY: {
        title: "Total sales",
        stripLines: [{
          value: this.averageValue1,
          label: this.averageLabel1,
          color: "#0059b3",
          labelFontColor: "#0059b3"
        },
        {
          value: this.averageValue2,
          label: this.averageLabel2,
          color: "#ff0200",
          labelFontColor: "#ff0200"
        }
        ]
      },
      legend: {
        cursor: "pointer",
        verticalAlign: "top",
        horizontalAlign: "center",
      },
      data: [
        {
          name: "Category 1",
          type: "spline",
          xValueFormatString: "MMM, YYYY",
          color: "#003366",
          showInLegend: true,
          dataPoints: this.dataPoints1
        },
        {
          name: "Category 2",
          type: "spline",
          xValueFormatString: "MMM, YYYY",
          color: "#b30100",
          showInLegend: true,
          dataPoints: this.dataPoints2
        }
      ]
    });

    chart.render();
  }

  generateData(dataPoints, statData: Map<Number, Number>) {
    let y = 0;
    let x = 0;
    let month = 0;
    let year = 0;

    for (var key in statData) {
      if (statData.hasOwnProperty(key)) {
        y = <any>statData[key];
        x = <any>key;
        month = x % 100;
        year = Math.floor(x / 100);

        dataPoints.push({ x: new Date(year, month, 1), y: y });
      }
    };
  }

  changeCategory1() {
    this.averageValue1 = 0;
    this.averageLabel1 = "";
    this.dataPoints1 = [];

    this.categoryService.getCategoryById(this.selectedCategory1).subscribe(data => {
      this.category1 = data;
      this.populateChart(this.category1.id, this.displayAverage1, 1, this.dataPoints1);
    });
  }

  onDisplayAverage1() {
    if (this.category1) {
      this.changeCategory1();
    }
  }

  changeCategory2() {
    this.averageValue2 = 0;
    this.averageLabel2 = "";
    this.dataPoints2 = [];

    this.categoryService.getCategoryById(this.selectedCategory2).subscribe(data => {
      this.category2 = data;
      this.populateChart(this.category2.id, this.displayAverage2, 2, this.dataPoints2);
    });
  }

  onDisplayAverage2() {
    if (this.category2) {
      this.changeCategory2();
    }
  }

  changeMonthStart() {
    if (this.selectedMonthStart > this.selectedMonthEnd) {
      let temp = this.selectedMonthStart;
      this.selectedMonthStart = this.selectedMonthEnd;
      this.selectedMonthEnd = temp;
    }

    if (this.category1) {
      this.changeCategory1();
    }

    if (this.category2) {
      this.changeCategory2();
    }

  }

  changeMonthEnd() {
    if (this.selectedMonthStart > this.selectedMonthEnd) {
      let temp = this.selectedMonthStart;
      this.selectedMonthStart = this.selectedMonthEnd;
      this.selectedMonthEnd = temp;
    }

    if (this.category1) {
      this.changeCategory1();
    }

    if (this.category2) {
      this.changeCategory2();
    }

  }

  changeMinValue() {
    if (parseInt(this.selectedMinValue, 10) > parseInt(this.selectedMaxValue, 10)) {
      let temp = this.selectedMinValue;
      this.selectedMinValue = this.selectedMaxValue;
      this.selectedMaxValue = temp;
    }

    if (this.category1) {
      this.changeCategory1();
    }

    if (this.category2) {
      this.changeCategory2();
    }

  }

  changeMaxValue() {
    if (parseInt(this.selectedMinValue, 10) > parseInt(this.selectedMaxValue, 10)) {
      let temp = this.selectedMinValue;
      this.selectedMinValue = this.selectedMaxValue;
      this.selectedMaxValue = temp;
    }

    if (this.category1) {
      this.changeCategory1();
    }

    if (this.category2) {
      this.changeCategory2();
    }

  }

}
