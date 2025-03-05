
export interface PVIPPPModel {
  id:number;
  idFormalite: number;
  datearrivee: Date;
  via: string;
  dateInspection: Date;
  dateDepartPrevue: Date;
  partieNavireVisitee: string;
  officierNavire: string;
  lieuInspection: string;
  commandant: string;
  datePv: Date;
  remarque: string;
  agentPV: string;
  expediteur: string;
  destinataire: string;
  detPvProduitDtoList: PVProduitModel[];
  detPVInspecteurDtos: PVInspecteurModel[];
  agrementSocieteImportatrice: string;
  homologationProduit: string;
  resultatInspection: string;
}

export interface PVProduitModel {
  codProduit: string;
  quantite: number;
  origine: string;
  nomProduit: string;
  emplacement: string;
  mesure: string;
  moyenTransport: string;
  matiereActive: string;
}

export interface PVInspecteurModel {
  fonction: string;
  idInspecteur: number;
  nomInspecteur: string;
}




