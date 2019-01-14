package com.example.studentproject.management;


import com.example.studentproject.db.DBConnect;
import com.example.studentproject.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public String StudentUpdate(@RequestBody StudentDto studentDto) throws Exception{
        DBConnect db = new DBConnect();
        Connection conn = db.connect();
        Boolean checkked;
        String send="Update Fail";

        StudentDto student1;
        student1 = new StudentDto(studentDto);
        checkked = studentRepository.updateStudent(conn,student1);

        if (checkked){
            send = "Update Complete!!";
        }

        conn.close();
        return send;
    }


    @DeleteMapping("/delete")
    public String StudentDelete(@RequestBody StudentDto studentDto) throws Exception{
        DBConnect dbConnect = new DBConnect();
        Connection connection = dbConnect.connect();
        Boolean checkked;
        String send="Delete Fail";

        StudentDto studentl = new StudentDto(studentDto);
        checkked = studentRepository.updateStudent(connection,studentl);

        if (checkked){
            send = "Delete Complete!!";
        }
        connection.close();

        return send;
    }

}
