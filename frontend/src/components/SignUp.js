import styled from "styled-components";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { useNavigate, Link } from "react-router-dom";
import { signUp } from "../api/userAPI";

const Wrap = styled.div`
  position: fixed;
  display: flex;
  justify-content: center;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: var(--whitegray);
  z-index: 800;
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
    color: var(--bluedark);
    :hover {
      cursor: pointer;
      color: var(--blue);
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
    color: var(--bluedark);
    :hover {
      cursor: pointer;
      color: var(--blue);
    }
  }
`;

const SignUpContainer = styled.div`
  margin-top: 50px;
  display: flex;
  text-align: center;
  justify-content: center;
  height: auto;
  width: 300px;
  padding: 10px;
  background-color: #ffffff;
  border-radius: var(--bd-rd);
  box-shadow: var(--form);
`;

const SignUpForm = styled.form`
  display: flex;
  flex-direction: column;
  text-align: center;
  justify-content: center;
  height: auto;
  width: 278px;
  margin-top: 20px;
  .singUpInstruction {
    text-align: left;
    font-size: 14px;
    color: var(--graydark);
    margin-top: 0px;
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
  border: 1px solid var(--gray);
  border-radius: var(--bd-rd);
  :focus {
    outline: none;
    border: 1px solid var(--grayblue);
    box-shadow: 0 0 0 5px var(--graywhite);
  }
`;

const SignUpButton = styled.button`
  height: 40px;
  margin: 10px 0 10px 0;
  background-color: var(--blue);
  color: var(--white);
  border: solid;
  border-color: var(--grayblue);
  border-radius: var(--bd-rd);
  cursor: pointer;
  :hover {
    background-color: var(--bluedark);
  }
`;

const ErrorMsg = styled.p`
  color: var(--red);
  font-size: small;
  width: 100%;
  margin-top: 0px;
`;

function SignUp() {
  const {
    register,
    handleSubmit,
    formState: { isSubmitting, isDirty, errors },
  } = useForm();

  const navigate = useNavigate();

  const onSubmit = async (data) => {
    signUp(data);
    navigate("/");
  };

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
            <SignUpForm onSubmit={handleSubmit(onSubmit)}>
              <SignUpLabel htmlFor="Display Name">Display Name</SignUpLabel>
              <SignUpInput
                type="text"
                name="username"
                aria-invalid={
                  !isDirty ? undefined : errors.name ? "true" : "false"
                }
                {...register("name", {
                  required: "유저명은 필수 입력입니다.",
                  pattern: {
                    value: /^[a-zA-Z가-힣0-9]{4,16}$/,
                    message:
                      "이름은 특수문자 없이 4~16자 사이로 만들어야 합니다.",
                  },
                })}
              />
              {errors.name && (
                <ErrorMsg role="alert">{errors.name.message}</ErrorMsg>
              )}
              <SignUpLabel htmlFor="Email">Email</SignUpLabel>
              <SignUpInput
                type="email"
                name="email"
                aria-invalid={
                  !isDirty ? undefined : errors.email ? "true" : "false"
                }
                {...register("email", {
                  required: "이메일은 필수 입력입니다.",
                  pattern: {
                    value:
                      /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/,
                    message: "이메일 형식에 맞지 않습니다.",
                  },
                })}
              />
              {errors.email && (
                <ErrorMsg role="alert">{errors.email.message}</ErrorMsg>
              )}
              <SignUpLabel htmlFor="Password">Password</SignUpLabel>
              <SignUpInput
                type="password"
                name="password"
                autoComplete="on"
                aria-invalid={
                  !isDirty ? undefined : errors.password ? "true" : "false"
                }
                {...register("password", {
                  required: "비밀번호는 필수 입력입니다.",
                  pattern: {
                    value: /(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z\d]{8,}/,
                    message:
                      "비밀번호는 8자 이상이면서 숫자 하나와 알파벳 하나가 포함되어야 합니다.",
                  },
                })}
              />
              {errors.password && (
                <ErrorMsg role="alert">{errors.password.message}</ErrorMsg>
              )}
              <p className="singUpInstruction">
                Passwords must contain at least eight characters, including at
                least 1 letter and 1 number.
              </p>
              <SignUpButton type="submit" disabled={isSubmitting}>
                Sign up
              </SignUpButton>
              <p className="singUpInstruction">
                By clicking “Sign up”, you agree to our terms of service,
                privacy policy and cookie policy
              </p>
            </SignUpForm>
          </SignUpContainer>
          <LoginWrap>
            <span>Already have an account?</span>
            <Link to="http://localhost:3000/login">Login</Link>
          </LoginWrap>
        </div>
      </Container>
    </Wrap>
  );
}

export default SignUp;
