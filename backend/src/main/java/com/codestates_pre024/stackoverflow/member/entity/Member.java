package com.codestates_pre024.stackoverflow.member.entity;

import com.codestates_pre024.stackoverflow.answer.entity.Answer;
import com.codestates_pre024.stackoverflow.global.utils.CustomDateTimeFormatter;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(length = 320, nullable = false)
    private String email;

    @Setter
    @Column(length = 16, nullable = false)
    private String name;

    @Column(length = 300, nullable = false)
    private String password;

    @Setter
    @Column(length = 255, nullable = true)
    private String aboutMe;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime lastLoginAt;

    @Column(length = 1000)
    private String profileImage;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Answer> answers = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER) //권한은 즉시 필요함. (인증정보이므로)
    private List<String> roles = new ArrayList<>();

    private void setAboutMeDefault() {
        this.aboutMe = "안녕하세요";
    }

    private void setCreatedAt() {
        this.createdAt = CustomDateTimeFormatter.formatDateTime(LocalDateTime.now());
    }

    private String randomImageSelector(){
        String[] imgUri = {
                "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/f38066b8-ad73-4e02-8bb2-8d6a69110a9c/7839bcaccc3359d426e4b9bda6598fe6.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230221%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230221T030701Z&X-Amz-Expires=86400&X-Amz-Signature=6a33b1e1ab867f28d08f35cd0050503634a6b6b6ea8ef788520b97ea37f99911&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%227839bcaccc3359d426e4b9bda6598fe6.png%22&x-id=GetObject",
                "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/80a6766b-792b-44bc-a838-a572ecf169b7/download.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230221%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230221T030848Z&X-Amz-Expires=86400&X-Amz-Signature=e41d8a30ed7f1785fbbbe56aefa77a8d830b1dc01fce6f4c88be3399225e84d0&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22download.png%22&x-id=GetObject",
                "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/8338f37d-04f1-4a18-872c-cdb930cb8e6b/5193076d58279bb17568547d3757effd.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230221%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230221T031014Z&X-Amz-Expires=86400&X-Amz-Signature=ebc5fa2625ff678268a0147968c733ac313670578376a6f31d7808cae190442e&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%225193076d58279bb17568547d3757effd.png%22&x-id=GetObject",
                "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/f9d2aa78-51b8-44ea-ae39-4514df3f4d8a/df2638245d621a52a0a52b9bb9700477.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230221%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230221T031120Z&X-Amz-Expires=86400&X-Amz-Signature=8ccd44c8339460591fe90918de6e3ffd918d42d12f54730c1732d59eca214d71&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22df2638245d621a52a0a52b9bb9700477.png%22&x-id=GetObject"
        };
        Random random = new Random();
        int randomNumber = random.nextInt(4);

        return imgUri[randomNumber];

    }
    private void setDefaultProfileImage() {
        this.profileImage = randomImageSelector();
    }

    @Builder(builderClassName = "CreateNewMember", builderMethodName = "CreateNewMember")
    public Member (String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        setAboutMeDefault();
        setCreatedAt();
        setDefaultProfileImage();
    }

    @Builder(builderClassName = "UpdateMember", builderMethodName = "UpdateMember")
    public Member (Long id, String name, String aboutMe) {
        this.id = id;
        this.name = name;
        this.aboutMe = aboutMe;
    }


}
