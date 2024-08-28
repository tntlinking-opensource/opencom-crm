<template>
  <el-row class="dc-container" v-loading="loading">
    <el-form ref="editForm" :model="editFormData" label-width="100px" :disabled="action == 'view'"
             class="dc-el-form_ElEditForm">
      <el-row>
        <el-col :span="8">
          <el-form-item prop="customerId" label="客户名称"  :rules="[{ required: true, message: '客户名称不能为空', trigger: 'blur' }]"
                        class="dc-el-form-item_SingleInput">
            <el-select clearable v-model="editFormData.customerId"  :disabled="true"  placeholder="请选择客户名称" style="width: 100%;">
              <el-option v-for="item in customerOptions" :key="index"
                         :label="item.cusName"
                         :value="item.id"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="opportunityId" label="商机"  :rules="[{ required: true, message: '商机不能为空', trigger: 'blur' }]"
                        class="dc-el-form-item_SingleInput">
            <el-select clearable v-show="false" v-model="editFormData.opportunityId" placeholder="请选择商机" style="width: 100%;">
              <el-option v-for="item in 2" :key="item" :value="item" :label="item" />
            </el-select>
            <el-input  :disabled="true" v-model="editFormData.opportunityName"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="id" label="客户名称" v-if="false"
                        class="dc-el-form-item_SingleInput">
            <el-input v-model="editFormData.id" :maxLength="255" :disable="true"  placeholder="请输入合同编号" clearable
                      autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
          </el-form-item>
        </el-col>
        <el-divider>合同信息</el-divider>
        <el-col :span="8">
          <el-form-item prop="contractCode" label="合同编号"
                        :rules="[{ required: true, message: '合同编号不能为空', trigger: 'blur' }]" class="dc-el-form-item_SingleInput">
            <el-input v-model="editFormData.contractCode" :maxLength="255" placeholder="请输入合同编号" clearable
                      autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="contractName" label="合同名称"  class="dc-el-form-item_SingleInput"
                        :rules="[{ required: true, message: '合同名称不能为空', trigger: 'blur' }]">
            <el-input v-model="editFormData.contractName" :maxLength="255" placeholder="请输入合同名称" clearable
                      autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="contractAmount" label="合同金额" class="dc-el-form-item_SingleInput"
                        :rules="[{ required: true, message: '合同金额不能为空', trigger: 'blur' }]">
            <el-input v-model="editFormData.contractAmount"  :disabled="true"  :maxLength="255" placeholder="请输入合同金额" clearable
                      autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="signDate" label="签约日期" class="dc-el-form-item_SingleInput"
                        :rules="[{ required: true, message: '签约日期不能为空', trigger: 'blur' }]">
            <el-date-picker v-model="editFormData.signDate" type="date" value-format='yyyy-MM-dd' clearable
                            style="width: 100%;" placeholder="选择签约日期"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="beginDate" label="开始日期" class="dc-el-form-item_SingleInput"
                        :rules="[{ required: true, message: '开始日期不能为空', trigger: 'blur' }]">
            <el-date-picker v-model="editFormData.beginDate" type="date" value-format='yyyy-MM-dd' clearable
                            style="width: 100%;" placeholder="选择开始日期"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="endDate" label="结束日期" class="dc-el-form-item_SingleInput"
                        :rules="[{ required: true, message: '结束日期不能为空', trigger: 'blur' }]">
            <el-date-picker v-model="editFormData.endDate" type="date" value-format='yyyy-MM-dd' clearable
                            style="width: 100%;" placeholder="选择结束日期"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="partyA" label="甲方" class="dc-el-form-item_SingleInput"
                        :rules="[{ required: true, message: '甲方不能为空', trigger: 'blur' }]">
            <el-input v-model="editFormData.partyA" :maxLength="50" placeholder="请输入甲方" clearable
                      autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="partyB" label="乙方" class="dc-el-form-item_SingleInput"
                        :rules="[{ required: true, message: '乙方不能为空', trigger: 'blur' }]">
            <el-input v-model="editFormData.partyB" :maxLength="50" placeholder="请输入乙方" clearable
                      autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="payType" label="支付方式" class="dc-el-form-item_SingleInput"
                        :rules="[{ required: true, message: '支付方式不能为空', trigger: 'blur' }]">
            <el-select clearable v-model="editFormData.payType" placeholder="请选择支付方式" style="width: 100%;">
              <el-option v-for="item in payWayList" :key="item.value" :label="item.name" :value="item.value" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item prop="remark" label="备注" class="dc-el-form-item_SingleInput">
            <el-input v-model="editFormData.remark" :maxLength="200" placeholder="请输入备注" clearable type="textarea"
                      autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
          </el-form-item>
        </el-col>
      </el-row>


    </el-form>

    <el-divider>还款信息</el-divider>
    <div style="margin-bottom:10px">
      <el-button icon="el-icon-plus" @click="addWork">新增</el-button>
    </div>
    <el-form ref="editFormData" :model="editFormData" class="dc-el-form_ElEditForm">
      <ux-grid :data="editFormData.payList" ref="tsmWorkExperienceTable" height="200px" border
               :edit-config="{ trigger: 'click', mode: 'cell' }" class="dc-ux-grid_QueryTable">
        <ux-table-column title="序号" type="index" tree-node resizable min-width="80px" fixed="center" align="center"
                         header-align="center" class="dc-ux-table-column_ElTableNumColumn"></ux-table-column>
        <ux-table-column field="order" title="期数" tree-node resizable min-width="100px"
                         header-align="center" align="center" show-overflow
                         class="dc-ux-table-column_SingleInput">
          <template v-slot:header="{ column }">
            <span style="color: #f56c6c">*</span>
            {{ column.title }}
          </template>
          <template v-slot:edit="{ row, rowIndex }">
            <el-form-item label-width="0px" :prop="'payList.' + rowIndex + '.order'" :rules="[
              {
                required: true,
                message: '期数不能为空',
                trigger: 'blur'
              }
            ]" class="dc-el-form-item_SingleInput">
              <el-input v-model="row.order" :maxLength="50" placeholder="请输入期数" clearable autocomplete="new-password"
                        class="dc-el-input_SingleInput"></el-input>
            </el-form-item>
          </template>
          <template v-slot="{ row }">
            {{ row.order }}
          </template>
        </ux-table-column>
        <ux-table-column field="payDate" title="还款日期" tree-node resizable min-width="120px" align="center"
                         header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
                         class="dc-ux-table-column_SingleInput">
          <template v-slot:header="{ column }">
            <span style="color: #f56c6c">*</span>
            {{ column.title }}
          </template>
          <template v-slot:edit="{ row, rowIndex }">
            <el-form-item label-width="0px" :prop="'payList.' + rowIndex + '.payDate'" :rules="[
              {
                required: true,
                message: '还款日期不能为空',
                trigger: 'blur'
              }
            ]" class="dc-el-form-item_SingleInput">
              <el-date-picker v-model="row.payDate" :style="{ width: '100%' }" placeholder="请选择" type="date"
                              format="yyyy-MM-dd" value-format="yyyy-MM-dd" class="dc-el-date-picker_PickerTimeDate"></el-date-picker>
            </el-form-item>
          </template>
          <template v-slot="{ row }">
            {{ row.payDate }}
          </template>
        </ux-table-column>

        <ux-table-column field="payRate" title="还款比例" tree-node resizable min-width="120px" align="right"
                         header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
                         class="dc-ux-table-column_SingleInput">
          <template v-slot:header="{ column }" >
            <span style="color: #f56c6c">*</span>
            {{ column.title }}
          </template>
          <template v-slot:edit="{ row, rowIndex }">
            <el-form-item label-width="0px" :prop="'payList.' + rowIndex + '.payRate'" class="dc-el-form-item_SingleInput"
                          :rules="[
                {
                  required: true,
                  message: '还款比例不能为空',
                  trigger: 'blur'
                }
              ]">
              <el-input v-model="row.payRate" :maxLength="100" placeholder="请输入还款比例" clearable autocomplete="new-password"
                        class="dc-el-input_SingleInput"></el-input>
            </el-form-item>
          </template>
          <template v-slot="{ row }">
            {{ row.payRate }}
          </template>
        </ux-table-column>
        <ux-table-column field="payAmount" title="还款金额" tree-node resizable min-width="120px" align="right"
                         header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
                         class="dc-ux-table-column_SingleInput">
          <template v-slot:header="{ column }">
            <span style="color: #f56c6c">*</span>
            {{ column.title }}
          </template>
          <template v-slot:edit="{ row, rowIndex }">
            <el-form-item label-width="0px" :prop="'payList.' + rowIndex + '.payAmount'" :rules="[
              {
                required: true,
                message: '还款金额不能为空',
                trigger: 'blur'
              }
            ]" class="dc-el-form-item_SingleInput">
