package beans;


import java.sql.Timestamp;

public class WorkBook {

    public WorkBook(String name, String questionsId, int status) {
        this.name = name;
        this.questionsId = questionsId;
        this.status = status;
    }

    public WorkBook(int id, String name, String questionsId, int status, Timestamp createTime) {
        this.id = id;
        this.name = name;
        this.questionsId = questionsId;
        this.status = status;
        this.createTime = createTime;
    }

    private int id;
    private String name;
    private String questionsId;
    private int status;
    private Timestamp createTime;
    private int questionsNum;

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

    public String getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(String questionsId) {
        this.questionsId = questionsId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getQuestionsNum() {
        if (null == questionsId || questionsId.isEmpty()) return 0;
        return questionsId.split(",").length;
    }
}
