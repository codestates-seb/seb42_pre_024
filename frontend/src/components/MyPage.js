import styled from "styled-components";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

const Wrap = styled.main`
  width: 72%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  position: relative;
  left: 280px;
  margin-top: 70px;
  padding: 30px;
  > button {
    margin-top: 100px;
  }
`;

const ProfileContainer = styled.form`
  width: 1014.48px;
  height: 150px;
  display: flex;
  flex-direction: row;
  text-align: center;
  .imgContainer {
    width: 130px;
    height: 130px;
    > img {
      width: 130px;
      height: 130px;
      border-radius: 4px;
    }
  }
  .userInfoContainer {
    width: 100%;
    display: flex;
    flex-direction: column;
    text-align: left;
    margin-left: 20px;
  }
  .buttonContainer {
    width: 394.48px;
    display: flex;
    justify-content: right;
  }
`;

const MyQuestionsContainer = styled.div`
  > div {
    border: 1px solid #d6d9db;
    border-radius: 4px;
    height: 300px;
    padding: 30px;
    > ul {
      list-style: none;
      padding-left: 0px;
    }
  }
`;

const MyPageButton = styled.button`
  width: 103px;
  height: 35px;
  background-color: #ffffff;
  border: 1px solid #9ea6ac;
  border-radius: 4px;
  color: #51595f;
  :hover {
    cursor: pointer;
    background-color: #f8f9f9;
  }
`;

function MyPage() {
  const [myProfile, setMyProfile] = useState();

  const readMyProfile = async () => {
    const { data } = await axios.get("http://localhost:4000/members");
    setMyProfile(data);
  };

  useEffect(() => {
    (async () => {
      await readMyProfile();
    })();
  }, []);

  return (
    <Wrap>
      {myProfile && (
        <>
          <ProfileContainer>
            <div className="imgContainer">
              <img alt="profile_image" src={myProfile.profileImage}></img>
            </div>
            <div className="userInfoContainer">
              <h1>{myProfile.name}</h1>
              <div>{myProfile.aboutMe}</div>
            </div>
            <div className="buttonContainer">
              <MyPageButton>Edit my profile</MyPageButton>
            </div>
          </ProfileContainer>
          <MyQuestionsContainer>
            <h1>My Questions</h1>
            <div>
              {myProfile.questions.length === 0 && (
                <p>작성한 질문이 없습니다.</p>
              )}
              {myProfile.questions &&
                myProfile.questions.map((question) => {
                  return (
                    <ul>
                      <li key={question.id}>
                        <Link
                          to={`http://localhost:3000/questions/${question.id}`}
                        >
                          {question.title}
                        </Link>
                      </li>
                    </ul>
                  );
                })}
            </div>
          </MyQuestionsContainer>
          <MyPageButton>탈퇴하기</MyPageButton>
        </>
      )}
    </Wrap>
  );
}

export default MyPage;
