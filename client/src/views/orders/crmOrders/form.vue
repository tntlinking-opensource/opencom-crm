<template>
  <el-row class="dc-container">
    <el-dialog
      v-on:open="onDialogOpen"
      v-on:close="onDialogClose"
      v-loading="loading"
      width="70%"
      :fullscreen="false"
      :title="dialogProps.title"
      :visible.sync="dialogProps.visible"
      :close-on-click-modal="false"
      class="dc-el-dialog_ElDialog"
    >
      <el-row>
        <el-form
          ref="editForm"
          :model="editFormData"
          label-width="100px"
          :disabled="action == 'view'"
          class="dc-el-form_ElEditForm"
        >
          <el-row>
            <!--<el-col :span="12">
              <el-form-item prop="ordersHead" label="订单负责人" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.ordersHead"
                  :maxLength="20"
                  placeholder="请输入订单负责人"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>-->
            <el-col :span="8">
              <el-form-item
                prop="ordersHead"
                label="订单负责人"
                :rules="[{ required: true, message: '订单负责人不能为空', trigger: 'change' }]"
                class="dc-el-form-item_SelectInput"
              >
                <el-select
                  v-model="editFormData.ordersHead"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  clearable
                  filterable
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in selectOptions11"
                    :key="index"
                    :label="item.customerName"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-divider content-position="left" class="dc-el-divider_ElDivider">订单信息</el-divider>
            <el-col :span="8">
              <el-form-item
                prop="customName"
                label="客户名称"
                :rules="[{ required: true, message: '客户名称不能为空', trigger: 'change' }]"
                class="dc-el-form-item_SelectInput"
              >
                <el-select
                  v-model="editFormData.customName"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  clearable
                  filterable
                  class="dc-el-select_SelectInput"
                  v-on:change="listSelectOptions2"
                >
                  <el-option
                    v-for="(item, index) in selectOptions1"
                    :key="index"
                    :label="item.customerName"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item
                prop="bsoyName"
                label="商机名称"
                :rules="[{ required: true, message: '商机名称不能为空', trigger: 'change' }]"
                class="dc-el-form-item_SelectInput"
              >
                <el-select
                  v-model="editFormData.bsoyName"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  clearable
                  filterable
                  class="dc-el-select_SelectInput"
                  v-on:change="listSelectOptions3"
                >
                  <el-option
                    v-for="(item, index) in selectOptions2"
                    :key="index"
                    :label="item.businessName"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="contractCode" label="合同编号" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.contractCode"
                  :maxLength="10"
                  placeholder=""
                  clearable
                  class="dc-el-input_SingleInput"
                  :disabled="true">
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="contractName" label="合同名称" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.contractName"
                  :maxLength="10"
                  placeholder=""
                  clearable
                  class="dc-el-input_SingleInput"
                  :disabled="true">
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="ordersMacount" label="订单金额" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.ordersMacount"
                  :maxLength="120"
                  placeholder=""
                  clearable
                  class="dc-el-input_SingleInput"
                  :disabled="true">
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item
                prop="oedersCode"
                label="订单编号"
                :rules="[{ required: true, message: '订单编号不能为空', trigger: 'blur' }]"
                class="dc-el-form-item_SingleInput"
              >
                <el-input
                  v-model="editFormData.oedersCode"
                  :maxLength="120"
                  placeholder="请输入订单编号"
                  clearable
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="notes" label="备注" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.notes"
                  :maxLength="300"
                  placeholder="请输入备注"
                  clearable
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <upload-file
                title="附件"
                :action="action"
                :objectId="editFormData.wbnumber"
                v-model="editFormData.wbnumberFile"
                class="dc-upload-file_ElUploadFile"
              ></upload-file>
            </el-col>
            <el-divider content-position="left" class="dc-el-divider_ElDivider">订单产品</el-divider>
            <el-col :span="24">
              <ux-grid
                :data="editFormData.table132Data"
                ref="table132"
                :currentRow="table132CurrentRow"
                height="200px"
                border
                :edit-config="{ trigger: 'click', mode: 'cell' }"
                class="dc-ux-grid_EditTable"
              >
                <ux-table-column
                  title="序号"
                  :params="{ sortId: '142', summary: false }"
                  type="index"
                  tree-node
                  resizable
                  min-width="50px"
                  fixed="left"
                  align="left"
                  header-align="center"
                  class="dc-ux-table-column_ElTableNumColumn"
                ></ux-table-column>
                <ux-table-column
                  field="pdNo"
                  title="产品编号1"
                  tree-node
                  resizable
                  align="left"
                  header-align="center"
                  show-overflow
                  class="dc-ux-table-column_ElTableColumn"
                ></ux-table-column>
                <ux-table-column
                  field="pdName"
                  title="产品名称"
                  tree-node
                  resizable
                  align="left"
                  header-align="center"
                  show-overflow
                  class="dc-ux-table-column_ElTableColumn"
                ></ux-table-column>
                <ux-table-column
                  field="pdClassifyId"
                  title="产品分类"
                  tree-node
                  resizable
                  align="left"
                  header-align="center"
                  show-overflow
                  class="dc-ux-table-column_ElTableColumn"
                ></ux-table-column>
                <ux-table-column
                  field="pdTypeId"
                  title="产品类型"
                  tree-node
                  resizable
                  align="left"
                  header-align="center"
                  show-overflow
                  class="dc-ux-table-column_ElTableColumn"
                ></ux-table-column>
                <ux-table-column
                  field="pdGuidePrice"
                  title="指导价"
                  tree-node
                  resizable
                  align="left"
                  header-align="center"
                  show-overflow
                  class="dc-ux-table-column_ElTableColumn"
                ></ux-table-column>
                <ux-table-column
                  field="sigleNum"
                  title="数量"
                  tree-node
                  resizable
                  align="left"
                  header-align="center"
                  show-overflow
                  class="dc-ux-table-column_ElTableColumn"
                ></ux-table-column>
                <ux-table-column
                  field="discount"
                  title="折扣"
                  tree-node
                  resizable
                  align="left"
                  header-align="center"
                  show-overflow
                  class="dc-ux-table-column_ElTableColumn"
                ></ux-table-column>
                <ux-table-column
                  field="sumAount"
                  title="总价"
                  tree-node
                  resizable
                  align="left"
                  header-align="center"
                  show-overflow
                  class="dc-ux-table-column_ElTableColumn"
                ></ux-table-column>
              </ux-grid>
            </el-col>
            <el-divider content-position="left" class="dc-el-divider_ElDivider">收获信息</el-divider>
            <el-col :span="12">
              <el-form-item prop="consignee" label="收货人" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.consignee"
                  :maxLength="30"
                  placeholder="请输入收货人"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="consigneePhone" label="收货人电话" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.consigneePhone"
                  :maxLength="30"
                  placeholder="请输入收货人电话"
                  clearable
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="consigneeAddr" label="收货人地址" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.consigneeAddr"
                  :maxLength="30"
                  placeholder="请输入收货人地址"
                  clearable
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button v-on:click="onSubmit" type="primary" v-show="action != 'view'">保存</el-button>
        <el-button v-on:click="onDialogClose" v-if="action != 'view'">取消</el-button>
        <el-button v-on:click="onDialogClose" v-if="action == 'view'">关闭</el-button>
      </span>
    </el-dialog>
  </el-row>
