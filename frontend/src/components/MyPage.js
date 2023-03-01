import styled from "styled-components";
import { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useSelector } from "react-redux";
import { deleteAccount, readMyProfile } from "../api/userAPI";

const Wrap = styled.main`
  width: 72%;
  height: 100%;
  display: flex;
  flex-direction: column;
  position: relative;
  left: 280px;
  margin-top: 70px;
  padding: 30px;
  > form {
    margin-bottom: 30px;
  }
`;

const ProfileContainer = styled.form`
  width: 1014.48px;
  height: 150px;
  display: flex;
  flex-direction: row;
  text-align: center;
  .imgContainer {
    display: flex;
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
    margin: 0 20px;
  }
  .buttonContainer {
    display: flex;
    justify-content: right;
  }
`;

const MyQuestionsContainer = styled.div`
  margin: 0 0 160px 0;
  > p {
    color: var(--graydark);
    margin-bottom: 26px;
  }
  > div {
    border: 1px solid var(--graylight);
    border-radius: var(--bd-rd);
    height: 300px;
    padding: 30px;
    > ul {
      list-style: none;
      padding-left: 0px;
      margin: 0;
      > li {
        margin-bottom: 10px;
        > a {
          text-decoration-line: none;
          color: var(--bluedark);
          :hover {
            cursor: pointer;
            color: var(--blue);
          }
        }
      }
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
  const { id } = useParams();

  const editHandler = (e) => {
    e.preventDefault();
    alert("아직 사용할 수 없습니다.");
  };

  const resignHandler = (e) => {
    e.preventDefault();
    let isSure = window.confirm("정말로 탈퇴하시겠습니까?");
    if (isSure) {
      deleteAccount(user.userId, user.accessToken);
      navigate("/");
      window.location.reload();
    }
  };

  const userInfo = useSelector((state) => {
    return state.userId;
  });

  const user = userInfo.userAccess;

  const userProfile = async () => {
    const { data } = await readMyProfile(Number(id));
    setMyProfile(data.data);
  };

  useEffect(() => {
    (async () => {
      await userProfile();
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
              <MyPageButton onClick={editHandler}>Edit my profile</MyPageButton>
            </div>
          </ProfileContainer>
          <MyQuestionsContainer>
            <h1>My Questions</h1>
            <p>10 questions you've asked recently</p>
            <div>
              {myProfile.questions.length === 0 && (
                <p>작성한 질문이 없습니다.</p>
              )}
              <ul>
                {myProfile.questions &&
                  myProfile.questions.map((question) => {
                    return (
                      <li key={`${question.id}`}>
                        <Link to={`/questionlist/${question.id}`}>
                          {question.title}
                        </Link>
                      </li>
                    );
                  })}
              </ul>
            </div>
          </MyQuestionsContainer>
          {user && Number(id) === user.userId && (
            <form onSubmit={resignHandler}>
              <MyPageButton type="submit">탈퇴하기</MyPageButton>
            </form>
          )}
        </>
      )}
    </Wrap>
  );
}

export default MyPage;
