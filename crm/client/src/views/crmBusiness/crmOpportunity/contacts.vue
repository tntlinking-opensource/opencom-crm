<template>
  <el-row class="dc-container" v-if="dialogProps.visible">
    <edit-contact-form ref="editContactForm"  v-on:save-finished="this.getList"
    ></edit-contact-form>
    <el-button v-on:click="addContacts" :disabled="action == 'view'" type="primary" icon="el-icon-edit-outline" class="dc-el-button_Button">新增</el-button>
    <el-button @click="editContacts" :disabled="action == 'view'" type="primary" icon="el-icon-edit-outline" class="dc-el-button_Button">编辑</el-button>
    <el-button @click="deleteContacts" :disabled="action == 'view'" type="danger" icon="el-icon-edit-outline" class="dc-el-button_Button">删除</el-button>
    <!-- el-table 在弹窗中显示的表格 -->
    <ux-grid :data="tableData4" style="width: 100%" @row-click="contraceHandleRowClick"   highlight-current-row
              :current-row="currentRow"  class="dc-ux-grid_QueryTable">
      <ux-table-column field="ID" width="80px" title="序号" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
      <ux-table-column v-if=false field="id" title="ID"></ux-table-column>
      <ux-table-column title="联系人姓名" field="contactsName" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
      </ux-table-column>
      <ux-table-column title="所属部门" field="depart" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
      </ux-table-column>
      <ux-table-column title="职位" field="workPost" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
      </ux-table-column>
      <ux-table-column title="电话" field="contactPhone" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
      </ux-table-column>
      <ux-table-column title="手机号" field="phoneNumber" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
      </ux-table-column>
      <ux-table-column title="邮箱" field="email" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
      </ux-table-column>
      <ux-table-column title="所在区域" field="region" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
      </ux-table-column>
      <ux-table-column title="详细地址" field="address" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
      </ux-table-column>
      <ux-table-column title="备注" field="notes" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
      </ux-table-column>
    </ux-grid>
    <el-pagination :total="tableData4DataTotal" :page-size="search.limit" background :current-page="tableData4DataPage"
                   :page-sizes="[10, 20, 30, 40, 50, 100]" layout="total, sizes, prev, pager, next, jumper"
                   v-on:size-change="onSizeChange" v-on:current-change="onCurrentChange"
                   class="dc-el-pagination_ElPagination"></el-pagination>

    <!--
    <el-table :data="tableData4" style="width: 100%" @row-click="contraceHandleRowClick"   highlight-current-row
              :current-row="currentRow" >
      <el-table-column prop="ID" width="70px" label="序号" align="center"></el-table-column>
      <el-table-column v-if=false prop="id" label="ID"></el-table-column>
      <el-table-column label="联系人姓名" prop="contactsName" align="center">
      </el-table-column>
      <el-table-column label="所属部门" prop="depart" align="center">
      </el-table-column>
      <el-table-column label="职位" prop="workPost" align="center">
      </el-table-column>
      <el-table-column label="电话" prop="contactPhone" align="center">
      </el-table-column>
      <el-table-column label="手机号" prop="phoneNumber" align="center">
      </el-table-column>
      <el-table-column label="邮箱" prop="email" align="center">
      </el-table-column>
      <el-table-column label="所在区域" prop="region" align="center">
      </el-table-column>
      <el-table-column label="详细地址" prop="address" align="center">
      </el-table-column>
      <el-table-column label="备注" prop="notes" align="center">
      </el-table-column>
    </el-table>-->
  </el-row>
</template>

<script>
import History from '@/views/components/history'
import EditContactForm from './contactsDialog.vue'
import QueryTag from '@/views/components/queryTag'
import QueryConditionPanel from '@/views/components/queryConditionPanel'
import ViewColumnsSelect from '@/views/components/DcViewColumnsSelect'
import ExportExcelButton from '@/components/ExportExcelButton'
import OperationIcon from '@/components/OperationIcon'
import BaseUI from '@/views/components/baseUI'
import {deleteCrmOpportunityLink, listCrmOpportunityLinkPage} from "../../../api/crmBusiness/crmOpportunityLink";
import EditForm from "./contractDialog.vue";

export default {
  extends: BaseUI,
  components: {
    EditForm,
    /** 根据用户界面配置组件 开始 */
    History,
    EditContactForm,
    QueryTag,
    QueryConditionPanel,
    ViewColumnsSelect,
    /** 根据用户界面配置组件 结束 */
    ExportExcelButton,
    OperationIcon,
  },
  data() {
    return {
      name: "contract",
      tableData4: [],
      tableData4DataTotal:0,
      tableData4DataPage:1,
      dialogProps: {
        visible: false,
        title: '联系人'
      },
      currentRow:[],
      search: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },

    };
  },
  props:{
    sjId:{
      type:String
    },
    customerId:{
      type:String
    },
    action:{
      type:String
    }
  },
  methods: {
    // 合同行选中
    contraceHandleRowClick(row){
      this.currentRow = row; // 更新当前选中的行数据
    },
    getList(){
      this.dialogProps.visible = true
      let search_List = {
        params:[]
      }
      search_List.params.push({'columnName': 'opp.id', 'queryType': '=', 'value': this.sjId})
      listCrmOpportunityLinkPage(search_List).then((res)=>{
        if (res.code == 100) {
          let array = []
          this.tableData4DataTotal = res.data.total;
          if (res.data.total === 0) {
            array = []
          }else{
            res.data.rows.map((item, index) => {
              array.push(
                Object.assign({}, item.link, {
                  'ID':index+1,
                }))
            })
          }
          this.tableData4 = array;

        }
      })
    },
    addContacts() {
      this.$refs.editContactForm.$emit('openContactsAddDialog',this.sjId,this.customerId)
    },
    // 选择数据
    seletRow(){
      if (this.currentRow.length == 0) {
        this.$message.warning("请选择一条数据");
        return false;
      }
      return true;
    },
    // 编辑
    editContacts() {
      if (this.seletRow()){
        this.$refs.editContactForm.$emit('openContactsEditDialog',this.sjId,this.customerId,this.currentRow.id)
      }
    },
    // 删除
    deleteContacts() {
      if (!this.seletRow()){
        return false;
      }
      this.$confirm('确认删除该数据？', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteCrmOpportunityLink(this.currentRow).then((res)=>{
          if (res.code == 100) {
            this.showMessage({
              type: 'success',
              msg: '删除成功'
            })
            this.currentRow = []
            this.getList()
          }else{
            this.$message.error(res)
          }
        })
      })
    },
  },
  mounted: function () {
    this.$nextTick(() => {
      this.$on('openContactsAddDialog', function () {
        this.getList()
        this.dialogProps.visible = true
      })
    })
  }
};
</script>
