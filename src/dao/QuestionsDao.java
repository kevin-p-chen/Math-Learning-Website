package dao;

import beans.Questions;
import common.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionsDao {

    /**
     * add questions
     * @param questions
     */
    public String insertQuestions(Questions questions, int workId, String oldQuestionsId) throws SQLException {
        String newQuestionsIds = null;
        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);
        String sql ="insert into questions(question, choice, answer, status, create_time) values(?, ?, ?, ?, now())";
        String updateWorkbookSql = "update workbook set questions_id = ? where id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pr.setString(1, questions.getQuestion());
            pr.setString(2, questions.getChoice());
            pr.setString(3, questions.getAnswer());
            pr.setInt(4, questions.getStatus());
            pr.execute();
            ResultSet resultSet = pr.getGeneratedKeys();
            if (resultSet.next()) {
                int newId = resultSet.getInt(1);
                PreparedStatement wbPr = con.prepareStatement(updateWorkbookSql);

                List<String> questionsIdList = new ArrayList<>(Arrays.asList(oldQuestionsId.split(",")));
                if (questionsIdList.get(0).isEmpty()) questionsIdList.remove(0);
                questionsIdList.add(String.valueOf(newId));
                newQuestionsIds = String.join(",", questionsIdList);
                wbPr.setString(1, newQuestionsIds);
                wbPr.setInt(2, workId);
                wbPr.execute();
            }
            con.commit();
            return newQuestionsIds;
        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }

    public void updateQuestions(Questions questions) throws SQLException {
        String sql = "update questions set question = ?, choice = ?, answer = ?, status = ? where id = ?";
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, questions.getQuestion());
            pr.setString(2, questions.getChoice());
            pr.setString(3, questions.getAnswer());
            pr.setInt(4, questions.getStatus());
            pr.setInt(5, questions.getId());
            pr.execute();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
    }
    public List<Questions> selectQuestionsList(String ids) throws SQLException {
        List<Questions> list = new ArrayList<>();
        String sql = "select * from questions where find_in_set(id, '" + ids + "') and status = 0 order by id asc";
        Connection con = DBConnection.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet re = st.executeQuery(sql);
            while(re.next()) {
                Integer id = re.getInt(1);
                String question = re.getString(2);
                String choice = re.getString(3);
                String answer = re.getString(4);
                int status = re.getInt(5);
                Timestamp createTime = re.getTimestamp(6);
                Questions questions = new Questions(id, question, choice, answer, status, createTime);

                if (null != choice && !choice.isEmpty())
                    questions.setChoiceArray(choice.split("\\|"));
                list.add(questions);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    public Questions selectQuestionsById(int id) throws SQLException {
        Questions questions = null;
        String sql = "select * from questions where id = " + id;
        Connection con = DBConnection.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet re = st.executeQuery(sql);
            while(re.next()) {
                id = re.getInt(1);
                String question = re.getString(2);
                String choice = re.getString(3);
                String answer = re.getString(4);
                int status = re.getInt(5);
                Timestamp createTime = re.getTimestamp(6);
                questions = new Questions(id, question, choice, answer, status, createTime);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return questions;
    }

    public void deleteQuestionsById(int id, String workId, String newQuestionids) throws SQLException {
        String sql = "delete from questions where id = ?";
        String updateWBSql = "update workbook set questions_id = '" + newQuestionids + "' where id = " + workId;
        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, id);
            pr.execute();
            PreparedStatement upPr = con.prepareStatement(updateWBSql);
            upPr.execute();
            con.commit();
        } catch (Exception e) {
            // TODO: handle exception
            con.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }
}
