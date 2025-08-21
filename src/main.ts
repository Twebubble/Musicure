import { createApp } from 'vue'
import ElementPlus from "element-plus";
import App from './App.vue'
import router from './router'
import store from './store'
import "element-plus/dist/index.css"

// assets中的一些内容没加上

createApp(App).use(store).use(router).mount('#app')
