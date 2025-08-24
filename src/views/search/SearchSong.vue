<template>
    <div class="search-song">
        <song-list :songList="currentSongList"></song-list>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref, computed, watch, onMounted, getCurrentInstance } from "vue";
import { useStore } from "vuex";
import SongList from "@/components/SongList.vue";
import { HttpManager } from "@/api";

export default defineComponent({
    components: {
        SongList,
    },

    setup() {
        const { proxy } = getCurrentInstance();
        const store = useStore();

        const currentSongList = ref([]); // 存放的音乐，创建了一个响应式引用，初始值为空数组
        //创建一个计算属性，从 Vuex store 中获取搜索关键词
        const searchWord = computed(() => store.getters.searchWord);
        // 监听搜索关键词的变化，并在变化时执行搜索函数
        watch(searchWord, (value) => {
            searchSong(value);
        });

        // 搜索音乐
        async function searchSong(value) {
            if (!value) {
                currentSongList.value = [];
                return;
            }
            const result = (await HttpManager.getSongOfSingerName(value)) as ResponseBody;
            if (!result.data.length) {
                currentSongList.value = [];
                (proxy as any).$message({
                    message: "暂时没有相关歌曲",
                    type: "warning",
                });
            } else {
                currentSongList.value = result.data;
            }
        }

        onMounted(() => {
            searchSong(proxy.$route.query.keywords);
        });

        return {
            currentSongList,
        };
    }
})
</script>