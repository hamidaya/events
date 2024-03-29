package org.music.events.repositories;

import org.music.events.models.CIModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CIModuleRepository extends JpaRepository<CIModule, Long> {
}
