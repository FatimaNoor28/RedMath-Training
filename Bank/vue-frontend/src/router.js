import Home from "./components/Home.vue";
import Login from "./components/Login.vue";
import AddAccount from "./components/AddAccount.vue";
import AddBalance from "./components/AddBalance" 

import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    name: "AddAccount",
    component: AddAccount,
    path: "/addAccount",
  },
  {
    name: "Login",
    component: Login,
    path: "/login",
  },
  {
    name: "Home",
    component: Home,
    path: "/",
  }
  
  
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
