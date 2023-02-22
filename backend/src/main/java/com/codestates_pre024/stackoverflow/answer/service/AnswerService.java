package com.codestates_pre024.stackoverflow.answer.service;

import com.codestates_pre024.stackoverflow.answer.entity.Answer;
import com.codestates_pre024.stackoverflow.answer.repository.AnswerRepository;
import com.codestates_pre024.stackoverflow.exception.BusinessLogicException;
import com.codestates_pre024.stackoverflow.exception.ExceptionCode;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import com.codestates_pre024.stackoverflow.member.repository.MemberRepository;
import com.codestates_pre024.stackoverflow.member.service.MemberService;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final MemberService memberService;

    //answer 둥록
    public Answer createAnswer(Answer answer, Long memberId) {
//        Member member = new Member();
//        answer.setMember(memberService.getMember(member.getId()));

//        Question question = new Question();
//        answer.setQuestion(questionService.findQuestion(question.getQuestionId()));

        answer.addMember(memberService.getMember(memberId));

        //로그인 된 회원인지 확인
        return answerRepository.save(answer);
    }

    //answer 수정
    public Answer updateAnswer(Answer answer, Long memberId) {
        //로그인된 회원이 작성자와 같은 회원이지 확인 (if문) 다르면 exception code 날림
        memberService.checkMemberExistById(memberId);
        Member findMember = memberService.checkMemberExistById(memberId);

        Answer findAnswer = findVerifiedAnswer(answer.getId());

        Optional.ofNullable(answer.getContents())
                .ifPresent(findAnswer::setContents);

        return findAnswer;
    }

    //answer 가져오기
    public List<Answer> findAnswers(Question question) {
        return answerRepository.findByQuestion(question);
    }

    //answer 삭제
    public void deleteAnswer(Long id) {
        //로그인된 회원이 작성자와 같은 회원이지 확인 (if문) 다르면 exception code 날림
        Answer findAnswer = findVerifiedAnswer(id);

        answerRepository.delete(findAnswer);
    }

    //특정 answer 있는지 확인
    private Answer findVerifiedAnswer(Long id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);

        return optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }

    //특정 member 있는지 확인
//    private Answer findVerifiedMember(Long id) {
//        Optional<Member> optionalMember = memberRepository.findById(id);
//
//        return optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
////        return optionalMember.orElseThrow(() -> new RuntimeException());
//    }

}