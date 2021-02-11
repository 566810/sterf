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
import org.springframework.web.bind.annotation.*;

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

        if (studentNumber != null){
            User userByName  = userService.getUserBName(studentNumber);
            if (userByName == null) return  new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            else return new ResponseEntity<User>(userByName, HttpStatus.OK);
        }




        Specification spec = Specification
                .where(new UserWithStudentTypeEqualTo(studentType))
                .and(new UserHasProfileWithLastNameLike(lastName))
                .and(new UserHasProfileWithFirstNameLike(firstName));


        return new ResponseEntity<>(userService.getUsers(spec), HttpStatus.OK) ;

    }

    @RequestMapping(value = "/users/{id}/matches/{id2}", consumes= {"application/json"}, produces ={"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<?> matchUser(@PathVariable("id") Long id, @PathVariable("id2") Long id2   ){
        return new ResponseEntity<>(userService.addMatchFromUser(id, id2))  ;

        //return new ResponseEntity("" , HttpStatus.OK);
    }


    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> postUser(@RequestBody User student) throws Exception {
        if (userService.addUser(student))  return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.CONFLICT);

    }

}
