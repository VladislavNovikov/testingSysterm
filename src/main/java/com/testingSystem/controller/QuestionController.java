package com.testingSystem.controller;

import com.testingSystem.model.services.QuestionStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/QuestionInfo")
public class QuestionController {

    private QuestionStatisticService questionStatisticService;

    @Autowired
    public QuestionController(QuestionStatisticService questionStatisticService) {
        this.questionStatisticService = questionStatisticService;
    }

    @GetMapping
    @RequestMapping("")
    public String showStartPage(ModelMap model) {
        return "QuestionInfo";
    }
}