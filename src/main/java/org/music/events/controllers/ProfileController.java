package org.music.events.controllers;
import org.music.events.repositories.ProfileRepository;
import org.music.events.repositories.UserRepository;
import org.music.events.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final UserService userService;

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileController(UserService userService, ProfileRepository profileRepository, UserRepository userRepository) {
        this.userService = userService;
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }


    @PutMapping(value = "/{username}")
    public ResponseEntity<Void> updatePhoto(@PathVariable("username") String username,
                                            @RequestParam("profilePhoto") MultipartFile profilePhoto) throws IOException {
        userService.storeFile(profilePhoto, username);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/profiles/")
                .path(Objects.requireNonNull(username))
                .toUriString();
        return ResponseEntity.created(URI.create(url)).build();

    }
}

