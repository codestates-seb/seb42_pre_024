import styled from "styled-components";
import { useLocation } from "react-router-dom";
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
      font-size: medium;
      color: var(--graydark);
      display: flex;
    }
    #public {
      margin: 30px 0 5px 0;
      font-size: small;
      padding-left: 10px;
    }
  }
`;

const NavButton = styled.a`
  width: 150px;
  height: 33px;
  background-color: var(--white);
  text-decoration: none;
  padding-left: 10px;
  :visited {
    color: var(--black);
  }
  > div {
    display: flex;
    align-items: center;
    height: 100%;
  }
  > div > div {
    color: var(--graydarker);
    text-align: left;
    margin-left: 5px;
    :hover {
      cursor: pointer;
      color: var(--black);
    }
  }
`;

const NavSelectedButton = styled.a`
  width: 150px;
  height: 33px;
  background-color: var(--whitegray);
  border-right: 3px solid var(--orange);
  padding-left: 10px;
  text-decoration: none;
  :visited {
    color: var(--black);
  }
  > div {
    display: flex;
    align-items: center;
    height: 100%;
  }
  > div > div {
    font-family: var(--main-font-bold);
    color: var(--black);
    text-align: left;
    margin-left: 5px;
    :hover {
      cursor: pointer;
    }
  }
`;

function Nav() {
  const curruntPage = useLocation().pathname;

  return (
    <NavContainer>
      <ul>
        <li id="home">
          {curruntPage === "/" ? (
            <NavSelectedButton href="http://stackoverflow-coco-bucket.s3-website.ap-northeast-2.amazonaws.com">
              <div>
                <div>Home</div>
              </div>
            </NavSelectedButton>
          ) : (
            <NavButton href="http://stackoverflow-coco-bucket.s3-website.ap-northeast-2.amazonaws.com">
              <div>
                <div>Home</div>
              </div>
            </NavButton>
          )}
        </li>
        <li id="public">PUBLIC</li>
        <li id="questions">
          {curruntPage !== "/" ? (
            <NavSelectedButton href="http://stackoverflow-coco-bucket.s3-website.ap-northeast-2.amazonaws.com/1">
              <div>
                <FontAwesomeIcon icon={faEarthAsia} />
                <div>Questions</div>
              </div>
            </NavSelectedButton>
          ) : (
            <NavButton href="http://stackoverflow-coco-bucket.s3-website.ap-northeast-2.amazonaws.com/1">
              <div>
                <FontAwesomeIcon icon={faEarthAsia} />
                <div>Questions</div>
              </div>
            </NavButton>
          )}
        </li>
      </ul>
    </NavContainer>
  );
}

export default Nav;
