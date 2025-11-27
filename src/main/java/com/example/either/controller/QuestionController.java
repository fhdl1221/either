package com.example.either.controller;

import com.example.either.entity.Answer;
import com.example.either.entity.Question;
import com.example.either.service.AnswerService;
import com.example.either.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @GetMapping
    public String list(Model model) {

        List<Question> questions = questionService.getAllOrderByAnswerDesc();
        model.addAttribute("questions", questions);
        return "list";
    }

    @GetMapping("/new")
    public String createQuestion(Model model) {

        model.addAttribute("question", new Question());
        return "create";
    }

    @PostMapping
    public String createQuestion(@ModelAttribute Question question) {
        questionService.createQuestion(question);
        return "redirect:/questions";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Question question = questionService.getQuestionById(id);
        List<Answer> answers = questionService.getAnswerById(id);

        int countA = 0;
        int countB = 0;

        for(Answer answer : answers) {
            if(answer.getAnswerText().equals("A")) {
                countA++;
            } else if(answer.getAnswerText().equals("B")) {
                countB++;
            }
        }

        int total = answers.size();
        double percentA = 0;
        double percentB = 0;

        if (total > 0) {
            percentA = (double) countA / total * 100;
            percentB = (double) countB / total * 100;

            percentA = Math.round(percentA * 10) / 10.0;
            percentB = Math.round(percentB * 10) / 10.0;
        }

        model.addAttribute("question", question);
        model.addAttribute("answers", answers);

        model.addAttribute("percentA", percentA);
        model.addAttribute("percentB", percentB);
        return "detail";
    }

    @PostMapping("/{id}/answer")
    public String answer(@PathVariable Long id,
                         @RequestParam String answer,
                         @RequestParam String opinion) {
        questionService.createAnswer(id, answer, opinion);
        return "redirect:/questions/" + id;
    }
}
