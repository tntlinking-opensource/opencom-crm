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
    <opportunity-move
      ref="opportunityMove"
      v-on:save-finished="getList"
      v-on:before-load="setLoad"
      v-on:after-load="resetLoad"
      :permission="permission"></opportunity-move>
    <send-pay-quotation
    ref="payQuotation"
    v-on:save-finished="getList"
    ></send-pay-quotation>
    <!--    <el-row gutter="0" type="flex" justify="start" align="top" class="dc-el-row_ElRow">
          <el-col :span="24">
            <query-tag
              ref="queryTag"
              v-model="queryEditObject"
              :moreParm="moreParm"
              :routerId="$route && $route.meta.routerId"
              :isQueryConditionPanel="isQueryConditionPanel"
              v-on:queryTagPanel="onQueryTagPanel"
              v-on:queryTagClick="onQueryTagClick"
              v-on:search="onSearch"
              class="dc-query-tag_ElConditionTag"
            ></query-tag>
          </el-col>
          <el-col :span="6">
            <div style="text-align: right">
              <el-button-group class="dc-el-button-group_ButtonGroup">
                <el-tooltip effect="dark" content="搜索" placement="top">
                  <el-button v-on:click="onSearch" type="primary" icon="el-icon-search"></el-button>
                </el-tooltip>
                <el-tooltip effect="dark" content="重置" placement="top">
                  <el-button
                    v-on:click="isQueryConditionPanel ? $refs.conditionPanel.reset() : $refs.queryForm.resetFields()"
                    type="primary"
                    icon="el-icon-refresh-right"
                  ></el-button>
                </el-tooltip>
                <el-tooltip effect="dark" content="更多" placement="top">
                  <el-button v-on:click="onQueryConditionPanel('query')" type="primary" icon="el-icon-d-arrow-right"></el-button>
                </el-tooltip>
              </el-button-group>
            </div>
          </el-col>
        </el-row>-->
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
          <el-form-item
            prop="customer.id"
            label="客户"
            class="dc-el-form-item_CascaderInput"
          >
            <el-select
              v-model="queryFormData.customer.id"
              :style="{ width: '100%' }"
              placeholder="请选择客户"
              clearable
              filterable
              class="dc-el-select_SelectInput"
            >
              <el-option
                v-for="(item, index) in customerOptions"
                :key="index"
                :label="item.cusName"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
<!--          <el-form-item prop="customer" label="客户名称" class="dc-el-form-item_SingleInput">
            <el-input
              v-model="queryFormData.customer"
              :maxLength="300"
              placeholder="请输入客户"
              clearable
              class="dc-el-input_SingleInput"
            ></el-input>
          </el-form-item>-->
        </el-col>
        <el-col :span="6">
          <el-form-item prop="oppName" label="商机名称" class="dc-el-form-item_SingleInput">
            <el-input
              v-model="queryFormData.oppName"
              :maxLength="300"
              placeholder="请输入商机名称"
              clearable
              class="dc-el-input_SingleInput"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item
            prop="oppStage"
            label="销售阶段"
            class="dc-el-form-item_SelectInput"
          >
            <el-select
              v-model="queryFormData.oppStage"
              :style="{ width: '100%' }"
              placeholder="请选择"
              clearable
              filterable
              class="dc-el-select_SelectInput"
            >
              <el-option
                v-for="(item, index) in oppStageOptions1"
                :key="index"
                :label="item.name"
                :value="item.name"
              ></el-option>
            </el-select>
          </el-form-item>
