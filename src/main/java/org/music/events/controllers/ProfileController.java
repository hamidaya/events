package org.music.events.controllers;
import org.music.events.models.Profile;
import org.music.events.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/profiles")
public class ProfileController {
    private final UserService userService;
    public ProfileController(UserService userService) {
        this.userService = userService;
    }
    @PutMapping("/{username}/profilephoto")
    public ResponseEntity<Void> updateProfilePhoto(@PathVariable String username, @RequestBody Byte[] profilePhoto) {
        userService.updateProfile(username, profilePhoto);
        return ResponseEntity.ok().build();
    }
}