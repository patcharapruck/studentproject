package com.example.studentproject.management;

import com.example.studentproject.dto.StudentDto;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("StudentRepository")
public class StudentRepositoryImpl implements StudentRepository {

    @Override
    public boolean insertStudent(Connection conn, StudentDto dto) throws Exception {

        boolean addResult = false;
        StudentDto newStd = dto;

        String generatedColumns[] = { "id" };

        String sqlText = "INSERT INTO student(std_id,std_fname, std_lname, std_major, std_gpa, std_del) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sqlText, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,newStd.getStd_id());
        preparedStatement.setString(2,newStd.getStd_fname());
        preparedStatement.setString(3,newStd.getStd_lname());
        preparedStatement.setString(4,newStd.getStd_major());
        preparedStatement.setFloat(5,newStd.getStd_gpa());
        preparedStatement.setBoolean(6,newStd.getStd_del());


        if (preparedStatement.executeUpdate() == 1){
            addResult = true;
        }

        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                dto.setId(generatedKeys.getLong(1));
                Long id = dto.getId();

                String val = laststdID(conn);
                System.out.println(id);

                String sqlup = "UPDATE student SET std_id = ? WHERE id = ?";
                PreparedStatement p = conn.prepareStatement(sqlup);
                p.setString(1,val);
                p.setLong(2,id);

                if (p.executeUpdate() == 1){
                    addResult = true;
                }
                p.close();
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        preparedStatement.close();
        return addResult;
    }

    private String laststdID(Connection conn) throws Exception{
        Statement statement = conn.createStatement();
        String s = "SELECT std_id FROM student WHERE  std_id IS NOT NULL ORDER BY std_id DESC limit 1";
        ResultSet resultSet = statement.executeQuery(s);
        String students ="";
        while (resultSet.next()){
            students = resultSet.getString("std_id");
        }

        String stdID = students;
        String[] parts = stdID.split("-");
        String part2 = parts[0];
        String part1 = parts[1];

        Long id = Long.parseLong(part1)+1;
        String val = part2+"-";
        String id_str = id.toString();
        int n = id_str.length();
        String num_val = "";

        for(int i=1;i<=6-n;i++){
            num_val = num_val +"0";
        }

        val = val+ num_val +id_str;

        System.out.println(val);

        resultSet.close();
        statement.close();

        return val;
    }


    @Override
    public boolean updateStudent(Connection conn, StudentDto dto) throws Exception {
        boolean addResult = false;
        StudentDto newStd = dto;

        String sqlText = "UPDATE student SET std_fname = ?,std_lname = ?,std_major = ?,std_gpa = ? WHERE std_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sqlText);
        preparedStatement.setString(1,newStd.getStd_fname());
        preparedStatement.setString(2,newStd.getStd_lname());
        preparedStatement.setString(3,newStd.getStd_major());
        preparedStatement.setFloat(4,newStd.getStd_gpa());
        preparedStatement.setString(5,newStd.getStd_id());

        if (preparedStatement.executeUpdate() == 1){
            addResult = true;
        }

        preparedStatement.close();
        return addResult;
    }

    @Override
    public boolean deleteStudent(Connection conn, StudentDto dto) throws Exception {
        boolean addResult = false;
        StudentDto srd = dto;


        String sqlText = "UPDATE student SET std_del = true WHERE std_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sqlText);
        preparedStatement.setString(1,srd.getStd_id());

        if (preparedStatement.executeUpdate() == 1){
            addResult = true;
        }

        preparedStatement.close();
        return addResult;
    }
}
