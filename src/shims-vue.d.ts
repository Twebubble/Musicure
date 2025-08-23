/* eslint-disable */
declare module '*.vue' {
  import type { DefineComponent } from 'vue'
  const component: DefineComponent<{}, {}, any>
  export default component
}

// 扩展了 Vue 的类型定义，告诉 TypeScript Vue 实例上存在 $router 和 $route 属性。
declare module "vue/types/vue" {
  import VueRouter, { Route } from "vue-router";

  // 扩展 Vue 接口，添加两个新属性
  interface Vue {
    $router: VueRouter;
    $route: Route;
  }
}

// 定义全局接口，用于描述 API 响应的数据结构
interface ResponseBody {
  code: string;
  success: boolean;
  message: string;
  type: string;
  data?: any;
}
