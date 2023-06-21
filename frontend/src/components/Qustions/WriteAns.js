import axios from "axios";
import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";

axios.defaults.withCredentials = true;

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

function WriteAns({ edit, id, editYes, setUpdate, setEditYes, setEditUpdate }) {
  const [input, setInput] = useState("");
  const navigate = useNavigate();

  const userId = localStorage.getItem("Id");
  const accessToken = localStorage.getItem("Token");

  useEffect(() => {
    if (!edit) {
      setInput("");
    } else setInput(edit);
  }, [edit]);

  const handleCancel = () => {
    setEditYes(-1);
  };

  const postAns = async () => {
    if (!accessToken) {
      alert("로그인 후 이용해 주세요");
      setInput("");
      navigate("../login");
    } else if (!input.length) {
      alert("답변을 입력해 주세요!");
    } else {
      try {
        const token = `Bearer ${accessToken}`.toString("base64");
        axios.post(
          `http://ec2-54-180-114-122.ap-northeast-2.compute.amazonaws.com:8080/questions/${id}/answers`,
          {
            memberId: userId,
            contents: input,
          },
          { headers: { Authorization: `${token}` } }
        );
        console.log("성공");
      } catch (error) {
        console.log(error);
      }
      setInput("");
      setUpdate("yes");
    }
  };

  const editAns = async () => {
    if (!input.length) {
      alert("답변을 입력해 주세요!");
    } else {
      try {
        const token = `Bearer ${accessToken}`.toString("base64");
        axios.patch(
          `http://ec2-54-180-114-122.ap-northeast-2.compute.amazonaws.com:8080/answers/${editYes}`,
          {
            memberId: userId,
            contents: input,
          },
          { headers: { Authorization: `${token}` } }
        );
        setEditYes(-1);
      } catch (error) {
        console.log(error);
      }
      setEditUpdate("yes");
    }
  };

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
