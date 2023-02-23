import { createSlice } from "@reduxjs/toolkit";

export const paramsIdSlice = createSlice({
  name: "paramsId",
  initialState: {
    id: null,
  },
  reducers: {
    paramsId: (state, action) => {
      state.id = action.payload;
    },
  },
});

export const { paramsId } = paramsIdSlice.actions;
export default paramsIdSlice.reducer;
