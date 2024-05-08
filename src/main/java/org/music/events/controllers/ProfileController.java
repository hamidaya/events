package org.music.events.controllers;
import org.music.events.repositories.ProfileRepository;
import org.music.events.services.UserService;
import org.springframework.http.ResponseEntity;
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

    public ProfileController(UserService userService, ProfileRepository profileRepository) {
        this.userService = userService;
        this.profileRepository = profileRepository;
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

    @GetMapping("/{username}")
    public ResponseEntity<Void[]> getUserPhoto(@PathVariable("username") String username) {

        userService.getUserPhoto(username);

        MediaType mediaType;

        try {
            mediaType = MediaType.parseMediaType(photo.getContentType());
        } catch (InvalidMediaTypeException ignore) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }
}

