package com.supadosworks.synctest.repository;

import com.supadosworks.synctest.domain.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the PhoneBook entity.
 */
public interface PhoneBookRepository extends JpaRepository<PhoneBook,Long>{

}
