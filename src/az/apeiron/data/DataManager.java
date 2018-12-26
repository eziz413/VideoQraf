package az.apeiron.data;

import az.apeiron.config.DBHelper;
import az.apeiron.model.Sector;
import az.apeiron.model.Student;
import az.apeiron.model.User;
import sun.security.pkcs11.Secmod;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    Connection c;
    PreparedStatement ps;
    ResultSet rs;

    public List<Student> getStudentList() throws SQLException {
        String query = "select s.*,SS.SECTOR_NAME from student s inner join sector ss on S.SECTOR_ID=SS.ID where S.ACTIVE=1";
        List<Student> students = new ArrayList<>();

        try {
            c = DBHelper.connect();
            if (c != null) {
                ps = c.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setSurname(rs.getString("surname"));
                    student.setDob(rs.getDate("dob"));
                    student.setSectorId(rs.getInt("sector_id"));
                    student.setSectorName(rs.getString("sector_name"));
                    student.setActive(rs.getInt("active"));
                    students.add(student);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.disconnect(c, ps, rs);
        }
        return students;
    }

    public List<Sector> getSectorComboList() throws SQLException {
        List<Sector> sectors = new ArrayList<>();

        String querysector = "select * from sector";

        try {

            c = DBHelper.connect();
            if (c != null) {
                ps = c.prepareStatement(querysector);
                rs = ps.executeQuery();
                while (rs.next()) {
                    sectors.add(new Sector(rs.getInt("id"), rs.getString("sector_name")));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.disconnect(c, ps, rs);
        }

        return sectors;
    }

    public void addStudent(Student student,User user) throws SQLException {
        String query = "insert into student values ( seq_student.nextval,?,?,?,?,1 )";
        String userQuery ="insert into users values(seq_users.nextval,?,?,1,?,?,?)\n";

        try {

            c = DBHelper.connect();
            if (c != null) {
                ps = c.prepareStatement(query,new String[] {"id"});
                ps.setString(1, student.getName());
                ps.setString(2, student.getSurname());
                ps.setDate(3, new Date(student.getDob().getTime()));
                ps.setInt(4, student.getSectorId());
                ps.execute();
                rs=ps.getGeneratedKeys();
                if(rs.next()){
                    BigDecimal b = (BigDecimal) rs.getObject(1);
                    long studentId=b.longValue();
                    user.setStudentId(studentId);
                    System.out.println(studentId);
                }

                ps=c.prepareStatement(userQuery);
                ps.setString(1,user.getUsername());
                ps.setString(2,user.getPassword());
                ps.setString(3,user.getEmail());
                ps.setLong(4,user.getStudentId());
                ps.setInt(5,user.getRoleId());
                ps.execute();

            }

    }catch(Exception e)

    {
        e.printStackTrace();
    }finally

    {
        DBHelper.disconnect(c, ps, rs);
    }
}

    public Student getStudent(String studentId) throws SQLException {

        String query ="select s.*,SS.SECTOR_NAME from student s inner join sector ss on S.SECTOR_ID=SS.ID where s.id=? and S.ACTIVE=1";
        Student student = new Student();

        try{
            c=DBHelper.connect();
              if(c!=null){
                  ps=c.prepareStatement(query);
                  ps.setInt(1,Integer.parseInt(studentId));
                  rs=ps.executeQuery();
                  while (rs.next()){
                      student.setId(rs.getInt("id"));
                      student.setName(rs.getString("name"));
                      student.setSurname(rs.getString("surname"));
                      student.setDob(rs.getDate("dob"));
                      student.setSectorId(rs.getInt("sector_id"));
                      student.setSectorName(rs.getString("sector_name"));

                  }
              }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBHelper.disconnect(c,ps,rs);
        }
        return student;
    }

    public void updateStudent(Student student) throws SQLException {

        String query= " update student set name=?,surname=?,dob=?,sector_id=? where id=? ";

        try{
            c=DBHelper.connect();

            if(c!=null){
                ps=c.prepareStatement(query);
                ps.setString(1,student.getName());
                ps.setString(2,student.getSurname());
                ps.setDate(3,new Date(student.getDob().getTime()));
                ps.setInt(4,student.getSectorId());
                ps.setInt(5,student.getId());
                ps.execute();

            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBHelper.disconnect(c,ps,rs);
        }

    }

    public List<Student> getStudentBySimpleSearch(String searchValue) throws SQLException {

        String query = "select s.*,SS.SECTOR_NAME from student s " +
                "inner join sector ss on S.SECTOR_ID=SS.ID where" +
                " S.ACTIVE=1 and s.name like '%"+searchValue+"%' " +
                "or s.surname like '%"+searchValue+"%'";
        List<Student> students = new ArrayList<>();

        try {
            c = DBHelper.connect();
            if (c != null) {
                ps = c.prepareStatement(query);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setSurname(rs.getString("surname"));
                    student.setDob(rs.getDate("dob"));
                    student.setSectorId(rs.getInt("sector_id"));
                    student.setSectorName(rs.getString("sector_name"));
                    student.setActive(rs.getInt("active"));
                    students.add(student);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.disconnect(c, ps, rs);
        }
        return students;
    }

    public List<Student> getStudentListBySector(int sectorId) throws SQLException {

        String query = "select s.*,SS.SECTOR_NAME from student s " +
                "inner join sector ss on S.SECTOR_ID=SS.ID " +
                "where S.ACTIVE=1 and s.sector_id=?";
        List<Student> students = new ArrayList<>();

        try {
            c = DBHelper.connect();
            if (c != null) {
                ps = c.prepareStatement(query);
                ps.setInt(1,sectorId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setSurname(rs.getString("surname"));
                    student.setDob(rs.getDate("dob"));
                    student.setSectorId(rs.getInt("sector_id"));
                    student.setSectorName(rs.getString("sector_name"));
                    student.setActive(rs.getInt("active"));
                    students.add(student);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBHelper.disconnect(c, ps, rs);
        }
        return students;
    }


    public User doLogin(String username, String password) throws SQLException {

        User user = null;

        String query = "select u.*,R.ROLE_NAME from users u inner join roles r on  U.ROLE=R.ID" +
                "where u.username=? and u.password=? and u.active=1";

        try {
            c=DBHelper.connect();
            if(c!=null){
                ps=c.prepareStatement(query);
                ps.setString(1,username);
                ps.setString(2,password);
                rs=ps.executeQuery();

                if(rs.next()){
                    user= new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setRoleName(rs.getString("role_name"));
                    user.setStudentId(rs.getLong("student_id"));
                    user.setRoleId(rs.getInt("role"));

                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBHelper.disconnect(c,ps,rs);
        }
        return user;
    }


}
