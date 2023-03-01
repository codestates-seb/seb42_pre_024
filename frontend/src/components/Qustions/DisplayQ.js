import styled from "styled-components";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate, Link } from "react-router-dom";
import { saveContents } from "../../store/questionSlice";
import { doEdit } from "../../store/editSlice";
import axios from "axios";
import { useParams } from "react-router-dom";

const TitleContainer = styled.div`
  display: flex;
  justify-content: space-between;
  position: relative;
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

function DisplayQ({ list, accessToken, userId }) {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { id } = useParams();

  const handleEdit = ({ id, title, contents }) => {
    const newContents = {
      title,
      contents,
    };
    dispatch(saveContents(newContents));
    dispatch(doEdit(true));
    navigate(`/question/${id}`);
  };

  const moveQuestion = (e) => {
    e.preventDefault();
    if (userId === null) {
      let loginAlert = window.confirm(
        "게시물을 등록하기 위해서는 로그인이 필요합니다."
      );
      if (loginAlert) {
        navigate("../login");
      }
    } else {
      navigate("/question");
    }
  };

  const handleDelete = async () => {
    const token = `Bearer ${accessToken}`.toString("base64");
    await axios.delete(`/questions/${id}`, {
      headers: { Authorization: `${token}` },
    });
    navigate("../");
  };

  return (
    <>
      {list && (
        <div key={`${list[0].id}`}>
          <TitleContainer>
            <div className="titleCreate">
              <Title>{list[0].title}</Title>
              <div className="createAt">
                <span>
                  Asked: {new Date(list[0].createdAt).toLocaleString()}
                </span>
                {list[0].modifiedAt && (
                  <span>
                    Modified: {new Date(list[0].modifiedAt).toLocaleString()}
                  </span>
                )}
              </div>
            </div>
            <AskBtn onClick={moveQuestion}>Ask Quetion</AskBtn>
          </TitleContainer>
          <QuestionContainer>
            <p>{list[0].contents}</p>
            <ModifyWrap>
              {userId == list[0].member.id ? (
                <>
                  <Edit onClick={() => handleEdit(list[0])}>Edit</Edit>
                  <Delete onClick={handleDelete}>Delete</Delete>
                </>
              ) : (
                ""
              )}

              <Profile>
                <img alt="logo" src={list[0].member.profileImage}></img>
                <Link to={`/members/${list[0].member.id}`}>
                  {list[0].member.name}
                </Link>
              </Profile>
            </ModifyWrap>
          </QuestionContainer>
        </div>
      )}
    </>
  );
}

export default DisplayQ;
