<template>
    <el-container>
        <el-header>
            <!-- 页头 -->
            <musicure-header></musicure-header>
        </el-header>
        <el-main>
            <!-- 路由视图 -->
            <router-view />
            <!-- 播放列表 -->
            <musicure-current-play></musicure-current-play>
            <!-- 页面底部的播放控制区 -->
            <musicure-play-bar></musicure-play-bar>
            <!-- 回到顶部 -->
            <musicure-scroll-top></musicure-scroll-top>
            <!-- 接收音乐并播放的位置 -->
            <musicure-audio></musicure-audio>
        </el-main>
        <el-footer>
            <!-- 页脚 -->
            <musicure-footer></musicure-footer>
        </el-footer>
    </el-container>
</template>

<script lang="ts" setup>
import { getCurrentInstance } from "vue";
import MusicureHeader from "@/components/layouts/MusicureHeader.vue";
import MusicureCurrentPlay from "@/components/layouts/MusicureCurrentPlay.vue";
import MusicurePlayBar from "@/components/layouts/MusicurePlayBar.vue";
import MusicureScrollTop from "@/components/layouts/MusicureScrollTop.vue";
import MusicureFooter from "@/components/layouts/MusicureFooter.vue";
import MusicureAudio from "@/components/layouts/MusicureAudio.vue";

// 获取当前组件实例
const { proxy } = getCurrentInstance();

// ===页面状态持久化===

// =页面刷新后，从 sessionStorage 恢复状态=
// 检查 sessionStorage 中是否有保存的 Vuex 状态（键为 "dataStore"），
// 如果有，将其解析并与当前 store 状态合并，使用 replaceState 方法替换整个状态。
if (sessionStorage.getItem("dataStore")) {
    proxy.$store.replaceState(Object.assign({}, proxy.$store.state, JSON.parse(sessionStorage.getItem("dataStore"))));
}

// =在页面卸载前保存状态=
// 添加一个事件监听器，在页面即将卸载（关闭或刷新）前触发，
// 将当前 Vuex 状态保存到 sessionStorage 中，以便下次访问时可以恢复，
// 使用 beforeunload 事件确保状态在页面关闭前被保存。
window.addEventListener("beforeunload", () => {
    sessionStorage.setItem("dataStore", JSON.stringify(proxy.$store.state));
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";

.el-container {
    // 父元素高度的100%减去60像素
    min-height: calc(100% - 60px);
}

.el-header {
    padding: 0;
}

.el-main {
    padding-left: 0;
    padding-right: 0;
    // 不设置上下，保留框架的默认值
}
</style>
