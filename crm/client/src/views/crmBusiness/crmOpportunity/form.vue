<template>
  <el-row class="dc-container">
    <!--    <crmContactDialog
          v-on:save-finished="this.getShangJiPage"
          ref="editFormDialog"
        ></crmContactDialog>-->

    <!--    <crmContract-form
          v-show="this.clickVisible"
          v-on:save-finished="this.getShangJiPage"
          ref="editFormDialog"
        ></crmContract-form>-->

    <el-dialog
      v-on:open="onDialogOpen"
      v-on:close="onDialogClose"
      v-loading="loading"
      width="100%"
      :fullscreen="false"
      :title="dialogProps.title"
      :visible.sync="dialogProps.visible"
      :close-on-click-modal="false"
      class="dc-el-dialog_ElDialog"
      append-to-body
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
            <el-col :span="6">
              <el-form-item
                prop="customer.id"
                label="客户"
                :rules="[{ required: true, message: '客户不能为空', trigger: 'change' }]"
                class="dc-el-form-item_CascaderInput"
              >
                <el-select
                  v-model="editFormData.customer.id"
                  :style="{ width: '100%' }"
                  placeholder="请选择客户"
                  clearable
                  filterable
                  class="dc-el-select_SelectInput"
                  @change="searchContacts"
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
            <el-col :span="6">
              <el-form-item
                prop="oppName"
                label="商机名称"
                :rules="[{ required: true, message: '商机名称不能为空', trigger: 'blur' }]"
                class="dc-el-form-item_SingleInput"
              >
                <el-input
                  v-model="editFormData.oppName"
                  :maxLength="300"
                  placeholder="请输入商机名称"
                  clearable
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item
                prop="oppSource"
                label="商机来源"
                :rules="[{ required: true, message: '商机来源不能为空', trigger: 'change' }]"
                class="dc-el-form-item_SelectInput"
              >
                <el-select
                  v-model="editFormData.oppSource"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  clearable
                  filterable
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in selectOptions1"
                    :key="index"
                    :label="item.name"
                    :value="item.name"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <!--            <el-col :span="6">
                          <el-form-item
                            prop="oppStage"
                            label="销售阶段"
                            :rules="[{ required: true, message: '销售阶段', trigger: 'change' }]"
                            class="dc-el-form-item_SelectInput"
                          >
                            <el-select
                              v-model="editFormData.oppStage"
                              :style="{ width: '100%' }"
                              placeholder="请选择"
                              clearable
                              filterable
                              class="dc-el-select_SelectInput"
                            >
                              <el-option
                                v-for="(item, index) in oppStageOptions1"
                                :key="index"
                                :label="item.name"
                                :value="item.name"
                              ></el-option>
                            </el-select>
                          </el-form-item>
                        </el-col>-->
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item
                prop="oppContacts"
                label="联系人"
                :rules="[{ required: true, message: '联系人不能为空', trigger: 'blur' }]"
                class="dc-el-form-item_SingleInput"
              >
                <el-select
                  v-model="editFormData.oppContacts"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  clearable
                  filterable
                  class="dc-el-select_SelectInput"
                  @change="oppContactClick"
                >
                  <el-option
                    v-for="(item, index) in oppContactsOptions"
                    :key="index"
                    :label="item.contactsName+'-'+item.phoneNumber"
                    :value="item.id"
                  ></el-option>
                </el-select>
                <!--                <el-input
                                  v-model="editFormData.oppContacts"
                                  :maxLength="50"
                                  placeholder="请输入联系人"
                                  clearable
                                  autocomplete="new-password"
                                  class="dc-el-input_SingleInput"
                                ></el-input>-->
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item prop="oppDepartment" label="所属部门" class="dc-el-form-item_SelectInput">
                <!--                <el-select
                                  v-model="editFormData.oppDepartment"
                                  :style="{ width: '100%' }"
                                  placeholder="请选择"
                                  clearable
                                  filterable
                                  class="dc-el-select_SelectInput"
                                >
                                  <el-option v-for="(item, index) in departmentOptions"
                                             :key="index"
                                             :label="item.name"
                                             :value="item.code">

                                  </el-option>
                                </el-select>-->
                <el-input
                  v-model="editFormData.oppDepartment"
                  :maxLength="50"
                  placeholder="所属部门"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>

              </el-form-item>
            </el-col>
            <el-col :span="6">
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
            </el-col>
          </el-row>
        </el-form>
        <!-- 下半部分：列表数据 -->
        <el-tabs v-model="activeName" @tab-click="handleTabClick">
          <el-tab-pane label="商机明细" name="1">
            <div style="margin-bottom: 10px">
              <el-button :disabled="action == 'view'" type="primary" icon="el-icon-edit-outline"
                         class="dc-el-button_Button"
                         @click="addProduct">添加产品
              </el-button>
              <el-button :disabled="action == 'view'" type="danger" icon="el-icon-edit-outline"
                         class="dc-el-button_Button"
                         @click="removeProduct">删除
              </el-button>
              <span
                style="float: right;">已添加产品： {{
                  this.tableDataTotal
                }}  种       总金额：{{ this.priceSumFormat(totalRepayment) }}元</span>
            </div>
            <ux-grid :show-summary="true" :summary-method="getSummaries"
                     :data="tableData2" @row-click="selectionChange"
                     style="width: 100%" border
                     :edit-config="{ trigger: 'click', mode: 'cell' }" class="dc-ux-grid_QueryTable">
              <ux-table-column type="selection" tree-node resizable min-width="20px" align="center" v-if="false"
                               header-align="center" sortable show-overflow
                               class="dc-ux-table-column_ElTableColumn"></ux-table-column>
              <ux-table-column field="ID" title="序号" min-width="80px" tree-node resizable align="center"
                               header-align="center" sortable show-overflow
                               class="dc-ux-table-column_ElTableColumn"></ux-table-column>
              <ux-table-column v-if=false field="id" label="ID"></ux-table-column>
              <ux-table-column field="pdNo" title="产品编号" tree-node resizable min-width="180px" align="center"
                               header-align="center" sortable show-overflow
                               class="dc-ux-table-column_ElTableColumn"></ux-table-column>
              <ux-table-column v-if=false display="none" field="product" label="产品Id"></ux-table-column>
              <ux-table-column field="pdName" title="产品名称" tree-node resizable min-width="180px" align="center"
                               header-align="center" sortable show-overflow
                               class="dc-ux-table-column_ElTableColumn"></ux-table-column>
              <ux-table-column field="pdcName" title="产品分类" tree-node resizable min-width="180px" align="center"
                               header-align="center" sortable show-overflow
                               class="dc-ux-table-column_ElTableColumn"></ux-table-column>
              <ux-table-column field="pdType" title="产品类型" tree-node resizable min-width="180px" align="center"
                               header-align="center" sortable show-overflow
                               class="dc-ux-table-column_ElTableColumn"></ux-table-column>
              <ux-table-column field="pdGuidePrice" title="指导价(元)" tree-node resizable min-width="180px"
                               align="right"
                               header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="scope">
                  <!-- 使用 formatter 属性自定义列的显示内容 -->
                  <span>{{ scope.row.pdGuidePrice }}</span>
                </template>
              </ux-table-column>
              <ux-table-column field="sigleNum" title="数量" tree-node resizable min-width="180px" align="center"
                               header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
                <template slot-scope="scope">
                  <number-input
                    v-model.trim="scope.row.sigleNum"
                    :disabled="action == 'view'"
                    :min="0"
                    :max="9999999999.99"
                    :precision="0"
                    placeholder="请输入数量"
                    clearable
                    class="dc-number-input_NumberInput"
                    @input="updatePriceSum"
                  ></number-input>
                </template>
              </ux-table-column>
              <ux-table-column field="discount" title="折扣" tree-node resizable min-width="180px" align="center"
                               header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
                <template slot-scope="scope">
                  <number-input
                    v-model.trim="scope.row.discount"
                    :disabled="action == 'view'"
                    :min="0"
                    :max="9999999999.99"
                    :precision="2"
                    placeholder="请输入数量"
                    clearable
                    class="dc-number-input_NumberInput"
                    style="width: calc(100% - 24px)"
                    @input="updatePriceSum"

                  ></number-input>
                  <span class="percentage-unit">%</span> <!-- 这里添加固定单位 -->
                </template>

              </ux-table-column>
              <ux-table-column field="pdPriceSum" title="总价" tree-node resizable min-width="180px" align="right"
                               header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
                <template slot-scope="scope">
                  {{
                    pdPriceSumTotal(scope.row)
                  }}
                </template>
              </ux-table-column>
              <ux-table-column field="remarks" title="备注" tree-node resizable min-width="180px" align="rigth"
                               header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
                <template slot-scope="scope">
                  <el-input
                    v-model.trim="scope.row.remarks"
                    clearable
                    placeholder="请输入"
                    :disabled="action == 'view'"
                  >
                  </el-input>
                </template>
              </ux-table-column>
            </ux-grid>
            <el-pagination :total="tableDataTotal" :page-size="search.limit" background :current-page="tableDataPage"
                           :page-sizes="[10, 20, 30, 40, 50, 100]" layout="total, sizes, prev, pager, next, jumper"
                           v-on:size-change="onSizeChange" v-on:current-change="onCurrentChange"
                           class="dc-el-pagination_ElPagination"></el-pagination>
            <!--            <el-table @select="selectionChange" show-summary :summary-method="getSummaries"
                                  :data="tableData2" @selection-change="selectionChange"
                                  style="width: 100%">
                          &lt;!&ndash; 定义表格列 &ndash;&gt;
                          <el-table-column type="selection"></el-table-column>
                          <el-table-column prop="ID" label="ID"  min-width="20px"></el-table-column>
                          <el-table-column v-if=false prop="id" label="ID"></el-table-column>
                          <el-table-column prop="pdNo" label="产品编号" header-align="center"   align="center"></el-table-column>
                          <el-table-column v-if=false display="none" prop="product" label="产品Id"></el-table-column>
                          <el-table-column prop="pdName" label="产品名称" header-align="center"   align="center"></el-table-column>
                          <el-table-column prop="pdcName" label="产品分类" header-align="center"   align="center"></el-table-column>
                          <el-table-column prop="pdType" label="产品类型" header-align="center"   align="center"></el-table-column>
                          <el-table-column prop="pdGuidePrice" label="指导价(元)"   header-align="center"   align="right"
                          >
                            <template slot-scope="scope">
                              &lt;!&ndash; 使用 formatter 属性自定义列的显示内容 &ndash;&gt;
                              <span>{{ scope.row.pdGuidePrice }}</span>
                            </template>
                          </el-table-column>
                          <el-table-column prop="sigleNum" label="数量">
                            <template slot-scope="scope">
                              <number-input
                                v-model.trim="scope.row.sigleNum"
                                :disabled="action == 'view'"
                                :min="0"
                                :max="9999999999.99"
                                :precision="0"
                                placeholder="请输入数量"
                                clearable
                                class="dc-number-input_NumberInput"
                              ></number-input>
                            </template>
                          </el-table-column>
                          <el-table-column prop="discount" label="折扣" header-align="center"   align="right">
                            <template slot-scope="scope">
                              <number-input
                                v-model.trim="scope.row.discount"
                                :disabled="action == 'view'"
                                :min="0"
                                :max="9999999999.99"
                                :precision="2"
                                placeholder="请输入数量"
                                clearable
                                class="dc-number-input_NumberInput"
                                style="width: calc(100% - 24px)"
                              ></number-input>
                              <span class="percentage-unit">%</span> &lt;!&ndash; 这里添加固定单位 &ndash;&gt;
                            </template>

                          </el-table-column>
                          <el-table-column prop="pdPriceSum" label="总价" header-align="center"   align="right">
                            <template slot-scope="scope">
                              {{
                                pdPriceSumTotal(scope.row)
                              }}
                            </template>
                          </el-table-column>
                          <el-table-column prop="remarks" label="备注">
                            <template slot-scope="scope">
                              <el-input
                                v-model.trim="scope.row.remarks"
                                clearable
                                placeholder="请输入"
                                :disabled="action == 'view'"
                              >
                              </el-input>
                            </template>
                          </el-table-column>
                        </el-table>-->
          </el-tab-pane>
          <el-tab-pane label="合同" v-if="action != 'add'" name="2">
            <div style="margin-bottom: 10px">
              <!--            <el-button v-on:click="addContract" :disabled="action == 'view'"  type="primary" icon="el-icon-edit-outline" class="dc-el-button_Button">新建合同</el-button>-->
              <el-button v-on:click="viewContract" type="primary"
                         icon="el-icon-edit-outline" class="dc-el-button_Button">查看合同
              </el-button>
              <div>
                <el-row class="dc-el-row_ElRow" style="top: 30%; position: absolute;">
                  <!--style="top: 64%;right: 81%; position: fixed;"-->

                  <process-panel v-if="action !== 'view' && this.contractData.length === 0" ref="processPanel"
                                 :bizAdd="true" :tenantId="currentUser.company.id" keyPrefix="PROC_SJ"
                                 procName="合同管理"
                                 formKey="wf/common/wfForm.vue#crmBusiness/crmOpportunity/contractFlow.vue"
                                 v-on:save-finished="getShangJiPage()"
                                 procTitile="${start_user_name}发起${form.name}XX审批" v-on:v-loading="onVLoading"
                                 class="dc-process-panel_ProcessPanel"></process-panel>
                </el-row>
                <span style="color: red" v-if="this.contractData.length>0">该商机已创建合同，不可重复添加</span>
              </div>
            </div>
            <ux-grid :data="contractData" @row-click="contraceHandleRowClick" highlight-current-row
                     :current-row="currentRow" style="width: 100%">
              <ux-table-column field="ID" title="序号" tree-node resizable min-width="80px" align="center"
                               header-align="center" sortable show-overflow
                               class="dc-ux-table-column_ElTableColumn"></ux-table-column>
              <ux-table-column v-if=false field="id" title="ID"></ux-table-column>
              <ux-table-column title="合同编号" field="contractCode" tree-node resizable min-width="180px"
                               align="center"
                               header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
              </ux-table-column>
              <ux-table-column title="合同名称" field="contractName" tree-node resizable min-width="180px"
                               align="center"
                               header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
              </ux-table-column>
              <ux-table-column title="客户" field="customerName" tree-node resizable min-width="180px" align="center"
                               header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
              </ux-table-column>
              <ux-table-column title="合同金额" field="contractAmount" tree-node resizable min-width="180px"
                               align="right"
                               header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
                <template slot-scope="scope">
                  <span>{{ scope.row.contractAmount }}元</span>
                </template>
              </ux-table-column>
              <ux-table-column title="签约日期" field="signDate" tree-node resizable min-width="180px" align="center"
                               header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
              </ux-table-column>
              <ux-table-column title="合同状态" field="contractStatus" tree-node resizable min-width="180px"
                               align="center"
                               header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
                <template slot-scope="{row}">
                  {{ conType(row.contractStatus) }}
                </template>
              </ux-table-column>
              <ux-table-column title="创建人" field="createBy" tree-node resizable min-width="180px" align="center"
                               header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
              </ux-table-column>
              <ux-table-column title="创建时间" field="createDate" tree-node resizable min-width="180px" align="center"
                               header-align="center" sortable show-overflow class="dc-ux-table-column_ElTableColumn">
              </ux-table-column>
            </ux-grid>
            <el-pagination :total="contractDataTotal" :page-size="search.limit" background
                           :current-page="contractDataPage"
                           :page-sizes="[10, 20, 30, 40, 50, 100]" layout="total, sizes, prev, pager, next, jumper"
                           v-on:size-change="onSizeChange" v-on:current-change="onCurrentChange"
                           class="dc-el-pagination_ElPagination"></el-pagination>
            <!-- el-table 在弹窗中显示的表格 -->
            <!--            <el-table :data="contractData" @row-click="contraceHandleRowClick" highlight-current-row
                                  :current-row="currentRow" style="width: 100%">
                          <el-table-column prop="ID" label="序号" min-width="20px"></el-table-column>
                          <el-table-column v-if=false prop="id" label="ID"  ></el-table-column>
                          <el-table-column label="合同编号" prop="contractCode" header-align="center"   align="center" >
                          </el-table-column>
                          <el-table-column label="合同名称" prop="contractName" header-align="center"   align="center">
                          </el-table-column>
                          <el-table-column label="客户" prop="customerName" header-align="center"   align="center">
                          </el-table-column>
                          <el-table-column label="合同金额" prop="contractAmount" header-align="center"   align="right">
                            <template slot-scope="scope">
                              &lt;!&ndash; 使用 formatter 属性自定义列的显示内容 &ndash;&gt;
                              <span>{{ scope.row.contractAmount }}元</span>
                            </template>
                          </el-table-column>
                          <el-table-column label="签约日期" prop="signDate"  header-align="center"   align="center">
                          </el-table-column>
                          <el-table-column label="合同状态" prop="contractStatus" header-align="center"   align="center">
                            <template slot-scope="{row}">
                              {{ conType(row.contractStatus) }}
                            </template>
                          </el-table-column>
                          <el-table-column label="创建人" prop="createBy" header-align="center"   align="center">
                          </el-table-column>
                          <el-table-column label="创建时间" prop="createDate" header-align="center"   align="center">
                          </el-table-column>
                        </el-table>-->
            <comment ref="commentForm" class="dc-comment_ElImport"></comment>
            <component ref="wfForm" v-on:save-finished="getShangJiPage()" v-on:after-wfForm-load="afterWfFormload()"
                       :is="wfForm"
                       class="dc-component_ElImport"></component>
          </el-tab-pane>
          <el-tab-pane label="回款计划" v-if="action != 'add'" name="3">
            <pay-back ref="payBackDialog" :sjId="this.sjId" :action=action></pay-back>
          </el-tab-pane>
          <el-tab-pane label="联系人" v-if="action != 'add'" name="4">
            <contacts ref="contactsDialog" :action="action" :sjId="this.sjId"
                      :customerId="this.editFormData.customer.id"></contacts>
          </el-tab-pane>
        </el-tabs>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button v-on:click="onSubmit" type="primary" v-show="action != 'view'">确认</el-button>
        <el-button v-on:click="onDialogClose" v-if="action != 'view'">取消</el-button>
        <el-button v-on:click="onDialogClose" v-if="action == 'view'">关闭</el-button>
      </span>
    </el-dialog>
    <el-dialog v-on:open="onProDialogOpen"
               v-on:close="onProDialogClose"
               v-loading="loading"
               width="90%"
               :fullscreen="false"
               title="新增意向产品"
               :visible.sync="dialogProps.provisible"
               :close-on-click-modal="false"
               class="dc-el-dialog_ElDialog">
      <el-row>
        <el-form ref="editProForm"
                 :model="editProductFormData"
                 label-width="100px"
                 :disabled="proAction == 'view'"
                 class="dc-el-form_ElEditForm">
          <el-row>
            <el-col :span="8">
              <el-form-item :rules="[{ required: true, message: '请选择产品', trigger: 'blur' }]" prop="pdName"
                            label="产品名称" class="dc-el-form-item_SelectInput">
                <el-select
                  v-model="editProductFormData.pdName"
                  @change="this.pdNameChanges"
                  :style="{ width: '100%' }"
                  class="dc-el-select_SelectInput"
                >
                  <el-option v-for="(item, index) in pdNameOptions"
                             :key="index"
                             :label="item.pdName"
                             :value="item.id">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="pdNo" label="产品编号" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editProductFormData.pdNo"
                  :maxLength="50"
                  placeholder="请输入产品编号"
                  clearable
                  disabled="disabled"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="pdcName" label="产品分类" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editProductFormData.pdcName"
                  :maxLength="50"
                  clearable
                  disabled="disabled"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>

            <el-form-item v-if="false" prop="pdcCode" label="产品分类" class="dc-el-form-item_MutilpleInput">
              <el-input
                v-model="editProductFormData.pdcCode"
                :maxLength="50"
                clearable
                disabled="disabled"
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>

            <el-col :span="8">
              <el-form-item prop="pdGuidePrice" label="指导价" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editProductFormData.pdGuidePrice"
                  :maxLength="50"
                  clearable
                  disabled="disabled"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="pdCostPrice" label="成本" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editProductFormData.pdCostPrice"
                  :maxLength="50"
                  clearable
                  disabled="disabled"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="pdType" label="产品类型" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editProductFormData.pdType"
                  :maxLength="50"
                  clearable
                  disabled="disabled"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>

              <el-form-item v-show="false" prop="pdTypeId" label="产品类型" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editProductFormData.pdTypeId"
                  :maxLength="50"
                  clearable
                  disabled="disabled"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>

            <el-col :span="8">
              <el-form-item prop="sigleNum" label="数量" class="dc-el-form-item_MutilpleInput">
                <number-input
                  v-model.trim="editProductFormData.sigleNum"
                  :min="0"
                  :max="9999999999.99"
                  :precision="0"
                  placeholder="请输入数量"
                  clearable
                  class="dc-number-input_NumberInput"
                  @input="updateTotal"
                ></number-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="discount" label="折扣比例" class="dc-el-form-item_MutilpleInput">
                <number-input
                  v-model.trim="editProductFormData.discount"
                  :min="0"
                  :max="9999999999.99"
                  :precision="0"
                  placeholder="请输入折扣比例"
                  clearable
                  class="dc-number-input_NumberInput"
                  append="%"
                  @input="updateTotal"
                >
                </number-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="pdPriceSum" label="总价" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editProductFormData.pdPriceSum"
                  :maxLength="50"
                  clearable
                  disabled="disabled"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="remarks" label="备注" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editProductFormData.remarks"
                  :autosize="{ minRows: 2, maxRows: 4}"
                  clearable
                  type="textarea"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button v-on:click="onProSubmit" type="primary" v-show="proAction != 'view'">保存</el-button>
        <el-button v-on:click="onProDialogClose" v-if="proAction != 'view'">取消</el-button>
        <el-button v-on:click="onProDialogClose" v-if="proAction == 'view'">关闭</el-button>
      </span>

    </el-dialog>
  </el-row>
