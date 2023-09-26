package com.example.lightsout.web;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolutionsAPIController {

    @GetMapping(value = "/lightsout/solutions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSolutions() throws Exception {

        String jsonResponse = new JSONObject("{\"message\" : \"This is Lights out solutions controller!\"}").toString();

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }
}
