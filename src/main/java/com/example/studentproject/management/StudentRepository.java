package com.example.studentproject.management;

import com.example.studentproject.dto.StudentDto;

import java.sql.Connection;
import java.util.ArrayList;

public interface StudentRepository {


    // Select ,Insert , Update , Delete
    public ArrayList<StudentDto> SelectAll(Connection conn,StudentDto dto) throws Exception;;
    public boolean insertStudent(Connection conn, StudentDto dto) throws Exception;
    public boolean updateStudent(Connection conn, StudentDto dto) throws Exception;
    public boolean deleteStudent(Connection conn, StudentDto dto) throws Exception;
}
