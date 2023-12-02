package servlet;

import beans.Users;
import common.MailConfig;
import dao.UsersDao;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

@WebServlet(name = "RegServlet", value = "/regist/*")
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String requestURI = request.getRequestURI();
        String method = requestURI.substring(requestURI.lastIndexOf("/") + 1);

        PrintWriter writer = response.getWriter();
        try {
            switch (method){
                case "user":
                    regUser(request, response);
                    return;
                case "activate":
                    activateUser(request, response);
                    return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("msg", "system is exception!");
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void regUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        UsersDao usersDao = new UsersDao();
        Users users = usersDao.selectUsersByEamil(email);
        if (null != users) {
            request.setAttribute("msg", "user is exist!");
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }
        int id = usersDao.register(new Users(name, password, email));
        HttpSession session = request.getSession();
        session.setAttribute("userId", id);
        session.setAttribute("username", name);
        session.setAttribute("email", email);
        session.setAttribute("rule", "student");

        String body =  "<a href=\"http://localhost:8080/regist/activate?id=".concat(String.valueOf(id)).concat("\">click this link</a>").concat(" activate your account!");

        send(email, body);
        response.sendRedirect("/activate.jsp");
    }

    private void activateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        UsersDao usersDao = new UsersDao();
        usersDao.activateUserById(Integer.valueOf(id));
        response.sendRedirect("/student/index.jsp");
    }

    private static void send(String to, String body) {
        try {
            Properties mailProps = MailConfig.properties;
            String propsFrom = mailProps.getProperty("from");
            String propsAuthorizationCode = mailProps.getProperty("authorizationCode");
            String propsProtocol = mailProps.getProperty("protocol");
            String propsHost = mailProps.getProperty("host");
            String propsPort = mailProps.getProperty("port");


            Properties props = System.getProperties();

            String from = mailProps.getProperty("from");

            String subject =  "Fun With Math Activate Mail" ;

            // 授权码
            String authorizationCode =  mailProps.getProperty("authorizationCode");
            // 协议
            props.setProperty("mail.transport.protocol",  mailProps.getProperty("protocol"));
            // 主机
            props.setProperty("mail.smtp.host", mailProps.getProperty("host"));
            // 端口
            props.put("mail.smtp.port", mailProps.getProperty("port"));

            props.setProperty("mail.smtp.auth", "true");

            //建立会话
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, authorizationCode);
                }
            });
            Message msg = new MimeMessage(session);//生成消息体
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = { new InternetAddress(to) };
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(body, "text/html;charset=utf8");

            Transport.send(msg);

        } catch (Exception ex) {

            ex.printStackTrace();

        }
    }
}
