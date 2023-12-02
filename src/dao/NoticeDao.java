package dao;

import beans.Notice;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NoticeDao {
public void add(Notice notice) {
	Connection con = DBConnection.getConnection();
	String sql ="insert into notice(title,details,n_time) values(?,?,now())";
	try {
		PreparedStatement pr = con.prepareStatement(sql);
		pr.setString(1, notice.getTitle());
		pr.setString(2, notice.getDetails());
		pr.execute();
		
	} catch (Exception e) {
		// TODO: handle exception
	}
}
public List<Notice> selectAll() {
	List<Notice> list = new ArrayList();
	String sql = "select * from notice";
	Connection con = DBConnection.getConnection();
	try {
		Statement st = con.createStatement();
		ResultSet re = st.executeQuery(sql);
		while(re.next()) {
			Integer id = re.getInt(1);
			String title = re.getString(2);
			String datails = re.getString(3);
			String n_time = re.getString(4);
			Notice notice = new Notice(id, title, datails, n_time);
			list.add(notice);
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	return list;
}
public void delete(String id1) {
	Connection con = DBConnection.getConnection();
	String sql = "delete from notice where id="+id1;
	try {
		Statement st = con.createStatement();
		st.execute(sql);
	} catch (Exception e) {
		// TODO: handle exception
	}
}

public void update(Notice notice) {
	Connection con = DBConnection.getConnection();
	String sql = "update notice set title=?,details=?";
	try {
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, notice.getTitle());
		st.setString(2, notice.getDetails());
		st.execute();
	} catch (Exception e) {
		// TODO: handle exception
	}
}

}
