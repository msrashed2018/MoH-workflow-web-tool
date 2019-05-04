import { REQUEST_TYPES, REQUEST_STATUS, REQUEST_PRICES, GOVERNATES, CITIES, OCCUPATION_TYPES, ZONES, CUSTOMS, DISABILITIES_TYPES, EQUIPMENTS_TYPES, USERS, COUNCILS, CITIZENS } from './app-words';

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

export const navItems: NavData[] = [
  {
    name:  'المواطنين',
    url: '/citizen',
    icon: 'cui-people',
    children: [
      {
        name: `بحث`,
        url: '/citizen/search',
        // icon: 'icon-star',
        icon: 'icon-star',
        // badge: {
        //   variant: 'success',
        //   text: 'NEW'
        // }
      },
      {
        name: `اضافة مواطن`,
        url: '/citizen/add',
        // icon: 'icon-star',
        icon: 'icon-star',
        // badge: {
        //   variant: 'success',
        //   text: 'NEW'
        // }
      }
    ]
    
    // badge: {
    //   variant: 'info',
    //   text: 'NEW'
    // }
    // children: [
    //     {
    //       name: 'Cards',
    //       url: '/base/cards',
    //       icon: 'icon-puzzle'
    //     },
    //     {
    //       name: 'Tooltips',
    //       url: '/base/tooltips',
    //       icon: 'icon-puzzle'
    //     }
    //   ]
  },
  // {
  //   title: true,
  //   name: 'Theme'
  // },
  // {
  //   name: 'إضافه مواطن',
  //   url: '/base/forms',
  //   icon: 'cui-user-follow'
  // },
  {
    name: 'الطلبات',
    url: '/theme/typography',
    icon: 'cui-envelope-letter'
  },
  // {
  //   title: true,
  //   name: 'Components'
  // },
  {
    name: 'الخزينه',
    url: '/base/cards',
    icon: 'cui-dollar',
    // children: [
    //   {
    //     name: 'Cards',
    //     url: '/base/cards',
    //     icon: 'icon-puzzle'
    //   },
    //   {
    //     name: 'Carousels',
    //     url: '/base/carousels',
    //     icon: 'icon-puzzle'
    //   },
    //   {
    //     name: 'Collapses',
    //     url: '/base/collapses',
    //     icon: 'icon-puzzle'
    //   },
    //   {
    //     name: 'Forms',
    //     url: '/base/forms',
    //     icon: 'icon-puzzle'
    //   },
    //   {
    //     name: 'Pagination',
    //     url: '/base/paginations',
    //     icon: 'icon-puzzle'
    //   },
    //   {
    //     name: 'Popovers',
    //     url: '/base/popovers',
    //     icon: 'icon-puzzle'
    //   },
    //   {
    //     name: 'Progress',
    //     url: '/base/progress',
    //     icon: 'icon-puzzle'
    //   },
    //   {
    //     name: 'Switches',
    //     url: '/base/switches',
    //     icon: 'icon-puzzle'
    //   },
    //   {
    //     name: 'Tables',
    //     url: '/base/tables',
    //     icon: 'icon-puzzle'
    //   },
    //   {
    //     name: 'Tabs',
    //     url: '/base/tabs',
    //     icon: 'icon-puzzle'
    //   },
    //   {
    //     name: 'Tooltips',
    //     url: '/base/tooltips',
    //     icon: 'icon-puzzle'
    //   }
    // ]
  },
  {
    name: 'اعتماد الطلبات',
    url: '/buttons',
    icon: 'icon-layers',
    // children: [
    //   {
    //     name: 'Buttons',
    //     url: '/buttons/buttons',
    //     icon: 'icon-cursor'
    //   },
    //   {
    //     name: 'Dropdowns',
    //     url: '/buttons/dropdowns',
    //     icon: 'icon-cursor'
    //   },
    //   {
    //     name: 'Brand Buttons',
    //     url: '/buttons/brand-buttons',
    //     icon: 'icon-cursor'
    //   }
    // ]
  },
  {
    name: 'النتائج',
    url: '/charts',
    icon: 'icon-pie-chart'
  },
  {
    name: 'اﻹداره',
    url: '/administration',
    icon: 'cui-briefcase',
    children: [
      {
        name: `${REQUEST_TYPES}`,
        url: '/administration/request-types',
        // icon: 'icon-star',
        icon: 'icon-star',
        // badge: {
        //   variant: 'success',
        //   text: 'NEW'
        // }
      },
      {
        name: `${REQUEST_STATUS}`,
        url: '/administration/request-status',
        icon: 'icon-star'
      },
      {
        name: `${REQUEST_PRICES}`,
        url: '/administration/request-prices',
        icon: 'icon-star',
        // badge: {
        //   variant: 'secondary',
        //   text: '4.7'
        // }
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
        url: '/administration/disability-types',
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
        name: `${COUNCILS}`,
        url: '/administration/councils',
        icon: 'icon-star'
      }
    ]
  },
  // {
  //   name: 'icons',
  //   url: '/icons',
  //   icon: 'cui-briefcase',
  //   children: [
  //     {
  //       name: `${REQUEST_TYPES}`,
  //       url: '/icons/coreui-icons',
  //       // icon: 'icon-star',
  //       icon: 'icon-star',
  //       // badge: {
  //       //   variant: 'success',
  //       //   text: 'NEW'
  //       // }
  //     },
  //     {
  //       name: `${REQUEST_STATUS}`,
  //       url: '/icons/flags',
  //       icon: 'icon-star'
  //     },
  //     {
  //       name: `${REQUEST_PRICES}`,
  //       url: '/icons/font-awesome',
  //       icon: 'icon-star',
  //       // badge: {
  //       //   variant: 'secondary',
  //       //   text: '4.7'
  //       // }
  //     },
  //     {
  //       name: `${GOVERNATES}`,
  //       url: '/icons/simple-line-icons',
  //       icon: 'icon-star'
  //     },
  //     {
  //       name: `${CITIES}`,
  //       url: '/icons/simple-line-icons',
  //       icon: 'icon-star'
  //     },
  //     {
  //       name: `${OCCUPATION_TYPES}`,
  //       url: '/icons/simple-line-icons',
  //       icon: 'icon-star'
  //     },
  //     {
  //       name: `${ZONES}`,
  //       url: '/icons/simple-line-icons',
  //       icon: 'icon-star'
  //     },
  //     {
  //       name: `${CUSTOMS}`,
  //       url: '/icons/simple-line-icons',
  //       icon: 'icon-star'
  //     },
  //     {
  //       name: `${DISABILITIES_TYPES}`,
  //       url: '/icons/simple-line-icons',
  //       icon: 'icon-star'
  //     },
  //     {
  //       name: `${EQUIPMENTS_TYPES}`,
  //       url: '/icons/simple-line-icons',
  //       icon: 'icon-star'
  //     },
  //     {
  //       name: `${USERS}`,
  //       url: '/icons/simple-line-icons',
  //       icon: 'icon-star'
  //     },
  //     {
  //       name: `${COUNCILS}`,
  //       url: '/icons/simple-line-icons',
  //       icon: 'icon-star'
  //     }
  //   ]
  // },
  // {
  //   name: 'Notifications',
  //   url: '/notifications',
  //   icon: 'icon-bell',
  //   children: [
  //     {
  //       name: 'Alerts',
  //       url: '/notifications/alerts',
  //       icon: 'icon-bell'
  //     },
  //     {
  //       name: 'Badges',
  //       url: '/notifications/badges',
  //       icon: 'icon-bell'
  //     },
  //     {
  //       name: 'Modals',
  //       url: '/notifications/modals',
  //       icon: 'icon-bell'
  //     }
  //   ]
  // },
  {
    name: 'بحث',
    url: '/widgets',
    icon: 'cui-lightbulb',
    // badge: {
    //   variant: 'info',
    //   text: 'NEW'
    // }
  },
  // {
  //   divider: true
  // },
  // {
  //   title: true,
  //   name: 'Extras',
  // },
  // {
  //   name: 'Pages',
  //   url: '/pages',
  //   icon: 'icon-star',
  //   children: [
  //     {
  //       name: 'Login',
  //       url: '/login',
  //       icon: 'icon-star'
  //     },
  //     {
  //       name: 'Register',
  //       url: '/register',
  //       icon: 'icon-star'
  //     },
  //     {
  //       name: 'Error 404',
  //       url: '/404',
  //       icon: 'icon-star'
  //     },
  //     {
  //       name: 'Error 500',
  //       url: '/500',
  //       icon: 'icon-star'
  //     }
  //   ]
  // },
  // {
  //   name: 'Disabled',
  //   url: '/dashboard',
  //   icon: 'icon-ban',
  //   badge: {
  //     variant: 'secondary',
  //     text: 'NEW'
  //   },
  //   attributes: { disabled: true },
  // },
  // {
  //   name: 'Download CoreUI',
  //   url: 'http://coreui.io/angular/',
  //   icon: 'icon-cloud-download',
  //   class: 'mt-auto',
  //   variant: 'success',
  //   attributes: { target: '_blank', rel: 'noopener' }
  // },
  // {
  //   name: 'Try CoreUI PRO',
  //   url: 'http://coreui.io/pro/angular/',
  //   icon: 'icon-layers',
  //   variant: 'danger',
  //   attributes: { target: '_blank', rel: 'noopener' }
  // }
];
