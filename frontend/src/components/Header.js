import styled from "styled-components";
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
  background-color: var(--white);
  z-index: 999;
`;

const Logo = styled.div`
  margin-left: 30px;
  img {
    height: 30px;
    width: 150px;
  }
`;

const SearchContainer = styled.div`
  .icon {
    position: absolute;
    top: 36%;
    margin-left: 7px;
    color: var(--graydark);
  }
`;

const SearchInput = styled.input`
  width: 710px;
  height: 30px;
  padding-left: 25px;
  :focus {
    outline: none;
    border: 1px solid var(--grayblue);
    box-shadow: 0 0 0 5px var(--graywhite);
    border-radius: var(--bd-rd);
  }
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
//   border-radius: var(--bd-rd);
//   margin-right: 10px;
// `;

// const SignUpButton = styled.button`
//   width: 65px;
//   height: 30px;
//   background-color: #0a95ff;
//   color: var(--white);
//   border-radius: var(--bd-rd);
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
    border-radius: var(--bd-rd);
  }
`;

const LogoutButton = styled.button`
  border: solid;
  border-color: #83a6c4;
  width: 58px;
  height: 30px;
  background-color: #e1ecf4;
  color: #39739d;
  border-radius: var(--bd-rd);
  margin-right: 10px;
`;

function Header() {
  return (
    <HeaderContainer>
      <Logo>
        <a href="/">
          <img alt="logo" src={logo} />
        </a>
      </Logo>
      <form>
        <SearchContainer>
          <FontAwesomeIcon className="icon" icon={faMagnifyingGlass} />
          <SearchInput placeholder="Search..." />
        </SearchContainer>
      </form>
      <HeaderNav>
        <ol>
          <li>
            {" "}
            {/* <LoginButton>Log in</LoginButton> */}
            <MypageButton>
              <img alt="profile_image" src={profile}></img>
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
