import styled from "styled-components";
import profile from "../image/profile.png";
import axios from "axios";

import { useState, useEffect } from "react";
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
    border-bottom: 1px solid #d6d9dc;
    > h1 {
      flex: 1;
      margin: 0;
    }
  }
  .questionsContainer {
    height: 1690px;
  }
`;

const ListContainer = styled.div`
  display: flex;
  flex-direction: column;
  border-bottom: 1px solid #d6d9dc;
`;

const QuestionContainer = styled.div`
  display: flex;
  div:nth-child(1) {
    margin: auto 20px;
    text-align: center;
    color: #6a737c;
  }
  #viewCounts {
    font-weight: bold;
  }
  .contentContainer {
    width: 800px;
    h3 {
      flex-wrap: nowrap;
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
  background-color: #4393f7;
  border-color: #8ebefa;
  color: #ffff;
  border-radius: 5px;
  cursor: pointer;
`;

const PageContainer = styled.div``;

const PageButton = styled.button`
  width: 26.8px;
  height: 27px;
  border: 1px solid #d6d9dc;
  border-radius: 4px;
  background-color: #ffffff;
  margin: 0 2px;
  padding: 0 auto;
  :hover {
    cursor: pointer;
    background-color: #d7d9dc;
  }
`;

const PageSelectedButton = styled.button`
  width: 26.8px;
  height: 27px;
  border: 1px solid #d6d9dc;
  border-radius: 4px;
  background-color: #f48224;
  color: #ffffff;
  margin: 0 2px;
  padding: 0 auto;
`;

const MovePageButton = styled.button`
  height: 27px;
  border: 1px solid #d6d9dc;
  border-radius: 4px;
  background-color: #ffffff;
  margin: 0 2px;
`;

function QuestionsList() {
  const [list, setList] = useState("");

  const readData = async () => {
    const { data } = await axios.get("http://localhost:4000/data");
    setList(data);
  };
  console.log(list);
  useEffect(() => {
    (async () => {
      await readData();
    })();
  }, []);

  return (
    <Wrap>
      <div className="buttonContainer">
        <h1>All Questions</h1>
        <WriteButton>Ask Question</WriteButton>
      </div>
      <div className="questionsContainer">
        <ListContainer>
          {list &&
            list.map((el) => (
              <QuestionContainer>
                <div>
                  <div>View</div>
                  <div id="viewCounts">1</div>
                </div>
                <div className="contentContainer">
                  <h3>{el.title}</h3>
                  <div className="writerContainer">
                    <img alt=" profile_image" src={profile}></img>
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
        <MovePageButton>prev</MovePageButton>
        <PageSelectedButton>1</PageSelectedButton>
        <PageButton>2</PageButton>
        <PageButton>3</PageButton>
        <PageButton>4</PageButton>
        <PageButton>5</PageButton>
        <span>...</span>
        <PageButton>18</PageButton>
        <MovePageButton>next</MovePageButton>
      </PageContainer>
    </Wrap>
  );
}

export default QuestionsList;
