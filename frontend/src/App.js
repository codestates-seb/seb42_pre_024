import { createGlobalStyle } from "styled-components";
import Header from "./components/Header";
import Nav from "./components/Nav";
import WriteQuestion from "./components/WriteQuestion";
import QuestionsList from "./components/QuestionsList";
import Login from "./components/Login";
import SignUp from "./components/SignUp";
import MyPage from "./components/MyPage";
import Question from "./components/Question";
import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

const GlobalStyle = createGlobalStyle`
  box-sizing: border-box;
  margin: 0;
  padding: 0;
`;

function App() {
  return (
    <>
      <GlobalStyle />
      <BrowserRouter>
        <Header />
        <Nav />
        <Routes>
          <Route path="/" element={<QuestionsList />} />
          <Route path="/question" element={<WriteQuestion />} />
          <Route path="/questionlist" element={<Question />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/members" element={<MyPage />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