</template>
<script>
import { validatenull } from '@/utils/validate'
/** 根据用户界面配置import组件 结束 */
import uploadFile from '@/views/components/uploadFile'
import {byotyid} from '@/api/contract/crmContract'
import { getCrmCustomerManagementByIds,getCrmddfzrs} from '@/api/customerManagement/crmCustomerManagement'
import { getCrmBusinessOpportunityBycusname } from '@/api/businessopportunity/crmBusinessOpportunity'
import { getSysDictItemBytype } from '@/api/dictitem/sysDictItem'
import { getdeparbycompany } from '@/api/orgdepartment/orgDepartment'
/** 根据用户界面配置import组件 结束 */
import { getCrmOrdersById, saveCrmOrders,getseleddcp } from '@/api/orders/crmOrders'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'
export default {
  extends: BaseUI,
  name: 'crmOrders-form',
  components: {
    /** 根据用户界面配置组件 开始 */
    uploadFile,
    /** 根据用户界面配置组件 结束 */

    OperationIcon
  },
  data() {
    return {
      /** 根据用户界面配置生成data数据 开始 */
      editFormData: this.initEditData(),
      // 对话框属性变量
      dialogProps: {
        visible: false,
        title: '订单管理'
      },
      // 客户名称选项
      selectOptions1: [],
      // 商机名称选项
      selectOptions2: [],
      selectOptions11:[],
      dialogTitle: '订单管理',
      // 选项变量
      table132CurrentRow: {}, // 当前行
      /** 根据用户界面配置生成data数据 结束 */

      // 窗口操作类型 view/edit/add
      action: ''
    }
  },
  props: {
    // 权限
    permission: {
      type: Object
    }
  },
  computed: {},
  methods: {
    onSubmit() {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          this.doSave()
        } else {
          return false
        }
      })
    },
    doSave() {
      this.setLoad()

      let formData = this.createFormData(this.editFormData)
      saveCrmOrders(formData)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.dialogProps.visible = false
            this.showMessage({
              type: 'success',
              msg: '保存成功'
            })
            this.$emit('save-finished')
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    switchEdit() {
      this.action = 'edit'
      this.dialogProps.title = `修改${this.dialogTitle}`
      this.initOptions(this.editFormData)
    },

    createFormData(editFormData) {
      let formData = new FormData()
      let deletes = []

      for (let idx in editFormData.wbnumberFile.uploads) {
        formData.append('wbnumberUploads', editFormData.wbnumberFile.uploads[idx].raw)
      }
      deletes = deletes.concat(editFormData.wbnumberFile.deletes)

      formData.append('entity', JSON.stringify(editFormData))
      formData.append('deleteIds', JSON.stringify(deletes))
      return formData
    },

    getFormById(id) {
      this.setLoad()
      return new Promise((resolve) => {
        getCrmOrdersById(id)
          .then((responseData) => {
            let form = {}
            if (responseData.code == 100) {
              form = responseData.data
            } else {
              this.showMessage(responseData)
            }
            this.resetLoad()
            resolve(form)
          })
          .catch((error) => {
            this.outputError(error)
          })
      })
    },
    onDialogClose() {
      this.dialogProps.visible = false
    },
    onDialogOpen() {
      this.$nextTick(() => {
        this.$refs['editForm'].clearValidate()
      })
    },

    listSelectOptions1() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])

      getCrmCustomerManagementByIds(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.selectOptions1 = responseData.data;
        } else {
          this.showMessage(responseData)
        }
      })
    },

    listSelectOptions11() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])

      getCrmddfzrs(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.selectOptions11 = responseData.data;
        } else {
          this.showMessage(responseData)
        }
      })
    },

    listSelectOptions2() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])
      let id = this.editFormData.customName

      getCrmBusinessOpportunityBycusname(id).then((responseData) => {
        if (responseData.code == 100) {
          this.selectOptions2 = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
    },

    listSelectOptions3(){
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])
      console.log('触发3'+this.editFormData.bsoyName)
      // filter条件
      let id = this.editFormData.bsoyName

      //获取订单产品信息
      this.handseleuserls();

      byotyid(id).then((responseData) => {
        if (responseData.code == 100) {
          /*this.selectOptions2 = responseData.data*/
          this.editFormData.contractCode = responseData.data.contractCode;
          this.editFormData.contractName = responseData.data.contractName;
          this.editFormData.ordersMacount = responseData.data.contractAmount;
        } else {
          this.showMessage(responseData)
        }
      })




    },

    handseleuserls() {
      console.log('查询事件')
      let usids = {
        pageNum:1,
        pageSize:5,
        bsoyName:this.editFormData.bsoyName
      };
      getseleddcp(usids).then(response => {
        this.editFormData.table132Data = response.data;
        //this.total = response.total;
      });
    },

    initOptions(This) {
      this.listSelectOptions1()
      this.listSelectOptions11()
      // 初始化自定义类型选择框选项变量
    },
    initEditData(This) {
      return {
        ordersHead: '', // 订单负责人
        customName: '', // 客户名称
        bsoyName: '', // 商机名称
        contractCode: '', // 合同编号
        contractName: '', // 合同名称
        ordersMacount: '', // 订单金额
        oedersCode: '', // 订单编号
        notes: '', // 备注
        wbnumber: null,
        wbnumberFile: {
          deletes: [], // 待删除（已上传）的文件列表
          uploads: [] // 待上传的文件列表
        },
        ordersProduct: '', // 订单产品
        table132Data: [],
        consignee: '', // 收货人
        consigneePhone: '', // 收货人联系电话
        consigneeAddr: '' // 收货人地址
      }
    }
  },
  watch: {},
  mounted: function () {
    this.$nextTick(() => {
      this.$on('openViewDialog', async function (id) {
        this.action = 'view'
        this.dialogProps.title = `查看${this.dialogTitle}`
        this.editFormData = {
          ...this.initEditData(),
          ...(await this.getFormById(id))
        }
        this.initOptions(this.editFormData)
        this.dialogProps.visible = true
      })

      this.$on('openEditDialog', async function (id) {
        this.action = 'edit'
        this.dialogProps.title = `修改${this.dialogTitle}`
        this.editFormData = {
          ...this.initEditData(),
          ...(await this.getFormById(id))
        }
        this.initOptions(this.editFormData)
        this.dialogProps.visible = true
      })
      this.$on('openAddDialog', function () {
        this.action = 'add'
        this.dialogProps.title = `添加${this.dialogTitle}`
        this.editFormData = this.initEditData()
        this.initOptions(this.editFormData)
        this.dialogProps.visible = true
      })
      this.$on('openCopyDialog', async function (id) {
        this.action = 'add'
        this.dialogProps.title = `添加${this.dialogTitle}`
        this.editFormData = {
          ...this.initEditData(),
          ...(await this.getFormById(id))
        }
        this.initOptions(this.editFormData)
        this.editFormData.id = null //把id设置为空，添加一个新的

        this.editFormData.wbnumber = null

        this.dialogProps.visible = true
      })
    })
  }
}
</script>
