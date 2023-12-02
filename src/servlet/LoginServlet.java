package servlet;

import beans.Users;
import dao.UsersDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        Object rule = session.getAttribute("rule");
        if (!Objects.isNull(rule)) {
            if (Objects.equals(rule, "teacher")) {
                response.sendRedirect("/users/list");
                return;
            } else {
                response.sendRedirect("/workbook/list");
                return;
            }
        }

        String email = request.getParameter("email");
        String password1 = request.getParameter("password");

        if (Objects.isNull(email) || email.length() < 1 || Objects.isNull(password1) || password1.length() < 1) {
	        request.setAttribute("msg", "email and password is not empty!");
	        request.getRequestDispatcher("/login.html").forward(request, response);
	        return;
        }

        UsersDao usersDao = new UsersDao();
        Users users = null;
        try {
            users = usersDao.selectUsersByEamil(email);
        } catch (SQLException e) {
            request.setAttribute("msg", "system is exception!");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return;
        }
        if (null == users) {
            request.setAttribute("msg", "user not exist!");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return;
        }
        if (!password1.equals(users.getPassword())) {
            request.setAttribute("msg", "wrong password!");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return;
        }
        session.setAttribute("userId", users.getId());
        session.setAttribute("email", email);
        session.setAttribute("username", users.getName());
        session.setAttribute("rule", users.getRule());

        if ("teacher".equals(users.getRule())) {
            response.sendRedirect("/users/list");
            return;
        } else {
            response.sendRedirect("/workbook/list");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
