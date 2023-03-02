import axios from "axios";
axios.defaults.withCredentials = true;

export const readData = async (pageNum) => {
  try {
    const res = await axios.get(
      `http://ec2-3-35-149-213.ap-northeast-2.compute.amazonaws.com:8080/questions?page=${pageNum}&size=10`
    );
    return res;
  } catch (e) {
    console.log(e);
  }
};
