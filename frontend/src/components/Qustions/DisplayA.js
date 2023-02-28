import styled from "styled-components";
import axios from "axios";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import WriteAns from "./WriteAns";

const Wrap = styled.div``;

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
  border: solid var(--graymiddle);
  border-radius: var(--bd-rd);
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
    color: var(--graydark);
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
  background-color: var(--white);
  font-size: 15px;
  :hover {
    cursor: pointer;
  }
`;

const Delete = styled.button`
  position: relative;
  left: 60px;
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
  a {
    margin-left: 5px;
    color: var(--bluedark);
    font-family: var(--main-font-bold);
    text-decoration: none;
    :hover {
      color: var(--blue);
    }
  }
`;

function DisplayA({ list, readData, qId }) {
  const [editYes, setEditYes] = useState(-1); //answer의 id
  const [edit, setEdit] = useState("");

  //리덕스 불러와서 아이디 일치 여부

  // useEffect(()=>{
  //   setEditYes(false)
  // ,[handleEdit])

  const handleEdit = async ({ id, contents }) => {
    setEditYes(id);

    // await axios.patch(`http://localhost:4000/data/${id}`, {
    //   contents: contents,
    // });
    setEdit(contents);
  };
  // const handleDelete = async ({ id }) => {
  //   //메인페이지(id) => questionlist로 delete
  //   console.log(id);
  //   await axios.delete(`http://localhost:4000/data/${qId}/answers/${id}`);
  //   readData();
  //};

  return (
    <>
      <AnsTitle>Answer</AnsTitle>
      <AnsContainer>
        {list &&
          list.map(
            (el) =>
              el.answers.length !== 0 &&
              el.answers.map((el) => {
                return (
                  <Wrap key={`${el.id}`}>
                    {editYes === el.id ? (
                      <WriteAns edit={edit} editYes={editYes} />
                    ) : (
                      <Answer>
                        <p>{el.contents}</p>
                        <Date>
                          <div className="createAt">
                            <div>
                              Asked:
                              {new window.Date(el.createdAt).toLocaleString()}
                            </div>
                            {el.modifiedAt && (
                              <div>
                                Modified:
                                {new window.Date(
                                  el.modifiedAt
                                ).toLocaleString()}
                              </div>
                            )}
                          </div>
                        </Date>
                        <ModifyWrap>
                          <Edit onClick={() => handleEdit(el)}>Edit</Edit>
                          <Delete>Delete</Delete>
                          <Profile>
                            <img alt="logo" src={el.member.profileImage}></img>
                            <Link to={`/members/${el.member.id}`}>
                              {el.member.name}
                            </Link>
                          </Profile>
                        </ModifyWrap>
                      </Answer>
                    )}
                  </Wrap>
                );
              })
          )}
      </AnsContainer>
    </>
  );
}

export default DisplayA;
