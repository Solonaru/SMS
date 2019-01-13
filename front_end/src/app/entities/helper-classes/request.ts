import { Month } from "../enums/Month";

export class DataRequest {
    objectId: Number;
    monthStart: Month;
    monthEnd: Month;
    minValue: Number;
    maxValue: Number;
}