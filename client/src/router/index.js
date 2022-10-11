import Vue from 'vue'
import VueRouter from 'vue-router';

Vue.use(VueRouter);

import modifyField from "../components/modifyField.vue"
const routes = [
    {
        path: "/",
        component: modifyField
    }

]

const router = new VueRouter({
    routes
})

export default router
