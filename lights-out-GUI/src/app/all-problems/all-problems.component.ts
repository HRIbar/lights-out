import { Component, OnInit } from '@angular/core';
import { LightsOutService } from '../lights-out.service';

@Component({
  selector: 'app-all-problems',
  templateUrl: './all-problems.component.html',
  styleUrls: ['./all-problems.component.css']
})
export class AllProblemsComponent implements OnInit {
  problems: any[] = [];

  constructor(private lightsOutService: LightsOutService) { }

  ngOnInit(): void {
    this.lightsOutService.fetchAllProblems()
      .subscribe(response => {
        this.problems = response.problems;
      });
  }
}
