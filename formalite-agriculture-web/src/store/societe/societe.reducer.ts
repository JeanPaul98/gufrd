import {createFeature, createReducer, createSelector, on} from "@ngrx/store";
import {SocieteModel} from "../../app/models/societe.model";
import {societeActions} from "./societe.actions";

export interface SocietesState {
  societes: SocieteModel[];
  loading: boolean;
  error: string | null;
}

const societesInitialState: SocietesState = {
  societes: [],
  loading: true,
  error: null
};

export const societesReducer = createReducer(
  societesInitialState,
    on(societeActions.getAll, (state) => {
      console.log("societeActions.getAll", state);
      return ({
        ...state,
        loading: true,
        error: null
      })
    }),
    on(societeActions.getAllSuccess, (state, {societes}) => ({
      ...state,
      societes,
      loading: false,
      error: null
    })),
    on(societeActions.getAllFailure, (state, {error}) => ({
      ...state,
      societes: [],
      loading: false,
      error
    })),

)

export const societesFeature = createFeature({
  name: 'societes',
  reducer: societesReducer,
  extraSelectors: ({selectSocietes}) => {
    return {
      selectTestSocietes: createSelector(
        selectSocietes,
        (societes) => societes.filter(societe => societe.email === 'test@test.com')
      )
    }
  }
})
