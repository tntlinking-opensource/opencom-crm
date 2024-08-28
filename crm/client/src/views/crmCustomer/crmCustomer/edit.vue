<template>
  <el-row class="dc-container">
    <edit-form
      ref="editForm"
      v-on:save-finished="getList1"
    >
    </edit-form>
    <top1-edit-form
      ref="top1EditForm"
      v-on:save-finished="getList();getList1()"
      v-on:before-load="setLoad"
      v-on:after-load="resetLoad"
      :permission="permission"
    ></top1-edit-form>

    <top2-edit-form
      ref="top2EditForm"
      v-on:save-finished="getList2"
      v-on:before-load="setLoad"
      v-on:after-load="resetLoad"
      :permission="permission"
    ></top2-edit-form>

    <top3-edit-form
      ref="top3EditForm"
      v-on:save-finished="getList3"
      v-on:before-load="setLoad"
      v-on:after-load="resetLoad"
      :permission="permission"
    ></top3-edit-form>

    <top4-edit-form
      ref="top4EditForm"
      v-on:save-finished="getList4"
      v-on:before-load="setLoad"
      v-on:after-load="resetLoad"
      :permission="permission"
    ></top4-edit-form>

    <top5-edit-form
      ref="top5EditForm"
      v-on:save-finished="getList5"
      v-on:before-load="setLoad"
      v-on:after-load="resetLoad"
      :permission="permission"
    ></top5-edit-form>

    <top6-edit-form
      ref="top6EditForm"
      v-on:save-finished="getList6"
      v-on:before-load="setLoad"
      v-on:after-load="resetLoad"
      :permission="permission"
    ></top6-edit-form>

    <top7-edit-form
      ref="top7EditForm"
      v-on:save-finished="getList3"
    ></top7-edit-form>

    <el-dialog
      v-loading="loading"
      width="100%"
      fullscreen
      :title="dialogProps.title"
      :visible.sync="dialogProps.visible"
      :close-on-click-modal="false"
      class="dc-el-dialog_ElDialog"
    >
      <el-row>
        <el-tooltip disabled class="dc-el-tooltip_Button">
          <el-button type="primary" class="dc-el-button_Button" @click="onDialogClose">返回</el-button>
        </el-tooltip>
        <el-divider class="dc-el-divider_ElDivider">客户信息</el-divider>
        <el-tooltip disabled class="dc-el-tooltip_Button">
          <el-button type="primary" icon="el-icon-edit-outline" v-on:click="onEdit" class="dc-el-button_Button">编辑
          </el-button>
        </el-tooltip>
        <el-form ref="editForm128" :model="editForm128Data" label-width="100px" class="dc-el-form_ElEditForm">
          <el-row>
            <el-col :span="8">
              <el-form-item prop="cusName" label="客户名称" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.cusName"
                  :maxLength="255"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item prop="cusStage" label="客户阶段" class="dc-el-form-item_SelectInput">
                <el-select
                  v-model="editForm128Data.cusStage"
                  :style="{ width: '100%' }"
                  placeholder="客户类型"
                  clearable
                  value-key="id"
                  filterable
                  disabled
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in selectOptions1"
                    :key="index"
                    :label="item.name"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item prop="cusType" label="客户类型" class="dc-el-form-item_SingleInput">
                <el-select
                  v-model="editForm128Data.cusType"
                  :style="{ width: '100%' }"
                  placeholder="客户类型"
                  clearable
                  value-key="id"
                  filterable
                  disabled
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in customeType"
                    :key="index"
                    :label="item.name"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item prop="cusContacts" label="联系人" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.cusContacts"
                  :maxLength="50"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item prop="cusMobile" label="联系方式" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.cusMobile"
                  :maxLength="60"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="parent.cusName" label="上级客户" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.parent.cusName"
                  :maxLength="15"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="cusRegion" label="所在区域" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.cusRegion"
                  :maxLength="100"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item prop="cusAddress" label="详细地址" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.cusAddress"
                  :maxLength="200"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item prop="cntPname" label="最新更进人" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.cntPname"
                  :maxLength="60"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="cntDate" label="最新更近时间" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.cntDate"
                  :maxLength="60"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>


            <el-col :span="8">
              <el-form-item prop="remarks" label="备注" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.remarks"
                  :maxLength="500"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item prop="cusSource" label="客户来源" class="dc-el-form-item_SingleInput">
                <el-select
                  v-model="editForm128Data.cusSource"
                  :style="{ width: '100%' }"
                  placeholder="客户来源"
                  clearable
                  value-key="id"
                  filterable
                  disabled
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in custmerSource"
                    :key="index"
                    :label="item.name"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-tabs v-model="activeName" @tab-click="handleClick" type="border-card" tab-position="top"
                 class="dc-el-tabs_ElTabs">
          <el-tab-pane label="沟通记录" name="tab1" class="dc-el-tab-pane_ElTabPane">
            <el-tooltip disabled class="dc-el-tooltip_Button">
              <el-button type="primary" v-on:click="tab1onCreate" icon="el-icon-add-location"
                         class="dc-el-button_Button">新增
              </el-button>
            </el-tooltip>
            <ux-grid
              column-key
              ref="table"
              :data="tableData"
              border
              v-on:sort-change="onSortChange"
              v-on:header-dragend="onWidthChange($refs.table)"
              :tree-config="{ children: '', indent: 20, accordion: false, line: false, showIcon: true, iconOpen: '', iconClose: '' }"
              class="dc-ux-grid_QueryTable"
            >
              <ux-table-column
                :params="{ sortId: '141', summary: false }"
                type="checkbox"
                tree-node
                resizable
                min-width="80px"
                fixed="left"
                align="center"
                header-align="center"
                class="dc-ux-table-column_ElTableCheckBoxColumn"
              ></ux-table-column>
              <ux-table-column
                title="序号"
                :params="{ sortId: '142', summary: false }"
                type="index"
                tree-node
                resizable
                min-width="80px"
                fixed="left"
                align="center"
                header-align="center"
                class="dc-ux-table-column_ElTableNumColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns['129'].field"
                :title="tableColumns['129'].title"
                :width="tableColumns['129'].width"
                :visible="tableColumns['129'].visible"
                :params="{ sortId: '129', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="{ row }">
                  {{ handleKeyValue(row.cntType, selectOptions) }}
                </template>
              </ux-table-column>
              <ux-table-column
                :field="tableColumns['130'].field"
                :title="tableColumns['130'].title"
                :width="tableColumns['130'].width"
                :visible="tableColumns['130'].visible"
                :params="{ sortId: '130', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns['131'].field"
                :title="tableColumns['131'].title"
                :width="tableColumns['131'].width"
                :visible="tableColumns['131'].visible"
                :params="{ sortId: '131', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns['132'].field"
                :title="tableColumns['132'].title"
                :width="tableColumns['132'].width"
                :visible="tableColumns['132'].visible"
                :params="{ sortId: '132', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns['133'].field"
                :title="tableColumns['133'].title"
                :width="tableColumns['133'].width"
                :visible="tableColumns['133'].visible"
                :params="{ sortId: '133', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns['134'].field"
                :title="tableColumns['134'].title"
                :width="tableColumns['134'].width"
                :visible="tableColumns['134'].visible"
                :params="{ sortId: '134', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns['135'].field"
                :title="tableColumns['135'].title"
                :width="tableColumns['135'].width"
                :visible="tableColumns['135'].visible"
                :params="{ sortId: '135', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns['136'].field"
                :title="tableColumns['136'].title"
                :width="tableColumns['136'].width"
                :visible="tableColumns['136'].visible"
                :params="{ sortId: '136', summary: false }"
                tree-node
                resizable
                min-width="160px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns['137'].field"
                :title="tableColumns['137'].title"
                :width="tableColumns['137'].width"
                :visible="tableColumns['137'].visible"
                :params="{ sortId: '137', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                title="操作"
                :params="{ sortId: '114' }"
                tree-node
                resizable
                width="140px"
                min-width="140px"
                fixed="right"
                align="left"
                header-align="center"
                class="dc-ux-table-column_ElTableOptColumn"
              >
                <template v-slot:header="scope">
                  <span>操作</span>
                </template>
                <template slot-scope="scope">
                  <OperationIcon
                    v-on:click="onEdit1(scope)"
                    type="primary"
                    content="编辑"
                    placement="top"
                    icon-name="el-icon-edit"
                    class="dc-OperationIcon_IconButton"
                  ></OperationIcon>
                  <OperationIcon
                    v-on:click="onDelete(scope)"
                    type="danger"
                    content="删除"
                    placement="top"
                    icon-name="el-icon-delete"
                    class="dc-OperationIcon_IconButton"
                  ></OperationIcon>
                </template>
              </ux-table-column>
            </ux-grid>
            <el-pagination
              :total="tableDataTotal"
              :page-size="search.limit"
              background
              :current-page="tableDataPage"
              :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
              layout="total, sizes, prev, pager, next, jumper"
              v-on:size-change="onSizeChange"
              v-on:current-change="onCurrentChange"
              class="dc-el-pagination_ElPagination"
            ></el-pagination>
          </el-tab-pane>
          <el-tab-pane label="产品" name="tab2" class="dc-el-tab-pane_ElTabPane">
            <el-tooltip disabled class="dc-el-tooltip_Button">
              <el-button type="primary" v-on:click="tab2onCreate" icon="el-icon-add-location"
                         class="dc-el-button_Button">新增
              </el-button>
            </el-tooltip>
            <ux-grid
              column-key
              ref="table"
              :data="tableData2"
              border
              v-on:sort-change="onSortChange2"
              :tree-config="{ children: '', indent: 20, accordion: false, line: false, showIcon: true, iconOpen: '', iconClose: '' }"
              class="dc-ux-grid_QueryTable"
            >
              <ux-table-column
                :field="tableColumns2['129'].field"
                :title="tableColumns2['129'].title"
                :width="tableColumns2['129'].width"
                :visible="tableColumns2['129'].visible"
                :params="{ sortId: '129', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns2['130'].field"
                :title="tableColumns2['130'].title"
                :width="tableColumns2['130'].width"
                :visible="tableColumns2['130'].visible"
                :params="{ sortId: '130', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns2['131'].field"
                :title="tableColumns2['131'].title"
                :width="tableColumns2['131'].width"
                :visible="tableColumns2['131'].visible"
                :params="{ sortId: '131', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns2['132'].field"
                :title="tableColumns2['132'].title"
                :width="tableColumns2['132'].width"
                :visible="tableColumns2['132'].visible"
                :params="{ sortId: '132', summary: false }"
                tree-node
                resizable
                min-width="100px"
                align="right"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="{ row }">
                  <span v-if="row.pdGuidePrice">￥{{ row.pdGuidePrice }}</span>
                </template>
              </ux-table-column>
              <ux-table-column
                :field="tableColumns2['133'].field"
                :title="tableColumns2['133'].title"
                :width="tableColumns2['133'].width"
                :visible="tableColumns2['133'].visible"
                :params="{ sortId: '133', summary: false }"
                tree-node
                resizable
                min-width="100px"
                align="right"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="{ row }">
                  <span v-if="row.pdCostPrice">￥{{ row.pdCostPrice }}</span>
                </template>
              </ux-table-column>
              <ux-table-column
                :field="tableColumns2['134'].field"
                :title="tableColumns2['134'].title"
                :width="tableColumns2['134'].width"
                :visible="tableColumns2['134'].visible"
                :params="{ sortId: '134', summary: false }"
                tree-node
                resizable
                min-width="80px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="{ row }">
                  {{ handleKeyValue(row.pdTypeId, productType) }}
                </template>
              </ux-table-column>
              <ux-table-column
                :field="tableColumns2['135'].field"
                :title="tableColumns2['135'].title"
                :width="tableColumns2['135'].width"
                :visible="tableColumns2['135'].visible"
                :params="{ sortId: '135', summary: false }"
                tree-node
                resizable
                min-width="80px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
              </ux-table-column>
                            <ux-table-column
                              title="操作"
                              :params="{ sortId: '114' }"
                              tree-node
                              resizable
                              width="140px"
                              min-width="140px"
                              fixed="right"
                              align="center"
                              header-align="center"
                              class="dc-ux-table-column_ElTableOptColumn"
                            >
                              <template v-slot:header="scope">
                                <span>操作</span>
                                <el-popover ref="popover" width="10" trigger="click" placement="bottom" offset="10"
                                            class="dc-el-popover_ElPopover">
                                  <view-columns-select
                                    v-model="tableColumns2"
                                    ref="viewColumnsSelect"
                                    v-on:save-column-view="saveColumn($event, 'table')"
                                    v-on:show-all-column="showAllColumn($event, 'table')"
                                    v-on:show-default-column="showDefaultColumn('table')"
                                    class="dc-view-columns-select_ViewColumnsSelect"
                                  ></view-columns-select>
                                  <template slot="reference">
                                    <OperationIcon type="primary" content="自定义列" placement="top"
                                                   icon-name="el-icon-tickets"></OperationIcon>
                                  </template>
                                </el-popover>
                              </template>
                              <template slot-scope="scope">
                                <OperationIcon
                                  v-on:click="onEdit2(scope)"
                                  type="primary"
                                  content="编辑"
                                  placement="top"
                                  icon-name="el-icon-edit"
                                  class="dc-OperationIcon_IconButton"
                                ></OperationIcon>
                                <OperationIcon
                                  v-on:click="onDelete2(scope)"
                                  type="danger"
                                  content="删除"
                                  placement="top"
                                  icon-name="el-icon-delete"
                                  class="dc-OperationIcon_IconButton"
                                ></OperationIcon>
                              </template>
                            </ux-table-column>
            </ux-grid>
            <el-pagination
              :total="tableDataTotal2"
              :page-size="search2.limit"
              background
              :current-page="tableDataPage2"
              :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
              layout="total, sizes, prev, pager, next, jumper"
              v-on:size-change="onSizeChange"
              v-on:current-change="onCurrentChange"
              class="dc-el-pagination_ElPagination"
            ></el-pagination>
          </el-tab-pane>


          <el-tab-pane label="商机" name="tab3" class="dc-el-tab-pane_ElTabPane">
            <el-tooltip disabled class="dc-el-tooltip_Button">
              <el-button type="primary" v-on:click="tab3onCreate" icon="el-icon-add-location"
                class="dc-el-button_Button">新增
              </el-button>
            </el-tooltip>
            <el-tooltip disabled class="dc-el-tooltip_Button">
              <el-button type="primary" @click="handlePrice" icon="el-icon-add-location"
                  class="dc-el-button_Button">报价单
              </el-button>
            </el-tooltip>
            <ux-grid
              column-key
              ref="table"
              :data="tableData3"
              border
              @selection-change="handleRowClick"
              v-on:sort-change="onSortChange3"
              :tree-config="{ children: '', indent: 20, accordion: false, line: false, showIcon: true, iconOpen: '', iconClose: '' }"
              class="dc-ux-grid_QueryTable"
            >
              <ux-table-column
                :params="{ sortId: '141', summary: false }"
                type="checkbox"
                tree-node
                resizable
                min-width="80px"
                fixed="left"
                align="center"
                header-align="center"
                class="dc-ux-table-column_ElTableCheckBoxColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns3['129'].field"
                :title="tableColumns3['129'].title"
                :width="tableColumns3['129'].width"
                :visible="tableColumns3['129'].visible"
                :params="{ sortId: '129', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns3['130'].field"
                :title="tableColumns3['130'].title"
                :width="tableColumns3['130'].width"
                :visible="tableColumns3['130'].visible"
                :params="{ sortId: '130', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="right"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="{ row }">
                  ￥{{ row.oppPay.toFixed(2) || 0 }}
                </template>
              </ux-table-column>
              <ux-table-column
                :field="tableColumns3['131'].field"
                :title="tableColumns3['131'].title"
                :width="tableColumns3['131'].width"
                :visible="tableColumns3['131'].visible"
                :params="{ sortId: '131', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="{ row }">
                  {{ handleKeyValue(row.oppStage, oppStageOptions1) }}
                </template>
              </ux-table-column>
              <ux-table-column
                :field="tableColumns3['132'].field"
                :title="tableColumns3['132'].title"
                :width="tableColumns3['132'].width"
                :visible="tableColumns3['132'].visible"
                :params="{ sortId: '132', summary: false }"
                tree-node
                resizable
                min-width="100px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns3['133'].field"
                :title="tableColumns3['133'].title"
                :width="tableColumns3['133'].width"
                :visible="tableColumns3['133'].visible"
                :params="{ sortId: '133', summary: false }"
                tree-node
                resizable
                min-width="100px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns3['134'].field"
                :title="tableColumns3['134'].title"
                :width="tableColumns3['134'].width"
                :visible="tableColumns3['134'].visible"
                :params="{ sortId: '134', summary: false }"
                tree-node
                resizable
                min-width="80px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="{row}">
                  {{ row.isSendPriceSheet == '0' ? '未发送' : '已发送' }}
                </template>
              </ux-table-column>
              <ux-table-column
                :field="tableColumns3['135'].field"
                :title="tableColumns3['135'].title"
                :width="tableColumns3['135'].width"
                :visible="tableColumns3['135'].visible"
                :params="{ sortId: '135', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="scope">
                  {{ getCluesSources(scope.row.pdTypeId) }}<!--调用getChangeType方法-->
                </template>
              </ux-table-column>
              <ux-table-column
                title="操作"
                :params="{ sortId: '114' }"
                tree-node
                resizable
                width="140px"
                min-width="140px"
                fixed="right"
                align="center"
                header-align="center"
                class="dc-ux-table-column_ElTableOptColumn"
              >
                <template v-slot:header="scope">
                  <span>操作</span>
                  <el-popover ref="popover" width="10" trigger="click" placement="bottom" offset="10"
                              class="dc-el-popover_ElPopover">
                    <view-columns-select
                      v-model="tableColumns3"
                      ref="viewColumnsSelect"
                      v-on:save-column-view="saveColumn($event, 'table')"
                      v-on:show-all-column="showAllColumn($event, 'table')"
                      v-on:show-default-column="showDefaultColumn('table')"
                      class="dc-view-columns-select_ViewColumnsSelect"
                    ></view-columns-select>
                    <template slot="reference">
                      <OperationIcon type="primary" content="自定义列" placement="top"
                                     icon-name="el-icon-tickets"></OperationIcon>
                    </template>
                  </el-popover>
                  <OperationIcon
                    v-on:click="onExport(tableData)"
                    type="primary"
                    v-show="permission.export"
                    content="导出excel文件"
                    placement="top"
                    icon-name="el-icon-download"
                    class="dc-OperationIcon_IconButton"
                  ></OperationIcon>
                </template>
                <template slot-scope="scope">
                  <OperationIcon
                    v-on:click="onEdit3(scope)"
                    type="primary"
                    content="编辑"
                    placement="top"
                    icon-name="el-icon-edit"
                    class="dc-OperationIcon_IconButton"
                  ></OperationIcon>
                  <OperationIcon
                    v-on:click="onDelete3(scope)"
                    type="danger"
                    content="删除"
                    placement="top"
                    icon-name="el-icon-delete"
                    class="dc-OperationIcon_IconButton"
                  ></OperationIcon>
                </template>
              </ux-table-column>
            </ux-grid>
            <el-pagination
              :total="tableDataTotal3"
              :page-size="search3.limit"
              background
              :current-page="tableDataPage3"
              :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
              layout="total, sizes, prev, pager, next, jumper"
              v-on:size-change="onSizeChange"
              v-on:current-change="onCurrentChange"
              class="dc-el-pagination_ElPagination"
            ></el-pagination>
          </el-tab-pane>


          <el-tab-pane label="合同" name="tab4" class="dc-el-tab-pane_ElTabPane">
            <el-row gutter="10" type="flex" justify="end" align="top" class="dc-el-row_ElRow" style="top: 42px;z-index: 100">
              <process-panel ref="processPanel"
              :bizAdd="true" :tenantId="currentUser.company.id" keyPrefix="PROC_KH"
              procName="合同管理"
              formKey="wf/common/wfForm.vue#crmCustomer/crmCustomer/crmContractFlow.vue"
              procTitile="${start_user_name}发起${form.name}XX审批" v-on:v-loading="onVLoading"
              v-on:save-finished="getList4()"
              class="dc-process-panel_ProcessPanel"></process-panel>
            </el-row>
            <ux-grid
              column-key
              ref="table"
              :data="tableData4"
              style="margin-top: 30px"
              border
              v-on:sort-change="onSortChange4"
              :tree-config="{ children: '', indent: 20, accordion: false, line: false, showIcon: true, iconOpen: '', iconClose: '' }"
              class="dc-ux-grid_QueryTable"
            >
              <ux-table-column
                :field="tableColumns4['129'].field"
                :title="tableColumns4['129'].title"
                :width="tableColumns4['129'].width"
                :visible="tableColumns4['129'].visible"
                :params="{ sortId: '129', summary: false }"
                tree-node
                resizable
                width="100px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns4['130'].field"
                :title="tableColumns4['130'].title"
                :width="tableColumns4['130'].width"
                :visible="tableColumns4['130'].visible"
                :params="{ sortId: '130', summary: false }"
                tree-node
                resizable
                min-width="120px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns4['131'].field"
                :title="tableColumns4['131'].title"
                :width="tableColumns4['131'].width"
                :visible="tableColumns4['131'].visible"
                :params="{ sortId: '131', summary: false }"
                tree-node
                resizable
                min-width="120px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns4['132'].field"
                :title="tableColumns4['132'].title"
                :width="tableColumns4['132'].width"
                :visible="tableColumns4['132'].visible"
                :params="{ sortId: '132', summary: false }"
                tree-node
                resizable
                min-width="100px"
                align="right"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="{row}">
                  ￥{{ row.contractMoney }}
                </template>
              </ux-table-column>
              <ux-table-column
                :field="tableColumns4['133'].field"
                :title="tableColumns4['133'].title"
                :width="tableColumns4['133'].width"
                :visible="tableColumns4['133'].visible"
                :params="{ sortId: '133', summary: false }"
                tree-node
                resizable
                min-width="100px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
              </ux-table-column>
              <ux-table-column
                :field="tableColumns4['134'].field"
                :title="tableColumns4['134'].title"
                :width="tableColumns4['134'].width"
                :visible="tableColumns4['134'].visible"
                :params="{ sortId: '134', summary: false }"
                tree-node
                resizable
                width="100px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="{row}">
                  {{ handleKeyValue(row.contractStatus, ConTraStatus) }}
                </template>
              </ux-table-column>
              <ux-table-column
                :field="tableColumns4['135'].field"
                :title="tableColumns4['135'].title"
                :width="tableColumns4['135'].width"
                :visible="tableColumns4['135'].visible"
                :params="{ sortId: '135', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
              </ux-table-column>

              <ux-table-column
                :field="tableColumns4['136'].field"
                :title="tableColumns4['136'].title"
                :width="tableColumns4['136'].width"
                :visible="tableColumns4['136'].visible"
                :params="{ sortId: '136', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                title="操作"
                :params="{ sortId: '114' }"
                tree-node
                resizable
                width="140px"
                min-width="140px"
                fixed="right"
                align="center"
                header-align="center"
                class="dc-ux-table-column_ElTableOptColumn"
              >
                <template v-slot:header="scope">
                  <span>操作</span>
                  <el-popover ref="popover" width="10" trigger="click" placement="bottom" offset="10"
                              class="dc-el-popover_ElPopover">
                    <view-columns-select
                      v-model="tableColumns4"
                      ref="viewColumnsSelect"
                      v-on:save-column-view="saveColumn($event, 'table')"
                      v-on:show-all-column="showAllColumn($event, 'table')"
                      v-on:show-default-column="showDefaultColumn('table')"
                      class="dc-view-columns-select_ViewColumnsSelect"
                    ></view-columns-select>
                    <template slot="reference">
                      <OperationIcon type="primary" content="自定义列" placement="top"
                                     icon-name="el-icon-tickets"></OperationIcon>
                    </template>
                  </el-popover>
                </template>
                <template slot-scope="scope">
                  <OperationIcon
                    v-on:click="onDelete4(scope)"
                    type="danger"
                    content="删除"
                    placement="top"
                    icon-name="el-icon-delete"
                    class="dc-OperationIcon_IconButton"
                  ></OperationIcon>
                </template>
              </ux-table-column>
            </ux-grid>
            <el-pagination
              :total="tableDataTotal4"
              :page-size="search4.limit"
              background
              :current-page="tableDataPage4"
              :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
              layout="total, sizes, prev, pager, next, jumper"
              v-on:size-change="onSizeChange"
              v-on:current-change="onCurrentChange"
              class="dc-el-pagination_ElPagination"
            ></el-pagination>
          </el-tab-pane>


          <el-tab-pane label="回款计划" name="tab5" class="dc-el-tab-pane_ElTabPane">
            <el-tooltip disabled class="dc-el-tooltip_Button">
              <!-- <el-button type="primary" v-on:click="tab5onCreate" icon="el-icon-add-location"
                         class="dc-el-button_Button">新增
              </el-button> -->
            </el-tooltip>
            <ux-grid
              column-key
              ref="table"
              :data="tableData5"
              border
              v-on:sort-change="onSortChange5"
              :tree-config="{ children: '', indent: 20, accordion: false, line: false, showIcon: true, iconOpen: '', iconClose: '' }"
              class="dc-ux-grid_QueryTable"
            >
              <ux-table-column
                :field="tableColumns5['129'].field"
                :title="tableColumns5['129'].title"
                :width="tableColumns5['129'].width"
                :visible="tableColumns5['129'].visible"
                :params="{ sortId: '129', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns5['130'].field"
                :title="tableColumns5['130'].title"
                :width="tableColumns5['130'].width"
                :visible="tableColumns5['130'].visible"
                :params="{ sortId: '130', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns5['131'].field"
                :title="tableColumns5['131'].title"
                :width="tableColumns5['131'].width"
                :visible="tableColumns5['131'].visible"
                :params="{ sortId: '131', summary: false }"
                tree-node
                resizable
                min-width="120px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns5['132'].field"
                :title="tableColumns5['132'].title"
                :width="tableColumns5['132'].width"
                :visible="tableColumns5['132'].visible"
                :params="{ sortId: '132', summary: false }"
                tree-node
                resizable
                min-width="100px"
                align="right"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="{ row }">
                  ￥{{ row.contractAmount || 0 }}
                </template>
              </ux-table-column>
              <ux-table-column
                :field="tableColumns5['133'].field"
                :title="tableColumns5['133'].title"
                :width="tableColumns5['133'].width"
                :visible="tableColumns5['133'].visible"
                :params="{ sortId: '133', summary: false }"
                tree-node
                resizable
                min-width="100px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns5['134'].field"
                :title="tableColumns5['134'].title"
                :width="tableColumns5['134'].width"
                :visible="tableColumns5['134'].visible"
                :params="{ sortId: '134', summary: false }"
                tree-node
                resizable
                min-width="80px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns5['135'].field"
                :title="tableColumns5['135'].title"
                :width="tableColumns5['135'].width"
                :visible="tableColumns5['135'].visible"
                :params="{ sortId: '135', summary: false }"
                tree-node
                resizable
                width="120px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
              </ux-table-column>
              <ux-table-column
                :field="tableColumns5['136'].field"
                :title="tableColumns5['136'].title"
                :width="tableColumns5['136'].width"
                :visible="tableColumns5['136'].visible"
                :params="{ sortId: '136', summary: false }"
                tree-node
                resizable
                min-width="130px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="{ row }">
                  ￥{{ row.oddPaymentBackAmount || 0 }}
                </template>
              </ux-table-column>
              <ux-table-column
                :field="tableColumns5['137'].field"
                :title="tableColumns5['137'].title"
                :width="tableColumns5['137'].width"
                :visible="tableColumns5['137'].visible"
                :params="{ sortId: '137', summary: false }"
                tree-node
                resizable
                width="100px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns5['138'].field"
                :title="tableColumns5['138'].title"
                :width="tableColumns5['138'].width"
                :visible="tableColumns5['138'].visible"
                :params="{ sortId: '138', summary: false }"
                tree-node
                resizable
                min-width="120px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
              <template slot-scope="{ row }">
                  ￥{{ row.newPaymentBackAmount || 0 }}
                </template>
              </ux-table-column>
              <ux-table-column
                :field="tableColumns5['139'].field"
                :title="tableColumns5['139'].title"
                :width="tableColumns5['139'].width"
                :visible="tableColumns5['139'].visible"
                :params="{ sortId: '139', summary: false }"
                tree-node
                resizable
                width="100px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>

              <ux-table-column
                title="操作"
                :params="{ sortId: '114' }"
                tree-node
                resizable
                width="140px"
                min-width="140px"
                fixed="right"
                align="center"
                header-align="center"
                class="dc-ux-table-column_ElTableOptColumn"
              >
                <template v-slot:header="scope">
                  <span>操作</span>
                  <el-popover ref="popover" width="10" trigger="click" placement="bottom" offset="10"
                              class="dc-el-popover_ElPopover">
                    <view-columns-select
                      v-model="tableColumns5"
                      ref="viewColumnsSelect"
                      v-on:save-column-view="saveColumn($event, 'table')"
                      v-on:show-all-column="showAllColumn($event, 'table')"
                      v-on:show-default-column="showDefaultColumn('table')"
                      class="dc-view-columns-select_ViewColumnsSelect"
                    ></view-columns-select>
                    <template slot="reference">
                      <OperationIcon type="primary" content="自定义列" placement="top"
                                     icon-name="el-icon-tickets"></OperationIcon>
                    </template>
                  </el-popover>
                </template>
                <template slot-scope="scope">
                  <OperationIcon
                    v-on:click="onView1(scope)"
                    type="info"
                    v-show="permission.view"
                    content="查看"
                    placement="top"
                    icon-name="el-icon-view"
                    class="dc-OperationIcon_IconButton"
                  ></OperationIcon>
                  <OperationIcon
                    v-on:click="onEdit5(scope)"
                    type="primary"
                    content="编辑"
                    placement="top"
                    icon-name="el-icon-edit"
                    class="dc-OperationIcon_IconButton"
                  ></OperationIcon>
                  <!-- <OperationIcon
                    v-on:click="onDelete5(scope)"
                    type="danger"
                    content="删除"
                    placement="top"
                    icon-name="el-icon-delete"
                    class="dc-OperationIcon_IconButton"
                  ></OperationIcon> -->
                </template>
              </ux-table-column>
            </ux-grid>

            <el-pagination
              :total="tableDataTotal5"
              :page-size="search5.limit"
              background
              :current-page="tableDataPage5"
              :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
              layout="total, sizes, prev, pager, next, jumper"
              v-on:size-change="onSizeChange"
              v-on:current-change="onCurrentChange"
              class="dc-el-pagination_ElPagination"
            ></el-pagination>

          </el-tab-pane>


          <el-tab-pane label="联系人" name="tab6" class="dc-el-tab-pane_ElTabPane">
            <el-tooltip disabled class="dc-el-tooltip_Button">
              <el-button type="primary" v-on:click="tab6onCreate" icon="el-icon-add-location"
                         class="dc-el-button_Button">新增
              </el-button>
            </el-tooltip>
            <ux-grid
              column-key
              ref="table"
              :data="tableData6"
              border
              v-on:sort-change="onSortChange6"
              :tree-config="{ children: '', indent: 20, accordion: false, line: false, showIcon: true, iconOpen: '', iconClose: '' }"
              class="dc-ux-grid_QueryTable"
            >
              <ux-table-column
                :field="tableColumns6['129'].field"
                :title="tableColumns6['129'].title"
                :width="tableColumns6['129'].width"
                :visible="tableColumns6['129'].visible"
                :params="{ sortId: '129', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns6['130'].field"
                :title="tableColumns6['130'].title"
                :width="tableColumns6['130'].width"
                :visible="tableColumns6['130'].visible"
                :params="{ sortId: '130', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns6['131'].field"
                :title="tableColumns6['131'].title"
                :width="tableColumns6['131'].width"
                :visible="tableColumns6['131'].visible"
                :params="{ sortId: '131', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns6['132'].field"
                :title="tableColumns6['132'].title"
                :width="tableColumns6['132'].width"
                :visible="tableColumns6['132'].visible"
                :params="{ sortId: '132', summary: false }"
                tree-node
                resizable
                min-width="100px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns6['133'].field"
                :title="tableColumns6['133'].title"
                :width="tableColumns6['133'].width"
                :visible="tableColumns6['133'].visible"
                :params="{ sortId: '133', summary: false }"
                tree-node
                resizable
                min-width="100px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns6['134'].field"
                :title="tableColumns6['134'].title"
                :width="tableColumns6['134'].width"
                :visible="tableColumns6['134'].visible"
                :params="{ sortId: '134', summary: false }"
                tree-node
                resizable
                min-width="80px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns6['135'].field"
                :title="tableColumns6['135'].title"
                :width="tableColumns6['135'].width"
                :visible="tableColumns6['135'].visible"
                :params="{ sortId: '135', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
              </ux-table-column>
              <ux-table-column
                :field="tableColumns6['136'].field"
                :title="tableColumns6['136'].title"
                :width="tableColumns6['136'].width"
                :visible="tableColumns6['136'].visible"
                :params="{ sortId: '136', summary: false }"
                tree-node
                resizable
                min-width="70px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns6['137'].field"
                :title="tableColumns6['137'].title"
                :width="tableColumns6['137'].width"
                :visible="tableColumns6['137'].visible"
                :params="{ sortId: '137', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns6['138'].field"
                :title="tableColumns6['138'].title"
                :width="tableColumns6['138'].width"
                :visible="tableColumns6['138'].visible"
                :params="{ sortId: '138', summary: false }"
                tree-node
                resizable
                min-width="160px"
                align="center"
                header-align="center"
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                title="操作"
                :params="{ sortId: '114' }"
                tree-node
                resizable
                width="140px"
                min-width="140px"
                fixed="right"
                align="center"
                header-align="center"
                class="dc-ux-table-column_ElTableOptColumn"
              >
                <template v-slot:header="scope">
                  <span>操作</span>
                  <el-popover ref="popover" width="10" trigger="click" placement="bottom" offset="10"
                              class="dc-el-popover_ElPopover">
                    <view-columns-select
                      v-model="tableColumns6"
                      ref="viewColumnsSelect"
                      v-on:save-column-view="saveColumn($event, 'table')"
                      v-on:show-all-column="showAllColumn($event, 'table')"
                      v-on:show-default-column="showDefaultColumn('table')"
                      class="dc-view-columns-select_ViewColumnsSelect"
                    ></view-columns-select>
                    <template slot="reference">
                      <OperationIcon type="primary" content="自定义列" placement="top"
                                     icon-name="el-icon-tickets"></OperationIcon>
                    </template>
                  </el-popover>
                </template>
                <template slot-scope="scope">
                  <OperationIcon
                    v-on:click="onEdit6(scope)"
                    type="primary"
                    content="编辑"
                    placement="top"
                    icon-name="el-icon-edit"
                    class="dc-OperationIcon_IconButton"
                  ></OperationIcon>
                  <OperationIcon
                    v-on:click="onDelete6(scope)"
                    type="danger"
                    content="删除"
                    placement="top"
                    icon-name="el-icon-delete"
                    class="dc-OperationIcon_IconButton"
                  ></OperationIcon>
                </template>
              </ux-table-column>
            </ux-grid>

            <el-pagination
              :total="tableDataTotal6"
              :page-size="search6.limit"
              background
              :current-page="tableDataPage6"
              :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
              layout="total, sizes, prev, pager, next, jumper"
              v-on:size-change="onSizeChange"
              v-on:current-change="onCurrentChange"
              class="dc-el-pagination_ElPagination"
            ></el-pagination>

          </el-tab-pane>


        </el-tabs>
      </el-row>
    </el-dialog>


    <el-dialog
      v-loading="loading"
      width="70%"
      :title="dialogPropstap1.title"
      :visible.sync="dialogPropstap1.visible"
      class="dc-el-dialog_ElDialog"
    >
      <el-row>
        <el-form ref="editForm216" :model="editForm216Data" label-width="100px" class="dc-el-form_ElEditForm">
          <el-row>
            <el-col :span="12">
              <el-form-item
                prop=""
                label="沟通类型"
                :rules="[{ required: true, message: '沟通类型不能为空', trigger: 'change' }]"
                class="dc-el-form-item_SelectInput"
              >
                <el-select
                  v-model="editForm216Data.ac"
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
            </el-col>
            <el-col :span="12">
              <el-form-item
                prop=""
                label="沟通人姓名"
                :rules="[{ required: true, message: '沟通人姓名不能为空', trigger: 'blur' }]"
                class="dc-el-form-item_SingleInput"
              >
                <el-input
                  v-model="editForm216Data.ac"
                  :maxLength="30"
                  placeholder="请输入"
                  clearable
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                prop=""
                label="沟通时间"
                :rules="[{ required: true, message: '沟通时间不能为空', trigger: 'change' }]"
                class="dc-el-form-item_PickerTimeDate"
              >
                <el-date-picker
                  v-model="editForm216Data.ac"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  type="datetime"
                  format="yyyy-MM-dd HH:mm:ss"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  class="dc-el-date-picker_PickerTimeDate"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item
                prop=""
                label="沟通内容"
                :rules="[{ required: true, message: '沟通内容不能为空', trigger: 'blur' }]"
                class="dc-el-form-item_MutilpleInput"
              >
                <el-input
                  v-model="editForm216Data.ac"
                  type="textarea"
                  placeholder="请输入"
                  rows="2"
                  :maxLength="100"
                  class="dc-el-input_MutilpleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <upload-file
                title="附件上传"
                :action="action"
                :objectId="editForm216Data.null"
                :fileFormats="['doc', 'docx', 'pdf', 'jpg', 'jpeg', 'png']"
                :fileSizes="10"
                v-model="editForm216Data.nullFile"
                class="dc-upload-file_ElUploadFile"
              ></upload-file>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="null" class="dc-el-form-item_ElCheckbox">
                <el-checkbox v-model="editForm216Data.null" class="dc-el-checkbox_ElCheckbox">
                  是否为下次跟进创建任务
                </el-checkbox>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                prop=""
                label="沟通时间"
                :rules="[{ required: true, message: '沟通时间不能为空', trigger: 'change' }]"
                class="dc-el-form-item_PickerTimeDate"
              >
                <el-date-picker
                  v-model="editForm216Data.ac"
                  :style="{ width: '100%' }"
                  placeholder="请选择"
                  type="datetime"
                  format="yyyy-MM-dd HH:mm:ss"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  class="dc-el-date-picker_PickerTimeDate"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item prop="" label="沟通时间" class="dc-el-form-item_CounterInput">
                <el-input-number
                  v-model="editForm216Data.ac"
                  :min="0"
                  :max="999"
                  :step="1"
                  :precision="0"
                  :style="{ width: '100%' }"
                  class="dc-el-input-number_CounterInput"
                ></el-input-number>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item prop="" class="dc-el-form-item_SelectInput">
                <el-select
                  v-model="editForm216Data.ac"
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
            </el-col>
          </el-row>
        </el-form>
      </el-row>
      <template slot="reference"></template>
    </el-dialog>
  </el-row>
