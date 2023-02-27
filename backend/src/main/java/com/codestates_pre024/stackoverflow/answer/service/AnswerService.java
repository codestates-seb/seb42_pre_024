package com.codestates_pre024.stackoverflow.answer.service;

import com.codestates_pre024.stackoverflow.answer.entity.Answer;
import com.codestates_pre024.stackoverflow.answer.repository.AnswerRepository;
import com.codestates_pre024.stackoverflow.exception.BusinessLogicException;
import com.codestates_pre024.stackoverflow.exception.ExceptionCode;
import com.codestates_pre024.stackoverflow.member.service.MemberService;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import com.codestates_pre024.stackoverflow.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final MemberService memberService;
    private final QuestionRepository questionRepository;

    //answer 둥록
    public Answer createAnswer(Answer answer, Long memberId, Long questionId) {

        answer.addMember(memberService.getMember(memberId));

        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        answer.addQuestion(findQuestion);
        answer.setCreatedAt(LocalDateTime.now());

        return answerRepository.save(answer);
    }

    //answer 수정
    public Answer updateAnswer(Answer answer, Long memberId, Long id) {
        memberService.compareIdAndLoginId(memberId);

        Answer findAnswer = findVerifiedAnswer(id);

        Optional.ofNullable(answer.getContents())
                .ifPresent(findAnswer::setContents);
        findAnswer.setModifiedAt(LocalDateTime.now());

        return answerRepository.save(findAnswer);
    }

    //해당 질문에 있는 answer 다 가져오기
    public List<Answer> findAnswers(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    //answer 삭제
    public void deleteAnswer(Long id, Long memberId) {
        memberService.compareIdAndLoginId(memberId);

        Answer findAnswer = findVerifiedAnswer(id);

        answerRepository.delete(findAnswer);
    }

    //특정 answer 있는지 확인
    private Answer findVerifiedAnswer(Long id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);

        return optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }
}