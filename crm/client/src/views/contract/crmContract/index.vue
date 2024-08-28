<template>
  <el-row class="dc-container" v-loading="loading">
    <history ref="history" :bussObject="curentRow"></history>
    <el-form ref="queryForm" :model="queryFormData" label-width="100px" class="dc-el-form_ElQueryForm">
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
          <el-form-item prop="contractCode" label="合同编号:" class="dc-el-form-item_SingleInput">
            <el-input v-model="queryFormData.contractCode" :maxLength="64" placeholder="合同编号" clearable
              class="dc-el-input_SingleInput"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item prop="contractStatus" label="合同状态:" class="dc-el-form-item_SelectInput">
            <el-select v-model="queryFormData.contractStatus" :style="{ width: '100%' }" placeholder="请选择" clearable
              filterable class="dc-el-select_SelectInput">
              <el-option v-for="item in statusOptions" :key="item.value" :label="item.name"
                :value="item.value"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="18"> <el-tooltip disabled class="dc-el-tooltip_Button">
            <el-button @click="getList" style="margin-left:20px" icon="el-icon-search"
              class="dc-el-button_Button">查询</el-button>
          </el-tooltip>
          <el-tooltip disabled class="dc-el-tooltip_Button">
            <el-button icon="el-icon-refresh" class="dc-el-button_Button" @click="reset">重置</el-button>
          </el-tooltip>

        </el-col></el-row>
    </el-form>
    <el-row gutter="0" type="flex" justify="end" align="top" class="dc-el-row_ElRow">
      <process-panel ref="processPanel" :bizAdd="true" :tenantId="currentUser.company.id" keyPrefix="PROC_"
        procName="合同管理" formKey="wf/common/wfForm.vue#contract/crmContract/form.vue" v-on:save-finished="getList()"
        procTitile="${start_user_name}发起${form.name}XX审批" v-on:v-loading="onVLoading"
        class="dc-process-panel_ProcessPanel"></process-panel>
    </el-row>
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
        :params="{ sortId: '140', summary: false }" tree-node resizable min-width="180px" align="right"
        header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
      <ux-table-column :field="tableColumns['141'].field" :title="tableColumns['141'].title"
        :width="tableColumns['141'].width" :visible="tableColumns['141'].visible"
        :params="{ sortId: '141', summary: false }" tree-node resizable min-width="180px" align="center"
        header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
      <ux-table-column :field="tableColumns['142'].field" :title="tableColumns['142'].title"
        :width="tableColumns['142'].width" :visible="tableColumns['142'].visible"
        :params="{ sortId: '142', summary: false }" tree-node resizable min-width="180px" align="center"
        header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
      <ux-table-column :field="tableColumns['143'].field" :title="tableColumns['143'].title"
        :width="tableColumns['143'].width" :visible="tableColumns['143'].visible"
        :params="{ sortId: '143', summary: false }" tree-node resizable min-width="180px" align="center"
        header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"></ux-table-column>
      <ux-table-column :field="tableColumns['144'].field" :title="tableColumns['144'].title"
        :width="tableColumns['144'].width" :visible="tableColumns['144'].visible"
        :params="{ sortId: '144', summary: false }" tree-node resizable min-width="180px" align="center"
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
          <OperationIcon v-on:click="onShowHistory(scope)" type="info" content="历史记录" placement="top"
            icon-name="el-icon-info" class="dc-OperationIcon_IconButton"></OperationIcon>
          <OperationIcon type="danger" v-if="scope.row.contractStatus != 1" content="删除" placement="top"
            icon-name="el-icon-delete" @click="deleteData(scope)" class="dc-OperationIcon_IconButton"></OperationIcon>
        </template>
      </ux-table-column>
    </ux-grid>
    <el-pagination :total="tableDataTotal" :page-size="search.limit" background :current-page="tableDataPage"
      :page-sizes="[10, 20, 30, 40, 50, 100]" layout="total, sizes, prev, pager, next, jumper"
      v-on:size-change="onSizeChange" v-on:current-change="onCurrentChange"
      class="dc-el-pagination_ElPagination"></el-pagination>
    <comment ref="commentForm" class="dc-comment_ElImport"></comment>
    <component ref="wfForm" v-on:save-finished="getList()" v-on:after-wfForm-load="afterWfFormload()" :is="wfForm"
      class="dc-component_ElImport"></component>
  </el-row>
