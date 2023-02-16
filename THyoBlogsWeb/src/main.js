import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/css/init.css'
import './assets/css/comm.css'
import './assets/css/animation.css'
import ElementPlus from "element-plus";
import VueParticles from 'vue-particles'
import "./utils/globalConfig"
import directives from "./directives/index"







createApp(App).use(store).use(router).use(ElementPlus).use(VueParticles).use(directives).mount('#app')
