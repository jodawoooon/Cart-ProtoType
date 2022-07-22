import { createRouter, createWebHistory } from "vue-router";

import Cart from "@/views/cart/Cart";
import Product from "@/views/product/Product";
import NotFoundVue from "@/components/NotFoundVue";

const routes = [
  { path: "", redirect: "/shop/product" },
  { path: "/", redirect: "/shop/product" },
  {
    path: "/shop/cart",
    name: "Cart",
    component: Cart,
  },
  {
    path: "/shop/product",
    name: "Product",
    component: Product,
  },
  {
    path: "/notfound",
    name: "NotFound",
    component: NotFoundVue,
  },
  {
    path: "/:pathMatch(.*)*",
    redirect: "/notfound",
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
