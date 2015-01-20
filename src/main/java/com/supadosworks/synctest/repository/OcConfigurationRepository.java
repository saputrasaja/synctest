package com.supadosworks.synctest.repository;

import com.supadosworks.synctest.domain.OcConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the OcConfiguration entity.
 */
public interface OcConfigurationRepository extends JpaRepository<OcConfiguration,Long>{

}
