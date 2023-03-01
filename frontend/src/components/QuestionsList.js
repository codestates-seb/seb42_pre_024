import styled from "styled-components";
import { useState, useEffect } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";
import { useSelector } from "react-redux";
import { readData } from "../api/questionAPI";
import Pagination from "./Pagination";

const Wrap = styled.main`
  width: 72%;
  height: 100%;
  display: flex;
  flex-direction: column;
  position: relative;
  left: 280px;
  margin-top: 70px;
  padding: 30px;
  .buttonContainer {
    height: 70px;
    display: flex;
    justify-content: right;
    border-bottom: 1px solid var(--graylight);
    > h1 {
      flex: 1;
      margin: 0;
    }
  }
  .questionsContainer {
    height: 1200px;
  }
`;

const ListContainer = styled.div`
  display: flex;
  flex-direction: column;
  border-bottom: 1px solid var(--graylight);
`;

const QuestionContainer = styled.div`
  display: flex;
  div:nth-child(1) {
    margin: auto 20px;
    text-align: center;
    color: var(--graydark);
  }
  #contentNum {
    font-weight: bold;
  }
  .contentContainer {
    width: 800px;
    h3 {
      flex-wrap: nowrap;
      color: var(--bluedark);
      :hover {
        cursor: pointer;
        color: var(--blue);
      }
    }
  }
  .writerContainer {
    display: flex;
    justify-content: right;
    margin-bottom: 20px;
    align-items: end;
    img {
      width: 30px;
      height: 30px;
      margin-right: 5px;
    }
    > a {
      color: var(--bluedark);
      text-decoration: none;
      margin-right: 5px;
      :hover {
        color: var(--blue);
      }
    }
    > div {
      color: var(--graydark);
    }
  }
`;

const WriteButton = styled.button`
  width: 100px;
  height: 40px;
  border: solid;
  background-color: var(--blue);
  border-color: var(--bluelight);
  color: var(--white);
  border-radius: var(--bd-rd);
  cursor: pointer;
`;

function QuestionsList() {
  const [list, setList] = useState("");
  const [pageInfo, setPageInfo] = useState();
  const navigate = useNavigate();
  const { page } = useParams();

  const readPage = async (el) => {
    const { data } = await readData(el);
    setList(data.data);
    setPageInfo(data.pageInfo);
  };

  const userInfo = useSelector((state) => {
    return state.userId;
  });
  const user = userInfo.userAccess;

  const click = (e) => {
    e.preventDefault();
    if (user === null) {
      let loginAlert = window.confirm(
        "게시물을 등록하기 위해서는 로그인이 필요합니다."
      );
      if (loginAlert) {
        navigate("../login");
      }
    } else {
      navigate("../question");
    }
  };

  const moveQustion = ({ id }) => {
    navigate(`/questionlist/${id}`);
  };

  useEffect(() => {
    (async () => {
      await readPage(page);
    })();
  }, [page]);

  return (
    <Wrap>
      <div className="buttonContainer">
        <h1>All Questions</h1>
        <WriteButton onClick={click}>Ask Question</WriteButton>
      </div>
      <div className="questionsContainer">
        <ListContainer>
          {list &&
            list.map((el, idx) => (
              <QuestionContainer key={`${el.id}`}>
                <div>
                  <div>No.</div>
                  <div id="contentNum">{idx + 1}</div>
                </div>
                <div className="contentContainer">
                  <h3 onClick={() => moveQustion(el)}>{el.title}</h3>
                  <div className="writerContainer">
                    <img alt="profile_image" src={el.member.profileImage}></img>
                    <Link to={`/members/${el.member.id}`}>
                      {el.member.name}
                    </Link>
                    <div className="date">
                      {new Date(el.createdAt).toLocaleString("ko-KR")}
                    </div>
                  </div>
                </div>
              </QuestionContainer>
            ))}
        </ListContainer>
      </div>
      <Pagination pageInfo={pageInfo} readPage={readPage} page={page} />
    </Wrap>
  );
}

export default QuestionsList;
