<template>
    <el-dialog v-on:open="onDialogOpen"
               v-on:close="onDialogClose"
               v-loading="loading"
               width="70%"
               :fullscreen="false"
               :title="dialogProps.title"
               :visible.sync="dialogProps.visible"
               :close-on-click-modal="false"
               class="dc-el-dialog_ElDialog">
      <el-form
        ref="queryForm"
        :model="queryFormData"
        label-width="100px"
        v-if="!isQueryConditionPanel"
        :rules="rules"
        class="dc-el-form_ElQueryForm"
      >
        <el-row>
          <el-col :span="15">
            <el-form-item  prop="title" label="标题" class="dc-el-form-item_CascaderInput">
              <el-input
                v-model="queryFormData.title"
                :maxLength="128"
                placeholder="请输入标题"
                clearable
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="15">
            <el-form-item prop="textInfo" label="正文" class="dc-el-form-item_SingleInput">
              <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 4}"
                v-model="queryFormData.textInfo"
                placeholder="请输入正文"
                clearable
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="15">
            <el-form-item prop="sendInfo" label="发送邮箱" class="dc-el-form-item_SingleInput">
              <el-input
                :disabled="true"
                v-model="queryFormData.sendInfo"
                :maxLength="128"
                placeholder="请输入标题"
                clearable
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="15">
            <el-form-item prop="recipient" label="接收人" class="dc-el-form-item_SingleInput">
              <el-select
                v-model="queryFormData.recipient"
                :style="{ width: '100%' }"
                placeholder="请选择接收人"
                clearable
                filterable
                multiple
                class="dc-el-select_SelectInput"
              >
                <el-option
                  v-for="(item, index) in oppContactsOptions"
                  :key="index"
                  :label="item.contactsName"
                  :value="item.email"
                >
                  <span style="float: left">{{ item.contactsName }}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">{{ item.email }}</span>
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
  
        </el-row>
        <el-row>
          <el-col>
            <upload-file
              title="文件上传"
              :action="action != 'view'"
              :objectId="queryFormData.objectId"
              v-model="queryFormData.fileList"
              class="dc-upload-file_ElUploadFile"
            ></upload-file>
          </el-col>
        </el-row>
      </el-form>
  
      <ux-grid
        v-if="false"
        column-key
        ref="table"
        :data="tableData"
        border
        v-on:sort-change="onSortChange3"
        @selection-change="handleSelectionChange3"
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
          :field="tableColumns3['125'].field"
          :title="tableColumns3['125'].title"
          :width="tableColumns3['125'].width"
          :visible="tableColumns3['125'].visible"
          :params="{ sortId: '125' }"
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
        v-show="false"
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
  import { crmCustomerSendPayQuotation } from "@/api/crmCustomer/crmCustomer";
  import UploadFile from "../../components/uploadFile.vue";
  import {listCrmContactsPage} from "../../../api/contacts/crmContacts";
  import {getSendEmail} from "../../../api/crmBusiness/crmOpportunityLink";
  
  export default {
    extends: BaseUI,
    components: {
      UploadFile,
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
          fileList:[]
        },
        rules: {
          title: [
            {required: true, message: '请输入标题', trigger: 'blur'},
          ],
          textInfo: [
            {required: true, message: '请输入正文', trigger: 'blur'}
          ],
          recipient:[
            {required: true, message: '请选择接收人邮箱', trigger: 'blur'}
          ]
        },
        search: {
          params: {
            oppCode: "",
            oppName: ''
          }
        },
        oppName: "",
        tableColumns3: {
          125: {
            title: 'id',
            field: 'id',
            visible: false,
            order: 19
          },
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
            field: 'department.code',
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
            visible: false,
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
            visible: true,
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
            visible: false,
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
        rowData: "",
        action:"edit",
        oppContactsOptions:[],
      };
  
    },
    props: {
      sjId: {
        type: String
      }
    },
    methods: {
      listCrmContactsDate(id){
        let search = {
          params:[]
        }
        search.params.push({
          columnName: 'custom_name',
          queryType: 'like',
          value: id
        })
        listCrmContactsPage(search)
          .then((responseData) => {
            if (responseData.code == 100) {
              this.oppContactsOptions = responseData.data.rows ? responseData.data.rows : []
            } else {
              this.showMessage(responseData)
            }
            this.resetLoad()
          })
          .catch((error) => {
            this.outputError(error)
          })
      },
      createWfData(bizFormModel) {
        let formData = new FormData
        let deletes= [];
        formData.append("payInfo", JSON.stringify(bizFormModel))
        /* for (let idx in files) {
           formData.append("files", files[idx].raw)
         }*/
        for (let idx in bizFormModel.fileList.uploads) {
          formData.append('files', bizFormModel.fileList.uploads[idx].raw)
        }
        deletes = deletes.concat(bizFormModel.fileList.deletes)
        formData.append('deleteIds', JSON.stringify(deletes))
  
        return formData
      },
      editCrmClues() {
        if (this.queryFormData.fileList.uploads === undefined) {
          this.$message.warning("请选择文件");
          return false;
        }
        this.setLoad()
        this.queryFormData.opportunityId = this.rowData.oppId
        let formData = this.createWfData(this.queryFormData)
        this.$refs["queryForm"].validate((valid) => {
          if (valid){
            this.$confirm('确认发送报价单？', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              crmCustomerSendPayQuotation(formData).then(responseData => {
                if (responseData.code == 100) {
                  this.showMessage({
                    type: 'success',
                    msg: '发送成功'
                  })
                  this.$emit("save-finished");
                }else{
                  this.showMessage(responseData)
                }
                this.dialogProps.visible=false
              })
            }).catch(() => {
            });
          }
        })
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
      getSendEmailMethod(){
        getSendEmail().then((responseData)=>{
          this.queryFormData.sendInfo = responseData.data
        })
      }
    },
    mounted() {
      this.$on('openDialogView', function (row, formId) {
        this.rowData = row
        this.action = 'edit'
        this.dialogProps.title = `发送报价单`
        this.oppName = row.oppName
        this.listCrmContactsDate(formId)
        this.getSendEmailMethod()
        this.tableData = []
        this.getList3()
        this.dialogProps.visible = true
      })
    },
  
  };
  </script>
  