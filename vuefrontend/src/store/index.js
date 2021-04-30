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
        lateEmployee: [],
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
            console.log(Date.parse(new Date().toISOString().substr(0, 10)));
        },
        SET_EVENTS(state, response) {
            state.events = response.data.events;
            console.log(state.events);
        },
        SET_OT(state, response) {
            state.OTEmployee = response.data.employees;
            console.log(state.OTEmployee);
        }
    },
    actions: {
        async loadTeamData({ commit }) {
            const response = await axios.get("/team/list");
            commit("SET_TEAMS", response)
        },
        async loadEmployeesData({ commit }) {
            const response = await axios.get('/employee/listAll?thisDate=' + Date.parse(new Date().toISOString().substr(0, 10)));
            commit("SET_EMPLOYEES", response);
        },
        async loadEventData({ commit }) {
            const response = await axios.get('/event/list');
            commit("SET_EVENTS", response);
        },
        async loadOTimerData({ commit }) {
            var m = new Date().getMonth();
            var y = new Date().getFullYear();
            const response = await axios.get('/check/ot?currentMonth=' + y + "-" + (m + 1) + "-1&nextMonth=" + y + "-" + (m + 1) + "-31");
            commit("SET_OT", response);
            console.log(response.data);
        }
    },
    modules: {},
});