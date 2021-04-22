import Vue from "vue";
import Vuex from "vuex";
import axios from '@/axios/axios'
Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        userData: {},
        teams: [],
        employees: [],
        events: [],
        lateEmployee: [{
                team: "Frozen Yogurt",
                name: "John",
                contact: "@gmail.com",
                in: new Date(1618562483000),
                out: new Date(),
            },
            {
                team: "Gingerbread",
                name: "Mary",
                contact: "@gmail.com",
                in: 9.0,
                out: 16.55,
            },
            {
                team: "Frozen Yogurt",
                name: "Sea",
                contact: "@gmail.com",
                in: 9.0,
                out: 18.09,
            },
            {
                team: "Gingerbread",
                name: "Pete",
                contact: "@gmail.com",
                in: 9.0,
                out: 17.59,
            },
            {
                team: "Gingerbread",
                name: "Evan",
                contact: "@gmail.com",
                in: 9.0,
                out: 20,
            },
            {
                team: "Frozen Yogurt",
                name: "Isac",
                contact: "@gmail.com",
                in: 9.0,
                out: 18,
            },
            {
                team: "Frozen Yogurt",
                name: "Jess",
                contact: "@gmail.com",
                in: 9.0,
                out: 16,
            },
            {
                team: "KitKat",
                name: "Oven",
                contact: "@gmail.com",
                in: 9.0,
                out: 17,
            },
            {
                team: "Donut",
                name: "Tibb",
                contact: "@gmail.com",
                in: 9.0,
                out: 17,
            },
            {
                team: "KitKat",
                name: "Pop",
                contact: "@gmail.com",
                in: 9.0,
                out: 17,
            },
            {
                team: "Frozen Yogurt",
                name: "Isac",
                contact: "@gmail.com",
                in: 9.0,
                out: 17,
            },
            {
                team: "Frozen Yogurt",
                name: "Jess",
                contact: "@gmail.com",
                in: 9.0,
                out: 17,
            },
            {
                team: "KitKat",
                name: "Oven",
                contact: "@gmail.com",
                in: 9.0,
                out: 17,
            },
            {
                team: "Donut",
                name: "Tibb",
                contact: "@gmail.com",
                in: 9.0,
                out: 17,
            },
            {
                team: "KitKat",
                name: "Pop",
                contact: "@gmail.com",
                in: 9.0,
                out: 17,
            },
        ],
        OTEmployee: [{
                team: "Frozen Yogurt",
                name: "John",
                contact: "@gmail.com",
                time: 10,
            },
            {
                team: "Gingerbread",
                name: "Mary",
                contact: "@gmail.com",
                time: 0,
            },

            {
                team: "Frozen Yogurt",
                name: "John",
                contact: "@gmail.com",
                time: 15,
            },
            {
                team: "Gingerbread",
                name: "Mary",
                contact: "@gmail.com",
                time: 3,
            },
            {
                team: "Frozen Yogurt",
                name: "Sea",
                contact: "@gmail.com",
                time: 5,
            },
            {
                team: "Gingerbread",
                name: "Pete",
                contact: "@gmail.com",
                time: 6,
            },
            {
                team: "Gingerbread",
                name: "Evan",
                contact: "@gmail.com",
                time: 8,
            },
            {
                team: "Frozen Yogurt",
                name: "Isac",
                contact: "@gmail.com",
                time: 1,
            },
            {
                team: "Frozen Yogurt",
                name: "Jess",
                contact: "@gmail.com",
                time: 9,
            },
            {
                team: "KitKat",
                name: "Oven",
                contact: "@gmail.com",
                time: 20,
            },
            {
                team: "Donut",
                name: "Tibb",
                contact: "@gmail.com",
                time: 10,
            },
            {
                team: "KitKat",
                name: "Pop",
                contact: "@gmail.com",
                time: 19,
            },
        ],
        items: [],
    },
    getters: {

    },
    mutations: {
        SET_TEAMS(state, response) {
            state.teams = response.data.teams;
            console.log(state.teams);
        },
        SET_EMPLOYEES(state, response) {
            state.employees = response.data.employees;
            console.log(state.employees);
        },
        SET_EVENTS(state, response) {
            state.events = response.data.events;
            console.log(state.events);
        },
    },
    actions: {
        async loadTeamData({ commit }) {
            const response = await axios.get("/team/list");
            commit("SET_TEAMS", response)
        },
        async loadEmployeesData({ commit }) {
            const response = await axios.get('/employee/listAll');
            commit("SET_EMPLOYEES", response);
        },
        async loadEventData({ commit }) {
            const response = await axios.get('/event/list');
            commit("SET_EVENTS", response);
        },
    },
    modules: {},
});