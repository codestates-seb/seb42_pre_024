import styled from "styled-components";

const NavContainer = styled.nav`
  position: absolute;
  width: 154px;
  height: 100%;
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
    <NavContainer>
      <NavButton>Questions</NavButton>
    </NavContainer>
  );
}

export default Nav;