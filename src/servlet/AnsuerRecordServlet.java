package servlet;

import beans.AnswerRecord;
import beans.UserQuestion;
import dao.AnswerRecordDao;
import dao.UserQuestionDao;

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

@WebServlet(name = "AnsuerRecordServlet", value = "/ansuer/record/*")
public class AnsuerRecordServlet extends HttpServlet {
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
                case "add":
                    insert(request, response);
                    return;
                case "list":
                    ansuerRecordList(request, response);
                    return;
                case "question":
                    userAnsuerQuestionList(request, response);
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

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        HttpSession session = request.getSession();
        String workId = request.getParameter("workId");
        String questionIds = request.getParameter("ids");
        String[] questionArray = questionIds.split(",");
        int questionCount = questionArray.length;
        int correctCount = 0;
        int incorrectCount = 0;

        List<UserQuestion> userQuestionList = new ArrayList<>();

        for (String qid : questionArray) {
            String questionId = request.getParameter("id_" + qid);
            String answer = request.getParameter("answer_" + qid);
            String userAnswer = request.getParameter("userAnswer_" + qid);

            if (answer.equals(userAnswer))
                correctCount++;
            else
                incorrectCount++;

            UserQuestion userQuestion = new UserQuestion(Integer.parseInt(questionId), answer, userAnswer);
            userQuestionList.add(userQuestion);
        }
        float accuracy =  (float)correctCount /  (float)questionCount;
        String level = null;
        if (accuracy >= 0.9f)
            level = "A";
        else if(accuracy >= 0.8f)
            level = "B";
        else if(accuracy >= 0.6f)
            level = "C";
        else if(accuracy >= 0.4f)
            level = "D";
        else
            level = "E";

        Object userId = session.getAttribute("userId");
        AnswerRecord answerRecord = new AnswerRecord(Integer.valueOf(userId.toString()), Integer.valueOf(workId), questionCount, correctCount, incorrectCount, level);

        AnswerRecordDao answerRecordDao = new AnswerRecordDao();
        answerRecordDao.insertAnswerRecord(answerRecord, userQuestionList);

        response.sendRedirect("/workbook/list");
        return;
    }

    private void ansuerRecordList(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        String userId = request.getParameter("userId");
        String studentName = request.getParameter("studentName");
        AnswerRecordDao answerRecordDao = new AnswerRecordDao();
        List<AnswerRecord>  answerRecords = answerRecordDao.selectAnswerRecordList(Integer.valueOf(userId.toString()));
        request.setAttribute("answerRecords", answerRecords);
        request.setAttribute("studentName", studentName);

        request.getRequestDispatcher("/teacher/student_history.jsp").forward(request, response);
    }

    private void userAnsuerQuestionList(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        HttpSession session = request.getSession();
        Object rule = session.getAttribute("rule");

        String recordId = request.getParameter("recordId");
        String workName = request.getParameter("workName");
        String questionCount = request.getParameter("questionCount");
        String correctCount = request.getParameter("correctCount");
        String incorrectCount = request.getParameter("incorrectCount");
        String level = request.getParameter("level");
        String userName = null;
        UserQuestionDao userQuestionDao = new UserQuestionDao();
        List<UserQuestion> userQuestions = userQuestionDao.selectUserQuestionList(Integer.valueOf(recordId));
        if (String.valueOf(rule).equals("teacher")) {
            userName = request.getParameter("studentName");
        } else {
            userName = String.valueOf(session.getAttribute("username"));
        }
        request.setAttribute("userQuestions", userQuestions);
        request.setAttribute("workName", workName);
        request.setAttribute("userName", userName);
        request.setAttribute("questionCount", questionCount);
        request.setAttribute("correctCount", correctCount);
        request.setAttribute("incorrectCount", incorrectCount);
        request.setAttribute("level", level);

        request.getRequestDispatcher("/review.jsp").forward(request, response);
    }
}
