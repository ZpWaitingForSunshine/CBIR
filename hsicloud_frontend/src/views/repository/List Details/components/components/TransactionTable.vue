<template>
  <el-table :data="list" style="width: 100%;padding-top: 15px;">
    <el-table-column label="ID" >
      <template slot-scope="scope" align="center">
        {{ scope.row.id }}
      </template>
    </el-table-column>
    <el-table-column label="名称" >
      <template slot-scope="scope" align="center">
        {{ scope.row.componentname }}
      </template>
    </el-table-column>
    <el-table-column label="描述" min-width="200">
      <template slot-scope="scope">
        {{ scope.row.description}}
      </template>
    </el-table-column>
    <el-table-column label="查看" width="100" align="center">
      <button type="button" class="el-button el-button--primary el-button--medium"><span>查看</span></button>
    </el-table-column>
  </el-table>
</template>

<script>
import { transactionList } from '@/api/remote-search'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        success: 'success',
        pending: 'danger'
      }
      return statusMap[status]
    },
    orderNoFilter(str) {
      return str.substring(0, 30)
    }
  },
  data() {
    return {
      list: [
        {
          "id":"1",
        "componentname": "大米检测",
        "input": [
          "大米光谱文件路径", "运行方式", "阈值"
        ],
        "output": ["运行时间", "检测图片"],
        "description": "通过对不同反射率的像素进行区别，得到大米优劣结果图"
      },{
          "id":"2",
        "componentname": "PCA",
        "input": [
          "高光谱文件路径", "运行方式", "降维维数"
        ],
        "output": ["运行时间", "降维后数组"],
        "description": "对高光谱数据实现降维，降低高光谱文件大小"
      },{
          "id":"3",
        "componentname": "PPI",
        "input": [
          "降维后数组", "运行方式", "随机数组数目"
        ],
        "output": ["运行时间", "端元集合"],
        "description": "对高光谱数据实现端元提取"
      },{
          "id":"4",
        "componentname": "N-Finder",
        "input": [
          "高光谱文件路径", "运行方式"
        ],
        "output": ["运行时间", "端元集合"],
        "description": "对高光谱数据实现端元提取"
      },{
          "id":"5",
        "componentname": "SAM",
        "input": [
          "待匹配端元合集", "光谱库","运行方式"
        ],
        "output": ["运行时间", "端元名称集合"],
        "description": "进行端元匹配，获取端元所属物种的类别"
      },{
          "id":"6",
        "componentname": "SVM",
        "input": [
          "高光谱文件路径 ", "迭代次数","运行方式","阈值"
        ],
        "output": ["运行时间", "分类合集"],
        "description": "进行端元匹配，获取端元所属物种的类别"
      }
      ]




    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      // transactionList().then(response => {
      //   this.list = response.data.items.slice(0, 18)
      // })
    }
  }
}
</script>
