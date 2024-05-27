package org.music.events.repositories;

import org.music.events.models.Photo;
import org.music.events.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProfileRepository extends JpaRepository<Profile, Long> {


}

