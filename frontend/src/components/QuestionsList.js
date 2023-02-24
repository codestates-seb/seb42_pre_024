import styled from "styled-components";
import { useDispatch } from "react-redux";
import { paramsId } from "../store/paramsId.Slice";

import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import { readData } from "../api/questionAPI";

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
  #viewCounts {
    font-weight: bold;
  }
  .contentContainer {
    width: 800px;
    h3 {
      flex-wrap: nowrap;
    }
    :hover {
      color: #3172c6;
      cursor: pointer;
    }
  }
  .writerContainer {
    display: flex;
    justify-content: right;
    margin-bottom: 20px;
    img {
      width: 30px;
      height: 30px;
      margin-right: 5px;
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

const PageContainer = styled.div``;

const PageButton = styled.button`
  width: 26.8px;
  height: 27px;
  border: 1px solid var(--graylight);
  border-radius: var(--bd-rd);
  background-color: var(--white);
  margin: 0 2px;
  padding: 0 auto;
  :hover {
    cursor: pointer;
    background-color: var(--graylight);
  }
`;

const PageSelectedButton = styled.button`
  width: 26.8px;
  height: 27px;
  border: 1px solid var(--graylight);
  border-radius: var(--bd-rd);
  background-color: var(--orangelight);
  color: var(--white);
  margin: 0 2px;
  padding: 0 auto;
  :hover {
    cursor: pointer;
  }
`;

const MovePageButton = styled.button`
  height: 27px;
  border: 1px solid var(--graylight);
  border-radius: 4px;
  background-color: var(--white);
  margin: 0 2px;
  :hover {
    cursor: pointer;
    background-color: var(--graylight);
  }
`;

function QuestionsList() {
  const [list, setList] = useState("");
  const [pageInfo, setPageInfo] = useState();
  const [pageBtnClicked, setPageBtnClicked] = useState(-1);

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const readPage = async (el) => {
    const { data } = await readData(el);
    setList(data.data);
    setPageInfo(data.pageInfo);
  };

  useEffect(() => {
    (async () => {
      await readPage(1);
    })();
  }, []);

  const click = () => {
    navigate("./question");
  };

  const moveQustion = ({ id }) => {
    dispatch(paramsId(id));
    navigate(`./questionlist/${id}`);
  };

  const pageHandler = (el) => {
    setPageBtnClicked(el);
    readPage(el);
    navigate(`/${el}`);
  };

  const prevPageHandler = (el) => {
    setPageBtnClicked(el - 1);
    readPage(el - 1);
    navigate(`/${el - 1}`);
  };

  const nextPageHandler = (el) => {
    setPageBtnClicked(el + 1);
    readPage(el + 1);
    navigate(`/${el + 1}`);
  };

  const pageArray = [];
  if (pageInfo) {
    for (let i = 1; i <= pageInfo.totalPages; i++) {
      pageArray.push(i);
    }
  }

  return (
    <Wrap>
      <div className="buttonContainer">
        <h1>All Questions</h1>
        <WriteButton onClick={click}>Ask Question</WriteButton>
      </div>
      <div className="questionsContainer">
        <ListContainer>
          {list &&
            list.map((el) => (
              <QuestionContainer key={`${el.id}`}>
                <div>
                  <div>View</div>
                  <div id="viewCounts">1</div>
                </div>
                <div className="contentContainer">
                  <h3 onClick={() => moveQustion(el)}>{el.title}</h3>
                  <div className="writerContainer">
                    <img alt=" profile_image" src={el.profileImage}></img>
                    <div>{el.id}</div>
                    <div className="date">
                      {new Date(el.createdAt).toLocaleString()}
                    </div>
                  </div>
                </div>
              </QuestionContainer>
            ))}
        </ListContainer>
      </div>
      <PageContainer>
        {pageArray.length !== 0 && pageBtnClicked !== 1 ? (
          <MovePageButton onClick={() => prevPageHandler(pageInfo.page)}>
            prev
          </MovePageButton>
        ) : (
          <span></span>
        )}
        {pageArray.length > 0 &&
          pageArray.length <= 6 &&
          pageArray.map((page) => {
            return pageBtnClicked === page ? (
              <PageSelectedButton key={page} onClick={() => pageHandler(page)}>
                {page}
              </PageSelectedButton>
            ) : (
              <PageButton key={page} onClick={() => pageHandler(page)}>
                {page}
              </PageButton>
            );
          })}
        {pageArray.length >= 7 &&
          pageArray.map((page) => {
            return pageBtnClicked === page ? (
              <PageSelectedButton key={page} onClick={() => pageHandler(page)}>
                {page}
              </PageSelectedButton>
            ) : (
              <PageButton key={page} onClick={() => pageHandler(page)}>
                {page}
              </PageButton>
            );
          })}
        {pageArray.length !== 0 && pageBtnClicked !== pageArray.length ? (
          <MovePageButton onClick={() => nextPageHandler(pageInfo.page)}>
            next
          </MovePageButton>
        ) : (
          <span></span>
        )}
      </PageContainer>
    </Wrap>
  );
}

export default QuestionsList;

////prev, next 버튼 구현하기

//// 6페이지 이하일 때
//// 첫번째 페이지에 있으면 1, 2, 3, 4, 5, 6 next
//// 중간 페이지에 있으면 prev, 1, 2, 3, 4, 5, 6 next
//// 마지막 페이지에 있으면 prev, 1, 2, 3, 4, 5, 6
//// prev 첫번째 페이지만 아니면 보이기, next 마지막 페이지만 아니면 보이기

// 7페이지 이상일 때
// 첫번째 페이지에 있으면 1, 2, 3, 4, 5, ... ,last, next
// 중간 페이지에 있으면 prev, 1, ... , current-2 ,current- 1, current, current+1, current+2, ... , last, next
// 마지막 페이지에 있으면 prev, 1, ... , ,last-4, last-3,last-2, last-1, last
