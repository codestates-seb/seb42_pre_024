import { configureStore } from "@reduxjs/toolkit";
import questionSlice from "./questionSlice";
import editSlice from "./editSlice";
import paramsIdSlice from "./paramsId.Slice";
import userIdSlice from "./userIdSlice";

const store = configureStore({
  reducer: {
    question: questionSlice,
    edit: editSlice,
    paramsId: paramsIdSlice,
    userId: userIdSlice,
  },
});

export default store;
