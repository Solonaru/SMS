import { Address } from "./address";

export interface Employee {
    id: Number;
    username: String;
    password: String;
    name: String;
    email: String;
    creationDate: Date;
    address: Address;
}