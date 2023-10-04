import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LightsOutService {
  private gridSize: number = 5;
  private grid: boolean[][] = [];
  private apiUrl: string = 'http://localhost:8080/lightsout/problems';

  constructor(private http: HttpClient) {
    this.fetchProblemData();
  }

  public fetchProblemData() {
    this.http.post<{ problem: { size: string, problemId: string } }>(this.apiUrl, {})
      .pipe(
        catchError(error => {
          console.error('Error fetching problem data:', error);
          return throwError(error);
        })
      )
      .subscribe(response => {
        const problemId = response.problem.problemId;
        this.fetchProblemDetails(problemId);
      });
  }

  private fetchProblemDetails(problemId: string) {
    const url = `${this.apiUrl}/${problemId}`;
    this.http.get<{ size: number, id: string, matrix: string }>(url)
      .pipe(
        catchError(error => {
          console.error('Error fetching problem details:', error);
          return throwError(error);
        })
      )
      .subscribe(response => {
        this.gridSize = response.size;
        this.grid = JSON.parse(response.matrix).map((row: any[]) => row.map(value => Boolean(value)));
      });
  }

  resetGrid() {
    this.grid = Array.from({ length: this.gridSize }, () =>
      Array.from({ length: this.gridSize }, () => Math.random() < 0.5)
    );
  }

  toggleCell(row: number, col: number) {
    this.grid[row][col] = !this.grid[row][col];
    if (row > 0) this.grid[row - 1][col] = !this.grid[row - 1][col];
    if (row < this.gridSize - 1) this.grid[row + 1][col] = !this.grid[row + 1][col];
    if (col > 0) this.grid[row][col - 1] = !this.grid[row][col - 1];
    if (col < this.gridSize - 1) this.grid[row][col + 1] = !this.grid[row][col + 1];
  }

  getGrid(): boolean[][] {
    return this.grid;
  }
}
