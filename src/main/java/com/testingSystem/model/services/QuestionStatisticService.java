package com.testingSystem.model.services;

import com.testingSystem.model.dao.QuestionDao;
import com.testingSystem.model.dao.StatisticDao;
import com.testingSystem.model.entity.Question;
import com.testingSystem.model.entity.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionStatisticService {

    /**
     * Кастомный класс для вывода данных в JSP
     */
    public static final class QuestionInfo {
        private String questionName;
        private String numberOfTimes;
        private String percent;

        QuestionInfo(String questionName, String numberOfTimes, String percent) {
            this.questionName = questionName;
            this.numberOfTimes = numberOfTimes;
            this.percent = percent;
        }

        public String getQuestionName() {
            return questionName;
        }

        public String getNumberOfTimes() {
            return numberOfTimes;
        }

        public String getPercent() {
            return percent;
        }
    }

    private QuestionDao questionDao;
    private StatisticDao statisticDao;

    @Autowired
    public QuestionStatisticService(QuestionDao questionDao, StatisticDao statisticDao) {
        this.questionDao = questionDao;
        this.statisticDao = statisticDao;
    }

    /**
     * Получает объкт типа Question. На основании его делает запрос в БД и формирует объект для JSP.
     */
    private QuestionInfo createQInfobj(Question question){
        QuestionInfo questionInfo = null;

        List<Statistic> statisticList = statisticDao.getAllStatisticByQuestionId(question.getQuestionId());
        if(statisticList != null){
            // сумарное количество ответов на вопрос
            int numberOfTimes = statisticList.size();
            // процент правильных ответов на вопрос
            double correcet = 0;

            for (Statistic statistic : statisticList){
                if (statistic.isCorrect()){
                    correcet ++;
                }
            }
            questionInfo = new QuestionInfo(
                    question.getDescription(),
                    String.valueOf(numberOfTimes),
                    correcet / numberOfTimes * 100 + "%");
        }

        return questionInfo;
    }

    /**
     * Возвращает лист объектов для JSP
     */
    public List<QuestionInfo> getQuestionInfoList(){

        List<QuestionInfo> questionInfoList = new ArrayList<>();

        QuestionInfo questionInfo;

        List<Question> questionList = questionDao.getAllQuestions();
        for (Question question : questionList) {
            questionInfo = createQInfobj(question);
            //Если есть хоть какая-то статистика для вопроса
            if(questionInfo != null) {
                questionInfoList.add(questionInfo);
            }
            // Иначе вернуть описание вопроса и, что пройден он 0 раз.
            else {
                questionInfoList.add(
                        new QuestionInfo(question.getDescription(),
                                "Ответов на вопрос ещё не было",
                                "0%")
                );
            }
        }

        //questionNumberOfTimesPercentList = questionNumberOfTimesPercentList.stream()
          //      .collect(Collectors.groupingBy(QuestionInfo::getQuestionName)).values();
        // прежде чем вернуть этот лист, небходимо сгруппировать вопросы с одинаковым описанием в один.
        // т.к один и тот же вопрос может относиться к разным тестам из одного топика.
        return questionInfoList;
    }
}