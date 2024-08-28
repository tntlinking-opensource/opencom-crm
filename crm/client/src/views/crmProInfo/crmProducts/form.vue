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
            <el-form-item prop="pdNo" label="产品编号" class="dc-el-form-item_AutoSerial">
              <el-input
                v-model="editFormData.pdNo"
                :maxLength="255"
                placeholder="保存后自动生成"
                disabled
                :disabled="true"
                class="dc-el-input_AutoSerial"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="pdName"
              label="产品名称"
              :rules="[{ required: true, message: '产品名称不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_SingleInput"
            >
              <el-input
                v-model="editFormData.pdName"
                :maxLength="255"
                placeholder="请输入产品名称"
                clearable
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="pdClassify.id"
              label="产品分类"
              :rules="[{ required: true, message: '产品分类不能为空', trigger: 'change' }]"
              class="dc-el-form-item_CascaderInput"
            >
              <el-cascader
                ref="cascader108"
                :options="pdClassifyOptions"
                v-model="editFormData.pdClassify.id"
                :style="{ width: '100%' }"
                placeholder="请选择产品分类"
                :props="{ label: 'pdcName', value: 'id', children: 'children', checkStrictly: true, emitPath: false }"
                clearable
                filterable
                separator="/"
                class="dc-el-cascader_CascaderInput"
              ></el-cascader>
            </el-form-item>
            <el-form-item
              prop="pdGuidePrice"
              label="指导价"
              :rules="[{ required: true, message: '指导价不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_NumberInput"
            >
              <number-input
                v-model="editFormData.pdGuidePrice"
                currency="CNY"
                :min="0"
                :max="9999999999.99"
                :precision="2"
                placeholder="请输入指导价"
                clearable
                class="dc-number-input_NumberInput"
              ></number-input>
            </el-form-item>
            <el-form-item
              prop="pdCostPrice"
              label="成本价"
              :rules="[{ required: true, message: '成本价不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_NumberInput"
            >
              <number-input
                v-model="editFormData.pdCostPrice"
                currency="CNY"
                :min="0"
                :max="9999999999.99"
                :precision="2"
                placeholder="请输入成本价"
                clearable
                class="dc-number-input_NumberInput"
              ></number-input>
            </el-form-item>
            <el-form-item
              prop="pdTotal"
              label="库存数量"
              :rules="[{ required: true, message: '库存数量不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_NumberInput"
            >
              <number-input
                v-model="editFormData.pdTotal"
                :min="0"
                :max="99999999999"
                :precision="0"
                placeholder="请输入库存数量"
                clearable
                class="dc-number-input_NumberInput"
              ></number-input>
            </el-form-item>
            <el-form-item prop="pdTypeId" label="产品类型" class="dc-el-form-item_SelectInput">
              <el-select
                v-model="editFormData.pdTypeId"
                :style="{ width: '100%' }"
                placeholder="请选择"
                clearable
                filterable
                class="dc-el-select_SelectInput"
              >
                <el-option
                  v-for="(item, index) in selectOptions"
                  :key="index"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item
              prop="pdStatus"
              label="状态"
              :rules="[{ required: true, message: '状态不能为空', trigger: 'change' }]"
              class="dc-el-form-item_Switch"
            >
              <el-switch
                v-model="editFormData.pdStatus"
                active-value="1"
                inactive-value="0"
                class="dc-el-switch_Switch"
              ></el-switch>
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
import { getCrmProductsById, saveCrmProducts } from '@/api/crmProInfo/crmProducts'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'
export default {
  extends: BaseUI,
  name: 'crmProducts-form',
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
        title: '产品管理'
      },
      dialogTitle: '产品管理',
      // 选项变量
      // 产品分类选项
      pdClassifyOptions: [],
      // 产品类型选项
      selectOptions: [
        {
          label: '实体类',
          value: '1'
        },
        {
          label: '虚拟类',
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

      saveCrmProducts(this.editFormData)
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
        getCrmProductsById(id)
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

    listPdClassifyOptions() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])

      // 数据权限: 产品分类crm_products_classify
      this.pushDataPermissions(search_List.params, this.$route.meta.routerId, '1709759135450792260')

      treeCrmProductsClassify(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.pdClassifyOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
    },

    initOptions(This) {
      // 初始化自定义类型选择框选项变量

      this.listPdClassifyOptions()
    },
    initEditData(This) {
      return {
        pdNo: '', // 产品编号
        pdName: '', // 产品名称
        // 产品分类
        pdClassify: {
          id: null,
          parentId: null,
          pdcCode: null,
          pdcName: null,
          createBy: null,
          createDate: null,
          updateBy: null,
          updateDate: null,
          delFlag: null,
          remarks: null
        },
        pdGuidePrice: null, // 指导价
        pdCostPrice: null, // 成本价
        pdTotal: null, // 库存数量
        pdTypeId: null, // 产品类型
        pdStatus: '0', // 状态
        remarks: '' // 备注信息
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
      this.$on('openAddDialog', function (parent) {
        this.action = 'add'
        this.dialogProps.title = `添加${this.dialogTitle}`
        this.editFormData = {
          ...this.initEditData(),
          ...parent
        }
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
