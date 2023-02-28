import { createSlice } from "@reduxjs/toolkit";

export const userIdSlice = createSlice({
  name: " userId",
  initialState: {
    userAccess: null,
  },
  reducers: {
    userInfo: (state, action) => {
      state.userAccess = action.payload;
    },
  },
});

export const { userInfo } = userIdSlice.actions;
export default userIdSlice.reducer;
