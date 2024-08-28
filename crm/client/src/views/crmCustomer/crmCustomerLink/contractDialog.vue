<template>
  <el-row class="dc-container">
    <el-dialog v-on:open="onDialogOpen"
               v-on:close="onDialogClose"
               v-loading="loading"
               width="90%"
               :fullscreen="false"
               :title="dialogProps.title"
               :visible.sync="dialogProps.visible"
               :close-on-click-modal="false"
               class="dc-el-dialog_ElDialog"
               append-to-body>
      <el-row>
        <el-form ref="editFormDialog" :model="editFormData" label-width="100px" :disabled="action == 'view'"
                 class="dc-el-form_ElEditForm">
          <el-row>
            <el-col :span="8">
              <el-form-item
                prop="customerId"
                label="客户"
                class="dc-el-form-item_CascaderInput"
              >
                <el-select
                  v-model="editFormData.customerId"
                  :style="{ width: '100%' }"
                  placeholder="请选择客户"
                  clearable
                  filterable
                  :disabled="true"
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in customerOptions"
                    :key="index"
                    :label="item.cusName"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item
                prop="oppName"
                label="商机名称"
                class="dc-el-form-item_SingleInput"
              >
                <el-input
                  v-model="editFormData.oppName"
                  :maxLength="300"
                  placeholder="请输入商机名称"
                  clearable
                  :disabled="true"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-divider>合同信息</el-divider>
            <el-col :span="8">
              <el-form-item prop="contractCode" label="合同编号" class="dc-el-form-item_SingleInput"
                            :rules="[{ required: true, message: '合同编号不能为空', trigger: 'blur' }]">
                <el-input v-model="editFormData.contractCode" :maxLength="255" placeholder="请输入合同编号" clearable
                          autocomplete="new-password" class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="contractName" label="合同名称" class="dc-el-form-item_SingleInput"
                            :rules="[{ required: true, message: '合同名称不能为空', trigger: 'blur' }]">
                <el-input v-model="editFormData.contractName" :maxLength="255" placeholder="请输入合同名称" clearable
                          autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="contractName" label="合同金额" class="dc-el-form-item_SingleInput">
                <el-input v-model="editFormData.contractAmount" :maxLength="255" placeholder="请输入合同金额" clearable
                          autocomplete="new-password" :disabled="true" :value="this.totalPrice" class="dc-el-input_SingleInput"></el-input>
                <!-- :disabled="true"  -->
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="signDate" label="签约日期" class="dc-el-form-item_SingleInput"
                            :rules="[{ required: true, message: '签约日期不能为空', trigger: 'blur' }]">
                <el-date-picker v-model="editFormData.signDate" type="date" value-format='yyyy-MM-dd' clearable
                                style="width: 100%;" placeholder="选择签约日期" @change="compareDates"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="beginDate" label="开始日期"
                            :rules="[{ required: true, message: '开始日期不能为空', trigger: 'blur' }]"
                            class="dc-el-form-item_SingleInput">
                <el-date-picker v-model="editFormData.beginDate" type="date" value-format='yyyy-MM-dd' clearable
                                style="width: 100%;" placeholder="选择开始日期" @change="compareDates"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="endDate" label="结束日期" class="dc-el-form-item_SingleInput"
                            :rules="[{ required: true, message: '结束日期不能为空', trigger: 'blur' }]">
                <el-date-picker v-model="editFormData.endDate" type="date" value-format='yyyy-MM-dd' clearable
                                style="width: 100%;" placeholder="选择结束日期" @change="compareDates"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="partyA" label="甲方" class="dc-el-form-item_SingleInput"
                            :rules="[{ required: true, message: '甲方不能为空', trigger: 'blur' }]">
                <el-input v-model="editFormData.partyA" :maxLength="50" placeholder="请输入甲方" clearable
                          autocomplete="new-password" class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="partyB" label="乙方" class="dc-el-form-item_SingleInput"
                            :rules="[{ required: true, message: '乙方不能为空', trigger: 'blur' }]">
                <el-input v-model="editFormData.partyB" :maxLength="50" placeholder="请输入乙方" clearable
                          autocomplete="new-password" class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="payType" label="支付方式" class="dc-el-form-item_SingleInput"
                            :rules="[{ required: true, message: '支付方式不能为空', trigger: 'blur' }]">
                <el-select clearable v-model="editFormData.payType" placeholder="请选择支付方式" style="width: 100%;"
                >
                  <el-option
                    v-for="item in payTypeOptions"
                    :key="item"
                    :value="item.id"
                    :label="item.name"/>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="remark" label="备注" class="dc-el-form-item_SingleInput">
                <el-input v-model="editFormData.remark" :maxLength="200" placeholder="请输入备注" clearable
                          type="textarea"
                          autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
