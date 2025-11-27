package com.example.either.service;

import com.example.either.entity.Answer;
import com.example.either.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {
    private final AnswerRepository answerRepository;

}
