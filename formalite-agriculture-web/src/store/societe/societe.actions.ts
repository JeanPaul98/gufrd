import {createActionGroup, emptyProps, props} from '@ngrx/store';
import {SocieteModel} from "../../app/models/societe.model";


export const societeActions = createActionGroup({
  source: 'Societes',
  events: {
    'Get All': emptyProps(),
    'Get All Success': props<{ societes: SocieteModel[] }>(),
    'Get All Failure': props<{ error: string }>(),
  }
});
