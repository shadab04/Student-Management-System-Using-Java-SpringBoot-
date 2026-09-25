package com.shad.StudentCrudDemo.service;

import com.shad.StudentCrudDemo.entity.Student;
import com.shad.StudentCrudDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(Student studentReq) {
        studentReq.setDeleted(false);
        Student studentResp = studentRepository.save(studentReq);

        return studentResp;
    }

    public Student getStudent(Long id) {
        Optional<Student> studentResp = studentRepository.findByIdAndIsDeletedFalse(id);
        if (studentResp.isPresent()) {
            return studentResp.get();
        } else {
            return null;
        }
    }

    public List<Student> getAllStudent() {
        List<Student> studentList = studentRepository.findByIsDeletedFalse();
        return studentList;
    }

    public Student updateStudent(Long id, Student studentReq){
        Optional<Student> existingStudent = studentRepository.findByIdAndIsDeletedFalse(id);
        if(existingStudent.isEmpty()){
            return null;
        }
        Student studentToSave=existingStudent.get();
        studentToSave.setName(studentReq.getName());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setDeleted(false);
        return studentRepository.save(studentToSave);
    }

    public Boolean deleteStudent(Long id){
        Boolean isStudent=studentRepository.existsById(id);
        if (!isStudent){
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    public Boolean deleteStudentSoftly(Long id){
        Optional<Student> existingStudent =studentRepository.findByIdAndIsDeletedFalse(id);
        if(existingStudent.isEmpty()){
            return false;
        }
        Student studentToSave=existingStudent.get();
        studentToSave.setDeleted(true);
        studentRepository.save(studentToSave);
        return true;

    }
}