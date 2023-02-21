package com.codestates_pre024.stackoverflow.question.entity;

import com.codestates_pre024.stackoverflow.answer.entity.Answer;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

//    @OneToMany(mappedBy = "Question")
//    private List<Answer> answers;
//
//    public void setMember(Member member) {
//        this.member = member;
//    }
//
//    public void setAnswers(List<Answer> answers) {
//        this.answers = answers;
//    }
}
