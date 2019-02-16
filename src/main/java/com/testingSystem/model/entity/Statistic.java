package com.testingSystem.model.entity;

import java.util.Date;

public class Statistic {
    private Integer statisticId;
    private Date date;
    private boolean correct;
    private Integer questionId;
    private Integer userId;

    public Statistic(){

    }

    public Statistic(Date date, boolean correct, Integer questionId, Integer userId) {
        this.date = date;
        this.correct = correct;
        this.questionId = questionId;
        this.userId = userId;
    }

    public Integer getStatisticId() {
        return statisticId;
    }

    public Date getDate() {
        return date;
    }

    public boolean isCorrect() {
        return correct;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setStatisticId(Integer statisticId) {
        this.statisticId = statisticId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}