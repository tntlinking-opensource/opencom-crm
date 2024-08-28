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
            <el-col :span="12">
              <el-form-item prop="cntType" label="沟通类型" class="dc-el-form-item_SelectInput">
                <el-select
                  v-model="editFormData.cntType"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  clearable
                  filterable
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in cntTypeOptions"
                    :key="index"
                    :label="item.name"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                prop="cntPname"
                label="沟通人姓名"
                :rules="[{ required: true, message: '沟通人姓名不能为空', trigger: 'blur' }]"
                class="dc-el-form-item_SingleInput"
              >
                <el-input
                  v-model="editFormData.cntPname"
                  :maxLength="100"
                  placeholder="请输入沟通人姓名"
                  clearable
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                prop="cntDate"
                label="沟通时间"
                :rules="[{ required: true, message: '沟通时间不能为空', trigger: 'change' }]"
                class="dc-el-form-item_PickerTimeDate"
              >
                <el-date-picker
                  v-model="editFormData.cntDate"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  type="datetime"
                  format="yyyy-MM-dd HH:mm:ss"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  class="dc-el-date-picker_PickerTimeDate"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item
                prop="cntContent"
                label="沟通内容"
                :rules="[{ required: true, message: '沟通内容不能为空', trigger: 'blur' }]"
                class="dc-el-form-item_MutilpleInput"
              >
                <el-input
                  v-model="editFormData.cntContent"
                  type="textarea"
                  placeholder="请输入"
                  rows="3"
                  :maxLength="100"
                  class="dc-el-input_MutilpleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <upload-file
                title="文件上传"
                :action="action"
                :objectId="editFormData.id"
                v-model="editFormData.files"
                class="dc-upload-file_ElUploadFile"
              ></upload-file>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="null" class="dc-el-form-item_ElCheckbox">
                <el-checkbox v-model="editFormData.cutNextDateIs" class="dc-el-checkbox_ElCheckbox">是否为下次跟进创建任务</el-checkbox>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="cutNextDate" label="下次沟通时间" class="dc-el-form-item_SingleInput">
                <el-date-picker
                  v-model="editFormData.cutNextDate"
                  :maxLength="100"
                  placeholder="请输入下次沟通时间"
                  clearable
                  type="datetime"
                  format="yyyy-MM-dd HH:mm:ss"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  class="dc-el-date-picker_PickerTimeDate"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item prop="null" label="跟进提醒" class="dc-el-form-item_CounterInput">
                <el-input-number
                  v-model="editFormData.cutRemind"
                  :min="0"
                  :max="999"
                  :step="1"
                  :precision="0"
                  :style="{ width: '100%' }"
                  class="dc-el-input-number_CounterInput"
                ></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item prop="cutRemindType" class="dc-el-form-item_SelectInput">
                <el-select
                  v-model="editFormData.cutRemindType"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  clearable
                  filterable
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in cutRemindTypeOptions"
                    :key="index"
                    :label="item.name"
                    :value="item.value"
                  ></el-option>
                </el-select>
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
  import { listDictItemAll } from '@/api/sys/dictItem.js'
import { validatenull } from '@/utils/validate'
/** 根据用户界面配置import组件 结束 */
import uploadFile from '@/views/components/uploadFile'
/** 根据用户界面配置import组件 结束 */
import { getMyClueCrmCommunicateById, saveMyClueCrmCommunicate } from '@/api/myClue/crmCommunicate'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'
export default {
  extends: BaseUI,
  name: 'crmCommunicate-form',
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
        title: '沟通记录'
      },
      dialogTitle: '沟通记录',
      // 选项变量
      // 沟通类型选项
      // selectOptions: [
      //   {
      //     label: '选项一',
      //     value: '1'
      //   },
      //   {
      //     label: '选项二',
      //     value: '2'
      //   }
      // ],
      // 选项
      cntTypeOptions:[],
      cutRemindTypeOptions:[],
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
    listSelectOptions() {
      let search_List = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "CRM_DICT_CNT_TYPE"}]
      }
      listDictItemAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.cntTypeOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
       search_List = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "TIME_DIMENSION"}]
      }
      listDictItemAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.cutRemindTypeOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
    },
    createFormData(editFormData) {
      let formData = new FormData()
      let deletes = []

      for (let idx in editFormData.files.uploads) {
        formData.append('Uploads', editFormData.files.uploads[idx].raw)
      }
      deletes = deletes.concat(editFormData.files.deletes)

      formData.append('entity', JSON.stringify(editFormData))
      formData.append('deleteIds', JSON.stringify(deletes))
      return formData
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
      let formData = this.createFormData(this.editFormData)
      saveMyClueCrmCommunicate(formData)
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
        getMyClueCrmCommunicateById(id)
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

    initOptions(This) {
      this.listSelectOptions()
      // 初始化自定义类型选择框选项变量
    },
    initEditData(This) {
      return {
        cntType: null, // 沟通类型
        cntPname: '', // 沟通人姓名
        cntDate: '2023-09-06 00:00:00', // 沟通时间
        cntContent: '', // 沟通内容
        cutNextDate: '', // 下次沟通时间
        cutRemind: '', // 下次沟通时间
        cutNextDateIs: '', // 是否提醒
        cutRemindType: null ,//
        cntMyclueId: "" ,//
        files : {
          deletes: [], // 待删除（已上传）的文件列表
          uploads: [] // 待上传的文件列表
      }
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
      this.$on('openAddDialog', function (id) {
        this.action = 'add'
        this.dialogProps.title = `添加${this.dialogTitle}`
        this.editFormData = this.initEditData()
        this.initOptions(this.editFormData)
        this.dialogProps.visible = true
        this.editFormData.cntMyclueId=id
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