<!--          <el-form-item prop="oppStage" label="销售阶段" class="dc-el-form-item_SingleInput">
            <el-input
              v-model="queryFormData.oppStage"
              :maxLength="100"
              placeholder="请输入销售阶段"
              clearable
              class="dc-el-input_SingleInput"
            ></el-input>
          </el-form-item>-->
        </el-col>
        <el-col :span="6">
          <div style="text-align: left">
            <el-button-group class="dc-el-button-group_ButtonGroup">
              <el-button v-on:click="onSearch" type="primary" icon="el-icon-search">查询</el-button>
              <el-button
                v-on:click="isQueryConditionPanel ? $refs.conditionPanel.reset() : $refs.queryForm.resetFields();getList()"
                type="info"
                icon="el-icon-refresh-right"
              >
                重置
              </el-button>
            </el-button-group>
          </div>
        </el-col>
      </el-row>
    </el-form>
    <el-row gutter="0" type="flex" justify="end" align="top" class="dc-el-row_ElRow">
      <el-tooltip v-show="permission.add" disabled class="dc-el-tooltip_Button">
        <el-button type="primary" icon="el-icon-plus" v-on:click="onCreate">创建商机</el-button>
      </el-tooltip>
      <el-tooltip disabled class="dc-el-tooltip_Button">
        <el-button type="primary" icon="el-icon-edit-outline" class="dc-el-button_Button" v-on:click="onEdit()">
          编辑
        </el-button>
      </el-tooltip>
      <el-tooltip disabled class="dc-el-tooltip_Button">
        <el-button type="danger" icon="el-icon-delete" v-on:click="onDelete(null,1)" class="dc-el-button_Button">删除
        </el-button>
      </el-tooltip>
      <el-tooltip disabled class="dc-el-tooltip_Button">
        <el-button type="primary" icon="el-icon-top-right" v-on:click="onMulChange" class="dc-el-button_Button">转移
        </el-button>
      </el-tooltip>
      <el-tooltip disabled class="dc-el-tooltip_Button">
        <el-button type="primary" icon="el-icon-download" v-on:click="onMulExport" class="dc-el-button_Button">导出
        </el-button>
      </el-tooltip>
      <el-tooltip disabled class="dc-el-tooltip_Button">
        <el-button type="primary" icon="el-icon-s-promotion" v-on:click="onMulSend" class="dc-el-button_Button">
          发送报价单
        </el-button>
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
      :tree-config="{ children: '', indent: 20, accordion: false, line: false, showIcon: true, iconOpen: '', iconClose: '' }"
      class="dc-ux-grid_QueryTable"
    >
      <ux-table-column
        :params="{ sortId: '251', summary: false }"
        type="checkbox"
        tree-node
        resizable
        min-width="80px"
        fixed="left"
        align="center"
        header-align="center"
        class="dc-ux-table-column_ElTableCheckBoxColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['130'].field"
        :title="tableColumns['130'].title"
        :width="tableColumns['130'].width"
        :visible="tableColumns['130'].visible"
        :params="{ sortId: '130', summary: false }"
        tree-node
        resizable
        min-width="180px"
        align="center"
        header-align="center"
        sortable
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
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['134'].field"
        :title="tableColumns['134'].title"
        :width="tableColumns['134'].width"
        :visible="tableColumns['134'].visible"
        :params="{ sortId: '134', summary: false }"
        tree-node
        resizable
        min-width="100px"
        align="right"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['132'].field"
        :title="tableColumns['132'].title"
        :width="tableColumns['132'].width"
        :visible="tableColumns['132'].visible"
        :params="{ sortId: '132', summary: false }"
        tree-node
        resizable
        min-width="120px"
        align="center"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
      :field="tableColumns['136'].field"
      :title="tableColumns['136'].title"
      :width="tableColumns['136'].width"
      :visible="tableColumns['136'].visible"
      :params="{ sortId: '136', summary: false }"
      tree-node
      resizable
      min-width="120px"
      align="center"
      header-align="center"
      sortable
      show-overflow
      class="dc-ux-table-column_ElTableColumn"
    ></ux-table-column>
      <ux-table-column
        :field="tableColumns['135'].field"
        :title="tableColumns['135'].title"
        :width="tableColumns['135'].width"
        :visible="tableColumns['135'].visible"
        :params="{ sortId: '135', summary: false }"
        tree-node
        resizable
        min-width="120px"
        align="center"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['131'].field"
        :title="tableColumns['131'].title"
        :width="tableColumns['131'].width"
        :visible="tableColumns['131'].visible"
        :params="{ sortId: '131', summary: false }"
        tree-node
        resizable
        min-width="120px"
        align="center"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['115'].field"
        :title="tableColumns['115'].title"
        :width="tableColumns['115'].width"
        :visible="tableColumns['115'].visible"
        :params="{ sortId: '115', summary: false }"
        tree-node
        resizable
        min-width="120px"
        align="center"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      >
        <template slot-scope="{row}">
          {{(fangfa(row.oppQuotation)) }}
        </template>
      </ux-table-column>
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
        sortable
        show-overflow
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
        align="right"
        header-align="center"
        sortable
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
        min-width="120px"
        align="center"
        header-align="center"
        sortable
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
        min-width="120px"
        align="right"
        header-align="center"
        sortable
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
        min-width="120px"
        align="center"
        header-align="center"
        sortable
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
        min-width="120px"
        align="center"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        title="操作"
        :params="{ sortId: '114' }"
        tree-node
        resizable
        width="140px"
        min-width="140px"
        fixed="right"
        align="center"
        header-align="center"
        class="dc-ux-table-column_ElTableOptColumn"
      >
        <template v-slot:header="scope">
          <span>操作</span>
          <el-popover ref="popover" width="10" trigger="click" placement="bottom" offset="10"
                      class="dc-el-popover_ElPopover">
            <view-columns-select
              v-model="tableColumns"
              ref="viewColumnsSelect"
              v-on:save-column-view="saveColumn($event, 'table')"
              v-on:show-all-column="showAllColumn($event, 'table')"
              v-on:show-default-column="showDefaultColumn('table')"
              class="dc-view-columns-select_ViewColumnsSelect"
            ></view-columns-select>
            <template slot="reference">
              <OperationIcon type="primary" content="自定义列" placement="top"
                             icon-name="el-icon-tickets"></OperationIcon>
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
            v-on:click="onEdit(scope)"
            type="primary"
            v-show="permission.edit"
            content="编辑"
            placement="top"
            icon-name="el-icon-edit"
            class="dc-OperationIcon_IconButton"
          ></OperationIcon>
          <OperationIcon
            v-on:click="onCopy(scope)"
            type="primary"
            v-show="permission.add"
            content="复制"
            placement="top"
            icon-name="el-icon-document"
            class="dc-OperationIcon_IconButton"
          ></OperationIcon>
          <OperationIcon
            v-on:click="onDelete(scope,2)"
            type="danger"
            v-show="permission.remove"
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
      :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      v-on:size-change="onSizeChange"
      v-on:current-change="onCurrentChange"
      class="dc-el-pagination_ElPagination"
    ></el-pagination>

    <!-- 弹出框 -->
    <!--    <el-dialog
          title="弹出框标题"
          :visible.sync="dialogVisible"
          width="50%"
          @closed="handleDialogClose"
        >
          &lt;!&ndash; 上半部分：自动带出数据的文本框 &ndash;&gt;
          <el-form :model="formData" label-width="80px">
            <el-form-item label="文本框数据">
              <el-input v-model="formData.textData" clearable></el-input>
            </el-form-item>
          </el-form>

          &lt;!&ndash; 下半部分：列表数据 &ndash;&gt;
          <el-table :data="tableData2" style="width: 100%">
            &lt;!&ndash; 定义表格列 &ndash;&gt;
            <el-table-column prop="id" label="ID"></el-table-column>
            <el-table-column prop="name" label="名称"></el-table-column>
            <el-table-column prop="description" label="描述"></el-table-column>
          </el-table>
        </el-dialog>-->
  </el-row>