</template>
<script>
import {validatenull} from '@/utils/validate'
/** 根据用户界面配置import组件 结束 */
import {listDictItemAll, listDictItemPage} from '@/api/sys/dictItem.js'
import uploadFile from '@/views/components/uploadFile'
/** 根据用户界面配置import组件 结束 */
import {getCrmCustomerById, saveCrmCustomer} from '@/api/crmCustomer/crmCustomer'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'

import {listCrmCustomerCntPage, deleteCrmCustomerCnt} from '@/api/crmCustomer/crmCustomerCnt'
import {listCrmCustomerProductsPage, deleteCrmCustomerProducts} from '@/api/crmCustomer/crmCustomerProducts'
import {listCrmCustomerCotPage, deleteCrmCustomerCot} from '@/api/crmCustomer/crmCustomerCot'
import {listCrmCustomerLinkPage, deleteCrmCustomerLink} from '@/api/crmCustomer/crmCustomerLink'
import {listCrmCustomerOppPage, deleteCrmCustomerOpp} from '@/api/crmCustomer/crmCustomerOpp'
import { listPaymentBackPage } from '@/api/paymentBack/paymentBack'
import {listCrmCustomerPayPage, deleteCrmCustomerPay} from '@/api/crmCustomer/crmCustomerPay'
import processPanel from "@/views/wf/common/processPanel"

