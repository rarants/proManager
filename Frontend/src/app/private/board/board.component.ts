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
import { ToastrService } from "ngx-toastr";
import { Card } from "../interfaces/card";
import { CardsService } from "../services/cards.service";

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
    cards: []
  };

  cardModel: Card = {
    id: 0,
    title: "",
    description: "",
    start_date: null,
    end_date: null,
    collumn: {
      id: 0
    }
  }

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
    private cardService: CardsService,
    private router: Router,
    private toastr: ToastrService
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
  handleOpenColumnModal(column: any) {
    this.isEditing = true;
    this.collumnModel.id = column.id;
    this.collumnModel.title = column.title;
    this.collumnModel.board.id = this.board.id;
    this.setShowModalCol();
  }
  handleOpenCardModal(collumn: Collumn) {
    this.cardModel.collumn.id = collumn;
    this.setShowModalCard();
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
    if (!this.isEditing) {
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
        this.toastr.success("Coluna adicionada com sucesso");
        this.setHideModalCol();
      } catch (e) {
        this.toastr.error(
          "Ocorreu um erro ao cadastrar a coluna. Verifique as informações e tente novamente"
        );
      }
    } else {
      this.handleUpdateCollumn();
    }
  }
  handleSubmitNewCard() {
    try {
      console.log(this.cardModel)
      this.cardService.postCard(this.cardModel).subscribe(
        (response) => {
          /* this.board.collumns.map((e: { id: any; cards: any[]; }) => {
            if (e.id === this.cardModel.collumn.id) {
              e.cards.push(response);
            }
          }); */
          //this.board.collumns.push(response);
          console.log(this.board);
        },
        (error) => {
          this.error = error;
        }
      );
      this.toastr.success("Cartão adicionado com sucesso");
      // this.setHideModalCol();
    } catch (e) {
      this.toastr.error(
        "Ocorreu um erro ao cadastrar o cartão. Verifique as informações e tente novamente"
      );
    }
  }
  handleUpdateBoard() {
    try {
      this.boardsService.updateBoard(this.board).subscribe((response) => {
        this.board.title = response.title;
        this.board.description = response.description;
      });
      this.toastr.success("Quadro atualizado com sucesso");
      this.setHideModalBoard();
    } catch (e) {
      this.toastr.error(
        "Ocorreu um erro ao salvar as informações. Verifique as informações e tente novamente"
      );
    }
  }
  handleUpdateCollumn() {
    this.isEditing = false;
    try {
      this.collumnsService
        .updateCollumn(this.collumnModel)
        .subscribe((response) => {
          this.collumnModel.title = response.title;
        });
      this.toastr.success("Alterações salvas com sucesso");
      this.getBoardInfo(this.board_id);
      this.setHideModalCol();
    } catch (e) {
      this.toastr.error(
        "Ocorreu um erro ao atualizar a coluna. Verifique as informações e tente novamente"
      );
    }
  }

  cleanModelCol() {
    this.collumnModel.id = 0;
    this.collumnModel.title = "";
    this.collumnModel.board.id = 0;
  }
}
