<template>
    <div class="personal">
        <div class="personal-info">
            <div class="personal-img" @click="dialogTableVisible = true">
                <el-image  fit="cover" :src="attachImageUrl(userPic)" />
            </div>
            <div class="personal-msg">
                <div class="username">{{ personalInfo.username }}</div>
                <div class="introduction">{{ personalInfo.introduction }}</div>
            </div>
            <el-button class="edit-info" round :icon="Edit" @click="goPage()">修改个人信息</el-button>
        </div>
        <div class="personal-body">
            <!-- 展示用户收藏的歌曲，当歌曲列表变化时重新加载数据 -->
            <song-list :songList="collectSongList" :show="true" @changeData="changeData"></song-list>
        </div>
        <el-dialog v-model="dialogTableVisible" title="修改头像">
            <upload></upload>
        </el-dialog>
    </div>
</template>

<script lang="ts">
import { defineComponent, nextTick, ref, computed, watch, reactive } from "vue";
import { useStore } from "vuex";
import { Edit } from "@element-plus/icons-vue";
import SongList from "@/components/SongList.vue";
import Upload from "../setting/Upload.vue";
import mixin from "@/mixins/mixin";
import { HttpManager } from "@/api";
import { RouterName } from "@/enums";

export default defineComponent({
    components: {
        SongList,
        Upload,
    },
    setup() {
        const store = useStore();

        const { routerManager } = mixin();

        const dialogTableVisible = ref(false);
        const collectSongList = ref([]); // 收藏的歌曲
        const personalInfo = reactive({
            username: "",
            userSex: "",
            birth: "",
            location: "",
            introduction: "",
        });
        const userId = computed(() => store.getters.userId);
        const userPic = computed(() => store.getters.userPic);

        // 监听用户头像变化，当头像更新后自动关闭对话框
        watch(userPic, () => {
            dialogTableVisible.value = false;
        });

        function goPage() {
            routerManager(RouterName.Setting, { path: RouterName.Setting });
        }

        async function getUserInfo(id) {
            const result = (await HttpManager.getUserOfId(id)) as ResponseBody;
            personalInfo.username = result.data[0].username;
            personalInfo.userSex = result.data[0].sex;
            personalInfo.birth = result.data[0].birth;
            personalInfo.introduction = result.data[0].introduction;
            personalInfo.location = result.data[0].location;
        }

        // 获取收藏的歌曲
        async function getCollection(userId) {
            collectSongList.value = []
            const result = (await HttpManager.getCollectionOfUser(userId)) as ResponseBody;
            const collectIDList = result.data || []; // 存放收藏的歌曲ID
            // 通过歌曲ID获取歌曲信息
            for (const item of collectIDList) {
                if (!item.songId) {
                    console.error(`歌曲${item}异常`);
                    continue;
                }

                const result = (await HttpManager.getSongOfId(item.songId)) as ResponseBody;
                collectSongList.value.push(result.data[0]);
            }
        }

        // 重新加载收藏歌曲数据
        function changeData() {
            getCollection(userId.value);
        }

        // 在组件挂载(DOM)后获取用户信息和收藏歌曲列表
        nextTick(() => {
            getUserInfo(userId.value);
            getCollection(userId.value);
        });

        return {
            Edit,
            userPic,
            dialogTableVisible,
            collectSongList,
            personalInfo,
            attachImageUrl: HttpManager.attachImageUrl,
            goPage,
            changeData,
        };
    }
})
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";

.personal {
    padding-top: $header-height + 150px;

    // 使用伪元素创建顶部背景色块
    &::before {
        content: "";
        background-color: $color-blue-shallow;
        position: absolute;
        top: 0;
        width: 100%;
        height: $header-height + 150px;
    }
}

.personal-info {
    position: relative;
    margin-bottom: 60px;

    .personal-img {
        height: 200px;
        width: 200px;
        border-radius: 50%; // 圆形头像
        border: 5px solid $color-white; // 白色边框
        position: absolute;
        top: -180px;
        left: 50px;
        cursor: pointer;
        overflow: hidden;
        // 确保 el-image 填满容器
        .el-image {
            width: 100%;
            height: 100%;
            img {
                width: 100%;
                height: 100%;
                object-fit: cover;
                object-position: center center; // 确保图片居中显示
            }
        }
    }

    .personal-msg {
        margin-left: 300px;
        position: absolute;
        top: -120px;

        .username {
            font-size: 50px;
            font-weight: 600;
        }

        .introduction {
            font-size: 20px;
            font-weight: 500;
        }
    }

    // 修改按钮定位在右侧
    .edit-info {
        position: absolute;
        right: 10vw;
        margin-top: -120px;
    }
}

// 响应式设计

@media screen and (min-width: $sm) {
    .personal-body {
        padding: 0px 100px;
    }
}

@media screen and (max-width: $sm) {
    .edit-info {
        display: none;
    }
}
</style>
