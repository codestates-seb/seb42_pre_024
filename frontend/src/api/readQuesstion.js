import axios from "axios";
axios.defaults.withCredentials = true;

export const readQustionData = async (id) => {
  try {
    const res = await axios.get(
      `http://ec2-54-180-114-122.ap-northeast-2.compute.amazonaws.com:8080/questions/${id}`
    );
    return res;
  } catch (e) {
    console.log(e);
  }
};
