import axios from "axios";
axios.defaults.withCredentials = true;

export const login = async (data) => {
  try {
    const res = await axios({
      method: "post",
      data,
      headers: { Authorization: null },
      url: `${process.env.REACT_APP_API_URL}/login`,
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
      url: `${process.env.REACT_APP_API_URL}/logout`,
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
      url: `${process.env.REACT_APP_API_URL}/members`,
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
      url: `${process.env.REACT_APP_API_URL}/members/${userId}`,
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
      url: `${process.env.REACT_APP_API_URL}/members/${userId}`,
    });
    return res;
  } catch (e) {
    console.log(e);
  }
};
