<template>
  <el-row class="dc-container">
      <el-row>
        <el-form
          ref="editForm"
          :model="editFormData"
          label-width="140px"
          :disabled="action == 'view'"
          class="dc-el-form_ElEditForm"
        >
          <el-row>
            <el-col :span="12">
              <el-form-item
                label="申请人："
                class="dc-el-form-item_SingleInput"
              >{{ editFormData.createBy ? editFormData.createBy : currentUser.name }}</el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                label="申请日期："
                style="width: 100%"
              >{{ editFormData.createDate ? editFormData.createDate : currentDate }}</el-form-item>
            </el-col>
            <el-divider content-position="left" class="dc-el-divider_ElDivider">主要信息</el-divider>
            <el-col :span="8">
              <el-form-item
                prop="contractId"
                label="合同编号："
                :rules="[{ required: true, message: '合同编号不能为空', trigger: 'blur' }]"
                class="dc-el-form-item_SingleInput"
              >
                <el-select
                  v-model="editFormData.contractId"
                  filterable
                  remote
                  clearable
                  reserve-keyword
                  placeholder="请输入合同编号"
                  :style="{ width: '100%' }"
                  :remote-method="handleGetContract"
                  @change="handleChangeCont"
                  class="dc-el-input_SingleInput"
                  :loading="cLoading">
                  <el-option
                    v-for="item in contractData"
                    :key="item.id"
                    :label="item.contractName"
                    :value="item.id">
                    <span style="float: left">{{ item.contractCode }}</span>
                    <span style="float: right; color: #8492a6; font-size: 13px">{{ item.contractName }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item
                label="合同名称："
                class="dc-el-form-item_SelectInput"
              >
              <el-input
                  v-model="editFormData.contractName"
                  clearable
                  disabled
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item
                label="合同归属部门："
                class="dc-el-form-item_SelectInput"
              >
              <el-input
                  v-model="editFormData.deptName"
                  :maxLength="30"
                  placeholder="请输入"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item
                label="签约方公司："
                class="dc-el-form-item_SelectInput"
              >
              <el-input
                  v-model="editFormData.signComp"
                  :maxLength="30"
                  placeholder="请输入"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="合同单位：" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.contractUnit"
                  :maxLength="30"
                  placeholder="请输入"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="票面客户单位：" class="dc-el-form-item_CascaderInput">
                <el-input
                  v-model="editFormData.parCustUnit"
                  :maxLength="30"
                  placeholder="请输入"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="项目名称：" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editFormData.itemName"
                  :maxLength="30"
                  placeholder="请输入"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="项目编号：" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model.number="editFormData.itemCode"
                  :maxLength="30"
                  placeholder="请输入"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-divider content-position="left" class="dc-el-divider_ElDivider">开票信息</el-divider>
            <el-col :span="12">
              <el-form-item label="发票类型：" class="dc-el-form-item_SelectInput">
                <el-select
                  v-model="editFormData.invoiceType"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  clearable
                  filterable
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in invoiceType"
                    :key="item.value"
                    :label="item.name"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="税号：" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editFormData.taxNum"
                  placeholder="请输入"
                  :maxLength="30"
                  class="dc-el-input_MutilpleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="地址、电话：" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editFormData.invoiceAddressTel"
                  placeholder="地址和电话电话之间请使用空格分隔，切勿使用分号"
                  :maxLength="100"
                  class="dc-el-input_MutilpleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="开户银行名称及账户" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editFormData.invoiceBankAccount"
                  placeholder="银行名称和账号之间请使用空格分隔，切勿使用分号"
                  :maxLength="100"
                  class="dc-el-input_MutilpleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="发票金额：" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model.number="editFormData.invoiceAmount"
                  placeholder="发票金额"
                  :maxLength="20"
                  @blur="handleInputAment"
                  class="dc-el-input_MutilpleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="税率：" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model.number="editFormData.taxRate"
                  placeholder="税率"
                  @blur="handleInputRate"
                  :maxLength="3"
                  class="dc-el-input_MutilpleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="税金：" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editFormData.taxes"
                  placeholder="税金"
                  :maxLength="20"
                  disabled
                  class="dc-el-input_MutilpleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="不含税价：" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editFormData.excludTax"
                  placeholder="不含税价"
                  disabled
                  class="dc-el-input_MutilpleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="18">
              <el-form-item label="票面打印内容：" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editFormData.invoicePrint"
                  type="textarea"
                  placeholder=""
                  rows="2"
                  :maxLength="200"
                  class="dc-el-input_MutilpleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="18">
              <el-form-item label="发票票面备注：" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editFormData.invoiceRemark"
                  type="textarea"
                  placeholder=""
                  rows="2"
                  :maxLength="200"
                  class="dc-el-input_MutilpleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="18">
              <el-form-item label="申请人说明：" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editFormData.applicantDesc"
                  type="textarea"
                  placeholder=""
                  rows="2"
                  :maxLength="200"
                  class="dc-el-input_MutilpleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-divider content-position="left" class="dc-el-divider_ElDivider">发票邮寄信息</el-divider>
            <el-col :span="12">
              <el-form-item label="公司名称：" class="dc-el-form-item_MutilpleInput">
                <el-input v-model="editFormData.companyName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="地址：" class="dc-el-form-item_MutilpleInput">
                <el-input v-model="editFormData.address"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="收件人：" class="dc-el-form-item_MutilpleInput">
                <el-input v-model="editFormData.recipient"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="联系电话：" class="dc-el-form-item_MutilpleInput">
                <el-input v-model.number="editFormData.phone" @blur="handleBlurPhone" :maxLength="11"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="邮编：" class="dc-el-form-item_MutilpleInput">
                <el-input v-model.number="editFormData.postalCode" @blur="handleBlurCode" :maxLength="6"></el-input>
              </el-form-item>
            </el-col>
            <el-divider content-position="left" class="dc-el-divider_ElDivider">发票收迄回执</el-divider>
            <el-col :span="18">
              <el-form-item label="发票内容：" class="dc-el-form-item_MutilpleInput">
                <el-input v-model="editFormData.invoiceContent" type="textarea" rows="2"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="18">
              <el-form-item label="发票种类：" class="dc-el-form-item_MutilpleInput">
                <el-select
                  v-model="editFormData.invoiceKind"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  clearable
                  filterable
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in invoiceKind"
                    :key="item.value"
                    :label="item.name"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-row>
  </el-row>
</template>
<script>
import BizForm from '@/views/wf/common/bizForm'
import { validatenull } from '@/utils/validate'
/** 根据用户界面配置import组件 结束 */

/** 根据用户界面配置import组件 结束 */
import {
  getCrmInvoiceById,
  getCrmInvoiceContract,
  createCrmInvoice,
  reapplyCrmInvoice,
  getCrmInvoiceByTaskId,
  agreeCrmInvoice,
  forwardCrmInvoice,
  commissionCrmInvoice,
  proposeCrmInvoice,
  backCrmInvoice,
  rejectCrmInvoice,
  terminateCrmInvoice,
  reverseCrmInvoice,
  listBackActivity,
  saveDraftCrmInvoice
} from '@/api/crmInvoice/crmInvoice'
import OperationIcon from '@/components/OperationIcon'
import { getCurrentUser } from '@/api/admin/common/personalInfo'
import { getDictTypeById } from '@/api/sys/dictType'
import dayjs from 'dayjs'
export default {
  extends: BizForm,
  name: 'crmInvoice-form',
  components: {
    /** 根据用户界面配置组件 开始 */

    /** 根据用户界面配置组件 结束 */

    OperationIcon
  },
  data() {
    return {
      /** 根据用户界面配置生成data数据 开始 */
      editFormData: this.initEditData(),
      currentDate: dayjs().format('YYYY.MM.DD'),
      dialogProps: {
        visible: false,
        title: '发票申请'
      },
      invoiceKind: [],
      cLoading: false,
      currentUser: {},
      invoiceType: [],

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
  mounted () {
    this.$nextTick(() => {
      this.dialogProps.visible = true
      this.handleGetContract()
      this.initOptions()
      this.handleType()
      this.tabIndex = '1'
      if (this.wfAction == 'create') {
        this.$refs['editForm'].resetFields()
        // 从草稿创建
        if (this.bizData) {
          this.editFormData = {
            ...this.initEditData(),
            ...this.bizData,
            ...this.bizData.contract,
            contractId: this.bizData.contract.contractCode,
            id: ''
          }
        }
      } else if (this.wfAction == 'approve') {
        // 重新申请
        let reApply =
          this.task.assignee == this.task.owner &&
          this.task.assignee == this.task.extInfo.applicantId &&
          this.task.taskDefinitionKey == this.task.extInfo.appActId
        if (!reApply) {
          this.action = 'view'
        }
        this.loadModelData(this.task.id)
      } else if (this.wfAction == 'viewBiz') {
        this.action = 'view'
        this.editFormData = {
          ...this.initEditData(),
          ...this.bizData,
          ...this.bizData.contract,
          contractId: this.bizData.contract.contractCode,
          id: ''
        }
      } else if (this.wfAction == 'viewTask') {
        this.action = 'view'
        this.loadModelData(this.task.id)
      } else {
        console.err('=============== 业务审批单中，实现的：' + this.wfAction)
      }
    })
  },
  methods: {
    handleBlurPhone () {
      const { phone } = this.editFormData
      if (phone && !Number(phone)) {
        this.showMessage({type: 'warning', msg: '请输入数字'})
      }
    },
    handleBlurCode () {
      const { postalCode } = this.editFormData
      if (postalCode && !Number(postalCode)) {
        this.showMessage({type: 'warning', msg: '请输入数字'})
      }
    },
    handleInputAment (v) {
      const { taxRate, invoiceAmount } = this.editFormData
      if (!invoiceAmount) return
      if (!Number(invoiceAmount)) {
        this.showMessage({type: 'warning', msg: '请输入数字'})
      } else {
        this.editFormData = {
          ...this.editFormData,
          invoiceAmount: Number(invoiceAmount).toFixed(2)
        }
        if (this.editFormData.taxRate) {
          const taxes = (+Number(invoiceAmount).toFixed(2)) * ((+taxRate) / 100)
          this.editFormData = {
            ...this.editFormData,
            taxes,
            excludTax: (+Number(invoiceAmount).toFixed(2)) - taxes
          }
        }
      }
    },
    handleInputRate () {
      const { taxRate, invoiceAmount } = this.editFormData
      if (!taxRate) return
      if (!Number(taxRate)) {
        this.showMessage({type: 'warning', msg: '请输入数字'})
      } else {
        if (invoiceAmount) {
          const taxes = (+invoiceAmount) * ((+Number(taxRate).toFixed(2)) / 100)
          this.editFormData = {
            ...this.editFormData,
            taxes,
            excludTax: (+invoiceAmount) - taxes
          }
        }
      }
    },
    handleChangeCont (v) {
      const current = this.contractData.findIndex(item => item.id === v)
      this.editFormData = {
        ...this.editFormData,
        ...this.contractData[current],
        contract: this.contractData[current],
        contractId: this.contractData[current].contractCode,
        invoiceAmount: this.contractData[current].contractAmount,
        id: ''
      }
      
    },
    handleGetContract (v) {
      this.cLoading = true
      getCrmInvoiceContract({
        contractCode: v
      }).then(res => {
        this.contractData = res
        this.cLoading = false
      }).catch(() => {
        this.cLoading = false
      })
    },
    handleType () {
      // 发票类型
      getDictTypeById('1743166610852663463').then(res => {
        if (res.code === '100') {
          this.invoiceType = res.data.dictItemList
        }
      })
      // 发票种类
      getDictTypeById('1766000701405178798').then(res => {
        if (res.code === '100') {
          this.invoiceKind = res.data.dictItemList
        }
      })
    },
    handleGetUser () {
      getCurrentUser().then(res => {
        if (res.code === '100') {
          this.currentUser = res.data
        }
      })
    },
    loadModelData(taskId) {
      this.setLoad()
      getCrmInvoiceByTaskId(taskId)
        .then((responseData) => {
          if (responseData.code == '100') {
            this.editFormData = {
              ...this.initEditData(),
              ...responseData.data,
              ...responseData.data.contract,
            contractId: responseData.data.contract.id
            }
            this.$emit('after-load-data', this.editFormData) // 表单数据对象传给父节点
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    // 保存草稿，files附件列表, draftModel 草稿对象
    doSaveDraft(draftModel, files) {
      if (!draftModel) {
        // 不是从草稿打开创建，保存时更新原草稿
        draftModel = {
          procdefId: this.procDef.id,
          procdefName: this.procDef.name,
          name: this.procDef.name + '-' + this.editFormData.contractName
        }
      }
      this.setLoad()
      let formData = this.createWfData(this.editFormData, files)
      formData.append('strDraft', JSON.stringify(draftModel))
      saveDraftCrmInvoice(formData)
        .then((responseData) => {
          this.handleResponse(responseData)
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    // 创建一个新流程 process流程 actNodes是提交的用户任务节点及其审批用户
    doCreate(files, actNodes) {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          this.setLoad()
          this.editFormData.actNodes = actNodes // 选择节点及审批用户
          let formData = this.createWfData(this.editFormData, files)
          createCrmInvoice(this.procDef.id, formData)
            .then((responseData) => {
              this.handleResponse(responseData)
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        }
      })
    },
    // 重新发起申请 comment 意见, files文件附件列表，actNodes提交节点和用户信息
    doReApply(comment, files, actNodes) {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          this.setLoad()
          this.editFormData.actNodes = actNodes // 选择节点及审批用户
          this.editFormData.taskComement = comment // 意见

          let formData = this.createWfData(this.editFormData, files)
          reapplyCrmInvoice(this.task.id, formData)
            .then((responseData) => {
              this.handleResponse(responseData)
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        }
      })
    },
    // 同意 comment 意见, files文件附件列表，actNodes提交节点和用户信息
    doAgree(comment, files, actNodes) {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          this.setLoad()
          this.editFormData.actNodes = actNodes // 选择节点及审批用户
          this.editFormData.taskComement = comment // 意见

          let formData = this.createWfData(this.editFormData, files)
          agreeCrmInvoice(this.task.id, formData)
            .then((responseData) => {
              this.handleResponse(responseData)
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        }
      })
    },
    // 转派任务
    doForward(comment, files, actNodes) {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          this.setLoad()
          // this.editFormData.actNodes = actNodes // 选择节点及审批用户
          // this.editFormData.taskComement = actNodes // 意见
          this.editFormData = {
            ...this.editFormData,
            actNodes,
            taskComement: actNodes
          }

          let formData = this.createWfData(this.editFormData, files)
          forwardCrmInvoice(this.task.id, formData)
            .then((responseData) => {
              this.handleResponse(responseData)
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        }
      })
    },
    // 委派任务
    doCommission(comment, files, actNodes) {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          this.setLoad()
          // this.editFormData.actNodes = actNodes // 选择节点及审批用户
          // this.editFormData.taskComement = comment // 意见
          this.editFormData = {
            ...this.editFormData,
            actNodes,
            taskComement: comment
          }

          let formData = this.createWfData(this.editFormData, files)
          commissionCrmInvoice(this.task.id, formData)
            .then((responseData) => {
              this.handleResponse(responseData)
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        }
      })
    },
    // 拟办（办理委派过来的任务）
    doPropose(comment, files) {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          this.setLoad()
          // this.editFormData.taskComement = comment // 意见
          this.editFormData = {
            ...this.editFormData,
            taskComement: comment
          }

          let formData = this.createWfData(this.editFormData, files)
          proposeCrmInvoice(this.task.id, formData)
            .then((responseData) => {
              this.handleResponse(responseData)
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        }
      })
    },
    // 拒绝
    doReject(comment, files) {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          this.setLoad()
          // this.editFormData.taskComement = comment // 意见
          this.editForm = {
            ...this.editForm,
            taskComement: comment
          }

          let formData = this.createWfData(this.editFormData, files)
          rejectCrmInvoice(this.task.id, formData)
            .then((responseData) => {
              this.handleResponse(responseData)
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        }
      })
    },
    // 退回 comment 意见, actNodes退回节点和用户信息
    doBack(comment, files, actNodes) {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          this.setLoad()
          // this.editFormData.actNodes = actNodes // 选择节点及审批用户
          // this.editFormData.taskComement = comment // 意见
          this.editForm = {
            ...this.editForm,
            actNodes,
            taskComement: comment
          }

          let formData = this.createWfData(this.editFormData, files)
          backCrmInvoice(this.task.id, formData)
            .then((responseData) => {
              this.handleResponse(responseData)
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        }
      })
    },
    // 终止流程 comment 意见, actNodes退回节点和用户信息
    doTerminate(comment, files) {
      this.setLoad()
      // this.editFormData.taskComement = comment // 意见
      this.editForm = {
        ...this.editForm,
        taskComement: comment
      }

      let formData = this.createWfData(this.editFormData, files)
      terminateCrmInvoice(this.task.id, formData)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.dialogProps.visible = false
            this.$emit('save-finished')
          }
          this.showMessage(responseData)
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    // 撤回任务
    doReverse(task) {
      this.setLoad()
      reverseCrmInvoice(task.id)
        .then((responseData) => {
          this.handleResponse(responseData)
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    doListActivity() {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          this.setLoad()
          listBackActivity(this.task.id)
            .then((responseData) => {
              this.handleResponse(responseData)
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        }
      })
    },

    initOptions(This) {
      // 初始化自定义类型选择框选项变量
      this.handleGetUser()
    },
    initEditData(This) {
      return {}
    }
  },
  watch: {}
}
</script>
