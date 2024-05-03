package org.music.events.controllers;
import org.music.events.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
@RestController
@RequestMapping(value = "profiles")
public class ProfileController {
    private final UserService userService;
    public ProfileController(UserService userService) {
        this.userService = userService;
    }
    @PutMapping(value = "/{username}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> updateProfilePhoto(@PathVariable String username, @RequestParam("profilePhoto") MultipartFile profilePhoto) throws IOException {
        userService.updateProfilePhoto(username, profilePhoto);
        return ResponseEntity.ok().build();
    }
}