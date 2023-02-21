import styled from "styled-components";
import profile from "../image/profile.png";

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
    width: 150px;
    height: 150px;
    > img {
      width: 100%;
      height: 100%;
      border-radius: 4px;
    }
  }
  .userInfoContainer {
    width: 600px;
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
  return (
    <Wrap>
      <ProfileContainer>
        <div className="imgContainer">
          <img alt="profile_image" src={profile}></img>
        </div>
        <div className="userInfoContainer">
          <h1>User Name</h1>
          <div>about me 내용</div>
        </div>
        <div className="buttonContainer">
          <MyPageButton>Edit my profile</MyPageButton>
        </div>
      </ProfileContainer>
      <MyQuestionsContainer>
        <h1>My Questions</h1>
        <div>질문 목록</div>
      </MyQuestionsContainer>
      <MyPageButton>탈퇴하기</MyPageButton>
    </Wrap>
  );
}

export default MyPage;
