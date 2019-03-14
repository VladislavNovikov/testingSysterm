package com.testingSystem.controller;

import com.testingSystem.model.daoimpl.AnswerImpl;
import com.testingSystem.model.daoimpl.QuestionImpl;
import com.testingSystem.model.entity.Answer;
import com.testingSystem.model.entity.Question;
import com.testingSystem.model.services.QuestionEditingService;
import com.testingSystem.model.services.QuestionStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@Controller
public class QuestionController {

    private QuestionStatisticService questionStatisticService;
    private QuestionImpl questionImpl;
    private AnswerImpl answerImpl;
    private QuestionEditingService questionEditingService;

    @Autowired
    public QuestionController(QuestionStatisticService questionStatisticService, QuestionImpl questionImpl, AnswerImpl answerImpl, QuestionEditingService questionEditingService) {
        this.questionStatisticService = questionStatisticService;
        this.questionImpl = questionImpl;
        this.answerImpl = answerImpl;
        this.questionEditingService = questionEditingService;
    }

    @GetMapping("/QuestionsInfo")
    public String showQuestionStatistic(Model model) {
        model.addAttribute("list",questionStatisticService.getQuestionInfoList());
        return "QuestionsInfo";
    }

    @GetMapping("/UpdateQuestion")
    public String updateQuestion(Model model){
        model.addAttribute("questions",questionImpl.getAllQuestions());
        return "UpdateQuestion";
    }

    @GetMapping("/CreateQuestion")
    public String createQuestion(Model model){
        model.addAttribute("questions",questionImpl.getAllQuestions());
        return "CreateQuestion";
    }

    @PostMapping("/CreateQuestion")
    public String createQuestion(Model model, @RequestParam(name = "btn") String button,
                                              @RequestParam(name = "question", required = false) String question,
                                              @RequestParam(name = "answer[]", required = false) String[] answers,
                                              @RequestParam(name="checkbox_option", required = false) String[] checkbox_option) {




        model.addAttribute("questions",questionImpl.getAllQuestions());

        questionEditingService.addQuestionByButton(button, question, answers, checkbox_option);
        return "CreateQuestion";
    }




}
