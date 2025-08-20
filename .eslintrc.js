module.exports = {
  root: true,
  env: {
    node: true
  },
  'extends': [
    'plugin:vue/vue3-essential',
    'eslint:recommended',
    '@vue/typescript/recommended'
  ],
  parserOptions: {
    ecmaVersion: 2020
  },
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'vue/multi-word-component-names': 0,
    "@typescript-eslint/no-unused-vars": ["off"],
    "@typescript-eslint/no-explicit-any": ["off"],
    'no-undef': 0,
    // // 核心规则：设置tab缩进为 2 格
    // "indent": ["error", 2, {
    //   "SwitchCase": 1, // switch 语句的 case 缩进特殊处理（可选）
    //   "ignoredNodes": ["TemplateLiteral > *"] // 忽略模板字符串内的缩进检测（Vue 模板中可能需要）
    // }],
    // "vue/script-indent": ["error", 2, {
    //   "baseIndent": 1, // 脚本块（<script>）的基础缩进
    //   "switchCase": 1
    // }]
  }
}
