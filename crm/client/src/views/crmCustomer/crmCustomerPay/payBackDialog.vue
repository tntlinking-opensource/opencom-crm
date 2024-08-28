<template>
  <el-dialog v-on:open="onDialogOpen" v-on:close="onDialogClose" v-loading="loading" width="80%" :fullscreen="false"
             :title="dialogProps.title" :visible.sync="dialogProps.visible" :close-on-click-modal="false"
             class="dc-el-dialog_ElDialog" append-to-body>
    <el-row>
      <el-form ref="editForm" :model="editFormData" label-width="150px" :disabled="action == 'view'"
               class="dc-el-form_ElEditForm">
        <el-row>
          <el-divider>合同信息</el-divider>
          <el-col :span="8">
            <el-form-item prop="contractName" label="合同名称" class="dc-el-form-item_SingleInput">
              <el-input v-model="editFormData.contractName" :maxLength="255" placeholder="请输入合同名称" clearable
                        autocomplete="new-password" class="dc-el-input_SingleInput" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="customerName" label="客户名称" class="dc-el-form-item_SingleInput">
              <el-input v-model="editFormData.customerName" :maxLength="255" placeholder="请输入合同名称" clearable
                        autocomplete="new-password" class="dc-el-input_SingleInput" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="contractAmount" label="合同金额" class="dc-el-form-item_SingleInput">
              <el-input v-model="editFormData.contractAmount" :maxLength="255" placeholder="请输入合同金额" clearable
                        autocomplete="new-password" class="dc-el-input_SingleInput" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="contractCode" label="合同编号" class="dc-el-form-item_SingleInput">
              <el-input v-model="editFormData.contractCode" :maxLength="255" placeholder="请输入合同编号" clearable
                        autocomplete="new-password" class="dc-el-input_SingleInput" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="paymentBackPlanNo" label="回款计划编号" class="dc-el-form-item_SingleInput">
              <el-input v-model="editFormData.paymentBackPlanNo" :maxLength="255" placeholder="请输入合同编号" clearable
                        autocomplete="new-password" class="dc-el-input_SingleInput" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="oddPayAmount" label="剩余还款金额" class="dc-el-form-item_SingleInput">
              <el-input v-model="editFormData.oddPayAmount" :maxLength="255" placeholder="请输入合同编号" clearable
                        autocomplete="new-password" class="dc-el-input_SingleInput" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="beginDate" label="开始日期" class="dc-el-form-item_SingleInput">
              <el-input v-model="editFormData.beginDate" :maxLength="255" placeholder="请输入合同编号" clearable
                        autocomplete="new-password" class="dc-el-input_SingleInput" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="endDate" label="结束日期" class="dc-el-form-item_SingleInput">
              <el-input v-model="editFormData.endDate" :maxLength="255" placeholder="请输入合同编号" clearable
                        autocomplete="new-password" class="dc-el-input_SingleInput" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>


      </el-form>
    </el-row>
    <el-divider>还款情况</el-divider>
    <div style="margin-bottom:10px">
        <span
          style="margin-left: 85%;">总计还款金额：{{editFormData.totalPayAmount}}元</span>
    </div>
    <el-form ref="taxDetailsInfoForm" :model="editFormData" label-width="100px" :disabled="action == 'view'"
             class="dc-el-form_ElEditForm">
      <el-row>
        <ux-grid :data="editFormData.paymentBackList" ref="taxDetailsInfoTable" :currentRow="table124CurrentRow"
                 height="300px" border :edit-config="{ trigger: 'click', mode: 'cell' }" class="dc-ux-grid_EditTable">
          <ux-table-column
            title="序号"
            type="index"
            tree-node
            resizable
            min-width="80px"
            fixed="left"
            align="left"
            header-align="center"
            class="dc-ux-table-column_ElTableNumColumn"
          ></ux-table-column>
          <ux-table-column field="id" title="id" tree-node resizable min-width="60px" align="left"
                           header-align="center" v-if="false" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
                           class="dc-ux-table-column_SingleInput">
          </ux-table-column>
          <ux-table-column field="order" title="期数" tree-node resizable min-width="60px" align="left"
                           header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
                           class="dc-ux-table-column_SingleInput">
          </ux-table-column>
          <ux-table-column field="payDate" title="计划还款日期" tree-node resizable min-width="120px" align="left"
                           header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
                           class="dc-ux-table-column_SingleInput">
          </ux-table-column>
          <ux-table-column field="payRate" title="计划还款比例" tree-node resizable min-width="120px" align="left"
                           header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
                           class="dc-ux-table-column_SingleInput">
          </ux-table-column>
          <ux-table-column field="payAmount" title="计划还款金额" tree-node resizable min-width="120px" align="left"
                           header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
                           class="dc-ux-table-column_SingleInput">

          </ux-table-column>
          <ux-table-column field="paymentBackDate" title="实际回款日期" tree-node resizable min-width="120px"
                           align="left"
                           header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
                           class="dc-ux-table-column_SingleInput">
            <template v-slot:edit="{ row, rowIndex }">
              <el-form-item :prop="'paymentBackList.' + rowIndex + '.paymentBackDate'" label-width="0px"
                            class="dc-el-form-item_SingleInput">
                <el-date-picker v-model="row.paymentBackDate" :style="{ width: '100%' }" placeholder="请选择"
                                type="date"
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
          <ux-table-column field="paymentBackAmount" title="实际回款金额" tree-node resizable min-width="120px"
                           align="left"
                           header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
                           class="dc-ux-table-column_SingleInput">
            <template v-slot:edit="{ row, rowIndex }">
              <el-form-item :prop="'paymentBackList.' + rowIndex + '.paymentBackAmount'" label-width="0px"
                            class="dc-el-form-item_SingleInput">
                <el-input v-model="row.paymentBackAmount" placeholder="请输入实际回款金额" clearable
                          autocomplete="new-password"
                          class="dc-el-input_SingleInput"></el-input>
              </el-form-item>
            </template>
            <template v-slot="{ row, rowIndex }">
                  <span class="my-input-sc">
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
</template>
<script>
/** 根据用户界面配置import组件 结束 */
import {getByContractId, savePaymentBack} from '@/api/paymentBack/paymentBack'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'
import {getCrmOpportunityPayById, savePayBackInfo} from "../../../api/crmBusiness/crmOpportunityPay";

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
      dialogTitle: '汇款信息',
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
      action: '',
      workForm: {dataList: []},
      sjId:'',
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
      if (this.editFormData.paymentBackList.length > 0) {
        let arr = JSON.parse(JSON.stringify(this.editFormData.paymentBackList))
        let isEmpty = false, isValid = true
        // arr.forEach(item => {
        //   if (!item.rateLevel || !item.rate) {
        //     isEmpty = true
        //   }
        //   if ((!item.speedDeductAmount && item.speedDeductAmount !== 0) || (!item.amountUp && item.amountUp !== 0) || (!item.amountDown && item.amountDown !== 0) || !item.freeTaxAmount || !item.monthTaxAmount) {
        //     isValid = false
        //   }
        // })

        if (!isEmpty && isValid) {
          this.setLoad()
          console.log(this.editFormData.paymentBackList);
          this.editFormData.paymentBackList[0].opportunityName = this.sjId
          savePayBackInfo(this.editFormData.paymentBackList)
            .then((responseData) => {
              if (responseData.code == 100) {
                this.dialogProps.visible = false
                this.showMessage({
                  type: 'success',
                  msg: responseData.data
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
        } else {
          if (isEmpty) {
            this.$message({
              type: 'warning',
              message: '不是数字或精度有误！'
            })
          } else if (!isValid) {
            this.$message({
              type: 'warning',
              message: '请填写速算扣除数/月纳所得额/免税所得额/！'
            })
          }
        }
      } else {
        this.$message({
          type: 'warning',
          message: '请填写税率表详细信息！'
        })
      }
    },
    switchEdit() {
      this.action = 'edit'
      this.dialogProps.title = `修改${this.dialogTitle}`
      this.initOptions(this.editFormData)
    },

    getFormById(id) {
      this.setLoad()
      return new Promise((resolve) => {
        getCrmOpportunityPayById(id)
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

      // this.listSelectOptions1()
      // this.listSelectOptions3()
      // this.listSelectOptions4()
      // this.listSelectOptions5()
      /*if(this.bsoyls !== null){
        //this.listSelectOptions2()
        this.listSelectOptions2()
      }*/

    },
    addWork() {
      this.workForm.dataList.push({
        workUnit: this.workForm.dataList.length + 1,
        position: `第${this.workForm.dataList.length + 1}期`,
        startTime: "",
        endTime: "",
        resignReason: "",
        referencePhone: "",
        referenceName: "",
      });
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
        notes: '' // 备注
      }
    }
  },
  watch: {
    'editFormData.customName': {
      handler: function (val, oldVal) {
        this.$nextTick(() => {
          if (this.editFormData.customName !== null) {
            if (this.action !== 'edit') {
              if (this.action !== 'openAddDialog2') {
                this.editFormData.bsoy = ''
              }
              this.listSelectOptions2()
              this.action = 'openAddDialog'
            } else {
              this.action = 'openAddDialog1'
            }
          } else {
            this.editFormData.bsoy = ''

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
        this.sjId = id;
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
        this.sjId = id;
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
        this.dialogProps.title = `编辑${this.dialogTitle}`
        this.editFormData = this.initEditData()
        // this.initOptions(this.editFormData)
        this.dialogProps.visible = true
      })
      this.$on('openCopyDialog', async function (id) {
        this.action = 'add'
        this.dialogProps.title = `编辑${this.dialogTitle}`
        this.editFormData = {
          ...this.initEditData(),
          ...(await this.getFormById(id))
        }
        this.initOptions(this.editFormData)
        this.editFormData.id = null //把id设置为空，添加一个新的

        this.dialogProps.visible = true
      })
    })
  },

}
</script>
