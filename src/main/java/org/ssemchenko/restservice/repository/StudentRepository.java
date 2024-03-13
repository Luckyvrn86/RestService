package org.ssemchenko.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssemchenko.restservice.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
