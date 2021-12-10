import { Component, OnInit } from '@angular/core';
import { faAngleRight } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-board-list',
  templateUrl: './board-list.component.html',
  styleUrls: ['./board-list.component.css']
})
export class BoardListComponent {
  faAngleRight = faAngleRight;
  boardList = [
    {
      id: 1,
      title: "Quadro 1",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ac."
    },
    {
      id: 2,
      title: "Quadro 2",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ac."
    },
    {
      id: 3,
      title: "Quadro 3",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ac."
    },
    {
      id: 4,
      title: "Quadro 4",
      description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ac."
    }
  ]

}
