<template>
  <el-row class="dc-container" v-if="dialogProps.visible">
    <crmContacts-form
      ref="crmContactOpenDialog"
      v-on:save-finished="this.getList"
    ></crmContacts-form>
    <el-button  type="primary" :disabled="action != 'edit'" v-on:click="editPayBack">编辑回款</el-button>
    <el-button  type="primary" :disabled="action != 'view'" v-on:click="viewPayBack">查看回款</el-button>
    <!-- el-table 在弹窗中显示的表格 -->
    <ux-grid column-key ref="table" :data="tableData" border v-on:sort-change="onSortChange" @row-click="handleRowClick"
             v-on:header-dragend="onWidthChange($refs.table)"
             :tree-config="{ children: '', indent: 20, accordion: false, line: false, showIcon: true, iconOpen: '', iconClose: '' }"
             class="dc-ux-grid_QueryTable">
      <ux-table-column :field="tableColumns['136'].field" :title="tableColumns['136'].title"
                       :width="tableColumns['136'].width" :visible="tableColumns['136'].visible"
                       :params="{ sortId: '136', summary: false }" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
      <ux-table-column :field="tableColumns['137'].field" :title="tableColumns['137'].title"
                       :width="tableColumns['137'].width" :visible="tableColumns['137'].visible"
                       :params="{ sortId: '137', summary: false }" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
      <ux-table-column :field="tableColumns['138'].field" :title="tableColumns['138'].title"
                       :width="tableColumns['138'].width" :visible="tableColumns['138'].visible"
                       :params="{ sortId: '138', summary: false }" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
      <ux-table-column :field="tableColumns['139'].field" :title="tableColumns['139'].title"
                       :width="tableColumns['139'].width" :visible="tableColumns['139'].visible"
                       :params="{ sortId: '139', summary: false }" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
      <ux-table-column :field="tableColumns['140'].field" :title="tableColumns['140'].title"
                       :width="tableColumns['140'].width" :visible="tableColumns['140'].visible"
                       :params="{ sortId: '140', summary: false }" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
      <ux-table-column :field="tableColumns['141'].field" :title="tableColumns['141'].title"
                       :width="tableColumns['141'].width" :visible="tableColumns['141'].visible"
                       :params="{ sortId: '141', summary: false }" tree-node resizable min-width="180px" align="right"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
      <ux-table-column :field="tableColumns['142'].field" :title="tableColumns['142'].title"
                       :width="tableColumns['142'].width" :visible="tableColumns['142'].visible"
                       :params="{ sortId: '142', summary: false }" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
      <ux-table-column :field="tableColumns['143'].field" :title="tableColumns['143'].title"
                       :width="tableColumns['143'].width" :visible="tableColumns['143'].visible"
                       :params="{ sortId: '143', summary: false }" tree-node resizable min-width="180px" align="right"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
      <ux-table-column :field="tableColumns['144'].field" :title="tableColumns['144'].title"
                       :width="tableColumns['144'].width" :visible="tableColumns['144'].visible"
                       :params="{ sortId: '144', summary: false }" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
      <ux-table-column :field="tableColumns['145'].field" :title="tableColumns['145'].title"
                       :width="tableColumns['145'].width" :visible="tableColumns['145'].visible"
                       :params="{ sortId: '145', summary: false }" tree-node resizable min-width="180px" align="right"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
      <ux-table-column :field="tableColumns['146'].field" :title="tableColumns['146'].title"
                       :width="tableColumns['146'].width" :visible="tableColumns['146'].visible"
                       :params="{ sortId: '146', summary: false }" tree-node resizable min-width="180px" align="center"
                       header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
    </ux-grid>
    <el-pagination :total="tableDataTotal" :page-size="search.limit" background :current-page="tableDataPage"
                   :page-sizes="[10, 20, 30, 40, 50, 100]" layout="total, sizes, prev, pager, next, jumper"
                   v-on:size-change="onSizeChange" v-on:current-change="onCurrentChange"
                   class="dc-el-pagination_ElPagination"></el-pagination>
  </el-row>
</template>