import EditForm from "./form.vue";
import top6EditForm from "../crmCustomerLink/form.vue";
import top2EditForm from "../crmCustomerProducts/form.vue";
import top1EditForm from "../crmCustomerCnt/form.vue";
import top4EditForm from "../crmCustomerCot/form.vue";
import top3EditForm from "../crmCustomerOpp/form.vue";
import top5EditForm from "../crmCustomerPay/form.vue";
import top7EditForm from '../crmCustomerOpp/price.vue';
import { getDictTypeById } from '@/api/sys/dictType'

export default {
  extends: BaseUI,
  name: 'crmClues-form',
  components: {
    /** 根据用户界面配置组件 开始 */
    uploadFile,
    /** 根据用户界面配置组件 结束 */
    EditForm,
    processPanel,
    top7EditForm,
    top1EditForm,
    top2EditForm,
    top3EditForm,
    top4EditForm,
    top5EditForm,
    top6EditForm,
    OperationIcon
  },
  data() {
    return {
      productType: [],
      //意向产品
      // 查询表格列头
      tableColumns2: {
        129: {
          title: '产品编号',
          field: 'pdNo',
          visible: true,
          order: 0
        },
        130: {
          title: '产品名称',
          field: 'pdName',
          visible: true,
          order: 1
        },
        131: {
          title: '产品分类',
          field: 'pdClassify.pdcName',
          visible: true,
          order: 2
        },
        132: {
          title: '指导价',
          field: 'pdGuidePrice',
          visible: true,
          order: 3
        },
        133: {
          title: '成本价',
          field: 'pdCostPrice',
          visible: true,
          order: 4
        },

        134: {
          title: '产品类型',
          field: 'pdTypeId',
          visible: true,
          order: 6
        },
        135: {
          title: '备注信息',
          field: 'remarks',
          visible: true,
          order: 7
        }
      },
      tableColumns3: {
        141: {
          visible: true
        },
        129: {
          title: '商机名称',
          field: 'oppName',
          visible: true,
          order: 0
        },
        130: {
          title: '销售金额',
          field: 'oppPay',
          visible: true,
          order: 1
        },
        131: {
          title: '销售阶段',
          field: 'oppStage',
          visible: true,
          order: 2
        },
        132: {
          title: '商机来源',
          field: 'oppSource',
          visible: true,
          order: 3
        },
        133: {
          title: '产品数量',
          field: 'productCount',
          visible: true,
          order: 4
        },
        134: {
          title: '是否已发送报价单',
          field: 'isSendPriceSheet',
          visible: true,
          order: 5
        },
        135: {
          title: '备注信息',
          field: 'remarks',
          visible: false,
          order: 12
        }
      },
      tableColumns4: {
        129: {
          title: '合同编号',
          field: 'contractCode',
          visible: true,
          order: 0
        },
        130: {
          title: '合同名称',
          field: 'contractName',
          visible: true,
          order: 1
        },
        131: {
          title: '商机名称',
          field: 'oppName',
          visible: true,
          order: 2
        },
        132: {
          title: '合同金额',
          field: 'contractMoney',
          visible: true,
          order: 3
        },
        133: {
          title: '签约日期',
          field: 'sginDate',
          visible: true,
          order: 4
        },
        134: {
          title: '合同状态',
          field: 'contractStatus',
          visible: true,
          order: 5
        },
        135: {
          title: '创建人',
          field: 'createBy',
          visible: true,
          order: 6
        },
        136: {
          title: '创建时间',
          field: 'createDate',
          visible: true,
          order: 7
        }
      },
      tableColumns5: {
        129: {
          title: '回款计划编号',
          field: 'paymentBackPlanNo',
          visible: true,
          order: 0
        },
        130: {
          title: '关联合同编号',
          field: 'contractCode',
          visible: true,
          order: 1
        },
        131: {
          title: '合同名称',
          field: 'contractName',
          visible: true,
          order: 2
        },
        132: {
          title: '合同金额',
          field: 'contractAmount',
          visible: true,
          order: 3
        },
        133: {
          title: '付款周期',
          field: 'contractpPay',
          visible: false,
          order: 3
        },
        134: {
          title: '付款比例',
          field: 'contractpPay',
          visible: false,
          order: 3
        },
        135: {
          title: '结束日期',
          field: 'endDate',
          visible: true,
          order: 4
        },
        136: {
          title: '剩余回款金额',
          field: 'oddPaymentBackAmount',
          visible: true,
          order: 5
        },
        137: {
          title: '最新回款日期',
          field: 'newPaymentBackDate',
          visible: true,
          order: 6
        },
        138: {
          title: '最新回款金额',
          field: 'newPaymentBackAmount',
          visible: true,
          order: 7
        },
        139: {
          title: '回款状态',
          field: 'paymentBackStatus',
          visible: true,
          order: 8
        }
      },
      tableColumns6: {
        129: {
          title: '联系人姓名',
          field: 'link.contactsName',
          visible: true,
          order: 0
        },
        130: {
          title: '商机',
          field: 'link.bsoy',
          visible: true,
          order: 1
        },
        131: {
          title: '所属部门',
          field: 'link.depart',
          visible: true,
          order: 2
        },
        132: {
          title: '职位',
          field: 'link.workPost',
          visible: true,
          order: 3
        },
        133: {
          title: '电话',
          field: 'link.contactPhone',
          visible: true,
          order: 4
        },
        134: {
          title: '手机号',
          field: 'link.phoneNumber',
          visible: true,
          order: 5
        },
        135: {
          title: '邮箱',
          field: 'link.email',
          visible: true,
          order: 6
        },
        136: {
          title: '所在区域',
          field: 'link.region',
          visible: true,
          order: 7
        },
        137: {
          title: '详细地址',
          field: 'link.address',
          visible: false,
          order: 8
        },
        138: {
          title: '备注',
          field: 'link.notes',
          visible: false,
          order: 9
        }
      },
      ConTraStatus: [
        {value: '0', name: '已保存'},
        {value: '1', name: '审批中'},
        {value: '2', name: '审批通过'},
        {value: '9', name: '审批不通过'}
      ],
      selections: [],
      //沟通记录
      tableData: [],
      tableData2: [],
      tableData3: [],
      tableData4: [],
      tableData5: [],
      tableData6: [],
      // 查询表格列头
      tableColumns: {
        129: {
          title: '沟通类型',
          field: 'cntType',
          visible: true,
          order: 2
        },
        130: {
          title: '沟通人姓名',
          field: 'cntPname',
          visible: true,
          order: 3
        },
        131: {
          title: '沟通时间',
          field: 'cntDate',
          visible: true,
          order: 4
        },
        132: {
          title: '沟通内容',
          field: 'cntContent',
          visible: true,
          order: 5
        },
        138: {
          title: '下次沟通时间',
          field: 'cutNextDate',
          visible: true,
          order: 6
        },
        139: {
          title: '跟进提醒',
          field: 'cutRemind',
          visible: true,
          order: 7
        },
        133: {
          title: '创建者',
          field: 'createBy',
          visible: false,
          order: 8
        },
        134: {
          title: '创建时间',
          field: 'createDate',
          visible: false,
          order: 9
        },
        135: {
          title: '跟进人',
          field: 'updateBy',
          visible: false,
          order: 10
        },
        136: {
          title: '跟进进度',
          field: 'updateDate',
          visible: false,
          order: 11
        },
        137: {
          title: '备注信息',
          field: 'remarks',
          visible: false,
          order: 12
        },
        114: {
          title: '操作',
          width: '140px',
          visible: true,
          order: 13
        }
      },
      // 分页属性
      tableDataPage: 1,
      tableDataTotal: 0,
      // 分页属性
      tableDataPage2: 1,
      tableDataTotal2: 0,
      // 分页属性
      tableDataPage3: 1,
      tableDataTotal3: 0,
      // 分页属性
      tableDataPage4: 1,
      tableDataTotal4: 0,
      // 分页属性
      tableDataPage5: 1,
      tableDataTotal5: 0,
      // 分页属性
      tableDataPage6: 1,
      tableDataTotal6: 0,
      // 选项变量
      /** 根据用户界面配置生成data数据 结束 */
      version: 2,
      search: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
      search2: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
      search3: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
      search4: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
      search5: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
      search6: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
      searchList: [],
      tableId: '1720018472291172356',
      schemeId: '1720018472291172354',
      // 选项变量
      fromId: "",
      /** 根据用户界面配置生成data数据 开始 */
      editFormData: this.initEditData(),
      // 对话框属性变量
      dialogProps: {
        visible: false,
        title: '我的线索'
      },
      dialogPropstap1: {
        visible: false,
        title: '沟通记录'
      },
      readonlys: false,
      dialogTitle: '对话框标题',
      editForm128Data: {
        cluesName: '', // 线索名称
        parent: {
          cusName: ''
        },
        cluesPool: {
          id: null,
          cplName: null
        },
        cluesState: '', // 线索状态
        cluesContacts: '', // 线索联系人
        gender: '', // 性别
        phoneNumber: '', // 手机号
        birthDate: '', // 出生日期
        telephone: '', // 电话
        email: '', // 邮箱
        microblog: '', // 微博
        qq: '', // QQ
        wechat: '', // 微信
        cluesSources: '', // 线索来源
        region: '', // 所在区域
        addr: '', // 详细地址
        company: '', // 公司
        department: '', // 所属部门
        position: '', // 职位
        // position: '', // 最新跟进人
        // position: '', // 最新跟进时间
        remarks: '' // 备注
      },
      // 线索状态选项
      cluesStateOptions: [],
      table196Data: [],
      // 查询表格列头

      editForm216Data: {},
      table250Data: [],
      // 选项变量
      // 所属线索池选项
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
      tabName: 'editForm', // tab标签页
      // 窗口操作类型 view/edit/add
      action: '',
      activeName: 'tab1',
      selectOptions1: [],
      custmerSource: [],
      oppStageOptions1: [],
      customeType: ''
    }
  },
  props: {
    // 权限
    permission: {
      type: Object
    }
  },
  methods: {
    handleKeyValue (v, arr) {
      const current = arr.findIndex(item => item.value === v)
      return (current > -1 && arr[current].name) || ''
    },
    handleGetOptions () {
      // 客户阶段
      getDictTypeById('1705054496995033199').then(res => {
        if (res.code === '100') {
          this.selectOptions1 = res.data.dictItemList
        }
      })
      // 客户来源
      getDictTypeById('1705054496995033113').then(res => {
        if (res.code === '100') {
          this.custmerSource = res.data.dictItemList
        }
      })
      // 客户类型
      getDictTypeById('1705054496995033109').then(res => {
        if (res.code === '100') {
          this.customeType = res.data.dictItemList
        }
      })
      //
      getDictTypeById('1705054496995033156').then(res => {
        if (res.code === '100') {
          this.selectOptions = res.data.dictItemList
        }
      })
      // 产品类型
      let search_List = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "CRM_DICT_PRODUCT_TYPE"}]
      }
      listDictItemAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.productType = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
      // 销售阶段
      let search_List1 = {
        params: []
      }
      search_List1.params.push({'columnName': 'dictType.code', 'queryType': '=', 'value': "CRM_DICT_OPPS_STAGE"})
      listDictItemPage(search_List1).then((res) => {
        if (res.code == 100) {
          this.oppStageOptions1 = res.data.rows;
        }
      })
    },
    listCluesStateOptions() {
      let search_List = {
        params: [{'columnName': 'dictType.code', 'queryType': '=', 'value': "CRM_DICT_PRODUCT_TYPE"}]
      }
      listDictItemAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.cluesSourcesOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
    },
    getCluesSources(e) {

      for (var i = 0; i < this.cluesSourcesOptions.length; i++) {
        if (this.cluesSourcesOptions[i].id == e) { //dictValue，dictLabel保持和上面定义一致
          return this.cluesSourcesOptions[i].name;
        }
      }
    },
    handleClick(tab, event) {
      if (this.activeName == 'tab1') {

        this.getList()
      } else if (this.activeName == 'tab2') {

        this.getList2()
      } else if (this.activeName == 'tab3') {

        this.getList3()
      } else if (this.activeName == 'tab4') {

        this.getList4()
      } else if (this.activeName == 'tab5') {

        this.getList5()
      } else if (this.activeName == 'tab6') {

        this.getList6()
      }
    },
    //意向产品
    getList2() {
      this.setLoad()
      /* 查询参数 和数据权限 */
      this.search2.params = []
      if (this.isQueryConditionPanel) {
        this.search2.params = this.search2.params.concat(this.compositeCondition())
      } else {
      }
      // 数据权限: 我的线索意向产品crm_crmCustomer_products

      // this.pushDataPermissions(this.search2.params, this.$route.meta.routerId, this.tableId)
      this.search2.params.push({'columnName': 'cus_id', 'queryType': '=', 'value': this.fromId});
      listCrmCustomerProductsPage(this.search2)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.tableDataTotal2 = responseData.data.total
            this.tableData2 = responseData.data.rows ? responseData.data.rows : []
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
//意向产品
    getList3() {
      this.setLoad()
      /* 查询参数 和数据权限 */
      this.search3.params = []
      if (this.isQueryConditionPanel) {
        this.search3.params = this.search3.params.concat(this.compositeCondition())
      } else {
      }
      // 数据权限: 我的线索意向产品crm_crmCustomer_products
      // this.pushDataPermissions(this.search3.params, this.$route.meta.routerId, this.tableId)
      this.search3.params.push({'columnName': 'cus_id', 'queryType': '=', 'value': this.fromId});
      listCrmCustomerOppPage(this.search3)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.tableDataTotal3 = responseData.data.total
            this.tableData3 = responseData.data.rows ? responseData.data.rows : []
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    //意向产品
    getList4() {
      this.setLoad()
      /* 查询参数 和数据权限 */
      this.search4.params = []
      if (this.isQueryConditionPanel) {
        this.search4.params = this.search4.params.concat(this.compositeCondition())
      } else {
      }
      // 数据权限: 我的线索意向产品crm_crmCustomer_products
      // this.pushDataPermissions(this.search4.params, this.$route.meta.routerId, this.tableId)
      this.search4.params.push({'columnName': 'cus_id', 'queryType': '=', 'value': this.fromId});
      listCrmCustomerCotPage(this.search4)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.tableDataTotal4 = responseData.data.total
            this.tableData4 = responseData.data.rows ? responseData.data.rows : []
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    onView(scope) {
      this.setLoad()
      getHistProcInstance(scope.row.procInst).then(responseData => {
        if (responseData.hasOwnProperty('processDefinitionId')) {
          let definitionId = responseData.processDefinitionId
          if (definitionId) {
            getStartForm(definitionId).then(startFormResp => {
              if (startFormResp.hasOwnProperty('key')) {
                if (startFormResp.key) {
                  this.currentRow = {
                    formKey: startFormResp.key,
                    definitionId: definitionId,
                    row: scope.row
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
              this.resetLoad()
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
        this.resetLoad()
      }).catch(error => {
        this.outputError(error)
      })
    },
    //意向产品
    getList5() {
      this.setLoad()
      /* 查询参数 和数据权限 */
      this.search5.params = []
      if (this.isQueryConditionPanel) {
        this.search5.params = this.search5.params.concat(this.compositeCondition())
      } else {
      }
      // 数据权限: 我的线索意向产品crm_crmCustomer_products
      // this.pushDataPermissions(this.search5.params, this.$route.meta.routerId, this.tableId)
      this.search5.params.push({'columnName': 'customer_id', 'queryType': '=', 'value': this.fromId});
      listPaymentBackPage({
        searchParams: this.search5
      })
        .then((responseData) => {
          if (responseData.code == 100) {
            this.tableDataTotal5 = responseData.data.total
            this.tableData5 = responseData.data.rows ? responseData.data.rows : []
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },//意向产品
    getList6() {
      this.setLoad()
      /* 查询参数 和数据权限 */
      this.search6.params = []
      if (this.isQueryConditionPanel) {
        this.search6.params = this.search6.params.concat(this.compositeCondition())
      } else {
      }
      // 数据权限: 我的线索意向产品crm_crmCustomer_products
      // this.pushDataPermissions(this.search6.params, this.$route.meta.routerId, this.tableId)
      this.search6.params.push({'columnName': 'cus_id', 'queryType': '=', 'value': this.fromId});
      listCrmCustomerLinkPage(this.search6)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.tableDataTotal6 = responseData.data.total
            this.tableData6 = responseData.data.rows ? responseData.data.rows : []
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    //沟通记录
    onDelete(scope) {
      this.$confirm('确定删除吗？', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.setLoad()
          deleteCrmCustomerCnt(scope.row)
            .then((responseData) => {
              if (responseData.code == 100) {
                this.getList()
                this.showMessage({
                  type: 'success',
                  msg: '删除成功'
                })
              } else {
                this.showMessage(responseData)
              }
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        })
        .catch(() => {
        })
    },

    onDelete2(scope) {
      this.$confirm('确定删除吗？', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.setLoad()
          deleteCrmCustomerProducts(scope.row)
            .then((responseData) => {
              if (responseData.code == 100) {
                this.getList2()
                this.showMessage({
                  type: 'success',
                  msg: '删除成功'
                })
              } else {
                this.showMessage(responseData)
              }
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        })
        .catch(() => {
        })
    },

    onDelete3(scope) {
      this.$confirm('确定删除吗？', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.setLoad()
          deleteCrmCustomerOpp(scope.row)
            .then((responseData) => {
              if (responseData.code == 100) {
                this.getList3()
                this.showMessage({
                  type: 'success',
                  msg: '删除成功'
                })
              } else {
                this.showMessage(responseData)
              }
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        })
        .catch(() => {
        })
    },

    onDelete4(scope) {
      this.$confirm('确定删除吗？', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.setLoad()
          deleteCrmCustomerCot(scope.row)
            .then((responseData) => {
              if (responseData.code == 100) {
                this.getList4()
                this.showMessage({
                  type: 'success',
                  msg: '删除成功'
                })
              } else {
                this.showMessage(responseData)
              }
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        })
        .catch(() => {
        })
    },

    onDelete5(scope) {
      this.$confirm('确定删除吗？', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.setLoad()
          deleteCrmCustomerPay(scope.row)
            .then((responseData) => {
              if (responseData.code == 100) {
                this.getList5()
                this.showMessage({
                  type: 'success',
                  msg: '删除成功'
                })
              } else {
                this.showMessage(responseData)
              }
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        })
        .catch(() => {
        })
    },

    onDelete6(scope) {
      this.$confirm('确定删除吗？', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.setLoad()
          deleteCrmCustomerLink(scope.row)
            .then((responseData) => {
              if (responseData.code == 100) {
                this.getList6()
                this.showMessage({
                  type: 'success',
                  msg: '删除成功'
                })
              } else {
                this.showMessage(responseData)
              }
              this.resetLoad()
            })
            .catch((error) => {
              this.outputError(error)
            })
        })
        .catch(() => {
        })
    },

    onEdit6(scope) {
      this.$refs.top6EditForm.$emit('openEditDialog', scope.row)
    },

    onEdit5(scope) {
      this.$refs.top5EditForm.$emit('openEditDialog', scope.row.contractId)
    },
    onView1 (scope) {
      this.$refs.top5EditForm.$emit('openViewDialog', scope.row.contractId)
    },
    onEdit4(scope) {
      this.$refs.top4EditForm.$emit('openEditDialog', scope.row.cotId)
    },

    onEdit3(scope) {
      this.$refs.top3EditForm.$emit('openEditDialog', scope.row.oppId)
    },

    onEdit2(scope) {
      this.$refs.top2EditForm.$emit('openEditDialog', scope.row.id)
    },

    onEdit1(scope) {
      this.$refs.top1EditForm.$emit('openEditDialog', scope.row)
    },

    getList() {
      this.setLoad()
      /* 查询参数 和数据权限 */
      this.search.params = []
      if (this.isQueryConditionPanel) {
        this.search.params = this.search.params.concat(this.compositeCondition())
      } else {
      }
      // 数据权限: 沟通crm_communicate
      // this.pushDataPermissions(this.search.params, this.$route.meta.routerId, this.tableId)
      this.search.params.push({'columnName': 'cus_id', 'queryType': '=', 'value': this.fromId});
      listCrmCustomerCntPage(this.search)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.tableDataTotal = responseData.data.total
            this.tableData = responseData.data.rows ? responseData.data.rows : []
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    onSortChange(orderby) {
      if (validatenull(orderby.prop)) {
        this.search.columnName = ''
        this.search.order = ''
      } else {
        this.search.columnName = orderby.prop
        this.search.order = orderby.order
      }
      this.getList()
    },
    onSortChange2(orderby) {
      if (validatenull(orderby.prop)) {
        this.search2.columnName = ''
        this.search2.order = ''
      } else {
        this.search2.columnName = orderby.prop
        this.search2.order = orderby.order
      }
      this.getList2()
    },
    onSortChange3(orderby) {
      if (validatenull(orderby.prop)) {
        this.search3.columnName = ''
        this.search3.order = ''
      } else {
        this.search3.columnName = orderby.prop
        this.search3.order = orderby.order
      }
      this.getList3()
    },

    onSortChange4(orderby) {
      if (validatenull(orderby.prop)) {
        this.search4.columnName = ''
        this.search4.order = ''
      } else {
        this.search4.columnName = orderby.prop
        this.search4.order = orderby.order
      }
      this.getList4()
    },

    onSortChange5(orderby) {
      if (validatenull(orderby.prop)) {
        this.search5.columnName = ''
        this.search5.order = ''
      } else {
        this.search5.columnName = orderby.prop
        this.search5.order = orderby.order
      }
      this.getList5()
    },

    onSortChange6(orderby) {
      if (validatenull(orderby.prop)) {
        this.search6.columnName = ''
        this.search6.order = ''
      } else {
        this.search6.columnName = orderby.prop
        this.search6.order = orderby.order
      }
      this.getList6()
    },


    tab2onCreate() {
      this.$refs.top2EditForm.$emit('openAddDialog', this.fromId)
    },
    handleRowClick (row) {
      this.selections = row;
    },
    handlePrice () {
      if (!this.selections.length) {
        this.$message({
            type: 'warning',
            message: '请先选择商机'
        })
        return;
      }
      if (this.selections.length > 1) {
        this.$message({
            type: 'warning',
            message: '一次只能选择一个商机'
        })
        return;
      }
      this.$refs.top7EditForm.$emit('openDialogView', this.selections[0], this.formId)
    },

    tab3onCreate() {
      this.$refs.top3EditForm.$emit('openAddDialog', {id: this.fromId, name: this.editForm128Data.cusName})
    },

    tab4onCreate() {
      this.$refs.top4EditForm.$emit('openAddDialog', this.fromId)
    },

    tab5onCreate() {
      this.$refs.top5EditForm.$emit('openAddDialog', this.fromId)
    },

    tab6onCreate() {
      this.$refs.top6EditForm.$emit('openAddDialog', this.fromId)
    },

    tab1onCreate() {
      this.$refs.top1EditForm.$emit('openAddDialog', this.fromId)
    },
    async getList1() {
      this.editForm128Data = {
        ...this.initEditData(),
        ...(await this.getFormById(this.fromId))
      }
    },
    onEdit() {
      this.$refs.editForm.$emit('openEditDialog', this.fromId)
    },
    onSubmit() {
      let validFlag = true

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
      }
    },
    doSave() {
      this.setLoad()

      saveCrmCustomer(this.editFormData)
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
        getCrmCustomerById(id)
          .then((responseData) => {
            let form = {}
            if (responseData.code == 100) {
              form = responseData.data
            } else {
              this.showMessage(responseData)
            }
            this.resetLoad()
            resolve({
              ...form,
              ...form.crmCustomer
            })
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

    listSelectOptions() {
      let search_List = {
        params: [{'columnName': 'dictType.code', 'queryType': '=', 'value': "CLUE_STATUS"}]
      }
      listDictItemAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.cluesStateOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
    },

    initOptions(This) {
      // 初始化自定义类型选择框选项变量
      this.listSelectOptions()
      this.tableDataTotal1 = 0
      this.tableDataTotal2 = 0
      this.tableDataTotal3 = 0
      this.tableDataTotal4 = 0
      this.tableDataTotal5 = 0
      this.tableDataTotal6 = 0
      this.tableData1 = []
      this.tableData2 = []
      this.tableData3 = []
      this.tableData4 = []
      this.tableData5 = []
      this.tableData6 = []
      this.handleClick()
    },
    onDeleteChild(tableData, index) {
      // 根据下标删除子表数据
      tableData.splice(index, 1)
    },
    initEditData(This) {
      return {
        cluesName: '', // 线索名称
        cluesPool: {
          id: null,
          cplName: null
        },
        cluesState: '', // 线索状态
        cluesContacts: '', // 线索联系人
        gender: '', // 性别
        phoneNumber: '', // 手机号
        birthDate: '', // 出生日期
        telephone: '', // 电话
        email: '', // 邮箱
        microblog: '', // 微博
        qq: '', // QQ
        wechat: '', // 微信
        cluesSources: '', // 线索来源
        region: '', // 所在区域
        addr: '', // 详细地址
        company: '', // 公司
        department: '', // 所属部门
        position: '', // 职位
        // position: '', // 最新跟进人
        // position: '', // 最新跟进时间
        remarks: '' // 备注
      }
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
      this.handleGetOptions()
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
        this.editForm128Data = {
          ...this.initEditData(),
          ...(await this.getFormById(id))
        }
        this.initOptions(this.editFormData)
        this.tabName = 'editForm'
        this.dialogProps.visible = true
        this.fromId = id;
        //这个id应该也要加入的
        this.getList()
        this.listCluesStateOptions()
        sessionStorage.setItem('CRM_CONTRACT_FLOW', JSON.stringify({
          id: this.editForm128Data.id,
          name: this.editForm128Data.cusName
        }))
      })
      this.$on('openAddDialog', function () {
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
        this.editForm128Data = {
          ...this.initEditData(),
          ...(await this.getFormById(id))
        }

        this.initOptions(this.editFormData)
        this.tabName = 'editForm'
        this.editFormData.id = null //把id设置为空，添加一个新的

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
