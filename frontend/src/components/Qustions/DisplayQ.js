import React from "react";
import { useDispatch } from "react-redux";
import { saveTitle, saveContents } from "../../store/questionSlice";
import { doEdit } from "../../store/editSlice";
import styled from "styled-components";

import { Navigate, useNavigate } from "react-router-dom";
const TitleContainer = styled.div`
  display: flex;
  justify-content: space-between;
  position: relative;
  /* left: 80px; */
  /* width: 80%; */
  min-height: 70px auto;
  border-bottom: 1px solid var(--graylight);
  margin-top: 70px;
  margin-left: 10px;
  .titleCreate {
    flex-direction: column;
  }
  .createAt {
    margin-bottom: 10px;
    color: var(--graydark);
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
  background-color: var(--blue);
  border-color: var(--bluelight);
  color: var(--white);
  border-radius: var(--bd-rd);
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
  background-color: var(--white);
  font-size: 15px;
  margin-right: 30px;
  :hover {
    cursor: pointer;
  }
`;
const Delete = styled.button`
  /* position: relative;
  left: 60px; */
  height: 40px;
  border: none;
  background-color: var(--white);
  font-size: 15px;
  color: var(--red);
  :hover {
    cursor: pointer;
  }
`;
const Profile = styled.div`
  position: absolute;
  right: 0;
  margin-left: 500px;
  width: 187px;
  height: 60px;
  padding: 5px 6px 7px 7px;
  background-color: var(--graywhite);
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  margin-right: 50px;
  border-radius: var(--bd-rd);
  img {
    width: 50px;
    height: 50px;
  }
  b {
    margin-left: 5px;
    color: var(--bluedark);
  }
`;

function DisplayQ({ list }) {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleEdit = ({ id, title, contents }) => {
    // dispatch(saveTitle(title));
    const newContents = {
      title,
      contents,
    };
    dispatch(saveContents(newContents));
    dispatch(doEdit(true));
    navigate(`/question`);
  };
  const moveQuestion = () => {
    navigate("/question");
  };

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
                    <span>
                      Asked: {new Date(el.createdAt).toLocaleString()}
                    </span>
                    <span>
                      Modified: {new Date(el.modifiedAt).toLocaleString()}{" "}
                    </span>
                  </div>
                </div>
                <AskBtn onClick={moveQuestion}>Ask Quetion</AskBtn>
              </TitleContainer>
              <QuestionContainer>
                <p>{el.contents}</p>
                <ModifyWrap>
                  <Edit onClick={() => handleEdit(el)}>Edit</Edit>
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
