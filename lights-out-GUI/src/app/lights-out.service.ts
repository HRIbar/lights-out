import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LightsOutService {
  private gridSize: number = 5;
  private grid: boolean[][] = [];

  constructor() {
    this.resetGrid();
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
