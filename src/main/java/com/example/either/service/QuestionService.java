package com.example.either.service;

import com.example.either.entity.Answer;
import com.example.either.entity.Question;
import com.example.either.repository.AnswerRepository;
import com.example.either.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Transactional
    public void createQuestion(Question question) {
        questionRepository.save(question);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow();
    }

    public void createAnswer(Long questionId, String answer, String opinion) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow();

        Answer a = new Answer();
        a.setQuestion(question);
        a.setAnswerText(answer);
        a.setContent(opinion);

        answerRepository.save(a);
    }

    public List<Answer> getAnswerById(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public List<Question> getAllOrderByAnswerDesc() {
        return questionRepository.findAllOrderByAnswerCountDesc();
    }
}
