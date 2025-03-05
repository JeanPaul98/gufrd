export interface ProductModel {
  id: number,
  quantite: number,
  unite: string,
  code: string,
  fournisseur: string,
  libelle: string,
  conteneur: string,
  nomProduit: number,
  nombreCarton: number,
  nombreColis: number,
  provenance: string,
  poidNets: number,
  descriptionEnvoi: string,
  origine: string,
  dateProduction: string,
  datePrevueInspection: string,
  nomDemandeur: string,
  age: string,
  paysExpediteur: string,
  professionDemandeur: string,
  lieuExpedition: string,
  nomImportateur:string,
  lieuInspection: string,
  produit: ProduitSimpleModel,
  description: string,
  espece: string,
  conditionnement: string,
  natureProduit: string,
  race: string,
  sexe: string,
  nombre: number,
  poidsTotal: number,

  typeProduit: {
    code: string,
    libelle: string,
    ref: string,
  }

}

export interface ProductNewModel {
  libelle: string,
  description: string,
  typeProduit: number,
}

export  interface ProduitSimpleModel {
    libelle: string,
    nomProduit: string,
    code: string,
    description: string,
}

export interface ProductObjModel {
  quantite: number,
  unite: string,
  poidNets: number,
  produit: {
    libelle: string,
    nomProduit: string,
    code: string,
    description: string,
  },
  nombreCarton: number,
  nombreColis: number,
  descriptionEnvoi: string,
  fournisseur: string,
  origine: string,
  nomImportateur:string,
  provenance:string,
  conteneur: string,
  dateProduction: string,
  espece: string,
  conditionnement: string,
  natureProduit: string,
  race: string,
  description: string,
  sexe: string,
  libelle: string,
  datePrevueInspection: string,
  nomDemandeur: string,
  professionDemandeur: string,
  lieuExpedition: string,
  lieuInspection: string,
  nombre: number,
  poidsTotal: number,


  typeProduit: {
    code: string,
    libelle: string,
    ref: string,
  }
}
