import React from "react";
import styled from "styled-components";
import profile from "../../image/profile.png";

const TitleContainer = styled.div`
  display: flex;
  justify-content: space-between;
  position: relative;
  /* left: 80px; */
  /* width: 80%; */
  min-height: 70px auto;
  border-bottom: 1px solid #d6d9dc;
  margin-top: 70px;
  margin-left: 10px;
  .titleCreate {
    flex-direction: column;
  }
  .createAt {
    margin-bottom: 10px;
    color: rgba(0, 0, 0, 0.5);
    > :nth-child(1) {
      margin-right: 15px;
    }
  }
`;

const Title = styled.h1`
  display: flex;
  height: auto;
  flex-wrap: wrap;
  font-size: 30px;
  flex-wrap: wrap-reverse;
  /* margin-bottom: 80px; */
`;

const AskBtn = styled.button`
  justify-content: right;
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
  /* left: 70px; */
  /* background-color: violet; */
  width: 100%;
  max-height: 500px auto;
  margin-bottom: 30px;
  margin-left: 10px;
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

function DisplayQ({ list }) {
  return (
    <>
      {/* {qustion && (본문 컴포넌트)} */}
      {list &&
        list.map((el) => {
          return (
            <>
              <TitleContainer>
                <div className="titleCreate">
                  <Title>{el.title}</Title>
                  <div className="createAt">
                    <sapn>
                      Asked: {new Date(el.createdAt).toLocaleString()}
                    </sapn>
                    <span>
                      Modified: {new Date(el.modifiedAt).toLocaleString()}{" "}
                    </span>
                  </div>
                </div>
                <AskBtn>Ask Quetion</AskBtn>
              </TitleContainer>
              <QuestionContainer>
                <p>{el.contents}</p>
                <ModifyWrap>
                  <Edit>Edit</Edit>
                  <Delete>Delete</Delete>
                  <Profile>
                    <img alt="logo" src={el.member.profileImage}></img>
                    <b>{el.member.name}</b>
                  </Profile>
                </ModifyWrap>
              </QuestionContainer>
            </>
          );
        })}
    </>
  );
}

export default DisplayQ;
