import {NavireInfoModel} from "./navire-info.model";
import {ProductObjModel} from "./product.model";

export interface DemandIpnModel {
  atp: string,
  numGenerer: string,
  nomNavire: string,
  imo: string,
  immo: string,
  affreteur: string,
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
  fournisseur: string,
  descriptionEnvoi: string,
  idFormalite: number,
  conteneur: string,
  provenance: string,
  datearrivee: string,
  idAutorisation: number,
  detPhytosanitaireProduitDtos: ProductObjModel[],
  detCertificatProduitDtos: ProductObjModel[],
  typeAutorisation: string,
  refTypeAutorisation: string,
  montantRedevance: number,
  compteClient: string,
  moyenTransport: string,
  lieuExpedition: string,
  identification: string,
  traitement: string,
  expediteur: string,
  destinataire: string,
}


export interface DemandAeaaSimpleModel extends NavireInfoModel {
  detAutorisationProduitDtos: ProductObjModel[],
}

export interface DemandAeaaCompletModel extends Omit<DemandIpnModel, "detAutorisationProduitDtos"> {
  detAutorisationProduitDtos: ProductObjModel[],
  detPhytosanitaireProduitDtos: ProductObjModel[],

}


