import { Component, OnInit } from "@angular/core";
import { faEdit } from "@fortawesome/free-solid-svg-icons";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { faAngleLeft } from "@fortawesome/free-solid-svg-icons";
import { BoardsService } from "../services/boards.service";

import * as bootstrap from "bootstrap";
import { Modal } from "bootstrap";
import { Board } from "../interfaces/board";
import { ActivatedRoute, ParamMap, Router } from "@angular/router";
import { CollumnsService } from "../services/collumns.service";
import { Collumn } from "../interfaces/collumn";

@Component({
  selector: "app-board",
  templateUrl: "./board.component.html",
  styleUrls: ["./board.component.css"],
})
export class BoardComponent implements OnInit {
  modalComponent: Modal | undefined;
  faEdit = faEdit;
  faPlus = faPlus;
  faAngleLeft = faAngleLeft;
  isEditing = false;
  board_id!: Number;
  collumns: Collumn[] = [];

  collumnModel: Collumn = {
    id: 0,
    title: "",
    board: {
      id: 0,
    },
  };

  tags = [
    {
      id: 1,
      title: "primary",
      color: "primary",
    },
    {
      id: 2,
      title: "dark",
      color: "dark",
    },
    {
      id: 3,
      title: "info",
      color: "info",
    },
    {
      id: 4,
      title: "danger",
      color: "danger",
    },
    {
      id: 5,
      title: "warning",
      color: "warning",
    },
    {
      id: 6,
      title: "success",
      color: "success",
    },
  ];

  board!: Board;
  error = "";

  constructor(
    private route: ActivatedRoute,
    private boardsService: BoardsService,
    private collumnsService: CollumnsService,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.board_id = +params.get("id")!;
    });
    this.getBoardInfo(this.board_id);
  }
  setShowModalCard() {
    var modal = document.getElementById("modalCard");
    this.modalComponent = new bootstrap.Modal(modal || "{}");
    this.modalComponent.show();
  }
  setShowModalCol() {
    var modal = document.getElementById("modalCol");
    this.modalComponent = new bootstrap.Modal(modal || "{}");
    this.modalComponent.show();
  }
  setShowModalBoard() {
    var modal = document.getElementById("modalBoard");
    this.modalComponent = new bootstrap.Modal(modal || "{}");
    this.modalComponent.show();
  }
  setHideModalCol() {
    var modal = document.getElementById("closeColButton");
    modal?.click();
  }
  setHideModalBoard() {
    var modal = document.getElementById("closeBoardButton");
    modal?.click();
  }
  getBoardInfo(id: Number) {
    this.boardsService.getBoard(id).subscribe(
      (response) => {
        this.board = response;
        this.getCollumnsList(id);
      },
      (error) => {
        this.error = error;
      }
    );
  }
  getCollumnsList(id: Number) {
    this.collumnsService.getCollumnList(id).subscribe((response) => {
      this.board.collumns = response;
    });
  }
  handleSubmitNewCollumn() {
    if (!this.isEditing){
      try {
        this.collumnModel.board.id = this.board.id;
        this.collumnsService.postCollumn(this.collumnModel).subscribe(
          (response) => {
            this.board.collumns.push(response);
          },
          (error) => {
            this.error = error;
          }
        );
        this.setHideModalCol();
      } catch (e) {
        console.log(e);
      }
    } else {
      this.handleUpdateColumn();
    }
  }
  handleUpdateBoard() {
    try {
      this.boardsService.updateBoard(this.board).subscribe((response) => {
        this.board.title = response.title;
        this.board.description = response.description;
      });
      this.setHideModalBoard();
    } catch (e) {
      console.log(e);
    }
  }
  handleOpenColumnModal(column: any) {
    this.isEditing = true;
    this.collumnModel.id = column.id;
    this.collumnModel.title = column.title;
    this.collumnModel.board.id = this.board.id;
    this.setShowModalCol();
  }
  handleUpdateColumn() {
    this.isEditing = false;
    try {
      this.collumnsService.updateCollumn(this.collumnModel).subscribe((response) => {
        this.collumnModel.title = response.title;
      });
      this.getBoardInfo(this.board_id);
      this.setHideModalCol();
    } catch (e) {
      console.log(e);
    }
  }
  cleanModelCol() {
    this.collumnModel.id = 0;
    this.collumnModel.title = "";
    this.collumnModel.board.id = 0;
  }
}
