import { createGlobalStyle } from "styled-components";
import Header from "./components/Header";
import Nav from "./components/Nav";

const GlobalStyle = createGlobalStyle`
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  
`;

function App() {
  return (
    <>
      <GlobalStyle />
      <Header />
      <Nav />
    </>
  );
}

export default App;
