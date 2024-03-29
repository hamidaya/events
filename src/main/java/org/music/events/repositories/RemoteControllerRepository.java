package org.music.events.repositories;

import org.music.events.models.RemoteController;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoteControllerRepository extends JpaRepository<RemoteController, Long> {
}
