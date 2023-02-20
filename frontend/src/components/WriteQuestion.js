import React, { useState } from "react";
import styled from "styled-components";

const Wrap = styled.form`
  position: relative;
  left: 280px;
  display: flex;
  flex-direction: column;
  width: 68%;
  margin-top: 70px;
  margin: 0;
  padding: 30px;
`;
const TopTitle = styled.h1`
  display: flex;
  height: 70px;
  margin-top: 70px;
  flex-wrap: wrap;
  font-size: 30px;
  flex-wrap: wrap-reverse;
  border-bottom: 1px solid #d6d9dc;
`;

const TitleContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 150px;
  border: solid rgba(0, 0, 0, 0.2);
  border-radius: 5px;
  box-shadow: 0 3px 3px rgba(0, 0, 0, 0.2);
  position: relative;
  color: #ffff;
  b {
    position: absolute;
    top: 30px;
    margin-right: 750px;
    margin-bottom: 80px;
    font-size: 22px;
    color: black;
  }
  p {
    color: rgba(0, 0, 0, 0.5);
    position: absolute;
    bottom: 60px;
    margin: 0 340px 0 0;
  }
`;

const TitleInput = styled.input`
  position: absolute;
  bottom: 15px;
  margin: 13px 0 0 5px;
  width: 800px;
  height: 30px;
  border: solid 1px rgba(0, 0, 0, 0.5);
  border-radius: 3px;
`;

const BodyContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 400px;
  border: solid rgba(0, 0, 0, 0.2);
  border-radius: 5px;
  box-shadow: 0 5px 5px rgba(0, 0, 0, 0.3);
  position: relative;
  margin-top: 70px;
  b {
    position: absolute;
    top: 30px;
    /* left: 55px; */
    margin-right: 400px;
    font-size: 22px;
    color: black;
  }
`;
const Body = styled.textarea`
  position: absolute;
  /* left: 50px; */
  width: 800px;
  height: 250px;
  border: solid 1px rgba(0, 0, 0, 0.5);
  border-radius: 3px;
  font-size: 20px;
`;

const Submitbtn = styled.button`
  position: absolute;
  width: 100px;
  height: 40px;
  /* left: 50px; */
  bottom: 25px;
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

  const ClickBtn = () => {
    setBtn(true);
  };

  const sendQuestion = () => {
    //setRedirect
  };
  return (
    <>
      {/* {redirect={/}} */}

      <Wrap onSubmit={(e) => sendQuestion(e)}>
        <TopTitle>Ask a public question</TopTitle>
        <TitleContainer>
          <b>Title</b>
          <p>
            Be specific and imagine you’re asking a question to another person.
          </p>
          <TitleInput placeholder="  Title..." />
        </TitleContainer>

        <BodyContainer>
          <b>What are the details of your problem?</b>
          <p>Introduce the problem and expand on what you put in the title.</p>
          <Body
            placeholder="  Introduce the problem and expand on what you put in the title "
            onClick={ClickBtn}
          />
          {btn === false ? "" : <Submitbtn type={"submit"}>Submit</Submitbtn>}
        </BodyContainer>
      </Wrap>
    </>
  );
}

export default WriteQuestion;
