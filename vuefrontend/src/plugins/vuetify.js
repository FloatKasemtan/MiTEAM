import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';
import colors from 'vuetify/lib/util/colors'

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: "#31517D", // #E53935
                secondary: "#31517D", // #FFCDD2
                accent: "#31517D", // #3F51B5
            },
        },
    },
});