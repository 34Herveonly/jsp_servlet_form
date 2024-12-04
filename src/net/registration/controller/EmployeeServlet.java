package net.registration.controller;

import daoRegistration.EmployeeDao;
import registration.model.Employees;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;


@WebServlet("/Register")
public class EmployeeServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmployeeServlet() {
        super();
    }

    EmployeeDao employeeDao = new EmployeeDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().append("Served at: ").append(request.getContextPath());

    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/employeeRegister/EmployeeRegister.jsp");
dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

String firstName = request.getParameter("firstName");
String lastName = request.getParameter("lastName");
String username = request.getParameter("username");
String password = request.getParameter("password");
String adress = request.getParameter("adress");
String contact = request.getParameter("contact");

        Employees employee = new Employees();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setAdress(adress);
        employee.setContact(contact);

        try {
            employeeDao.registerEmployee(employee);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/employeeRegister/EmployeeDetails.jsp");
        dispatcher.forward(request, response);    }
}




