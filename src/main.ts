import { createApp } from 'vue'
import ElementPlus from "element-plus";
import App from './App.vue'
import router from './router'
import store from './store'
import "element-plus/dist/index.css"

// assets中的一些内容没加上

// 有一个import没加
import { Store } from "vuex";

declare module "@vue/runtime-core" {
    interface State {
        count: number;
    }

    interface ComponentCustomProperties {
        $store: Store<State>;
    }
}

createApp(App).use(store).use(router).use(ElementPlus).mount("#app");