<!--              <el-upload
                :file-list="fileList"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :before-upload="beforeUpload"
                :limit="10"
                :accept="'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/pdf,image/jpeg,image/jpg,image/png'"
                :on-exceed="handleExceed"
                :show-file-list="true"
                :auto-upload="false"
                :on-change="handleChange"
                multiple>
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div slot="tip" class="el-upload__tip">只能上传doc/docx/pdf/jpg/jpeg/png格式的文件,点击文件即可预览
                </div>
              </el-upload>
              &lt;!&ndash;               el-dialog 用于文件预览&ndash;&gt;
              <el-dialog :visible.sync="dialogVisible">
                <img v-if="previewImage" :src="previewImage" style="width: 100%;"/>
                <div v-else>
                  <iframe :src="previewUrl" style="width: 100%; height: 500px;" frameborder="0"></iframe>
                </div>
              </el-dialog>-->
              <upload-file
                title="文件上传"
                :action="action != 'view'"
                :objectId="editFormData.objectId"
                v-model="fileList"
                class="dc-upload-file_ElUploadFile"
              ></upload-file>
            </el-col>
          </el-row>


        </el-form>
      </el-row>
      <el-divider>还款信息</el-divider>
      <div style="margin-bottom:10px">
        <el-button icon="el-icon-plus" @click="addWork">新增</el-button>
        <span
          style="float: right;">总计还款金额：{{totalRepayment}}元</span>
      </div>
      <el-form ref="workForm" :model="workForm" class="dc-el-form_ElEditForm">
        <ux-grid :data="workForm.dataList" ref="tsmWorkExperienceTable" height="200px" border
                 :edit-config="{ trigger: 'click', mode: 'cell' }" class="dc-ux-grid_EditTable">
          <ux-table-column field="workUnit" title="序号" tree-node resizable min-width="50px" align="center"
                           header-align="center" show-overflow class="dc-ux-table-column_SingleInput">
          </ux-table-column>
          <ux-table-column field="id" title="id" v-if="false" tree-node resizable min-width="50px" align="center"
                           header-align="center" show-overflow class="dc-ux-table-column_SingleInput">
          </ux-table-column>
          <ux-table-column field="order" title="期数" tree-node resizable min-width="100px" header-align="center"
                           align="center" show-overflow class="dc-ux-table-column_SingleInput">
          </ux-table-column>
          <ux-table-column field="payDate" title="还款日期" tree-node resizable min-width="120px" align="center"
                           header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
                           class="dc-ux-table-column_SingleInput">
            <template v-slot:header="{ column }">
              <span style="color: #f56c6c">*</span>
              {{ column.title }}
            </template>
            <template v-slot:edit="{ row, rowIndex }">
              <el-form-item label-width="0px" :prop="'dataList.' + rowIndex + '.payDate'" :rules="[
                {
                  required: true,
                  message: '还款日期不能为空',
                  trigger: 'blur'
                }
              ]" class="dc-el-form-item_SingleInput">
                <el-date-picker v-model="row.payDate" :style="{ width: '100%' }" placeholder="请选择" type="date"
                                format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                                class="dc-el-date-picker_PickerTimeDate"></el-date-picker>
              </el-form-item>
            </template>
            <template v-slot="{ row }">
              {{ row.payDate }}
            </template>
          </ux-table-column>

          <ux-table-column field="payRate" title="还款比例" tree-node resizable min-width="120px" align="center"
                           header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
                           class="dc-ux-table-column_SingleInput">
            <template v-slot:header="{ column }">
              <span style="color: #f56c6c">*</span>
              {{ column.title }}
            </template>
            <template v-slot:edit="{ row, rowIndex }">
              <el-form-item label-width="0px" :prop="'dataList.' + rowIndex + '.payRate'"
                            class="dc-el-form-item_SingleInput" :rules="[
                  {
                    required: true,
                    message: '还款比例不能为空',
                    trigger: 'blur'
                  }
                ]">
                <number-input v-model="row.payRate" :min="0"
                              :max="100"
                              :precision="0"
                              placeholder="请输入数量"
                              clearable
                              class="dc-number-input_NumberInput"></number-input>
              </el-form-item>
            </template>
            <template v-slot="{ row }">
              {{ row.payRate }}
            </template>
          </ux-table-column>
          <ux-table-column field="payAmount" title="还款金额" tree-node resizable min-width="120px" align="center"
                           header-align="center" show-overflow :edit-render="{ autofocus: '.el-input__inner' }"
                           class="dc-ux-table-column_SingleInput">
            <template v-slot:header="{ column }">
              <span style="color: #f56c6c">*</span>
              {{ column.title }}
            </template>
            <template v-slot:edit="{ row, rowIndex }">
              <el-form-item label-width="0px" :prop="'dataList.' + rowIndex + '.payAmount'" :rules="[
                {
                  required: true,
                  message: '还款金额不能为空',
                  trigger: 'blur'
                }
              ]" class="dc-el-form-item_SingleInput">
                <el-input v-model="row.payAmount" :maxlength="isNumberInput ? 10 : 100"
                          :show-word-limit="true"
                          @input="handleInput" placeholder="请输入还款金额" clearable
                          autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
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
              <el-form-item label-width="0px" :prop="'dataList.' + rowIndex + '.remark'" :rules="[
                {
                  required: true,
                  message: '备注不能为空',
                  trigger: 'blur'
                }
              ]" class="dc-el-form-item_SingleInput">
                <el-input v-model="row.remark" :maxLength="20" placeholder="请输入备注" clearable
                          autocomplete="new-password" class="dc-el-input_SingleInput"></el-input>
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
              <OperationIcon v-on:click="onDeleteChild(workForm.dataList, scope)" type="danger" content="删除"
                             placement="top" icon-name="el-icon-delete" class="dc-OperationIcon_IconButton">
              </OperationIcon>
            </template>
          </ux-table-column>
        </ux-grid>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button v-on:click="onSubmit" type="primary" v-show="action != 'view'">保存</el-button>
        <el-button v-on:click="onDialogClose" v-if="action != 'view'">取消</el-button>
        <el-button v-on:click="onProDetails" v-if="action != 'view'">产品明细</el-button>
        <el-button v-on:click="onApprove" v-if="action != 'view'">提交审批</el-button>
      </span>
    </el-dialog>
    <el-dialog v-on:close="onTableDialogClose"
               v-loading="loading"
               width="90%"
               :fullscreen="false"
               title="产品明细"
               :visible.sync="dialogProps.tableVisible"
               :close-on-click-modal="false"
               class="dc-el-dialog_ElDialog">
      <span style="float: right;margin-bottom: 10px;">指导价合计：<span
        style="color: red">{{ pdGuidePriceTotal.toFixed(2) }}</span>元
        数量：<span style="color: red">{{ sigleNumTotal }} </span>  总价：<span
          style="color: red">{{ pdPriceSumTotal.toFixed(2) }}</span>元</span>
      <el-table :data="tableData2"
                style="width: 100%">
        <!-- 定义表格列 -->
        <el-table-column prop="ID" label="ID"></el-table-column>
        <el-table-column v-if=false prop="id" label="ID"></el-table-column>
        <el-table-column prop="pdNo" label="产品编号"></el-table-column>
        <el-table-column v-if=false display="none" prop="product" label="产品Id"></el-table-column>
        <el-table-column prop="pdName" label="产品名称"></el-table-column>
        <el-table-column prop="pdcName" label="产品分类"></el-table-column>
        <el-table-column prop="pdType" label="产品类型"></el-table-column>
        <el-table-column prop="pdGuidePrice" label="指导价">
          <template slot-scope="scope">
            <!-- 使用 formatter 属性自定义列的显示内容 -->
            <span>{{ scope.row.pdGuidePrice }}元</span>
          </template>
        </el-table-column>
        <el-table-column prop="sigleNum" label="数量">

        </el-table-column>
        <el-table-column prop="discount" label="折扣">
          <template slot-scope="scope">
            <!-- 使用 formatter 属性自定义列的显示内容 -->
            <span>{{ scope.row.discount }}%</span>
          </template>
        </el-table-column>
        <el-table-column prop="pdPriceSum" label="总价">
          <template slot-scope="scope">
            {{
              scope.row.pdPriceSum = scope.row.sigleNum && scope.row.discount ? scope.row.pdGuidePrice * scope.row.sigleNum
                * (1 - scope.row.discount / 100) : 0 | scope.row.pdPriceSum.toFixed(2)
            }}
            元
          </template>
        </el-table-column>
        <el-table-column prop="remarks" label="备注">
        </el-table-column>
      </el-table>
    </el-dialog>
  </el-row>
