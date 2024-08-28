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
        <el-tabs v-model="tabName" type="border-card" tab-position="top" class="dc-el-tabs_ElTabs">
          <el-tab-pane label="线索池管理" name="editForm" class="dc-el-tab-pane_ElTabPane">
            <el-form
              ref="editForm"
              :model="editFormData"
              label-width="100px"
              :disabled="action == 'view'"
              class="dc-el-form_ElEditForm"
            >
              <el-row>
                <el-col :span="8">
                  <el-form-item
                    prop="cplName"
                    label="线索池名称"
                    :rules="[{ required: true, message: '线索池名称不能为空', trigger: 'blur' }]"
                    class="dc-el-form-item_SingleInput"
                  >
                    <el-input v-model="editFormData.cplName" placeholder="请输入线索池名称"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item
                    prop="cplResponsible"
                    label="线索池负责人"
                    label-width="130px"
                    :rules="[{ required: true, message: '线索池负责人不能为空', trigger: 'blur' }]"
                    class="dc-el-form-item_SingleInput"
                  >
                    <el-select
                      v-model="editFormData.cplResponsible"
                      :style="{ width: '100%' }"
                      placeholder="请选择线索池负责人"
                      clearable
                      filterable
                      class="dc-el-select_SelectInput"
                    >
                      <el-option
                        v-for="(item, index) in selectOptions11"
                        :key="index"
                        :label="item.customerName.split('|')[0]"
                        :value="item.id"
                      >
                        <span>{{ item.customerName.split('|')[0]}}</span>
                        <span> | {{ item.customerName.split('|')[1]}}</span>
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="24">
                  <el-form-item
                    prop="cplResponsible"
                    label="线索池成员"
                    :rules="[{ required: true, message: '线索池成员', trigger: 'blur' }]"
                    class="dc-el-form-item_SingleInput"
                  >
                    <el-tag
                      :key="tag"
                      v-for="tag in dynamicTags"
                      closable
                      :disable-transitions="false"
                      @close="handleClose(tag)">
                      {{tag.split("|")[1]}}
                    </el-tag>
                  </el-form-item>
                </el-col>
                <el-divider content-position="left">人员列表</el-divider>
                <el-col :span="24">
                  <!--<el-row>-->
                   <!-- <el-col :span="8">
                      <el-form-item
                        prop="jobNumber"
                        label="工号:"
                        class="dc-el-form-item_SingleInput"
                      >
                        <el-input v-model="userofjober" placeholder="请输入内容"></el-input>
                      </el-form-item>
                    </el-col>-->
                    <el-col :span="8">
                      <el-form-item
                        prop="name"
                        label="人员姓名:"
                        class="dc-el-form-item_SingleInput"
                      >
                        <el-input v-model="userofname" placeholder="请输入内容"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="4">
                      <el-button type="primary" v-on:click="handseleuserls">查询</el-button>
                      <el-button type="info" v-on:click="handleClear">重置</el-button>
                    </el-col>


                 <!-- </el-row>-->
                </el-col>
                <el-col :span="24">
                  <el-table
                    ref="multipleTable"
                    :data="tableData"
                    tooltip-effect="dark"
                    style="width: 100%"
                    @selection-change="handleSelectionChange">
                   <!-- <el-table-column
                      prop="id"
                      label="工号"
                      width="120"
                      >
                    </el-table-column>-->
                    <el-table-column
                      prop="name"
                      label="姓名"
                      width="120">
                    </el-table-column>
                    <el-table-column
                      prop="sex"
                      label="性别"
                      width="120">
                    </el-table-column>
                    <el-table-column
                      prop="department"
                      label="部门"
                      width="120">
                    </el-table-column>
                    <el-table-column
                      prop="position"
                      label="职位"
                      width="120">
                    </el-table-column>
                    <el-table-column
                      prop="phone"
                      label="联系电话"
                      width="120">
                    </el-table-column>
                    <el-table-column label="操作">
                      <template slot-scope="scope">
                        <el-button
                          size="mini"
                          type="success"
                          @click="handleEdit(scope.$index, scope.row)">添加</el-button>
                      </template>
                    </el-table-column>
                  </el-table>
                  <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page.sync="pageNum"
                    :page-sizes="[5, 10, 20, 50]"
                    :page-size="5"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total" style="float: right;margin-top: 10px">
                  </el-pagination>
                </el-col>
                <el-divider content-position="left">手工规则设置：</el-divider>
                <el-col :span="24">

                  <el-form-item prop="cplMemberClaimRules" label="成员领取规则" class="dc-el-form-item_SingleInput">
                    <!--<el-input
                      v-model="editFormData.cplMemberClaimRules"
                      :maxLength="2"
                      placeholder="请输入成员领取规则"
                      clearable
                      autocomplete="new-password"
                      class="dc-el-input_SingleInput"
                    ></el-input>-->
                    <template>
                      <el-radio-group v-model="editFormData.cplMemberClaimRules">
                        <el-radio label="1">成员可见可领取，管理员可分配</el-radio>
                        <el-radio label="2">成员不可见，仅管理员分配</el-radio>
                      </el-radio-group>
                    </template>
                  </el-form-item>
                </el-col>
                <el-col :span="24">
                  <el-form-item prop="cplMemberClaimLimit" label="分配领取上限" class="dc-el-form-item_NumberInput">
                    每个成员最多领取/被分配
                    <number-input
                      v-model="editFormData.cplMemberClaimLimit"
                      :min="1"
                      :max="100"
                      :precision="0"
                      placeholder="请输入分配领取上限"
                      clearable
                      class="dc-number-input_NumberInput"
                      style="width: 160px"
                    ></number-input>
                    条线索
                  </el-form-item>
                </el-col>
                <el-divider content-position="left">自动规则设置：</el-divider>
                <el-col :span="24">
                  <el-card>
                    <div class="titleitem">
                      <span >满足条件：</span>
                    </div>
                    <el-table
                      :data="AnnualAssessmentData"
                      stripe
                      style="width: 100%">
                      <el-table-column
                        prop="year"
                        label="状态"
                        width="260">
                        <template slot-scope="scope">
                          <el-select v-model="scope.row.gzzt" placeholder="请选择状态">
                            <el-option
                              v-for="item in selectOptions2"
                              :key="item.value"
                              :label="item.name"
                              :value="item.value">
                            </el-option>
                          </el-select>
                        </template>
                      </el-table-column>
                      <el-table-column
                        prop="result"
                        label="范围"
                        width="260">
                        <template slot-scope="scope">
                          <el-select v-model="scope.row.gzfw" placeholder="请选择范围">
                            <el-option
                              v-for="item in selectOptions3"
                              :key="item.value"
                              :label="item.name"
                              :value="item.value">
                            </el-option>
                          </el-select>
                        </template>

                      </el-table-column>
                      <el-table-column
                        prop="remarks"
                        label="值"
                        width="380">
                        <template slot-scope="scope">
                          <number-input
                            v-model="scope.row.gzts"
                            :min="0"
                            :max="999999999999"
                            :precision="0"
                            placeholder="请输入自动回收天数"
                            clearable
                            class="dc-number-input_NumberInput"
                            style="width: 200px"
                          ></number-input>
                        </template>
                      </el-table-column>
                      <el-table-column
                        prop="operate"
                        label="操作">
                        <template slot-scope="scope">
                          <el-button
                            size="mini"
                            type="success"
                            icon="el-icon-save"
                            @click="handlesaveAnnualAssessment(scope.$index, scope.row)">保存
                          </el-button>
                          <el-button
                            size="mini"
                            type="danger"
                            icon="el-icon-delete"
                            @click="handleDeleteAnnualAssessment(scope.$index, scope.row)">删除
                          </el-button>
                        </template>
                      </el-table-column>
                    </el-table>
                    <div>
                      <el-button
                        type="primary"
                        icon="el-icon-plus"
                        size="mini"
                        @click="handleAddAnnualAssessmentLine">新增自动分配规则
                      </el-button>
                    </div>
                  </el-card>
                </el-col>
                <el-col :span="24">
                  <el-form-item
                    prop="cplRecycleDay"
                    label="自动回收天"
                    :rules="[{ required: true, message: '自动回收天不能为空', trigger: 'blur' }]"
                    class="dc-el-form-item_NumberInput"
                  >
                    自动回收超过
                    <number-input
                      v-model="editFormData.cplRecycleDay"
                      :min="0"
                      :max="999999999999"
                      :precision="0"
                      placeholder="请输入自动回收天"
                      clearable
                      class="dc-number-input_NumberInput"
                      style="width: 60px"
                    ></number-input>
                    天
                    <el-select v-model="editFormData.cplRecycleStatus" placeholder="请选择">
                      <el-option
                        v-for="(item, index) in selectOptions2"
                        :key="index"
                        :label="item.name"
                        :value="item.value">
                      </el-option>
                    </el-select>
                    的线索
                  </el-form-item>
                </el-col>
               <!-- <el-col :span="12">
                  <el-form-item
                    prop="cplRecycleStatus"
                    label="自动回收状态"
                    :rules="[{ required: true, message: '自动回收状态不能为空', trigger: 'blur' }]"
                    class="dc-el-form-item_SingleInput"
                  >
                    <el-input
                      v-model="editFormData.cplRecycleStatus"
                      :maxLength="60"
                      placeholder="请输入自动回收状态"
                      clearable
                      autocomplete="new-password"
                      class="dc-el-input_SingleInput"
                    ></el-input>

                  </el-form-item>
                </el-col>
                <el-col :span="24">
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
                </el-col>-->
              </el-row>
            </el-form>
          </el-tab-pane>
          <!--<el-tab-pane label="线索池管理-成员" name="crmCluePoolMemberTable" class="dc-el-tab-pane_ElTabPane">
            <el-row v-if="action != 'view'" gutter="0" type="flex" justify="end" align="top" class="dc-el-row_ElRow">
              <el-tooltip disabled class="dc-el-tooltip_Button">
                <el-button
                  type="primary"
                  icon="el-icon-plus"
                  v-on:click="onAddCrmCluePoolMemberList"
                  class="dc-el-button_Button"
                ></el-button>
              </el-tooltip>
            </el-row>
            <el-form
              ref="crmCluePoolMemberForm"
              :model="editFormData"
              label-width="100px"
              :disabled="action == 'view'"
              class="dc-el-form_ElEditForm"
            >
              <el-row>
                <ux-grid
                  :data="editFormData.crmCluePoolMemberList"
                  ref="crmCluePoolMemberTable"
                  :currentRow="table132CurrentRow"
                  height="200px"
                  border
                  :edit-config="{ trigger: 'click', mode: 'cell' }"
                  class="dc-ux-grid_EditTable"
                >
                  <ux-table-column
                    field="memberId"
                    title="联系人Id"
                    tree-node
                    resizable
                    min-width="120px"
                    align="left"
                    header-align="center"
                    show-overflow
                    :edit-render="{ autofocus: '.el-input__inner' }"
                    class="dc-ux-table-column_SingleInput"
                  >
                    <template v-slot:header="{ column }">
                      <span style="color: #f56c6c">*</span>
                      {{ column.title }}
                    </template>
                    <template v-slot:edit="{ row, rowIndex }">
                      <el-form-item
                        :prop="'crmCluePoolMemberList.' + rowIndex + '.memberId'"
                        label-width="0px"
                        :rules="[{ required: true, message: '联系人Id不能为空', trigger: 'blur' }]"
                        class="dc-el-form-item_SingleInput"
                      >
                        <el-input
                          v-model="row.memberId"
                          :maxLength="64"
                          placeholder="请输入联系人Id"
                          clearable
                          autocomplete="new-password"
                          class="dc-el-input_SingleInput"
                        ></el-input>
                      </el-form-item>
                    </template>
                    <template v-slot="{ row, rowIndex }">
                      <span class="my-input-sc">
                        <el-form-item
                          :prop="'crmCluePoolMemberList.' + rowIndex + '.memberId'"
                          label-width="0px"
                          :rules="[{ required: true, message: '联系人Id不能为空', trigger: 'blur' }]"
                          class="dc-el-form-item_SingleInput"
                        >
                          {{ row.memberId }}
                        </el-form-item>
                      </span>
                    </template>
                  </ux-table-column>
                  <ux-table-column
                    field="remarks"
                    title="备注信息"
                    tree-node
                    resizable
                    min-width="120px"
                    align="left"
                    header-align="center"
                    show-overflow
                    :edit-render="{ autofocus: '.el-input__inner' }"
                    class="dc-ux-table-column_MutilpleInput"
                  >
                    <template v-slot:header="{ column }">
                      {{ column.title }}
                    </template>
                    <template v-slot:edit="{ row, rowIndex }">
                      <el-form-item
                        :prop="'crmCluePoolMemberList.' + rowIndex + '.remarks'"
                        label-width="0px"
                        class="dc-el-form-item_MutilpleInput"
                      >
                        <el-input
                          v-model="row.remarks"
                          type="textarea"
                          placeholder="请输入备注信息"
                          rows="2"
                          :maxLength="255"
                          class="dc-el-input_MutilpleInput"
                        ></el-input>
                      </el-form-item>
                    </template>
                    <template v-slot="{ row, rowIndex }">
                      <span class="my-input-sc">
                        <el-form-item
                          :prop="'crmCluePoolMemberList.' + rowIndex + '.remarks'"
                          label-width="0px"
                          class="dc-el-form-item_MutilpleInput"
                        >
                          {{ row.remarks }}
                        </el-form-item>
                      </span>
                    </template>
                  </ux-table-column>
                  <ux-table-column
                    title="操作"
                    tree-node
                    resizable
                    width="160px"
                    min-width="80px"
                    fixed="right"
                    align="left"
                    header-align="center"
                    v-if="action != 'view'"
                    class="dc-ux-table-column_ElTableOptColumn"
                  >
                    <template v-slot:header="scope">
                      <span>操作</span>
                    </template>
                    <template slot-scope="scope">
                      <OperationIcon
                        v-on:click="onDeleteChild(editFormData.crmCluePoolMemberList, scope.rowIndex)"
                        type="danger"
                        content="删除"
                        placement="top"
                        icon-name="el-icon-delete"
                        class="dc-OperationIcon_IconButton"
                      ></OperationIcon>
                    </template>
                  </ux-table-column>
                </ux-grid>
              </el-row>
            </el-form>
          </el-tab-pane>-->
        </el-tabs>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button v-on:click="onSubmit" type="primary" v-show="action != 'view'">保存</el-button>
        <el-button v-on:click="onDialogClose" v-if="action != 'view'">取消</el-button>
        <el-button v-on:click="onDialogClose" v-if="action == 'view'">关闭</el-button>
      </span>
    </el-dialog>
  </el-row>
