package com.example.demo.profile;


import com.example.demo.profile.Profile;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository repository;
    @Autowired
    private UserRepository userRepository;


    public ResponseEntity setImage(String username, MultipartFile req, String first, String last, String bio) {

        User user = userRepository.findStudentByUsername(username);
        Profile profile = null;

        if (user.getProfile() != null){
            profile = user.getProfile();
        }  else {
            profile = new Profile();
            profile.setUser(user);
        }



        profile.setFirstName(first);
        profile.setLastName(last);
        profile.setBiography(bio);

        if (req!= null){
            try{
                byte[] byteArray = req.getBytes();
                profile.setProfilePicture(byteArray);

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        user.setProfile(profile);
        repository.save(profile);
        userRepository.save(user);

        return new ResponseEntity(profile, HttpStatus.OK );

    }
}
