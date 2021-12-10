import { ModuleWithProviders } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { DashboardComponent } from "./private/dashboard/dashboard.component";
import { BoardListComponent } from "./private/board-list/board-list.component";
import { BoardComponent } from "./private/board/board.component";
import { HomeComponent } from "./public/home/home.component";
import { ProfileComponent } from "./private/profile/profile.component";

const APP_ROUTES: Routes = [
  { path: "", redirectTo: "home", pathMatch: "full" },
  { path: "home", component: HomeComponent },
  {
    path: "user",
    component: DashboardComponent,
    children: [
      { path: "boards", component: BoardListComponent },
      { path: "boards/:id/view", component: BoardComponent },
      { path: "profile", component: ProfileComponent },
    ],
  },
];

export const routing: ModuleWithProviders<any> =
  RouterModule.forRoot(APP_ROUTES);
