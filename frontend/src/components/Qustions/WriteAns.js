import axios from "axios";
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

function WriteAns({ edit, id, editYes }) {
  const [input, setInput] = useState("");
  //editYes는 멤버 아이디 전달
  //id 는 params id
  //답변 수정시 /{question-id}/answers/{answer-id}
  //답변 수정시 patch내용에 memberid가 필요함
  //답변 등록시 /questions/id/answers

  console.log(editYes);
  useEffect(() => {
    if (!edit) {
      setInput("");
    } else setInput(edit);
  }, [edit]);

  // const readData = async () => {
  //    await axios.get(
  //     `http://ec2-3-36-122-3.ap-northeast-2.compute.amazonaws.com:8080/questions/${id}`
  //   );
  // };

  // useEffect(() => {
  //   (async () => {
  //     await readData();
  //   })();
  // }, []);

  const handleCancel = () => {
    window.location.replace("./questionlist");
  };
  const postAns = async () => {
    // axios.post(
    //   `http://ec2-3-36-122-3.ap-northeast-2.compute.amazonaws.com:8080/questions/${id}/answers`,
    //   {
    //     memberId: 1,
    //     contents: input,
    //   }
    // );
    // readData();
    //포스트를 하고 다시 데이터를 get해야  바뀌는데 포스트 하는 컴포넌트와 get하는 컴포넌트가 다름
    //방안 1: 아예 새로고침 => params의 id값을 잃어버림 ()
    //props로 list값 내려주고 여기서 get하기 => 코드 개지저분 ㅋㅋㅋㅋ...
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
            <SubBtn>Add</SubBtn>
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
