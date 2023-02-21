import { configureStore } from "@reduxjs/toolkit";
import questionSlice from "./questionSlice";
import userSlice from "./userSlice";
import editSlice from "./editSlice";

const store = configureStore({
  reducer: { question: questionSlice, user: userSlice, edit: editSlice },
});

export default store;
