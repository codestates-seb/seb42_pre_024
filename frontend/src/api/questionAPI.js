import axios from "axios";

export const readData = async (pageNum) => {
  try {
    const res = await axios.get(`/questions?page=${pageNum}&size=10`);
    return res;
  } catch (e) {
    console.log(e);
  }
};
