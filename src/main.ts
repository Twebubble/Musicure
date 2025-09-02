import { createApp } from 'vue'
import ElementPlus from "element-plus";
import * as echarts from 'echarts';
import App from './App.vue'
import router from './router'
import store from './store'
import "element-plus/dist/index.css"
import "./assets/css/index.scss";
import "./assets/icons/index.js";

// 导入 ECharts 插件
import ECharts from './plugins/echarts';

// 有一个import没加
import { Store } from "vuex";
import { ComponentCustomProperties } from "vue";

declare module "@vue/runtime-core" {
    interface State {
        count: number;
    }

    interface ComponentCustomProperties {
        $store: Store<State>;
        $echarts: typeof echarts; // 添加 ECharts 类型声明
    }
}

createApp(App).use(store).use(router).use(ElementPlus).use(ECharts).mount("#app");
