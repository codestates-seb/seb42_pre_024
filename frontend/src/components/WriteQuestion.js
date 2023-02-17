import React from "react";
import styled from "styled-components";

// const Wrap = styled.div`
//   display: flex;
//   align-items: center;
//   justify-content: center;
// `;
const QuestionContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 700px;
  height: 500px;
  margin-top: 150px;
  border: solid 1px;
  transform: translateX(90%);
  border-radius: 2%;
`;
const Title = styled.input`
  margin-top: 50px;
  width: 600px;
  height: 30px;
  border: solid 1px;
`;
const Body = styled.textarea`
  margin-top: 40px;
  width: 600px;
  height: 250px;
`;

const Submitbtn = styled.button`
  width: 100px;
  height: 40px;
  margin-top: 30px;
  border: solid;
  background-color: #4393f7;
  border-color: #8ebefa;
  color: #ffff;
  cursor: pointer;
`;

function WriteQuestion() {
  return (
    <QuestionContainer>
      <Title placeholder="Title..." />
      <Body placeholder="What are the details of your problem? " />
      <Submitbtn>Submit</Submitbtn>
    </QuestionContainer>
  );
}

export default WriteQuestion;
