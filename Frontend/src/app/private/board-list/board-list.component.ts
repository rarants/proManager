import { Component, ElementRef, OnInit, ViewChild } from "@angular/core";

import { faAngleRight } from "@fortawesome/free-solid-svg-icons";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import * as bootstrap from "bootstrap";
import { Modal } from "bootstrap";

import { Board } from "../interfaces/board";
import { BoardsService } from "../services/boards.service";


@Component({
  selector: "app-board-list",
  templateUrl: "./board-list.component.html",
  styleUrls: ["./board-list.component.css"],
})
export class BoardListComponent implements OnInit {

  model: Board = {
    id: 0,
    title: "",
    description: "",
    collumns: []
  };

  boards: Board[] = [];
  modalComponent: Modal | undefined;
  faAngleRight = faAngleRight;
  faPlus = faPlus;
  error = "";
  empty = true;

  ngOnInit(): void {
    this.getBoards();
  }

  constructor(private boardsService: BoardsService) {}

  setShowModalCard() {
    var modal = document.getElementById("modalBoard");
    this.modalComponent = new bootstrap.Modal(modal || "{}");
    this.modalComponent.show();
  }

  setHideModalCard() {
    var modal = document.getElementById("closeButton");
    modal?.click();
  }

  getBoards() {
    this.boardsService.getBoardList().subscribe(
      (response) => {
        this.boards = response;
      },
      (error) => {
        this.error = error;
      }
    );
    if (this.boards.length) this.empty = false;

  }

  handleSubmitNewBoard() {
    try {
      this.boardsService.postBoard(this.model).subscribe(
        (response) => {
          this.boards.push(response);
        },
        (error) => {
          this.error = error;
        }
      );
      this.setHideModalCard();
    } catch (e) {
      console.log(e);
    }
  }
}
