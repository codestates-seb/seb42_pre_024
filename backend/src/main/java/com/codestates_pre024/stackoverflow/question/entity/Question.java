package com.codestates_pre024.stackoverflow.question.entity;

import com.codestates_pre024.stackoverflow.answer.entity.Answer;
import com.codestates_pre024.stackoverflow.global.utils.CustomDateTimeFormatter;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import lombok.Builder;
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

    @Column
    private LocalDateTime createdAt;

    @Column(name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt;

//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID")
//    private Member member;

//    @OneToMany(mappedBy = "Question")
//    private List<Answer> answers;
}
