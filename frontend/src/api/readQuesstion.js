import axios from "axios";

export const readQustionData = async (id) => {
  try {
    const res = await axios.get(`/questions/${id}`);
    return res;
  } catch (e) {
    console.log(e);
  }
};
