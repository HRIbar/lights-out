// src/app/game-board/game-board.component.ts
import { Component } from '@angular/core';
import { LightsOutService } from '../lights-out.service';

@Component({
  selector: 'app-game-board',
  templateUrl: './game-board.component.html',
  styleUrls: ['./game-board.component.css']
})
export class GameBoardComponent {

  constructor(public lightsOutService: LightsOutService) { }

  onCellClick(row: number, col: number) {
    this.lightsOutService.toggleCell(row, col);
  }
}

