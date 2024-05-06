package org.music.events.repositories;

import org.music.events.models.Profile;
import org.music.events.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


    public interface ProfileRepository extends JpaRepository<Profile, String> {

    }
