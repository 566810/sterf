package com.example.demo.user;

import com.example.demo.user.specifications.UserHasProfileWithFirstNameLike;
import com.example.demo.user.specifications.UserHasProfileWithLastNameLike;
import com.example.demo.user.specifications.UserWithStudentTypeEqualTo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
     private UserService userService;

    @RequestMapping(value = "/users", consumes= {"application/json"}, produces ={"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers(
            @RequestParam(required = false, value = "studentNumber") String studentNumber,
            @RequestParam(value = "studentType", required = false) String studentType,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "firstName", required = false) String firstName) throws Exception {

        if (studentNumber != null)
            return new ResponseEntity<User>(userService.getUserBName(studentNumber), HttpStatus.OK);

        Specification spec = Specification
                .where(new UserWithStudentTypeEqualTo(studentType))
                .and(new UserHasProfileWithLastNameLike(lastName))
                .and(new UserHasProfileWithFirstNameLike(firstName));
        return new ResponseEntity<>(userService.getUsers(spec), HttpStatus.OK) ;

    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> postUser(@RequestBody User student) throws Exception {
        if (userService.addUser(student))  return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.CONFLICT);

    }

}
