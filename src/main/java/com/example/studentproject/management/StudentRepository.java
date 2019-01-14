package com.example.studentproject.management;

import com.example.studentproject.dto.StudentDto;

import java.sql.Connection;

public interface StudentRepository {


    // Insert , Update , Delete
    public boolean insertStudent(Connection conn, StudentDto dto) throws Exception;
    public boolean updateStudent(Connection conn, StudentDto dto) throws Exception;
    public boolean deleteStudent(Connection conn, StudentDto dto) throws Exception;
}
