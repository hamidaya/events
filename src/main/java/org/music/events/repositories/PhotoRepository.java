package org.music.events.repositories;

import org.music.events.models.Photo;
import org.music.events.models.Profile;
import org.music.events.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

}
