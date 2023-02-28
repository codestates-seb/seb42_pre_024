import axios from "axios";
import React, { useState, useEffect } from "react";
import styled from "styled-components";
import Loading from "../Loading";
import { readQustionData } from "../../api/readQuesstion";

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
  border-color: var(--graymiddle);
  border-radius: var(--bd-rd);
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

const Btndiv = styled.div``;
const CancelBtn = styled.button`
  width: 130px;
  height: 40px;
  margin: 30px 0 0 10px;
  border: solid;
  background-color: var(--red);
  border-radius: var(--bd-rd);
  color: var(--white);
  cursor: pointer;
`;

const SubBtn = styled.button`
  width: 130px;
  height: 40px;
  margin: 30px 0 0 10px;
  border: solid;
  background-color: var(--blue);
  border-color: var(--bluelight);
  border-radius: var(--bd-rd);
  color: var(--white);
  cursor: pointer;
`;

function WriteAns({ edit, id, editYes, setUpdate, userId }) {
  const [input, setInput] = useState("");
  const [loading, setLoading] = useState(false);
  //editYes는 멤버 아이디 전달
  //id 는 params id
  //답변 수정시 /{question-id}/answers/{answer-id}
  //답변 수정시 patch내용에 memberid가 필요함
  //답변 등록시 /questions/id/answers
  console.log(userId);
  useEffect(() => {
    if (!edit) {
      setInput("");
    } else setInput(edit);
  }, [edit]);

  const handleCancel = () => {
    window.location.reload();
  };

  const postAns = async () => {
    try {
      axios.post(`/questions/${id}/answers`, {
        memberId: userId,
        contents: input,
      });
      setLoading(false);
      console.log("성공");
    } catch (error) {
      console.log(error);
    }
    setInput("");
    setUpdate("yes");
  };

  const editAns = async () => {
    setLoading(true);
    try {
      axios.patch(`/answers/${editYes}`, {
        memberId: userId, // 멤버아이디 수정
        contents: input,
      });
      setLoading(false);
    } catch (error) {
      console.log(error);
    }
    setUpdate("yes");
  };

  return (
    <>
      {loading ? <Loading /> : null}
      <Title>Your Answer</Title>
      <Container>
        {edit ? (
          <Ans value={input} onChange={(e) => setInput(e.target.value)} />
        ) : (
          <Ans value={input} onChange={(e) => setInput(e.target.value)} />
        )}
      </Container>
      <Btndiv>
        {edit ? (
          <>
            <SubBtn onClick={editAns}>Add</SubBtn>
            <CancelBtn onClick={handleCancel}>Cancel</CancelBtn>
          </>
        ) : (
          <SubBtn onClick={postAns}>Post Your Answer</SubBtn>
        )}
      </Btndiv>
    </>
  );
}

export default WriteAns;
