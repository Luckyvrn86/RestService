package org.ssemchenko.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssemchenko.restservice.entity.Faculty;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
}
