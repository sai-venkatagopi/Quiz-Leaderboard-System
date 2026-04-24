package com.quiz.controller;

import com.quiz.model.LeaderboardEntry;
import com.quiz.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/run")
    public List<LeaderboardEntry> runQuiz() throws InterruptedException {
        return quizService.execute();
    }
}
