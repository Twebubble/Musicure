import { getCurrentInstance, computed } from "vue";
import { useStore } from "vuex";
import { LocationQueryRaw } from "vue-router";
import { RouterName } from "@/enums";
import { HttpManager } from "@/api";
import axios from 'axios'

// TypeScript 接口，为路由导航操作提供类型安全的参数规范。
interface routerOptions {
    path?: string;
    query?: LocationQueryRaw;
}

export default function () {
    const { proxy } = getCurrentInstance();

    const store = useStore();

    const token = computed(() => store.getters.token);

    function getUserSex(sex) {
        if (sex === 0) {
            return "女";
        } else if (sex === 1) {
            return "男";
        }
    }

    // 获取歌曲名
    function getSongTitle(str) {
        return str.split("-")[1];
    }

    // 获取歌手名
    function getSingerName(str) {
        return str.split("-")[0];
    }

    // 判断登录状态
    function checkStatus(status?: boolean) {
        if (!token.value) {
            if (status !== false)
                (proxy as any).$message({
                    message: "请先登录",
                    type: "warning",
                });
            return false;
        }
        return true;
    }

    // 播放
    function playMusic({ id, url, pic, index, name, lyric, currentSongList }) {
        const songTitle = getSongTitle(name);
        const singerName = getSingerName(name);
        proxy.$store.dispatch("playMusic", {
            id,
            url,
            pic,
            index,
            songTitle,
            singerName,
            lyric,
            currentSongList,
        });
    }
}