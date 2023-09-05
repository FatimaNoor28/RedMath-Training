import Home from "./components/Home.vue";
import Login from "./components/Login.vue";
import AddAccount from "./components/AddAccount.vue";
import AddBalance from "./components/AddBalance" 
import MakeTransaction from "./components/MakeTransaction";
import Accounts from "./components/Accounts";

import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    name: "MakeTransaction",
    component: MakeTransaction,
    path: "/makeTransaction",
  },
  {
    name: "AddAccount",
    component: AddAccount,
    path: "/addAccount",
  },
  {
    name: "AddBalance",
    component: AddBalance,
    path: "/addBalance",
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
  },
  {
    name: "Accounts",
    component: Accounts,
    path: "/viewAccounts",
  }
  
  
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
