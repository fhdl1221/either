package com.example.either.repository;

import com.example.either.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query("SELECT q FROM Question q LEFT JOIN q.answers a GROUP BY q ORDER BY COUNT(a) DESC")
    List<Question> findAllOrderByAnswerCountDesc();
}
