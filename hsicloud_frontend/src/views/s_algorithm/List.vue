<template>
  <div class="app-container">
    <el-select
      ref="dragSelect"
      v-model="selectVal"
      v-bind="$attrs"
      class="drag-select"
      multiple
      v-on="$listeners"
    >
      <slot />
    </el-select>
    <button type="button" class="el-button el-button--primary el-button--medium">
      <span data-v-12538e5f>搜索</span>
    </button>

    <el-table :data="list" style="width: 100%;padding-top: 15px;">
      <el-table-column label="ID">
        <template slot-scope="scope" align="center">{{ scope.row.cid }}</template>
      </el-table-column>
      <el-table-column label="名称">
        <template slot-scope="scope" align="center">{{ scope.row.name }}</template>
      </el-table-column>
      <el-table-column label="描述" min-width="200">
        <template slot-scope="scope">{{ scope.row.summary}}</template>
      </el-table-column>
      <!-- <el-table-column label="查看" width="100" align="center">
      <button type="button" class="el-button el-button--primary el-button--medium"><span>查看</span></button>
      </el-table-column>-->

      <el-table-column align="center" label="查看" width="100">
        <template slot-scope="scope">
          <router-link :to="'/s_algorithm/declartion/'+scope.row.cid" class="link-type">
            <el-button type="primary" size="small" icon="el-icon-edit">查看</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getList"
    />
  </div>
</template>
<script>
import { fetchList } from "@/api/components";
import Pagination from "@/components/Pagination"; // Secondary package based on el-pagination
// import Sortable from 'sortablejs'

export default {
  name: "ArticleList1",
  components: { Pagination },
  data() {
    return {
      list: [
        // {
        //   id: "1",
        //   componentname: "大米检测",
        //   input: ["大米光谱文件路径", "运行方式", "阈值"],
        //   output: ["运行时间", "检测图片"],
        //   description: "通过对不同反射率的像素进行区别，得到大米优劣结果图"
        // },
        // {
        //   id: "2",
        //   componentname: "PCA",
        //   input: ["高光谱文件路径", "运行方式", "降维维数"],
        //   output: ["运行时间", "降维后数组"],
        //   description: "对高光谱数据实现降维，降低高光谱文件大小"
        // },
        // {
        //   id: "3",
        //   componentname: "PPI",
        //   input: ["降维后数组", "运行方式", "随机数组数目"],
        //   output: ["运行时间", "端元集合"],
        //   description: "对高光谱数据实现端元提取"
        // },
        // {
        //   id: "4",
        //   componentname: "N-Finder",
        //   input: ["高光谱文件路径", "运行方式"],
        //   output: ["运行时间", "端元集合"],
        //   description: "对高光谱数据实现端元提取"
        // },
        // {
        //   id: "5",
        //   componentname: "SAM",
        //   input: ["待匹配端元合集", "光谱库", "运行方式"],
        //   output: ["运行时间", "端元名称集合"],
        //   description: "进行端元匹配，获取端元所属物种的类别"
        // },
        // {
        //   id: "6",
        //   componentname: "SVM",
        //   input: ["高光谱文件路径 ", "迭代次数", "运行方式", "阈值"],
        //   output: ["运行时间", "分类合集"],
        //   description: "进行端元匹配，获取端元所属物种的类别"
        // },
        // {
        //   id: "7",
        //   componentname: "NMF",
        //   input: ["高光谱文件路径 ", "迭代次数", "运行方式", "阈值"],
        //   output: ["运行时间", "分类合集"],
        //   description: "非负矩阵分解"
        // },
        // {
        //   id: "8",
        //   componentname: "Liner Regression",
        //   input: ["高光谱文件路径 ", "迭代次数", "运行方式", "阈值"],
        //   output: ["运行时间", "分类合集"],
        //   description: "线性回归，预测趋势"
        // },
        // {
        //   id: "9",
        //   componentname: "MNF",
        //   input: ["高光谱文件路径 ", "迭代次数", "运行方式", "阈值"],
        //   output: ["运行时间", "分类合集"],
        //   description: "进行端元匹配，获取端元所属物种的类别"
        // },
        // {
        //   id: "10",
        //   componentname: "K-Means",
        //   input: ["高光谱文件路径 ", "迭代次数", "运行方式", "阈值"],
        //   output: ["运行时间", "分类合集"],
        //   description: "聚类算法，获取端元所属物种的类别"
        // },
        // {
        //   id: "11",
        //   componentname: "基于PPI解混流程",
        //   input: ["高光谱文件路径 ", "迭代次数", "运行方式", "阈值"],
        //   output: ["运行时间", "分类合集"],
        //   description: "解混算法流程集合，获取端元所属物种的类别"
        // }
      ],

      total: 6,
      listLoading: false,
      listQuery: {
        page: 1,
        limit: 20
      },
      selectVal: 1
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      // this.listLoading = true
      fetchList().then(response => {
        console.log(response);
        this.list = response.data;
        // console.log(response)
        // this.total = response.total
        // this.listLoading = false
      });
    }
  }
};
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
