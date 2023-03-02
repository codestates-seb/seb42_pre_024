import axios from "axios";
axios.defaults.withCredentials = true;

export const login = async (data) => {
  try {
    const res = await axios({
      method: "post",
      data,
      headers: { Authorization: null },
      url: `http://ec2-3-35-149-213.ap-northeast-2.compute.amazonaws.com:8080/login`,
    });
    return res;
  } catch (e) {
    console.log(e);
  }
};

export const logout = async (accessToken) => {
  try {
    const token = `Bearer ${accessToken}`.toString("base64");
    const res = await axios({
      method: "post",
      url: `http://ec2-3-35-149-213.ap-northeast-2.compute.amazonaws.com:8080/logout`,
      headers: { Authorization: `${token}` },
    });
    return res;
  } catch (e) {
    console.log(e);
  }
};

export const signUp = async (data) => {
  try {
    const res = await axios({
      method: "post",
      data,
      url: `http://ec2-3-35-149-213.ap-northeast-2.compute.amazonaws.com:8080/members`,
    });
    return res;
  } catch (e) {
    console.log(e);
  }
};

export const deleteAccount = async (userId, accessToken) => {
  try {
    const token = `Bearer ${accessToken}`.toString("base64");
    const res = await axios({
      method: "delete",
      url: `http://ec2-3-35-149-213.ap-northeast-2.compute.amazonaws.com:8080/members/${userId}`,
      headers: { Authorization: `${token}` },
    });
    return res;
  } catch (e) {
    console.log(e);
  }
};

export const readMyProfile = async (userId) => {
  try {
    const res = await axios({
      method: "get",
      url: `http://ec2-3-35-149-213.ap-northeast-2.compute.amazonaws.com:8080/members/${userId}`,
    });
    return res;
  } catch (e) {
    console.log(e);
  }
};
