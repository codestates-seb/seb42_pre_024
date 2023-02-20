import React from "react";
import styled from "styled-components";
import profile from "../../image/profile.png";

const TitleContainer = styled.div`
  display: flex;
  justify-content: space-between;
  position: relative;
  left: 80px;
  width: 80%;
  height: 200px;
  border-bottom: solid;
  border-color: rgba(0, 0, 0, 0.3);
  margin-left: 100px;
`;

const Title = styled.div`
  display: flex;
  height: auto;
  margin-top: 100px;
  flex-wrap: wrap;
  font-size: 30px;
  flex-wrap: wrap-reverse;
`;

const AskBtn = styled.button`
  /* position: absolute;
  right: 100px;
  bottom: 10px; */
  margin-top: 140px;
  width: 100px;
  height: 40px;
  border: solid;
  background-color: #4393f7;
  border-color: #8ebefa;
  color: #ffff;
  border-radius: 5px;
  cursor: pointer;
`;

const QuestionContainer = styled.div`
  position: relative;
  left: 70px;
  /* background-color: violet; */
  width: 80%;
  max-height: 500px auto;
  /* top: 0px;
  left: 30px; */
  /* border-bottom: solid; */
  margin-bottom: 30px;
  margin-left: 100px;
  /* box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.2); */
  > p {
    padding: 50px;
    font-size: 17px;
    line-height: 2rem;
  }
`;

const ModifyWrap = styled.div`
  display: flex;
  height: auto;
  align-items: center;
  padding-bottom: 40px;
  margin-left: 60px;
  position: relative;
`;

const Edit = styled.button`
  /* position: relative;
  left: 50px; */
  border: none;
  height: 40px;
  border: none;
  background-color: #ffff;
  font-size: 15px;
  margin-right: 30px;
`;
const Delete = styled.button`
  /* position: relative;
  left: 60px; */
  height: 40px;
  border: none;
  background-color: #ffff;
  font-size: 15px;
  color: red;
`;
const Profile = styled.div`
  /* position: relative;
  left: 300px; */
  position: absolute;
  right: 0;
  width: 187px;
  height: 60px;
  padding: 5px 6px 7px 7px;
  background-color: #dce9f6;
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  margin-right: 50px;
  border-radius: 5px;
  img {
    width: 50px;
    height: 50px;
  }
  b {
    margin-left: 30px;
    color: #2e75c6;
  }
`;

function DisplayQ(props) {
  return (
    <>
      {/* {qustion && (본문 컴포넌트)} */}
      <TitleContainer>
        <Title>javascript split method comma but without double quotes</Title>
        <AskBtn>Ask Quetion</AskBtn>
      </TitleContainer>
      <QuestionContainer>
        <p>
          position: relative 요소를 일반적인 문서 흐름에 따라 배치하고, 자기
          자신을 기준으로 top, right, bottom, left의 값에 따라 오프셋을
          적용합니다. 절차 : box modal -> nomal flow -> positioning position:
          relative로 적용된 element는 static과 마찬가지로 박스 사이즈를 계산 후
          position: relative 요소를 일반적인 문서 흐름에 따라 배치하고, 자기
          자신을 기준으로 top, right, bottom, left의 값에 따라 오프셋을
          적용합니다. 절차 : box modal -> nomal flow -> positioning position:
          relative로 적용된 element는 static과 마찬가지로 박스 사이즈를 계산 후
          position: relative 요소를 일반적인 문서 흐름에 따라 배치하고, 자기
          자신을 기준으로 top, right, bottom, left의 값에 따라 오프셋을
          적용합니다. 절차 : box modal -> nomal flow -> positioning position:
          relative로 적용된 element는 static과 마찬가지로 박스 사이즈를 계산 후
        </p>
        <ModifyWrap>
          <Edit>Edit</Edit>
          <Delete>Delete</Delete>
          <Profile>
            <img alt="logo" src={profile}></img>
            <b>name</b>
          </Profile>
        </ModifyWrap>
      </QuestionContainer>
    </>
  );
}

export default DisplayQ;
