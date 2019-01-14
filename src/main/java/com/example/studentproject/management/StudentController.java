package com.example.studentproject.management;


import com.example.studentproject.db.DBConnect;
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

    @Autowired
    StudentRepository studentRepository;


//    @GetMapping("/select")
//    public ArrayList<String> StudentSelect() throws SQLException {
//
//
//    }

    @PostMapping("/insert")
    public String StudentInsert(@RequestBody StudentDto studentDto) throws Exception{
        DBConnect dbConnect = new DBConnect();
        Connection connection = dbConnect.connect();
        Boolean checkked;
        String send="Insert Fail";


        StudentDto student1 = new StudentDto(studentDto);
        checkked = studentRepository.insertStudent(connection,student1);

        if (checkked){
            send = "Inset Complete!!";
        }
        connection.close();

        return send;
    }

    @PutMapping("/update")
    public void StudentUpdate(@RequestBody StudentDto studentDto) throws Exception{


    }

    @DeleteMapping("/delete")
    public void StudentDelete(@RequestBody StudentDto studentDto) throws Exception{

    }


}
