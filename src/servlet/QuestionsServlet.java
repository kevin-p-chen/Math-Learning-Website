package servlet;

import beans.Questions;
import dao.QuestionsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "QuestionsServlet", value = "/questions/*")
public class QuestionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

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
                    questionsList(request, response);
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("msg", "system is exception!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void questionsList(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        String ids = request.getParameter("ids");
        String workId = request.getParameter("workId");
        String workName = request.getParameter("workName");

        QuestionsDao questionsDao = new QuestionsDao();
        List<Questions> questionsList = questionsDao.selectQuestionsList(ids);

        request.setAttribute("questionsList", questionsList);
        request.setAttribute("workId", workId);
        request.setAttribute("workName", workName);
        request.setAttribute("questionsIds", ids);

        HttpSession session = request.getSession();
        Object rule = session.getAttribute("rule");
        if (String.valueOf(rule).equals("teacher"))
            request.getRequestDispatcher("/teacher/questions/list.jsp").forward(request, response);
        else
            request.getRequestDispatcher("/student/play.jsp").forward(request, response);
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");

        String choice = "";
        for (int i = 0; i < 4; i++) {
            choice = choice + request.getParameter("choice_".concat(String.valueOf(i)));
            if (i < 3) {
                choice = choice.concat("|");
            }
        }
        Questions questions = new Questions(question, choice, answer, 0);

        String oldIds = request.getParameter("ids");
        String workId = request.getParameter("workId");
        String workName = request.getParameter("workName");

        QuestionsDao questionsDao = new QuestionsDao();
        String newIds = questionsDao.insertQuestions(questions, Integer.parseInt(workId), oldIds);

        response.sendRedirect("/questions/list?ids=".concat(newIds).concat("&workId=").concat(workId).concat("&workName=".concat(workName)));
        return;
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");

        String choice = "";
        for (int i = 0; i < 4; i++) {
            choice = choice + request.getParameter("choice_".concat(String.valueOf(i)));
            if (i < 3) {
                choice = choice.concat("|");
            }
        }
        Questions questions = new Questions(id, question, choice, answer, 0, null);

        QuestionsDao questionsDao = new QuestionsDao();
        questionsDao.updateQuestions(questions);

        String ids = request.getParameter("ids");
        String workId = request.getParameter("workId");
        String workName = request.getParameter("workName");
        response.sendRedirect("/questions/list?ids=".concat(ids).concat("&workId=").concat(workId).concat("&workName=".concat(workName)));
        return;
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        int id = Integer.valueOf(request.getParameter("id"));

        String oldIds = request.getParameter("ids");
        List<String> newIdList = new ArrayList<>(Arrays.asList(oldIds.split(",")));
        newIdList.remove(String.valueOf(id));
        String workId = request.getParameter("workId");
        String workName = request.getParameter("workName");
        String newIds = String.join(",", newIdList);

        QuestionsDao questionsDao = new QuestionsDao();
        questionsDao.deleteQuestionsById(id, workId, newIds);

        response.sendRedirect("/questions/list?ids=".concat(newIds).concat("&workId=").concat(workId).concat("&workName=".concat(workName)));
        return;
    }
}
