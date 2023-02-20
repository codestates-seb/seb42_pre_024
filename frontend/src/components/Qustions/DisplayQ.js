import React from "react";
import styled from "styled-components";
//3
const Title = styled.h2`
  width: 100%;
  margin-left: 10px;
`;
const Container = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
  width: 100%;
  height: 300px;
  /* margin-left: 100px; */
  border: solid;
  border-color: rgba(0, 0, 0, 0.3);
  border-radius: 5px;
  margin-left: 10px;
  padding-bottom: 20px;
`;

const Ans = styled.textarea`
  position: relative;
  max-width: 70%;
  width: 800px;
  height: 300px;
  margin-bottom: 10px;
  margin-top: 50px;
`;

const SubBtn = styled.button`
  width: 100px;
  height: 40px;
  margin-top: 30px;
  border: solid;
  background-color: #4393f7;
  border-color: #8ebefa;
  border-radius: 5px;
  color: #ffff;
  cursor: pointer;
`;

function WriteAns(props) {
  return (
    <>
      <Title>Your Answer</Title>
      <Container>
        <Ans />

        <SubBtn>Post Your Answer</SubBtn>
      </Container>
    </>
  );
}

export default WriteAns;
