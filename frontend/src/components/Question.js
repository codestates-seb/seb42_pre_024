import React from "react";
import styled from "styled-components";

import DisplayQ from "./Qustions/DisplayQ";
import DisplayA from "./Qustions/DisplayA";
import WriteAns from "./Qustions/WriteAns";

const Container = styled.div`
  position: relative;
  left: 280px;
  display: flex;
  flex-direction: column;
  width: 68%;
  margin-top: 70px;
  margin: 0;
  padding: 30px;
`;

function Question(props) {
  //props {match} ,
  //get요청시 match.params.id , setQustion
  //question(s)
  //로그인x=>답변불가 질문불가,본인질문x=>답변ㅇ 질문가능,본인질문=>질문수정 질문가능,본인이 답변=>질문수정x 답변가능
  //로컬스토리지 이용해서 수정,삭제버튼 나오게하고 수정하거나 삭제 action시 서버에 반영??
  return (
    <Container>
      <DisplayQ />
      <DisplayA />
      <WriteAns />
    </Container>
  );
}

export default Question;