</template>
<style>
  .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
</style>
<script>
import { validatenull } from '@/utils/validate'
/** 根据用户界面配置import组件 结束 */

/** 根据用户界面配置import组件 结束 */
import { getCrmCluePoolById, saveCrmCluePool,seleStaff,saveccpl } from '@/api/crmCluePool/crmCluePool'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'
import { getCrmCustomerManagementByIds,getCrmddfzrs} from '@/api/customerManagement/crmCustomerManagement'
import { getSysDictItemBytype } from '@/api/dictitem/sysDictItem'
export default {
  extends: BaseUI,
  name: 'crmCluePool-form',
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
        title: '线索池管理'
      },
      dialogTitle: '线索池管理',
      // 选项变量
      table132CurrentRow: {}, // 当前行
      tabName: 'editForm', // tab标签页
      // 窗口操作类型 view/edit/add
      action: '',
      radio: 3,
      userofjober:'',
      userofname:'',
      pageNum:1,
      pageSize:5,

      tableData: [],
      multipleSelection: [],
      options:[],
      value:'',
      value1:'',
      month1:'',
      month2:'',
      AnnualAssessmentData: [],
      selectOptions11:[],
      selectOptions2:[],
      selectOptions3:[],
      dynamicTags: [],
      dynamicTagss:[],
      inputVisible: false,
      inputValue: '',
      formData: {
      }
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
    handleEdit(index, row) {

      /*let itemcys = {};
      itemcys.id = row.id;
      itemcys.name = row.name;
      if (!this.dynamicTagss.some(t => t.id === itemcys.id)) {
        this.dynamicTagss.push(itemcys);
      }else{
        this.$message({
          message: '该成员已添加',
          type: 'warning'
        });
      }*/
      console.log(this.dynamicTags)
      console.log(this.dynamicTags.indexOf((row.id+'|'+row.name)))
      if ((this.dynamicTags.indexOf((row.id+'|'+row.name)))>=0) {
        this.$message({
          message: '该成员已添加',
          type: 'warning'
        });
      } else {
        this.dynamicTags.push(row.id+'|'+row.name)
      }
        // this.dynamicTags.push(row.id+'|'+row.name)
    },

    handleClose(tag) {
     /* this.dynamicTagss.splice(this.dynamicTagss.findIndex(item => item.id === 'Mike'), 1);
      console.log(this.dynamicTagss);*/
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
      console.log( this.dynamicTags)
    },

    showInput() {
      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
        this.dynamicTags.push(inputValue);
      }
      this.inputVisible = false;
      this.inputValue = '';
    },

    handleSizeChange(val){
      this.pageSize = val;
      this.handleInquirepage();
    },
    handleCurrentChange(val){
      this.pageNum = val;
      this.handleInquirepage();
    },
    handleClear() {
      this.userofjober = '';
      this.userofname = '';
      this.handseleuserls()
    },
    handleInquirepage() {
      let usids = {
        pageNum:this.pageNum,
        pageSize:this.pageSize,
        userofjober:this.userofjober,
        userofname: this.userofname
      };
      seleStaff(usids).then(response => {
        this.tableData = response.data;
        this.total = response.total;
      });},
    handseleuserls() {
      this.pageNum=1;
      this.pageSize = 5;
      let usids = {
        pageNum:this.pageNum,
        pageSize:this.pageSize,
        userofjober:this.userofjober,
        userofname: this.userofname
      };
      seleStaff(usids).then(response => {
        this.tableData = response.data;
        this.total = response.total;
      });
    },

    listSelectOptions3() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])
      let type = 'COMPARE'
      getSysDictItemBytype(type).then((responseData) => {
        if (responseData.code == 100) {
          this.selectOptions3 = responseData.data;
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
      let type = 'CLUE_STATUS'
      getSysDictItemBytype(type).then((responseData) => {
        if (responseData.code == 100) {
          this.selectOptions2 = responseData.data;
        } else {
          this.showMessage(responseData)
        }
      })
    },
    listSelectOptions11() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])

      getCrmddfzrs(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.selectOptions11 = responseData.data;
        } else {
          this.showMessage(responseData)
        }
      })
    },

    onSubmit() {

      if(this.dialogProps.title == '修改线索池管理'){
        let validFlag = true
        let ccpls = {
          cplName: this.editFormData.cplName,
          cplResponsible: this.editFormData.cplResponsible,
          ccpms:this.dynamicTags.join(','),
          cplMemberClaimRules:this.editFormData.cplMemberClaimRules,
          cplMemberClaimLimit:this.editFormData.cplMemberClaimLimit,
          AnnualAssessmentData:JSON.stringify(this.AnnualAssessmentData),
          cplRecycleDay:this.editFormData.cplRecycleDay,
          cplRecycleStatus:this.editFormData.cplRecycleStatus
        };

        var params = new URLSearchParams();
        params.append('cplName',this.editFormData.cplName);
        params.append('cplResponsible', this.editFormData.cplResponsible);
        params.append('ccpms', this.dynamicTags.join(','));
        params.append('cplMemberClaimRules', this.editFormData.cplMemberClaimRules);
        params.append('cplMemberClaimLimit', this.editFormData.cplMemberClaimLimit);
        params.append('AnnualAssessmentData', JSON.stringify(this.AnnualAssessmentData));
        params.append('cplRecycleDay', this.editFormData.cplRecycleDay);
        params.append('cplRecycleStatus',this.editFormData.cplRecycleStatus);
        params.append('id',this.editFormData.id);

        saveccpl(params).then((responseData) => {
          if (responseData.code == 100) {
            this.dialogProps.visible = false
            this.showMessage({
              type: 'success',
              msg: '保存成功'
            })
            this.$emit('save-finished')
            this.initipput()
          }else {
            this.showMessage({
              type: 'success',
              msg: '保存异常'
            })
          }
          this.resetLoad()
        });
      }else{
        let validFlag = true
        let ccpls = {
          cplName: this.editFormData.cplName,
          cplResponsible: this.editFormData.cplResponsible,
          ccpms:this.dynamicTags.join(','),
          cplMemberClaimRules:this.editFormData.cplMemberClaimRules,
          cplMemberClaimLimit:this.editFormData.cplMemberClaimLimit,
          AnnualAssessmentData:JSON.stringify(this.AnnualAssessmentData),
          cplRecycleDay:this.editFormData.cplRecycleDay,
          cplRecycleStatus:this.editFormData.cplRecycleStatus
        };

        var params = new URLSearchParams();
        params.append('cplName',this.editFormData.cplName);
        params.append('cplResponsible', this.editFormData.cplResponsible);
        params.append('ccpms', this.dynamicTags.join(','));
        params.append('cplMemberClaimRules', this.editFormData.cplMemberClaimRules);
        params.append('cplMemberClaimLimit', this.editFormData.cplMemberClaimLimit);
        params.append('AnnualAssessmentData', JSON.stringify(this.AnnualAssessmentData));
        params.append('cplRecycleDay', this.editFormData.cplRecycleDay);
        params.append('cplRecycleStatus',this.editFormData.cplRecycleStatus);
        params.append('id','null');

        saveccpl(params).then((responseData) => {
          if (responseData.code == 100) {
            this.dialogProps.visible = false
            this.showMessage({
              type: 'success',
              msg: '保存成功'
            })
            this.$emit('save-finished')
            this.initipput()
          }else {
            this.showMessage({
              type: 'success',
              msg: '保存异常'
            })
          }
          this.resetLoad()
        });
      }
/*
      this.$refs['crmCluePoolMemberForm'].validate((valid) => {
        if (valid) {
        } else {
          this.tabName = 'crmCluePoolMemberTable'
          validFlag = false
          return false
        }
      })

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
      }*/
    },
    doSave() {
      this.setLoad()

      saveCrmCluePool(this.editFormData)
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
        getCrmCluePoolById(id)
          .then((responseData) => {
            let form = {}
            if (responseData.code == 100) {
              form = responseData.data
              this.formData = responseData.data

              this.editFormData.cplName = this.formData.cplName;
              this.editFormData.cplResponsible = this.formData.cplResponsible;
              this.dynamicTags = this.formData.members.split(',')
              //this.dynamicTags.push(row.id)
              this.editFormData.cplMemberClaimRules = Number(this.formData.cplMemberClaimRules);
              this.editFormData.cplMemberClaimLimit = this.formData.cplMemberClaimLimit;
              this.AnnualAssessmentData = JSON.parse(this.formData.cplAutoWhere1);
              this.editFormData.cplRecycleDay = this.formData.cplRecycleDay;
              this.editFormData.cplRecycleStatus = this.formData.cplRecycleStatus;

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

    initipput(){
      this.editFormData.cplName=''
      this.editFormData.cplResponsible=''
      this.dynamicTags = []
      this.editFormData.cplMemberClaimRules=1
      this.editFormData.cplMemberClaimLimit = ''
      this.AnnualAssessmentData = []
      this.editFormData.cplRecycleDay=0
      this.editFormData.cplRecycleStatus = ''
    },

    initOptions(This) {
      if(this.action != 'edit'){
        this.AnnualAssessmentData = [],
        this.dynamicTags = []

      }
      // 初始化自定义类型选择框选项变量
      this.listSelectOptions11()
      this.listSelectOptions2()
      this.listSelectOptions3()
      this.handseleuserls()

      //this.initipput()
    },
    onDeleteChild(tableData, index) {
      // 根据下标删除子表数据
      tableData.splice(index, 1)
    },
    initEditData(This) {
      return {
        cplName: '', // 线索池名称
        cplResponsible: '', // 线索池负责人
        cplRuleType: '', // 分配规则 手动、自动
        cplMemberClaimRules: '1', // 成员领取规则
        cplMemberClaimLimit: '', // 分配领取上限
        cplAutoWhere1: '', // 自动分配规则1
        cplAutoWhere2: '', // 自动分配规则2
        cplAutoNum: '', // 自动分配数量
        cplRecycleDay: '', // 自动回收天
        cplRecycleStatus: '', // 自动回收状态
        remarks: '', // 备注信息
        crmCluePoolMemberList: []
      }
    },

    onAddCrmCluePoolMemberList() {
      this.editFormData.crmCluePoolMemberList.push({
        memberId: '', // 联系人Id

        remarks: '' // 备注信息
      })
    },
    //增加年度考核行
    handleAddAnnualAssessmentLine() {
      if (this.AnnualAssessmentData == undefined) {
        this.AnnualAssessmentData = new Array();
      }
      let obj = {};

      this.AnnualAssessmentData.push(obj);
    },
    //保存年度考核行
    handlesaveAnnualAssessment(a, b) {
    },
    //删除年度考核行
    handleDeleteAnnualAssessment(index) {
      this.AnnualAssessmentData.splice(index, 1)
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
        this.userofjober = '';
        this.userofname = '';
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

        for (var i = 0; i <= this.editFormData.crmCluePoolMemberList.length - 1; i++) {
          this.editFormData.crmCluePoolMemberList[i].id = null
        }

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
