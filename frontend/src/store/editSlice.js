import { createSlice } from "@reduxjs/toolkit";

export const editSlice = createSlice({
  name: "edit",
  initialState: {
    edit: false,
  },
  reducers: {
    doEdit: (state, action) => {
      //action type이 loginUser일 때 실행
      state.edit = action.payload; //actions에 payloadr값이 들어옴
    },
  },
});

export const { doEdit } = editSlice.actions;
export default editSlice.reducer;
