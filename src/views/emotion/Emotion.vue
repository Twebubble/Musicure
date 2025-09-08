<template>
    <!-- 当前情绪类别（二分类） -->
    <div class="emotionclass">
        <el-card class="header">
        <!-- 在适当位置添加刷新按钮 -->
        <el-button @click="refreshData" type="primary" :loading="loading">
            刷新脑电数据
        </el-button>
        </el-card>

    </div>
    <!-- 脑电波形图 -->
    <!-- direction="vertical" -->
    <el-space class="charts" alignment="flex-start">
        <!-- 某一时段的唤醒度随时间变化图和某一时段的效价随时间变化图 -->
        <el-card class="EEGimg">
            <el-tabs type="border-card" class="img-tabs">
                <el-tab-pane>
                    <template #label>
                        <span class="custom-tabs-label">
                            <el-icon>
                                <TrendCharts />
                            </el-icon>
                            <span>EEG wave</span>
                        </span>
                    </template>
                    <div class="EEGwave" ref="chartRef"></div>
                </el-tab-pane>
                <el-tab-pane>
                    <template #label>
                        <span class="custom-tabs-label">
                            <el-icon>
                                <Orange />
                            </el-icon>
                            <span>EEG map</span>
                        </span>
                    </template>
                    <div class="EEGmap">
                        脑地形图
                    </div>
                </el-tab-pane>
            </el-tabs>
        </el-card>
        <!-- 根据情绪推荐的歌单 -->
        <el-card class="SongSheet">
            <div class="title">根据您的情绪状态为您推荐以下歌曲：</div>
            <el-divider border-style="dashed" />
            <div class="sheet">
                <!-- 展示用户收藏的歌曲，当歌曲列表变化时重新加载数据 -->
                <song-list :songList="collectSongList" :show="true" @changeData="changeData"></song-list>
            </div>
        </el-card>
    </el-space>
</template>

<script lang="ts" setup>
import { ref, computed, onMounted, onBeforeUnmount, nextTick } from 'vue'
import { TrendCharts, Orange } from '@element-plus/icons-vue'
import * as echarts from 'echarts'
import { GridComponentOption, XAXisComponentOption, YAXisComponentOption, SeriesOption} from 'echarts';

import { useStore } from "vuex";
import SongList from "@/components/SongList.vue";
import Upload from "../setting/Upload.vue";
import mixin from "@/mixins/mixin";
import { HttpManager } from "@/api";
import { RouterName } from "@/enums";

const store = useStore();
const { routerManager } = mixin();
const collectSongList = ref([]); // 收藏的歌曲
const userId = computed(() => store.getters.userId);

