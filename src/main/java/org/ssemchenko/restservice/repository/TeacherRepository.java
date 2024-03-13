package org.ssemchenko.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssemchenko.restservice.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
