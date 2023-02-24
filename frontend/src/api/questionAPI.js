import axios from "axios";

export const readData = async (pageNum) => {
  try {
    const res = await axios.get(
      `http://ec2-3-36-122-3.ap-northeast-2.compute.amazonaws.com:8080/questions?page=${pageNum}&size=10`
    );
    return res;
  } catch (e) {
    console.log(e);
  }
};
