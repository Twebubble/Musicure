<template>
    <ul class="musicure-nav">
        <li v-for="(item, index) in styleList" :key="index" :class="{ active: item.name == activeName }"
            @click="handleChangeView(item)">
            {{ item.name }}
        </li>
    </ul>
</template>

<script lang="ts">
import { defineComponent, getCurrentInstance, PropType } from "vue";

// 定义样式项接口【新增：解决item报错问题】
interface NavItem {
    name: string;
    value?: string; // 根据 Search.vue 的使用，还有 value 属性
    [key: string]: any; // 允许其他属性
}

export default defineComponent({
    props: {
        // styleList: Array,
        // activeName: String,
        styleList: {
            type: Array as PropType<NavItem[]>,
            default: () => [] as NavItem[]
        },
        activeName: {
            type: String,
            default: ""
        }
    },
    emits: ["click"],
    // methods: {
    //     handleClick(item) {
    //         this.$emit("click", item); // 触发点击事件
    //     }
    // },
    setup() {
        const { proxy } = getCurrentInstance();

        function handleChangeView(val) {
            proxy.$emit("click", val);
        }
        return {
            handleChangeView,
        };
    },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";

.musicure-nav {
    width: 100%;

    li {
        line-height: 2rem;
        font-size: 1rem;
        color: $color-grey;
        border-bottom: none;
        cursor: pointer;
    }

    li.active {
        color: $color-black;
        font-weight: 600;
    }
}

@media screen and (min-width: $sm) {
    .yin-nav {
        li {
            margin: 0.5rem 1rem;
        }
    }
}

@media screen and (max-width: $sm) {
    .yin-nav {
        li {
            margin: 0.3rem 0.4rem;
        }
    }
}
</style>