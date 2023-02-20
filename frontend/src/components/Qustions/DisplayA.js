import React from "react";
import styled from "styled-components";
import profile from "../../image/profile.png";

const AnsTitle = styled.h2`
  width: 100%;
  margin-left: 100px;
`;
const AnsContainer = styled.div`
  position: relative;
  /* border-top: solid; */
  /* left: 140px; */
  /* border-color: rgba(0, 0, 0, 0.3); */
  width: 100%;
  align-items: center;
  justify-content: center;
  /* margin-left: 100px; */
`;
const Answer = styled.div`
  /* left: 230px; */
  border: solid rgba(0, 0, 0, 0.3);
  border-radius: 5px;
  width: 100%;
  max-height: 300px auto;
  margin-top: 20px;
  margin-bottom: 30px;
  margin-left: 10px;
  p {
    padding: 50px;
    font-size: 17px;
    line-height: 2rem;
  }
`;
const ModifyWrap = styled.div`
  display: flex;
  position: relative;
  align-items: center;
  padding-bottom: 40px;
`;

const Edit = styled.button`
  position: relative;
  left: 50px;
  border: none;
  height: 40px;
  border: none;
  background-color: #ffff;
  font-size: 15px;
`;
const Delete = styled.button`
  position: relative;
  left: 60px;
  height: 40px;
  border: none;
  background-color: #ffff;
  font-size: 15px;
  color: red;
`;
const Profile = styled.div`
  /* position: relative;
  left: 450px; */
  position: absolute;
  right: 0;
  margin-left: 500px;
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
    margin-left: 5px;
    color: #2e75c6;
  }
`;
function DisplayA(props) {
  return (
    <>
      <AnsTitle>Answer</AnsTitle>
      <AnsContainer>
        <Answer>
          <p>
            position: relative 요소를 일반적인 문서 흐름에 따라 배치하고, 자기
            자신을 기준으로 top, right, bottom, left의 값에 따라 오프셋을
            적용합니다. 절차 : box modal -> nomal flow -> positioning position:
            relative로 적용된 element는 static과 마찬가지로 박스 사이즈를 계산
            후 position: relative 요소를 일반적인 문서 흐름에 따라 배치하고,
            자기 자신을 기준으로 top, right, bottom, left의 값에 따라 오프셋을
            적용합니다. 절차 : box modal -> nomal flow -> positioning position:
            relative로 적용된 element는 static과 마찬가지로 박스 사이즈를 계산
            후 position: relative 요소를 일반적인 문서 흐름에 따라 배치하고,
            relative로 적용된 element는 static과 마찬가지로 박스 사이즈를 계산
            후 position: relative 요소를 일반적인 문서 흐름에 따라 배치하고,
            자기 자신을 기준으로 top, right, bottom, left의 값에 따라 오프셋을
            적용합니다. 절차 : box modal -> nomal flow -> positioning position:
            relative로 적용된 element는 static과 마찬가지로 박스 사이즈를 계산
            후 position: relative 요소를 일반적인 문서 흐름에 따라 배치하고,
            relative로 적용된 element는 static과 마찬가지로 박스 사이즈를 계산
            후 position: relative 요소를 일반적인 문서 흐름에 따라 배치하고,
            자기 자신을 기준으로 top, right, bottom, left의 값에 따라 오프셋을
            적용합니다. 절차 : box modal -> nomal flow -> positioning position:
            relative로 적용된 element는 static과 마찬가지로 박스 사이즈를 계산
            후 position: relative 요소를 일반적인 문서 흐름에 따라 배치하고,
          </p>
          <ModifyWrap>
            <Edit>Edit</Edit>
            <Delete>Delete</Delete>
            <Profile>
              <img alt="logo" src={profile}></img>
              <b>name</b>
            </Profile>
          </ModifyWrap>
        </Answer>
      </AnsContainer>
    </>
  );
}

export default DisplayA;
