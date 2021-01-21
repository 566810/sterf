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

    public void addUser(User user) throws Exception {
        userRepository.save(user);
    }

    public List getUsers(Specification spec) throws Exception{
        return userRepository.findAll(spec);
    }


    public User getUserBName(String username) {
        return userRepository.findStudentByUsername(username);
    }






}
