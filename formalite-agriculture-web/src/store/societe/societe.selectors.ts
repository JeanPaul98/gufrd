import {createFeatureSelector, createSelector} from "@ngrx/store";
import {SocietesState} from "./societe.reducer";

export const selectSocietesState = createFeatureSelector<SocietesState>('societes');


export const selectSocietes = createSelector(
  selectSocietesState,
  (state: SocietesState) => {
    console.log("selectSocietes", state);
    return state.societes;
  }
);

export const selectSocietesLoading = createSelector(
  selectSocietesState,
  (state: SocietesState) => state.loading
);
