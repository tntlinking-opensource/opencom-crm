<style scoped>
.text-left {
  text-align: left;
}
.flex {
  display: flex;
  justify-content: center;
}
.center {
  align-items: center;
}
.ml {
  margin-left: 5px;
}
</style>
<template>
  <el-row class="dc-container" v-loading="loading">
    <history ref="history" :bussObject="curentRow"></history>
    <el-form
      ref="queryForm"
      :model="queryFormData"
      label-width="100px"
      v-if="!isQueryConditionPanel"
      class="dc-el-form_ElQueryForm"
    >
      <el-row>
        <el-col :span="6">
          <el-form-item prop="" label="客户名称" class="dc-el-form-item_SingleInput">
            <el-input
              v-model="queryFormData.cusName"
              :maxLength="-1"
              placeholder="请输入"
              clearable
              class="dc-el-input_SingleInput"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item prop="" label="合同名称" class="dc-el-form-item_SingleInput">
            <el-input
              v-model="queryFormData.contractName"
              :maxLength="-1"
              placeholder="请输入"
              clearable
              class="dc-el-input_SingleInput"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item prop="" label="开票状态" class="dc-el-form-item_SelectInput">
            <el-select
              v-model="queryFormData.status"
              :style="{ width: '100%' }"
              placeholder="请选择"
              clearable
              filterable
              class="dc-el-select_SelectInput"
            >
              <el-option
                v-for="(item, index) in selectOptions"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6" class="dc-el-form-item_SelectInput">
          <el-tooltip disabled class="dc-el-tooltip_Button">
            <el-button @click="getList" style="margin-left:20px" icon="el-icon-search"
              class="dc-el-button_Button" type="primary">查询</el-button>
          </el-tooltip>
          <el-tooltip disabled class="dc-el-tooltip_Button">
            <el-button icon="el-icon-refresh" class="dc-el-button_Button" @click="handleReset">重置</el-button>
          </el-tooltip>
        </el-col>
        <el-col :span="24"></el-col>
      </el-row>
    </el-form>
    <el-row gutter="0" type="flex" justify="end" align="top" class="dc-el-row_ElRow" style="top: 30px">
      <process-panel ref="processPanel" :bizAdd="true" :tenantId="currentUser.company.id" keyPrefix="PROC_FP"
        procName="发票管理" formKey="wf/common/wfForm.vue#invoice/crmInvoice/form.vue"
        procTitile="${start_user_name}发起${form.name}XX审批" v-on:v-loading="onVLoading"
        class="dc-process-panel_ProcessPanel"></process-panel>
    </el-row>
    <ux-grid
      column-key
      ref="table"
      :data="tableData"
      border
      v-on:sort-change="onSortChange"
      v-on:header-dragend="onWidthChange($refs.table)"
      :tree-config="{ children: '', indent: 20, accordion: false, line: false, showIcon: true, iconOpen: '', iconClose: '' }"
      class="dc-ux-grid_QueryTable"
    >
    <!-- <ux-table-column
        :params="{ sortId: '136', summary: false }"
        type="checkbox"
        tree-node
        resizable
        min-width="80px"
        fixed="left"
        align="center"
        header-align="center"
        class="dc-ux-table-column_ElTableCheckBoxColumn"
      ></ux-table-column> -->
      <ux-table-column
        :field="tableColumns['137'].field"
        :title="tableColumns['137'].title"
        :width="tableColumns['137'].width"
        :visible="tableColumns['137'].visible"
        :params="{ sortId: '137', summary: false }"
        tree-node
        resizable
        min-width="120px"
        align="center"
        header-align="center"
        show-overflow
        show-overflow-tooltip
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['138'].field"
        :title="tableColumns['138'].title"
        :width="tableColumns['138'].width"
        :visible="tableColumns['138'].visible"
        :params="{ sortId: '138', summary: false }"
        tree-node
        resizable
        min-width="120px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['139'].field"
        :title="tableColumns['139'].title"
        :width="tableColumns['139'].width"
        :visible="tableColumns['139'].visible"
        :params="{ sortId: '139', summary: false }"
        tree-node
        resizable
        min-width="180px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['140'].field"
        :title="tableColumns['140'].title"
        :width="tableColumns['140'].width"
        :visible="tableColumns['140'].visible"
        :params="{ sortId: '140', summary: false }"
        tree-node
        resizable
        min-width="180px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['141'].field"
        :title="tableColumns['141'].title"
        :width="tableColumns['141'].width"
        :visible="tableColumns['141'].visible"
        :params="{ sortId: '141', summary: false }"
        tree-node
        resizable
        min-width="180px"
        align="right"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['142'].field"
        :title="tableColumns['142'].title"
        :width="tableColumns['142'].width"
        :visible="tableColumns['142'].visible"
        :params="{ sortId: '142', summary: false }"
        tree-node
        resizable
        min-width="180px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      >
        <template slot-scope="{ row }">
          {{ handleKeyValue(row.invoiceType, invoiceType) }}
        </template>
      </ux-table-column>
      <ux-table-column
        :field="tableColumns['143'].field"
        :title="tableColumns['143'].title"
        :width="tableColumns['143'].width"
        :visible="tableColumns['143'].visible"
        :params="{ sortId: '143', summary: false }"
        tree-node
        resizable
        min-width="180px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      >
      <template slot-scope="{ row }">
        {{ handleKeyValue(row.status, selectOptions) }}
      </template>
    </ux-table-column>
      <ux-table-column
        :field="tableColumns['144'].field"
        :title="tableColumns['144'].title"
        :width="tableColumns['144'].width"
        :visible="tableColumns['144'].visible"
        :params="{ sortId: '144', summary: false }"
        tree-node
        resizable
        min-width="180px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['145'].field"
        :title="tableColumns['145'].title"
        :width="tableColumns['145'].width"
        :visible="tableColumns['145'].visible"
        :params="{ sortId: '145', summary: false }"
        tree-node
        resizable
        min-width="180px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['146'].field"
        :title="tableColumns['146'].title"
        :width="tableColumns['146'].width"
        :visible="tableColumns['146'].visible"
        :params="{ sortId: '146', summary: false }"
        tree-node
        resizable
        min-width="180px"
        align="center"
        fixed="right"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      >
        <template slot-scope="scope">
          <span class="flex center">
            <OperationIcon v-on:click="onView(scope)" type="info" content="查看" placement="top" icon-name="el-icon-view"
              class="dc-OperationIcon_IconButton"></OperationIcon>
            <OperationIcon v-on:click="onShowHistory(scope)" type="info" content="历史记录" placement="top"
              icon-name="el-icon-info" class="dc-OperationIcon_IconButton"></OperationIcon>
            <OperationIcon type="danger" content="删除" placement="top" icon-name="el-icon-delete" @click="deleteData(scope)"
              class="dc-OperationIcon_IconButton"></OperationIcon>
            <img src="../../../assets/images/invoice.png" class="ml" @click="handleInvoice(scope)" style="width: 15px;height: 15px;" alt="">
          </span>
        </template>
      </ux-table-column>
    </ux-grid>
    <el-pagination
      :total="tableDataTotal"
      :page-size="search.limit"
      background
      :current-page="tableDataPage"
      :page-sizes="[10, 20, 30, 40, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      v-on:size-change="onSizeChange"
      v-on:current-change="onCurrentChange"
      class="dc-el-pagination_ElPagination"
    ></el-pagination>
    <comment ref="commentForm" class="dc-comment_ElImport"></comment>
    <component
      ref="wfForm"
      v-on:save-finished="getTaskList()"
      v-on:after-wfForm-load="afterWfFormload()"
      :is="wfForm"
      class="dc-component_ElImport"
    ></component>
    <print-box ref="printer" preview style="display: none;">
      <template>
      <div>
        <h3 style="text-align: center;">发票邮寄信息联</h3>
        <table class="table-print" style="font-size: 16px">
            <tbody>
              <tr style="line-height: 40px;">
                <td>公司名称：</td>
                <td colspan="4">{{ form.companyName || '' }}</td>
              </tr>
              <tr style="line-height: 40px;">
                <td>地址：</td>
                <td colspan="4">{{ form.invoiceAddressTel || '' }}</td>
              </tr>
              <tr style="line-height: 40px;">
                <td>邮编：</td>
                <td colspan="4">{{ form.postalCode || '' }}</td>
              </tr>
              <tr style="line-height: 40px;">
                <td>收件人：</td>
                <td colspan="4">{{ form.recipient || '' }}</td>
              </tr>
              <tr style="line-height: 40px;">
                <td>联系电话：</td>
                <td colspan="4">{{ form.phone || '' }}</td>
              </tr>
              <tr style="line-height: 40px;">
                <td>发票号码及票面金额明细：</td>
                <td colspan="4">{{ form.taxNum || '' }} {{ form.invoiceAmount || '' }}</td>
              </tr>
              <tr style="line-height: 40px;">
                <td>邮寄发票张数：</td>
                <td></td>
                <td>发票票面总金额：</td>
                <td colspan="2">{{ form.invoiceAmount || '' }}</td>
              </tr>
            </tbody>
        </table>
      </div>
      <div style="padding: 40px 80px 20px;">
        <div style="border-bottom: 1px dashed "></div>
      </div>
      <div>
        <h2 style="text-align: center;">发票收讫回执</h2>
        <table class="table-print" style="font-size: 18px">
            <tbody>
              <tr style="line-height: 40px;">
                <td>发票号码</td>
                <td colspan="2">{{ form.taxNum || '' }}</td>
                <td>开票日期</td>
                <td colspan="2">{{ form.createDate || '' }}</td>
              </tr>
              <tr style="line-height: 40px;">
                <td>客户名称</td>
                <td colspan="5">{{ form.cusName || '' }}</td>
              </tr>
              <tr style="line-height: 40px;">
                <td>发票内容</td>
                <td colspan="5">{{ form.invoiceContent || '' }}</td>
              </tr>
              <tr>
                <td>发票种类</td>
                <td colspan="5">{{ form.invoiceKind ? handleKeyValue(form.invoiceKind, invoiceKind) : '' }}</td>
              </tr>
              <tr style="line-height: 40px;">
                <td colspan="2">金额：{{ form.invoiceAmount || '' }}</td>
                <td>税率：{{ form.taxRate ?  `${form.taxRate}%` : '' }}</td>
                <td>税额：{{ form.taxes || '' }}</td>
                <td colspan="2">价税合计：{{ form.excludTax || '' }}</td>
              </tr>
              <tr style="line-height: 40px;">
                <td colspan="3" class="text-left"><b>公司盖章：</b></td>
                <td colspan="3" class="text-left"><b>发票收讫单位签字/盖章：</b></td>
              </tr>
            </tbody>
        </table>
      </div>
      <h4 style="text-align: center;">感谢贵方的大力配合与支持！我们将一如既往地为贵公司提供优质的服务！</h4>
      <h4 style="text-align: center;">鉴于本公司管理的需求，烦请在收到回执单后，签字，盖章，并交还我公司市场人员。谢谢！</h4>
    </template>
    </print-box>
  </el-row>
