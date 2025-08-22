// 登录注册验证规则

// 登录规则
// 两个validator函数
const validateName = (rule, value, callback) => {
    if (!value) {
        return callback(new Error("用户名不能为空"));
    } else {
        callback();     // 回调函数
    }
};

export const validatePassword = (rule, value, callback) => {
    if (value === "") {
        callback(new Error("密码不能为空"));
    } else {
        callback();
    }
};

// 验证规则配置
// 输入框失去焦点时（trigger: "blur"），触发验证
export const SignInRules = {
    // 调用验证函数
    // 检查格式长度的部分在别的地方？
    username: [{ validator: validateName, trigger: "blur", min: 3 }],
    password: [{ validator: validatePassword, trigger: "blur", min: 3 }],
};

// 注册规则
export const SignUpRules = {
    // required 必填
    username: [{ required: true, trigger: "blur", min: 3 }],
    password: [{ required: true, trigger: "blur", min: 3 }],
    sex: [{ required: true, message: "请选择性别", trigger: "change" }],
    phoneNum: [
        { message: "请输入联系方式", trigger: "blur" },
        {
            // 新增：检查电话号码格式
            pattern: /^1[3-9]\d{9}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
        },
    ],
    email: [
        { message: "请输入邮箱地址", trigger: "blur" },
        {
            // 特殊验证类型，检查邮箱格式
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
        },
    ],
    birth: [{ required: true, type: "date", message: "请选择日期", trigger: "change" }],
    introduction: [{ message: "请输入介绍", trigger: "blur" }],
    location: [{ message: "请输入地区", trigger: "change" }],
};
