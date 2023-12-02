package dao;

import beans.AnswerRecord;
import beans.UserQuestion;
import common.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerRecordDao {

    /**
     * add answerRecord
     * @param answerRecord
     */
    public String insertAnswerRecord(AnswerRecord answerRecord, List<UserQuestion> userQuestionList) throws SQLException {
        String newAnswerRecordIds = null;
        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);
        String insertRecord ="insert into answer_record(user_id, workbook_id, question_count, correct_count, incorrect_count, level) values(?, ?, ?, ?, ?, ?)";
        String insertUserQue = "insert into user_question(record_id, question_id, question_answer, user_answer) value (?, ?, ?, ?)";
        try {
            PreparedStatement pr = con.prepareStatement(insertRecord, Statement.RETURN_GENERATED_KEYS);
            pr.setInt(1, answerRecord.getUserId());
            pr.setInt(2, answerRecord.getWorkbookId());
            pr.setInt(3, answerRecord.getQuestionCount());
            pr.setInt(4, answerRecord.getCorrectCount());
            pr.setInt(5, answerRecord.getIncorrectCount());
            pr.setString(6, answerRecord.getLevel());
            pr.execute();
            ResultSet resultSet = pr.getGeneratedKeys();
            if (resultSet.next()) {
                int newId = resultSet.getInt(1);
                PreparedStatement uqPr = con.prepareStatement(insertUserQue);

                for (UserQuestion userQuestion : userQuestionList) {
                    uqPr.setInt(1, newId);
                    uqPr.setInt(2, userQuestion.getQuestionId());
                    uqPr.setString(3, userQuestion.getQuestionAnswer());
                    uqPr.setString(4, userQuestion.getUserAnswer());
                    uqPr.addBatch();
                }
                int[] ns = uqPr.executeBatch();
            }
            con.commit();
            return newAnswerRecordIds;
        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }

    public List<AnswerRecord> selectAnswerRecordList(int userId) throws SQLException {
        List<AnswerRecord> list = new ArrayList<>();
        String sql = "select t1.*, t2.name from answer_record t1 left join workbook t2 on t1.workbook_id = t2.id where user_id = " + userId + " order by t1.id desc";
        Connection con = DBConnection.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet re = st.executeQuery(sql);
            while(re.next()) {
                int id = re.getInt(1);
                userId = re.getInt(2);
                int workbookId = re.getInt(3);
                int questionCount = re.getInt(4);
                int correctCount = re.getInt(5);
                int incorrectCount = re.getInt(6);
                String level = re.getString(7);
                Timestamp createTime = re.getTimestamp(8);
                String workbookName = re.getString(9);
                AnswerRecord answerRecord = new AnswerRecord(id, userId, workbookId, questionCount, correctCount, incorrectCount, level, createTime);
                answerRecord.setWorkbookName(workbookName);
                list.add(answerRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    public AnswerRecord selectAnswerRecordById(int id) throws SQLException {
        AnswerRecord answerRecord = null;
        String sql = "select * from answer_record where id = " + id;
        Connection con = DBConnection.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet re = st.executeQuery(sql);
            while(re.next()) {
                id = re.getInt(1);
                int userId = re.getInt(2);
                int workbookId = re.getInt(3);
                int questionCount = re.getInt(4);
                int correctCount = re.getInt(5);
                int incorrectCount = re.getInt(6);
                String level = re.getString(7);
                Timestamp createTime = re.getTimestamp(8);
                answerRecord = new AnswerRecord(id, userId, workbookId, questionCount, correctCount, incorrectCount, level, createTime);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return answerRecord;
    }
}
