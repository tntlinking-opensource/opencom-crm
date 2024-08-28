<template>
  <el-row class="dc-container">
    <el-dialog
      v-on:open="onDialogOpen"
      v-on:close="onDialogClose"
      v-loading="loading"
      width="30%"
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
            <el-form-item prop="parent.id" label="父级类型" class="dc-el-form-item_CascaderInput">
              <el-cascader
                ref="parentCascader"
                :options="parentOptions"
                v-model="editFormData.parent.id"
                :style="{ width: '100%' }"
                placeholder="请选择父级类型"
                :props="{ label: 'pdcName', value: 'id', children: 'children', checkStrictly: true, emitPath: false }"
                clearable
                filterable
                separator="/"
                v-on:change="onParentChange"
                class="dc-el-cascader_CascaderInput"
              ></el-cascader>
            </el-form-item>
            <el-form-item prop="pdcCode" label="节点编号" class="dc-el-form-item_AutoSerial">
              <el-input
                v-model="editFormData.pdcCode"
                :maxLength="100"
                placeholder="保存后自动生成"
                disabled
                :disabled="true"
                class="dc-el-input_AutoSerial"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="pdcName"
              label="节点名称"
              :rules="[{ required: true, message: '节点名称不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_SingleInput"
            >
              <el-input
                v-model="editFormData.pdcName"
                :maxLength="100"
                placeholder="请输入节点名称"
                clearable
                autocomplete="new-password"
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
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
import { treeCrmProductsClassify } from '@/api/crmProClassify/crmProductsClassify.js'
/** 根据用户界面配置import组件 结束 */
import { getCrmProductsClassifyById, saveCrmProductsClassify } from '@/api/crmProClassify/crmProductsClassify'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'
export default {
  extends: BaseUI,
  name: 'crmProductsClassify-form',
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
        title: '产品分类'
      },
      dialogTitle: '产品分类',
      // 选项变量
      // 父级类型选项
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

      saveCrmProductsClassify(this.editFormData)
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
        getCrmProductsClassifyById(id)
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
            resolve(form)
          })
          .catch((error) => {
            this.outputError(error)
          })
      })
    },

    listParentOptions() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])

      // 数据权限: 产品分类crm_products_classify
      this.pushDataPermissions(search_List.params, this.$route.meta.routerId, '1709759135450792260')

      treeCrmProductsClassify(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.parentOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
    },

    initOptions(This) {
      // 初始化自定义类型选择框选项变量

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
        // 父级类型
        parent: {
          id: validatenull(This) || validatenull(This.id) ? null : This.id,
          parentId: validatenull(This) || validatenull(This.parentId) ? null : This.parentId,
          pdcCode: validatenull(This) || validatenull(This.pdcCode) ? null : This.pdcCode,
          pdcName: validatenull(This) || validatenull(This.pdcName) ? null : This.pdcName,
          delFlag: validatenull(This) || validatenull(This.delFlag) ? null : This.delFlag,
          remarks: validatenull(This) || validatenull(This.remarks) ? null : This.remarks,
          createBy: validatenull(This) || validatenull(This.createBy) ? null : This.createBy,
          createDate: validatenull(This) || validatenull(This.createDate) ? null : This.createDate,
          updateBy: validatenull(This) || validatenull(This.updateBy) ? null : This.updateBy,
          updateDate: validatenull(This) || validatenull(This.updateDate) ? null : This.updateDate
        },
        pdcCode: '', // 节点编号
        pdcName: '', // 节点名称
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
