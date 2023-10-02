package com.example.lightsout.web;

import com.example.lightsout.common.GameProblem;
import com.example.lightsout.database.entity.Problem;
import com.example.lightsout.database.service.ProblemService;
import org.json.JSONArray;
import org.json.JSONObject;
import com.example.lightsout.service.LightsOutSolverService;
import com.example.lightsout.service.ArrayConversionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
public class SolutionsAPIController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private ArrayConversionService arrayConversionService;

    @Autowired
    private LightsOutSolverService lightsOutSolverService;

    @GetMapping(value = "/lightsout/solutions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSolutions() throws Exception {

        String jsonResponse = new JSONObject("{\"message\" : \"This is Lights out solutions controller"
                + " and return all optimal solutions!\"}").toString();

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/lightsout/solutions/problem/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSolutionById(@NotBlank @PathVariable String id) throws Exception {
        Optional<Problem> optionalProblem = problemService.getProblemById(id);

        if (optionalProblem.isPresent()) {
            Problem problem = optionalProblem.get();
            int[][] problemArray = arrayConversionService.convertStringToArray(problem.getMatrix());
            int[][] solutionArray = lightsOutSolverService.solve(problemArray);
            if (solutionArray.length == 0) {
                String jsonResponse = new JSONObject("{\"message\" : " +
                        "\"There is no solution for problem with problem id: " + id + "\"}").toString();
                return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
            }
            String jsonResponse = new JSONObject("{\"message\" : \"The solution for problem id: " + id +
                    " is " + arrayConversionService.convertArrayToString(solutionArray) + "\"}").toString();
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

        } else {
            String jsonResponse = new JSONObject("{\"message\" : \"Problem with problem id: " + id +
                    " was not found therefore there is no solution." + "\"}").toString();
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }
    }

}
