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
                <el-col :span="24">
                  <el-form-item prop="cluesResponsible" label="线索负责人" class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.cluesResponsibleName"
                      :maxLength="50"
                      placeholder=""
                      clearable
                      disabled
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-divider content-position="left" class="dc-el-divider_ElDivider">主要信息</el-divider>
                <el-col :span="12">
                  <el-form-item
                    prop="cluesName"
                    label="线索名称"
                    :rules="[{ required: true, message: '线索名称不能为空', trigger: 'blur' }]"
                    class="dc-el-form-item_SingleInput"
                  >
                    <el-input
                      v-model="editFormData.cluesName"
                      :maxLength="30"
                      placeholder="请输入线索名称"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item
                    prop="cluesPool.id"
                    label="所属线索池"
                    :rules="[{ required: true, message: '所属线索池不能为空', trigger: 'change' }]"
                    class="dc-el-form-item_SelectInput"
                  >
                    <el-select
                      v-model="editFormData.cluesPool"
                      :style="{ width: '100%' }"
                      placeholder="请选择所属线索池"
                      clearable
                      value-key="id"
                      filterable
                      class="dc-el-select_SelectInput"
                    >
                      <el-option
                        v-for="(item, index) in cluesPoolOptions"
                        :key="index"
                        :label="item.cplName"
                        :value="item"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item
                    prop="cluesSources"
                    label="线索来源"
                    :rules="[{ required: true, message: '线索来源不能为空', trigger: 'change' }]"
                    class="dc-el-form-item_SelectInput"
                  >
                    <el-select
                      v-model="editFormData.cluesSources"
                      :style="{ width: '100%' }"
                      placeholder="请选择线索来源"
                      clearable
                      value-key="id"
                      filterable
                      class="dc-el-select_SelectInput"
                    >
                      <el-option
                        v-for="(item, index) in cluesSourcesOptions"
                        :key="index"
                        :label="item.name"
                        :value="item.value"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item
                    prop="cluesContacts"
                    label="线索联系人"
                    :rules="[{ required: true, message: '线索联系人不能为空', trigger: 'blur' }]"
                    class="dc-el-form-item_SingleInput"
                  >
                    <el-input
                      v-model="editFormData.cluesContacts"
                      :maxLength="30"
                      placeholder="请输入线索联系人"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item
                    prop="phoneNumber"
                    label="手机号"
                    :rules="[
                      { required: true, message: '手机号不能为空', trigger: 'blur' },
                      { pattern: new RegExp(`^1[34578]\\d{9}$`), message: '请输入正确的手机号格式', trigger: 'blur' }
                    ]"
                    class="dc-el-form-item_SingleInput"
                  >
                    <el-input
                      v-model="editFormData.phoneNumber"
                      :maxLength="15"
                      placeholder="请输入手机号"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-divider content-position="left" class="dc-el-divider_ElDivider">其他信息</el-divider>
                <el-col :span="12">
                  <el-form-item prop="company" label="公司" class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.company"
                      :maxLength="100"
                      placeholder="请输入公司"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item prop="department" label="所属部门" class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.department"
                      :maxLength="100"
                      placeholder="请输入所属部门"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item prop="position" label="职位" class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.position"
                      :maxLength="60"
                      placeholder="请输入职位"
                      clearable
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
                        v-for="(item, index) in selectOptions"
                        :key="index"
                        :label="item.name"
                        :value="item.value"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="24">
                  <el-form-item prop="addr" label="详细地址" class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.addr"
                      :maxLength="200"
                      placeholder="请输入详细地址"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item prop="gender" label="性别" class="dc-el-form-item_SelectInput">
                    <el-select
                      v-model="editFormData.gender"
                      :style="{ width: '100%' }"
                      placeholder="请选择性别"
                      clearable
                      value-key="id"
                      filterable
                      class="dc-el-select_SelectInput"
                    >
                      <el-option
                        v-for="(item, index) in genderOptions"
                        :key="index"
                        :label="item.name"
                        :value="item.value"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item prop="birthDate" label="出生日期" class="dc-el-form-item_PickerTimeDate">
                    <el-date-picker
                      v-model="editFormData.birthDate"
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
                  <el-form-item prop="telephone" label="电话" class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.telephone"
                      :maxLength="30"
                      placeholder="请输入电话"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item prop="email" label="邮箱" class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.email"
                      :maxLength="60"
                      placeholder="请输入邮箱"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item prop="wechat" label="微信" class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.wechat"
                      :maxLength="60"
                      placeholder="请输入微信"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item prop="qq" label="QQ" class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.qq"
                      :maxLength="60"
                      placeholder="请输入QQ"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item prop="microblog" label="微博" class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.microblog"
                      :maxLength="60"
                      placeholder="请输入微博"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="24">
                  <el-form-item prop="remarks" label="备注" class="dc-el-form-item_MutilpleInput">
                    <el-input
                      v-model="editFormData.remarks"
                      type="textarea"
                      placeholder="请输入备注"
                      rows="2"
                      :maxLength="100"
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
import { validatenull } from '@/utils/validate'
/** 根据用户界面配置import组件 结束 */
import { listMyCrmCluePoolAll } from '@/api/myClue/crmCluePool.js'
import { listDictItemAll } from '@/api/sys/dictItem.js'
/** 根据用户界面配置import组件 结束 */
import { getCrmCluesById, saveCrmClues } from '@/api/myClue/crmClues'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'
export default {
  extends: BaseUI,
  name: 'crmClues-form',
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
        title: '我的线索'
      },
      dialogTitle: '我的线索',
      // 选项变量
      // 所属线索池选项
      cluesPoolOptions: [],
      // 线索来源选项
      cluesSourcesOptions: [],
      // 所在区域选项
      selectOptions: [],
      // 性别选项
      genderOptions: [],
      tabName: 'editForm', // tab标签页
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
      let validFlag = true

      this.$refs['editForm'].validate((valid) => {
        if (valid) {
        } else {
          this.tabName = 'editForm'
          validFlag = false
          return false
        }
      })
      if (validFlag) {
        this.doSave()
      }
    },
    doSave() {
      this.setLoad()

      saveCrmClues(this.editFormData)
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
        getCrmCluesById(id)
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

    listCluesPoolOptions() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])

      // 数据权限: 线索池管理crm_clue_pool
      this.pushDataPermissions(search_List.params, this.$route.meta.routerId, '1709759135450792838')

      listMyCrmCluePoolAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.cluesPoolOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
    },

    listCluesSourcesOptions() {
      let dictItem = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "CLUE_SOURCE"}]
      };
      listDictItemAll(dictItem).then((responseData) => {
        if (responseData.code == 100) {
          this.cluesSourcesOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
      dictItem = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "GENDER"}]
      };
      listDictItemAll(dictItem).then((responseData) => {
        if (responseData.code == 100) {
          this.genderOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
      dictItem = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "AREA"}]
      };
      listDictItemAll(dictItem).then((responseData) => {
        if (responseData.code == 100) {
          this.selectOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
    },

    initOptions(This) {
      // 初始化自定义类型选择框选项变量

      this.listCluesPoolOptions()

      this.listCluesSourcesOptions()
    },
    onDeleteChild(tableData, index) {
      // 根据下标删除子表数据
      tableData.splice(index, 1)
    },
    initEditData(This) {
      return {
        cluesResponsible: currentUser.id, // 线索负责人
        cluesResponsibleName: currentUser.name, // 线索负责人
        cluesName: '', // 线索名称
        cluesPool: {
          id: null,
          cplName: null
        },
        cluesState:"0",
        cluesSources: '', // 线索来源
        cluesContacts: '', // 线索联系人
        phoneNumber: '', // 手机号
        company: '', // 公司
        department: '', // 所属部门
        position: '', // 职位
        region: null, // 所在区域
        addr: '', // 详细地址
        gender: null, // 性别
        birthDate: '', // 出生日期
        telephone: '', // 电话
        email: '', // 邮箱
        wechat: '', // 微信
        qq: '', // QQ
        microblog: '', // 微博
        remarks: '' // 备注
      }
    }
  },
  watch: {
    tabName(val, oldVal) {
      this.$nextTick(() => {
        if (this.$refs[val] && this.$refs[val].doLayout) {
          this.$refs[val].doLayout()
        }
      })
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
        this.tabName = 'editForm'
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
        this.tabName = 'editForm'
        this.dialogProps.visible = true
      })
      this.$on('openAddDialog', function () {
        this.action = 'add'
        this.dialogProps.title = `添加${this.dialogTitle}`
        this.editFormData = this.initEditData()
        this.initOptions(this.editFormData)
        this.tabName = 'editForm'
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
        this.tabName = 'editForm'
        this.editFormData.id = null //把id设置为空，添加一个新的

        this.dialogProps.visible = true
      })
    })
  }
}
</script>
<style>
.my-input-sc {
  display: inline-block;
  height: 30px;
  line-height: 30px;
  -webkit-appearance: none;
  background-color: #ffffff;
  background-image: none;
  border-radius: 4px;
  border: 1px solid #dcdfe6;
  box-sizing: border-box;
  color: #606266;
  font-size: inherit;
  outline: none;
  padding: 0 15px;
  transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
  width: 100%;
}
</style>
