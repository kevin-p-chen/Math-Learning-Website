package beans;


import java.sql.Timestamp;

public class AnswerRecord {

  public AnswerRecord(){}

  public AnswerRecord(int userId, int workbookId, int questionCount, int correctCount, int incorrectCount, String level) {
    this.userId = userId;
    this.workbookId = workbookId;
    this.questionCount = questionCount;
    this.correctCount = correctCount;
    this.incorrectCount = incorrectCount;
    this.level = level;
  }

  public AnswerRecord(int id, int userId, int workbookId, int questionCount, int correctCount, int incorrectCount, String level, Timestamp createTime) {
    this.id = id;
    this.userId = userId;
    this.workbookId = workbookId;
    this.questionCount = questionCount;
    this.correctCount = correctCount;
    this.incorrectCount = incorrectCount;
    this.level = level;
    this.createTime = createTime;
  }

  private int id;
  private int userId;
  private int workbookId;
  private int questionCount;
  private int correctCount;
  private int incorrectCount;
  private String level;
  private java.sql.Timestamp createTime;
  private UserQuestion userQuestion;
  private String workbookName;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }


  public int getWorkbookId() {
    return workbookId;
  }

  public void setWorkbookId(int workbookId) {
    this.workbookId = workbookId;
  }


  public int getQuestionCount() {
    return questionCount;
  }

  public void setQuestionCount(int questionCount) {
    this.questionCount = questionCount;
  }


  public int getCorrectCount() {
    return correctCount;
  }

  public void setCorrectCount(int correctCount) {
    this.correctCount = correctCount;
  }


  public int getIncorrectCount() {
    return incorrectCount;
  }

  public void setIncorrectCount(int incorrectCount) {
    this.incorrectCount = incorrectCount;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

  public UserQuestion getUserQuestion() {
    return userQuestion;
  }

  public void setUserQuestion(UserQuestion userQuestion) {
    this.userQuestion = userQuestion;
  }

  public String getWorkbookName() {
    return workbookName;
  }

  public void setWorkbookName(String workbookName) {
    this.workbookName = workbookName;
  }
}
