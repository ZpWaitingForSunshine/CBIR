<template>
  <div :id="id" :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
// require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

export default {
  mixins: [resize],
  props: {
    swss: {
      type: Array,
      default: () => {
      }
    },
    className: {
      type: String,
      default: 'chart'
    },
    id: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '200px'
    },
    height: {
      type: String,
      default: '200px'
    }
  },
  data() {
    return {
      num: 0,
      chart: null,
      /* name: '',
      reflectStr: [],
      ids: [],
      abscissa: [], // 横坐标
      flag: false*/
      reflectStr: [], // 数组
      waveLenStr: [],
      curve1: [], // 曲线
      curve: {} // 曲线
    }
  },
  // 监听纵坐标变化
  watch: {
    swss: {
      handler(newValue, oldValue) { // 会把更新的数据送至
        this.chart.clear()
        this.curve1 = [] // 清空显示队列
        this.waveLenStr = []
        this.reflectStr = []
        newValue.map(item => {
          this.curve = {
            id: '',
            name: '',
            type: 'line',
            symbol: 'none',
            smooth: true,
            xAxisIndex: 0,
            connectNulls: true,
            data: []
          }
          this.curve.id = item.id
          this.curve.name = item.specName
          this.waveLenStr = Array.from(item.waveLenStr)
          this.reflectStr = Array.from(item.reflectStr)
          this.curve.data = this.waveLenStr.map((data, i) => ([data, this.reflectStr[i]]))
          this.curve1.push(this.curve)
        })
        console.log('oo' + this.curve1)
        this.initChart() // 刷新echarts图表
      }
    }
  },
  mounted() {
    this.initChart()
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart.clear()
    this.chart = null
  },
  methods: {
    initChart() {
      this.chart = echarts.init(document.getElementById(this.id))
      this.chart.setOption({
        backgroundColor: 'white',
       
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            lineStyle: {
              color: '#57617B'
            }
          }
        },
        xAxis: [{
          name: '',
          type: 'value',
          position: 'bottom'
        }],
        yAxis: [{
          type: 'value',
          name: 'Reflectivity',
          axisTick: {
            show: false
          },
          axisLine: {
            lineStyle: {
              color: '#57617B'
            }
          },
          axisLabel: {
            margin: 10,
            textStyle: {
              fontSize: 14
            }
          }

        }],
        series: this.curve1
      })
    }
  }
}
</script>
