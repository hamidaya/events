package org.music.events.repositories;

import org.music.events.models.Profile;
import org.music.events.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByProfile(Profile profile);

    Optional<Object> findByUsername(String username);
}
