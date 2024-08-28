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
    <!--<el-row gutter="0" type="flex" justify="start" align="top" class="dc-el-row_ElRow">
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
          <el-form-item prop="cplName" label="线索池名称" class="dc-el-form-item_SingleInput">
            <el-input
              v-model="queryFormData.cplName"
              :maxLength="120"
              placeholder="请输入线索池名称"
              clearable
              class="dc-el-input_SingleInput"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item prop="cplResponsible" label="线索池负责人" class="dc-el-form-item_SingleInput">
            <el-input
              v-model="queryFormData.cplResponsible"
              :maxLength="120"
              placeholder="请输入线索池负责人"
              clearable
              class="dc-el-input_SingleInput"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12" style="float:right">
          <el-tooltip disabled class="dc-el-tooltip_Button">
            <el-button v-on:click="onSearch" type="primary" icon="el-icon-search" class="dc-el-button_Button">搜索</el-button>
          </el-tooltip>
          <el-tooltip effect="dark" content="重置" placement="top">
            <el-button
              v-on:click="searchclear"
              type="primary"
              icon="el-icon-refresh-right"
            >重置</el-button>
          </el-tooltip>
        </el-col>
      </el-row>
    </el-form>
    <el-row gutter="0" type="flex" justify="end" align="top" class="dc-el-row_ElRow">
      <el-tooltip v-show="permission.add" disabled class="dc-el-tooltip_Button">
        <el-button type="primary" icon="el-icon-plus" v-on:click="onCreate">添加</el-button>
      </el-tooltip>
      <el-tooltip v-show="permission.add" disabled class="dc-el-tooltip_Button">
        <el-button type="primary" icon="el-icon-plus" v-on:click="incorporate">合并</el-button>
      </el-tooltip>
    </el-row>
    <ux-grid
      column-key
      ref="table"
      :data="tableData"
      border
      v-on:sort-change="onSortChange"
      v-on:header-dragend="onWidthChange($refs.table)"
      :tree-config="{ children: '', indent: 20, accordion: false, line: false, showIcon: true, iconOpen: '', iconClose: '' }"
      @selection-change="handleSelectionChange"
      class="dc-ux-grid_QueryTable"
    >
      <ux-table-column
        :params="{ sortId: '251', summary: false }"
        :width='80'
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
        title="序号"
        :params="{ sortId: '142', summary: false }"
        :width="tableColumns['142'].width"
        type="index"
        tree-node
        resizable
        min-width="50px"
        fixed="left"
        align="center"
        header-align="center"
        class="dc-ux-table-column_ElTableNumColumn"
      ></ux-table-column>
      <!--<ux-table-column
        :field="tableColumns['111'].field"
        :title="tableColumns['111'].title"
        :width="tableColumns['111'].width"
        :visible="tableColumns['111'].visible"
        :params="{ sortId: '111', summary: false }"
        tree-node
        resizable
        min-width="180px"
        align="left"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>-->
      <ux-table-column
        :field="tableColumns['111'].field"
        :title="tableColumns['111'].title"
        :width="tableColumns['111'].width"
        :visible="tableColumns['111'].visible"
        :params="{ sortId: '111', summary: false }"
        tree-node
        resizable
        min-width="80px"
        align="center"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['118'].field"
        :title="tableColumns['118'].title"
        :width="tableColumns['118'].width"
        :visible="tableColumns['118'].visible"
        :params="{ sortId: '118', summary: false }"
        tree-node
        resizable
        min-width="80px"
        align="center"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['119'].field"
        :title="tableColumns['119'].title"
        :width="tableColumns['119'].width"
        :visible="tableColumns['119'].visible"
        :params="{ sortId: '119', summary: false }"
        tree-node
        resizable
        min-width="80px"
        align="center"
        header-align="center"
        sortable
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
        min-width="80px"
        align="center"
        header-align="center"
        sortable
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
        min-width="80px"
        align="center"
        header-align="center"
        sortable
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
        min-width="80px"
        align="center"
        header-align="center"
        sortable
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
        min-width="80px"
        align="center"
        header-align="center"
        sortable
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
        min-width="80px"
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
        min-width="80px"
        align="center"
        header-align="center"
        sortable
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
        min-width="80px"
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
        min-width="80px"
        align="center"
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
        min-width="80px"
        align="center"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        title="操作"
        :params="{ sortId: '113' }"
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
            v-on:click="onDelete(scope)"
            type="danger"
            v-show="permission.remove"
            content="删除"
            placement="top"
            icon-name="el-icon-delete"
            class="dc-OperationIcon_IconButton"
          ></OperationIcon>
          <!--<OperationIcon
            v-on:click="onShowHistory(scope)"
            type="info"
            v-show="permission.view"
            content="历史记录"
            placement="top"
            icon-name="el-icon-info"
            class="dc-OperationIcon_IconButton"
          ></OperationIcon>-->
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

    <el-dialog
      :visible.sync="dialog189Props.visible"
      :close-on-click-modal="false"
      class="dc-el-dialog_ElDialog"
      width="70%"
      title="合并线索">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-form-item label="线索池名称" prop="cluesName">
          <el-radio-group v-model="formData.cluesName" size="medium">
            <el-radio v-for="(item, index) in cluesNameOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="线索池负责人" prop="cplResponsible">
          <el-radio-group v-model="formData.cplResponsible" size="medium">
            <el-radio v-for="(item, index) in cplResponsibleOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="线索池成员" prop="member">
          <el-checkbox-group v-model="formData.member">
            <el-checkbox v-for="(item, index) in membersOptions" :key="index" :label="item.value"
                      >{{item.label==""?"无数据":item.label}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="线索池规则" prop="cplMemberClaimLimit">
          <el-radio-group v-model="formData.cplMemberClaimLimit" size="medium">
            <el-radio v-for="(item, index) in cplMemberClaimLimitOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="closeIncorporate">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>

  </el-row>
</template>
<script>
import { validatenull } from '@/utils/validate'

import { listCrmCluePoolPage, deleteCrmCluePool,mergecpl } from '@/api/crmCluePool/crmCluePool'

import { listResourcePermission } from '@/api/admin/common/permission'

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
export default {
  extends: DcMain,
  components: {
    /** 根据用户界面配置组件 开始 */
    History,
    EditForm,
    QueryTag,
    QueryConditionPanel,
    ViewColumnsSelect,
    /** 根据用户界面配置组件 结束 */
    ExportExcelButton,
    OperationIcon
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
      metadata,

      /** 根据用户界面配置生成data数据 开始 */
      queryFormData: {
        cplName: '', // 线索池名称

        cplResponsible: '' // 线索池负责人
      },
      tableData: [],
      multipleSelection: [],
      // 查询表格列头
      tableColumns: {
        251: {
          isShow: true,
          visible: true,
          order: 0
        },
        142: {
          title: '序号',
          isShow: true,
          visible: true,
          order: 0,
          width: '80px'
        },
        111: {
          title: 'id',
          field: 'id',
          visible: false,
          order: 0
        },
        118: {
          title: '线索池名称',
          field: 'cplName',
          visible: true,
          order: 0,
          width: '160px'
        },
        119: {
          title: '线索池负责人',
          field: 'cplResponsible',
          visible: true,
          order: 1,
          width: '160px'
        },
        120: {
          title: '线索池成员',
          field: 'membersp',
          visible: true,
          order: 2,
          width: '280px'
        },
        121: {
          title: '线索数量',
          field: 'countsxss',
          visible: true,
          order: 3,
          width: '80px'
        },
        122: {
          title: '未分配线索数量',
          field: 'countswfp',
          visible: true,
          order: 4,
          width: '160px'
        },
        123: {
          title: '领取上限',
          field: 'cplMemberClaimLimit',
          visible: true,
          order: 5,
          width: '80px'
        },
        128: {
          title: '创建者',
          field: 'createBy',
          visible: true,
          order: 10,
          width: '180px'
        },
        129: {
          title: '创建时间',
          field: 'createDate',
          visible: true,
          order: 11,
          width: '180px'
        },
        130: {
          title: '更新者',
          field: 'updateBy',
          visible: false,
          order: 12
        },
        131: {
          title: '更新时间',
          field: 'updateDate',
          visible: false,
          order: 13
        },
        132: {
          title: '备注信息',
          field: 'remarks',
          visible: false,
          order: 14
        },
        113: {
          title: '操作',
          width: '160px',
          visible: true,
          order: 15
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
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
      searchList: [],
      tableId: '1709759135450792434',
      schemeId: '1709759135450792432',
      dialog189Props: {
        visible: false,
        title: '合并线索'
      },
      formData: {
        id:"",
        cluesName: "",
        cplResponsible: "",
        member: [],
        cplMemberClaimLimit: ""
      },
    }
  },
  computed: {},
  methods: {
    handelConfirm(){
      this.$confirm('确认将选择的线索合并？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var params = new URLSearchParams();
        params.append('id',this.formData.id);
        params.append('cluesName', this.formData.cluesName);
        params.append('cplResponsible', this.formData.cplResponsible);
        params.append('cplResponmember', this.formData.member.join(','));
        params.append('cplMemberClaimLimit', this.formData.cplMemberClaimLimit);
        mergecpl(params).then((responseData) => {
          if (responseData.code == 100) {
            this.showMessage({
              type: 'success',
              msg: '保存成功'
            })
            /*this.$emit('save-finished')*/
            this.dialog189Props.visible = false
            this.onSearch()
          }else {
            this.showMessage({
              type: 'success',
              msg: '保存异常'
            })
          }
          //this.resetLoad()
        });


      }).catch(() => {
        this.dialog189Props.visible=false
      });
    },
    closeIncorporate(){
      this.dialog189Props.visible=false
    },
    async incorporate() {
      if (this.multipleSelection.length < 2) {
        this.showMessage({
          type: 'warning',
          msg: '请选择多条数据'
        })
        return
      }
      if (this.multipleSelection.length > 2) {
        this.showMessage({
          type: 'warning',
          msg: '最多选择两条'
        })
        return
      }

      if(this.multipleSelection.length != 2){
        this.showMessage({
          type: 'success',
          msg: '不满足合并条件'
        })
        return
      }

      // this.formData.cluesState = cluesState

      this.cluesNameOptions = []
      this.cplResponsibleOptions = []
      this.membersOptions = []
      this.cplMemberClaimLimitOptions = []
      //匹配对，进行合并
      for (let i = 0; i < this.multipleSelection.length; i++) {
        var multipleSelectionElement = this.multipleSelection[i];
        this.cluesNameOptions.push({
          "label": multipleSelectionElement.cplName,
          "value": multipleSelectionElement.cplName
        })
        this.cplResponsibleOptions.push({
          "label": multipleSelectionElement.cplResponsible,
          "value": multipleSelectionElement.cplResponsible
        })
        this.membersOptions.push({
          "label": multipleSelectionElement.members,
          "value": multipleSelectionElement.members
        })
        this.cplMemberClaimLimitOptions.push({
          "label": multipleSelectionElement.cplMemberClaimLimit,
          "value": multipleSelectionElement.cplMemberClaimLimit
        })

        if(i === 0){
          this.formData.id = multipleSelectionElement.id
        }else{
          this.formData.id = this.formData.id.toString() + ',' + multipleSelectionElement.id.toString()
        }

      }
      //这里为什么要给- -一个无作用的参数是为了直接赋值后参数少了出错
      /*this.multipleSelection[0].myclueProducts=""
      this.formData = JSON.parse(JSON.stringify(this.multipleSelection[0]));
      console.log(this.formData.id)
      this.formData.myclueProducts = JSON.parse(JSON.stringify(this.multipleSelection[0])).id*/
      this.dialog189Props.visible = true
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    getList() {
      this.setLoad()
      /* 查询参数 和数据权限 */
      this.search.params = []
      if (this.isQueryConditionPanel) {
        this.search.params = this.search.params.concat(this.compositeCondition())
      } else {
        this.search.params.push({
          columnName: 'cpl_name',
          queryType: 'like',

          value: !validatenull(this.queryFormData.cplName) ? this.queryFormData.cplName : null
        })

        this.search.params.push({
          columnName: 'cpl_responsible',
          queryType: 'like',

          value: !validatenull(this.queryFormData.cplResponsible) ? this.queryFormData.cplResponsible : null
        })
      }
      // 数据权限: 线索池管理crm_clue_pool
      this.pushDataPermissions(this.search.params, this.$route.meta.routerId, this.tableId)
      listCrmCluePoolPage(this.search)
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
    onSearch() {
      if (this.isQueryConditionPanel) {
        this.search.offset = 0
        this.tableDataPage = 1
        this.getList()
      } else {
        this.$refs['queryForm'].validate((valid) => {
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
    searchclear(){
      this.queryFormData.cplName = ''
      this.queryFormData.cplResponsible = ''
      this.onSearch()
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
              return item.permission === 'crmCluePool:read'
            })
            this.permission.export = responseData.data.find((item) => {
              return item.permission === 'crmCluePool:export'
            })

            this.permission.add = responseData.data.find((item) => {
              return item.permission === 'crmCluePool:create'
            })
            this.permission.edit = responseData.data.find((item) => {
              return item.permission === 'crmCluePool:update'
            })
            this.permission.remove = responseData.data.find((item) => {
              return item.permission === 'crmCluePool:delete'
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

    onCreate() {
      this.$refs.editForm.$emit('openAddDialog')
    },
    onEdit(scope) {
      this.$refs.editForm.$emit('openEditDialog', scope.row.id)
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
          deleteCrmCluePool(scope.row)
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

    this.columnDrop(this.$refs.table)
  }
}
</script>
