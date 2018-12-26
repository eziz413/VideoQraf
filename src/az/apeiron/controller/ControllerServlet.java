package az.apeiron.controller;

import az.apeiron.config.DBHelper;
import az.apeiron.data.DataManager;
import az.apeiron.model.Sector;
import az.apeiron.model.Student;
import az.apeiron.model.User;
import az.apeiron.util.Algoritms;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = "";
        String address = "";

        DataManager manager = new DataManager();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

        if (request.getParameter("action") != null) {
            action = request.getParameter("action");

        }

        try {

            if (action.equalsIgnoreCase("userRegister")) {
                String Username = request.getParameter("username");
                String Email = request.getParameter("email");
                System.out.println(Username + " " + Email);
            } else if (action.equalsIgnoreCase("studentRegister")) {

                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String dob = request.getParameter("dob");
                String sectorId = request.getParameter("sectorId");
                String email = request.getParameter("email");
                String username = request.getParameter("username");

                Student student = new Student();
                student.setName(name);
                student.setSurname(surname);
                student.setDob(simpleDateFormat.parse(dob));
                student.setSectorId(Integer.parseInt(sectorId));

                UUID uuid = UUID.randomUUID();
                String password = uuid.toString();
                password=password.substring(password.length()-10);
                System.out.println("pasword"+password);

                password= Algoritms.encodeSha256(password);

                User user = new User();
                user.setEmail(email);
                user.setUsername(username);
                user.setRoleId(1);
                user.setPassword(password);


                manager.addStudent(student,user);


                // System.out.println(name + " " + surname + " " + password);

            } else if (action.equalsIgnoreCase("getStudentList")) {
                List<Student> students = manager.getStudentList();
                request.setAttribute("students", students);
                address = "WEB-INF/parts/part-student-table.jsp";

            } else if (action.equalsIgnoreCase("getSectorComboList")) {
                List<Sector> sectors = manager.getSectorComboList();
                request.setAttribute("sectors", sectors);
                address = "WEB-INF/combo/part-sector-combo.jsp";

            } else if (action.equalsIgnoreCase("getStudent")) {
                String studentId = request.getParameter("studentId");

                Student student = manager.getStudent(studentId);
                request.setAttribute("student", student);
                address = "WEB-INF/parts/part-edit-student.jsp";

            } else if (action.equalsIgnoreCase("updateStudent")) {

                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String dob = request.getParameter("dob");
                String sectorId = request.getParameter("sectorId");
                String studentId = request.getParameter("studentId");


                Student student = new Student();
                student.setId(Integer.parseInt(studentId));
                student.setName(name);
                student.setSurname(surname);
                student.setDob(simpleDateFormat.parse(dob));
                student.setSectorId(Integer.parseInt(sectorId));

                manager.updateStudent(student);
            }else  if (action.equalsIgnoreCase("getStudentBySimpleSearch")){
                String searchValue = request.getParameter("searchValue");
                List<Student> students = manager.getStudentBySimpleSearch(searchValue);
                request.setAttribute("students",students);
                address="WEB-INF/parts/part-student-table.jsp";
            }else if (action.equalsIgnoreCase("getStudentComboListBySector")){
                int sectorId = Integer.parseInt(request.getParameter("sectorId")) ;
                List<Student> students = null;
                if(sectorId==0){
                    students=manager.getStudentList();
                }else {
                    students=manager.getStudentListBySector(sectorId);
                }
                request.setAttribute("students",students);
                address="WEB-INF/combo/part-student-combo.jsp";

            }


            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    public void init() throws ServletException {
        System.out.println("inti isledi");
    }

    @Override
    public void destroy() {
        System.out.println("destroy isledi");
    }
}
