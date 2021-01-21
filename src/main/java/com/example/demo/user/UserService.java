package com.example.demo.user;

import com.example.demo.match.Match;
import com.example.demo.match.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MatchRepository matchRepository;

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



    public HttpStatus addMatchFromUser(Long Userid, Long Userid2) {
        Optional<User> OptionalUser =  userRepository.findById(Userid);
        Optional<User> OptionalUser2 =  userRepository.findById(Userid2);

        if (!Stream.of(OptionalUser, OptionalUser2).allMatch(Optional::isPresent)

        )
            return HttpStatus.NOT_FOUND;

        User user = OptionalUser.get();
        User user2 = OptionalUser2.get();
        Match match = new Match();
        match.setUser1(user);
        match.setUser2(user2);
        matchRepository.save(match);
        System.out.print(match.id);
        List<Match> x = user.getMatches();
        x.add(match);
        List<Match> z = user2.getMatches();
        z.add(match);
        user.setMatches(x);
        user2.setMatches(z);
        userRepository.saveAll(Arrays.asList(user, user2));

        return HttpStatus.OK;

        //repository.getMatch()
    }






}
