package com.example.lightsout.web;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
public class SolutionsAPIController {

    @GetMapping(value = "/lightsout/solutions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSolutions() throws Exception {

        String jsonResponse = new JSONObject("{\"message\" : \"This is Lights out solutions controller"
                + " and return all optimal solutions!\"}").toString();

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/lightsout/solutions/problem/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSolutionById(@NotBlank @PathVariable String id) throws Exception {

        String jsonResponse = new JSONObject("{\"message\" : \"This is Lights out solutions"
                + "by Id controller and return optimal solutions for solution id: " + id + "\"}").toString();

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

}
