package com.example.studentproject.management;

import com.example.studentproject.dto.StudentDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository("StudentRepository")
public class StudentRepositoryImpl implements StudentRepository {

    @Override
    public Long insertStudent(Connection conn, StudentDto dto) throws Exception {
        return null;
    }

    @Override
    public boolean updateStudent(Connection conn, StudentDto dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteStudent(Connection conn, StudentDto dto) throws Exception {
        return false;
    }
}
