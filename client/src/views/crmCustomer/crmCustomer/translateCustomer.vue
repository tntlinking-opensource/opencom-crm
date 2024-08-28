<template>
  <el-dialog v-on:open="onDialogOpen"
             v-on:close="onDialogClose"
             v-loading="loading"
             width="90%"
             :fullscreen="false"
             :title="dialogProps.title"
             :visible.sync="dialogProps.visible"
             :close-on-click-modal="false"
             class="dc-el-dialog_ElDialog">
    <span style="padding-bottom: 10px;display: flex;font-weight: bold;">客户名称：{{ rowData.cusName }}</span>
    <el-form
      ref="queryForm"
      :model="queryFormData"
      label-width="100px"
      v-if="!isQueryConditionPanel"
      class="dc-el-form_ElQueryForm"
    >
      <el-row>
        <el-col :span="6">
          <el-form-item prop="department.id" label="部门" class="dc-el-form-item_CascaderInput">
            <el-cascader
              ref="cascader145"
              :options="departmentOptions"
              v-model="queryFormData.department.id"
              :style="{ width: '100%' }"
              placeholder="请选择部门"
              :props="{ label: 'name', value: 'id', children: 'children', checkStrictly: true, emitPath: false }"
              clearable
              filterable
              separator="/"
              class="dc-el-cascader_CascaderInput"
            ></el-cascader>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item prop="name" label="姓名" class="dc-el-form-item_SingleInput">
            <el-input
              v-model="queryFormData.name"
              :maxLength="128"
              placeholder="请输入用户名"
              clearable
              class="dc-el-input_SingleInput"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-tooltip effect="dark" content="搜索" placement="top">
            <el-button v-on:click="onSearch3" type="primary" icon="el-icon-search"></el-button>
          </el-tooltip>
          <el-tooltip effect="dark" content="重置" placement="top">
            <el-button
              v-on:click="isQueryConditionPanel ? $refs.conditionPanel.reset() : $refs.queryForm.resetFields()"
              type="primary"
              icon="el-icon-refresh-right"
            ></el-button>
          </el-tooltip>
        </el-col>
      </el-row>
    </el-form>
    <ux-grid
      column-key
      ref="table"
      :data="tableData"
      border
      v-on:sort-change="onSortChange3"
      @current-change="handleSelectionChange3"
      v-on:header-dragend="onWidthChange($refs.table)"
      :tree-config="{
              children: '',
              indent: 20,
              accordion: false,
              line: false,
              showIcon: true,
              iconOpen: '',
              iconClose: ''
            }"
      class="dc-ux-grid_QueryTable"
    >
      <ux-table-column
        :field="tableColumns3['126'].field"
        :title="tableColumns3['126'].title"
        :width="tableColumns3['126'].width"
        :visible="tableColumns3['126'].visible"
        :params="{ sortId: '126' }"
        tree-node
        resizable
        min-width="120px"
        align="left"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns3['127'].field"
        :title="tableColumns3['127'].title"
        :width="tableColumns3['127'].width"
        :visible="tableColumns3['127'].visible"
        :params="{ sortId: '127' }"
        tree-node
        resizable
        min-width="120px"
        align="left"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns3['128'].field"
        :title="tableColumns3['128'].title"
        :width="tableColumns3['128'].width"
        :visible="tableColumns3['128'].visible"
        :params="{ sortId: '128' }"
        tree-node
        resizable
        min-width="120px"
        align="left"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns3['129'].field"
        :title="tableColumns3['129'].title"
        :width="tableColumns3['129'].width"
        :visible="tableColumns3['129'].visible"
        :params="{ sortId: '129' }"
        tree-node
        resizable
        min-width="120px"
        align="left"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns3['130'].field"
        :title="tableColumns3['130'].title"
        :width="tableColumns3['130'].width"
        :visible="tableColumns3['130'].visible"
        :params="{ sortId: '130' }"
        tree-node
        resizable
        min-width="120px"
        align="left"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns3['131'].field"
        :title="tableColumns3['131'].title"
        :width="tableColumns3['131'].width"
        :visible="tableColumns3['131'].visible"
        :params="{ sortId: '131' }"
        tree-node
        resizable
        min-width="180px"
        align="left"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns3['132'].field"
        :title="tableColumns3['132'].title"
        :width="tableColumns3['132'].width"
        :visible="tableColumns3['132'].visible"
        :params="{ sortId: '132' }"
        tree-node
        resizable
        min-width="180px"
        align="left"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns3['133'].field"
        :title="tableColumns3['133'].title"
        :width="tableColumns3['133'].width"
        :visible="tableColumns3['133'].visible"
        :params="{ sortId: '133' }"
        tree-node
        resizable
        min-width="180px"
        align="left"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns3['134'].field"
        :title="tableColumns3['134'].title"
        :width="tableColumns3['134'].width"
        :visible="tableColumns3['134'].visible"
        :params="{ sortId: '134' }"
        tree-node
        resizable
        min-width="180px"
        align="center"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      >
        <template slot-scope="{ row, rowIndex }">
          <div
            v-html="
                    ((cellValue, row, index) => {
                      switch (cellValue) {
                        case '1':
                          return '<span style=' + 'color:red' + '>√</span>'
                        case '0':
                          return ''
                        default:
                          return ''
                      }
                    })(row.isLocked, row, rowIndex)
                  "
          ></div>
        </template>
      </ux-table-column>
      <ux-table-column
        :field="tableColumns3['135'].field"
        :title="tableColumns3['135'].title"
        :width="tableColumns3['135'].width"
        :visible="tableColumns3['135'].visible"
        :params="{ sortId: '135' }"
        tree-node
        resizable
        min-width="180px"
        align="left"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns3['143'].field"
        :title="tableColumns3['143'].title"
        :width="tableColumns3['143'].width"
        :visible="tableColumns3['143'].visible"
        :params="{ sortId: '143' }"
        tree-node
        resizable
        min-width="160px"
        align="right"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns3['139'].field"
        :title="tableColumns3['139'].title"
        :width="tableColumns3['139'].width"
        :visible="tableColumns3['139'].visible"
        :params="{ sortId: '139' }"
        tree-node
        resizable
        min-width="180px"
        align="left"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns3['136'].field"
        :title="tableColumns3['136'].title"
        :width="tableColumns3['136'].width"
        :visible="tableColumns3['136'].visible"
        :params="{ sortId: '136' }"
        tree-node
        resizable
        min-width="180px"
        align="left"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns3['140'].field"
        :title="tableColumns3['140'].title"
        :width="tableColumns3['140'].width"
        :visible="tableColumns3['140'].visible"
        :params="{ sortId: '140' }"
        tree-node
        resizable
        min-width="180px"
        align="left"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns3['141'].field"
        :title="tableColumns3['141'].title"
        :width="tableColumns3['141'].width"
        :visible="tableColumns3['141'].visible"
        :params="{ sortId: '141' }"
        tree-node
        resizable
        min-width="160px"
        align="right"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns3['142'].field"
        :title="tableColumns3['142'].title"
        :width="tableColumns3['142'].width"
        :visible="tableColumns3['142'].visible"
        :params="{ sortId: '142' }"
        tree-node
        resizable
        min-width="180px"
        align="left"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
    </ux-grid>
    <el-pagination
      :total="tableDataTotal"
      :page-size="search3.limit"
      background
      :current-page="tableDataPage3"
      :page-sizes="[10, 20, 30, 40, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      v-on:size-change="onSizeChange3"
      v-on:current-change="onCurrentChange3"
      class="dc-el-pagination_ElPagination"
    ></el-pagination>
    <span slot="footer" class="dialog-footer">
        <el-button @click="transferClose">取消</el-button>
        <el-button @click="editCrmClues" type="primary">确认</el-button>
      </span>
  </el-dialog>
