package com.steveb.curso.springboot.jpa.springboot_jpa_relationships.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.steveb.curso.springboot.jpa.springboot_jpa_relationships.entities.Student;

public interface StudentRepository extends CrudRepository<Student,Long> {

     @Query("select s from Student s left join fetch s.courses  where s.id = ?1")
    Optional<Student> findOne(Long id);
}
