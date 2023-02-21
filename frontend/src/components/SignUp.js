import styled from "styled-components";

const Wrap = styled.div`
  position: fixed;
  display: flex;
  justify-content: center;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #f1f2f3;
`;

const Container = styled.div`
  margin-top: 100px;
  width: 774px;
  height: 633px;
  display: flex;
  justify-content: center;
  text-align: center;
`;

const LoginWrap = styled.div`
  margin-top: 30px;
  display: flex;
  justify-content: center;
  text-align: center;
  font-size: 14px;
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

const Info = styled.div`
  display: flex;
  justify-content: center;
  text-align: left;
  width: 410px;
  margin: auto auto auto 0;
  > div > div {
    margin-bottom: 25px;
    font-size: 16px;
  }
  > div > h1 {
    font-size: 25px;
    margin-bottom: 40px;
  }
  > div > div:nth-last-child(2) {
    font-size: 14px;
    margin-bottom: 0px;
  }
  > div > div:nth-last-child(1) {
    font-size: 14px;
    color: #3172c6;
    :hover {
      cursor: pointer;
      color: #4393f7;
    }
  }
`;

const SignUpContainer = styled.div`
  margin-top: 50px;
  display: flex;
  text-align: center;
  justify-content: center;
  height: 500px;
  width: 300px;
  padding: 10px;
  background-color: #ffffff;
  border-radius: 2%;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
`;

const SignUpForm = styled.form`
  display: flex;
  flex-direction: column;
  text-align: center;
  justify-content: center;
  height: 470px;
  width: 278px;
  margin-top: 20px;
  > p {
    text-align: left;
    font-size: 14px;
    color: #6a737c;
  }
  > input:nth-child(3n) {
    margin-bottom: 0px;
  }
`;

const SignUpLabel = styled.label`
  text-align: left;
  margin-bottom: 5px;
  font-weight: bold;
`;

const SignUpInput = styled.input`
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

const SignUpButton = styled.button`
  height: 40px;
  margin: 50px 0 10px 0;
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

function SignUp() {
  return (
    <Wrap>
      <Container>
        <Info>
          <div>
            <h1>Join the Stack Overflow community</h1>
            <div>
              <div>Get unstuck — ask a question</div>
            </div>
            <div>
              <div>Unlock new privileges like voting and commenting</div>
            </div>
            <div>
              <div>Save your favorite tags, filters, and jobs</div>
            </div>
            <div>
              <div>Earn reputation and badges</div>
            </div>
            <div>
              Collaborate and share knowledge with a private group for FREE.
            </div>
            <div>Get Stack Overflow for Teams free for up to 50 users.</div>
          </div>
        </Info>
        <div>
          <SignUpContainer>
            <SignUpForm>
              <SignUpLabel>Display Name</SignUpLabel>
              <SignUpInput></SignUpInput>
              <SignUpLabel>Email</SignUpLabel>
              <SignUpInput></SignUpInput>
              <SignUpLabel>Password</SignUpLabel>
              <SignUpInput></SignUpInput>
              <p>
                Passwords must contain at least eight characters, including at
                least 1 letter and 1 number.
              </p>
              <SignUpButton>Sign up</SignUpButton>
              <p>
                By clicking “Sign up”, you agree to our terms of service,
                privacy policy and cookie policy
              </p>
            </SignUpForm>
          </SignUpContainer>
          <LoginWrap>
            <span>Already have an account?</span>
            <a href="http://localhost:3000/login">Login</a>
          </LoginWrap>
        </div>
      </Container>
    </Wrap>
  );
}

export default SignUp;
