import {Component} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'lights-out-GUI';

  constructor(private router: Router) {
  }

  onProblemSelected(problemId: string) {
    console.log('Problem Clicked:', problemId);
    this.router.navigate(['/game-board', {problemId: problemId}]);
  }
}
