import React, { useState, useEffect } from "react";
import styled from "styled-components";
import axios from "axios";
import DisplayQ from "./Qustions/DisplayQ";
import DisplayA from "./Qustions/DisplayA";
import WriteAns from "./Qustions/WriteAns";
import { useParams } from "react-router-dom";

axios.defaults.withCredentials = true;

const Container = styled.div`
  position: relative;
  left: 280px;
  display: flex;
  flex-direction: column;
  width: 68%;
  margin-top: 70px;
  margin: 0;
  padding: 30px;
`;

function Question() {
  const { id } = useParams();

  const [list, setList] = useState();
  const [update, setUpdate] = useState("");
  const [editUpdate, setEditUpdate] = useState("");

  const accessToken = localStorage.getItem("Token");
  const userId = localStorage.getItem("Id");

  const readData = async () => {
    const { data } = await axios.get(
      `http://ec2-54-180-114-122.ap-northeast-2.compute.amazonaws.com:8080/questions/${id}`
    );
    setList([data.data]);
  };

  useEffect(() => {
    (async () => {
      await readData();
    })();
    setUpdate("");
    setEditUpdate("");
  }, [update, editUpdate]);

  return (
    <Container>
      <DisplayQ list={list} accessToken={accessToken} userId={userId} />
      <DisplayA
        list={list}
        setEditUpdate={setEditUpdate}
        accessToken={accessToken}
        userId={userId}
      />
      <WriteAns id={id} setUpdate={setUpdate} userId={userId} />
    </Container>
  );
}

export default Question;
