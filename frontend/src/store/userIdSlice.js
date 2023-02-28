import { createSlice } from "@reduxjs/toolkit";

export const userIdSlice = createSlice({
  name: " userId",
  initialState: {
    userId: null,
    accessToken: null,
  },
  reducers: {
    userInfo: (state, action) => {
      //action type이 loginUser일 때 실행
      state.userId = action.payload;
      state.accessToken = action.payload; //actions에 payloadr값이 들어옴
    },
  },
});

export const { userInfo } = userIdSlice.actions;
export default userIdSlice.reducer;
