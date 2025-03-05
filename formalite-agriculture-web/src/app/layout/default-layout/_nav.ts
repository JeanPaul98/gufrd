import {INavData} from '@coreui/angular';

export interface INavDataModel extends INavData {
  description?: string;
  code?: string;
  children?: INavDataModel[];
}

export const navItems: INavData[] = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    iconComponent: {name: 'cil-speedometer'}
  },
  {
    title: true,
    name: 'Importation'
  },
  {
    name: 'Exportation',
    title: true
  },
  {
    name: 'Inspection phytosanitaire',
    url: '/base',
    iconComponent: {name: 'cil-puzzle'},
    children: [
      {
        name: 'Produits Pharmaceutiques',
        url: '/base/accordion',

      },
      {
        name: 'Breadcrumbs',
        url: '/base/breadcrumbs',

      },
      {
        name: 'Cards',
        url: '/base/cards',

      },
      {
        name: 'Carousel',
        url: '/base/carousel',

      },
      {
        name: 'Collapse',
        url: '/base/collapse',

      },
      {
        name: 'List Group',
        url: '/base/list-group',

      },
      {
        name: 'Navs & Tabs',
        url: '/base/navs',

      },
      {
        name: 'Pagination',
        url: '/base/pagination',

      },
      {
        name: 'Placeholder',
        url: '/base/placeholder',

      },
      {
        name: 'Popovers',
        url: '/base/popovers',

      },
      {
        name: 'Progress',
        url: '/base/progress',

      },
      {
        name: 'Spinners',
        url: '/base/spinners',

      },
      {
        name: 'Tables',
        url: '/base/tables',

      },
      {
        name: 'Tabs',
        url: '/base/tabs',

      },
      {
        name: 'Tooltips',
        url: '/base/tooltips',

      }
    ]
  },
  // {
  //   name: 'Buttons',
  //   url: '/buttons',
  //   iconComponent: { name: 'cil-cursor' },
  //   children: [
  //     {
  //       name: 'Buttons',
  //       url: '/buttons/buttons',
  //
  //     },
  //     {
  //       name: 'Button groups',
  //       url: '/buttons/button-groups',
  //
  //     },
  //     {
  //       name: 'Dropdowns',
  //       url: '/buttons/dropdowns',
  //
  //     }
  //   ]
  // },
  // {
  //   name: 'Forms',
  //   url: '/forms',
  //   iconComponent: { name: 'cil-notes' },
  //   children: [
  //     {
  //       name: 'Form Control',
  //       url: '/forms/form-control',
  //
  //     },
  //     {
  //       name: 'Select',
  //       url: '/forms/select',
  //
  //     },
  //     {
  //       name: 'Checks & Radios',
  //       url: '/forms/checks-radios',
  //
  //     },
  //     {
  //       name: 'Range',
  //       url: '/forms/range',
  //
  //     },
  //     {
  //       name: 'Input Group',
  //       url: '/forms/input-group',
  //
  //     },
  //     {
  //       name: 'Floating Labels',
  //       url: '/forms/floating-labels',
  //
  //     },
  //     {
  //       name: 'Layout',
  //       url: '/forms/layout',
  //
  //     },
  //     {
  //       name: 'Validation',
  //       url: '/forms/validation',
  //
  //     }
  //   ]
  // },
  // {
  //   name: 'Charts',
  //   iconComponent: { name: 'cil-chart-pie' },
  //   url: '/charts'
  // },
  // {
  //   name: 'Icons',
  //   iconComponent: { name: 'cil-star' },
  //   url: '/icons',
  //   children: [
  //     {
  //       name: 'CoreUI Free',
  //       url: '/icons/coreui-icons',
  //       ,
  //       badge: {
  //         color: 'success',
  //         text: 'FREE'
  //       }
  //     },
  //     {
  //       name: 'CoreUI Flags',
  //       url: '/icons/flags',
  //
  //     },
  //     {
  //       name: 'CoreUI Brands',
  //       url: '/icons/brands',
  //
  //     }
  //   ]
  // },
  // {
  //   name: 'Notifications',
  //   url: '/notifications',
  //   iconComponent: { name: 'cil-bell' },
  //   children: [
  //     {
  //       name: 'Alerts',
  //       url: '/notifications/alerts',
  //
  //     },
  //     {
  //       name: 'Badges',
  //       url: '/notifications/badges',
  //
  //     },
  //     {
  //       name: 'Modal',
  //       url: '/notifications/modal',
  //
  //     },
  //     {
  //       name: 'Toast',
  //       url: '/notifications/toasts',
  //
  //     }
  //   ]
  // },
  // {
  //   name: 'Widgets',
  //   url: '/widgets',
  //   iconComponent: { name: 'cil-calculator' },
  //   badge: {
  //     color: 'info',
  //     text: 'NEW'
  //   }
  // },
  // {
  //   title: true,
  //   name: 'Extras'
  // },
  // {
  //   name: 'Pages',
  //   url: '/login',
  //   iconComponent: { name: 'cil-star' },
  //   children: [
  //     {
  //       name: 'Login',
  //       url: '/login',
  //
  //     },
  //     {
  //       name: 'Register',
  //       url: '/register',
  //
  //     },
  //     {
  //       name: 'Error 404',
  //       url: '/404',
  //
  //     },
  //     {
  //       name: 'Error 500',
  //       url: '/500',
  //
  //     }
  //   ]
  // },
  // {
  //   title: true,
  //   name: 'Links',
  //   class: 'mt-auto'
  // },
  // {
  //   name: 'Docs',
  //   url: 'https://coreui.io/angular/docs/5.x/',
  //   iconComponent: { name: 'cil-description' },
  //   attributes: { target: '_blank' }
  // }
];

