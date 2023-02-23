import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { useSelector, useDispatch } from "react-redux";
import { doEdit } from "../store/editSlice";
import { Navigate, useNavigate } from "react-router-dom";
import axios from "axios";

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
  height: 70px;
  display: flex;
  justify-content: right;
  border-bottom: 1px solid var(--graylight);
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
  border: solid var(--gray);
  border-radius: var(--bd-rd);
  box-shadow: var(--form);
  margin-top: 100px;
  color: var(--white);
  overflow: auto;
  .b {
    display: fixed;
    margin: 30px 0 0 20px;
    font-size: 22px;
    font-weight: bold;
    color: var(--black);
  }
  p {
    display: fixed;
    color: var(--graydark);
    margin: 10px 0 10px 20px;
  }
`;

const TitleInput = styled.input`
  margin: 5px 0 10px 20px;
  width: 90%;
  height: 30px;
  border: solid 1px var(--graydark);
  border-radius: var(--bd-rd);
  padding-left: 10px;
  :focus {
    outline: none;
    border: 1px solid var(--grayblue);
    box-shadow: 0 0 0 5px var(--graywhite);
    border-radius: var(--bd-rd);
  }
`;

const BodyContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 80%;
  height: 400px;
  border: solid var(--gray);
  border-radius: var(--bd-rd);
  box-shadow: var(--form);
  margin-top: 100px;
  .b1 {
    margin: 30px 0 10px 20px;
    font-size: 22px;
    font-weight: bold;
    color: var(--black);
  }
`;

const Body = styled.textarea`
  margin: 5px 0 10px 20px;
  width: 90%;
  height: 60%;
  border: solid 1px var(--graydark);
  border-radius: var(--bd-rd);
  padding-left: 10px;
  :focus {
    outline: none;
    border: 1px solid var(--grayblue);
    box-shadow: 0 0 0 5px var(--graywhite);
    border-radius: var(--bd-rd);
  }
`;

const Submitbtn = styled.button`
  width: 100px;
  height: 40px;
  margin: 10px 0 0 20px;
  border: solid;
  background-color: var(--blue);
  border-color: var(--bluelight);
  color: var(--white);
  border-radius: var(--bd-rd);
  :hover {
    cursor: pointer;
    background-color: var(--bluedark);
  }
`;

function WriteQuestion() {
  //title,body,redirec
  const [btn, setBtn] = useState(false);
  const [queTitle, setQueTitle] = useState("");
  const [queContent, setQueContent] = useState("");
  const navigate = useNavigate();
  const dispatch = useDispatch();
  let editQuestion = useSelector((state) => {
    return state;
  });
  console.log(editQuestion);
  useEffect(() => {
    if (editQuestion.edit !== true) {
      setQueTitle(editQuestion.question.contents.title);
      setQueContent(editQuestion.question.contents.contents);
      dispatch(doEdit(true));
    } else {
      setQueTitle("");
      setQueContent("");
    }
  }, []);
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
      // await axios.post(
      //   "http://localhost:4000/question",
      //   {
      //     title: queTitle,
      //     contents: queContent,
      //   },
      //   { withCredentials: true }
      // );
      setQueTitle("");
      setQueContent("");
    }
    navigate(`/questionlist`);
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
              placeholder="Title..."
              value={queTitle}
              onChange={(e) => setQueTitle(e.target.value)}
            />
          </div>
        </TitleContainer>

        <BodyContainer>
          <span className="b1">What are the details of your problem?</span>
          <Body
            placeholder="Introduce the problem and expand on what you put in the title "
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
