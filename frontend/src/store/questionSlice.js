import { createSlice } from "@reduxjs/toolkit";

export const questionSlice = createSlice({
  name: "question",
  initialState: {
    title: "",
    contents: "",
  },
  reducers: {
    saveTitle: (state, action) => {
      //action type이 loginUser일 때 실행
      state.title = action.payload; //actions에 payloadr값이 들어옴
    },
    saveContents: (state, action) => {
      state.contents = action.payload;
    },
  },
});

export const { saveTitle, saveContents } = questionSlice.actions;
export default questionSlice.reducer;
