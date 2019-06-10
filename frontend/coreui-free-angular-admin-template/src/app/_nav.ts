import { REQUEST_TYPES, REQUEST_STATUS, REQUEST_PRICES, GOVERNATES, CITIES, OCCUPATION_TYPES, ZONES, CUSTOMS, DISABILITIES_TYPES, EQUIPMENTS_TYPES, USERS, COUNCILS, CITIZENS, COMMITTEE_MEMBERS, COMMITTEES, EYE_MEASURES, EYE_REVEAL_SETTINGS, REQUESTS, PAYMENTS, REQUESTS_REVIEWER, SYSTEM_ADMINISTRATION } from './app-words';

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
export const adminNavItems: NavData[] = [
  
  {
    name: 'ادارة النظام',
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
      }
    ]
  }
]
export const navItems: NavData[] = [
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
    name: `${REQUESTS_REVIEWER}`,
    url: '/theme/typography',
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
      }
    ]
  },
];
