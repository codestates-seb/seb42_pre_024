import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEarthAsia } from "@fortawesome/free-solid-svg-icons";

const NavContainer = styled.nav`
  width: 280px;
  height: 100vh;
  border-right: 1px solid var(--graylight);
  top: 70px;
  position: fixed;
  z-index: 100;
  background-color: var(--white);
  display: flex;
  flex-direction: column;
  justify-content: right;
  > ul {
    margin-left: auto;
    list-style: none;
    > li {
      font-size: small;
      color: var(--graydark);
    }
    #public {
      margin: 30px 0 5px 0;
    }
  }
`;

const NavButton = styled.button`
  width: 150px;
  height: 33px;
  background-color: var(--white);
  font-size: medium;
  color: var(--graydarker);
  text-align: left;
  padding: 0;
  border: none;
  :hover {
    cursor: pointer;
    color: var(--black);
  }
  > span {
    margin-left: 5px;
  }
`;

const NavSelectedButton = styled.button`
  width: 150px;
  height: 33px;
  background-color: var(--whitegray);
  font-size: medium;
  font-family: var(--main-font-bold);
  text-align: left;
  border: none;
  border-right: 3px solid var(--orange);
  :hover {
    cursor: pointer;
  }
  > span {
    margin-left: 5px;
  }
`;

function Nav() {
  const [isHomeClicked, setHomeClicked] = useState(true);
  const [isQuestionsClicked, setQuestionsClicked] = useState(false);
  const navigate = useNavigate();

  const HomeHandler = () => {
    setHomeClicked(true);
    setQuestionsClicked(false);
    navigate("/");
  };

  const QuestionsHandler = () => {
    setHomeClicked(false);
    setQuestionsClicked(true);
    navigate("/1");
  };

  return (
    <NavContainer>
      <ul>
        <li>
          {isHomeClicked ? (
            <NavSelectedButton onClick={HomeHandler}>Home</NavSelectedButton>
          ) : (
            <NavButton onClick={HomeHandler}>Home</NavButton>
          )}
        </li>
        <li id="public">Public</li>
        <li>
          {isQuestionsClicked ? (
            <NavSelectedButton onClick={QuestionsHandler}>
              <FontAwesomeIcon icon={faEarthAsia} />
              <span>Questions</span>
            </NavSelectedButton>
          ) : (
            <NavButton id="questions" onClick={QuestionsHandler}>
              <FontAwesomeIcon icon={faEarthAsia} />
              <span>Questions</span>
            </NavButton>
          )}
        </li>
      </ul>
    </NavContainer>
  );
}

export default Nav;
