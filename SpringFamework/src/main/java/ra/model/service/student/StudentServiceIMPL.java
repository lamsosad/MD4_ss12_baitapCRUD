package ra.model.service.student;

import ra.model.entity.Student;
import ra.model.util.ConectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceIMPL implements IStudentService{
    @Override
    public List<Student> findAll() {
        List<Student> list =new ArrayList<>();
        Connection conn = null;

        try {
            conn= ConectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_SHOWALL()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setSex(rs.getBoolean("sex"));
                s.setAddress(rs.getString("address"));
                list.add(s);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ConectionToDB.close(conn);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public boolean save(Student student) {
        Connection conn = null;
        try {
            conn= ConectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_CREATESTUDENT(?,?,?,?)}");
            callSt.setString(1,student.getName());
            callSt.setInt(2, student.getAge());
            callSt.setBoolean(3,student.isSex());
            callSt.setString(4, student.getAddress());
            callSt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                ConectionToDB.close(conn);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean update(Student student) {
        Connection conn = null;
        try {
            conn= ConectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_UPDATESTUDENT(?,?,?,?,?)}");
            callSt.setInt(1,student.getId());
            callSt.setString(2,student.getName());
            callSt.setInt(3, student.getAge());
            callSt.setBoolean(4,student.isSex());
            callSt.setString(5, student.getAddress());
            callSt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                ConectionToDB.close(conn);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public Student findById(Integer integer) {
        Connection conn = null;
        Student student = null;
        try {
            conn= ConectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FINDBYID(?)}");
            callSt.setInt(1,integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                student= new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setAge(rs.getInt(3));
                student.setSex(rs.getBoolean(4));
                student.setAddress(rs.getString(5));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ConectionToDB.close(conn);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return student;
    }

    @Override
    public boolean delete(Integer integer) {
        Connection conn = null;

        try {
            conn= ConectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_DELETESTUDENT(?)}");
            callSt.setInt(1,integer);
            callSt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                ConectionToDB.close(conn);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
