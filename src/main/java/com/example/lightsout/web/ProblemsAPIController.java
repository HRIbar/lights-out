package com.example.lightsout.web;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
public class ProblemsAPIController {

    @GetMapping(value = "/lightsout/problems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getProblems() throws Exception {
        String jsonResponse = new JSONObject("{\"message\" : \"This is Lights out problems controller"
                + " and return all problems!\"}").toString();

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

    }

    @GetMapping(value = "/lightsout/problems/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getProblemId(@NotBlank @PathVariable String id) throws Exception {
        String jsonResponse = new JSONObject("{\"message\" : \"This is Lights out problems"
                + "by Id controller and returns problems with id: " + id + "\"}").toString();

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

    }

    @PostMapping(value = "/lightsout/problems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createProblem() throws Exception {
        String jsonResponse = new JSONObject("{\"message\" : \"This is Lights out POST problem controller"
                + " and creates a new problem!\"}").toString();

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

    }
}
