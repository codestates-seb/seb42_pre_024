import React, { useState, useEffect } from "react";
import styled from "styled-components";

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
  border: solid;
  border-color: rgba(0, 0, 0, 0.3);
  border-radius: 5px;
  margin-left: 10px;
  padding-bottom: 20px;
`;

const Ans = styled.textarea`
  position: relative;
  max-width: 90%;
  width: 100%;
  height: 100%;
  margin-top: 20px;
  border: none;
`;

const SubBtn = styled.button`
  width: 130px;
  height: 40px;
  margin: 30px 0 0 10px;
  border: solid;
  background-color: #4393f7;
  border-color: #8ebefa;
  border-radius: 5px;
  color: #ffff;
  cursor: pointer;
`;

function WriteAns({ edit }) {
  const [input, setInput] = useState("");

  useEffect(() => {
    if (!edit) {
      setInput("");
    } else setInput(edit);
  }, [edit]);

  return (
    <>
      <Title>Your Answer</Title>
      <Container>
        {edit ? (
          <Ans value={input} onChange={(e) => setInput(e.target.value)} />
        ) : (
          <Ans value={input} onChange={(e) => setInput(e.target.value)} />
        )}
      </Container>
      <SubBtn>Post Your Answer</SubBtn>
    </>
  );
}

export default WriteAns;
