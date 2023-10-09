package com.example.lightsout.web;

import com.example.lightsout.common.GameProblem;
import com.example.lightsout.database.entity.Problem;
import com.example.lightsout.database.service.ProblemService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Api(value = "Problems API", description = "API for managing game problems")
public class ProblemsAPIController {

    @Autowired
    private ProblemService problemService;
    private Object mediaType;

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get a list of all problems")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved problems",
                    responseHeaders = {
                            @ResponseHeader(name = "example", response = String.class, description = "{\"problems\":[{\"size\":3,\"id\":\"505e440c-d40f-40ee-b9e1-10bed54ce349\",\"matrix\":\"[[0,1,0],[1,0,1],[0,0,1]]\"}," +
                                    "{\"size\":5,\"id\":\"56292df7-4c97-44ec-9d95-e4204cdd17f1\",\"matrix\":\"[[0,0,0,0,0],[0,1,0,0,0],[1,1,0,1,1],[1,1,0,1,0],[1,1,0,1,1]]\"}," +
                                    "{\"size\":8,\"id\":\"18a693fe-a01f-42e6-b49d-4bb597ea179b\",\"matrix\":\"[[1,0,1,0,1,0,0,1],[0,1,1,1,1,1,1,0],[1,1,0,1,0,0,0,0],[1,1,1,0,1,1,1,0],[1,1,0,0,1,1,1,1],[1,1,1,1,1,0,0,1],[1,1,0,0,1,0,0,0],[0,1,1,1,0,1,0,1]]\"}]}")
                    }),
            @ApiResponse(code = 404, message = "Problem not found")
    })
    @GetMapping(value = "/lightsout/problems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getProblems() throws Exception {
        List<Problem> allProblems = problemService.getAllProblems();  // Get all problems from the ProblemService

        JSONArray problemsArray = new JSONArray();
        for (Problem problem : allProblems) {
            JSONObject problemJson = new JSONObject();
            problemJson.put("id", problem.getId());
            problemJson.put("matrix", problem.getMatrix());
            problemJson.put("size", problem.getSize());
            problemsArray.put(problemJson);
        }

        JSONObject responseJson = new JSONObject();
        responseJson.put("problems", problemsArray);

        return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "Get a problem by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved problem",
                    responseHeaders = {
                            @ResponseHeader(name = "example", response = String.class, description = "{\"size\":5," +
                                    "\"id\":\"55888359-e86d-48b4-bcd8-7b1c69194a3d\"," +
                                    "\"matrix\":\"[[1,0,0,1,0],[0,1,0,0,1],[0,0,0,0,0],[1,1,0,0,0],[1,0,0,1,1]]\"}")
                    }),
            @ApiResponse(code = 404, message = "Problem not found")
    })
    @GetMapping(value = "/lightsout/problems/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getProblemById(@NotBlank @PathVariable @Schema(description = "Problem id in UUID format",
            example = "{ \"ff9a1fc3-7493-4531-b428-e94a97586fca\" }") String id
    ) throws Exception {
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
    @ApiOperation(value = "Create a new problem")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created problem",
                    responseHeaders = {
                            @ResponseHeader(name = "example", response = String.class, description = "{\"problem\":" +
                                    "{\"size\":\"4\"," +
                                    "\"problemId\":\"fd0bc0cb-c12e-43f7-8b1a-c03a791fb4fc\"}}")
                    }),

    })
    @PostMapping(value = "/lightsout/problems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createProblem() throws Exception {
        System.out.println("problems POST controller invoked!");
        GameProblem gameProblem = new GameProblem();  
        problemService.createProblem(gameProblem);

        String jsonResponse = new JSONObject(
                "{\"problem\":{\"size\" : \"" + gameProblem.getSize() + "\","
                        + "\"problemId\": " + "\"" + gameProblem.getId() + "\"}}").toString();
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }
}
