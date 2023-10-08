import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AllProblemsComponent} from './all-problems/all-problems.component';
import {GameBoardComponent} from './game-board/game-board.component';

const routes: Routes = [
  {path: '', redirectTo: '/game-board', pathMatch: 'full'},  // Redirect to `game-board` by default
  {path: 'game-board', component: GameBoardComponent},
  { path: 'game-board/:problemId', component: GameBoardComponent },
  {path: 'all-problems', component: AllProblemsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
