<template>
  <el-row class="dc-container" v-loading="loading">
    <edit-form ref="editForm" v-on:save-finished="getList" v-on:before-load="setLoad"
      v-on:after-load="loading = false"></edit-form>
    <el-form ref="queryForm" :model="queryFormData" label-width="110px" class="dc-el-form_ElQueryForm">
      <el-row> <el-col :span="6">
          <el-form-item prop="customerName" label="客户名称:" class="dc-el-form-item_SingleInput">
            <el-select clearable v-model="queryFormData.customerName" placeholder="请选择客户名称" style="width: 100%;"
              @change="changeCustomer">
              <el-option v-for="item in customerOptions" :key="item.id" :label="item.cusName" :value="item.id" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item prop="opportunityName" label="商机名称:" class="dc-el-form-item_SingleInput">
            <el-select clearable v-model="queryFormData.opportunityName" placeholder="请选择商机" style="width: 100%;"
              :disabled="!queryFormData.customerName">
              <el-option v-for="item in businessOptions" :key="item.id" :value="item.id" :label="item.oppName" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item prop="contractName" label="合同名称:" class="dc-el-form-item_SingleInput">
            <el-input v-model="queryFormData.contractName" :maxLength="64" placeholder="合同名称" clearable
              class="dc-el-input_SingleInput"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item prop="paymentBackPlanNo" label="回款计划编号:" class="dc-el-form-item_SingleInput">
            <el-input v-model="queryFormData.paymentBackPlanNo" :maxLength="64" placeholder="回款计划编号" clearable
              class="dc-el-input_SingleInput"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item prop="paymentBackStatus" label="回款状态:" class="dc-el-form-item_SelectInput">
            <el-select v-model="queryFormData.paymentBackStatus" :style="{ width: '100%' }" placeholder="请选择" clearable
              filterable class="dc-el-select_SelectInput">
              <el-option v-for="(item, index) in statusOptions" :key="index" :label="item.name"
                :value="item.name"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="18"> <el-tooltip disabled class="dc-el-tooltip_Button">
            <el-button @click="getList" style="margin-left:20px" icon="el-icon-search"
              class="dc-el-button_Button">查询</el-button>
          </el-tooltip>
          <el-tooltip disabled class="dc-el-tooltip_Button">
            <el-button icon="el-icon-refresh" class="dc-el-button_Button" @click="reset">重置</el-button> </el-tooltip>
        </el-col></el-row>
    </el-form>

    <ux-grid column-key ref="table" :data="tableData" border v-on:sort-change="onSortChange"
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
      <ux-table-column title="操作" :params="{ sortId: '114' }" tree-node resizable width="140px" min-width="140px"
        fixed="right" align="center" header-align="center" class="dc-ux-table-column_ElTableOptColumn">
        <template v-slot:header="scope">
          <span>操作</span>
          <el-popover ref="popover" width="10" trigger="click" placement="bottom" offset="10"
            class="dc-el-popover_ElPopover">
            <view-columns-select v-model="tableColumns" ref="viewColumnsSelect"
              v-on:save-column-view="saveColumn($event, 'table')" v-on:show-all-column="showAllColumn($event, 'table')"
              v-on:show-default-column="showDefaultColumn('table')"
              class="dc-view-columns-select_ViewColumnsSelect"></view-columns-select>
            <template slot="reference">
              <OperationIcon type="primary" content="自定义列" placement="top" icon-name="el-icon-tickets"></OperationIcon>
            </template>
          </el-popover>
          <OperationIcon v-on:click="onExport(tableData)" type="primary" content="导出excel文件" placement="top"
            icon-name="el-icon-download" class="dc-OperationIcon_IconButton"></OperationIcon>
        </template>
        <template slot-scope="scope">
          <OperationIcon v-on:click="onView(scope)" type="info" content="查看" placement="top" icon-name="el-icon-view"
            class="dc-OperationIcon_IconButton"></OperationIcon>
          <OperationIcon v-on:click="onEdit(scope)" type="primary" content="编辑" placement="top" icon-name="el-icon-edit"
            class="dc-OperationIcon_IconButton"></OperationIcon>
        </template>
      </ux-table-column>
    </ux-grid>
    <el-pagination :total="tableDataTotal" :page-size="search.limit" background :current-page="tableDataPage"
      :page-sizes="[10, 20, 30, 40, 50, 100]" layout="total, sizes, prev, pager, next, jumper"
      v-on:size-change="onSizeChange" v-on:current-change="onCurrentChange"
      class="dc-el-pagination_ElPagination"></el-pagination>
  </el-row>
