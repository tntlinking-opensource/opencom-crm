<template>
  <el-row class="dc-container">
    <el-dialog v-on:open="onDialogOpen" v-on:close="onDialogClose" v-loading="loading" width="80%" :fullscreen="false"
      :title="dialogProps.title" :visible.sync="dialogProps.visible" :close-on-click-modal="false"
      class="dc-el-dialog_ElDialog">
      <el-row>
        <el-form ref="editForm" :model="editFormData" label-width="150px" disabled class="dc-el-form_ElEditForm">
          <el-row>
            <el-divider>合同信息</el-divider>
            <el-col :span="8">
              <el-form-item prop="contractName" label="合同名称" class="dc-el-form-item_SingleInput">
                <el-input v-model="editFormData.contractName" :maxLength="255" placeholder="请输入合同名称" clearable
                  autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="customerName" label="客户名称" class="dc-el-form-item_SingleInput">
                <el-input v-model="editFormData.customerName" :maxLength="255" placeholder="请输入客户名称" clearable
                  autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="contractAmount" label="合同金额" class="dc-el-form-item_SingleInput">
                <el-input v-model="editFormData.contractAmount" :maxLength="255" placeholder="请输入合同金额" clearable
                  autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="contractCode" label="合同编号" class="dc-el-form-item_SingleInput">
                <el-input v-model="editFormData.contractCode" :maxLength="255" placeholder="请输入合同编号" clearable
                  autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="paymentBackPlanNo" label="回款计划编号" class="dc-el-form-item_SingleInput">
                <el-input v-model="editFormData.paymentBackPlanNo" :maxLength="255" placeholder="请输入回款计划编号" clearable
                  autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="oddPayAmount" label="剩余还款金额" class="dc-el-form-item_SingleInput">
                <el-input v-model="editFormData.oddPayAmount" :maxLength="255" placeholder="请输入剩余还款金额" clearable
                  autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="beginDate" label="开始日期" class="dc-el-form-item_SingleInput">
                <el-date-picker v-model="editFormData.beginDate" type="date" value-format='yyyy-MM-dd' clearable
                  style="width: 100%;" placeholder="选择开始日期"></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="endDate" label="结束日期" class="dc-el-form-item_SingleInput">
                <el-date-picker v-model="editFormData.endDate" type="date" value-format='yyyy-MM-dd' clearable
                  style="width: 100%;" placeholder="选择开始日期"></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-row>
      <el-divider>还款情况</el-divider>
      <el-form ref="taxDetailsInfoForm" :model="editFormData" label-width="100px" :disabled="action === 'view'"
        class="dc-el-form_ElEditForm">
        <el-row>
          <ux-grid :data="editFormData.paymentBackList" ref="taxDetailsInfoTable" height="200px" border
            :edit-config="{ trigger: 'click', mode: 'cell' }" class="dc-ux-grid_EditTable">
            <ux-table-column title="序号" type="index" tree-node resizable min-width="80px" fixed="left" align="left"
              header-align="center" class="dc-ux-table-column_ElTableNumColumn"></ux-table-column>
            <ux-table-column field="order" title="期数" tree-node resizable min-width="60px" align="left"
              header-align="center" show-overflow class="dc-ux-table-column_SingleInput">

              <template v-slot="{ row, rowIndex }">
                <span class="my-input-sc">
                  <el-form-item :prop="'paymentBackList.' + rowIndex + '.order'" label-width="0px"
                    class="dc-el-form-item_SingleInput">
                    {{ row.order }}
                  </el-form-item>
                </span>
              </template>
            </ux-table-column>
            <ux-table-column field="payDate" title="计划还款日期" tree-node resizable min-width="120px" align="left"
              header-align="center" show-overflow class="dc-ux-table-column_SingleInput">
              <template v-slot="{ row, rowIndex }">
                <span class="my-input-sc">
                  <el-form-item :prop="'paymentBackList.' + rowIndex + '.payDate'" label-width="0px"
                    class="dc-el-form-item_SingleInput">
                    {{ row.payDate }}
                  </el-form-item>
                </span>
              </template>
            </ux-table-column>
            <ux-table-column field="payRate" title="计划还款比例(%)" tree-node resizable min-width="120px" align="left"
              header-align="center" show-overflow class="dc-ux-table-column_SingleInput">
              <template v-slot="{ row, rowIndex }">
                <span class="my-input-sc">
                  <el-form-item :prop="'paymentBackList.' + rowIndex + '.payRate'" label-width="0px"
                    class="dc-el-form-item_SingleInput">
                    {{ row.payRate }}
                  </el-form-item>
                </span>
              </template>
            </ux-table-column>
            <ux-table-column field="payAmount" title="计划还款金额" tree-node resizable min-width="120px" align="right"
              header-align="center" show-overflow class="dc-ux-table-column_SingleInput">
              <template v-slot="{ row, rowIndex }">
                <span class="my-input-sc" style="width: 100%;">
                  <el-form-item :prop="'paymentBackList.' + rowIndex + '.payAmount'" label-width="0px"
                    class="dc-el-form-item_SingleInput">
                    {{ row.payAmount }}
                  </el-form-item>
                </span>
              </template>
            </ux-table-column>
            <ux-table-column field="paymentBackDate" title="实际回款日期" tree-node resizable min-width="120px" align="left"
              header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
              class="dc-ux-table-column_SingleInput">
              <template v-slot:edit="{ row, rowIndex }">
                <el-form-item :prop="'paymentBackList.' + rowIndex + '.paymentBackDate'" label-width="0px"
                  class="dc-el-form-item_SingleInput">
                  <el-date-picker v-model="row.paymentBackDate" :style="{ width: '100%' }" placeholder="请选择" type="date"
                    format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                    class="dc-el-date-picker_PickerTimeDate"></el-date-picker>
                </el-form-item>
              </template>
              <template v-slot="{ row, rowIndex }">
                <span class="my-input-sc">
                  <el-form-item :prop="'paymentBackList.' + rowIndex + '.paymentBackDate'" label-width="0px"
                    class="dc-el-form-item_SingleInput">
                    {{ row.paymentBackDate }}
                  </el-form-item>
                </span>
              </template>
            </ux-table-column>
            <ux-table-column field="paymentBackAmount" title="实际回款金额" tree-node resizable min-width="120px" align="right"
              header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
              class="dc-ux-table-column_SingleInput">
              <template v-slot:edit="{ row, rowIndex }">
                <el-form-item :prop="'paymentBackList.' + rowIndex + '.paymentBackAmount'" label-width="0px"
                  class="dc-el-form-item_SingleInput">
                  <el-input v-model="row.paymentBackAmount" placeholder="请输入实际回款金额" clearable autocomplete="new-password"
                    class="dc-el-input_SingleInput"></el-input>
                </el-form-item>
              </template>
              <template v-slot="{ row, rowIndex }">
                <span class="my-input-sc" style="width: 100%;">
                  <el-form-item :prop="'paymentBackList.' + rowIndex + '.paymentBackAmount'" label-width="0px"
                    class="dc-el-form-item_SingleInput">
                    {{ row.paymentBackAmount }}
                  </el-form-item>
                </span>
              </template>
            </ux-table-column>
            <ux-table-column field="remark" title="备注" tree-node resizable min-width="120px" align="alter"
              header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
              class="dc-ux-table-column_SingleInput">
              <template v-slot:edit="{ row, rowIndex }">
                <el-form-item :prop="'paymentBackList.' + rowIndex + '.remark'" label-width="0px"
                  class="dc-el-form-item_SingleInput">
                  <el-input v-model="row.remark" placeholder="请输入备注" clearable autocomplete="new-password"
                    class="dc-el-input_SingleInput"></el-input>
                </el-form-item>
              </template>
              <template v-slot="{ row, rowIndex }">
                <span class="my-input-sc">
                  <el-form-item :prop="'paymentBackList.' + rowIndex + '.remark'" label-width="0px"
                    class="dc-el-form-item_SingleInput">
                    {{ row.remark }}
                  </el-form-item>
                </span>
              </template>
            </ux-table-column>
          </ux-grid>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button v-on:click="onSubmit" type="primary" v-show="action != 'view'">保存</el-button>
        <el-button v-on:click="onDialogClose" v-if="action != 'view'">取消</el-button>
        <el-button v-on:click="onDialogClose" v-if="action == 'view'">关闭</el-button>
      </span>
    </el-dialog>
  </el-row>
</template>
<script>
/** 根据用户界面配置import组件 结束 */
import { getByContractId, savePaymentBack } from '@/api/paymentBack/paymentBack'
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
        title: '回款信息'
      },
      dialogTitle: '还款信息',
      /** 根据用户界面配置生成data数据 结束 */
      // 窗口操作类型 view/edit/add
      action: '',
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
      savePaymentBack(this.editFormData.paymentBackList)
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

    getFormById(id) {
      this.setLoad()
      return new Promise((resolve) => {
        getByContractId(id)
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
      // 初始化自定义类型选择框选项变量
    },
    initEditData(This) {
      return {
        customName: null, // 客户名称
        bsoy: null, // 商机名称
        contactsName: '', // 联系人姓名
        phoneNumber: '', // 手机号
        depart: '', // 所属部门
        workPost: '', // 期数
        sex: null, // 性别
        borndate: '', // 出生日期
        contactPhone: '', // 电话
        email: '', // 邮箱
        wenumber: '', // 微信
        wbnumber: '', // 微博
        qnumber: '', // QQ
        region: null, // 所在区域
        address: '', // 详细地址
        notes: '', // 备注
        paymentBackList: []
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
    })
  },

}
</script>