</template>

<script>
import BaseUI from '@/views/components/baseUI'
import History from '@/views/components/history'
import QueryTag from '@/views/components/queryTag'
import QueryConditionPanel from '@/views/components/queryConditionPanel'
import ViewColumnsSelect from '@/views/components/DcViewColumnsSelect'
import ExportExcelButton from '@/components/ExportExcelButton'
import OperationIcon from '@/components/OperationIcon'
import {validatenull} from '@/utils/validate'
import {listUserPage} from "../../../api/admin/user";
import {crmCustomerSave} from "@/api/crmCustomer/crmCustomer";
import {treeDepartment} from "../../../api/org/department";

export default {
  extends: BaseUI,
  components: {
    /** 根据用户界面配置组件 开始 */
    History,
    QueryTag,
    QueryConditionPanel,
    ViewColumnsSelect,
    /** 根据用户界面配置组件 结束 */
    ExportExcelButton,
    OperationIcon,
  },
  data() {
    return {
      // 对话框属性变量
      dialogProps: {
        visible: false,
        tableVisible: false,
        title: '合同'
      },
      departmentOptions: [],

      /** 根据用户界面配置生成data数据 开始 */
      queryFormData: {
        // 部门
        department: {
          id: null,
          name: null,
          code: null,
          ids: null
        },
        name: '' // 用户名
      },
      search: {
        params: {
          oppCode: "",
          oppName: ''
        }
      },
      oppName: "",
      tableColumns3: {
        126: {
          title: '公司.全称',
          field: 'company.fullName',
          visible: true,
          order: 0
        },
        127: {
          title: '公司.编号',
          field: 'company.code',
          visible: false,
          order: 1
        },
        128: {
          title: '公司.名称',
          field: 'company.name',
          visible: false,
          order: 2
        },
        129: {
          title: '部门.编号',
          field: 'department.id',
          visible: false,
          order: 3
        },
        130: {
          title: '部门.名称',
          field: 'department.name',
          visible: true,
          order: 4
        },
        131: {
          title: '姓名',
          field: 'name',
          visible: true,
          order: 5
        },
        132: {
          title: '登录账号',
          field: 'loginName',
          visible: false,
          order: 6
        },
        133: {
          title: '密码',
          field: 'loginPassword',
          visible: false,
          order: 7
        },
        134: {
          title: '禁用',
          field: 'isLocked',
          visible: false,
          order: 8
        },
        135: {
          title: '手机号',
          field: 'phone',
          visible: true,
          order: 9
        },
        143: {
          title: '更新时间',
          field: 'updateDate',
          visible: true,
          order: 10
        },
        139: {
          title: '备注信息',
          field: 'remarks',
          visible: false,
          order: 11
        },
        136: {
          title: '邮箱地址',
          field: 'email',
          visible: false,
          order: 12
        },
        140: {
          title: '创建者',
          field: 'createBy',
          visible: false,
          order: 15
        },
        141: {
          title: '创建时间',
          field: 'createDate',
          visible: false,
          order: 16
        },
        142: {
          title: '更新者',
          field: 'updateBy',
          visible: false,
          order: 17
        },
        117: {
          title: '操作',
          width: '160px',
          visible: true,
          order: 18
        }
      },
      tableDataTotal: [],
      tableData: [],
      search3: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
      multipleSelection3: [],
      tableDataPage3: 1,
      rowData: {
        oppResponsible:"",
        oppRespondepartId:""
      }
    };

  },
  props: {
    sjId: {
      type: String
    }
  },
  methods: {
    listSelectDepart() {
      let search_List = {
        params: []
      };
      if (currentUser.id != 1001) {
        search_List.params.push({'columnName': 'company_id', 'queryType': '=', 'value': currentUser.company.id})
      } else {
        search_List.params.push({'columnName': 'company_id', 'queryType': '=', 'value': "1665295280360415265"})
      }
      treeDepartment(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.departmentOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
    },
    editCrmClues() {
      if (this.multipleSelection3.length > 1) {
        this.showMessage({
          type: 'success',
          msg: '请选择单条数据'
        })
        return
      } else if (this.multipleSelection3.length == 0) {
        this.showMessage({
          type: 'success',
          msg: '请选择单条数据'
        })
        return
      }
      this.$confirm('确认转移给' + this.multipleSelection3.name + '', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let params = {
          cusResponsible: this.multipleSelection3.id,
          id: this.rowData.id
        }
        crmCustomerSave(params).then(responseData => {
          if (responseData.code == 100) {
            this.showMessage({
              type: 'success',
              msg: '保存成功'
            })
          }else{
            this.showMessage(responseData)
          }
          this.dialogProps.visible=false
          this.$emit("getList")
        })
      }).catch(() => {

      });
    },
    transferClose() {
      this.multipleSelection3 = []
      this.dialogProps.visible = false
    },
    onCurrentChange3(val) {
      this.search3.offset = (val - 1) * this.search3.limit
      this.tableDataPage3 = val
      this.getList3()
    },
    onSizeChange3(val) {
      this.tableDataPage3 = 1
      this.search3.limit = val
      this.search3.offset = (this.tableDataPage3 - 1) * val
      this.getList3()
    },
    handleSelectionChange3(val) {
      this.multipleSelection3 = val;
    },
    onSortChange3(orderby) {
      if (validatenull(orderby.prop)) {
        this.search3.columnName = ''
        this.search3.order = ''
      } else {
        this.search3.columnName = orderby.prop
        this.search3.order = orderby.order
      }
      this.getList3()
    },
    onDialogOpen() {
      this.$nextTick(() => {
        this.$refs['queryForm'].clearValidate()
      })
    },
    onDialogClose() {
      this.dialogProps.visible = false
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
    getList3() {
      this.setLoad()
      /* 查询参数 和数据权限 */
      this.search.params = []

      this.search.params.push({
        columnName: 'company_id',
        queryType: '=',
        value: currentUser.company.id
      })

      if (this.isQueryConditionPanel) {
        this.search.params = this.search.params.concat(this.compositeCondition())
      } else {
        this.search.params.push({
          columnName: 'code',
          queryType: 'like',

          value: !validatenull(this.queryFormData.code) ? this.queryFormData.code : null
        })

        this.search.params.push({
          columnName: 'name',
          queryType: 'like',

          value: !validatenull(this.queryFormData.name) ? this.queryFormData.name : null
        })
      }
      // 数据权限: 用户sys_user
      listUserPage(this.search)
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
    onSearch3() {
      if (this.isQueryConditionPanel) {
        this.search3.offset = 0
        this.tableDataPage3 = 1
        this.getList3()
      } else {
        this.$refs['queryForm'].validate((valid) => {
          if (valid) {
            this.search3.offset = 0
            this.tableDataPage3 = 1
            this.getList3()
          } else {
            return false
          }
        })
      }
    },
  },
  mounted() {
    this.$on('openDialogView', async function (row) {
      this.rowData = row;
      this.action = 'view'
      this.dialogProps.title = `转移客户`
      this.tableData = []
      this.getList3()
      this.listSelectDepart()
      this.dialogProps.visible = true
    })
  },

};
</script>
