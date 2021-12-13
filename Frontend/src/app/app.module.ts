import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import { PublicHeaderComponent } from "../components/public-header/public-header.component";
import { PublicFooterComponent } from "../components/public-footer/public-footer.component";
import { DashboardComponent } from "./private/dashboard/dashboard.component";
import { HomeComponent } from "./public/home/home.component";
import { routing } from "./app.routing";
import { SidebarComponent } from "./private/sidebar/sidebar.component";
import { BoardListComponent } from "./private/board-list/board-list.component";
import { BoardComponent } from "./private/board/board.component";
import { ProfileComponent } from "./private/profile/profile.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MatSidenavModule } from "@angular/material/sidenav";
import { DragDropModule } from "@angular/cdk/drag-drop";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    PublicHeaderComponent,
    PublicFooterComponent,
    DashboardComponent,
    HomeComponent,
    SidebarComponent,
    BoardListComponent,
    BoardComponent,
    ProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    routing,
    BrowserAnimationsModule,
    MatSidenavModule,
    DragDropModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
