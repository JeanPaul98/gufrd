## A-)  IPN (Inspection Phytosanitaire de Navires)

### 1. Champs manquantes:
- `dateSoumission`  
- `dateTraitement`  
- `dateRejet`
- `compteClient` (on aura besoins du nom et prenoms ou raison sociale)

### 2. Listes des fichiers non disponibles pour détails et validation

### 3. Numéro de demande trop long


## B-)  IPCC (Inspection Phytosanitaire de Containers & Cargaisons)

### 1. Champs manquantes:
- `nomNavire`
- `dateSoumission`
- `dateTraitement`
- `dateRejet`   
- `compteClient`  (on aura besoins du nom et prenoms ou raison sociale)

exemple `listeSoumise ipcc`:

```json
{
  "idFormalite": 181,
  "numGenerer": "PHYPHA1726161479138",
  "atp": "ATP00000668",
  "nomNavire": null,
  "imo": "9410260",
  "affreteur": "OLIVIER",
  "chaine": null,
  "montantRedevance": 10000,
  "dateDemande": "2024-09-12T17:17:59.142+00:00",
  "dateSoumission": null,
  "dateTraitement": null,
  "dateRejet": null,
  "dateAccepte": null,
  "etat": "SOUMIS",
  "nomDemandeur": "KATOH",
  "professionDemandeur": null,
  "adresseDemandeur": null,
  "lieuInspection": null,
  "datePrevueInspection": null,
  "idPhytosanitaire": null,
  "detPhytosanitaireProduitDtos": [
    {
      "quantite": 5,
      "produit": {
        "code": "ZAAAA",
        "libelle": "RIZ LOCAL",
        "description": "RIZ A CONSOMMATION LOCAL"
      },
      "fournisseur": "AJAX",
      "origine": "TOGO",
      "conteneur": null,
      "descriptionEnvoi": null,
      "nombreColis": 0
    }
  ],
  "typeInspectionPhyto": "Inspection phytosanitaire (Cargaison et conteneur)",
  "refTypeInspectionPhyto": null,
  "compteClient": null
}
```

### 2. Listes des fichiers non disponibles pour détails et validation

### 3. Numéro de demande trop long

