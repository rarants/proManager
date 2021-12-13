import { Component, OnInit } from '@angular/core';
import { faSignOutAlt } from "@fortawesome/free-solid-svg-icons";
import { faHome } from "@fortawesome/free-solid-svg-icons";
import { faUser } from "@fortawesome/free-solid-svg-icons";
import { faBars } from '@fortawesome/free-solid-svg-icons';
import * as bootstrap from 'bootstrap';
import { Modal } from 'bootstrap';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  modalComponent: Modal | undefined;
  displayMenuSmallDevice = false;

  faSignOutAlt = faSignOutAlt;
  faHome = faHome;
  faUser = faUser;
  faBars = faBars;

  setShowModal() { 
    var modal = document.getElementById("modalShow");
    this.modalComponent = new bootstrap.Modal(modal || "{}");
    this.modalComponent.show();
  }
}