// 获取收藏的歌曲
async function getCollection(userId) {
    collectSongList.value = [];
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

// 重新加载脑电数据
const loading = ref(false);

// 刷新数据
async function refreshData() {
    loading.value = true;
    await fetchEEGData();
    loading.value = false;
}

// ===========================> 绘图相关

// 获取 DOM 元素的引用（用于初始化图表）
const chartRef = ref(null)
// 存储 ECharts 实例
let chartInstance = null

const channelNames = [
    "Fp1", "Fp2", "F3", "F4", "F7", "F8", "FC1", "FC2",
    "FC5", "FC6", "Cz", "C3", "C4", "T7", "T8", "CP1",
    "CP2", "CP5", "CP6", "Pz", "P3", "P4", "P7", "P8",
    "POz", "PO3", "PO4", "PO5", "PO6", "Oz", "O1", "O2"
]

// 颜色数组 - 使用不同的颜色区分各个通道
const colorArray = ref<string[]>([
    '#5470c6', '#91cc75', '#fac858', '#ee6666', '#73c0de',
    '#3ba272', '#fc8452', '#9a60b4', '#ea7ccc', '#d7504b',
    '#c14089', '#6e51b0', '#5c7ce3', '#4da4eb', '#46b3e3',
    '#40d4c5', '#3cd37f', '#a0d84c', '#fcd337', '#f9b13a',
    '#f78e3d', '#f26c43', '#e7544a', '#d73e4f', '#c22a6a',
    '#a8228c', '#8a23a6', '#6b2cb2', '#4c36b8', '#2e40b5',
    '#1a48a8', '#0c4e99'
])

// ===========================> 数据模拟
// const timeData = ref<string[]>([])
// const startTime = new Date()
// startTime.setHours(0, 0, 0, 0)

// // 生成时间序列 (10秒数据，每秒一个点)
// for (let i = 0; i < 300; i++) {
//     const time = new Date(startTime.getTime() + i * 1000)
//     timeData.value.push(time.toTimeString().substring(0, 8))
// }

// // 生成模拟脑电数据
// const generateEEGData = (): TableDataItem[] => {
//     return channelNames.map((name, index) => {
//         const data: number[] = []
//         // 为每个通道生成略有不同的波形
//         const baseAmplitude = 20 + Math.random() * 30
//         const frequency = 0.5 + Math.random() * 0.5

//         for (let i = 0; i < 300; i++) {
//             // 模拟脑电信号
//             const value = baseAmplitude * Math.sin(frequency * i) + (Math.random() - 0.5) * 10
//             data.push(parseFloat(value.toFixed(2)))
//         }

//         return { name, data }
//     })
// }

// const tableData = ref<TableDataItem[]>(generateEEGData())

// ===========================> 从后端获得脑电数据
// 定义类型
interface TableDataItem {
    name: string;
    data: number[];
}

interface EEGResponse {
    code: number;
    message: string;
    type: string;
    success: boolean;
    data: {
        timeData: string[];
        channelData: TableDataItem[];
    };
}

// 时间和通道数据
const timeData = ref<string[]>([])
const tableData = ref<TableDataItem[]>([])

async function fetchEEGData() {
    try{
        const response = await HttpManager.getEEGDataOfUser({
            userId: userId.value,
            // dataPoints根据需要输入，没有时使用设置的默认值
            // dataPoints: 300,
        }) as unknown as EEGResponse;

        if (response.success) {

            // 更新时间和通道数据
            timeData.value = response.data.timeData;
            tableData.value = response.data.channelData;

            // channelNames.value = response.data.channelData.map(item => item.name);
            
            // 重新初始化图表

            // 添加短暂延迟确保DOM更新完成
            setTimeout(() => {
                if (chartRef.value) {
                    chartRef.value.style.height = `${getChartHeight(tableData.value.length)}px`;
                }
                initChart();
            }, 100);

        } else {
            console.error('获取脑电数据失败:', response.message);
            // 可以在这里添加错误处理，比如显示提示信息
        }

    } catch (error) {
        console.error('获取脑电数据时出错:', error);
    }
}

// ===========================> 渲染图表
// 初始化图表的方法
const initChart = () => {
    
    if (!chartRef.value || tableData.value.length === 0) return

    // 初始化 echarts 实例
    if (chartInstance) {
        chartInstance.dispose();
    }
    chartInstance = echarts.init(chartRef.value)

    chartRef.value.style.height = `${getChartHeight(tableData.value.length)}px`;

    // 配置项
    const option: echarts.EChartsOption = {
        grid: [],
        xAxis: [],
        yAxis: [],
        series: [],
        animation: false,
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'line'
            },
            backgroundColor: 'rgba(255, 255, 255, 0.7)', // 设置白色的半透明背景
        },
        dataZoom: [
            {
                type: 'inside',
                xAxisIndex: Array.from({ length: channelNames.length }, (_, i) => i),
                start: 0,
                end: 100
            },
            {
                start: 0,
                end: 100,
                bottom: 5,
            }
        ],
        toolbox: {
            right: 20, // 距离右侧距离
            top: 0,   // 距离顶部距离
            feature: {
                dataZoom: {
                    yAxisIndex: 'none' // 只缩放时间轴，保持电压幅度不变
                },
                dataView:{},
                restore: {}, // 重置缩放状态
                saveAsImage: {
                    type: 'png', // 保存为PNG格式
                    name: '脑电波形图' // 默认文件名
                }
            }
        },
    }

    // 重新给图表赋值数据
    tableData.value.forEach((currentValue: TableDataItem, index: number, array: TableDataItem[]) => {
        // 按照参数的个数添加grid，需要叠加计算的是，距离bottom的高度
        const height = (index + 1) * 60;
        
        // (option.grid as echarts.GridComponentOption[])
        (option.grid as echarts.GridComponentOption[]).push({
            show: true,
            left: 50,
            right: 50,
            bottom: height,
            height: 55, // 每一个折线图的高度
            // 50 50 100
            backgroundColor: index % 2 === 1 ? '#f9f9f9' : 'white',  // 背景色
        });

        // 按照参数的个数添加xAxis里面的对象
        // 只有最后一个对象显示X轴坐标（时间）
        // index === array.length - 1
        if (index === 0) {
            // (option.grid as echarts.GridComponentOption[])
            (option.xAxis as echarts.XAXisComponentOption[]).push({
                gridIndex: index,//x 轴所在的 grid 的索引
                type: 'category',//类目轴
                boundaryGap: false,//坐标轴两边留白策略
                axisLine: {
                    onZero: false,
                    lineStyle: {
                        color: 'black',
                        width: 1,
                        // width: 2
                    },
                },
                axisLabel: {
                    formatter: function (value: string) {
                        return value;
                    },
                    color: '#595959',
                },
                data: timeData.value,
                show: true,
            });
        } else {
            (option.xAxis as echarts.XAXisComponentOption[]).push({
                gridIndex: index,
                type: 'category',
                boundaryGap: false,
                axisLine: {
                    onZero: true,
                    lineStyle: {
                        color: '#ddd',
                        // color: '#979797',
                    },
                },
                data: timeData.value,
                show: false,    // 是否显示 x 轴 true？。
                axisTick: {
                    //坐标轴刻度相关设置
                    show: false,
                },
                axisLabel: {
                    //刻度标签
                    show: false,
                },
            });
        }

        // 修改y轴
        (option.yAxis as echarts.YAXisComponentOption[]).push({
            gridIndex: index,   // y 轴所在的 grid 的索引
            type: 'value',
            position: 'left',
            name: currentValue.name,
            // nameRotate:0,
            nameTextStyle: {
                fontSize: 15,
                padding: [0, 0, -50, -50],
                color: colorArray.value[index]
                //index → index % colorArray.value.length 使用模运算防止颜色数组越界
            },
            // Y 轴刻度是否反向
            inverse: false,
            //  Y 轴的最值
            min: 'dataMin',
            max: 'dataMax',
            splitLine: {
                show: false,
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#ddd',
                    // color: '#979797',
                },
            },
            axisTick: {
                show: false,
            },
            axisLabel: {
                show: false,
                // 显示刻度标签
                // show: true,
                // showMinLabel: false, // 隐藏最小值标签
                // showMaxLabel: false, // 隐藏最大值标签
            },
        });

        // 最后修改series
        // (option.grid as echarts.GridComponentOption[])
        (option.series as echarts.SeriesOption[]).push({
            name: currentValue.name,
            type: 'line',
            color: colorArray.value[index],
            // 设置不显示小圆圈
            symbol: 'none',
            showSymbol: false,
            xAxisIndex: index,
            yAxisIndex: index,
            // hoverAnimation: false,
            // 平滑曲线
            smooth: true,
            // 每个grid的数据-数据注入
            data: currentValue.data,
            // 参考线
            // markLine: {
            //     symbol: 'none', //去掉箭头
            //     data: [
            //         {
            //             // type: 'median', //中位数。
            //             type: 'average', //中位数。
            //             symbol: 'none', //去掉开始的原点
            //             label: {
            //                 //字体设置
            //                 show: 'true',
            //                 position: 'insideEndTop',
            //                 distance: 10,
            //                 formatter: currentValue.name,
            //                 color: 'inherit',
            //             },
            //             lineStyle: {
            //                 //横线设置
            //                 width: 1,
            //             },
            //         },
            //     ],
            // },
            lineStyle: {
                shadowColor: colorArray[index], //透明的颜色？
                shadowOffsetX: 0,
                shadowOffsetY: 0,
                opacity: 0.6, //透明度
                shadowBlur: 7, //阴影大小
                type: "solid", //实线
                width: 1.5, // 2
            },
            emphasis: {
                lineStyle: {
                    width: 2,
                }
            }
        } as echarts.SeriesOption)
    });

    // 使用配置项渲染图表
    chartInstance.setOption(option);
}

