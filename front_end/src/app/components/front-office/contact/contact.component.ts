import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  name: string;
  email: string;
  message: string;

  lat: number= 47.17508495;
  lng: number= 27.57388292831633;
  click(event){
    this.lat=event.coords.lat;
    this.lng=event.coords.lng;
    console.log(event);
  }
  constructor() { }

  ngOnInit() {
  }
   processForm() {
    const allInfo = `My name is ${this.name}. My email is ${this.email}. My message is ${this.message}`;
    alert(allInfo);
    }
}
