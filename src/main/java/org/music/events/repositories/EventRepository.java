package org.music.events.repositories;

import org.music.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event>findAllEventsByEventNameEqualsIgnoreCase(String eventName);

     Optional<Event> findEventByEventName(String eventName);}



