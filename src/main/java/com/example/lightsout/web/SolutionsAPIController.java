package com.example.lightsout.web;

import com.example.lightsout.database.entity.Problem;
import com.example.lightsout.database.entity.Solution;
import com.example.lightsout.database.service.ProblemService;
import com.example.lightsout.database.service.SolutionService;
import com.example.lightsout.database.service.SolutionStepService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.json.JSONArray;
import org.json.JSONObject;
import com.example.lightsout.service.LightsOutSolverService;
import com.example.lightsout.service.ArrayConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "SolutionsAPI", description = "Operations pertaining to solutions in Lights Out Game")
public class SolutionsAPIController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private SolutionStepService solutionStepService;

    @Autowired
    private ArrayConversionService arrayConversionService;

    @Autowired
    private LightsOutSolverService lightsOutSolverService;

    private static final Logger LOGGER = Logger.getLogger(SolutionsAPIController.class.getName());

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/lightsout/solutions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list",
                    responseHeaders = {
                            @ResponseHeader(name = "example", response = String.class, description =
                                    "{\"solutions\":" +
                                            "[{\"solutionSteps\":6," +
                                            "\"id\":\"920f30c6-c27a-4385-89a3-76692ad0c3fe\"," +
                                            "\"problemId\":\"ff9a1fc3-7493-4531-b428-e94a97586fca\"," +
                                            "\"solutionMatrix\":\"[[0,0,1,0],[1,1,0,0],[0,1,0,1],[0,1,0,0]]\"}," +
                                            "{\"solutionSteps\":8," +
                                            "\"id\":\"e6e25c6d-12bd-4351-9703-98e8aa39d0c2\"," +
                                            "\"problemId\":\"81a5af75-120e-4fa2-a5b8-cc6125bbda55\"," +
                                            "\"solutionMatrix\":\"[[0,0,1,1],[0,1,0,1],[0,1,1,1],[0,1,0,0]]\"}]}")
                    }),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<String> getSolutions() throws Exception {

        List<Solution> allSolutions = solutionService.getAllSolutions();

        JSONArray solutionsArray = new JSONArray();
        for (Solution solution : allSolutions) {
            JSONObject solutionJson = new JSONObject();
            solutionJson.put("id", solution.getId());
            solutionJson.put("problemId", solution.getProblem().getId());
            solutionJson.put("solutionMatrix", solution.getSolutionMatrix());
            solutionJson.put("solutionSteps", solution.getSolutionSteps());
            solutionsArray.put(solutionJson);
        }

        JSONObject responseJson = new JSONObject();
        responseJson.put("solutions", solutionsArray);

        return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/lightsout/solutions/problem/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a solution by its problem ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved solution",
                    responseHeaders = {
                            @ResponseHeader(name = "example", response = String.class, description =
                                    "{\"problemid\":\"fd0bc0cb-c12e-43f7-8b1a-c03a791fb4fc\"," +
                                            "\"solutionMatrix\":\"[[1,0,0,0],[0,0,0,1],[1,1,0,1],[0,0,1,1]]\"}")
                    }),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<String> getSolutionById(@NotBlank @PathVariable @Schema(description = "Problem id in UUID format",
            example = "ff9a1fc3-7493-4531-b428-e94a97586fca") String id) throws Exception {
        LOGGER.info("Solutions GET controller invoked!");
        Optional<Problem> optionalProblem = problemService.getProblemById(id);
        Optional<Solution> optionalSolution = solutionService.getByProblemId(id);

        if (optionalSolution.isPresent() == false) {
            if (optionalProblem.isPresent()) {
                Problem problem = optionalProblem.get();
                int[][] problemArray = arrayConversionService.convertStringToArray(problem.getMatrix());
                Long currentTime = System.currentTimeMillis();
                int[][] solutionArray = lightsOutSolverService.solve(problemArray);
                Long solutionTime = System.currentTimeMillis() - currentTime;
                String solutionMatrix = arrayConversionService.convertArrayToString(solutionArray);
                solutionService.createSolution(solutionMatrix, problem);
                optionalSolution = solutionService.getByProblemId(id);
                Solution solution = optionalSolution.get();
                solutionStepService.createSolutionSteps(solution);
                solution.setSolutionSteps(solutionStepService.getNumberOfSteps(solution.getId()));
                solutionService.updateSolution(solution.getId(), solution);

                LOGGER.info("Time required to solve the problem with problemid " +
                        id + " was " + solutionTime + ", the problem requires " +
                        solutionStepService.getNumberOfSteps(solution.getId()) + " solution steps to solve it.");

                if (solutionArray.length == 0) {
                    String jsonResponse = new JSONObject("{\"problemid\":" + id + "," +
                            "\"solutionMatrix\":\"" + solutionMatrix + "\"}").toString();
                    return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
                }
                String jsonResponse = new JSONObject("{\"problemid\":" + id + "," +
                        "\"solutionMatrix\":\"" + solutionMatrix + "\"}").toString();
                return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

            } else {
                String jsonResponse = new JSONObject("{\"message\" : \"Problem with problem id: " + id +
                        " was not found therefore there is no solution." + "\"}").toString();
                return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
            }

        } else {
            Solution solution = optionalSolution.get();
            String jsonResponse = new JSONObject("{\"problemid\":" + id + "," +
                    "\"solutionMatrix\":\"" + solution.getSolutionMatrix() + "\"}").toString();
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }
    }

}