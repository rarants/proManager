import { Injectable } from "@angular/core";
import { Collumn } from "../interfaces/collumn";
import { HttpClient } from "@angular/common/http";
import { first, Observable, tap } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class CollumnsService {
  constructor(private http: HttpClient) {}

  public getCollumnList(board_id: Number): Observable<any> {
    return this.http.get<Collumn[]>(`http://localhost:8080/collumn/board/${board_id}`).pipe(first());
  }

  public postCollumn(collumn: Collumn): Observable<any> {
    return this.http
    .post<Collumn>(`http://localhost:8080/collumn`, collumn)
    .pipe(first());
  }

  public updateCollumn(collumn: Collumn): Observable<any> {
    return this.http
      .put<Collumn>(`http://localhost:8080/collumn/${collumn.id}`, collumn)
      .pipe(first());
  }
}
