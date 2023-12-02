package beans;

import java.sql.Timestamp;

public class Questions {

    public Questions(String question, String choice, String answer, int status) {
        this.question = question;
        this.choice = choice;
        this.answer = answer;
        this.status = status;
    }

    public Questions(int id, String question, String choice, String answer, int status, Timestamp createTime) {
        this.id = id;
        this.question = question;
        this.choice = choice;
        this.answer = answer;
        this.status = status;
        this.createTime = createTime;
    }

    private int id;

    // question
    private String question;

    // choice
    private String choice;

    private String[] choiceArray;

    // answer
    private String answer;

    // status
    private int status;

    // create_time
    private Timestamp createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String[] getChoiceArray() {
        return choiceArray;
    }

    public void setChoiceArray(String[] choiceArray) {
        this.choiceArray = choiceArray;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
}