export const customNavItems: INavDataModel[] = [
  // {
  //   name: 'Dashboard',
  //   url: '/dashboard',
  //   code: 'dashb',
  //   description: 'Dashboard',
  //   iconComponent: {name: 'cil-speedometer'}
  // },
  {
    name: 'Agrement',
    url: '/agrement',
    code: 'agrement',
    description: 'Agrement',
    iconComponent: {name: 'cil-puzzle'}
  },
  {
    name: 'Societe',
    url: '/society',
    code: 'soci',
    description: 'Societe',
    iconComponent: {name: 'cil-building'}
  },
  {
    name: 'IMPORTATION',
    title: true
  },
  {
    name: 'Inspection phytosanitaire',
    iconComponent: {name: 'cil-puzzle'},
    children: [
      {
        name: 'Navires',
        url: '/importations/ipn',
        code: 'ipn',
        description: 'Inspection phytosanitaire de navire',
      },
      {
        name: 'Cargaisons & Containers',
        url: '/importations/ipcc',
        code: 'ipcc',
        description: 'Inspection phytosanitaire de cargaisons et containers',
      },
      {
        name: 'Produits Pharmaceutiques',
        url: '/importations/ippp',
        code: 'ippp',
        description: 'Inspection phytosanitaire de produits phyto pharmaceutiques',
      },
    ],
  },
  {
    name: 'Autorisation',
    iconComponent: {name: 'cil-puzzle'},
    children: [
      {
        name: 'Import Animaux vivants',
        url: '/importations/aiav',
        code: 'aiav',
        description: 'Autorisation d’importation d’animaux vivants',
      },
      {
        name: 'Dépotage & de transit',
        url: '/importations/adt',
        code: 'adt',
        description: 'Autorisation de dépotage et de transit',
      },
      {
        name: 'Enlev. Aliments pour animaux',
        url: '/importations/aeaa',
        code: 'aeaa',
        description: 'Autorisation d\'enlèvement d\'aliment pour Animaux',
      },
      {
        name: 'Import Produit Cons. Local',
        url: '/importations/aipcl',
        code: 'aipcl',
        description: 'Autorisation d\'importation de produits en consommation locale',
      },
      {
        name: 'Import Enlev Medicmt Veterin.',
        url: '/importations/aiemv',
        code: 'aiemv',
        description: 'Autorisation d\'importation et d\'enlèvement de médicaments vétérinaires',
      },
    ],
  },
  {
    name: 'EXPORTATION',
    title: true
  },
  {
    name: 'Inspection',
    url: '/base',
    iconComponent: {name: 'cil-puzzle'},
    children: [
      /*{
        name: 'Obtention certf. phytosan.',
        url: '/exportations/ocep',
        code: 'ocep',
        description: 'Inspection phytosanitaire pour l’obtention de certificat phytosanitaire',
      },*/
      {
        name: 'Obtention certf. sanit. animaux',
        url: '/exportations/iocav',
        code: 'iocav',
        description: 'Inspection pour l’obtention de certificat sanitaire pour animaux vivants',
      },
    ],
  },
  {
    name: 'Certificat',
    iconComponent: {name: 'cil-puzzle'},
    children: [
  /*    {
        name: 'Certif. phytosan.',
        url: '/exportations/eccp',
        code: 'eccp',
        description: 'Etablissement de certificat phytosanitaire',
      },*/
      /*{
        name: 'Phytosanit. fruit & légumes',
        url: '/exportations/ecpfl',
        code: 'ecpfl',
        description: 'Etablissement de certificat phytosanitaire pour les fruits et légumes',
      },*/

      {
        name: 'Sanitaire pour cire',
        url: '/exportations/cspc',
        code: 'cspc',
        description: 'Certificat sanitaire pour cire',
      },
      {
        name: 'Sanitaire pr cuirs & peaux',
        url: '/exportations/cscp',
        code: 'cscp',
        description: 'Certificat sanitaire pour cuirs et peaux',
      }
    ],
  },
/*  {
    name: 'Ctrl. ravitaillmt. à bord navires',
    url: '/exportations/crbn',
    code: 'crbn',
    description: 'Contrôle de ravitaillement à bord de navires',
  }*/
]

