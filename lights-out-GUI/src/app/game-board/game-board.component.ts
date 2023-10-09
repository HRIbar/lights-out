import {Component, OnInit} from '@angular/core';
import {LightsOutService} from '../lights-out.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-game-board',
  templateUrl: './game-board.component.html',
  styleUrls: ['./game-board.component.css']
})
export class GameBoardComponent implements OnInit {

  solutionMatrix: boolean[][] = [];
  message: string = '';

  constructor(
    public lightsOutService: LightsOutService,
    private router: Router,
    private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      console.log('Route params:', params);
      const problemId = params['problemId'];
      if (problemId) {
        this.lightsOutService.setProblemId(problemId);
        this.lightsOutService.fetchProblemDetails(problemId);
      }
    });
  }


  onCellClick(row: number, col: number): void {
    this.lightsOutService.toggleCell(row, col);
    if (this.solutionMatrix[row] && this.solutionMatrix[row][col]) {
      // If the clicked cell is purple, reset it
      this.solutionMatrix[row][col] = false;
    }
  }

  onGetProblemClick() {
    this.lightsOutService.resetSolutionMatrix();
    this.solutionMatrix = [];
    this.lightsOutService.fetchProblemData();
  }

  ngOnInit(): void {
  }

  getSolution() {
    const problemId = this.lightsOutService.getProblemId();
    this.lightsOutService.fetchProblemDetails(problemId);
    this.lightsOutService.fetchSolutionData(problemId)
      .subscribe(response => {
        console.log('Solution Response: ', response.solutionMatrix);
        // @ts-ignore
        console.log(JSON.parse(response.solutionMatrix))
        // @ts-ignore
        const parsedMatrix: number[][] = JSON.parse(response.solutionMatrix);
        this.solutionMatrix = parsedMatrix.map(row => row.map(value => Boolean(value)));
        this.message = "To solve the puzzle please click on Purple squares from left to right and top to bottom";
      });
  }

  resetSolutionMatrix(): void {
    this.solutionMatrix = Array.from({length: this.lightsOutService.getGrid().length}, () =>
      Array.from({length: this.lightsOutService.getGrid()[0].length}, () => false)
    );
  }

  displayAllProblems() {
    this.router.navigate(['/all-problems']);
  }
}

