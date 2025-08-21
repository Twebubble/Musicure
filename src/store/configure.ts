export default {
    state: {
        // 全局状态
        token: false, // 用户是否登录
        showAside: false, // 是否显示侧边栏
        searchWord: "", // 存储搜索关键词
        activeNavName: "", // 当前激活的导航栏名称（高亮）
    },
    getters: {
        // 状态访问控制：只读
        token: (state) => state.token,
        activeNavName: (state) => state.activeNavName,
        showAside: (state) => state.showAside,
        searchWord: (state) => state.searchWord,
    },
    mutations: {
        // 状态修改控制：所有状态修改都必须通过 mutations 进行
        setToken: (state, token) => {
            state.token = token;
        },
        setActiveNavName: (state, activeNavName) => {
            state.activeNavName = activeNavName;
        },
        setShowAside: (state, showAside) => {
            state.showAside = showAside;
        },
        setSearchWord: (state, searchWord) => {
            state.searchWord = searchWord;
        },
    },
}