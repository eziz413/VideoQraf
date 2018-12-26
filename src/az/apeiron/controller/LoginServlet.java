package az.apeiron.controller;

import az.apeiron.data.DataManager;
import az.apeiron.model.User;
import az.apeiron.util.Algoritms;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String address = "";
        String action = "";

        DataManager  manager = new DataManager();

        if (request.getParameter("action") != null) {
            action=request.getParameter("action");

        }
        try {
            if (action.equalsIgnoreCase("doLogin")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                HttpSession userSession = request.getSession(true);

                if (username == null || username.trim().equals("")
                        || password == null || password.trim().equals("")) {
                    request.setAttribute("errorMesage", "Please fill empty fields");
                    address="login.jsp";
                    System.out.println("please isledi");
                }else{


                    User user = manager.doLogin(username, Algoritms.encodeSha256(password));
                    if(user == null){

                        request.setAttribute("errorMesage", "Invalide username or password");
                        address="login.jsp";
                        System.out.println("invalide isledi");
                    }else {

                        userSession.setAttribute("user",user);
                        address="index.jsp";
                        System.out.println("index isledi");
                    }

                }

            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
