package com.example.lightsout.web;

import com.example.lightsout.common.GameProblem;
import com.example.lightsout.database.entity.Problem;
import com.example.lightsout.database.service.ProblemService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProblemsAPIController {

    @Autowired
    private ProblemService problemService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/lightsout/problems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getProblems() throws Exception {
        List<Problem> allProblems = problemService.getAllProblems();  // Get all problems from the ProblemService

        JSONArray problemsArray = new JSONArray();
        for (Problem problem : allProblems) {
            JSONObject problemJson = new JSONObject();
            problemJson.put("id", problem.getId());
            problemJson.put("matrix", problem.getMatrix());
            problemJson.put("size", problem.getSize());
            // ... include other fields if necessary ...
            problemsArray.put(problemJson);
        }

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", "Retrieved all problems successfully!");
        responseJson.put("problems", problemsArray);

        return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/lightsout/problems/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getProblemById(@NotBlank @PathVariable String id) throws Exception {
        Optional<Problem> optionalProblem = problemService.getProblemById(id);
        if (optionalProblem.isPresent()) {
            Problem problem = optionalProblem.get();
            JSONObject problemJson = new JSONObject();
            problemJson.put("id", problem.getId());
            problemJson.put("matrix", problem.getMatrix());
            problemJson.put("size", problem.getSize());

            return new ResponseEntity<>(problemJson.toString(), HttpStatus.OK);
        } else {
            String jsonResponse = new JSONObject(
                    "{\"error\" : \"Problem not found with id: " + id + "\"}").toString();
            return new ResponseEntity<>(jsonResponse, HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/lightsout/problems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createProblem() throws Exception {
        System.out.println("problems POST controller invoked!");
        GameProblem gameProblem = new GameProblem();  // Create a new GameProblem object
        problemService.createProblem(gameProblem);    // Invoke the createProblem method of ProblemService

        String jsonResponse = new JSONObject(
                "{\"problem\":{\"size\" : \"" + gameProblem.getSize() + "\","
                        + "\"problemId\": " + "\""+ gameProblem.getId() +  "\"}}").toString();
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }
}
