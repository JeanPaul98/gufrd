import {SocieteModel} from "./societe.model";
import {TypeAgrementModel} from "./type-agrement.model";

export interface AgreementModel {
  numero: string,
  activite: string,
  raisonSocialSociete: string,
  dateAgreement: string,
}


export interface DemandAgrementModel {
  id: number,
  numero: string,
  activite: string,
  observation: string,
  etat: string,
  statutAgrement: string,
  dateDemande: string,
  dateAgrement: string,
  agrement: TypeAgrementModel,
  dateExpiration: string,
  dateAnnulation: string,
  dateRejet: string,
  dateAccepte: string,
  dateSoumise: string,
  dateTraiter: string,
  compteClient: string,
  compteAgentAccepter: string,
  compteAgentRejet: string,
  compteAgentTraiter: string,
  renouveler: boolean,
  createdAt: string,
  updateAt: string,
  societe: SocieteModel,
}



