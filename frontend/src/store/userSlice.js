import { createSlice } from "@reduxjs/toolkit";

export const userSlice = createSlice({
  name: "user",
  initialState: {
    email: "",
    pwd: "",
  },
  reducers: {
    loginUser: (state, action) => {
      //action type이 loginUser일 때 실행
      state.email = action.payload; //actions에 payloadr값이 들어옴
      state.pwd = action.payload;
    },
  },
});

export const { loginUser } = userSlice.actions;
export default userSlice.reducer;
