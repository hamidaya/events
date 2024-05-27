package org.music.events.controllers;
import org.music.events.exceptions.RecordNotFoundException;
import org.music.events.models.Photo;
import org.music.events.models.Profile;
import org.music.events.models.User;
import org.music.events.repositories.PhotoRepository;
import org.music.events.repositories.ProfileRepository;
import org.music.events.repositories.UserRepository;
import org.music.events.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("profiles")
public class ProfileController {
    private final UserService userService;
    private final ProfileRepository profileRepository;
    private final PhotoRepository photoRepository;

    @Autowired
    public ProfileController(UserService userService, ProfileRepository profileRepository, PhotoRepository photoRepository) {
        this.userService = userService;
        this.profileRepository = profileRepository;
        this.photoRepository = photoRepository;
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

    @GetMapping("/{username}/profilePhoto")
    public ResponseEntity<byte[]> getUserPhoto(@PathVariable("username") String username) {
       Photo photo = userService.getUsergetPhotoById(username);

        MediaType mediaType;
        try {
            mediaType = MediaType.parseMediaType(photo.getContentType());
        } catch (InvalidMediaTypeException ignore) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }
        return ResponseEntity
                .ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + photo.getFilename() + "\"")
                .body(photo.getProfilePhoto());
    }

}
