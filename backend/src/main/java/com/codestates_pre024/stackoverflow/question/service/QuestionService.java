package com.codestates_pre024.stackoverflow.question.service;

import com.codestates_pre024.stackoverflow.answer.service.AnswerService;
import com.codestates_pre024.stackoverflow.exception.BusinessLogicException;
import com.codestates_pre024.stackoverflow.exception.ExceptionCode;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import com.codestates_pre024.stackoverflow.member.service.MemberService;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import com.codestates_pre024.stackoverflow.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final MemberService memberService;
    private final AnswerService answerService;

    public Question createQuestion(Question question, Long memberId) {
        Member member = verifyMember(memberId);

        question.setMember(member);
        question.setCreatedAt(LocalDateTime.now());

        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question, Long memberId) {
        Question findQuestion = findVerifiedQuestion(question.getId());
        memberService.compareIdAndLoginId(memberId);

        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));
        Optional.ofNullable(question.getContents())
                .ifPresent(contents -> findQuestion.setContents(contents));

        findQuestion.setModifiedAt(LocalDateTime.now());

        return questionRepository.save(findQuestion);
    }

    public Question findQuestion(Long questionId) {
        Question question = findVerifiedQuestion(questionId);
        question.setAnswers(answerService.findAnswers(questionId));

        return question;
    }

    public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by("id").descending()));
    }

    public void deleteQuestion(Long questionId) {

        Question findQuestion = findVerifiedQuestion(questionId);

        questionRepository.delete(findQuestion);
    }

//     ????????? ??????????????? ??????
    private Member verifyMember(Long memberId) {
        Member member = memberService.checkMemberExistById(memberId);

        return member;
    }

//     ????????? question?????? ??????
    private Question findVerifiedQuestion(Long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }
    // memberId??? ???????????? ????????? ????????????
    public List<Question> findQuestionsOfMember (Long memberId) {
        return questionRepository.findByMemberId(memberId);
    }
}
