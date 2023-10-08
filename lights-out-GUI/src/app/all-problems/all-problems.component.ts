import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {LightsOutService} from '../lights-out.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-all-problems',
  templateUrl: './all-problems.component.html',
  styleUrls: ['./all-problems.component.css']
})
export class AllProblemsComponent implements OnInit {
  @Output() problemSelected = new EventEmitter<string>();
  problems: any[] = [];

  constructor(private lightsOutService: LightsOutService, private router: Router) {
  }

  ngOnInit(): void {
    this.lightsOutService.fetchAllProblems()
      .subscribe(response => {
        this.problems = response.problems;
      });
  }

  onProblemClick(problemId: string) {
    this.router.navigate(['/game-board', {problemId: problemId}]);
  }
}
