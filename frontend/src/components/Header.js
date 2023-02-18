import styled from "styled-components";
// eslint-disable-next-line import/no-unresolved
import logo from "../image/logo-stackoverflow.png";
import profile from "../image/profile.png";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";

const HeaderContainer = styled.header`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-around;
  box-shadow: 0 3px 3px rgba(0, 0, 0, 0.2);
  background-color: #ffff;
  z-index: 999;
`;

const Logo = styled.a`
  margin-left: 30px;
  img {
    height: 30px;
    width: 150px;
  }
`;

const SearchContainer = styled.div``;

const SearchInput = styled.input`
  width: 710px;
  height: 30px;
`;

const HeaderNav = styled.nav`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;

  ol {
    list-style: none;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;

    li {
      display: inline-block;
    }
  }
`;

// const LoginButton = styled.button`
//   border: solid;
//   border-color: #83a6c4;
//   width: 58px;
//   height: 30px;
//   background-color: #e1ecf4;
//   color: #39739d;
//   border-radius: 7%;
//   margin-right: 10px;
// `;

// const SignUpButton = styled.button`
//   width: 65px;
//   height: 30px;
//   background-color: #0a95ff;
//   color: #ffffff;
//   border-radius: 7%;
//   border: solid;
//   border-color: rgba(67, 147, 247, 1);
// `;

const MypageButton = styled.button`
  width: 30px;
  height: 30px;
  padding: 0;
  margin: 0;
  border: none;
  margin-right: 10px;

  img {
    width: 30px;
    height: 30px;
    border-radius: 7%;
  }
`;
const LogoutButton = styled.button`
  border: solid;
  border-color: #83a6c4;
  width: 58px;
  height: 30px;
  background-color: #e1ecf4;
  color: #39739d;
  border-radius: 7%;
  margin-right: 10px;
`;
function Header() {
  return (
    <HeaderContainer>
      <Logo>
        <img alt="logo" src={logo}></img>
      </Logo>
      <form>
        <SearchContainer>
          <FontAwesomeIcon icon={faMagnifyingGlass} />
          <SearchInput placeholder="Search..." />
        </SearchContainer>
      </form>

      <HeaderNav>
        <ol>
          <li>
            {" "}
            {/* <LoginButton>Log in</LoginButton> */}
            <MypageButton>
              <img src={profile}></img>
            </MypageButton>
          </li>
          <li>
            {" "}
            {/* <SignUpButton>Sign up</SignUpButton> */}
            <LogoutButton>Logout</LogoutButton>
          </li>
        </ol>
      </HeaderNav>
    </HeaderContainer>
  );
}

export default Header;
