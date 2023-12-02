package servlet;

import beans.AnswerRecord;
import beans.WorkBook;
import dao.AnswerRecordDao;
import dao.WorkBookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "UsersServlet", value = "/workbook/*")
public class WorkBookServlet extends HttpServlet {
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
                    workBookList(request, response);
                    return;
                case "add":
                    insert(request, response);
                    return;
                case "edit":
                    update(request, response);
                    return;
                case "delete":
                    delete(request, response);
                    return;
                case "info":
                    info(request, response);
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

    private void workBookList(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        HttpSession session = request.getSession();
        Object rule = session.getAttribute("rule");
        if (String.valueOf(rule).equals("teacher")) {
            WorkBookDao workBookDao = new WorkBookDao();
            List<WorkBook> workBookList = workBookDao.selectWorkBookList(true);

            request.setAttribute("workBookList", workBookList);
            request.getRequestDispatcher("/teacher/workbook/list.jsp").forward(request, response);
            return;
        } else {
            WorkBookDao workBookDao = new WorkBookDao();
            List<WorkBook> workBookList = workBookDao.selectWorkBookList(false);

            List<AnswerRecord> answerRecords = new ArrayList<>();
            Object userId = session.getAttribute("userId");
            if (Objects.nonNull(userId) && userId instanceof Integer) {
                AnswerRecordDao answerRecordDao = new AnswerRecordDao();
                answerRecords = answerRecordDao.selectAnswerRecordList(Integer.valueOf(userId.toString()));
            }

            request.setAttribute("workBookList", workBookList);
            request.setAttribute("answerRecords", answerRecords);
            request.getRequestDispatcher("/student/index.jsp").forward(request, response);
        }
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        String name = request.getParameter("name");
        String questionsId = request.getParameter("questionsId");
        int status = Integer.parseInt(request.getParameter("status"));
        WorkBook workBook = new WorkBook(name, questionsId, status);

        WorkBookDao workBookDao = new WorkBookDao();
        workBookDao.insertWorkBook(workBook);

        response.sendRedirect("/workbook/list");
        return;
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String questionsId = request.getParameter("questionsId");
        int status = Integer.parseInt(request.getParameter("status"));
        WorkBook workBook = new WorkBook(id, name, questionsId, status, null);

        WorkBookDao workBookDao = new WorkBookDao();
        workBookDao.updateWorkBook(workBook);

        response.sendRedirect("/workbook/list");
        return;
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        int id = Integer.valueOf(request.getParameter("id"));

        WorkBookDao workBookDao = new WorkBookDao();
        workBookDao.deleteWorkBookById(id);

        response.sendRedirect("/workbook/list");
        return;
    }

    private void info(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        int id = Integer.valueOf(request.getParameter("id"));

        WorkBookDao workBookDao = new WorkBookDao();
        WorkBook workBook = workBookDao.selectWorkBookById(id);
        request.setAttribute("workBook", workBook);

        request.getRequestDispatcher("/teacher/workbook/edit.jsp").forward(request, response);
    }
}
