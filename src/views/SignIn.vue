<template>
    <div class="page-container">
        <!-- 背景图片 -->
        <el-image class="bg" fit="cover" :src="require('@/assets/images/shu4.png')"></el-image>
        <!-- 内容区域 -->
        <div class="content-wrapper">
            <musicure-login-logo></musicure-login-logo>
            <div class="sign">
                <div class="sign-head">
                    <span>帐号登录</span>
                </div>
                <el-form ref="signInForm" status-icon :model="registerForm" :rules="SignInRules">
                    <el-form-item prop="username">
                        <el-input placeholder="用户名" v-model="registerForm.username"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input type="password" placeholder="密码" v-model="registerForm.password"
                            @keyup.enter="handleLoginIn"></el-input>
                    </el-form-item>
                    <el-form-item class="sign-btn">
                        <el-button @click="handleSignUp">注册</el-button>
                        <el-button type="primary" @click="handleLoginIn">登录</el-button>
                        <el-button @click="handleForgotPassword">忘记密码</el-button>
                        <el-button @click="handleEmail">邮箱登录</el-button>
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

        // 登录用户名密码
        const registerForm = reactive({
            username: "",
            password: "",
        });

        async function handleLoginIn() {
            let canRun = true;
            (proxy.$refs["signInForm"] as any).validate((valid) => {
                if (!valid) return (canRun = false);
            });
            if (!canRun) return;


            try {
                const username = registerForm.username;
                const password = registerForm.password;
                const result = (await HttpManager.signIn({ username, password })) as ResponseBody;
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

        // 路由导航函数
        function handleSignUp() {
            routerManager(RouterName.SignUp, { path: RouterName.SignUp });
        }

        function handleForgotPassword() {
            routerManager(RouterName.ForgotPassword, { path: RouterName.ForgotPassword });
        }

        function handleEmail() {
            routerManager(RouterName.loginByemail, { path: RouterName.loginByemail });
        }


        return {
            registerForm,
            SignInRules,
            handleLoginIn,
            handleForgotPassword,
            handleEmail,
            handleSignUp,
        };
    },
})
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