</template>
<script>
import { validatenull } from '@/utils/validate'
import { listPaymentBackPage } from '@/api/paymentBack/paymentBack'
import { treeCrmCustomer } from "../../../api/crmCustomer/crmCustomer";
import ViewColumnsSelect from '@/views/components/DcViewColumnsSelect'
/** 根据用户界面配置import组件 结束 */
import metadata from './metadata'
import ExportExcelButton from '@/components/ExportExcelButton'
import WfMainUI from '@/views/wf/common/wfMainUI'
import OperationIcon from '@/components/OperationIcon'
import EditForm from './form'
import { getDictTypeById } from '@/api/sys/dictType'
import {
  getCrmOppsByCusId
} from '@/api/crmCustomer/crmCustomerOpp'
export default {
  extends: WfMainUI,
  components: {
    /** 根据用户界面配置组件 开始 */
    EditForm,
    ViewColumnsSelect,
    /** 根据用户界面配置组件 结束 */
    ExportExcelButton,
    OperationIcon
  },
  data() {
    return {
      metadata,
      /** 根据用户界面配置生成data数据 开始 */
      queryFormData: {
        customerName: '',
        opportunityName: '',
        contractName: '',
        paymentBackPlanNo: '',
        paymentBackStatus: ''
      },
      tableData: [],
      loading: false,
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
          title: '合同金额',
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
          title: '剩余回款金额',
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
          title: '最新回款金额',
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
      // 选项变量

      /** 根据用户界面配置生成data数据 结束 */
      version: 2,
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
        backStatus: null // 回款状态
      },
      tableId: '1683007003934706801',
      schemeId: '1683007003934706799',
      statusOptions: [],
      // 客户名称选项
      customerOptions: [],
      //商机选项
      businessOptions: []
    }
  },
  computed: {},
  methods: {
    getList() {
      this.setLoad()
      /* 查询参数 和数据权限 */
      this.search.searchParams.params = []
      if (this.queryFormData.contractName) {
        this.search.searchParams.params.push({
          "columnName": "contract_name",
          "queryType": "like",
          "value": `%${this.queryFormData.contractName}%`
        },)
      }
      if (this.queryFormData.customerName) {
        this.search.searchParams.params.push({
          "columnName": "customer_id",
          "queryType": "=",
          "value": this.queryFormData.customerName
        },)
      }
      if (this.queryFormData.opportunityName) {
        this.search.searchParams.params.push({
          "columnName": "opportunity_id",
          "queryType": "like",
          "value": this.queryFormData.opportunityName
        },)
      }
      if (this.queryFormData.contractStatus) {
        this.search.searchParams.params.push({
          "columnName": "contract_status",
          "queryType": "=",
          "value": this.queryFormData.contractStatus
        })
      }
      if (this.queryFormData.paymentBackPlanNo) {
        this.search.backPlanNo = this.queryFormData.paymentBackPlanNo
      }
      if (this.queryFormData.paymentBackStatus) {
        this.search.backStatus = this.queryFormData.paymentBackStatus
      }
      listPaymentBackPage(this.search)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.tableDataTotal = responseData.data.total
            this.tableData = responseData.data.rows ? responseData.data.rows : []
          } else {
            this.showMessage(responseData)
          }
          this.loading = false
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    changeCustomer(val) {
      this.queryFormData.opportunityName = ''
      if (val) {
        this.getBusiness(val)
      }
    },
    // 客户option
    listCustomerOptions() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])
      // 数据权限: 客户管理crm_customer
      this.pushDataPermissions(search_List.params, this.$route.meta.routerId, "1709759135450792386")
      treeCrmCustomer(search_List).then(responseData => {
        if (responseData.code == 100) {
          this.customerOptions = responseData.data.rows || []
        } else {
          this.showMessage(responseData)
        }
      })
    },
    reset() {
      this.queryFormData = {
        customerName: '',
        opportunityName: '',
        contractName: '',
        paymentBackPlanNo: '',
        paymentBackStatus: ''
      }
      this.search.backPlanNo = ''
      this.search.backStatus = ''
      this.getList()
    },
    onSearch() {
      this.search.searchParams.offset = 0
      this.tableDataPage = 1
      this.getList()
    },
    onSizeChange(val) {
      this.tableDataPage = 1
      this.search.searchParams.limit = val
      this.search.searchParams.offset = (this.tableDataPage - 1) * val
      this.getList()
    },
    onCurrentChange(val) {
      this.search.searchParams.offset = (val - 1) * this.search.searchParams.limit
      this.tableDataPage = val
      this.getList()
    },
    async pageInit() {
      this.setLoad()
      this.initOptions(this.queryModel)
      this.onSearch()
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
    getBusiness(val) {
      getCrmOppsByCusId(val).then(res => {
        if (res.code === '100') {
          this.businessOptions = res.data
        }
      })
    },
    // 初始化自定义类型选择框选项变量
    initOptions(This) { },
    getStatus() {
      getDictTypeById('1825521320267440142').then(res => {
        if (res.code === '100') {
          this.statusOptions = res.data.dictItemList
        }
      })
    },
    onView(scope) {
      this.$refs.editForm.$emit('openViewDialog', scope.row.contractId)
    },

    onEdit(scope) {
      this.$refs.editForm.$emit('openEditDialog', scope.row.contractId)
    },
  },
  watch: {},
  mounted() {
    this.getStatus()
    this.pageInit()
    this.columnDrop(this.$refs.table)
    this.listCustomerOptions()
  }
}
</script>
