import styled from "styled-components";
import { useForm } from "react-hook-form";
import { useNavigate, Link } from "react-router-dom";
import { useDispatch } from "react-redux";
import { userInfo } from "../store/userIdSlice";
import { login } from "../api/userAPI";
import jwt_decode from "jwt-decode";

const Wrap = styled.div`
  position: fixed;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: var(--whitegray);
  z-index: 800;
`;

const SignupWrap = styled.div`
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

const LoginContainer = styled.div`
  margin: 150px auto;
  display: flex;
  text-align: center;
  justify-content: center;
  height: 400px;
  width: 412px;
  background-color: var(--white);
  border-radius: 2%;
  box-shadow: var(--form);
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
  border: 1px solid var(--gray);
  border-radius: var(--bd-rd);
  :focus {
    outline: none;
    border: 1px solid var(--grayblue);
    box-shadow: 0 0 0 5px var(--graywhite);
  }
`;

const LoginButton = styled.button`
  height: 40px;
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
  color: red;
  font-size: small;
  width: 100%;
  margin-top: 0px;
  margin-bottom: 10px;
`;

function Login() {
  const dispatch = useDispatch();
  const {
    register,
    handleSubmit,
    formState: { isSubmitting, isDirty, errors },
  } = useForm();
  const navigate = useNavigate();

  const onSubmit = async (data) => {
    const res = await login(data);
    if (res?.status !== 200) {
      alert("로그인에 실패했습니다. 이메일과 비밀번호를 다시 확인해주세요.");
    } else {
      const accessToken = res.headers.authorization.slice(7);
      var userId = jwt_decode(accessToken).id;
      localStorage.setItem("key", userId);
      dispatch(userInfo({ userId, accessToken }));
      navigate("/");
    }
  };

  return (
    <Wrap>
      <LoginContainer>
        <LoginForm onSubmit={handleSubmit(onSubmit)}>
          <LoginLabel htmlFor="email">Email</LoginLabel>
          <LoginInput
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
          <LoginLabel htmlFor="password">Password</LoginLabel>
          <LoginInput
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
          <LoginButton type="submit" disabled={isSubmitting}>
            Log in
          </LoginButton>
        </LoginForm>
      </LoginContainer>
      <SignupWrap>
        <span>Don't have an account?</span>
        <Link to="http://localhost:3000/signup">Sign up</Link>
      </SignupWrap>
    </Wrap>
  );
}

export default Login;
