<template>
    <div class="search-song-list">
        <play-list :playList="playList" path="song-sheet-detail"></play-list>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref, computed, watch, onMounted, getCurrentInstance } from "vue";
import { useStore } from "vuex";
import PlayList from "@/components/PlayList.vue";
import { HttpManager } from "@/api";

export default defineComponent({
    components: {
        PlayList,
    },
    setup() {
        const { proxy } = getCurrentInstance();
        const store = useStore();

        const playList = ref([]);   // 存储搜索到的歌单列表
        const searchWord = computed(() => store.getters.searchWord);    // 从 Vuex 获取搜索关键词
        // 当搜索关键词改变时，自动调用 getSearchList 函数获取新的搜索结果
        watch(searchWord, (value) => {
            getSearchList(value);
        });

        async function getSearchList(value) {
            if (!value) return;
            const result = (await HttpManager.getSongListOfLikeTitle(value)) as ResponseBody;
            if (!result.data.length) {
                (proxy as any).$message({
                    message: "暂无该歌曲内容",
                    type: "warning",
                });
            } else {
                // 有搜索结果，更新歌单列表
                playList.value = result.data;
            }
        }

        // 在组件挂载时，从路由查询参数中获取初始关键词并执行搜索
        // 这使得页面可以直接通过 URL 参数进行搜索（如 / search ? keywords = 流行）
        onMounted(() => {
            getSearchList(proxy.$route.query.keywords);
        });

        return {
            playList,
        };
    },
});
</script>
