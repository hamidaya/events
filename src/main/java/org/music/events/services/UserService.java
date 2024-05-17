package org.music.events.services;

import jakarta.transaction.Transactional;
import org.music.events.dtos.UserDto;
import org.music.events.exceptions.RecordNotFoundException;
import org.music.events.models.Authority;
import org.music.events.models.Photo;
import org.music.events.models.Profile;
import org.music.events.models.User;
import org.music.events.repositories.PhotoRepository;
import org.music.events.repositories.ProfileRepository;
import org.music.events.repositories.UserRepository;
import org.music.events.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PhotoRepository photoRepository;

    @Autowired
    public UserService(UserRepository userRepository, ProfileRepository profileRepository, PhotoRepository photoRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.photoRepository = photoRepository;
    }

    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }

    public UserDto getUser(String username) {
        UserDto dto = new UserDto();
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            dto = fromUser(user.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public boolean profileExists(String username, Long profileId) {
        if (userRepository.existsById(username)) {
            throw new RuntimeException("User already exists with username: " + username);
        }
        if (profileRepository.existsById(profileId)) {
            throw new RuntimeException("Profile already exists with username: " + username);
        }
        return false;
    }

    public String createUser(UserDto userDto) {
        String username = userDto.getUsername();
        if (userExists(username)) {
            throw new RuntimeException("User is already exists with username: " + username);
        }

        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        User user = toUser(userDto);
        Profile profile = toProfile(userDto);
        profileRepository.save(profile);
        user.setProfile(profile);
        user = userRepository.save(user);
        return user.getUsername();
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }


    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    public static UserDto fromUser(User user) {

        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApikey();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();
        return dto;
    }

    public static Profile toProfile(UserDto userDto) {
        var profile = new Profile();
        profile.setName(userDto.getName());
        profile.setCity(userDto.getCity());
        profile.setCountry(userDto.getCountry());
        profile.setPhone(userDto.getPhone());


        return profile;
    }


    public User toUser(UserDto userDto) {

        var user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());

        return user;
    }


    public void storeFile(MultipartFile profilePhoto, String username) throws IOException {

        Optional<User> optionalUser = userRepository.findById(username);

        if (optionalUser.isEmpty()) {
            throw new RecordNotFoundException("User not found");
        }
        User user = optionalUser.get();
        Profile profile = user.getProfile();
        String originalFileName = profilePhoto.getOriginalFilename();
        String contentType = profilePhoto.getContentType();
        byte[] bytes = profilePhoto.getBytes();

        Photo photo = new Photo(bytes, contentType, originalFileName);
        photoRepository.save(photo);
        profile.setPhoto(photo);

                 profileRepository.save(profile);

        //eerst foto maken
        //foto opslaan
        //foto koppelen aan profile
        //profile opslaan
    }


    @Transactional
    public Photo getUserPhoto(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        if(optionalUser.isEmpty()){
            throw new RecordNotFoundException("user with usernamme " + username + " not found.");
        }
        return optionalUser.get().getProfile().getPhoto();
    }
}



