package dao;

import beans.Users;
import common.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsersDao {

    /**
     * add user
     * @param users
     */
    public int register(Users users) throws SQLException {
        Connection con = DBConnection.getConnection();
        String sql ="insert into user(name, password, sex, age, status, email, create_time) values(?, ?, ?, ?, ?, ?, now())";
        try {
            PreparedStatement pr = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pr.setString(1, users.getName());
            pr.setString(2, users.getPassword());
            pr.setString(3, users.getSex());
            pr.setInt(4, users.getAge());
            pr.setInt(5, users.getStatus());
            pr.setString(6, users.getEmail());
            pr.execute();
            ResultSet resultSet = pr.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
    }

    public List<Users> selectUsersList() throws SQLException {
        List<Users> list = new ArrayList<>();
        //String sql = "select t1.*, GROUP_CONCAT(t2.id) record_ids from user t1 left join answer_record t2 on t1.id = t2.user_id GROUP BY t2.user_id, workbook_id";
        String sql = "select t1.*, GROUP_CONCAT(t2.id) record_ids from user t1 left join answer_record t2 on t1.id = t2.user_id where t1.rule='student' GROUP BY t1.id";
        Connection con = DBConnection.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet re = st.executeQuery(sql);
            while(re.next()) {
                Integer id = re.getInt(1);
                String name = re.getString(2);
                String email = re.getString(3);
                String password = re.getString(4);
                String sex = re.getString(5);
                int age = re.getInt(6);
                int status = re.getInt(7);
                Timestamp createTime = re.getTimestamp(8);
                String recordIds = re.getString(10);
                Users users = new Users(id, name, email, password, sex, age, status, createTime);
                if (Objects.nonNull(recordIds) && !recordIds.isEmpty()) {
                    users.setRecordIds(recordIds);
                    users.setRecordNum(recordIds.split(",").length);
                } else
                    users.setRecordNum(0);
                list.add(users);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    public Users selectUsersByEamil(String email) throws SQLException {
        Users users = null;
        String sql = "select * from user where email = '" + email + "'";
        Connection con = DBConnection.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet re = st.executeQuery(sql);
            while(re.next()) {
                Integer id = re.getInt(1);
                String name = re.getString(2);
                email = re.getString(3);
                String password = re.getString(4);
                String sex = re.getString(5);
                int age = re.getInt(6);
                int status = re.getInt(7);
                Timestamp createTime = re.getTimestamp(8);
                String rule = re.getString(9);
                users = new Users(id, name, email, password, sex, age, status, createTime);
                users.setRule(rule);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return users;
    }

    public void activateUserById(int id) throws SQLException {
        String sql = "update user set status = 1 where id = ?";
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setInt(1, id);
            pr.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
    }
}
