package com.academic.adviser.model;

import javax.persistence.*;

@Entity
public class QuestionPair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "question_a_id", referencedColumnName = "id")
    private POQuestion questionA;

    @OneToOne
    @JoinColumn(name = "question_b_id", referencedColumnName = "id")
    private POQuestion questionB;

    public QuestionPair() {}

    public QuestionPair(Integer id, POQuestion questionA, POQuestion questionB) {
        this.id = id;
        this.questionA = questionA;
        this.questionB = questionB;
    }

    public Integer getId() {
        return id;
    }

    public POQuestion getQuestionA() {
        return questionA;
    }

    public void setQuestionA(POQuestion questionA) {
        this.questionA = questionA;
    }

    public POQuestion getQuestionB() {
        return questionB;
    }

    public void setQuestionB(POQuestion questionB) {
        this.questionB = questionB;
    }
}
