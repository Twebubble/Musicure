<template>
    <div class="musicure-header">
        <!--图标-->
        <div class="header-logo" @click="goPage()">
            <musicure-icon :icon="iconList.ERJI"></musicure-icon>
            <span>{{ musicName }}</span>
        </div>
        <musicure-header-nav class="musicure-header-nav" :styleList="headerNavList" :activeName="activeNavName"
            @click="goPage"></musicure-header-nav>
        <!--搜索框-->
        <div class="header-search">
            <el-input placeholder="搜索" :prefix-icon="Search" v-model="keywords" @keyup.enter="goSearch()" />
        </div>
        <!--设置-->
        <!-- 未登录 -->
        <musicure-header-nav v-if="!token" :styleList="signList" :activeName="activeNavName"
            @click="goPage"></musicure-header-nav>
        <!-- 已登录 -->
        <el-dropdown class="user-wrap" v-if="token" trigger="click">
            <el-image class="user" fit="cover" :src="attachImageUrl(userPic)" />
            <template #dropdown>
                <el-dropdown-menu>
                    <el-dropdown-item v-for="(item, index) in menuList" :key="index"
                        @click.stop="goMenuList(item.path)">{{ item.name }}</el-dropdown-item>
                </el-dropdown-menu>
            </template>
        </el-dropdown>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref, getCurrentInstance, computed, reactive } from "vue";
import { Search } from "@element-plus/icons-vue";
import { useStore } from "vuex";
import MusicureIcon from "./MusicureIcon.vue";
import MusicureHeaderNav from "./MusicureHeaderNav.vue";
import mixin from "@/mixins/mixin";
import { HEADERNAVLIST, SIGNLIST, MENULIST, Icon, MUSICNAME, RouterName, NavName } from "@/enums";
import { HttpManager } from "@/api";

export default defineComponent({
    components: {
        MusicureIcon,
        MusicureHeaderNav,
    },
    setup() {
        const { proxy } = getCurrentInstance();
        const store = useStore();
        const { changeIndex, routerManager } = mixin();

        const musicName = ref(MUSICNAME);
        const headerNavList = ref(HEADERNAVLIST); // 左侧导航栏
        const signList = ref(SIGNLIST); // 右侧导航栏
        const menuList = ref(MENULIST); // 用户下拉菜单项
        const iconList = reactive({
            ERJI: Icon.ERJI,
        });
        const keywords = ref("");
        const activeNavName = computed(() => store.getters.activeNavName);
        const userPic = computed(() => store.getters.userPic);
        const token = computed(() => store.getters.token);

        // 参数变为可选。函数内部判断如果没有传递path和name，就跳转到首页。
        function goPage(path?: string, name?: string) {
            if (!path && !name) {
                changeIndex(NavName.Home);
                routerManager(RouterName.Home, { path: RouterName.Home });
            } else {
                changeIndex(name);
                routerManager(path, { path });
            }
        }

        function goMenuList(path) {
            if (path == RouterName.SignOut) {
                proxy.$store.commit("setToken", false);
                changeIndex(NavName.Home);
                routerManager(RouterName.Home, { path: RouterName.Home });
            } else {
                routerManager(path, { path });
            }
        }
        
        function goSearch() {
            if (keywords.value !== "") {
                proxy.$store.commit("setSearchWord", keywords.value);
                routerManager(RouterName.Search, { path: RouterName.Search, query: { keywords: keywords.value } });
            } else {
                (proxy as any).$message({
                    message: "搜索内容不能为空",
                    type: "error",
                });
            }
        }

        return {
            musicName,
            headerNavList,
            signList,
            menuList,
            iconList,
            keywords,
            activeNavName,
            userPic,
            token,
            Search,
            goPage,
            goMenuList,
            goSearch,
            attachImageUrl: HttpManager.attachImageUrl,
        };
    },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";

@media screen and (min-width: $sm) {
    .header-logo {
        margin: 0 1rem;
    }
}

@media screen and (max-width: $sm) {
    .header-logo {
        margin: 0 1rem;

        span {
            display: none;
        }
    }

    .header-search {
        display: none;
    }
}

.musicure-header {
    position: fixed;
    width: 100%;
    height: $header-height;
    line-height: $header-height;
    padding: $header-padding;
    margin: $header-margin;
    background-color: $theme-header-color;
    box-shadow: $box-shadow;
    box-sizing: border-box;
    z-index: 100;
    display: flex;
    white-space: nowrap;
    flex-wrap: nowrap;
}

/* LOGO */
.header-logo {
    font-size: $font-size-logo;
    font-weight: bold;
    cursor: pointer;

    .icon {
        // @include icon(1.9rem, $color-black);
        @include icon(1.9rem, $color-green);
        vertical-align: middle;
    }

    span {
        margin-left: 1rem;
    }
}

.musicure-header-nav {
    flex: 1;
}

/*搜索输入框*/
.header-search {
    margin: 0 20px;
    width: 100%;

    // 关键修改：让 el-input 填满整个容器
    :deep(.el-input) {
        width: 100%;
    }

    // &::v-deep input {
    //     text-indent: 10px;
    //     max-width: $header-search-max-width;
    //     min-width: $header-search-min-width;
    //     border-radius: $header-search-radius;
    //     box-shadow: none;
    //     background-color: $color-light-grey;
    //     color: $color-black;
    // }
    :deep(.el-input__inner) {
        text-indent: 10px;
        width: 100%; // 确保输入框宽度100%
        border-radius: $header-search-radius;
        box-shadow: none;
        // background-color: $color-light-grey;
        color: $color-black;
        // 移除 max-width 和 min-width 限制
        max-width: none;
        min-width: 0;
    }
}

/*用户相关*/
.user-wrap {
    position: relative;
    display: flex;
    align-items: center;

    .user {
        width: $header-user-width;
        height: $header-user-width;
        border-radius: $header-user-radius;
        margin-right: $header-user-margin;
        cursor: pointer;
    }
}
</style>
