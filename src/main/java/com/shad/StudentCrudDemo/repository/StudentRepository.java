package com.shad.StudentCrudDemo.repository;

import com.shad.StudentCrudDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
//public class StudentRepository  {
    public interface StudentRepository extends JpaRepository<Student,Long> {

    Optional<Student> findByIdAndIsDeletedFalse(Long id);

    List<Student> findByIsDeletedFalse();
}
