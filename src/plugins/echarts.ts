import type { App } from 'vue';
import * as echarts from 'echarts';

export default {
    install: (app: App) => {
        // 将 echarts 挂载到全局属性
        app.config.globalProperties.$echarts = echarts;
        
        // 或者提供一个注入方法
        app.provide('echarts', echarts);
    }
};