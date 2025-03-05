import {NavireInfoModel} from "./navire-info.model";
import {ProductObjModel} from "./product.model";


export interface DemandiocavModel {
    atp: string,
    numGenerer: string,
    nomNavire: string,
    imo: string,
    immo: string,
    affreteur: string,
    libelle: string,
    urlPaiement: string,
    chaine: string,
    code: string,
    dateProduction: string,
    nomDemandeur: string,
    dateDemande: string,
    dateSoumission: string,
    lieuExpedition: string,
    dateTraitement: string,
    dateRejet: string,
    dateAccepte: string,
    etat: string,
    motif: string,
    provenance: string;
    portDestination: string;
    dateembarquement: string;
    compteClient: string;
    moyenTransport: string;
    destinataire: string;
    traitement: string;
    professionDemandeur: string;

    fournisseur: string,
    descriptionEnvoi: string,
    idFormalite: number,
    conteneur: string,
    datearrivee: string,
    idAutorisation: number,
    quantite: number,
    detCertificatProduitDtos: ProductObjModel[],
    typeAutorisation: string,
    refTypeAutorisation: string,
    montantRedevance: number,
    paysExpediteur: string,
    paysOrigine: string,
    lieuOrigine: string,
    paysDestination: string,
    nomDestinataire: string,
    lieuChargement: string,
    dateDepart: string,
    posteFrontalier: string,
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


    nombreCarton: number,
    origine: string,
    nomImportateur:string,
    espece: string,
    conditionnement: string,
    natureProduit: string,
    race: string,
    description: string,
    sexe: string,
    nombre: number,
  }
  

  export interface DemandiocavSimpleModel extends NavireInfoModel {
    detCertificatProduitDtos: ProductObjModel[],
  }


  export interface DemandiocavCompletModel extends Omit<DemandiocavModel, "detCertificatProduitDtos"> {
    detCertificatProduitDtos: ProductObjModel[],
  }