package com.example.demo.hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity<String> hi() {
        return new ResponseEntity<String>("FF", HttpStatus.OK);
    }

    @RequestMapping(value = "/testdb", method = RequestMethod.GET)
    public ResponseEntity<String> testdb() {
        return new ResponseEntity<String>("FF", HttpStatus.OK);
    }
}
