package org.music.events.repositories;

import org.music.events.models.TelevisionWallBracket;
import org.music.events.models.TelevisionWallBracketKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelevisionWallBracketRepository extends JpaRepository<TelevisionWallBracket, TelevisionWallBracketKey> {
    List<TelevisionWallBracket> findAllByTelevisionId(Long televisionId);
    List<TelevisionWallBracket> findAllByWallBracketId(Long wallBracketId);
}
