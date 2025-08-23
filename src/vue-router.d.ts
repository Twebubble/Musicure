// ComponentPublicInstance……上不存在属性“$router”。
import { Router } from 'vue-router';
import { RouteLocationNormalizedLoaded } from 'vue-router';

declare module '@vue/runtime-core' {
    interface ComponentCustomProperties {
        $router: Router;
        $route: RouteLocationNormalizedLoaded;
    }
}

export {};