<!--              <el-input v-model="row.payAmount" :maxLength="50" placeholder="请输入还款金额" clearable
                        autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>-->
              <el-input v-model="row.payAmount" :maxlength="isNumberInput ? 10 : 100"
                        :show-word-limit="true"
                        @input="handleInput(row.payAmount,rowIndex)" placeholder="请输入还款金额" clearable
                        @blur="blurHandle($event, row)"
                        autocomplete="new-password" class="dc-el-input_SingleInput">
                {{isNumberInput?toFormatMoney(row.payAmount,rowIndex)||'0.00':''}}
              </el-input>
              <el-tooltip content="支持输入字符，长度 100" placement="top">
                <div>支持输入字符，长度 100</div>
              </el-tooltip>
            </el-form-item>
          </template>
          <template v-slot="{ row }">
            {{ row.payAmount }}
          </template>
        </ux-table-column>
        <ux-table-column field="remark" title="备注" tree-node resizable min-width="120px" align="center"
                         header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
                         class="dc-ux-table-column_SingleInput">
          <template v-slot:header="{ column }">
            <span style="color: #f56c6c">*</span>
            {{ column.title }}
          </template>
          <template v-slot:edit="{ row, rowIndex }">
            <el-form-item label-width="0px" :prop="'payList.' + rowIndex + '.remark'" :rules="[
              {
                required: true,
                message: '备注不能为空',
                trigger: 'blur'
              }
            ]" class="dc-el-form-item_SingleInput">
              <el-input v-model="row.remark" :maxLength="20" placeholder="请输入备注" clearable autocomplete="new-password"
                        class="dc-el-input_SingleInput"></el-input>
            </el-form-item>
          </template>
          <template v-slot="{ row }">
            {{ row.remark }}
          </template>
        </ux-table-column>

        <ux-table-column title="操作" tree-node resizable width="80px" min-width="80px" fixed="right" align="center"
                         header-align="center" v-if="action != 'view'" class="dc-ux-table-column_ElTableOptColumn">
          <template v-slot:header="scope">
            <span>操作</span>
          </template>
          <template slot-scope="scope">
            <OperationIcon v-on:click="onDeleteChild(editFormData.payList, scope)" type="danger" content="删除"
                           placement="top" icon-name="el-icon-delete" class="dc-OperationIcon_IconButton">
            </OperationIcon>
          </template>
        </ux-table-column> </ux-grid></el-form>
  </el-row>
