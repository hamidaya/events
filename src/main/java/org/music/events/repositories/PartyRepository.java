package org.music.events.repositories;

import org.music.events.models.Party;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party, Long> {
    List<Party> findAllEventsByEventNameEqualsIgnoreCase(String eventName);

}