<script>
import History from '@/views/components/history'
import EditForm from './payBackDialog.vue'
import QueryTag from '@/views/components/queryTag'
import QueryConditionPanel from '@/views/components/queryConditionPanel'
import ViewColumnsSelect from '@/views/components/DcViewColumnsSelect'
import ExportExcelButton from '@/components/ExportExcelButton'
import OperationIcon from '@/components/OperationIcon'
import {validatenull} from "../../../utils/validate";
import {listPaymentBackPage} from "../../../api/paymentBack/paymentBack";
import CrmContactsForm from "./payBackDialog.vue";
import BaseUI from "../../components/baseUI.vue";
export default {
  extends: BaseUI,
  components: {
    CrmContactsForm,
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
      tableData: [
      ],
      // 查询表格列头
      tableColumns: {

        136: {
          title: '回款计划编号',
          field: 'paymentBackPlanNo',
          visible: true,
          order: 0
        },
        137: {
          title: '关联合同编号',
          field: 'contractCode',
          visible: true,
          order: 1
        },
        138: {
          title: '客户名称',
          field: 'customerName',
          visible: true,
          order: 2
        },
        139: {
          title: '商机名称',
          field: 'opportunityName',
          visible: true,
          order: 3
        },
        140: {
          title: '合同名称',
          field: 'contractName',
          visible: true,
          order: 4
        },
        141: {
          title: '合同金额(元)',
          field: 'contractAmount',
          visible: true,
          order: 5
        },
        142: {
          title: '结束日期',
          field: 'endDate',
          visible: true,
          order: 6
        },
        143: {
          title: '剩余回款金额(元)',
          field: 'oddPaymentBackAmount',
          visible: true,
          order: 7
        },
        144: {
          title: '最新回款日期',
          field: 'newPaymentBackDate',
          visible: true,
          order: 8
        },
        145: {
          title: '最新回款金额(元)',
          field: 'newPaymentBackAmount',
          visible: true,
          order: 9
        },
        146: {
          title: '回款状态',
          field: 'paymentBackStatus',
          visible: true,
          order: 10
        },
        114: {
          title: '操作',
          width: '140px',
          visible: true,
          order: 20
        }
      },
      // 分页属性
      tableDataPage: 1,
      tableDataTotal: 0,
      selectedRows:null, // 选中行数据
      search: {
        // 查询条件   业务表设置的筛选条件
        searchParams: {
          params: [],
          offset: 0,
          limit: 10,
          columnName: '', // 排序字段名
          order: '' // 排序
        },
        backPlanNo: null, // 计划回款编号
        backStatus: null, // 回款状态

      },
      dialogProps: {
        visible: false,
        title: '联系人'
      },
    };
  },
  props:{
    sjId:{
      type:String,
    },
    action:{
      type:String
    }
  },
  methods: {
    editPayBack(){
      if (this.selectedRows === null) {
        this.$message('请选择一条数据！')
        return false;
      }
      this.$refs.crmContactOpenDialog.$emit('openEditDialog',this.sjId)
    },
    viewPayBack(){
      if (this.selectedRows === null) {
        this.$message('请选择一条数据！')
        return false;
      }
      this.$refs.crmContactOpenDialog.$emit('openViewDialog',this.sjId)
    },
    onSortChange(orderby) {
      if (validatenull(orderby.prop)) {
        this.search.searchParams.columnName = ''
        this.search.searchParams.order = ''
      } else {
        this.search.searchParams.columnName = orderby.prop
        this.search.searchParams.order = orderby.order
      }
      this.getList()
    },
    handleRowClick(row) {
      this.selectedRows = row;
    },
    getList() {
      this.setLoad()
      /* 查询参数 和数据权限 */
      this.search.searchParams.params = []
      this.search.searchParams.params.push({
        "columnName": "opportunity_id",
        "queryType": "like",
        "value": `%${this.sjId}%`
      },)
      listPaymentBackPage(this.search)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.tableDataTotal = responseData.data.total
            this.tableData = responseData.data.rows ? responseData.data.rows : []
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
  },
  mounted: function () {
    let _this = this;
    this.$nextTick(() => {
      this.$on('openPayBackDialog', function (sjId) {
        _this.sjId = sjId
        _this.getList()
        _this.dialogProps.visible = true
      })
    })
  }
};
</script>