// 重新获得动态的图表高度
const getChartHeight = (length: number): number => {
    return length * 60 + 100 // 每个通道60px高度 + 额外空间
    // this.dynamicHeight = `${num * 100 + 150}px`;
}

// 窗口大小变化时调整图表尺寸
const resizeChart = () => {
    chartInstance?.resize()
}

// 组件挂载后执行初始化
onMounted(() => {
    nextTick(() => {
        getCollection(userId.value);

        // 获取脑电数据
        fetchEEGData();

        // 设置图表容器高度
        // if (chartRef.value) {
        //     chartRef.value.style.height = `${getChartHeight(tableData.value.length)}px`
        // }

        // initChart()
        // 监听窗口大小变化事件以支持响应式
        window.addEventListener('resize', resizeChart)
    })
})

// 组件卸载前清理资源，防止内存泄漏
onBeforeUnmount(() => {
    window.removeEventListener('resize', resizeChart)
    chartInstance?.dispose() // 销毁 echarts 实例
})

</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";

.header{
    margin: 40px;
    width: 720px;
    height: 100px;
}

.EEGimg{
    margin-left: 40px;
    width: 100%;
    width: 720px;
    height: auto;
    overflow: auto;

}

.SongSheet{
    margin-left: 40px;
    width: 600px;
    height: auto;
    // padding-top: $header-height + 150px;60+150px
}

.EEGwave{
    width: 100%;
    min-height: 500px;
    overflow: auto;
    padding-bottom: 30px;
}

.EEGmap {
    width: 100%;
    height: 100%;
}

.custom-tabs-label {
    display: flex;
    align-items: center;
    gap: 8px;
}

.charts {
    width: 100%;
    /* justify-content: center; */
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