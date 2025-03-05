export interface IpRequest {
  atp: string;
  nomNavire: string;
  imo: string;
  affreteur: string;
  nomDemandeur: string;
  professionDemandeur: string;
  adresseDemandeur: string;
  lieuInspection: string;
  bl:string,
  datePrevueInspection?: Date;
  detPhytosanitaireProduitDtos: DetPhytosanitaireProduitDto[];
  idFormalite: number;
  numGenerer: string;
  typeInspPhyto: string;
  refTypeInspectionPhyto: string;
  compteClient: string;
}

export interface DetPhytosanitaireProduitDto {
  quantite: number;
  produit: ProductModel;
  fournisseur: string;
  origine: string;
  conteneur: string;
  descriptionEnvoi: string;
  nombreColis: number;
  code: string;

}

export interface ProductModel {
  code: string;
  libelle: string;
  nomProduit: string,
  description: string,
}

export interface ProductRequestModel {
  libelle: string;
  quantite: number;
  fournisseur: string;
  origine: string;
  conteneur: string;
  descriptionEnvoi: string;
  nombreColis: number;
  code: string;
}

export const inits: IpRequest = {
  adresseDemandeur: "",
  affreteur: "",
  atp: "",
  bl: "",
  datePrevueInspection: undefined,
  detPhytosanitaireProduitDtos: [],
  imo: "",
  lieuInspection: "",
  nomDemandeur: "",
  nomNavire: "",
  professionDemandeur: "",
  idFormalite: 0,
  numGenerer: "",
  typeInspPhyto: "",
  refTypeInspectionPhyto: "",
  compteClient: "",
};
