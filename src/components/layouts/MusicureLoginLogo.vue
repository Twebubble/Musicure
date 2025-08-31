<template>
    <div class="login-logo">
        <!-- <musicure-icon :icon="icon"></musicure-icon> -->
        <el-image class="logo-img" fit="contain" :src="require('@/assets/images/logo.png')" />
    </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import MusicureIcon from "./MusicureIcon.vue";
import { Icon } from "@/enums";

export default defineComponent({
    components: {
        // MusicureIcon,
    },
    data() {
        return {
            icon: Icon.ERJI,
        };
    },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";

.login-logo {
    // background-color: $color-blue-light;
    height: calc(100vh - $header-height - $footer-height);
    min-width: 50vw;
    overflow: hidden;
    @include layout(center, center);

    // .icon {
    //     @include icon(36rem, $color-blue-dark);
    //     transform: rotate(-30deg);
    // }
    .logo-img {
        width: 100%;
        align-self: flex-start;
    }
}

@media screen and (min-width: $sm) {
    .login-logo {
        width: 50vw;
    }
}

@media screen and (max-width: $sm) {
    .login-logo {
        width: 100vw;
    }
}
</style>
