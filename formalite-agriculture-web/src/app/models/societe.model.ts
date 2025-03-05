export interface SocieteModel {
  id: number;
  raisonSociale: string;
  numRccm: string;
  numeroEnreg: string;
  adresse: string;
  contact: string;
  nif: string;
  email: string;
  typeSociete: TypeSocieteModel;
}




export interface TypeSocieteModel {
  id: number;
  libelle: string;
  ref: string;
}


