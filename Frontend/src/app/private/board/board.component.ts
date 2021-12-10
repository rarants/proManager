import { Component, OnInit } from '@angular/core';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { faAngleLeft } from '@fortawesome/free-solid-svg-icons';

import * as bootstrap from 'bootstrap';
import { Modal } from 'bootstrap';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent {
  modalComponent: Modal | undefined;
  modals = {
    modalTask: false,
    modalColumn: false
  }
  faEdit = faEdit;
  faPlus = faPlus;
  faAngleLeft = faAngleLeft;
  board = {
    id: 0,
    title: "Projeto Atos",
    description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ac.",
    collumns: [
      {
        id: 1,
        title: "To do",
        tasks: [
          {
            id: 1,
            title: "Tarefa 1",
            description: "Lorem ipsum dolor"
          },
          {
            id: 2,
            title: "Tarefa 2",
            description: "Lorem ipsum dolor"
          }
        ]
      },
      {
        id: 2,
        title: "Completed",
        tasks: []
      }
    ]
  }
  setShowModalTask() { 
    this.modals.modalTask = true;
    var modal = document.getElementById("modalTask");
    this.modalComponent = new bootstrap.Modal(modal || "{}");
    this.modalComponent.show();
  }
  setShowModalCol() { 
    this.modals.modalColumn = true;
    var modal = document.getElementById("modalCol");
    this.modalComponent = new bootstrap.Modal(modal || "{}");
    this.modalComponent.show();
  }
}
