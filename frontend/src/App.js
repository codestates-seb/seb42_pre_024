import { createGlobalStyle } from "styled-components";
import Header from "./components/Header";
import Nav from "./components/Nav";
import WriteQuestion from "./components/WriteQuestion";
import QuestionsList from "./components/QuestionsList";
import Login from "./components/Login";
import SignUp from "./components/SignUp";
import React, { Component } from "react";
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
        {/* <Nav /> */}
        <Routes>
          {/* <Route path="/" element={<QuestionsList />} /> */}
          <Route path="/question" element={<WriteQuestion />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<SignUp />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
