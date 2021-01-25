package com.example.demo.messages;


import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
public class MessageService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository repository;

    public ResponseEntity createMessage(@NotNull String message, Long from, Long to)
    {

        Optional<User> optUserFrom = userRepository.findById(from);
        Optional<User> optUserTo = userRepository.findById(to);

        if (!(optUserFrom.isPresent() && optUserTo.isPresent())) return new ResponseEntity(HttpStatus.NOT_FOUND);

        User userFrom = optUserFrom.get();
        User userTo = optUserTo.get();

        Message mes = new Message();
        mes.setMessage(message);

        mes.setReceiverId(to);
        mes.setSenderId(from);

        mes.from = userFrom;
        mes.to = userTo;



        LocalDateTime string = LocalDateTime.now();

        mes.setDate(string);



        repository.save(mes);




        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity getMessages(Long from, Long to){
        Set<Message> messages =  repository.findProfilesBy(from, to);


        return new ResponseEntity(messages, HttpStatus.OK);

    }
}
