<template>
  <el-row class="dc-container">
    <el-dialog
      v-on:open="onDialogOpen"
      v-on:close="onDialogClose"
      v-loading="loading"
      width="50%"
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
            <el-divider content-position="left" class="dc-el-divider_ElDivider">主要信息</el-divider>
            <el-col :span="12">
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
            <el-col :span="12">
              <el-form-item
                prop="bsoy"
                label="商机名称"
                class="dc-el-form-item_SelectInput"
              >
                <el-select
                  v-model="editFormData.bsoy"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  clearable
                  filterable
                  class="dc-el-select_SelectInput"
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
            <el-col :span="12">
              <el-form-item
                prop="contactsName"
                label="联系人姓名"
                :rules="[{ required: true, message: '联系人姓名不能为空', trigger: 'blur' }]"
                class="dc-el-form-item_SingleInput"
              >
                <el-input
                  v-model="editFormData.contactsName"
                  :maxLength="120"
                  placeholder="请输入联系人姓名"
                  clearable
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                prop="phoneNumber"
                label="手机号"
                :rules="[{ required: true, message: '手机号不能为空', trigger: 'blur' }]"
                class="dc-el-form-item_SingleInput"
              >
                <el-input
                  v-model="editFormData.phoneNumber"
                  :maxLength="120"
                  placeholder="请输入手机号"
                  clearable
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="email" label="邮箱" :rules="[{ required: true, message: '邮箱不能为空', trigger: 'blur' }]" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.email"
                  :maxLength="32"
                  placeholder="请输入邮箱"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-divider content-position="left" class="dc-el-divider_ElDivider">其他信息</el-divider>
            <el-col :span="12">
              <el-form-item prop="workPost" label="所属部门" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.depart"
                  :maxLength="60"
                  placeholder="请输入职位"
                  clearable
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <!--<el-col :span="12">
              <el-form-item prop="" label="所属部门" class="dc-el-form-item_SelectInput">
                <el-select
                  v-model="editFormData.depart"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  clearable
                  filterable
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in selectOptions5"
                    :key="index"
                    :label="item.name"
                    :value="item.name"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>-->
            <el-col :span="12">
              <el-form-item prop="workPost" label="职位" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.workPost"
                  :maxLength="60"
                  placeholder="请输入职位"
                  clearable
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="sex" label="性别" class="dc-el-form-item_SelectInput">
                <el-select
                  v-model="editFormData.sex"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  clearable
                  filterable
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in selectOptions3"
                    :key="index"
                    :label="item.name"
                    :value="item.name"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="borndate" label="出生日期" class="dc-el-form-item_PickerTimeDate">
                <el-date-picker
                  v-model="editFormData.borndate"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  type="date"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  class="dc-el-date-picker_PickerTimeDate"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="contactPhone" label="电话" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.contactPhone"
                  :maxLength="20"
                  placeholder="请输入电话"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <!--<el-col :span="12">
              <el-form-item prop="email" label="邮箱" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.email"
                  :maxLength="32"
                  placeholder="请输入邮箱"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>-->
            <el-col :span="12">
              <el-form-item prop="wenumber" label="微信" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.wenumber"
                  :maxLength="30"
                  placeholder="请输入微信"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="wbnumber" label="微博" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.wbnumber"
                  :maxLength="30"
                  placeholder="请输入微博"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="qnumber" label="QQ" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.qnumber"
                  :maxLength="30"
                  placeholder="请输入QQ"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="region" label="所在区域" class="dc-el-form-item_SelectInput">
                <el-select
                  v-model="editFormData.region"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  clearable
                  filterable
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in selectOptions4"
                    :key="index"
                    :label="item.name"
                    :value="item.name"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="address" label="详细地址" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.address"
                  :maxLength="50"
                  placeholder="请输入详细地址"
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
import { getCrmCustomerManagementByIds } from '@/api/customerManagement/crmCustomerManagement'
import { getCrmBusinessOpportunityBycusname } from '@/api/businessopportunity/crmBusinessOpportunity'
import { getSysDictItemBytype } from '@/api/dictitem/sysDictItem'
import { getdeparbycompany } from '@/api/orgdepartment/orgDepartment'
/** 根据用户界面配置import组件 结束 */
import { getCrmContactsById, saveCrmContacts } from '@/api/contacts/crmContacts'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'
export default {
  extends: BaseUI,
  name: 'crmContacts-form',
  components: {
    /** 根据用户界面配置组件 开始 */

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
        title: '联系人'
      },
      dialogTitle: '联系人',
      // 选项变量
      // 客户名称选项
      selectOptions1: [],
      // 商机名称选项
      selectOptions2: [],
      selectOptions3: [],
      selectOptions4: [],
      selectOptions5: [],
      // 所在区域选项
      selectOptions: [
        {
          label: '选项一',
          value: '1'
        },
        {
          label: '选项二',
          value: '2'
        }
      ],
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

      if(this.action === 'openAddDialog1'){
        this.editFormData.customName = this.editFormData.customNameid;
        this.action = 'openAddDialog2'
        this.editFormData.bsoy = this.editFormData.bsoyid;
      }

      saveCrmContacts(this.editFormData)
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

    getFormById(id) {
      this.setLoad()
      return new Promise((resolve) => {
        getCrmContactsById(id)
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

    listSelectOptions3() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])
      let type = 'GENDER'
      getSysDictItemBytype(type).then((responseData) => {
        if (responseData.code == 100) {
          this.selectOptions3 = responseData.data;
        } else {
          this.showMessage(responseData)
        }
      })
    },

    listSelectOptions4() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])
      let type = 'AREA'
      getSysDictItemBytype(type).then((responseData) => {
        if (responseData.code == 100) {
          this.selectOptions4 = responseData.data;
        } else {
          this.showMessage(responseData)
        }
      })
    },

    listSelectOptions5() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])
      getdeparbycompany(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.selectOptions5 = responseData.data;
        } else {
          this.showMessage(responseData)
        }
      })
    },

    initOptions(This) {
      // 初始化自定义类型选择框选项变量

      this.listSelectOptions1()
      this.listSelectOptions3()
      this.listSelectOptions4()
      this.listSelectOptions5()
      /*if(this.bsoyls !== null){
        //this.listSelectOptions2()
        this.listSelectOptions2()
      }*/

    },
    initEditData(This) {
      return {
        customName: null, // 客户名称
        bsoy: null, // 商机名称
        contactsName: '', // 联系人姓名
        phoneNumber: '', // 手机号
        depart: '', // 所属部门
        workPost: '', // 职位
        sex: null, // 性别
        borndate: '', // 出生日期
        contactPhone: '', // 电话
        email: '', // 邮箱
        wenumber: '', // 微信
        wbnumber: '', // 微博
        qnumber: '', // QQ
        region: null, // 所在区域
        address: '', // 详细地址
        notes: '' // 备注
      }
    }
  },
  watch: {
    'editFormData.customName': {
      handler: function (val, oldVal) {
        this.$nextTick(() => {
          if(this.editFormData.customName !== null){
            if(this.action !== 'edit'){
              if(this.action !== 'openAddDialog2'){
                this.editFormData.bsoy = ''
              }
              this.listSelectOptions2()
              this.action = 'openAddDialog'
            }else{
              this.action = 'openAddDialog1'
            }
          }else {
            this.editFormData.bsoy = null

          }
        })
      },
      deep: true
    }
  },
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

        this.dialogProps.visible = true
      })
    })
  }
}
</script>
