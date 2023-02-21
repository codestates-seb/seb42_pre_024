package com.codestates_pre024.stackoverflow.member.entity;

import com.codestates_pre024.stackoverflow.global.utils.CustomDateTimeFormatter;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 320, nullable = false)
    private String email;

    @Setter
    @Column(length = 16, nullable = false)
    private String name;

    @Column(length = 64, nullable = false)
    private String password;

    @Setter
    @Column(length = 255, nullable = true)
    private String aboutMe;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime lastLoginAt;


    //@Setter private String profileImage;

    /*
    @OneToMany(mappedBy = "question")
    private List<Question> questions;
*/

    private void setAboutMeDefault() {
        this.aboutMe = "안녕하세요";
    }

    private void setCreatedAt() {
        this.createdAt = CustomDateTimeFormatter.formatDateTime(LocalDateTime.now());
    }




    @Builder(builderClassName = "CreateNewMember", builderMethodName = "CreateNewMember")
    public Member (String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        setAboutMeDefault();
        setCreatedAt();
    }

    @Builder(builderClassName = "UpdateMember", builderMethodName = "UpdateMember")
    public Member (Long id, String name, String aboutMe) {
        this.id = id;
        this.name = name;
        // String profileImage;
        this.aboutMe = aboutMe;
    }


}