</template>
<script>
import BizForm from '@/views/wf/common/bizForm'
import { validatenull } from '@/utils/validate'
/** 根据用户界面配置import组件 结束 */

/** 根据用户界面配置import组件 结束 */
import {
  getCrmContractById,
  createCrmContract,
  reapplyCrmContract,
  getCrmContractByTaskId,
  agreeCrmContract,
  forwardCrmContract,
  commissionCrmContract,
  proposeCrmContract,
  backCrmContract,
  rejectCrmContract,
  terminateCrmContract,
  reverseCrmContract,
  listBackActivity,
  saveDraftCrmContract
} from '@/api/crmBusiness/crmContract'
import OperationIcon from '@/components/OperationIcon'
import { getDictTypeById } from '@/api/sys/dictType'
import {getCrmOpportunityById} from "../../../api/crmBusiness/crmOpportunity";
import {treeCrmCustomer} from "../../../api/crmCustomer/crmCustomer";
export default {
  extends: BizForm,
  name: 'contractFlow',
  components: {
    /** 根据用户界面配置组件 开始 */

    /** 根据用户界面配置组件 结束 */

    OperationIcon
  },
  data() {
    return {
      /** 根据用户界面配置生成data数据 开始 */
      editFormData: this.initEditData(),
      dialogProps: {
        visible: false,
        title: ''
      },
      payWayList: [],
      // 选项变量

      /** 根据用户界面配置生成data数据 结束 */

      // 窗口操作类型 view/edit/add
      action: '',
      formData: { payList: [] },
      // 客户名称选项
      customerOptions: [],

      isNumberInput: true, // 是否是数字输入模式

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
    /** 校验内容 */
    validateAssetValue(value) {
      let val = value.replace(/,/g, "")
      let valueNum = Number(val)
      return value && isNaN(valueNum) ? 0 : val
    },
    /** 失去焦点 */
    blurHandle(event, row) {
      const value = event.target.value
      row.payAmountValue = this.validateAssetValue(value)
    },
    /** 在组件中使用千分符 */
    toFormatMoney(value,rowIndex) {
      // 从输入中删除非数字字符
      let formattedValue = value.replace(/[^\d]/g, '');
      this.$set(this.editFormData.payList[rowIndex],'payAmount',formattedValue.replace(/\B(?=(\d{3})+(?!\d))/g, ","))
    },
    // 还款金额
    handleInput(val,rowIndex) {
      if (!(/[^0-9.,]/g.test(val))) {
        this.isNumberInput = true;
      } else {
        this.isNumberInput = false
      }
      if (this.isNumberInput) {
        // 数字输入模式
        // 移除非数字字符（保留小数点和数字）
        val = val.replace(/[^0-9.,]/g, '');

        // 限制小数点只能出现一次
        const decimalCount = (val.match(/\./g) || []).length;
        if (decimalCount > 1) {
          val = val.replace(/\.$/, '');
        }

        // 限制小数点后只能有两位数字
        const parts = val.split('.');
        if (parts[1] && parts[1].length > 2) {
          parts[1] = parts[1].substring(0, 2);
          val = parts.join('.');
        }

        // 限制总长度为 10
        if (val.length > 10) {
          val = val.substring(0, 10);
        }
      } else {
        // 字符输入模式
        // 限制总长度为 100
        if (val.length > 100) {
          val = val.substring(0, 100);
        }
      }
      this.$set(this.editFormData.payList[rowIndex],'payAmount',val)
    },
    loadModelData(taskId) {
      this.setLoad()
      getCrmContractByTaskId(taskId)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.editFormData = {
              ...this.initEditData(),
              ...responseData.data
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
    doSaveDraft: function (draftModel, files) {
      let bool = this.payListValid()
      if (!bool){
        return
      }
      if (!draftModel) {
        // 不是从草稿打开创建，保存时更新原草稿
        draftModel = {
          procdefId: this.procDef.id,
          procdefName: this.procDef.name,
          name: this.procDef.name + '-' + this.editFormData.name
        }
      }
      this.setLoad()
      const opportId = sessionStorage.getItem("opportunityId");
      if (opportId !== undefined&&opportId!==null) {
        this.editFormData.opportunityId = opportId;
        this.editFormData.opporStatus = 1;
      }
      let formData = this.createWfData(this.editFormData, files)
      formData.append('strDraft', JSON.stringify(draftModel))


      saveDraftCrmContract(formData)
        .then((responseData) => {
          this.handleResponse(responseData)
          this.resetLoad()
          this.$emit("save-finished");
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    // 公共方法
    payListValid(){
      // 判断还款比例 还款金额是否等于合同金额 还款日期是否递增
      let payList = this.editFormData.payList // 列表
      let payRate = 0;
      let payAmount = 0;
      payList = payList.sort(function (a,b){
        return a.order - b.order;
      })
      for (let i = 0; i < payList.length; i++) {
        payRate += parseInt(payList[i].payRate) // 还款比例
        if (this.isNumberInput) {
          payAmount += parseInt(payList[i].payAmountValue) // 还款总额是否等于合同金额
          if (payList.length > i+1) {
            if (payList[i].payDate > payList[i+1].payDate) {
              this.$message.error("还款明细中填写的还款日期有误");
              return false;
            }
          }
        }
      }
      if (payRate > 100) {
        this.$message.error("还款明细中填写的比例有误");
        return false;
      }else if (this.isNumberInput && payAmount > parseInt(this.editFormData.contractAmount)) {
        this.$message.error("还款明细中填写的还款金额有误");
        return false;
      } else{
        return  true
      }
    },
    // 创建一个新流程 process流程 actNodes是提交的用户任务节点及其审批用户
    doCreate(files, actNodes) {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          let bool = this.payListValid()
          if (!bool){
            return
          }
          this.setLoad()
          this.editFormData.actNodes = actNodes // 选择节点及审批用户

          let formData = this.createWfData(this.editFormData, files)
          this.editFormData.oppStage = "3"
          createCrmContract(this.procDef.id, formData)
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
          let bool = this.payListValid()
          if (!bool){
            return
          }
          this.setLoad()
          this.editFormData.actNodes = actNodes // 选择节点及审批用户
          this.editFormData.taskComement = comment // 意见

          let formData = this.createWfData(this.editFormData, files)
          reapplyCrmContract(this.task.id, formData)
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
          agreeCrmContract(this.task.id, formData)
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
          this.editFormData.actNodes = actNodes // 选择节点及审批用户
          this.editFormData.taskComement = comment // 意见

          let formData = this.createWfData(this.editFormData, files)
          forwardCrmContract(this.task.id, formData)
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
          this.editFormData.actNodes = actNodes // 选择节点及审批用户
          this.editFormData.taskComement = comment // 意见

          let formData = this.createWfData(this.editFormData, files)
          commissionCrmContract(this.task.id, formData)
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
          this.editFormData.taskComement = comment // 意见

          let formData = this.createWfData(this.editFormData, files)
          proposeCrmContract(this.task.id, formData)
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
          this.editFormData.taskComement = comment // 意见

          let formData = this.createWfData(this.editFormData, files)
          rejectCrmContract(this.task.id, formData)
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
          this.editFormData.actNodes = actNodes // 选择节点及审批用户
          this.editFormData.taskComement = comment // 意见

          let formData = this.createWfData(this.editFormData, files)
          backCrmContract(this.task.id, formData)
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
    //删除某条数据
    onDeleteChild(tableData, scope) {

      this.$confirm("确定要删除该条数据吗？", "确认", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          tableData.splice(scope.rowIndex, 1);
        })
        .catch(() => { });
    },
    // 终止流程 comment 意见, actNodes退回节点和用户信息
    doTerminate(comment, files) {
      this.setLoad()
      this.editFormData.taskComement = comment // 意见

      let formData = this.createWfData(this.editFormData, files)
      terminateCrmContract(this.task.id, formData)
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
    addWork() {
      this.editFormData.payList.push({
        order: `第${this.editFormData.payList.length + 1}期`,
        payDate: "",
        payRate: "",
        remark: "",
        payAmount: "",
        id: '',
        contractId: ''
      });
    },
    //获取付款方式
    getPayWay() {
      getDictTypeById('1721718282909475574').then(res => {
        if (res.code === '100') {
          this.payWayList = res.data.dictItemList
        }
      })
    },
    // 撤回任务
    doReverse(task) {
      this.setLoad()
      reverseCrmContract(task.id)
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
    },
    initEditData(This) {
      return {
        id:'',
        customerId: '', // 客户id
        opportunityId: '', // 商机id
        contractCode: '', // 合同编码
        contractName: '', // 合同名称
        signDate: '', // 签约日期
        beginDate: '', // 开始日期
        endDate: '', // 结束日期
        partyA: '', // 甲方
        partyB: '', // 乙方
        payType: '', // 支付方式
        remark: '', // 备注
        attachment: '', // 附件
        deptId: '', // 部门id
        userId: '', // 创建用户id
        contractAmount: '',
        payList: []
      }
    },
    // 商机管理-合同用 begin
    listSelectOptions2(id) {
      getCrmOpportunityById(id).then((responseData) => {
        if (responseData.code == 100) {
          this.editFormData.oppName = responseData.data.oppName;
          this.editFormData.opportunityId = responseData.data.id;
        } else {
          this.showMessage(responseData)
        }
      })
    },
    // 客户option
    listCustomerOptions() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])
      // 数据权限: 客户管理crm_customer
      this.pushDataPermissions(search_List.params, this.$route.meta.routerId, "1709759135450792386")
      treeCrmCustomer(search_List).then(responseData => {
        if (responseData.code == 100) {
          this.customerOptions = responseData.data.rows || []
        } else {
          this.showMessage(responseData)
        }
      })
    },
  },
  watch: {},
  mounted: function () {
    this.$nextTick(() => {
      this.dialogProps.visible = true
      this.initOptions()
      this.getPayWay()
      this.tabIndex = '1'
      this.listCustomerOptions()
      if (this.wfAction == 'create') {
        this.$refs['editForm'].resetFields()
        // 从草稿创建
        if (this.bizData) {
          this.editFormData = {
            ...this.initEditData(),
            ...this.bizData
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
          ...this.bizData
        }
      } else if (this.wfAction == 'viewTask') {
        this.action = 'view'
        this.loadModelData(this.task.id)
      } else {
        console.log('=============== 业务审批单中，实现的：' + this.wfAction)
      }
      const oppoId = sessionStorage.getItem("opportunityId");
      const opportunityName = sessionStorage.getItem("opportunityName");
      const customer = sessionStorage.getItem("opportunityCustomer");
      const payment = sessionStorage.getItem("opportunityPayment");
      this.editFormData.opportunityId = oppoId
      this.editFormData.opportunityName = opportunityName
      this.editFormData.customerId = customer
      this.editFormData.contractAmount = payment;
    })
  }
}
</script>
