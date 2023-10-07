import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllProblemsComponent } from './all-problems/all-problems.component';
import { GameBoardComponent } from './game-board/game-board.component';

const routes: Routes = [
  { path: '', component: GameBoardComponent },  // default route
  { path: 'all-problems', component: AllProblemsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
