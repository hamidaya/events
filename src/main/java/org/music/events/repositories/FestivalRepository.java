package org.music.events.repositories;

import org.music.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FestivalRepository extends JpaRepository<Event, Long> {
    List<Event>findAllEventsByEventNameEqualsIgnoreCase(String eventName);

}



