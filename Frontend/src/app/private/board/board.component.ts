import { Component, OnInit } from "@angular/core";
import { faEdit } from "@fortawesome/free-solid-svg-icons";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { faAngleLeft } from "@fortawesome/free-solid-svg-icons";
import { BoardsService } from "../services/boards.service";

import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import * as bootstrap from "bootstrap";
import { Modal } from "bootstrap";
import { Board } from "../interfaces/board";
import { ActivatedRoute, ParamMap, Router } from "@angular/router";
import { ColumnsService } from "../services/columns.service";
import { Column } from "../interfaces/column";
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
  columns: Column[] = [];

  columnModel: Column = {
    id: 0,
    title: "",
    board: {
      id: 0,
    },
    cards: [],
  };

  cardModel: Card = {
    id: 0,
    title: "",
    description: "",
    start_date: null,
    end_date: null,
    column: {
      id: 0,
    },
    order: 0
  };

/*   tags = [
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
  ]; */

  board!: Board;
  error = "";

  constructor(
    private route: ActivatedRoute,
    private boardsService: BoardsService,
    private columnsService: ColumnsService,
    private cardsService: CardsService,
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

  setHideModal() {
    var modal = document.getElementById("closeColButton");
    modal?.click();
  }
  setHideModalBoard() {
    var modal = document.getElementById("closeBoardButton");
    modal?.click();
  }

  handleOpenColumnModal(column: any) {
    this.isEditing = true;
    this.columnModel.id = column.id;
    this.columnModel.title = column.title;
    this.columnModel.board.id = this.board.id;
    this.setShowModalCol();
  }
  handleOpenCardModal(column: Column, card: Card | null) {
    this.cleanModelCard();
    this.cardModel.column.id = column.id;
    if (card === null) {
      // new
      this.isEditing = false;
    } else if (card !== null) {
      //update
      this.isEditing = true;
      this.cardModel.id = card.id;
      this.cardModel.title = card.title;
      this.cardModel.description = card.description;
      this.cardModel.start_date = card.start_date;
      this.cardModel.end_date = card.end_date;
      this.cardModel.order = card.order;
    }
    this.setShowModalCard();
  }

  getBoardInfo(id: Number) {
    this.boardsService.getBoard(id).subscribe(
      (response) => {
        this.board = response;
        console.log(this.board)
        if (this.board.columns) {
          this.board.columns.forEach((col) => {
            if (col.cards) {
              col.cards.sort((card1: Card, card2: Card) => {
                if (card1.order < card2.order) return -1;
                if (card1.order > card2.order) return 1;
                else return 0;
              })
            }
          })
        }
      },
      (error) => {
        this.error = error;
      }
    );
  }

  handleSubmitCard() {
    if (!this.isEditing) this.handleSubmitNewCard();
    else this.handleUpdateCard();
  }
  handleSubmitColumn() {
    if (!this.isEditing) {
      this.handleSubmitNewColumn();
    } else {
      this.handleUpdateColumn();
    }
  }

  handleSubmitNewColumn() {
    try {
      this.columnModel.board.id = this.board.id;
      this.columnsService.postColumn(this.columnModel).subscribe(
        (response) => {
          this.board.columns.push(response);
        },
        (error) => {
          this.error = error;
        }
      );
      this.toastr.success("Coluna adicionada com sucesso");
      this.setHideModal();
    } catch (e) {
      this.toastr.error(
        "Ocorreu um erro ao cadastrar a coluna. Verifique as informações e tente novamente"
      );
    }
  }
  handleSubmitNewCard() {
    if (this.board) {
      this.orderCards();
      var index = 1;
      const column_id = this.cardModel.column.id;
      const column = this.board.columns.find((col: Column) => {
        return col.id === column_id;
      })
      column?.cards.forEach((card: { order: number; }) => {
        if (card.order > index) index = card.order;
        if (card.order === index) index += 1;
      });
      if (!index) index = 1;
      this.cardModel.order = index;
      console.log(this.cardModel)
    }
    try {
      console.log(this.cardModel)
      this.cardsService.postCard(this.cardModel).subscribe(
        (response) => {
          this.board.columns.map((e: { id: any; cards: any[] }) => {
            if (e.id === this.cardModel.column.id) {
              e.cards.push(response);
            }
          });
        },
        (error) => {
          this.error = error;
        }
      );
      this.toastr.success("Cartão adicionado com sucesso");
      this.setHideModal();
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
  handleUpdateColumn() {
    this.isEditing = false;
    try {
      this.columnsService
        .updateColumn(this.columnModel)
        .subscribe((response) => {
          this.columnModel.title = response.title;
        });
      this.toastr.success("Alterações salvas com sucesso");
      this.getBoardInfo(this.board_id);
      this.setHideModal();
    } catch (e) {
      this.toastr.error(
        "Ocorreu um erro ao atualizar a coluna. Verifique as informações e tente novamente"
      );
    }
  }
  handleUpdateCard() {
    this.isEditing = false;
    try {
      this.cardsService.updateCard(this.cardModel).subscribe((response) => {
        this.cardModel.title = response.title;
      });
      this.toastr.success("Alterações salvas com sucesso");
      this.getBoardInfo(this.board_id);
      this.setHideModal();
    } catch (e) {
      this.toastr.error(
        "Ocorreu um erro ao atualizar o cartão. Verifique as informações e tente novamente"
      );
    }
  }
  handleUpdateCardOrder() {
    this.isEditing = false;
    try {
      this.board.columns.forEach((col) => {
        this.cardsService.updateCardOrder(col.id, col.cards).subscribe();
        this.toastr.success("Alterações salvas com sucesso");
        this.getBoardInfo(this.board_id);
        this.setHideModal();
      })
    } catch (e) {
      this.toastr.error(
        "Ocorreu um erro ao atualizar o cartão. Verifique as informações e tente novamente"
      );
    }
  }

  cleanModelCol() {
    this.columnModel.id = 0;
    this.columnModel.title = "";
    this.columnModel.board.id = 0;
  }
  cleanModelCard() {
    this.cardModel.id = 0;
    this.cardModel.title = "";
    this.cardModel.description = "";
    this.cardModel.start_date = null;
    this.cardModel.end_date = null;
    this.cardModel.column.id = 0;
  }

  handleDeleteCard() {
    this.isEditing = false;
    try {
      this.cardsService.deleteCard(this.cardModel.id).subscribe();
      this.toastr.success("Cartão removido com sucesso");
      this.getBoardInfo(this.board_id);
      this.setHideModal();
    } catch (e) {
      this.toastr.error("Ocorreu um erro ao remover o cartão.");
    }
  }
  handleDeleteColumn() {
    this.isEditing = false;
    try {
      this.columnsService.deleteColumn(this.columnModel.id).subscribe();
      this.toastr.success("Coluna removida com sucesso");
      this.getBoardInfo(this.board_id);
      this.setHideModal();
    } catch (e) {
      this.toastr.error("Ocorreu um erro ao remover a coluna.");
    }
  }
  handleDeleteBoard() {
    this.isEditing = false;
    try {
      this.boardsService.deleteBoard(this.board_id).subscribe();
      this.toastr.success("Quadro removido com sucesso");
      this.setHideModal();
      this.router.navigateByUrl("/user/boards");
    } catch (e) {
      this.toastr.error("Ocorreu um erro ao remover o quadro.");
    }
  }
  formatedDate(stringDate: string) {
    const date = new Date(stringDate);
    const formatedDate = date.toLocaleDateString("pt-BR", { timeZone: "UTC" });
    return formatedDate;
  }
  getDeadlineStatus(stringDate: string) {
    var date = new Date(stringDate);
    const today = new Date();
    date = new Date(date.setDate(date.getDate() + 1));

    if (date > today && new Date(today.setDate(today.getDate() + 3)) >= date)
      return "warning";
    if (date < today) return "danger";
    if (date >= today) return "success";
    return "success";
  }
  drop(event: CdkDragDrop<string[]>){
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
    }
    this.orderCards();
    // this.handleUpdateCardOrder();
  }
  orderCards() {
    this.board.columns.forEach((col) => {
      var order = 1;
      col.cards.forEach((card: Card) => {
        card.order = order;
        order++;
      })
    })
  }
}
