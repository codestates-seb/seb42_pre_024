import styled from "styled-components";
import { useNavigate } from "react-router-dom";

const NavContainer = styled.nav`
  width: 280px;
  height: 100vh;
  border-right: 1px solid var(--graylight);
  /* margin-left: 120px; */
  top: 70px;
  position: fixed;
  z-index: 999;
  background-color: var(--white);
`;

const NavButton = styled.button`
  margin-top: 20px;
  margin-left: 130px;
  width: 150px;
  height: 33px;
  background-color: var(--whitegray);
  border: none;
  border-right: 3px solid var(--orange);
  :hover {
    cursor: pointer;
  }
`;

function Nav() {
  const navigate = useNavigate();
  const clickHandler = () => {
    navigate("/");
  };

  return (
    <NavContainer>
      <NavButton onClick={clickHandler}>Questions</NavButton>
    </NavContainer>
  );
}

export default Nav;