</template>
<script>
import { validatenull } from '@/utils/validate'

import { listCrmContractPage, deleteCrmContract } from '@/api/contract/crmContract'
import { getHistProcInstance } from '@/api/wf/procinst'
import { getProcessByKey, getStartForm } from '@/api/wf/processdefinition'

/** 根据用户界面配置import组件 开始 */
import History from '@/views/components/history'

import processPanel from '@/views/wf/common/processPanel'
import ViewColumnsSelect from '@/views/components/DcViewColumnsSelect'
import Comment from '@/views/wf/common/comment'
/** 根据用户界面配置import组件 结束 */
import metadata from './metadata'
import ExportExcelButton from '@/components/ExportExcelButton'
import WfMainUI from '@/views/wf/common/wfMainUI'
import OperationIcon from '@/components/OperationIcon'
import EditForm from './form'
import { getDictTypeById } from '@/api/sys/dictType'
import { treeCrmCustomer } from "../../../api/crmCustomer/crmCustomer";
import {
  getCrmOppsByCusId
} from '@/api/crmCustomer/crmCustomerOpp'
export default {
  extends: WfMainUI,
  components: {
    /** 根据用户界面配置组件 开始 */
    History,

    EditForm,
    processPanel,
    ViewColumnsSelect,
    Comment,
    /** 根据用户界面配置组件 结束 */
    ExportExcelButton,
    OperationIcon
  },
  data() {
    return {
      wfForm: null, // 工作流表单
      currentRow: null, // 当前查看申请
      metadata,

      /** 根据用户界面配置生成data数据 开始 */
      queryFormData: {
        contractName: '',
        contractCode: '',
        contractStatus: '',
        customerName: '',
        opportunityName: ''
      },
      tableData: [],
      // 查询表格列头
      tableColumns: {
        136: {
          title: '合同编码',
          field: 'contractCode',
          visible: true,
          order: 0
        },
        137: {
          title: '合同名称',
          field: 'contractName',
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
          title: '商机',
          field: 'opportunityName',
          visible: true,
          order: 3
        },
        140: {
          title: '合同金额',
          field: 'contractAmount',
          visible: true,
          order: 4
        },
        141: {
          title: '签约日期',
          field: 'signDate',
          visible: true,
          order: 5
        },
        142: {
          title: '合同状态',
          field: 'conStatus',
          visible: true,
          order: 6
        },
        143: {
          title: '创建人',
          field: 'createBy',
          visible: false,
          order: 7
        },
        144: {
          title: '创建时间',
          field: 'createDate',
          visible: false,
          order: 8
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
      version: 4,
      search: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
      searchList: [],
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
      this.search.params = []
      if (this.queryFormData.contractName) {
        this.search.params.push({
          "columnName": "contract_name",
          "queryType": "like",
          "value": `%${this.queryFormData.contractName}%`
        },)
      }
      if (this.queryFormData.contractCode) {
        this.search.params.push({
          "columnName": "contract_code",
          "queryType": "like",
          "value": `%${this.queryFormData.contractCode}%`
        },)
      }
      if (this.queryFormData.customerName) {
        this.search.params.push({
          "columnName": "customer_id",
          "queryType": "=",
          "value": this.queryFormData.customerName
        },)
      }
      if (this.queryFormData.opportunityName) {
        this.search.params.push({
          "columnName": "opportunity_id",
          "queryType": "=",
          "value": this.queryFormData.opportunityName
        },)
      }
      if (this.queryFormData.contractStatus) {
        this.search.params.push({
          "columnName": "contract_status",
          "queryType": "=",
          "value": this.queryFormData.contractStatus
        })
      }
      listCrmContractPage(this.search)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.tableDataTotal = responseData.data.total
            let arr = responseData.data.rows ? responseData.data.rows : []
            arr.map(item => {
              let obj = this.statusOptions.find(it => it.value === item.contractStatus)
              item.conStatus = obj ? obj.name : item.contractStatus
              return item
            })
            this.tableData = JSON.parse(JSON.stringify(arr))
          } else {
            this.showMessage(responseData)
          }
          this.loading = false
        })
        .catch((error) => {
          this.outputError(error)
          this.loading = false
        })
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
    changeCustomer(val) {
      this.queryFormData.opportunityName = ''
      if (val) {
        this.getBusiness(val)
      }
    },
    getBusiness(val) {
      getCrmOppsByCusId(val).then(res => {
        if (res.code === '100') {
          this.businessOptions = res.data
        }
      })
    },
    //删除
    deleteData(scope) {
      this.$confirm("确定要删除该条数据吗？", "确认", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.setLoad();
          deleteCrmContract(scope.row)
            .then(responseData => {
              if (responseData.code == 100) {
                this.getList();
                this.showMessage({
                  type: "success",
                  msg: "删除成功"
                });
              } else {
                this.showMessage(responseData);
              }
              this.loading = false;
            })
            .catch(error => {
              this.outputError(error);
            });
        })
        .catch(() => { });
    },
    onSearch() {
      this.search.offset = 0
      this.tableDataPage = 1
      this.getList()
    },
    onSizeChange(val) {
      this.tableDataPage = 1
      this.search.limit = val
      this.search.offset = (this.tableDataPage - 1) * val
      this.getList()
    },
    onCurrentChange(val) {
      this.search.offset = (val - 1) * this.search.limit
      this.tableDataPage = val
      this.getList()
    },
    async pageInit() {
      this.setLoad()
      this.initOptions(this.queryModel)
      this.onSearch()
    },
    reset() {
      this.queryFormData = {
        contractName: '',
        contractCode: '',
        contractStatus: '',
        customerName: '',
        opportunityName: ''
      }
      this.getList()
    },
    onView(scope) {
      this.setLoad()
      getHistProcInstance(scope.row.procInst)
        .then((responseData) => {
          if (responseData.hasOwnProperty('processDefinitionId')) {
            let definitionId = responseData.processDefinitionId
            if (definitionId) {
              getStartForm(definitionId).then((startFormResp) => {
                if (startFormResp.hasOwnProperty('key')) {
                  if (startFormResp.key) {
                    this.currentRow = {
                      formKey: startFormResp.key,
                      definitionId: definitionId,
                      row: scope.row
                    }
                    this.loadWfForm(startFormResp.key)
                  } else {
                    this.showMessage({
                      type: 'warning',
                      msg: '流程未配置form，请联系管理员。'
                    })
                  }
                } else {
                  this.showMessage(startFormResp)
                }
                this.loading = false
              })
            } else {
              this.showMessage({
                type: 'warning',
                msg: '流程实例没有找到流程定义。'
              })
            }
          } else {
            this.showMessage(responseData)
          }
          this.loading = false
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    // 重写dcMain的方法
    onShowHistory(scope) {
      this.$refs.commentForm.$emit('openComment', scope.row.procInst)
    },
    afterWfFormload() {
      this.$refs.wfForm.$emit('openViewBizDialog', this.currentRow.formKey, this.currentRow.definitionId, this.currentRow.row)
    },
    onVLoading(loading) {
      loading ? this.setLoad() : this.loading = false
    },
    onSortChange(orderby) {
      if (validatenull(orderby.prop)) {
        this.search.columnName = ''
        this.search.order = ''
      } else {
        this.search.columnName = orderby.prop
        this.search.order = orderby.order
      }
      this.getList()
    },

    // 初始化自定义类型选择框选项变量
    initOptions(This) { },
    getStatus() {
      getDictTypeById('1723152166331441166').then(res => {
        if (res.code === '100') {
          this.statusOptions = res.data.dictItemList
        }
      })
    }
  },
  watch: {},
  mounted() {
    this.getStatus()
    this.pageInit()
    this.columnDrop(this.$refs.table)
    this.listCustomerOptions()
    // 商机管理用
    sessionStorage.removeItem("opportunityId");
    sessionStorage.removeItem("opportunityCustomer");
    sessionStorage.removeItem("opportunityPayment");

  }
}
</script>
