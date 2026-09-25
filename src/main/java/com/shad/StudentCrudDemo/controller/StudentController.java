package com.shad.StudentCrudDemo.controller;

import com.shad.StudentCrudDemo.entity.Student;
import com.shad.StudentCrudDemo.service.StudentService;
import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    @PostMapping("/createStudent")
    public ResponseEntity<Student> create(@RequestBody Student student){
        student.setDeleted(false);
        Student createStudent=studentService.create(student);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createStudent);
    }

//    @GetMapping("/getStudent/{id}")
    @GetMapping("/getStudent")
//    public ResponseEntity<Student> getStudent(@PathVariable Long id){
    public ResponseEntity<Student> getStudent(@RequestParam Long id){
       Student studentResp= studentService.getStudent(id);
       if(studentResp==null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
       return ResponseEntity.ok(studentResp);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> studentResp= studentService.getAllStudent();
        if(studentResp==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(studentResp);
    }

//    @PutMapping("/updateStudent/{id}")
//    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentReq){
@PutMapping("/updateStudent")
public ResponseEntity<Student> updateStudent(@RequestParam Long id, @RequestBody Student studentReq){
        Student studentResp = studentService.updateStudent(id,studentReq);

        if (studentResp==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }

//    @DeleteMapping("/deleteStudent/{id}")
//    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
@DeleteMapping("/deleteStudent")
public ResponseEntity<String> deleteStudent(@RequestParam Long id){
        boolean isDeleted=studentService.deleteStudent(id);
        if (!isDeleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok("Record Deleted.");
    }
//    @PatchMapping("/soft-delete/{id}")
//    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id){
@PatchMapping("/soft-delete")
public ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id){
        Boolean isDeleted=studentService.deleteStudentSoftly(id);
        if (!isDeleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok("Record Deleted.");
    }
}