</template>
<script>
import {
  validatenull
} from '@/utils/validate'

import {
  listCrmInvoicePage,
  deleteCrmInvoice
} from '@/api/crmInvoice/crmInvoice'

import {
  listResourcePermission
} from '@/api/admin/common/permission'
import {
  getHistProcInstance
} from '@/api/wf/procinst'
import {
  getProcessByKey,
  getStartForm
} from '@/api/wf/processdefinition'

/** 根据用户界面配置import组件 开始 */
import History from "@/views/components/history"
import QueryTag from "@/views/components/queryTag"
import QueryConditionPanel from "@/views/components/queryConditionPanel"
import processPanel from "@/views/wf/common/processPanel"
import ViewColumnsSelect from "@/views/components/DcViewColumnsSelect"
import Comment from "@/views/wf/common/comment"
import PrintBox from './PrintBox.vue'
/** 根据用户界面配置import组件 结束 */
import metadata from './metadata'
import ExportExcelButton from '@/components/ExportExcelButton'
import WfMainUI from '@/views/wf/common/wfMainUI'
import OperationIcon from '@/components/OperationIcon'
import { getDictTypeById } from '@/api/sys/dictType'
export default {
  extends: WfMainUI,
  components: {
    /** 根据用户界面配置组件 开始 */
    History,
    QueryTag,
    QueryConditionPanel,
    processPanel,
    ViewColumnsSelect,
    Comment,
    PrintBox,
    /** 根据用户界面配置组件 结束 */
    ExportExcelButton,
    OperationIcon
  },
  data() {
    return {
      wfForm: null, // 工作流表单
      currentRow: null, // 当前查看申请

      permission: {
        view: false,
        add: false,
        edit: false,
        remove: false,
        export: false
      },
      invoiceType: [],
      metadata,

      /** 根据用户界面配置生成data数据 开始 */
      queryFormData: {},
      tableData: [],
      // 查询表格列头
      tableColumns: {
        // 136: {
        //   isShow: true,
        //   visible: true,
        //   order: 0
        // },
        137: {
          title: '客户名称',
          field: 'cusName',
          visible: true,
          order: 1
        },
        138: {
          title: '合同名称',
          field: 'contract.contractName',
          visible: true,
          order: 2
        },
        139: {
          title: '合同编号',
          field: 'contract.contractCode',
          visible: true,
          order: 3
        },
        140: {
          title: '票面客户单位',
          field: 'parCustUnit',
          visible: true,
          order: 4
        },
        141: {
          title: '发票金额',
          field: 'invoiceAmount',
          visible: true,
          order: 5
        },
        142: {
          title: '发票类型',
          field: 'invoiceType',
          visible: true,
          order: 6
        },
        143: {
          title: '申请状态',
          field: 'status',
          visible: true,
          order: 7
        },
        144: {
          title: '申请人',
          field: 'createBy',
          visible: true,
          order: 8
        },
        145: {
          title: '申请时间',
          field: 'createDate',
          visible: true,
          order: 9
        },
        146: {
          title: '操作',
          field: 'createTime',
          visible: true,
          order: 10
        }
      },
      // 分页属性
      tableDataPage: 1,
      tableDataTotal: 0,
      // 选项变量
      // 开票状态选项
      selectOptions: [],
      /** 根据用户界面配置生成data数据 结束 */
      version: 2,
      form: {},
      search: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
      searchList: [],
      tableId: '1740772218121937029',
      schemeId: '1740772218121937027'
    }
  },
  mounted() {
    this.pageInit()
    this.handleStatus()
    this.columnDrop(this.$refs.table)
  },
  methods: {
    handleInvoice ({ row }) {
      this.form = {
        ...row,
        ...row.contract
      };
      this.$nextTick(() => {
        this.$refs.printer.print();
      })
    },
    handleReset () {
      this.queryFormData = {};
      this.getList();
    },
    deleteData(scope) {
      this.$confirm("确定要删除该条数据吗？", "确认", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.setLoad();
          deleteCrmInvoice(scope.row)
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
              this.loading = false;
              this.outputError(error);
            });
        })
        .catch(() => { });
    },
    handleStatus () {
      // 开票状态
      getDictTypeById('1743166610852663495').then(res => {
        if (res.code === '100') {
          this.selectOptions = res.data.dictItemList
        }
      })
      // 发票类型
      getDictTypeById('1743166610852663463').then(res => {
        if (res.code === '100') {
          this.invoiceType = res.data.dictItemList
        }
      })
      // 发票种类
      getDictTypeById('1766000701405178798').then(res => {
        if (res.code === '100') {
          this.invoiceKind = res.data.dictItemList
        }
      })
    },
    handleKeyValue (v, arr) {
      const current = arr.findIndex(item => item.value === v)
      return (current > -1 && arr[current].name) || ''
    },
    getList() {
      this.setLoad()
      /* 查询参数 和数据权限 */
      this.search.params = []
      if (this.queryFormData.cusName) {
        this.search.params.push({
          "columnName": "cus_name",
          "queryType": "like",
          "value": `%${this.queryFormData.cusName}%`
        },)
      }
      if (this.queryFormData.contractName) {
        this.search.params.push({
          "columnName": "contract_name",
          "queryType": "like",
          "value": `%${this.queryFormData.contractName}%`
        },)
      }
      if (this.queryFormData.status) {
        this.search.params.push({
          "columnName": "status",
          "queryType": "like",
          "value": `%${this.queryFormData.status}%`
        },)
      }
      // if (this.isQueryConditionPanel) {
      //   this.search.params = this.search.params.concat(this.compositeCondition())
      // } else {

      // }
      // 数据权限: 发票管理crm_invoice
      this.pushDataPermissions(this.search.params, this.$route.meta.routerId, this.tableId)
      listCrmInvoicePage(this.search).then(responseData => {
        if (responseData.code == 100) {
          this.tableDataTotal = responseData.data.total
          this.tableData = responseData.data.rows ? responseData.data.rows : []
        } else {
          this.showMessage(responseData)
        }
        this.loading = false;
        this.resetLoad()
      }).catch(error => {
        this.loading = false;
        this.outputError(error)
      })
    },
    onSearch() {
      if (this.isQueryConditionPanel) {
        this.search.offset = 0
        this.tableDataPage = 1
        this.getList()
      } else {
        this.$refs['queryForm'].validate(valid => {
          if (valid) {
            this.search.offset = 0
            this.tableDataPage = 1
            this.getList()
          } else {
            return false
          }
        })
      }
    },
    onSizeChange(val) {
      this.tableDataPage = 1
      this.search.limit = val;
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
      listResourcePermission(this.$route.meta.routerId).then(responseData => {
        if (responseData.code == 100) {
          this.permission.view = responseData.data.find(item => {
            return item.permission === 'crmInvoice:read'
          })
          this.permission.export = responseData.data.find(item => {
            return item.permission === 'crmInvoice:export'
          })

          this.permission.add = responseData.data.find(item => {
            return item.permission === 'crmInvoice:create'
          })
          this.permission.edit = responseData.data.find(item => {
            return item.permission === 'crmInvoice:update'
          })
          this.permission.remove = responseData.data.find(item => {
            return item.permission === 'crmInvoice:delete'
          })

        } else {
          this.showMessage(responseData)
        }
        this.resetLoad()
      }).catch(error => {
        this.outputError(error)
      })
    },
    onView(scope) {
      this.setLoad()
      getHistProcInstance(scope.row.procInst).then(responseData => {
        if (responseData.hasOwnProperty('processDefinitionId')) {
          let definitionId = responseData.processDefinitionId
          if (definitionId) {
            getStartForm(definitionId).then(startFormResp => {
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
              this.resetLoad()
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
        this.resetLoad()
      }).catch(error => {
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
      loading ? this.setLoad() : this.resetLoad()
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
    initOptions(This) {

    },
  },
  watch: {

  }
}
</script>
