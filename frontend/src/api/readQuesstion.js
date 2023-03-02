import axios from "axios";
axios.defaults.withCredentials = true;

export const readQustionData = async (id) => {
  try {
    const res = await axios.get(
      `${process.env.REACT_APP_API_URL}/questions/${id}`
    );
    return res;
  } catch (e) {
    console.log(e);
  }
};
