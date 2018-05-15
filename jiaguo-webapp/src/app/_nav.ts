export const Navigation = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    icon: 'icon-speedometer',
    badge: {
      variant: 'info',
      text: 'NEW'
    }
  },
  // {
  //   name: 'Admin',
  //   url: '/admin',
  //   icon: 'icon-settings',
  //   children: [
      {
        name: 'Users',
        url: '/admin/users',
        icon: 'icon-user'
      },
  //     {
  //       name: 'Roles',
  //       url: '/admin/roles',
  //       icon: 'icon-people'
  //     },
  //   ]
  // },
  {
    name: 'Login',
    url: '/account/login',
    icon: 'icon-login'
  }
];