export const navPhytosanitaireItems: INavDataModel[] = [
  // {
  //   name: 'Dashboard',
  //   url: '/dashboard',
  //   code: 'dashb',
  //   description: 'Dashboard',
  //   iconComponent: {name: 'cil-speedometer'}
  // },
  {
    name: 'IMPORTATION',
    title: true
  },
  {
    name: 'Inspection phytosanitaire',
    iconComponent: {name: 'cil-puzzle'},
    children: [
      {
        name: 'Navires',
        url: '/importations/ipn',
        code: 'ipn',
        description: 'Inspection phytosanitaire de navire',
      },
      {
        name: 'Cargaisons & Containers',
        url: '/importations/ipcc',
        code: 'ipcc',
        description: 'Inspection phytosanitaire de cargaisons et containers',
      },
      {
        name: 'Produits Pharmaceutiques',
        url: '/importations/ippp',
        code: 'ippp',
        description: 'Inspection phytosanitaire de produits phyto pharmaceutiques',
      },
    ],
  },
  ]

export const navVeterinaireItems: INavDataModel[] = [
  // {
  //   name: 'Dashboard',
  //   url: '/dashboard',
  //   code: 'dashb',
  //   description: 'Dashboard',
  //   iconComponent: {name: 'cil-speedometer'}
  // },
  {
    name: 'IMPORTATION',
    title: true
  },
  {
    name: 'Autorisation',
    iconComponent: {name: 'cil-puzzle'},
    children: [
      {
        name: 'Import Animaux vivants',
        url: '/importations/aiav',
        code: 'aiav',
        description: 'Autorisation d’importation d’animaux vivants',
      },
      {
        name: 'Dépotage & de transit',
        url: '/importations/adt',
        code: 'adt',
        description: 'Autorisation de dépotage et de transit',
      },
      {
        name: 'Enlev. Aliments pour animaux',
        url: '/importations/aeaa',
        code: 'aeaa',
        description: 'Autorisation d\'enlèvement d\'aliment pour Animaux',
      },
      {
        name: 'Import Produit Cons. Local',
        url: '/importations/aipcl',
        code: 'aipcl',
        description: 'Autorisation d\'importation de produits en consommation locale',
      },
      {
        name: 'Import Enlev Medicmt Veterin.',
        url: '/importations/aiemv',
        code: 'aiemv',
        description: 'Autorisation d\'importation et d\'enlèvement de médicaments vétérinaires',
      },
    ],
  },
  {
    name: 'EXPORTATION',
    title: true
  },
  {
    name: 'Inspection',
    url: '/base',
    iconComponent: {name: 'cil-puzzle'},
    children: [
      /*{
        name: 'Obtention certf. phytosan.',
        url: '/exportations/ocep',
        code: 'ocep',
        description: 'Inspection phytosanitaire pour l’obtention de certificat phytosanitaire',
      },*/
      {
        name: 'Obtention certf. sanit. animaux',
        url: '/exportations/iocav',
        code: 'iocav',
        description: 'Inspection pour l’obtention de certificat sanitaire pour animaux vivants',
      },
    ],
  },
  {
    name: 'Certificat',
    iconComponent: {name: 'cil-puzzle'},
    children: [
      /*    {
            name: 'Certif. phytosan.',
            url: '/exportations/eccp',
            code: 'eccp',
            description: 'Etablissement de certificat phytosanitaire',
          },*/
      /*{
        name: 'Phytosanit. fruit & légumes',
        url: '/exportations/ecpfl',
        code: 'ecpfl',
        description: 'Etablissement de certificat phytosanitaire pour les fruits et légumes',
      },*/

      {
        name: 'Sanitaire pour cire',
        url: '/exportations/cspc',
        code: 'cspc',
        description: 'Certificat sanitaire pour cire',
      },
      {
        name: 'Sanitaire pr cuirs & peaux',
        url: '/exportations/cscp',
        code: 'cscp',
        description: 'Certificat sanitaire pour cuirs et peaux',
      }
    ],
  },
  /*  {
      name: 'Ctrl. ravitaillmt. à bord navires',
      url: '/exportations/crbn',
      code: 'crbn',
      description: 'Contrôle de ravitaillement à bord de navires',
    }*/
]
