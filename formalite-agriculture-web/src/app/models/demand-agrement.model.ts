import {SocieteModel} from "./societe.model";
import {TypeAgrementModel} from "./type-agrement.model";

export interface CreateAgrementModel {
  numero: string;
  activite: string;
  observation: string;
  dateAgrement: string,
  dateExpiration: string,
  idSociete: string,
  nomSociete: string;
  societe: SocieteModel,
  idAgrement: string,
  nomAgrement: string;
  agrement: TypeAgrementModel,
  compteClient: string;
}