</template>
<script>
import {validatenull} from '@/utils/validate'

import {listCrmOpportunityPage, deleteCrmOpportunity} from '@/api/crmBusiness/crmOpportunity'

import {listResourcePermission} from '@/api/admin/common/permission'

/** 根据用户界面配置import组件 开始 */
import History from '@/views/components/history'
import EditForm from './form'
import QueryTag from '@/views/components/queryTag'
import QueryConditionPanel from '@/views/components/queryConditionPanel'
import ViewColumnsSelect from '@/views/components/DcViewColumnsSelect'


/** 根据用户界面配置import组件 结束 */
import metadata from './metadata'
import ExportExcelButton from '@/components/ExportExcelButton'
import DcMain from '@/views/components/dcMain'
import OperationIcon from '@/components/OperationIcon'
import {listCrmOpportunityAll} from "../../../api/crmBusiness/crmOpportunity";
import OpportunityMove from "./opportunityMove.vue";
import SendPayQuotation from "./sendPayQuotation.vue";
import {treeCrmCustomer} from "../../../api/crmCustomer/crmCustomer";
import {getDictTypeById} from "../../../api/sys/dictType";
import {listDictItemPage} from "../../../api/sys/dictItem";

export default {
  extends: DcMain,
  components: {
    SendPayQuotation,
    OpportunityMove,
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
      permission: {
        view: false,
        add: false,
        edit: false,
        remove: false,
        export: false
      },
      dialogVisible: false, // 控制弹出框的可见性
      formData: {
        textData: '', // 文本框的值
      },
      tableData2: [
        {id: 1, name: '数据1', description: '描述1'},
        {id: 2, name: '数据2', description: '描述2'},
        // 添加更多数据
      ],

      selectedRows: [], // 选中行数据

      metadata,

      /** 根据用户界面配置生成data数据 开始 */
      queryFormData: {
        customer: {
          id: null,
          cusName: null,
        },
        oppName: '', // 商机名称
        oppStage: '' // 销售阶段
      },
      tableData: [],
      // 查询表格列头
      tableColumns: {
        130: {
          title: '商机名称',
          field: 'oppName',
          visible: true,
          order: 0
        },
        129: {
          title: '客户名称',
          field: 'customer.cusName',
          visible: true,
          order: 1
        },
        126: {
          title: '客户名称1',
          field: 'customer.id',
          visible: false,
          order: 1
        },
        134: {
          title: '销售金额',
          field: 'oppPay',
          visible: true,
          order: 2
        },
        132: {
          title: '销售阶段',
          field: 'oppStage',
          visible: true,
          order: 3
        },
        136: {
          title: '所属部门',
          field: 'oppDepartmentId',
          visible: false,
          order: 4
        },
        135: {
          title: '所属部门',
          field: 'oppDepartment',
          visible: true,
          order: 4
        },
        131: {
          title: '商机来源',
          field: 'oppSource',
          visible: true,
          order: 5
        },
        137: {
          title: '创建者',
          field: 'createBy',
          visible: true,
          order: 6
        },
        138: {
          title: '创建时间',
          field: 'createDate',
          visible: true,
          order: 7
        },
        139: {
          title: '更新者',
          field: 'updateBy',
          visible: false,
          order: 8
        },
        140: {
          title: '更新时间',
          field: 'updateDate',
          visible: false,
          order: 9
        },
        141: {
          title: '备注信息',
          field: 'remarks',
          visible: true,
          order: 10
        },
        114: {
          title: '操作',
          width: '140px',
          visible: false,
          order: 11
        },
        115: {
          title: '是否已发送报价单',
          field: 'oppQuotation',
          visible: true,
          order: 12
        }
      },
      // 分页属性
      tableDataPage: 1,
      tableDataTotal: 0,
      // 选项变量

      /** 根据用户界面配置生成data数据 结束 */
      version: 3,
      search: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
      searchList: [],
      tableId: '1709759135450792726',
      schemeId: '1709759135450792724',
      // 客户options
      customerOptions:[],
      // 销售阶段
      oppStageOptions1:[],
    }
  },
  computed: {},
  methods: {
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

    oppStageOption() {
      let search_List = {
        params: []
      }
      search_List.params.push({'columnName': 'dictType.code', 'queryType': '=', 'value': "CRM_DICT_OPPS_STAGE"})
      listDictItemPage(search_List).then((res) => {
        if (res.code == 100) {
          this.oppStageOptions1 = res.data.rows;
        } else {
          this.showMessage(res)
        }
      })
    },
    fangfa(val) {
      return val === "1" ? "是" : "否"
    },
    getList() {
      this.setLoad()
      /* 查询参数 和数据权限 */
      this.search.params = []
      if (this.isQueryConditionPanel) {
        this.search.params = this.search.params.concat(this.compositeCondition())
      } else {
        this.search.params.push({
          columnName: 'customer_id',
          queryType: 'like',

          value: !validatenull(this.queryFormData.customer.id) ? this.queryFormData.customer.id : null
        })

        this.search.params.push({
          columnName: 'opp_name',
          queryType: 'like',

          value: !validatenull(this.queryFormData.oppName) ? this.queryFormData.oppName : null
        })

        this.search.params.push({
          columnName: 'opp_stage',
          queryType: 'like',

          value: !validatenull(this.queryFormData.oppStage) ? this.queryFormData.oppStage : null
        })
      }
      // 数据权限: 商机管理crm_opportunity
      this.pushDataPermissions(this.search.params, this.$route.meta.routerId, this.tableId)
      listCrmOpportunityPage(this.search)
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
    async getListAll() {
      this.setLoad()
      let tableData = []
      this.selectedRows = []

      /* 查询参数 和数据权限 */
      this.search.params = []
      if (this.isQueryConditionPanel) {
        this.search.params = this.search.params.concat(this.compositeCondition())
      } else {
        this.search.params.push({
          columnName: 'customer_id',
          queryType: 'like',

          value: !validatenull(this.queryFormData.customer.id) ? this.queryFormData.customer.id : null
        })

        this.search.params.push({
          columnName: 'opp_name',
          queryType: 'like',

          value: !validatenull(this.queryFormData.oppName) ? this.queryFormData.oppName : null
        })

        this.search.params.push({
          columnName: 'opp_stage',
          queryType: 'like',

          value: !validatenull(this.queryFormData.oppStage) ? this.queryFormData.oppStage : null
        })
      }
      // 数据权限: 商机管理crm_opportunity
      this.pushDataPermissions(this.search.params, this.$route.meta.routerId, this.tableId)
      await listCrmOpportunityAll(this.search)
        .then((responseData) => {
          if (responseData.code == 100) {
            tableData = responseData.data
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
      return tableData;
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
      listResourcePermission(this.$route.meta.routerId)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.permission.view = responseData.data.find((item) => {
              return item.permission === 'crmOpportunity:read'
            })
            this.permission.export = responseData.data.find((item) => {
              return item.permission === 'crmOpportunity:export'
            })

            this.permission.add = responseData.data.find((item) => {
              return item.permission === 'crmOpportunity:create'
            })
            this.permission.edit = responseData.data.find((item) => {
              return item.permission === 'crmOpportunity:update'
            })
            this.permission.remove = responseData.data.find((item) => {
              return item.permission === 'crmOpportunity:delete'
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
      this.$refs.editForm.$emit('openViewDialog', scope.row.id)
    },
    /**
     * 获取点击行数据
     */
    handleRowClick(row) {
      this.selectedRows = row;
    },

    /**
     *  转移
     */
    onMulChange() {
      if (this.selectedRows.length !== 1) {
        this.showMessage({
          type: 'success',
          msg: '请选择一条数据'
        })
      }else{
        this.$refs.opportunityMove.$emit("openDialogView", this.selectedRows[0])
      }
    },
    /**
     *  发送报价单
     * @returns {Promise<void>}
     */
    onMulSend(){
      if (this.selectedRows.length !== 1) {
        this.showMessage({
          type: 'success',
          msg: '请选择一条数据'
        })
      }else{
        this.$refs.payQuotation.$emit("openDialogView", this.selectedRows[0])
      }
    },
    async onMulExport() {
      let tableData = []
      if (this.selectedRows.length != 0) {
        tableData = (this.selectedRows)
      } else {
        tableData = await this.getListAll()
      }
      // 没有数据时，提示
      if (validatenull(tableData)) {
        this.$alert('没有导出数据！', '提示', {
          type: 'info'
        })
        return
      }
      for (let i = 0; i < tableData.length; i++) {
        tableData[i].orderNumber = i + 1
      }
      this.loading = true
      import('@/vendor/Export2Excel').then(excel => {
        var filterVal = []
        filterVal.push("orderNumber")
        filterVal.push("oppName")
        filterVal.push("customer.cusName")
        filterVal.push("oppPay")
        filterVal.push("oppStage")
        filterVal.push("oppDepartment")
        filterVal.push("oppSource")
        filterVal.push("createBy")
        filterVal.push("createDate")
        var heads = []
        heads.push("序号")
        heads.push("商机名称")
        heads.push("客户名称")
        heads.push("销售金额")
        heads.push("销售阶段")
        heads.push("所属部门")
        heads.push("商机来源")
        heads.push("创建人")
        heads.push("创建时间")
        const data = this.formatJson(filterVal, tableData)
        excel.export_json_to_excel({
          header: heads,
          data,
          filename: '商机明细',
          autoWidth: true
        })
        this.loading = false
      }).catch(error => {
        console.log('-----------export excel error-------------')
        console.error(error)
        this.loading = false
      })
    },

    onCreate() {
      this.$refs.editForm.$emit('openAddDialog')
    },
    onEdit(scope) {
      if (scope === undefined) {
        if (this.selectedRows.length === 0 || this.selectedRows.length > 1) {
          this.showMessage({
            type: 'success',
            msg: '请选择数据'
          })
          return false;
        }
        this.$refs.editForm.$emit('openEditDialog', this.selectedRows[0].id)
      } else {
        this.$refs.editForm.$emit('openEditDialog', scope.row.id)
      }
    },
    onCopy(scope) {
      this.$refs.editForm.$emit('openCopyDialog', scope.row.id)
    },
    onDelete(scope, status) {
      if (status === 1) {
        if (this.selectedRows.length === 0) {
          this.showMessage({
            type: 'success',
            msg: '请选择数据'
          })
          return false;
        }
      } else {
        this.selectedRows = scope.row;
      }
      this.$confirm('确定删除吗？', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.setLoad()
        let i = 0;
        this.selectedRows.forEach(items => {
          deleteCrmOpportunity(items)
            .then((responseData) => {
              i++;
              if (responseData.code == 100) {
                if (i == 1) {
                  this.getList()
                  this.showMessage({
                    type: 'success',
                    msg: '删除成功'
                  })
                }
              } else {
                this.showMessage(responseData)
              }
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        })

      })
        .catch(() => {
        })
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

    openDialog() {
      // 打开弹出框
      this.dialogVisible = true;
    },
    handleDialogClose() {
      // 处理弹出框关闭事件
      this.dialogVisible = false;
    },
  },
  watch: {},
  mounted() {
    this.pageInit()
    this.listCustomerOptions()
    this.oppStageOption()

    this.columnDrop(this.$refs.table)
  }
}
</script>
