package dao;

import beans.Questions;
import beans.UserQuestion;
import common.db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserQuestionDao {

    public List<UserQuestion> selectUserQuestionList(int recordId) throws SQLException {
        List<UserQuestion> list = new ArrayList<>();
        String sql = "select t2.question, t2.choice, t1.question_answer, t1.user_answer from user_question t1" +
                " inner join questions t2 on t1.question_id = t2.id where record_id = " + recordId;
        Connection con = DBConnection.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet re = st.executeQuery(sql);
            while(re.next()) {
                String question = re.getString(1);
                String choice = re.getString(2);
                String answer = re.getString(3);
                String userAnswer = re.getString(4);

                Questions questions = new Questions(question, choice, answer, 0);
                if (null != choice && !choice.isEmpty())
                    questions.setChoiceArray(choice.split("\\|"));
                UserQuestion userQuestion = new UserQuestion(answer, userAnswer, questions);
                list.add(userQuestion);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return list;
    }
}
