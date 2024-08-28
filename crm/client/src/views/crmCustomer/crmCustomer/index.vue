<template>
  <el-row class="dc-container" v-loading="loading">
    <history ref="history" :bussObject="curentRow"></history>
    <edit-form
      ref="editForm"
      v-on:save-finished="getList"
      v-on:before-load="setLoad"
      v-on:after-load="resetLoad"
      :permission="permission"
    ></edit-form>
    <edit-s-form
      ref="editSForm"
      v-on:save-finished="getList"
      v-on:before-load="getList"
      v-on:after-load="getList"
      :permission="permission"
    ></edit-s-form>
    <query-condition-panel
      v-if="isQueryConditionPanel"
      ref="conditionPanel"
      :conditionColumns="
        metadata.find((item) => {
          return item.type == 'main'
        }).conditionPanel
      "
      v-model="moreParm"
      :routerId="$route.meta.routerId"
      class="dc-query-condition-panel_ElConditionPanel"
    ></query-condition-panel>
    <el-form
      ref="queryForm"
      :model="queryFormData"
      label-width="100px"
      v-if="!isQueryConditionPanel"
      class="dc-el-form_ElQueryForm"
    >
      <el-row>
        <el-col :span="6">
          <el-form-item prop="cusName" label="客户名称" class="dc-el-form-item_SingleInput">
            <el-input
              v-model="queryFormData.cusName"
              :maxLength="300"
              placeholder="请输入客户名称"
              clearable
              class="dc-el-input_SingleInput"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item prop="cusStage" label="客户阶段" class="dc-el-form-item_SingleInput">
            <el-select
              v-model="queryFormData.cusStage"
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
        <el-col :span="6">
          <el-form-item prop="cusSource" label="客户来源" class="dc-el-form-item_SingleInput">
            <el-select
              v-model="queryFormData.cusSource"
              :style="{ width: '100%' }"
              placeholder="请选择"
              clearable
              filterable
              class="dc-el-select_SelectInput"
            >
              <el-option
                v-for="(item, index) in custmerSource"
                :key="item.value"
                :label="item.name"
                :value="item.value"
              ></el-option>
            </el-select>
            
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-tooltip disabled class="dc-el-tooltip_Button">
            <el-button @click="onSearch" style="margin-left:20px" icon="el-icon-search"
              class="dc-el-button_Button" type="primary">查询</el-button>
          </el-tooltip>
          <el-tooltip disabled class="dc-el-tooltip_Button">
            <el-button icon="el-icon-refresh" class="dc-el-button_Button" @click="handleReset">重置</el-button>
          </el-tooltip>
        </el-col>
      </el-row>
    </el-form>
    <el-row gutter="0" type="flex" justify="end" align="top" class="dc-el-row_ElRow">
      <el-tooltip v-show="permission.add" disabled class="dc-el-tooltip_Button">
        <el-button type="primary" icon="el-icon-plus" v-on:click="onCreate">添加</el-button>
      </el-tooltip>
      <el-tooltip disabled class="dc-el-tooltip_Button">
        <el-button type="primary" icon="el-icon-download" v-on:click="onExport" class="dc-el-button_Button">导出</el-button>
      </el-tooltip>
      <el-tooltip disabled class="dc-el-tooltip_Button">
        <el-button type="primary" icon="el-icon-top-right" v-on:click="handleRevcort" class="dc-el-button_Button">转移</el-button>
      </el-tooltip>
    </el-row>
    <ux-grid
      column-key
      ref="table"
      :data="tableData"
      @selection-change="handleRowClick"
      border
      v-on:sort-change="onSortChange"
      v-on:header-dragend="onWidthChange($refs.table)"
      :tree-config="{
        children: 'children',
        indent: 20,
        accordion: false,
        line: false,
        showIcon: true,
        iconOpen: '',
        iconClose: ''
      }"
      show-summary
      :summary-method="summaryMethod"
      class="dc-ux-grid_QueryTable"
    >
      <ux-table-column
        :params="{ sortId: '142', summary: false }"
        type="checkbox"
        tree-node
        resizable
        min-width="50px"
        fixed="left"
        align="center"
        header-align="center"
        class="dc-ux-table-column_ElTableCheckBoxColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['117'].field"
        :title="tableColumns['117'].title"
        :width="tableColumns['117'].width"
        :visible="tableColumns['117'].visible"
        :params="{ sortId: '117', summary: false }"
        tree-node
        resizable
        min-width="180px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['130'].field"
        :title="tableColumns['130'].title"
        :width="tableColumns['130'].width"
        :visible="tableColumns['130'].visible"
        :params="{ sortId: '130', summary: false }"
        tree-node
        resizable
        width="100px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      >
        <template slot-scope="{ row }">
          {{ handleKeyValue(row.cusStage, selectOptions) }}
        </template>
      </ux-table-column>
      <ux-table-column
        :field="tableColumns['118'].field"
        :title="tableColumns['118'].title"
        :width="tableColumns['118'].width"
        :visible="tableColumns['118'].visible"
        :params="{ sortId: '118', summary: false }"
        tree-node
        resizable
        width="100px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      >
        <template slot-scope="{ row }">
          {{ handleKeyValue(row.cusType, customeType) }}
        </template>
      </ux-table-column>
      <ux-table-column
        :field="tableColumns['119'].field"
        :title="tableColumns['119'].title"
        :width="tableColumns['119'].width"
        :visible="tableColumns['119'].visible"
        :params="{ sortId: '119', summary: false }"
        tree-node
        resizable
        width="100px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      >
        <template slot-scope="{ row }">
          {{ handleKeyValue(row.cusSource, custmerSource) }}
        </template>
      </ux-table-column>
      <ux-table-column
        :field="tableColumns['116'].field"
        :title="tableColumns['116'].title"
        :width="tableColumns['116'].width"
        :visible="tableColumns['116'].visible"
        :params="{ sortId: '116', summary: false }"
        tree-node
        resizable
        width="110px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['120'].field"
        :title="tableColumns['120'].title"
        :width="tableColumns['120'].width"
        :visible="tableColumns['120'].visible"
        :params="{ sortId: '120', summary: false }"
        tree-node
        resizable
        width="120px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['121'].field"
        :title="tableColumns['121'].title"
        :width="tableColumns['121'].width"
        :visible="tableColumns['121'].visible"
        :params="{ sortId: '121', summary: false }"
        tree-node
        resizable
        width="150px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['122'].field"
        :title="tableColumns['122'].title"
        :width="tableColumns['122'].width"
        :visible="tableColumns['122'].visible"
        :params="{ sortId: '122', summary: false }"
        tree-node
        resizable
        min-width="180px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['123'].field"
        :title="tableColumns['123'].title"
        :width="tableColumns['123'].width"
        :visible="tableColumns['123'].visible"
        :params="{ sortId: '123', summary: false }"
        tree-node
        resizable
        min-width="180px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['124'].field"
        :title="tableColumns['124'].title"
        :width="tableColumns['124'].width"
        :visible="tableColumns['124'].visible"
        :params="{ sortId: '124', summary: false }"
        tree-node
        resizable
        width="110px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['125'].field"
        :title="tableColumns['125'].title"
        :width="tableColumns['125'].width"
        :visible="tableColumns['125'].visible"
        :params="{ sortId: '125', summary: false }"
        tree-node
        resizable
        width="110px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['126'].field"
        :title="tableColumns['126'].title"
        :width="tableColumns['126'].width"
        :visible="tableColumns['126'].visible"
        :params="{ sortId: '126', summary: false }"
        tree-node
        resizable
        width="110px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['127'].field"
        :title="tableColumns['127'].title"
        :width="tableColumns['127'].width"
        :visible="tableColumns['127'].visible"
        :params="{ sortId: '127', summary: false }"
        tree-node
        resizable
        width="110px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['128'].field"
        :title="tableColumns['128'].title"
        :width="tableColumns['128'].width"
        :visible="tableColumns['128'].visible"
        :params="{ sortId: '128', summary: false }"
        tree-node
        resizable
        width="120px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['129'].field"
        :title="tableColumns['129'].title"
        :width="tableColumns['129'].width"
        :visible="tableColumns['129'].visible"
        :params="{ sortId: '129', summary: false }"
        tree-node
        resizable
        min-width="120px"
        align="center"
        header-align="center"
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        title="操作"
        :params="{ sortId: '112' }"
        tree-node
        resizable
        width="160px"
        min-width="160px"
        fixed="right"
        align="left"
        header-align="center"
        class="dc-ux-table-column_ElTableOptColumn"
      >
        <template v-slot:header="scope">
          <span>操作</span>
          <el-popover ref="popover" width="10" trigger="click" placement="bottom" offset="0" class="dc-el-popover_ElPopover">
            <view-columns-select
              v-model="tableColumns"
              ref="viewColumnsSelect"
              v-on:save-column-view="saveColumn($event, 'table')"
              v-on:show-all-column="showAllColumn($event, 'table')"
              v-on:show-default-column="showDefaultColumn('table')"
              class="dc-view-columns-select_ViewColumnsSelect"
            ></view-columns-select>
            <template slot="reference">
              <OperationIcon type="primary" content="自定义列" placement="top" icon-name="el-icon-tickets"></OperationIcon>
            </template>
          </el-popover>
          <OperationIcon
            v-on:click="onExport(tableData)"
            type="primary"
            v-show="permission.export"
            content="导出excel文件"
            placement="top"
            icon-name="el-icon-download"
            class="dc-OperationIcon_IconButton"
          ></OperationIcon>
        </template>
        <template slot-scope="scope">
          <OperationIcon
            v-on:click="onView(scope)"
            type="info"
            v-show="permission.view"
            content="查看"
            placement="top"
            icon-name="el-icon-view"
            class="dc-OperationIcon_IconButton"
          ></OperationIcon>
          <OperationIcon
            v-on:click="onCreate(scope)"
            type="primary"
            v-show="permission.add"
            content="添加下级"
            placement="top"
            icon-name="el-icon-circle-plus-outline"
            class="dc-OperationIcon_IconButton"
          ></OperationIcon>
          <OperationIcon
            v-on:click="onEdit(scope)"
            type="primary"
            v-show="permission.edit"
            content="编辑"
            placement="top"
            icon-name="el-icon-edit"
            class="dc-OperationIcon_IconButton"
          ></OperationIcon>
          <!-- <OperationIcon
            v-on:click="onCopy(scope)"
            type="primary"
            v-show="permission.add"
            content="复制"
            placement="top"
            icon-name="el-icon-document"
            class="dc-OperationIcon_IconButton"
          ></OperationIcon> -->
          <OperationIcon
            v-on:click="onDelete(scope)"
            type="danger"
            v-show="permission.remove && (!scope.row.children || scope.row.children.length <= 0)"
            content="删除"
            placement="top"
            icon-name="el-icon-delete"
            class="dc-OperationIcon_IconButton"
          ></OperationIcon>
          <OperationIcon
            v-on:click="onShowHistory(scope)"
            type="info"
            v-show="permission.view"
            content="历史记录"
            placement="top"
            icon-name="el-icon-info"
            class="dc-OperationIcon_IconButton"
          ></OperationIcon>
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
    <translate-customer
      ref="TranslateCustomer"
      @save-finished="getList"
      @before-load="setLoad"
      @after-load="resetLoad"
    ></translate-customer>
  </el-row>
