<template>
    <div class="page-container">
        <!-- 背景图片 -->
        <el-image class="bg" fit="cover" :src="require('@/assets/images/shu4.png')"></el-image>
        <!-- 内容区域 -->
        <div class="content-wrapper">
            <musicure-login-logo></musicure-login-logo>
            <div class="sign">
                <div class="sign-head">
                    <span>邮箱登录</span>
                </div>
                <el-form ref="signInForm" status-icon :model="registerForm" :rules="SignInRules">
                    <el-form-item prop="email">
                        <el-input placeholder="邮箱" v-model="registerForm.email"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input type="password" placeholder="密码" v-model="registerForm.password"
                            @keyup.enter="handleLoginIn"></el-input>
                    </el-form-item>
                    <el-form-item class="sign-btn">
                        <el-button type="primary" @click="handleLoginIn">登录</el-button>
                        <el-button type="primary" @click="handleLoginCancel">取消</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent, reactive, getCurrentInstance } from "vue";
import mixin from "@/mixins/mixin";
import MusicureLoginLogo from "@/components/layouts/MusicureLoginLogo.vue";
import { HttpManager } from "@/api";
import { NavName, RouterName, SignInRules } from "@/enums";

export default defineComponent({
    components: {
        MusicureLoginLogo,
    },
    setup() {
        const { proxy } = getCurrentInstance();
        const { routerManager, changeIndex } = mixin();

        // 登录
        const registerForm = reactive({
            email: "",
            password: "",
        });

        async function handleLoginCancel() {
            routerManager(RouterName.SignIn, { path: RouterName.SignIn });
        }

        async function handleLoginIn() {
            let canRun = true;
            (proxy.$refs["signInForm"] as any).validate((valid) => {
                if (!valid) return (canRun = false);
            });
            if (!canRun) return;


            try {
                const email = registerForm.email;
                const password = registerForm.password;
                const result = (await HttpManager.signInByemail({ email, password })) as ResponseBody;
                (proxy as any).$message({
                    message: result.message,
                    type: result.type,
                });

                if (result.success) {
                    proxy.$store.commit("setUserId", result.data[0].id);
                    proxy.$store.commit("setUsername", result.data[0].username);
                    proxy.$store.commit("setUserPic", result.data[0].avator);
                    proxy.$store.commit("setToken", true);
                    changeIndex(NavName.Home);
                    routerManager(RouterName.Home, { path: RouterName.Home });
                }
            } catch (error) {
                console.error(error);
            }
        }

        return {
            registerForm,
            SignInRules,
            handleLoginIn,
            handleLoginCancel,
        };
    },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/sign.scss";

.page-container {
    position: relative;
    width: 100%;
    height: 100vh;
    overflow: hidden;

    .bg {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        z-index: -1; // 确保背景在最底层

        // 如果需要添加遮罩效果
        &::after {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(255, 255, 255, 0.5); // 10%透明度的白色遮罩
        }
    }

    .content-wrapper {
        position: relative;
        z-index: 1; // 确保内容在背景之上
        display: flex;
        flex-direction: column;
        justify-content: center;
        // align-items: center;
        min-height: 100vh;
        padding: 20px;
    }
}
</style>
