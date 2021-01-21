package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean addUser(User user) throws Exception {
        User user2 = userRepository.findStudentByUsername(user.getStudentNumber());
        if (user2!= null) return false;
        userRepository.save(user);
        return true;
    }

    public List getUsers(Specification spec) throws Exception{
        return userRepository.findAll(spec);
    }


    public User getUserBName(String username) {
        return userRepository.findStudentByUsername(username);
    }






}
