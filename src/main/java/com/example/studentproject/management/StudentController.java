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


    @GetMapping("/select")
    public ArrayList<String> StudentSelect() throws Exception {
        DBConnect dbConnect = new DBConnect();
        Connection connection = dbConnect.connect();
        StudentDto std = new StudentDto();

        ArrayList<StudentDto> students = studentRepository.SelectAll(connection,std);
        ArrayList<String> strings = new ArrayList<>();

        String row;
        for(StudentDto student:students){
            row = student.getId()+"|"+student.getStd_id()+"|"
                    +student.getStd_fname()+"|"+student.getStd_lname()+"|"
                    +student.getStd_major()+"|"+student.getStd_gpa();
            strings.add(row);
        }

        connection.close();

        return strings;

    }

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

        StudentDto studentl = new StudentDto();
        studentl.setStd_id(studentDto.getStd_id());
        checkked = studentRepository.deleteStudent(connection,studentl);

        if (checkked){
            send = "Delete Complete!!";
        }
        connection.close();

        return send;
    }

}
