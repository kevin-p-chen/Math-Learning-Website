package beans;


public class UserQuestion {

  public UserQuestion(){}

  public UserQuestion(String questionAnswer, String userAnswer, Questions questions) {
    this.questionAnswer = questionAnswer;
    this.userAnswer = userAnswer;
    this.questions = questions;
  }

  public UserQuestion(int questionId, String questionAnswer, String userAnswer) {
    this.questionId = questionId;
    this.questionAnswer = questionAnswer;
    this.userAnswer = userAnswer;
  }

  private int id;
  private int recordId;
  private int questionId;
  private String questionAnswer;
  private String userAnswer;
  private Questions questions;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public int getRecordId() {
    return recordId;
  }

  public void setRecordId(int recordId) {
    this.recordId = recordId;
  }


  public int getQuestionId() {
    return questionId;
  }

  public void setQuestionId(int questionId) {
    this.questionId = questionId;
  }


  public String getQuestionAnswer() {
    return questionAnswer;
  }

  public void setQuestionAnswer(String questionAnswer) {
    this.questionAnswer = questionAnswer;
  }


  public String getUserAnswer() {
    return userAnswer;
  }

  public void setUserAnswer(String userAnswer) {
    this.userAnswer = userAnswer;
  }

  public Questions getQuestions() {
    return questions;
  }

  public void setQuestions(Questions questions) {
    this.questions = questions;
  }
}
