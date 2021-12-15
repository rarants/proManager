import { Injectable } from "@angular/core";
import { Card } from "../interfaces/card";
import { HttpClient } from "@angular/common/http";
import { first, Observable, tap } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class CardsService {
  constructor(private http: HttpClient) {}

  public getCardList(card_id: Number): Observable<any> {
    return this.http.get<Card[]>(`http://localhost:8080/collumn/card/${card_id}`).pipe(first());
  }

  public postCard(card: Card): Observable<any> {
    return this.http
    .post<Card>(`http://localhost:8080/card`, card)
    .pipe(first());
  }

  public updateCard(card: Card): Observable<any> {
    return this.http
      .put<Card>(`http://localhost:8080/card/${card.id}`, card)
      .pipe(first());
  }

  public deleteCard(card_id: Number): Observable<any> {
    return this.http
      .delete<Card>(`http://localhost:8080/card/${card_id}`)
      .pipe(first());
  }

  public updateCardOrder(column_id: Number, card_list: Card[]): Observable<any> {
    return this.http
      .put<Card[]>(`http://localhost:8080/card/column/${column_id}/order`, card_list)
      .pipe(first());
  }

}
