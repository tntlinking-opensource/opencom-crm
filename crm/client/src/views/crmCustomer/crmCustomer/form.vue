<template>
  <el-row class="dc-container">
    <el-dialog
      v-on:open="onDialogOpen"
      v-on:close="onDialogClose"
      v-loading="loading"
      width="80%"
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
                prop="cusName"
                label="客户名称"
                :rules="[{ required: true, message: '客户名称不能为空', trigger: 'blur' }]"
                class="dc-el-form-item_SingleInput"
              >
                <el-input
                  v-model="editFormData.cusName"
                  :maxLength="300"
                  placeholder="请输入客户名称"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                prop="cusType"
                label="客户类型"
                :rules="[{ required: true, message: '客户类型不能为空', trigger: 'change' }]"
                class="dc-el-form-item_SelectInput"
              >
                <el-select
                  v-model="editFormData.cusType"
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
            <el-col :span="12">
              <el-form-item
                prop="cusSource"
                label="客户来源"
                :rules="[{ required: true, message: '客户来源不能为空', trigger: 'change' }]"
                class="dc-el-form-item_SelectInput"
              >
                <el-select
                  v-model="editFormData.cusSource"
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
            <el-divider content-position="left" class="dc-el-divider_ElDivider">其他信息</el-divider>
            <el-col :span="12">
              <el-form-item prop="cusContacts" label="联系人" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.cusContacts"
                  :maxLength="50"
                  placeholder="请输入联系人"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="cusMobile" label="联系方式" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.cusMobile"
                  :maxLength="30"
                  placeholder="请输入联系方式"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="parent.id" label="上级客户" class="dc-el-form-item_CascaderInput">
                <el-cascader
                  ref="parentCascader"
                  :options="parentOptions"
                  v-model="editFormData.parent.id"
                  :style="{ width: '100%' }"
                  placeholder="请选择上级客户"
                  :props="{ label: 'cusName', value: 'id', children: 'children', checkStrictly: true, emitPath: false }"
                  clearable
                  filterable
                  separator="/"
                  v-on:change="onParentChange"
                  class="dc-el-cascader_CascaderInput"
                ></el-cascader>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="cusRegion" label="所在区域" class="dc-el-form-item_SelectInput">
                <el-select
                  v-model="editFormData.cusRegion"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  clearable
                  filterable
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in district"
                    :key="item.name"
                    :label="item.name"
                    :value="item.name"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="cusAddress" label="详细地址" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.cusAddress"
                  :maxLength="100"
                  placeholder="请输入详细地址"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="remarks" label="备注信息" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editFormData.remarks"
                  type="textarea"
                  placeholder="请输入备注信息"
                  rows="2"
                  :maxLength="255"
                  class="dc-el-input_MutilpleInput"
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
import {validatenull} from '@/utils/validate'
/** 根据用户界面配置import组件 结束 */
import {getDictTypeById} from '@/api/sys/dictType.js'
import {treeCrmCustomer} from '@/api/crmCustomer/crmCustomer.js'
/** 根据用户界面配置import组件 结束 */
import {getCrmCustomerById, saveCrmCustomer} from '@/api/crmCustomer/crmCustomer'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'

