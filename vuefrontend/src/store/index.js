import Vue from "vue";
import Vuex from "vuex";
import axios from '@/axios/axios'
Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        userData: {},
        teams: [],
        events: [{
                name: "event1",
                startDate: "03/18/2021",
                deadLine: "04/8/2021",
                Team: "Apple",
                finish: true,
            },
            {
                name: "event2",
                startDate: "03/18/2021",
                deadLine: "04/9/2021",
                Team: "Apple",
                finish: false,
            },
            {
                name: "event3",
                startDate: "03/18/2021",
                deadLine: "04/10/2021",
                Team: "Apple",
                finish: true,
            },
            {
                name: "event4",
                startDate: "03/18/2021",
                deadLine: "04/20/2021",
                Team: "Apple",
                finish: true,
            },
            {
                name: "event5",
                startDate: "03/18/2021",
                deadLine: "04/20/2021",
                Team: "Apple",
                finish: false,
            },
        ],
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

    },
    actions: {
        async loadUser() {
            const response = await axios.get("/account/load");
            console.log(response.data);
            this.state.userData = response.data.userDetail;
        },
        async loadTeamData() {
            const response = await axios.get("/team/list");
            console.log(response.data);
            this.state.teams = response.data.teams;
        },
        async loadEmployeesData() {
            const response = await axios.get('/employee/listAll');
            console.log(response.data)
        }
    },
    modules: {},
});