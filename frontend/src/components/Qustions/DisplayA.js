import React, { useState } from "react";
import styled from "styled-components";

const AnsTitle = styled.h2`
  width: 100%;
  margin-left: 10px;
`;
const AnsContainer = styled.div`
  position: relative;
  width: 100%;
  align-items: center;
  justify-content: center;
`;
const Answer = styled.div`
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

const Date = styled.div`
  display: flex;
  margin-bottom: 30px;
  margin-right: 50px;
  flex-direction: row-reverse;
  .createAt {
    display: flex;
    flex-direction: column;
    text-align: right;
    color: rgba(0, 0, 0, 0.5);
    font-size: small;
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
function DisplayA({ list }) {
  return (
    <>
      <AnsTitle>Answer</AnsTitle>
      <AnsContainer>
        {list &&
          list.map(
            (el) =>
              el.answers &&
              el.answers.map((el) =>
                !el ? (
                  ""
                ) : (
                  <>
                    <Answer>
                      <p>{el.contents}</p>
                      <Date>
                        <div className="createAt">
                          <div>
                            Asked:{" "}
                            {new window.Date(el.createdAt).toLocaleString()}
                          </div>
                          <div>
                            Modified:{" "}
                            {new window.Date(el.modifiedAt).toLocaleString()}
                          </div>
                        </div>
                      </Date>
                      <ModifyWrap>
                        <Edit>Edit</Edit>
                        <Delete>Delete</Delete>
                        <Profile>
                          <img alt="logo" src={el.member.profileImage}></img>
                          <b>{el.member.name}</b>
                        </Profile>
                      </ModifyWrap>
                    </Answer>
                  </>
                )
              )
          )}
      </AnsContainer>
    </>
  );
}

export default DisplayA;
