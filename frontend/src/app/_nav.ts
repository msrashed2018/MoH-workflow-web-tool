import { REQUEST_TYPES, REQUEST_STATUS, REQUEST_PRICES, GOVERNATES, CITIES, OCCUPATION_TYPES, ZONES, CUSTOMS, DISABILITIES_TYPES, EQUIPMENTS_TYPES, USERS, COUNCILS, CITIZENS, COMMITTEE_MEMBERS, COMMITTEES, EYE_MEASURES, EYE_REVEAL_SETTINGS, REQUESTS, PAYMENTS, REVIEW_REQUESTS, SYSTEM_ADMINISTRATION, CONTINUE_REGISTERING, BONES_REVEAL, EYE_REVEAL, MEDICAL_REVEAL_REGISTRATION, APPROVE_REQUESTS, AUDITS, DOCUMENT_TYPES } from './app-words';

interface NavAttributes {
  [propName: string]: any;
}
interface NavWrapper {
  attributes: NavAttributes;
  element: string;
}
interface NavBadge {
  text: string;
  variant: string;
}
interface NavLabel {
  class?: string;
  variant: string;
}

export interface NavData {
  name?: string;
  url?: string;
  icon?: string;
  badge?: NavBadge;
  title?: boolean;
  children?: NavData[];
  variant?: string;
  attributes?: NavAttributes;
  divider?: boolean;
  class?: string;
  label?: NavLabel;
  wrapper?: NavWrapper;
}

export const citizenNavItems: NavData[] = [
  {
    name:  `${CITIZENS}`,
    url: '/citizen/search',
    icon: 'cui-people',
  }
 ]
export const cashierNavItems: NavData[] = [
 {
    name: `${PAYMENTS}`,
    url: '/request/payments',
    icon: 'cui-dollar',
  }
]
export const eyeNavItems: NavData[] = [
  {
    name: `${EYE_REVEAL}`,
    url: '/request/eye-reveal',
    icon: 'cui-dollar',
  }
 ]
export const bonesNavItems: NavData[] = [
  {
    name: `${BONES_REVEAL}`,
    url: '/request/bones-reveal',
    icon: 'cui-dollar',
  }
 ]

 export const continueRegisteringNavItems: NavData[] = [
  {
    name: `${CONTINUE_REGISTERING}`,
    url: '/request/continue-registering',
    icon: 'cui-dollar',
  }
 ]

 export const medicalRegisteringNavItems: NavData[] = [
  {
    name: `${MEDICAL_REVEAL_REGISTRATION}`,
    url: '/request/medical-reveals',
    icon: 'cui-dollar',
  }
 ]
 export const reviewNavItems: NavData[] = [
  {
    name: `${REVIEW_REQUESTS}`,
    url: '/request/review-requests',
    icon: 'icon-layers',
  }
 ]

 export const approveNavItems: NavData[] = [
  {
    name: `${APPROVE_REQUESTS}`,
    url: '/request/approve-requests',
    icon: 'icon-layers',
  }
 ]



export const adminNavItems: NavData[] = [
  {
    name:  `${CITIZENS}`,
    url: '/citizen/search',
    icon: 'cui-people',
  },
  
  {
    name: `${REQUESTS}`,
    url: '/request/search',
    icon: 'cui-envelope-letter'
  },
  {
    name: `${PAYMENTS}`,
    url: '/request/payments',
    icon: 'cui-dollar',
  },
  {
    name: `${CONTINUE_REGISTERING}`,
    url: '/request/continue-registering',
    icon: 'cui-dollar',
  },
  {
    name: `${BONES_REVEAL}`,
    url: '/request/bones-reveal',
    icon: 'cui-dollar',
  },
  {
    name: `${EYE_REVEAL}`,
    url: '/request/eye-reveal',
    icon: 'cui-dollar',
  },
  {
    name: `${MEDICAL_REVEAL_REGISTRATION}`,
    url: '/request/medical-reveals',
    icon: 'cui-dollar',
  },
  
  {
    name: `${REVIEW_REQUESTS}`,
    url: '/request/review-requests',
    icon: 'icon-layers',
  },
  
  {
    name: `${APPROVE_REQUESTS}`,
    url: '/request/approve-requests',
    icon: 'icon-layers',
  },
  {
    name: 'النتائج',
    url: '/theme/typography',
    icon: 'icon-pie-chart'
  },
  {
    name: `${SYSTEM_ADMINISTRATION}`,
    url: '/administration',
    icon: 'cui-briefcase',
    children: [
      {
        name: `${COMMITTEE_MEMBERS}`,
        url: '/administration/committee-members',
        icon: 'icon-star'
      },
      {
        name: `${COMMITTEES}`,
        url: '/administration/committees',
        icon: 'icon-star'
      },
      {
        name: `${REQUEST_TYPES}`,
        url: '/administration/types',
        icon: 'icon-star'
      },
      {
        name: `${REQUEST_STATUS}`,
        url: '/administration/request-status',
        icon: 'icon-star'
      },
      {
        name: `${EYE_REVEAL_SETTINGS}`,
        url: '/administration/eye-reveal-settings',
        icon: 'icon-star'
      },
      {
        name: `${EYE_MEASURES}`,
        url: '/administration/eye-measures',
        icon: 'icon-star'
      },
      {
        name: `${GOVERNATES}`,
        url: '/administration/governates',
        icon: 'icon-star'
      },
      {
        name: `${CITIES}`,
        url: '/administration/cities',
        icon: 'icon-star'
      },
      {
        name: `${OCCUPATION_TYPES}`,
        url: '/administration/occupations',
        icon: 'icon-star'
      },
      {
        name: `${ZONES}`,
        url: '/administration/zones',
        icon: 'icon-star'
      },
      {
        name: `${CUSTOMS}`,
        url: '/administration/customs',
        icon: 'icon-star'
      },
      {
        name: `${DISABILITIES_TYPES}`,
        url: '/administration/disabilities',
        icon: 'icon-star'
      },
      {
        name: `${EQUIPMENTS_TYPES}`,
        url: '/administration/equipments',
        icon: 'icon-star'
      },
      {
        name: `${USERS}`,
        url: '/administration/users',
        icon: 'icon-star'
      },
      {
        name: `${DOCUMENT_TYPES}`,
        url: '/administration/document-types',
        icon: 'icon-star'
      },
      {
        name: `${AUDITS}`,
        url: '/administration/audits',
        icon: 'icon-star'
      }
    ]
  },
];