</template>
<script>
/** 根据用户界面配置import组件 结束 */
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'
import {treeCrmCustomer} from "../../../api/crmCustomer/crmCustomer";
import UploadFile from "../../components/uploadFile.vue";
import {listDictItemPage} from "../../../api/sys/dictItem";
import {getCrmContractById} from "../../../api/contract/crmContract";
import {saveCrmContract} from "../../../api/crmBusiness/crmOpportunityCot";
import {getCrmOpportunityById} from "../../../api/crmBusiness/crmOpportunity";
import {listCrmOpportunityProductPage} from "../../../api/crmBusiness/crmOpportunityProduct";
import {getContractPayDetails} from "../../../api/crmBusiness/crmOpportunityPay";
import WfForm from "../../wf/common/wfForm.vue";


export default {
  extends: WfForm,
  name: 'crmContactDialog',
  components: {
    UploadFile,
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
        tableVisible: false,
        title: '合同'
      },
      dialogTitle: '合同',
      // 选项变量
      // 客户名称选项
      customerOptions: [],
      // 付款方式
      payTypeOptions: [],

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

      fileList: [],
      dialogVisible: false, // 控制预览对话框的显示
      previewImage: '', // 预览图片的 URL
      previewUrl: '', // 预览其他文件类型的 URL
      // 产品明细
      tableData2: [],
      // 指导价合计
      pdGuidePriceTotal: 0,
      // 数量合计
      sigleNumTotal: 0,
      // 总价合计
      pdPriceSumTotal: 0,
      inputValue: '', // 存储输入的值
      isNumberInput: true, // 是否是数字输入模式

      // 合同总价
      totalPrice:0,
    }
  },
  props: {
    // 权限
    permission: {
      type: Object
    }
  },
  computed: {
    totalRepayment() {
      // 使用计算属性计算还款金额总和
      return this.workForm.dataList.reduce((total, row) => total + parseInt(row.payAmount), 0);
    },
  },
  methods: {
    // 还款金额
    handleInput(val) {
      if (/^\d+$/.test(val)) {
        this.isNumberInput = true;
      } else {
        this.isNumberInput = false
      }
      if (this.isNumberInput) {
        // 数字输入模式
        // 移除非数字字符（保留小数点和数字）
        this.inputValue = val.replace(/[^0-9.]/g, '');

        // 限制小数点只能出现一次
        const decimalCount = (this.inputValue.match(/\./g) || []).length;
        if (decimalCount > 1) {
          this.inputValue = this.inputValue.replace(/\.$/, '');
        }

        // 限制小数点后只能有两位数字
        const parts = this.inputValue.split('.');
        if (parts[1] && parts[1].length > 2) {
          parts[1] = parts[1].substring(0, 2);
          this.inputValue = parts.join('.');
        }

        // 限制总长度为 10
        if (this.inputValue.length > 10) {
          this.inputValue = this.inputValue.substring(0, 10);
        }
      } else {
        // 字符输入模式
        // 限制总长度为 100
        if (this.inputValue.length > 100) {
          this.inputValue = this.inputValue.substring(0, 100);
        }
      }
    },
    // 日期比较
    compareDates() {
      if (this.editFormData.beginDate && this.editFormData.endDate) {
        const startDate = new Date(this.editFormData.beginDate);
        const endDate = new Date(this.editFormData.endDate);

        if (startDate <= endDate) {
          return true
        } else {
          this.$message.error("请注意开始时间大于结束时间");
          this.editFormData.beginDate = ''
          this.editFormData.endDate = ''
          return false;
        }
      }
    },

    handleChange(file, fileList) {
      this.fileList = fileList;
    },
    // 文件预览
    handlePreview(file) {
      if (file.raw.type === 'image/jpeg' || file.raw.type === 'image/jpg' || file.raw.type === 'image/png') {
        // 如果是图片类型的文件，显示图片预览
        this.previewImage = URL.createObjectURL(file.raw);
      } else if (file.raw.type === 'application/pdf') {
        // 如果是 PDF 文件，使用 PDF.js 预览
        this.previewUrl = URL.createObjectURL(file.raw);
      } else if (file.raw.type === 'application/msword' || file.raw.type === 'application/vnd.openxmlformats-officedocument.wordprocessingml.document') {
        const docFileUrl = URL.createObjectURL(file.raw);
        window.open(docFileUrl, '_blank'); // 在新窗口中打开预览
      } else {
        // 其他文件类型的预览方式，可以根据需要自行实现
        // 这里只是一个示例，你可能需要使用第三方库或自定义组件来支持更多文件类型的预览
        this.previewUrl = URL.createObjectURL(file.raw);
      }
      this.dialogVisible = true; // 显示预览对话框
    },
    // 文件移除
    handleRemove(file, fileList) {
      // 处理文件移除逻辑，可以在这里发送请求删除服务器上的文件
      // this.fileList.splice();
    },
    // 文件上传前的处理
    beforeUpload(file) {
      // 根据文件类型进行验证，只允许上传指定类型的文件
      const isAllowedType =
        file.type === 'application/msword' ||
        file.type === 'application/vnd.openxmlformats-officedocument.wordprocessingml.document' ||
        file.type === 'application/pdf' ||
        file.type === 'image/jpeg' ||
        file.type === 'image/jpg' ||
        file.type === 'image/png';
      if (!isAllowedType) {
        this.$message.error('只能上传doc/docx/pdf/jpg/jpeg/png格式的文件');
        return false;
      }
      return true;
    },
    // 达到文件数量限制时的处理
    handleExceed(files, fileList) {
      if (fileList == 11) {
        this.$message.warning(`最多只能上传 10 个文件`);
        return false;
      }
    },
    // 付款方式
    payTypeOption() {
      let search_List = {
        params: []
      }
      search_List.params.push({'columnName': 'dictType.code', 'queryType': '=', 'value': "CRM_PAYMENT_TYPE"})
      listDictItemPage(search_List).then((res) => {
        if (res.code == 100) {
          this.payTypeOptions = res.data.rows;
        } else {
          this.showMessage(res)
        }
      })
    },
    onSubmit() {
      this.$refs['editFormDialog'].validate((valid) => {
        if (valid) {
          this.doSave()
        } else {
          return false
        }
      })
    },
    createWfData(bizFormModel,files) {
      let formData = new FormData
      let deletes= [];
      formData.append("crmContract", JSON.stringify(bizFormModel))
     /* for (let idx in files) {
        formData.append("files", files[idx].raw)
      }*/
      for (let idx in files.uploads) {
        formData.append('files', files.uploads[idx].raw)
      }
      deletes = deletes.concat(files.deletes)
      formData.append('deleteIds', JSON.stringify(deletes))

      return formData
    },
    doSave() {
      if (this.fileList.uploads.length == 0) {
        this.$message.warning("请选择文件");
        return false;
      }
      this.setLoad()
      this.editFormData.payList = this.workForm.dataList
      this.editFormData.contractAmount = parseInt(this.editFormData.contractAmount).toFixed(2)
      let formData = this.createWfData(this.editFormData, this.fileList)
      saveCrmContract(formData)
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
        getCrmContractById(id)
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
    onProDetails() {
      this.dialogProps.tableVisible = true
      this.sigleNumTotal= 0;
      this.pdGuidePriceTotal= 0
      this.pdPriceSumTotal = 0

      let search_List = {
        params: [],
      }
      search_List.params.push({'columnName': 'opp_id', 'queryType': '=', 'value': this.editFormData.opportunityId})

      listCrmOpportunityProductPage(search_List).then((responseData) => {
        if (responseData.code == 100) {
          let array = [];
          responseData.data.rows.map((item, index) => {
            array.push(
              Object.assign({}, item, {
                'ID': index + 1,
                'opp': item.opp.id,
                'product': item.product.id,
                'pdNo': item.product.pdNo,
                "pdName": item.product.pdName,
                "pdGuidePrice": item.product.pdGuidePrice
              }),
            )
            this.sigleNumTotal += item.sigleNum
            this.pdGuidePriceTotal += item.product.pdGuidePrice
            this.pdPriceSumTotal += item.product.pdGuidePrice * item.sigleNum * (1 - item.discount / 100)
          })

          this.tableData2 = array
        } else {
          this.showMessage(responseData)
        }
      })
    },
    onApprove() {

    },
    onDialogClose() {
      this.dialogProps.visible = false
    },
    onTableDialogClose() {
      this.dialogProps.tableVisible = false
    },
    onDialogOpen() {
      this.$nextTick(() => {
        this.$refs['editFormDialog'].clearValidate()
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
    // 合同table
    listTableHuiKuan(contractId) {
      this.workForm.dataList = []
      let search_List = {
        params: []
      }
      search_List.params.push({'columnName': 'contract_id', 'queryType': '=', 'value': contractId})
      getContractPayDetails(search_List).then((res) => {
        if (res.code == 100){
          res.data.rows.map((item, index) => {
            this.workForm.dataList.push(
              Object.assign({}, item, {
                'workUnit':index+1,
              }),
            )
          })
            // this.workForm.dataList = (res.data.rows);
        }else{
          this.$message.error(res)
        }
      })
    },
    initOptions(This) {
      // 初始化自定义类型选择框选项变量
      this.listCustomerOptions()

      this.payTypeOption()

      this.workForm.dataList = []

      // this.listSelectOptions3()
      // this.listSelectOptions4()
      // this.listSelectOptions5()


    },
    addWork() {
      this.workForm.dataList.push({
        workUnit: this.workForm.dataList.length + 1,
        order: `第${this.workForm.dataList.length + 1}期`,
        payDate: "",
        payRate: "",
        payAmount: "",
        remark: "",
      });
    },
    initEditData(This) {
      return {}
    },
    async getFilesByContractsId (conId) {
      let search_List = {
        params:[]
      }
      search_List.params.push("")
    },
  }
  ,
  watch: {}
  ,
  mounted: function () {
    this.$nextTick(() => {
      this.$on('openViewDialog', async function (id, conId,totalRepayment) {
        this.action = 'view'
        this.dialogProps.title = `查看${this.dialogTitle}`
        this.editFormData = {
          ...this.initEditData(),
          ...(await this.getFormById(conId))
        }
        this.totalPrice = totalRepayment;
        this.editFormData.contractAmount = totalRepayment
        this.editFormData.objectId = conId
        await this.listTableHuiKuan(conId)
        await this.listSelectOptions2(id);
        this.initOptions(this.editFormData)
        this.dialogProps.visible = true
      })

      this.$on('openEditDialog', async function (id, conId,totalRepayment) {
        this.action = 'edit'
        this.dialogProps.title = `修改${this.dialogTitle}` //conId  合同ID
        this.editFormData = {
          ...this.initEditData(),
          ...(await this.getFormById(conId))
        }
        this.totalPrice = totalRepayment;
        this.editFormData.contractAmount = totalRepayment
        this.editFormData.objectId = conId
        await this.listTableHuiKuan(conId)
        await this.listSelectOptions2(id);
        this.initOptions(this.editFormData)
        this.dialogProps.visible = true
      })
      this.$on('openAddDialog', function (id, cuId,totalRepayment) {
        this.action = 'add'
        this.dialogProps.title = `添加${this.dialogTitle}`
        this.editFormData = this.initEditData()
        this.listSelectOptions2(id);
        this.totalPrice = totalRepayment;
        this.editFormData.contractAmount = totalRepayment
        this.editFormData.customerId = cuId;
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
        this.listSelectOptions2(id);
        this.initOptions(this.editFormData)
        this.editFormData.id = null //把id设置为空，添加一个新的

        this.dialogProps.visible = true
      })
    })
  }
  ,

}
</script>
