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

            <el-form-item
              prop="pdName"
              label="产品名称"
              :rules="[{ required: true, message: '产品名称不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_SingleInput"
            >
              <el-select
                filterable
                v-model="editFormData.pdName"
                @change="handleChange"
                placeholder="请选择">
                <el-option
                  v-for="item in parentOptions"
                  :key="item.value"
                  :label="item.pdName"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item
              prop="pdNo"
              label="产品编号"
              :rules="[{ required: true, message: '产品编号不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_SingleInput"
            >
              <el-input
                v-model="editFormData.pdNo"
                :maxLength="255"
                placeholder="请输入产品编号"
                clearable
                disabled
                autocomplete="new-password"
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="pdClassifyId"
              label="产品分类"
              :rules="[{ required: true, message: '产品分类不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_SingleInput"
            >
              <el-input
                v-model="editFormData.pdClassifyId"
                :maxLength="255"
                placeholder="请输入产品分类"
                clearable
                disabled
                autocomplete="new-password"
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="pdGuidePrice"
              label="指导价"
              :rules="[{ required: true, message: '指导价不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_NumberInput"
            >
              <number-input
                v-model="editFormData.pdGuidePrice"
                :min="0"
                :max="9999999999.99"
                :precision="2"
                placeholder="请输入指导价"
                clearable
                disabled
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
                :min="0"
                :max="9999999999.99"
                :precision="2"
                placeholder="请输入成本价"
                clearable
                disabled
                class="dc-number-input_NumberInput"
              ></number-input>
            </el-form-item>
            <el-form-item
              prop="pdTypeId"
              label="产品类型"
              :rules="[{ required: true, message: '产品类型不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_SingleInput"
            >
              <el-select
                filterable
                v-model="editFormData.pdTypeId"
                @change="handleChange"
                placeholder="请选择">
                <el-option
                  v-for="(item, index)  in CRMDICTPRODUCTTYPE"
                  :key="index"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
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


import { listCrmProductsAll } from '@/api/crmProInfo/crmProducts'
import { validatenull } from '@/utils/validate'
import { listDictItemAll } from '@/api/sys/dictItem.js'
/** 根据用户界面配置import组件 结束 */

/** 根据用户界面配置import组件 结束 */
import { getCrmMyclueProductsById, saveCrmMyclueProducts } from '@/api/myClue/crmMyclueProducts'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'
export default {
  extends: BaseUI,
  name: 'crmMyclueProducts-form',
  components: {
    /** 根据用户界面配置组件 开始 */

    /** 根据用户界面配置组件 结束 */

    OperationIcon
  },
  data() {
    return {
      search: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
      parentOptions:[],
      CRMDICTPRODUCTTYPE:[],
      /** 根据用户界面配置生成data数据 开始 */
      editFormData: this.initEditData(),
      // 对话框属性变量
      dialogProps: {
        visible: false,
        title: '意向产品'
      },
      dialogTitle: '意向产品',
      // 选项变量

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
    handleChange(value) {
      for (let i = 0; i < this.parentOptions.length; i++) {
        if (value==this.parentOptions[i].id){
          this.editFormData.pdNo= this.parentOptions[i].pdNo
          this.editFormData.pdName= this.parentOptions[i].pdName
          this.editFormData.pdZname= this.parentOptions[i].pdName
          this.editFormData.pdClassifyId= this.parentOptions[i].pdClassify.pdcName
          this.editFormData.pdClassifyName= this.parentOptions[i].pdClassify.id
          this.editFormData.pdGuidePrice= this.parentOptions[i].pdGuidePrice
          this.editFormData.pdCostPrice= this.parentOptions[i].pdCostPrice
          this.editFormData.pdTotal= this.parentOptions[i].pdTotal
          this.editFormData.pdTypeId= this.parentOptions[i].pdTypeId
          this.editFormData.remarks= this.parentOptions[i].remarks

        }
      }
      console.log(value);
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

      saveCrmMyclueProducts(this.editFormData)
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
        getCrmMyclueProductsById(id)
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
      // filter条件
      this.search.params = []

      listCrmProductsAll(this.search)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.parentOptions = responseData.data ? responseData.data : []
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
      let search_List = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "CRM_DICT_PRODUCT_TYPE"}]
      }
      listDictItemAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.CRMDICTPRODUCTTYPE = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
      // 初始化自定义类型选择框选项变量
    },
    initEditData(This) {
      return {
        pdNo: '', // 产品编号
        pdName: '', // 产品名称
        pdZname: '', // 产品名称
        pdClassifyId: '', // 产品分类
        pdClassifyName: '', // 产品分类
        pdGuidePrice: null, // 指导价
        pdCostPrice: null, // 成本价
        pdTotal: null, // 库存数量
        pdTypeId: '', // 产品类型
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
