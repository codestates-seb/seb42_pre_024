import styled from "styled-components";

const Wrap = styled.div`
  position: relative;
`;
const NavContainer = styled.nav`
  position: absolute;
  width: 154px;
  height: 100vh;
  border-right: 1px solid #d7d9dc;
  margin: 0;
`;

const NavButton = styled.button`
  margin-top: 20px;
  width: 154px;
  height: 33px;
  background-color: #f6f6f6;
  border: none;
  border-right: 3px solid #e5883e;
`;

function Nav() {
  return (
    <Wrap>
      <NavContainer>
        <NavButton>Questions</NavButton>
      </NavContainer>
    </Wrap>
  );
}

export default Nav;
