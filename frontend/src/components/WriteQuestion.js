import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { useSelector, useDispatch } from "react-redux";
import { saveQuestion } from "../store/questionSlice";
import axios from "axios";
//44
const Wrap = styled.form`
  width: 72%;
  height: 100%;
  display: flex;
  flex-direction: column;
  position: relative;
  left: 280px;
  margin-top: 70px;
  padding: 30px;
`;
const TopTitle = styled.div`
  /* height: 70px; */
  height: 70px;
  display: flex;
  justify-content: right;
  border-bottom: 1px solid #d6d9dc;
  .toptitle {
    flex: 1;
    margin: 0;
  }
`;

const TitleContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 80%;
  height: 150px;
  border: solid rgba(0, 0, 0, 0.2);
  border-radius: 5px;
  box-shadow: 0 3px 3px rgba(0, 0, 0, 0.2);
  margin-top: 100px;
  color: #ffff;
  overflow: auto;
  .b {
    display: fixed;
    margin: 30px 0 0 20px;
    font-size: 22px;
    font-weight: bold;
    color: black;
  }
  p {
    display: fixed;
    color: rgba(0, 0, 0, 0.5);
    margin: 10px 0 10px 20px;
  }
`;

const TitleInput = styled.input`
  margin: 5px 0 10px 20px;
  width: 90%;
  height: 30px;
  border: solid 1px rgba(0, 0, 0, 0.5);
  border-radius: 3px;
`;

const BodyContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 80%;
  height: 400px;
  border: solid rgba(0, 0, 0, 0.2);
  border-radius: 5px;
  box-shadow: 0 5px 5px rgba(0, 0, 0, 0.3);
  margin-top: 100px;
  .b1 {
    margin: 30px 0 10px 20px;
    font-size: 22px;
    font-weight: bold;
    color: black;
  }
`;
const Body = styled.textarea`
  margin: 5px 0 10px 20px;
  width: 90%;
  height: 60%;
  border: solid 1px rgba(0, 0, 0, 0.5);
  border-radius: 3px;
  font-size: 20px;
`;

const Submitbtn = styled.button`
  width: 100px;
  height: 40px;
  margin: 10px 0 0 20px;
  border: solid;
  background-color: #4393f7;
  border-color: #8ebefa;
  color: #ffff;
  border-radius: 5px;
  cursor: pointer;
`;
function WriteQuestion() {
  //title,body,redirec
  const [btn, setBtn] = useState(false);
  const [queTitle, setQueTitle] = useState("");
  const [queContent, setQueContent] = useState("");
  let editQuestion = useSelector((state) => {
    return state.question;
  });
  const dispatch = useDispatch();

  // const readData = async () => {
  //   await axios.get("http://localhost:4000/data");
  // };

  // useEffect(() => {
  //   (async () => {
  //     await readData();
  //   })();
  // }, []);

  const ClickBtn = () => {
    setBtn(true);
  };

  const sendQuestion = async (e) => {
    //setRedirect
    e.preventDefault();
    if (!queTitle && queContent) {
      alert("제목을 입력해주세요");
    } else if (queTitle && !queContent) {
      alert("내용을 입력해주세요");
    } else if (!queTitle && !queContent) {
      alert("제목과 내용을 입력해주세요");
    } else {
      await axios.post(
        "http://localhost:4000/question",
        {
          title: queTitle,
          contents: queContent,
        },
        { withCredentials: true }
      );
      setQueTitle("");
      setQueContent("");
    }
    //버튼을 눌렀을 때 질문페이지 이동하기
  };
  return (
    <>
      {/* {redirect={/}} */}

      <Wrap onSubmit={(e) => sendQuestion(e)}>
        <TopTitle>
          <div className="toptitle">
            <h1>Ask a public question</h1>
          </div>
        </TopTitle>
        <TitleContainer>
          <span className="b">Title</span>
          <p>
            Be specific and imagine you’re asking a question to another person.
          </p>
          <div className="title">
            <TitleInput
              placeholder="  Title..."
              value={queTitle}
              onChange={(e) => setQueTitle(e.target.value)}
            />
          </div>
        </TitleContainer>

        <BodyContainer>
          <span className="b1">What are the details of your problem?</span>
          <Body
            placeholder="  Introduce the problem and expand on what you put in the title "
            value={queContent}
            onChange={(e) => setQueContent(e.target.value)}
            onClick={ClickBtn}
          />
          {btn === false ? "" : <Submitbtn type="submit">Submit</Submitbtn>}
        </BodyContainer>
      </Wrap>
    </>
  );
}

export default WriteQuestion;
