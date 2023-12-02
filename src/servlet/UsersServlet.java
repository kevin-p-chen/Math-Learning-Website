package servlet;

import beans.Users;
import dao.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "UsersServlet", value = "/users/*")
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter writer = response.getWriter();

        Object rule = session.getAttribute("rule");
        if (Objects.isNull(rule)) {
            response.sendRedirect("/login.html");
            return;
        }
        request.setCharacterEncoding("UTF-8");
        String requestURI = request.getRequestURI();
        String method = requestURI.substring(requestURI.lastIndexOf("/") + 1);

        try {
            switch (method){
                case "list":
                    userList(request, response);
                    return;
                case "activate":
                    activateUser(request, response);
                    return;
                case "logout":
                    logout(request, response);
                    return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            writer.write("system is exception!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void userList(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {

        UsersDao usersDao = new UsersDao();
        List<Users> usersList = usersDao.selectUsersList();

        request.setAttribute("usersList", usersList);
        request.getRequestDispatcher("/teacher/index.jsp").forward(request, response);

    }

    private void activateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        UsersDao usersDao = new UsersDao();
        usersDao.activateUserById(Integer.valueOf(id));
        response.sendRedirect("/index.jsp");
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        Object rule = session.getAttribute("rule");
        session.removeAttribute("userId");
        session.removeAttribute("email");
        session.removeAttribute("username");
        session.removeAttribute("rule");
        if (String.valueOf(rule).equals("teacher"))
            response.sendRedirect("/teacher");
        else
            response.sendRedirect("/");

    }
}
