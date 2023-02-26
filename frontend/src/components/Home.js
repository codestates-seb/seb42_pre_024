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

function Home() {
  const [list, setList] = useState("");

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const readPage = async (el) => {
    const { data } = await readData(el);
    setList(data.data);
  };

  const click = () => {
    navigate("./question");
  };

  const moveQustion = ({ id }) => {
    dispatch(paramsId(id));
    navigate(`/questionlist/${id}`);
  };

  useEffect(() => {
    (async () => {
      await readPage(1);
    })();
  }, []);

  return (
    <Wrap>
      <div className="buttonContainer">
        <h1>10 Questions asked recently</h1>
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
    </Wrap>
  );
}

export default Home;
