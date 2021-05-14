import Vue from "vue";
import VueRouter from "vue-router";
import TeamsName from "../views/TeamsName.vue";

Vue.use(VueRouter);

const routes = [{
        path: "/",
        name: "Login",
        component: () =>
            import ( /* webpackChunkName: "about" */ "../views/login.vue"),
        meta: {
            layout: "auth",
        },
    },
    {
        path: "/forget",
        name: "forgetpassword",
        component: () =>
            import ("../views/ForgetPass.vue"),
        meta: {
            layout: "auth",
        },
    },
    {
        path: "/dashboard",
        name: "Dashboard",
        component: () =>
            import ("../views/Dashboard.vue"),
    },
    {
        path: "/team",
        name: "TeamList",
        component: TeamsName,
    },
    {
        path: "/check-in",
        name: "Check-in",
        component: () =>
            import ("../views/Check-In_table.vue"),
    },
    {
        path: "/ot-timer",
        name: "OT-Timer",
        component: () =>
            import ("../views/Ottable.vue"),
    },
    {
        path: "/workerInfo/:id",
        name: "WorkerInfo",
        component: () =>
            import ("../views/WorkerInfo.vue"),
    },
    {
        path: "/events",
        name: "Event",
        component: () =>
            import ("../views/EventsList.vue"),
    },

];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes,
});

export default router;