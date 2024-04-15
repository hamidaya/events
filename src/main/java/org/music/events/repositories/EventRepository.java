package org.music.events.repositories;

import org.music.events.models.Event;
import org.music.events.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event>findAllEventsByEventNameEqualsIgnoreCase(String eventName);

}



