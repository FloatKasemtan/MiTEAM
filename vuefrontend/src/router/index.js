import Vue from 'vue'
import VueRouter from 'vue-router'
import TeamsName from '../views/TeamsName.vue'

Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'Login',
        component: () =>
            import ( /* webpackChunkName: "about" */ '../views/login.vue'),
        meta: {
            layout: 'auth'
        }
    },
    {
        path: '/team',
        name: 'TeamList',
        component: TeamsName
    },
    {
        path: '/check-in',
        name: 'Check-in',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import ( /* webpackChunkName: "about" */ '../views/Check-In_table.vue')
    },
    {
        path: '/ot-timer',
        name: 'OT-Timer',
        component: () =>
            import ( /* webpackChunkName: "about" */ '../views/Ottable.vue')
    },
    {
        path: '/userInfo',
        name: 'Info',
        component: () =>
            import ( /* webpackChunkName: "about" */ '../views/WorkerInfo.vue')
    },
    {
        path: '/calendar',
        name: 'Carendar',
        component: () =>
            import ( /* webpackChunkName: "about" */ '../views/calendar.vue')
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router