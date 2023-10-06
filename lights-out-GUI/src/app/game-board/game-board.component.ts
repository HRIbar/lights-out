// src/app/game-board/game-board.component.ts
import {Component, OnInit} from '@angular/core';
import {LightsOutService} from '../lights-out.service';

@Component({
  selector: 'app-game-board',
  templateUrl: './game-board.component.html',
  styleUrls: ['./game-board.component.css']
})
export class GameBoardComponent implements OnInit {

  solutionMatrix: boolean[][] = [];

  constructor(public lightsOutService: LightsOutService) {
  }

  onCellClick(row: number, col: number): void {
    this.lightsOutService.toggleCell(row, col);
    if (this.solutionMatrix[row] && this.solutionMatrix[row][col]) {
      // If the clicked cell is purple, reset it
      this.solutionMatrix[row][col] = false;
    }
  }

  onGetProblemClick() {
    this.lightsOutService.fetchProblemData();
  }

  ngOnInit(): void {
  }

  getSolution() {
    const problemId = this.lightsOutService.getProblemId();
    this.lightsOutService.fetchSolutionData(problemId)
      .subscribe(response => {
        console.log('Solution Response: ', response.solutionMatrix);
        // @ts-ignore
        console.log(JSON.parse(response.solutionMatrix))
        // @ts-ignore
        const parsedMatrix: number[][] = JSON.parse(response.solutionMatrix);
        this.solutionMatrix = parsedMatrix.map(row => row.map(value => Boolean(value)));
      });
  }
  resetSolutionMatrix(): void {
    this.solutionMatrix = Array.from({ length: this.lightsOutService.getGrid().length }, () =>
      Array.from({ length: this.lightsOutService.getGrid()[0].length }, () => false)
    );
  }

}

