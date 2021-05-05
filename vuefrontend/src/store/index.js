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
        OTEmployee: [],
        items: [],
        testGIt: "sdass"
    },
    getters: {

    },
    mutations: {
        SET_TEAMS(state, response) {
            state.teams = response.data.teams;
        },
        SET_EMPLOYEES(state, response) {
            state.employees = response.data.employees;
        },
        SET_EVENTS(state, response) {
            state.events = response.data.events;
        },
        SET_OT(state, response) {
            state.OTEmployee = response.data.employees;
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
            const response = await axios.get('/check/ot?currentMonth=' + y + "-" + (m + 1) + "-1&nextMonth=" + y + "-" + (m + 1) + "-30");
            commit("SET_OT", response);
        }
    },
    modules: {},
});