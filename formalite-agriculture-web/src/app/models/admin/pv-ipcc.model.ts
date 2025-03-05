export interface PVIPCCModel {
  id: number;
  idFormalite: number;
  pointEntree: string;
  lieuInspection: string;
  dateApplication: string;
  datearrivee: string;
  datePv: string;
  dateInspection: string;
  expediteur: string;
  destinataire: string;
  numeroEnregistrement: string;
  agentPv: string;
  controleTechnique: string;
  resultatInspection: string;
  remarque: string;
  detPvProduitDtos: PVProduitModel[];
  detTraitementProduitDtos: PVTraitementProduitModel[];
  detPVInspecteurDtos: PVInspecteurModel[];

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

export interface PVTraitementProduitModel {
  produit: ProduitModel;
  nomProduit: string;
  substanceActive: string;
  temperature: string;
  nomSociete: string;
  natureTraitement: string;
  heureDebTrait: string;
  concentration: string;
  duree: number;
}

export interface ProduitModel {
  code: string;
  libelle: string;
  description: string;
}

export interface PVInspecteurModel {
  fonction: string;
  idInspecteur: number;
  nomInspecteur: string;
}



