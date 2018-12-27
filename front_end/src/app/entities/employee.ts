import { Address } from "./address";

export interface Employee {
    id: Number;
    username: String;
    password: String;
    name: String;
    email: String;
    phoneNumber: Number;
    creationDate: Date;
    address: Address;
}