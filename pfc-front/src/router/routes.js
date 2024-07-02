const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      { path: "", component: () => import("pages/IndexPage.vue") },
      {
        path: "reservation",
        name: "ReservationPage",
        component: () => import("pages/ReservationPage.vue"),
      },
      {
        path: "my-reservations",
        name: "MyReservations",
        component: () => import("pages/MyReservations.vue"),
      },
    ],
  },
  {
    path: "/error",
    name: "ErrorPage",
    component: () => import("pages/GenericErrorPage.vue"),
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