</template>
<script>
import { validatenull } from '@/utils/validate'

import { treeCrmCustomer, deleteCrmCustomer, listCrmCustomerPage } from '@/api/crmCustomer/crmCustomer'

import { listResourcePermission } from '@/api/admin/common/permission'

/** 根据用户界面配置import组件 开始 */
import History from '@/views/components/history'
import EditForm from './form'
import EditSForm from './edit'
import QueryTag from '@/views/components/queryTag'
import QueryConditionPanel from '@/views/components/queryConditionPanel'
import ViewColumnsSelect from '@/views/components/DcViewColumnsSelect'
/** 根据用户界面配置import组件 结束 */
import metadata from './metadata'
import ExportExcelButton from '@/components/ExportExcelButton'
import DcMain from '@/views/components/dcMain'
import OperationIcon from '@/components/OperationIcon'
import { getDictTypeById } from '@/api/sys/dictType'
import TranslateCustomer from './translateCustomer.vue'
export default {
  extends: DcMain,
  components: {
    /** 根据用户界面配置组件 开始 */
    History,
    EditForm,
    EditSForm,
    TranslateCustomer,

    QueryTag,
    QueryConditionPanel,
    ViewColumnsSelect,
    /** 根据用户界面配置组件 结束 */
    ExportExcelButton,
    OperationIcon
  },
  data() {
    return {
      selections: [],
      isVisible: false,
      permission: {
        view: false,
        add: false,
        edit: false,
        remove: false,
        export: false
      },
      metadata,

      /** 根据用户界面配置生成data数据 开始 */
      queryFormData: {
        cusName: '', // 客户名称
        cusStage: '', // 客户阶段
        cusSource: '' // 客户来源
      },
      tableData: [],
      // 查询表格列头
      tableColumns: {
        142: {
          isShow: true,
          visible: true,
          order: 0
        },
        117: {
          title: '客户名称',
          field: 'cusName',
          visible: true,
          order: 1
        },
        130: {
          title: '客户阶段',
          field: 'cusStage',
          visible: true,
          order: 2
        },
        118: {
          title: '客户类型',
          field: 'cusType',
          visible: true,
          order: 3
        },
        119: {
          title: '客户来源',
          field: 'cusSource',
          visible: true,
          order: 4
        },
        116: {
          title: '上级客户',
          field: 'parent.cusName',
          visible: false,
          order: 5
        },
        120: {
          title: '联系人',
          field: 'cusContacts',
          visible: true,
          order: 6
        },
        121: {
          title: '联系方式',
          field: 'cusMobile',
          visible: true,
          order: 7
        },
        122: {
          title: '所在区域',
          field: 'cusRegion',
          visible: true,
          order: 8
        },
        123: {
          title: '详细地址',
          field: 'cusAddress',
          visible: true,
          order: 9
        },
        124: {
          title: '负责人',
          field: 'cusResponsible',
          visible: true,
          order: 10
        },
        125: {
          title: '创建者',
          field: 'createBy',
          visible: false,
          order: 11
        },
        126: {
          title: '创建时间',
          field: 'createDate',
          visible: false,
          order: 12
        },
        127: {
          title: '更新者',
          field: 'updateBy',
          visible: false,
          order: 13
        },
        128: {
          title: '更新时间',
          field: 'updateDate',
          visible: false,
          order: 14
        },
        129: {
          title: '备注信息',
          field: 'remarks',
          visible: false,
          order: 15
        },
        112: {
          title: '操作',
          width: '160px',
          isShow: true,
          visible: true,
          order: 16
        }
      },
      tableDataTotal: 0,

      // 选项变量

      /** 根据用户界面配置生成data数据 结束 */
      version: 7,

      search: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
      searchList: [],
      selectOptions: [],
      custmerSource: [],
      customeType: [],
      tableId: '1709759135450792386',
      schemeId: '1709759135450792384'
    }
  },
  methods: {
    onSearch() {
      if (this.isQueryConditionPanel) {
        this.search.offset = 0
        this.tableDataPage = 1
        this.getList()
      } else {
        this.$refs['queryFormData'].validate(valid => {
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
    handleRevcort () {
      if (!this.selections.length) {
        this.$message({
          type: 'success',
          message: '请先选择客户'
        })
        return;
      }
      if (this.selections.length > 1) {
        this.$message({
          type: 'success',
          message: '一次只能转移一个客户'
        })
        return;
      }
      this.$refs.TranslateCustomer.$emit("openDialogView", this.selections[0])
    },
    handleRowClick (row) {
      this.selections = row;
    },
    handleKeyValue (v, arr) {
      const current = arr.findIndex(item => item.value === v)
      return (current > -1 && arr[current].name) || ''
    },
    handleGetOptions () {
      // 客户阶段
      getDictTypeById('1705054496995033199').then(res => {
        if (res.code === '100') {
          this.selectOptions = res.data.dictItemList
        }
      })
      // 客户来源
      getDictTypeById('1705054496995033113').then(res => {
        if (res.code === '100') {
          this.custmerSource = res.data.dictItemList
        }
      })
      // 客户类型
      getDictTypeById('1705054496995033109').then(res => {
        if (res.code === '100') {
          this.customeType = res.data.dictItemList
        }
      })
      
    },
    handleReset () {
      this.queryFormData = {};
      this.onSearch();
    },
    getList() {
      this.setLoad()
      /* 查询参数 和数据权限 */
      this.search.params = []
      if (this.isQueryConditionPanel) {
        this.search.params = this.search.params.concat(this.compositeCondition())
      } else {
        this.search.params.push({
          columnName: 'cus_name',
          queryType: 'like',

          value: !validatenull(this.queryFormData.cusName) ? this.queryFormData.cusName : null
        })

        this.search.params.push({
          columnName: 'cus_stage',
          queryType: 'like',

          value: !validatenull(this.queryFormData.cusStage) ? this.queryFormData.cusStage : null
        })

        this.search.params.push({
          columnName: 'cus_source',
          queryType: 'like',

          value: !validatenull(this.queryFormData.cusSource) ? this.queryFormData.cusSource : null
        })
      }
      // 数据权限: 客户管理crm_customer
      this.pushDataPermissions(this.search.params, this.$route.meta.routerId, this.tableId)
      treeCrmCustomer(this.search)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.tableData = responseData.data.rows ? responseData.data.rows : []
            this.tableDataTotal = responseData.data.total || 0
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    onSearch() {
      if (this.isQueryConditionPanel) {
        this.getList()
      } else {
        this.$refs['queryForm'].validate((valid) => {
          if (valid) {
            this.getList()
          } else {
            return false
          }
        })
      }
    },
    async pageInit() {
      this.setLoad()
      this.initOptions(this.queryModel)
      this.onSearch()
      listResourcePermission(this.$route.meta.routerId)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.permission.view = responseData.data.find((item) => {
              return item.permission === 'crmCustomer:read'
            })
            this.permission.export = responseData.data.find((item) => {
              return item.permission === 'crmCustomer:export'
            })

            this.permission.add = responseData.data.find((item) => {
              return item.permission === 'crmCustomer:create'
            })
            this.permission.edit = responseData.data.find((item) => {
              return item.permission === 'crmCustomer:update'
            })
            this.permission.remove = responseData.data.find((item) => {
              return item.permission === 'crmCustomer:delete'
            })
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    onView(scope) {
      this.$refs.editForm.$emit('openViewDialog', scope.row)
    },

    onCreate(scope) {
      this.$refs.editForm.$emit('openAddDialog', scope.row)
    },
    onEdit(scope) {
      this.$refs.editSForm.$emit('openEditDialog', scope.row.id)
    },
    onCopy(scope) {
      this.$refs.editForm.$emit('openCopyDialog', scope.row.id)
    },
    onDelete(scope) {
      this.$confirm('确定删除吗？', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.setLoad()
          deleteCrmCustomer(scope.row)
            .then((responseData) => {
              if (responseData.code == 100) {
                this.getList()
                this.showMessage({
                  type: 'success',
                  msg: '删除成功'
                })
              } else {
                this.showMessage(responseData)
              }
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        })
        .catch(() => {})
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
    initOptions(This) {}
  },
  watch: {},
  mounted() {
    this.pageInit()
    this.handleGetOptions()

    this.columnDrop(this.$refs.table)
  }
}
</script>
