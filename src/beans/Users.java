package beans;

import java.sql.Timestamp;

public class Users {

    public Users(){}

    public Users(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Users(Integer id, String name, String email, String password, String sex, Integer age, Integer status, Timestamp createTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.age = age;
        this.status = status;
        this.createTime = createTime;
    }

    private int id;

    // username
    private String name;

    // password
    private String email;

    // password
    private String password;

    // sex
    private String sex;

    // age
    private int age;

    // rule
    private String rule;

    // status
    private int status;

    // create_time
    private Timestamp createTime;

    private String recordIds;

    private int recordNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getRecordIds() {
        return recordIds;
    }

    public void setRecordIds(String recordIds) {
        this.recordIds = recordIds;
    }

    public int getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(int recordNum) {
        this.recordNum = recordNum;
    }
}
