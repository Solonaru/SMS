import { Component, OnInit } from '@angular/core';
import * as CanvasJS from '../canvasjs.min';
import { Category } from '../../../entities/category';
import { Item } from '../../../entities/item';
import { CategoryService } from '../../../providers/services/category.service';
import { ItemService } from '../../../providers/services/item.service';
import { ProductStatsService } from '../../../providers/services/productstats.service';

@Component({
  selector: 'app-product-forecast-month',
  templateUrl: './product-forecast-month.component.html',
  styleUrls: ['./product-forecast-month.component.css']
})
export class ProductForecastMonthComponent implements OnInit {

  categories: Category[];
  productsStatisticDataMonth: Map<Number, Number>;

  items: Item[];
  item: Item;
  private selectedCategory;
  private selectedValue;

  private displayAverage: boolean = true;

  private averageValue = 0;
  private averageLabel = "";
  private dataPoints = [];

  private dataPointsForecast = [];
  private forecast: Number = 0;
  private periods = 3;

  constructor(private categoryService: CategoryService, private itemService: ItemService, private productStatsService: ProductStatsService) { }

  ngOnInit() {
    this.populateCategories();
  }

  populateCategories() {
    this.categoryService.getCategories().subscribe(data => { this.categories = data });
  }

  populateChart(productId: Number, displayAverage) {
    this.productStatsService.getCompleteProductsStatisticDataMonth(productId).subscribe(statData => {
      this.productStatsService.getAverageFromStatisticData(statData).subscribe(average => {

        if (displayAverage) {
          this.averageValue = average;
          this.averageLabel = "" + Math.round(this.averageValue);
        }

        switch (this.forecast) {
          case 1:
            this.productStatsService.getMovingAverageForecast(statData, this.periods).subscribe(forecastData => {
              this.generateData(this.dataPoints, statData);
              this.generateData(this.dataPointsForecast, forecastData);
              this.generateChart();
            });
            break;

          default:
            this.generateData(this.dataPoints, statData);
            this.generateChart();
            break;
        }
      });
    });
  }

  generateChart() {
    let chart = new CanvasJS.Chart("chartContainer", {
      zoomEnabled: true,
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Product Sales by Month"
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
          value: this.averageValue,
          label: this.averageLabel,
          color: "#0059b3",
          labelFontColor: "#0059b3"
        }]
      },
      legend: {
        cursor: "pointer",
        verticalAlign: "top",
        horizontalAlign: "center",
      },
      data: [
        {
          name: "Actual sales",
          type: "spline",
          xValueFormatString: "MMM, YYYY",
          color: "#003366",
          showInLegend: true,
          dataPoints: this.dataPoints
        },
        {
          name: "Forecast",
          type: "spline",
          xValueFormatString: "MMM, YYYY",
          color: "#b30100",
          showInLegend: true,
          lineDashType: "dash",
          dataPoints: this.dataPointsForecast
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
        // console.log(key + " -> " + statData[key]);
        y = <any>statData[key];
        x = <any>key;
        month = x % 100;
        year = Math.floor(x / 100);

        dataPoints.push({ x: new Date(year, month, 1), y: y });
      }
    }

  }

  changeItem() {
    this.averageValue = 0;
    this.averageLabel = "";
    this.dataPoints = [];
    this.dataPointsForecast = [];

    this.itemService.getItemById(this.selectedValue).subscribe(data => {
      this.item = data;
      this.populateChart(this.item.id, this.displayAverage);
    });
  }

  onDisplayAverage() {
    this.changeItem();
  }

  onDisplayForecast() {
    this.changeItem();
  }

  onChangePeriod() {
    if (this.forecast == 1) {
      this.changeItem();
    }
  }

  changeCategory() {
    this.itemService.getListedItemsByCategoryId(this.selectedCategory).subscribe(data => { this.items = data });
  }

}
