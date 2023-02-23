import { configureStore } from "@reduxjs/toolkit";
import questionSlice from "./questionSlice";
import userSlice from "./userSlice";
import editSlice from "./editSlice";
import paramsIdSlice from "./paramsId.Slice";

const store = configureStore({
  reducer: {
    question: questionSlice,
    user: userSlice,
    edit: editSlice,
    paramsId: paramsIdSlice,
  },
});

export default store;