</template>
<script>
import {
  validatenull
} from '@/utils/validate'
/** 根据用户界面配置import组件 结束 */
import {
  treeCrmCustomer
} from '@/api/crmCustomer/crmCustomer.js'
import {getDictTypeById} from '@/api/sys/dictType.js'


/** 根据用户界面配置import组件 结束 */
import {
  getCrmOpportunityById,
  saveCrmOpportunity
} from '@/api/crmBusiness/crmOpportunity'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'
import {treeDepartment} from "../../../api/org/department";
import {
  bulkDeleteCrmOpportunityProduct,
  bulkInsertCrmOpportunityProduct, deleteCrmOpportunityProduct, listCrmOpportunityProductAll,
  listCrmOpportunityProductPage,
  saveCrmOpportunityProduct
} from "../../../api/crmBusiness/crmOpportunityProduct";
import {getCrmProductsById, listCrmProductsAll} from "../../../api/crmProInfo/crmProducts";
import {getDictItemById, listDictItemAll, listDictItemPage} from "../../../api/sys/dictItem";
import PayBack from "./payBack.vue";
import Contacts from "./contacts.vue";
import EditFormDialog from './contractDialog.vue'
import {listCrmContractPage} from "../../../api/contract/crmContract";
import EditForm from "./contractDialog.vue";
import {
  deleteCrmOpportunityPay,
  getContractPayDetails,
  listCrmOpportunityPayPage
} from "../../../api/crmBusiness/crmOpportunityPay";
import OpportunityMove from "./opportunityMove.vue";
import crmContactDialog from "./contractDialog.vue"
import CrmContractForm from "../../contract/crmContract/form.vue";
import ProcessPanel from "../../wf/common/processPanel.vue";
import Comment from "../../wf/common/comment.vue";
import {getHistProcInstance} from "../../../api/wf/procinst";
import {getStartForm} from "../../../api/wf/processdefinition";
import WfBaseUI from "../../wf/common/wfBaseUI.vue";
import {listCrmContactsPage} from "../../../api/contacts/crmContacts";


