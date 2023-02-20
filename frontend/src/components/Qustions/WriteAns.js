import React from "react";
import styled from "styled-components";

const Title = styled.h2`
  width: 100%;
  margin-left: 200px;
`;
const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  left: 170px;
  position: relative;
  width: 80%;
  height: 300px;
  /* margin-left: 100px; */
  border: solid;
  border-color: rgba(0, 0, 0, 0.3);
  border-radius: 5px;

  /*overflow: hidden; */
`;

const Ans = styled.textarea`
  position: relative;
  max-width: 70%;
  width: 800px;
  height: 300px;
  /* left: 250px; */
  margin-bottom: 10px;
  /* margin-left: 250px; */
  margin-top: 50px;
`;

const SubBtn = styled.button`
  width: 100px;
  height: 40px;
  margin-top: 30px;
  /* margin-left: 600px; */
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
