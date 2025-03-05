import {AgreementModel} from "./agrement.model";
import {ListFilesModel} from "./list-files.model";
import {NavireInfoModel} from "./navire-info.model";
import {ProductObjModel} from "./product.model";

export interface DemandAeaaModel {
  numeroAgrement: string;
  raisonSocial: string;
  activite: string;
  nif: string;
  idProcessVerbal:number,
  numeroDossier: string;
  compteClient: string;
  agrement: AgreementModel;
  dateEmbarquement: string;
  atp: string,
  nomSociete: string,
  numGenerer: string,
  nomNavire: string,
  imo: string,
  immo: string,
  affreteur: string,
  structureDemandeur: string,
  numeroAutorisation: string,
  numeroBL: string,
  urlPaiement: string,
  chaine: string,
  dateProduction: string,
  nomDemandeur: string,
  dateDemande: string,
  dateSoumission: string,
  dateTraitement: string,
  dateRejet: string,
  dateAccepte: string,
  etat: string,
  motif: string,
  motifRejet:string,
  nomImportateur:string,
  fournisseur: string,
  descriptionEnvoi: string,
  idFormalite: number,
  conteneur: string,
  provenance: string,
  datearrivee: string,
  idAutorisation: number,
  detAutorisationProduitDtos: ProductObjModel[],
  detCertificatProduitDtos: ProductObjModel[],
  detPhytosanitaireProduitDtos: ProductObjModel[],
  typeAutorisation: string,
  refTypeAutorisation: string,
  montantRedevance: number,
  statuPaiement: string,
  statutPaiement: string,
  pieceJointeList: ListFilesModel[],
  lieuExpedition: string;
  moyenTransport: string;
  destinataire: string;
  traitement: string;
  professionDemandeur: string;
  expediteur: string;
  identification: string;
  adresseDemandeur: string;
  lieuInspection: string;
  datePrevueInspection: string;
  nombreColis: number;
  adresseExpediteur: string,
  adresseDestinataire: string,
  lieuDestination: string,
  lieuDeChargement: string,
  paysExpediteur: string,
  paysOrigine: string,
  lieuOrigine: string,
  paysDestination: string,
  nomDestinataire: string,
  lieuChargement: string,
  dateDepart: string,
  posteFrontalier: string,
}


export interface DemandAeaaSimpleModel extends NavireInfoModel {
  detAutorisationProduitDtos: ProductObjModel[],

}

export interface DemandAeaaCompletModel extends Omit<DemandAeaaModel, "detAutorisationProduitDtos"> {
  detAutorisationProduitDtos: ProductObjModel[],
  detPhytosanitaireProduitDtos: ProductObjModel[],

}


