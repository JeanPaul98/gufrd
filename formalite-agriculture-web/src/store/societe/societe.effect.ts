import {inject} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {switchMap} from "rxjs";
import {map} from "rxjs/operators";
import {SocietyService} from "../../app/modules/society/services/society.service";
import {societeActions} from "./societe.actions";

export const societesGetAll$ = createEffect(
  (action$ = inject(Actions), societyService = inject(SocietyService)) => {
    return action$.pipe(
      ofType(societeActions.getAll),
      switchMap((action) => societyService.getSocietyCreated()),
      map((societes) => {
        console.log("societesGetAll$", societes);
        return societeActions.getAllSuccess({societes});
      })
    )
  },
  {functional: true}
);
