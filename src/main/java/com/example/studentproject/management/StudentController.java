package com.example.studentproject.management;


import com.example.studentproject.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/projectstd")
public class StudentController {

    @Autowired
    StudentService studentService;


//    @GetMapping("/select")
//    public ArrayList<String> StudentSelect() throws SQLException {
//
//
//    }

    @PostMapping("/insert")
    public void StudentInsert(@RequestBody StudentDto studentDto) throws Exception{

    }

    @PutMapping("/update")
    public void StudentUpdate(@RequestBody StudentDto studentDto) throws Exception{


    }

    @DeleteMapping("/delete")
    public void StudentDelete(@RequestBody StudentDto studentDto) throws Exception{

    }
}
