package com.shad.StudentCrudDemo;

import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class StudentCrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCrudDemoApplication.class, args);
	}

}
