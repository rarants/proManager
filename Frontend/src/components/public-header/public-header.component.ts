import { Component } from '@angular/core';
import * as bootstrap from 'bootstrap';
import { Modal } from 'bootstrap';


@Component({
  selector: 'app-public-header',
  templateUrl: './public-header.component.html',
  styleUrls: ['./public-header.component.css']
})
export class PublicHeaderComponent {
  modalComponent: Modal | undefined;
  modal = {
    status: false,
    type: ""
  } 
  setShowModal(type:string) { 
    this.modal.status = true;
    this.modal.type = type;
    var modal = document.getElementById("modalShow");
    this.modalComponent = new bootstrap.Modal(modal || "{}");
    this.modalComponent.show();
  }
}
