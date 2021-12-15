import { Injectable } from "@angular/core";
import { Column } from "../interfaces/column";
import { HttpClient } from "@angular/common/http";
import { first, Observable, tap } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class ColumnsService {
  constructor(private http: HttpClient) {}

  public getColumnList(board_id: Number): Observable<any> {
    return this.http.get<Column[]>(`http://localhost:8080/column/board/${board_id}`).pipe(first());
  }

  public postColumn(column: Column): Observable<any> {
    return this.http
    .post<Column>(`http://localhost:8080/column`, column)
    .pipe(first());
  }

  public updateColumn(column: Column): Observable<any> {
    return this.http
      .put<Column>(`http://localhost:8080/column/${column.id}`, column)
      .pipe(first());
  }

  public deleteColumn(column_id: Number): Observable<any> {
    return this.http
      .delete<Column>(`http://localhost:8080/column/${column_id}`)
      .pipe(first());
  }
}
