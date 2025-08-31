<template>
    <div class="page-container">
        <!-- 背景图片 -->
        <el-image class="bg" fit="cover" :src="require('@/assets/images/shu4.png')"></el-image>
        <!-- 内容区域 -->
        <div class="content-wrapper">
            <musicure-login-logo></musicure-login-logo>
            <div class="sign">
                <div class="sigh-head">
                    <span>用户注册</span>
                </div>
                <el-form ref="signUpForm" label-width="70px" status-icon :model="registerForm" :rules="SignUpRules">
                    <el-form-item prop="username" label="用户名">
                        <el-input v-model="registerForm.username" placeholder="用户名"></el-input>
                    </el-form-item>
                    <el-form-item prop="password" label="密码">
                        <el-input type="password" placeholder="密码" v-model="registerForm.password"></el-input>
                    </el-form-item>
                    <el-form-item prop="sex" label="性别">
                        <el-radio-group v-model="registerForm.sex">
                            <el-radio :label="0">女</el-radio>
                            <el-radio :label="1">男</el-radio>
                            <el-radio :label="2">保密</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item prop="phoneNum" label="手机">
                        <el-input placeholder="手机" v-model="registerForm.phoneNum"></el-input>
                    </el-form-item>
                    <el-form-item prop="email" label="邮箱">
                        <el-input v-model="registerForm.email" placeholder="邮箱"></el-input>
                    </el-form-item>
                    <el-form-item prop="birth" label="生日">
                        <el-date-picker type="date" placeholder="选择日期" v-model="registerForm.birth"
                            style="width: 100%"></el-date-picker>
                    </el-form-item>
                    <el-form-item prop="introduction" label="签名">
                        <el-input type="textarea" placeholder="签名" v-model="registerForm.introduction"></el-input>
                    </el-form-item>
                    <el-form-item prop="location" label="地区">
                        <el-select v-model="registerForm.location" placeholder="地区" style="width: 100%">
                            <el-option v-for="item in AREA" :key="item.value" :label="item.label"
                                :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item class="sign-btn">
                        <el-button @click="goBackRegist">取消</el-button>
                        <!-- <el-button type="primary" @click="handleSignUp(formRef)">确定</el-button> -->
                        <el-button type="primary" @click="handleSignUp">确定</el-button>
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
import { getBirth } from "@/utils";
import { AREA, RouterName, NavName, SignUpRules } from "@/enums";

export default defineComponent({
    components: {
        MusicureLoginLogo,
    },
    setup() {
        const { proxy } = getCurrentInstance();
        
        const { routerManager, changeIndex } = mixin();

        const registerForm = reactive({
            username: "",
            password: "",
            sex: "",
            phoneNum: "",
            email: "",
            birth: new Date(),
            introduction: "",
            location: "",
        });

        async function goBackRegist() {
            routerManager(RouterName.SignIn, { path: RouterName.SignIn });
        }

        async function handleSignUp() {
            let canRun = true;
            (proxy.$refs["signUpForm"] as any).validate((valid) => {
                if (!valid) return (canRun = false);
            });
            if (!canRun) return;

            try {
                const username = registerForm.username;
                const password = registerForm.password;
                const sex = registerForm.sex;
                const phoneNum = registerForm.phoneNum;
                const email = registerForm.email;
                const birth = registerForm.birth;
                const introduction = registerForm.introduction;
                const location = registerForm.location;
                const result = (await HttpManager.SignUp({ username, password, sex, phoneNum, email, birth, introduction, location })) as ResponseBody;
                (proxy as any).$message({
                    message: result.message,
                    type: result.type,
                });

                if (result.success) {
                    changeIndex(NavName.SignIn);
                    routerManager(RouterName.SignIn, { path: RouterName.SignIn });
                }
            } catch (error) {
                console.error(error);
            }
        }

        return {
            SignUpRules,
            AREA,
            registerForm,
            handleSignUp,
            goBackRegist,
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
