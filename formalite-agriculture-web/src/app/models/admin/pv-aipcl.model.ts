export interface PVAIPCLModel {
  id: number;
  idFormalite: number;
  datearrivee: string;
  provenance: string;
  via: string;
  consignataire: string;
  dateInspection: string;
  dateDepartPrevue: string;
  partieNavireVisitee: string;
  officierNavire: string;
  lieuInspection: string;
  commandant: string;
  nomNavire: string;
  affreteur: string;
  datePv: string;
  remarque: string;
  agentPV: string;
  detPvProduitDtoList: PVProduitModel[];
  detPVInspecteurDtos: PVInspecteurModel[];
}

export interface PVProduitModel {
  codProduit: string;
  quantite: number;
  origine: string;
  nomProduit: string;
  descriptionEnvoie: string;
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



