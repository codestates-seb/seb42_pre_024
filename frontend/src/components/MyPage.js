import styled from "styled-components";
import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
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
      border-radius: var(--bd-rd);
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
  margin-bottom: 160px;
  > div {
    border: 1px solid var(--graylight);
    border-radius: var(--bd-rd);
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
  background-color: var(--white);
  border: 1px solid var(--graymiddle);
  border-radius: var(--bd-rd);
  color: var(--graydarker);
  :hover {
    cursor: pointer;
    background-color: var(--white);
  }
`;

function MyPage() {
  const [myProfile, setMyProfile] = useState();
  const navigate = useNavigate();

  const readMyProfile = async () => {
    const { data } = await axios.get("http://localhost:4000/members");
    setMyProfile(data);
  };
  console.log(myProfile);
  const deleteAccount = async () => {
    await axios.delete(`http://localhost:4000/members/${myProfile[0].id}`);
  };

  const resignHandler = (e) => {
    e.preventDefault();
    let isSure = window.confirm("정말로 탈퇴하시겠습니까?");
    if (isSure) {
      deleteAccount();
      navigate("/");
    }
  };

  useEffect(() => {
    (async () => {
      await readMyProfile();
    })();
  }, []);

  return (
    <Wrap>
      {myProfile &&
        myProfile.map((el) => {
          return (
            <>
              <ProfileContainer>
                <div className="imgContainer">
                  <img alt="profile_image" src={myProfile.profileImage}></img>
                </div>
                <div className="userInfoContainer">
                  <h1>{el.name}</h1>
                  <div>{el.aboutMe}</div>
                </div>
                <div className="buttonContainer">
                  <MyPageButton>Edit my profile</MyPageButton>
                </div>
              </ProfileContainer>
              <MyQuestionsContainer>
                <h1>My Questions</h1>
                <div>
                  {el.questions.length === 0 && <p>작성한 질문이 없습니다.</p>}
                  {el.questions &&
                    el.questions.map((question) => {
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
              <form onSubmit={resignHandler}>
                <MyPageButton type="submit">탈퇴하기</MyPageButton>
              </form>
            </>
          );
        })}
    </Wrap>
  );
}

export default MyPage;
