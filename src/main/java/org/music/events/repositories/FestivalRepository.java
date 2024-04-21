package org.music.events.repositories;

import org.music.events.models.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FestivalRepository extends JpaRepository<Festival, Long> {
    List<Festival>findAllEventsByEventNameEqualsIgnoreCase(String eventName);

}