export default {
  extends: WfBaseUI,
  name: 'crmOpportunity-form',
  components: {
    Comment,
    ProcessPanel,
    CrmContractForm,
    OpportunityMove,
    EditForm,
    Contacts,
    PayBack,
    EditFormDialog,
    /** 根据用户界面配置组件 开始 */

    /** 根据用户界面配置组件 结束 */

    OperationIcon,
    crmContactDialog,
  },
  data() {
    return {
      /** 根据用户界面配置生成data数据 开始 */
      editFormData: this.initEditData(),

      editProductFormData: [],
      // 接收意向产品数据
      productFormData: [],
      // 对话框属性变量
      dialogProps: {
        visible: false,
        provisible: false,
        title: '商机管理'
      },
      //  第二个表格数据
      tableData2: [],
      tableDataTotal: 0,
      tableDataPage: 1,
      // 合同表格
      contractData: [],
      contractDataTotal: 0,
      contractDataPage: 1,

      dialogTitle: '商机管理',
      // 选项变量
      // 客户选项
      customerOptions: [],
      // 商机来源选项
      selectOptions1: [],
      // 销售阶段选项
      selectOptions2: [],
      /** 根据用户界面配置生成data数据 结束 */
      // 产品选项
      pdNameOptions: [],
      departmentOptions: [],
      // 销售阶段
      oppStageOptions1: [],


      // 窗口操作类型 view/edit/add
      action: '',
      proAction: '',
      // 选项卡名称
      activeName: "1",
      // 多选绑定
      selectedRows: [],

      // 合并总价
      totalRepayment: 0,
      // 移除商品明细暂存
      removeTableData: [],
      // 商机ID
      sjId: '',
      // 合同选中行
      currentRow: [],
      clickVisible: false,
      // 联系人
      oppContactsOptions: [],
      search: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
    }
  },
  props: {
    // 权限
    permission: {
      type: Object
    }
  },
  computed: {},

  filters: {},

  methods: {
    searchContacts(id) {
      // 联系人
      this.listCrmContactsPage(id)
    },
    oppContactClick(val) {
      let newArray = this.oppContactsOptions.filter((item, index) => {
        return item.id === val;
      })
      debugger
      this.editFormData.oppDepartment = newArray[0].depart
    },
    priceSumFormat(num) {
      num = num == null || num == "" ? 0.00 : num
      return num.toFixed(2);
    },
    // 合同状态-列表  已保存（0）、审批中（1）、审批通过（2）、审批不通过（9）
    conType(row) {
      let name;
      switch (row) {
        case "0":
          name = "已保存"
          break;
        case "1":
          name = "审批中"
          break
        case "2":
          name = "审批通过"
          break
        case "9":
          name = "审批不通过"
          break
        default:
          name = "未知"
      }
      return name;
    },
    updateTotal(){
      let  discount = this.editProductFormData.discount === undefined ? 0:this.editProductFormData.discount;
      let  sigleNum = this.editProductFormData.sigleNum === undefined ? 0:this.editProductFormData.sigleNum;
      let  pdGuidePrice = this.editProductFormData.pdGuidePrice === undefined ? 0:this.editProductFormData.pdGuidePrice;
      this.editProductFormData.pdPriceSum = this.priceSumFormat(pdGuidePrice * sigleNum *
        (1 - discount/ 100));
    },
    // 列表总计
    pdPriceSumTotal(row) {
      row.pdPriceSum = row.pdGuidePrice * row.sigleNum * (1 - row.discount / 100)
      return this.priceSumFormat(row.pdPriceSum);
    },
    oppStageOption() {
      let search_List = {
        params: []
      }
      search_List.params.push({'columnName': 'dictType.code', 'queryType': '=', 'value': "CRM_DICT_OPPS_STAGE"})
      listDictItemPage(search_List).then((res) => {
        if (res.code == 100) {
          this.oppStageOptions1 = res.data.rows;
        } else {
          this.showMessage(res)
        }
      })
    },
    updatePriceSum(){
      let sums = 0;
      for (let i = 0; i < this.tableData2.length; i++) {
        sums += this.tableData2[i].pdGuidePrice * this.tableData2[i].sigleNum * (1 - this.tableData2[i].discount / 100)
      }
      this.totalRepayment = sums;
    },
    getSummaries(param) {
      const {columns, data} = param;
      if (columns == null) {
        return []
      }
      let sums = 0;
      for (let i = 0; i < data.length; i++) {
        sums += data[i].pdGuidePrice * data[i].sigleNum * (1 - data[i].discount / 100)
      }

      /*columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = '总价';
          return;
        }
        // if (column.property == "pdPriceSum") {
        //   debugger
        const values = data.map(item => Number(item[column.property]));
        if (!values.every(value => isNaN(value))) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr);
            if (!isNaN(value)) {
              return prev + curr;
            } else {
              return prev;
            }
          }, 0);
        }

        // }
      });*/
      this.totalRepayment = sums;
      return [];
    },
    selectionChange(row) {
      this.selectedRows = row;
    },

    // TAB 点击事件
    handleTabClick(tab) {
      if (tab.name == 1 && this.action != "add") {
        this.opportunityDetails(this.sjId)
      } else if (tab.name == 2) {

        sessionStorage.setItem("opportunityId", this.sjId);
        sessionStorage.setItem("opportunityName", this.editFormData.oppName);
        sessionStorage.setItem("opportunityCustomer", this.editFormData.customer.id);
        sessionStorage.setItem("opportunityPayment", this.totalRepayment);

        this.getShangJiPage();
      } else if (tab.name == 3) {
        this.$refs.payBackDialog.$emit("openPayBackDialog", this.sjId);
      } else if (tab.name == 4) {
        this.$refs.contactsDialog.$emit("openContactsAddDialog");
      }
    },
    // 添加产品
    addProduct() {
      this.proAction = "edit"
      this.dialogProps.provisible = true
      this.onProDialogOpen()
      this.editProductFormData = []
      let search_List = {
        params: []
      }
      search_List.params.push.apply(search_List.params, [])
      listCrmProductsAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.pdNameOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
        this.resetLoad()
      })
        .catch((error) => {
          this.outputError(error)
        })
    },
    removeProduct() {
      if (this.selectedRows.length === 0) {
        this.showMessage({
          type: 'success',
          msg: '请选择数据'
        })
        return false;
      }
      this.$confirm('确定删除吗？', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        debugger
        deleteCrmOpportunityProduct(this.selectedRows).then((res) => {
          if (res.code == 100) {
            this.showMessage({
              type: 'success',
              msg: '删除成功'
            })
            this.opportunityDetails(this.sjId)
          } else {
            // this.$message.error(res)
            this.opportunityDetails(this.sjId)
          }
        })
      })
    },
    onSubmit() {
      this.$refs['editForm'].validate(valid => {
        if (valid) {
          this.doSave()
        } else {
          return false
        }
      });
    },
    doSave() {
      let _this = this;
      this.setLoad()
      let i = 0;

      this.editFormData.oppPay = this.totalRepayment === undefined ? 0 : this.totalRepayment
      if (this.tableData2.length === 0) {
        this.editFormData.oppStage = "1" //"沟通"
      } else {
        this.editFormData.oppStage = "2"//"发送报价单"
      }
      _this.tableData2.forEach((item, index) => {
        if (item.sigleNum == null || item.sigleNum < 0 || item.discount == null || item.discount < 0) {
          _this.$message.error("请输入数量或者折扣！");
          this.resetLoad()
          i++;
        }
      })
      if (i !== 0) {
        return
      }
      saveCrmOpportunity(this.editFormData).then(responseData => {

        if (responseData.code == 100) {
          if (_this.tableData2.length != 0) {
            let array = [];
            _this.tableData2.map((item, index) => {
              array.push(
                Object.assign({}, item, {'opp': responseData.data}),
              )
            })
            bulkInsertCrmOpportunityProduct(array).then(responseData => {
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
            })
          } else {
            this.dialogProps.visible = false
            this.showMessage({
              type: 'success',
              msg: '保存成功'
            })
            this.$emit('save-finished')
          }
        } else {
          this.showMessage(responseData)
        }
        this.resetLoad()
      }).catch(error => {
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
      this.sjId = id
      return new Promise((resolve) => {
        getCrmOpportunityById(id).then((responseData) => {
          let form = {}
          if (responseData.code == 100) {
            form = responseData.data
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
          resolve(form)
        }).catch((error) => {
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
    onProDialogClose() {
      this.dialogProps.provisible = false
    },
    onProDialogOpen() {
      this.$nextTick(() => {
        this.$refs['editProForm'].clearValidate()
      })
    },
    // 新增意向产品
    onProSubmit() {
      this.$refs['editProForm'].validate(valid => {
        for (let i = 0; i < this.tableData2.length; i++) {
          if (this.editProductFormData.pdNo === this.tableData2[i].pdNo) {
            this.$message.info("明细中已添加该产品，无需重复添加");
            return
          }
        }
        if (valid) {
          this.productFormData = this.editProductFormData;
          this.productFormData.ID = this.tableData2.length + 1;
          this.productFormData.id = null;
          this.productFormData.product = this.editProductFormData.pdid
          this.tableData2.push(this.productFormData);
          this.tableDataTotal = this.tableData2.length
        } else {
          return false
        }
        this.onProDialogClose();
      });
    },

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

    listSelectOptions1() {
      getDictTypeById('1705054496995033113').then(responseData => {
        if (responseData.code == 100) {

          this.selectOptions1 = responseData.data.dictItemList

        } else {
          this.showMessage(responseData)
        }
      })
    },


    listSelectOptions2() {
      getDictTypeById('1705054496995033198').then(responseData => {
        if (responseData.code == 100) {

          this.selectOptions2 = responseData.data.dictItemList

        } else {
          this.showMessage(responseData)
        }
      })
    },

    listSelectDepart() {
      let search_List = {
        params: []
      };
      if (currentUser.id != 1001) {
        search_List.params.push({'columnName': 'company_id', 'queryType': '=', 'value': currentUser.company.id})
      } else {
        search_List.params.push({'columnName': 'company_id', 'queryType': '=', 'value': "1665295280360415265"})
      }
      treeDepartment(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.departmentOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
    },

    // 查看商机明细table
    opportunityDetails(id) {
      return new Promise((resolve) => {
        this.tableData2 = []
        let search_List = {
          params: [],
        }
        search_List.params.push({'columnName': 'opp_id', 'queryType': '=', 'value': id})

        listCrmOpportunityProductPage(search_List).then((responseData) => {
          if (responseData.code == 100) {
            let array = [];
            this.tableDataTotal = responseData.data.total
            let resData = responseData.data.rows ? responseData.data.rows : []
            if (resData == null) {
              return []
            }
            resData.map((item, index) => {
              debugger
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
            })
            this.tableData2 = array
          } else {
            this.showMessage(responseData)
          }
          resolve(this.tableData2)
        }).catch((error) => {
          this.outputError(error)
        })
      })
    },

    // 选中产品名称后带出信息
    pdNameChanges(val) {
      let _this = this;
      getCrmProductsById(val).then(async (responseData) => {
        if (responseData.code == 100) {
          let search_List = {
            params: [{'columnName': 'dictType.code', 'queryType': '=', 'value': "CRM_DICT_PRODUCT_TYPE"},
              {'columnName': 'value', 'queryType': '=', 'value': responseData.data.pdTypeId}]
          }
          // responseData.data.pdTypeId
          await listDictItemAll(search_List).then((res) => {
            _this.editProductFormData = responseData.data
            _this.editProductFormData.pdcName = responseData.data.pdClassify.pdcName
            _this.editProductFormData.pdcCode = responseData.data.pdClassify.pdcCode
            _this.editProductFormData.pdid = responseData.data.id
            _this.editProductFormData.pdTypeId = responseData.data.pdTypeId;
            if (res.data.length === 0) {
              _this.$message.error("产品类型有误，请确认")
            } else {
              _this.editProductFormData.pdType = res.data[0].name;
            }
          })
        } else {
          _this.showMessage(responseData)
        }
      }).catch((error) => {
        _this.outputError(error)
      })
    },
    // 合同行选中
    contraceHandleRowClick(row) {
      this.currentRow = row; // 更新当前选中的行数据
    },
    addContract() {
      if (this.contractData.length >= 1) {
        this.$message.error("该商机已创建合同，不可重复添加")
      } else {
        this.$refs.editFormDialog.$emit('openAddDialog', this.sjId, this.editFormData.customer.id, this.totalRepayment)
      }
    },
    deleteContract() {
      if (this.currentRow.contractStatus == 0 || this.currentRow.contractStatus == 9) {
        this.$confirm('确认删除该数据？', '确认', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let pay = {"opp": this.sjId}
          deleteCrmOpportunityPay(pay).then((res) => {
            if (res.code == 100) {
              this.$message.warning("删除成功")
            } else {
              this.$message.error(res);
            }
            this.$emit('save-finished')
          })
        })
      } else {
        this.$message.error("当前状态无法删除合同!")
      }
    },
    /*viewContract() {
      if (this.currentRow.length == 0) {
        this.$message.warning("请选择一条数据")
        return false;
      }
      this.$refs.editFormDialog.$emit('openViewDialog', this.sjId, this.currentRow.id, this.totalRepayment)
    },*/
    editContract() {
      if (this.currentRow.length == 0) {
        this.showMessage({
          type: 'success',
          msg: '请选择数据'
        })
        return false;
      }
      this.clickVisible = true
      this.$refs.editFormDialog.$emit('openEditDialog', this.sjId, this.currentRow.id, this.totalRepayment)
    },
    listCrmContactsPage(id) {
      this.search.params = []
      this.search.params.push({
        columnName: 'custom_name',
        queryType: 'like',
        value: id
      })
      listCrmContactsPage(this.search)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.oppContactsOptions = responseData.data.rows ? responseData.data.rows : []
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    initOptions(This) {
      // 初始化自定义类型选择框选项变量
      this.listCustomerOptions()
      this.listSelectOptions1()
      // this.listSelectOptions2()
      // 部门
      this.listSelectDepart()
      // 销售阶段
      this.oppStageOption()
      // 客户对于联系人
      this.listCrmContactsPage(This.customer.id)

    },
    initEditData(This) {
      return {
        // 客户
        customer: {
          id: null,
          cusName: null,
        },
        oppName: '', // 商机名称
        oppSource: null, // 商机来源
        oppStage: "沟通", // 销售阶段
        oppContacts: '', // 联系人
        oppPay: null, // 销售金额
        oppDepartment: '', // 所属部门
        oppResponsible: '', // 负责人
        remarks: '', // 备注信息
      }
    },
    viewContract() {
      if (this.currentRow.length == 0) {
        this.showMessage({
          type: 'success',
          msg: '请选择数据'
        })
        return false;
      }
      if (this.currentRow.procInst === undefined) {
        this.$message.error("当前合同暂无法查看")
        return false;
      }

      this.setLoad()
      getHistProcInstance(this.currentRow.procInst)
        .then((responseData) => {
          if (responseData.hasOwnProperty('processDefinitionId')) {
            let definitionId = responseData.processDefinitionId
            if (definitionId) {
              getStartForm(definitionId).then((startFormResp) => {
                if (startFormResp.hasOwnProperty('key')) {
                  if (startFormResp.key) {
                    this.currentRow = {
                      formKey: startFormResp.key,
                      definitionId: definitionId,
                      row: this.currentRow
                    }
                    this.loadWfForm(startFormResp.key)
                  } else {
                    this.showMessage({
                      type: 'warning',
                      msg: '流程未配置form，请联系管理员。'
                    })
                  }
                } else {
                  this.showMessage(startFormResp)
                }
                this.loading = false
              })
            } else {
              this.showMessage({
                type: 'warning',
                msg: '流程实例没有找到流程定义。'
              })
            }
          } else {
            this.showMessage(responseData)
          }
          this.loading = false
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    afterWfFormload() {
      debugger
      this.listTableHuiKuan(this.currentRow.row.id);
    },
    // 合同table
    listTableHuiKuan(contractId) {
      let search_List = {
        params: []
      }
      search_List.params.push({'columnName': 'contract_id', 'queryType': '=', 'value': contractId})
      getContractPayDetails(search_List).then((res) => {
        if (res.code == 100) {
          /* res.data.rows.map((item, index) => {
             buyes.push(
               Object.assign({}, item, {
                 'workUnit':index+1,
               }),
             )
           })*/
          this.currentRow.row.payList = (res.data.rows);
          this.$refs.wfForm.$emit('openViewBizDialog', this.currentRow.formKey, this.currentRow.definitionId, this.currentRow.row)
        } else {
          this.$message.error(res)
        }
      })
    },
    // 获取当前合同信息
    getShangJiPage() {
      this.contractData = []
      let search_List = {
        params: []
      }
      search_List.params.push({'columnName': 'opportunity_id', 'queryType': '=', 'value': this.sjId})

      listCrmContractPage(search_List).then((res) => {
        if (res.code == 100) {
          this.contractDataTotal = res.data.total
          let array = res.data.total == 0 ? [] : res.data.rows
          array.map((item, index) => {
            this.contractData.push(
              Object.assign({}, item, {
                'ID': index + 1,
              }),
            )
          })
        } else {
          this.$message(res);
        }
      })
    }
  },
  watch: {},

  mounted: function () {
    this.$nextTick(() => {
      let tab = {
        name: 1
      }
      this.$on('openViewDialog', async function (id) {
        this.action = 'view'

        this.dialogProps.title = `查看${this.dialogTitle}`
        this.tableData2 = []
        this.tableDataTotal = 0;
        this.editFormData = {
          ...this.initEditData(),
          ...await this.getFormById(id),
        }
        await this.handleTabClick(tab)
        // await this.opportunityDetails(id),
        this.initOptions(this.editFormData)
        this.dialogProps.visible = true
      })

      this.$on('openEditDialog', async function (id) {
        this.action = 'edit'
        this.dialogProps.title = `修改${this.dialogTitle}`
        this.tableData2 = []
        this.tableDataTotal = 0;
        this.editFormData = {
          ...this.initEditData(),
          ...await this.getFormById(id),
        }
        await this.handleTabClick(tab)
        // await this.opportunityDetails(id),
        this.initOptions(this.editFormData)
        this.dialogProps.visible = true
      })
      this.$on('openAddDialog', function () {
        this.action = 'add'
        this.handleTabClick(tab)
        this.dialogProps.title = `添加${this.dialogTitle}`
        this.tableData2 = []
        this.tableDataTotal = 0;
        this.editFormData = this.initEditData()
        this.initOptions(this.editFormData)
        this.dialogProps.visible = true
      })
      this.$on('openCopyDialog', async function (id) {
        this.action = 'add'
        this.dialogProps.title = `添加${this.dialogTitle}`
        this.tableData2 = []
        this.editFormData = {
          ...this.initEditData(),
          ...await this.getFormById(id)
        }
        this.handleTabClick(tab)

        // await this.opportunityDetails(id)
        await this.getShangJiPage()

        this.initOptions(this.editFormData)
        this.editFormData.id = null //把id设置为空，添加一个新的


        this.dialogProps.visible = true
      })
    })
  }
}
</script>
<style>
.percentage-unit {
  font-size: 12px; /* 调整单位的字体大小 */
  margin-left: 5px; /* 添加一些左侧间距 */
  vertical-align: middle; /* 垂直居中对齐 */
}
</style>
