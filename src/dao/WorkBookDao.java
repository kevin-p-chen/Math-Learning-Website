package dao;

import beans.WorkBook;
import common.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkBookDao {

    /**
     * add workBook
     * @param workBook
     */
    public void insertWorkBook(WorkBook workBook) throws SQLException {
        Connection con = DBConnection.getConnection();
        String sql ="insert into workbook(name, questions_id, status, create_time) values(?, ?, ?, now())";
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, workBook.getName());
            pr.setString(2, workBook.getQuestionsId());
            pr.setInt(3, workBook.getStatus());
            pr.execute();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
    }

    public void updateWorkBook(WorkBook workBook) throws SQLException {
        String sql = "update workbook set name = ?, questions_id = ?, status = ? where id = ?";
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, workBook.getName());
            pr.setString(2, workBook.getQuestionsId());
            pr.setInt(3, workBook.getStatus());
            pr.setInt(4, workBook.getId());
            pr.execute();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
    }

    public List<WorkBook> selectWorkBookList(boolean isTeacher) throws SQLException {
        List<WorkBook> list = new ArrayList<>();
        String sql = null;
        if (isTeacher)
            sql = "select * from workbook";
        else
            //sql = "select * from workbook where id not in (select workbook_id from answer_record)";
            sql = "select * from workbook where status = 0";
        Connection con = DBConnection.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet re = st.executeQuery(sql);
            while(re.next()) {
                Integer id = re.getInt(1);
                String name = re.getString(2);
                String questionsId = re.getString(3);
                int status = re.getInt(4);
                Timestamp createTime = re.getTimestamp(5);
                WorkBook questions = new WorkBook(id, name, questionsId, status, createTime);
                list.add(questions);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    public WorkBook selectWorkBookById(int id) throws SQLException {
        WorkBook workBook = null;
        String sql = "select * from workbook where id = " + id;
        Connection con = DBConnection.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet re = st.executeQuery(sql);
            while(re.next()) {
                id = re.getInt(1);
                String name = re.getString(2);
                String questionsId = re.getString(3);
                int status = re.getInt(4);
                Timestamp createTime = re.getTimestamp(5);
                workBook = new WorkBook(id, name, questionsId, status, createTime);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return workBook;
    }

    public void deleteWorkBookById(int id) throws SQLException {
        String sql = "delete from workbook where id = ?";
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, id);
            pr.execute();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
    }
}
