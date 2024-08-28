<template>
  <div>
    <edit-form
      ref="editForm"
    ></edit-form>
    <el-button v-on:click="addContract">新建合同</el-button>
    <el-button @click="alert(222)">编辑</el-button>
    <el-button @click="alert(222)">删除</el-button>
    <el-button @click="alert(222)">审批进度</el-button>
      <!-- el-table 在弹窗中显示的表格 -->
      <el-table :data="tableData" style="width: 100%">
        <el-table-column type="selection"></el-table-column>
        <el-table-column prop="ID" label="序号"></el-table-column>
        <el-table-column v-if=true prop="id" label="ID"></el-table-column>
        <el-table-column label="合同编号" prop="contractCode">
        </el-table-column>
        <el-table-column label="合同名称" prop="contractName">
        </el-table-column>
        <el-table-column label="客户" prop="opportunityName">
        </el-table-column>
        <el-table-column label="合同金额" prop="contractAmount">
        </el-table-column>
        <el-table-column label="签约日期" prop="signDate">
        </el-table-column>
        <el-table-column label="合同状态" prop="contractStatus">
        </el-table-column>
        <el-table-column label="创建人" prop="createBy">
        </el-table-column>
        <el-table-column label="创建时间" prop="createDate">
        </el-table-column>
      </el-table>
  </div>
</template>

<script>
import History from '@/views/components/history'
import EditForm from './contractDialog.vue'
import QueryTag from '@/views/components/queryTag'
import QueryConditionPanel from '@/views/components/queryConditionPanel'
import ViewColumnsSelect from '@/views/components/DcViewColumnsSelect'
import ExportExcelButton from '@/components/ExportExcelButton'
import DcMain from '@/views/components/dcMain'
import OperationIcon from '@/components/OperationIcon'
import {listCrmContractPage} from "@/api/contract/crmContract";
import action from "../../sys/action/index.vue";
export default {
  components: {
    /** 根据用户界面配置组件 开始 */
    History,
    EditForm,
    QueryTag,
    QueryConditionPanel,
    ViewColumnsSelect,
    /** 根据用户界面配置组件 结束 */
    ExportExcelButton,
    OperationIcon,
  },
  data() {
    return {
      name:"contract",
      tableData: [],
    };
  },
  props: {
    sjId:{
      type:String
    }
  },
  methods: {
    addContract(){
      this.$refs.editForm.$emit('openAddDialog',this.sjId)
    },

    // 获取当前合同信息
    getShangJiPage(){

      let search_List = {
        params:[]
      }
      search_List.params.push({'columnName': 'opportunityId', 'queryType': '=', 'value': this.sjId})

      listCrmContractPage(search_List).then((res)=>{
        debugger
        if (res.code == 100) {
          this.tableData = res.data.rows ? res.data.rows : []
        }else{
          this.$message(res);
        }
      })
    }
  },
  mounted() {
    // 在组件挂载后，获取后台数据并初始化表格
    this.getShangJiPage()
  },
};
</script>