export default {
  extends: BaseUI,
  name: 'crmCustomer-form',
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
        title: '客户管理'
      },
      dialogTitle: '客户管理',
      selectOptions4: [],
      custmerSource: [],
      district: [],
      // 上级客户选项
      parentOptions: [],
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
    handleGetOptions () {
      // 客户类型
      getDictTypeById('1705054496995033109').then(res => {
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
      getDictTypeById('1693069373575184403').then(res => {
        if (res.code === '100') {
          this.district = res.data.dictItemList
        }
      })
    },
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
      saveCrmCustomer({
        ...this.editFormData,
        cusStage: this.editForm && this.editForm.cusStage ? this.editForm.cusStage : '01'
      })
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

    onDialogClose() {
      this.dialogProps.visible = false
    },
    onDialogOpen() {
      this.$nextTick(() => {
        this.$refs['editForm'].clearValidate()
      })
    },
    getFormById(id) {
      this.setLoad()
      return new Promise((resolve) => {
        getCrmCustomerById(id)
          .then((responseData) => {
            let form = {}
            if (responseData.code == 100) {
              form = responseData.data
              if (validatenull(form.parent)) {
                form.parent = {
                  id: null
                }
              }
            } else {
              this.showMessage(responseData)
            }
            this.resetLoad()
            console.log(form);
            resolve(form)
          })
          .catch((error) => {
            this.outputError(error)
          })
      })
    },

    listSelectOptions(type) {
      // 类型
      let typeId = "";
      if (type == 1) {
        //阶段
        typeId = "1705054496995033199";
      } else if (type == 2) {
        //类型
        typeId = "1705054496995033109";
      } else if (type == 3) {
        //来源
        typeId = "1705054496995033113";
      } else if (type == 4) {
        //区域
        typeId = "1705054496995033128";
      } else {
        return
      }

      getDictTypeById(typeId).then((responseData) => {
        if (responseData.code == 100) {
          //console.dir(responseData.data.dictItemList)
          if (type == 1) {
            this.selectOptions1 = responseData.data.dictItemList
          } else if (type == 2) {
            this.selectOptions2 = responseData.data.dictItemList
          } else if (type == 3) {
            this.selectOptions3 = responseData.data.dictItemList
          } else if (type == 4) {
            this.selectOptions4 = responseData.data.dictItemList
          }


        } else {
          this.showMessage(responseData)
        }
      })
    },


    listParentOptions() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])

      // 数据权限: 客户管理crm_customer
      this.pushDataPermissions(search_List.params, this.$route.meta.routerId, '1709759135450792386')

      treeCrmCustomer(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.parentOptions = responseData.data.rows
        } else {
          this.showMessage(responseData)
        }
      })
    },


    initOptions(This) {
      // 初始化自定义类型选择框选项变量
      // 客户阶段选项
      this.listSelectOptions(1)
      // 客户类型选项
      this.listSelectOptions(2)
      // 客户来源选项
      this.listSelectOptions(3)
      // 所在区域选项
      this.listSelectOptions(4)

      this.listParentOptions()

    },
    onParentChange() {
      let nodes = this.$refs['parentCascader'].getCheckedNodes()
      if (nodes.length > 0) {
        if (nodes[0] && nodes[0].data && nodes[0].data.children) {
          this.editFormData.sort = (nodes[0].data.children.length + 1) * 10000
        } else if (nodes[0]) {
          this.editFormData.sort = 10000
        } else {
          this.editFormData.sort = (this.parentOptions.length + 1) * 10000
        }
      } else {
        this.editFormData.sort = (this.parentOptions.length + 1) * 10000
      }
    },
    initEditData(This) {
      return {
        cusName: '', // 客户名称
        cusStage: null, // 客户阶段
        cusType: null, // 客户类型
        cusSource: null, // 客户来源
        cusMobile: '', // 联系方式
        // 上级客户
        parent: {
          id: validatenull(This) || validatenull(This.id) ? null : This.id,
          cusName: validatenull(This) || validatenull(This.cusName) ? null : This.cusName
        },
        cusContacts: '', // 联系人
        cusAddress: '', // 详细地址
        cusRegion: null, // 所在区域
        remarks: '' // 备注信息
      }
    }
  },
  watch: {
    parentOptions(newVal, oldVal) {
      if (newVal != oldVal && this.action == 'add') {
        this.$nextTick(() => {
          this.onParentChange()
        })
      }
    }
  },
  mounted () {
    this.$nextTick(() => {
      this.handleGetOptions()
      this.$on('openViewDialog', async function (row) {
        this.action = 'view'
        this.dialogProps.title = `查看${this.dialogTitle}`
        this.editFormData = {
          ...this.initEditData(),
          ...row
        }
        this.initOptions(this.editFormData)
        this.dialogProps.visible = true
      })

      this.$on('openEditDialog', async function (id) {
        this.action = 'edit'
        this.dialogProps.title = `修改${this.dialogTitle}`
        const data1 = await this.getFormById(id)
        this.editFormData = {
          ...this.initEditData(),
          ...data1,
          ...data1.crmCustomer
        }
        this.initOptions(this.editFormData)
        this.dialogProps.visible = true
      })
      this.$on('openAddDialog', function (parent) {
        this.action = 'add'
        this.dialogProps.title = `添加${this.dialogTitle}`
        this.editFormData = this.initEditData(parent)
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
