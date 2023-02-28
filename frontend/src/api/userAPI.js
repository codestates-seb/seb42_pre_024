import axios from "axios";

export const login = async (data) => {
  try {
    const res = await axios({
      method: "post",
      data,
      headers: { Authorization: null },
      url: "/login",
    });
    return res;
  } catch (e) {
    console.log(e);
  }
};

export const logout = async () => {
  try {
    const res = await axios({
      method: "post",
      url: "/logout",
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
      url: "/members",
    });
    return res;
  } catch (e) {
    console.log(e);
  }
};

export const deleteAccount = async (userId) => {
  try {
    const res = await axios({
      method: "delete",
      url: `/members/${userId}`,
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
      url: `/members/${userId}`,
    });
    return res;
  } catch (e) {
    console.log(e);
  }
};
