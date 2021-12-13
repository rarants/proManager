import { Injectable } from "@angular/core";
import { Board } from "../interfaces/board";
import { HttpClient } from "@angular/common/http";
import { first, Observable, tap } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class BoardsService {
  constructor(private http: HttpClient) {}

  // OK
  public getBoardList(): Observable<any> {
    return this.http.get<Board[]>("http://localhost:8080/board").pipe(first());
  }

  // OK
  public getBoard(board_id: Number): Observable<any> {
    return this.http
      .get<Board>(`http://localhost:8080/board/${board_id}`)
      .pipe(first());
  }

  // OK
  public postBoard(board: Board): Observable<any> {
    return this.http
    .post<Board>(`http://localhost:8080/board`, board)
    .pipe(first());
  }

  // OK 
  public updateBoard(board: Board): Observable<any> {
    return this.http
      .put<Board>(`http://localhost:8080/board/${board.id}`, board)
      .pipe(first());
  }

/*   public deleteBoard(board_id: Number): Observable<any> {
    return this.http
      .delete<Board>(`http://localhost:8080/board/${board_id}`)
      .pipe(first());
  } */
}
