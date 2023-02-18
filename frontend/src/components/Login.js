import styled from "styled-components";

const Wrap = styled.div`
  position: fixed;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #f1f2f3;
`;

const SignupWrap = styled.div`
  display: flex;
  justify-content: center;
  text-align: center;
  > a {
    margin-left: 10px;
    text-decoration-line: none;
    color: #3172c6;
    :hover {
      cursor: pointer;
      color: #4393f7;
    }
  }
`;

const LoginContainer = styled.div`
  margin: 150px auto;
  display: flex;
  text-align: center;
  justify-content: center;
  height: 400px;
  width: 412px;
  background-color: #ffffff;
  border-radius: 2%;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
`;

const LoginForm = styled.form`
  display: flex;
  flex-direction: column;
  text-align: center;
  justify-content: center;
  height: 390px;
  width: 278px;
`;

const LoginLabel = styled.label`
  text-align: left;
  margin-bottom: 5px;
  font-weight: bold;
`;

const LoginInput = styled.input`
  height: 30px;
  margin-bottom: 20px;
  border: 1px solid #c4c8cc;
  border-radius: 4px;
  :focus {
    outline: none;
    border: 1px solid #6ba2d9;
    box-shadow: 0 0 0 5px #e0eaf6;
  }
`;

const LoginButton = styled.button`
  height: 40px;
  background-color: #4393f7;
  color: #ffff;
  border: solid;
  border-color: #83a6c4;
  border-radius: 4px;
  cursor: pointer;
  :hover {
    background-color: #3172c6;
  }
`;

function Login() {
  return (
    <Wrap>
      <LoginContainer>
        <LoginForm>
          <LoginLabel>Email</LoginLabel>
          <LoginInput></LoginInput>
          <LoginLabel>Password</LoginLabel>
          <LoginInput></LoginInput>
          <LoginButton>Log in</LoginButton>
        </LoginForm>
      </LoginContainer>
      <SignupWrap>
        <span>Don't have an account?</span>
        <a href="http://localhost:3000/signup">Sign up</a>
      </SignupWrap>
    </Wrap>
  );
}

export default Login;
