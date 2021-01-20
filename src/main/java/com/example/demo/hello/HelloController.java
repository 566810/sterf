package com.example.demo.hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping(value = "/test/ff", method = RequestMethod.GET)
    public ResponseEntity<String> hi() {
        return new ResponseEntity<String>("FF", HttpStatus.OK);

    }
}
