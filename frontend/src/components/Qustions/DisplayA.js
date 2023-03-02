import styled from "styled-components";
import axios from "axios";
import { useState } from "react";
import { Link } from "react-router-dom";
import WriteAns from "./WriteAns";
import profile from "../../image/profileImg.png";

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
  .editNo {
    background-color: aqua;
    height: 40px;
  }
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

function DisplayA({ list, setEditUpdate, accessToken, userId }) {
  const [editYes, setEditYes] = useState(-1);
  const [edit, setEdit] = useState("");

  const handleEdit = async ({ id, contents }) => {
    setEditYes(id);
    setEdit(contents);
  };
  const handleDelete = async ({ id }) => {
    const token = `Bearer ${accessToken}`.toString("base64");
    await axios.delete(`/answers/${id}`, {
      headers: { Authorization: `${token}` },
    });
    setEditUpdate(-2);
  };

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
                      <WriteAns
                        edit={edit}
                        editYes={editYes}
                        setEditYes={setEditYes}
                        setEditUpdate={setEditUpdate}
                      />
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
                          {userId == el.member.id ? (
                            <>
                              <Edit onClick={() => handleEdit(el)}>Edit</Edit>
                              <Delete onClick={() => handleDelete(el)}>
                                Delete
                              </Delete>
                            </>
                          ) : (
                            <div className="editNo"></div>
                          )}

                          <Profile>
                            <img alt="logo" src={profile}></img>
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
