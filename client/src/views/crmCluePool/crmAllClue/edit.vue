<template>
  <el-row class="dc-container">
    <edit-form
      ref="editForm"
      v-on:save-finished="getList1"
    >
    </edit-form>
    <top1-edit-form
      ref="top1EditForm"
      v-on:save-finished="getList"
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
<!--        <el-tooltip disabled class="dc-el-tooltip_Button">-->
<!--          <el-button type="primary" class="dc-el-button_Button">返回</el-button>-->
<!--        </el-tooltip>-->
        <el-divider class="dc-el-divider_ElDivider">线索池</el-divider>
        <el-tooltip disabled class="dc-el-tooltip_Button">
<!--          <el-button type="primary" icon="el-icon-edit-outline" v-on:click="onEdit" class="dc-el-button_Button">编辑</el-button>-->
        </el-tooltip>
        <el-form ref="editForm128" :model="editForm128Data" label-width="100px" class="dc-el-form_ElEditForm">
          <el-row>
            <el-col :span="8">
              <el-form-item prop="cluesName" label="线索名称" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.cluesName"
                  :maxLength="255"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="cluesPool.id" label="所属线索池" class="dc-el-form-item_SelectInput">
                <el-input
                  v-model="editForm128Data.cluesPool.cplName"
                  :maxLength="255"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="cluesState" label="线索状态" class="dc-el-form-item_SingleInput">
                <el-select
                  v-model="editForm128Data.cluesState"
                  :style="{ width: '100%' }"
                  placeholder="请选择所属线索池"
                  clearable
                  value-key="id"
                  filterable
                  disabled
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in cluesStateOptions"
                    :key="index"
                    :label="item.name"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="cluesContacts" label="线索联系人" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.cluesContacts"
                  :maxLength="50"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="gender" label="性别" class="dc-el-form-item_SingleInput">
                <el-select
                  v-model="editForm128Data.gender"
                  :style="{ width: '100%' }"
                  clearable
                  value-key="id"
                  filterable
                  disabled
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in cluesStateOptions1"
                    :key="index"
                    :label="item.name"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="phoneNumber" label="手机号" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.phoneNumber"
                  :maxLength="15"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="birthDate" label="出生日期" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.birthDate"
                  :maxLength="60"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="telephone" label="电话" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.telephone"
                  :maxLength="30"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="email" label="邮箱" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.email"
                  :maxLength="60"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="microblog" label="微博" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.microblog"
                  :maxLength="60"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="qq" label="QQ" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.qq"
                  :maxLength="60"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="wechat" label="微信" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.wechat"
                  :maxLength="60"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="cluesSources" label="线索来源" class="dc-el-form-item_SingleInput">
                <el-select
                  v-model="editForm128Data.cluesSources"
                  :style="{ width: '100%' }"
                  clearable
                  value-key="id"
                  filterable
                  disabled
                  class="dc-el-select_SelectInput"
                >
                  <el-option
                    v-for="(item, index) in cluesSourcesOptions1"
                    :key="index"
                    :label="item.name"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="region" label="所在区域" class="dc-el-form-item_SingleInput">
                <el-select
                  v-model="editForm128Data.region"
                  :style="{ width: '100%' }"
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
              <el-form-item prop="addr" label="详细地址" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.addr"
                  :maxLength="200"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="company" label="公司" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.company"
                  :maxLength="100"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="department" label="所属部门" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.department"
                  :maxLength="100"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="position" label="职位" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.position"
                  :maxLength="60"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="updateBy" label="最新跟进人" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.updateBy"
                  :maxLength="60"
                  disabled
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="position" label="最新跟进时间" class="dc-el-form-item_SingleInput">
                <el-input
                  v-model="editForm128Data.name"
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
          </el-row>
        </el-form>
        <el-tabs v-model="activeName" @tab-click="handleClick" type="border-card" tab-position="top" class="dc-el-tabs_ElTabs">
          <el-tab-pane label="沟通记录" name="tab1" class="dc-el-tab-pane_ElTabPane">
            <el-tooltip disabled class="dc-el-tooltip_Button">
<!--              <el-button type="primary" v-on:click="tab1onCreate" icon="el-icon-add-location" class="dc-el-button_Button">新增</el-button>-->
            </el-tooltip>
<!--            <el-tooltip disabled class="dc-el-tooltip_Button">-->
<!--              <el-button type="primary" icon="el-icon-edit-outline" class="dc-el-button_Button">编辑</el-button>-->
<!--&lt;!&ndash;            </el-tooltip>&ndash;&gt;-->
<!--            <el-tooltip disabled class="dc-el-tooltip_Button">-->
<!--              <el-button type="danger" icon="el-icon-delete-solid" class="dc-el-button_Button">删除</el-button>-->
<!--            </el-tooltip>-->
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
                align="left"
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
                sortable
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="scope">
                  {{getCntType(scope.row.cntType)}}<!--调用getChangeType方法-->
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
                sortable
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
                sortable
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
                sortable
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns['138'].field"
                :title="tableColumns['138'].title"
                :width="tableColumns['138'].width"
                :visible="tableColumns['138'].visible"
                :params="{ sortId: '138', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                sortable
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns['139'].field"
                :title="tableColumns['139'].title"
                :width="tableColumns['139'].width"
                :visible="tableColumns['139'].visible"
                :params="{ sortId: '139', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                sortable
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="scope">
                  {{ "提前"+scope.row.cutRemind+(scope.row.cutRemindType==1?"分":scope.row.cutRemindType==2?"小时":"天")}}<!--调用getChangeType方法-->
                </template>
              </ux-table-column>
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
                sortable
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
                min-width="160px"
                align="center"
                header-align="center"
                sortable
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
                sortable
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
                sortable
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
                sortable
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
<!--              <ux-table-column-->
<!--                title="操作"-->
<!--                :params="{ sortId: '114' }"-->
<!--                tree-node-->
<!--                resizable-->
<!--                width="140px"-->
<!--                min-width="140px"-->
<!--                fixed="right"-->
<!--                align="left"-->
<!--                header-align="center"-->
<!--                class="dc-ux-table-column_ElTableOptColumn"-->
<!--              >-->
<!--                <template v-slot:header="scope">-->
<!--                  <span>操作</span>-->
<!--                  <el-popover ref="popover" width="10" trigger="click" placement="bottom" offset="10" class="dc-el-popover_ElPopover">-->
<!--                    <view-columns-select-->
<!--                      v-model="tableColumns"-->
<!--                      ref="viewColumnsSelect"-->
<!--                      v-on:save-column-view="saveColumn($event, 'table')"-->
<!--                      v-on:show-all-column="showAllColumn($event, 'table')"-->
<!--                      v-on:show-default-column="showDefaultColumn('table')"-->
<!--                      class="dc-view-columns-select_ViewColumnsSelect"-->
<!--                    ></view-columns-select>-->
<!--                    <template slot="reference">-->
<!--                      <OperationIcon type="primary" content="自定义列" placement="top" icon-name="el-icon-tickets"></OperationIcon>-->
<!--                    </template>-->
<!--                  </el-popover>-->
<!--                  <OperationIcon-->
<!--                    v-on:click="onExport(tableData)"-->
<!--                    type="primary"-->
<!--                    v-show="permission.export"-->
<!--                    content="导出excel文件"-->
<!--                    placement="top"-->
<!--                    icon-name="el-icon-download"-->
<!--                    class="dc-OperationIcon_IconButton"-->
<!--                  ></OperationIcon>-->
<!--                </template>-->
<!--                <template slot-scope="scope">-->
<!--                  <OperationIcon-->
<!--                    v-on:click="onView(scope)"-->
<!--                    type="info"-->
<!--                    v-show="permission.view"-->
<!--                    content="查看"-->
<!--                    placement="top"-->
<!--                    icon-name="el-icon-view"-->
<!--                    class="dc-OperationIcon_IconButton"-->
<!--                  ></OperationIcon>-->
<!--                  <OperationIcon-->
<!--                    v-on:click="onEdit1(scope)"-->
<!--                    type="primary"-->
<!--                    content="编辑"-->
<!--                    placement="top"-->
<!--                    icon-name="el-icon-edit"-->
<!--                    class="dc-OperationIcon_IconButton"-->
<!--                  ></OperationIcon>-->
<!--                  <OperationIcon-->
<!--                    v-on:click="onCopy(scope)"-->
<!--                    type="primary"-->
<!--                    v-show="permission.add"-->
<!--                    content="复制"-->
<!--                    placement="top"-->
<!--                    icon-name="el-icon-document"-->
<!--                    class="dc-OperationIcon_IconButton"-->
<!--                  ></OperationIcon>-->
<!--                  <OperationIcon-->
<!--                    v-on:click="onDelete(scope)"-->
<!--                    type="danger"-->
<!--                    content="删除"-->
<!--                    placement="top"-->
<!--                    icon-name="el-icon-delete"-->
<!--                    class="dc-OperationIcon_IconButton"-->
<!--                  ></OperationIcon>-->
<!--                  <OperationIcon-->
<!--                    v-on:click="onShowHistory(scope)"-->
<!--                    type="info"-->
<!--                    v-show="permission.view"-->
<!--                    content="历史记录"-->
<!--                    placement="top"-->
<!--                    icon-name="el-icon-info"-->
<!--                    class="dc-OperationIcon_IconButton"-->
<!--                  ></OperationIcon>-->
<!--                  <OperationIcon-->
<!--                    type="primary"-->
<!--                    v-on:click="downloads(scope)"-->
<!--                    content="下载"-->
<!--                    placement="bottom"-->
<!--                    icon-name="el-icon-download"-->
<!--                    class="dc-OperationIcon_IconButton"-->
<!--                  ></OperationIcon>-->
<!--                </template>-->
<!--              </ux-table-column>-->
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
          <el-tab-pane label="意向产品" name="tab2" class="dc-el-tab-pane_ElTabPane">
            <el-tooltip disabled class="dc-el-tooltip_Button">
<!--              <el-button  type="primary" v-on:click="tab2onCreate"  icon="el-icon-add-location" class="dc-el-button_Button">新增</el-button>-->
            </el-tooltip>
<!--            <el-tooltip disabled class="dc-el-tooltip_Button">-->
<!--              <el-button type="primary" icon="el-icon-edit-outline" class="dc-el-button_Button">编辑</el-button>-->
<!--            </el-tooltip>-->
<!--            <el-tooltip disabled class="dc-el-tooltip_Button">-->
<!--              <el-button type="danger" icon="el-icon-delete-solid" class="dc-el-button_Button">删除</el-button>-->
<!--            </el-tooltip>-->
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
                sortable
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
                sortable
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
                sortable
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
                align="center"
                header-align="center"
                sortable
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns2['133'].field"
                :title="tableColumns2['133'].title"
                :width="tableColumns2['133'].width"
                :visible="tableColumns2['133'].visible"
                :params="{ sortId: '133', summary: false }"
                tree-node
                resizable
                min-width="100px"
                align="center"
                header-align="center"
                sortable
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
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
                sortable
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns2['135'].field"
                :title="tableColumns2['135'].title"
                :width="tableColumns2['135'].width"
                :visible="tableColumns2['135'].visible"
                :params="{ sortId: '135', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                sortable
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              >
                <template slot-scope="scope">
                  {{getCluesSources(scope.row.pdTypeId)}}<!--调用getChangeType方法-->
                </template>
              </ux-table-column>
              <ux-table-column
                :field="tableColumns2['141'].field"
                :title="tableColumns2['141'].title"
                :width="tableColumns2['141'].width"
                :visible="tableColumns2['141'].visible"
                :params="{ sortId: '141', summary: false }"
                tree-node
                resizable
                min-width="70px"
                align="center"
                header-align="center"
                sortable
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns2['137'].field"
                :title="tableColumns2['137'].title"
                :width="tableColumns2['137'].width"
                :visible="tableColumns2['137'].visible"
                :params="{ sortId: '137', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                sortable
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns2['138'].field"
                :title="tableColumns2['138'].title"
                :width="tableColumns2['138'].width"
                :visible="tableColumns2['138'].visible"
                :params="{ sortId: '138', summary: false }"
                tree-node
                resizable
                min-width="160px"
                align="center"
                header-align="center"
                sortable
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns2['139'].field"
                :title="tableColumns2['139'].title"
                :width="tableColumns2['139'].width"
                :visible="tableColumns2['139'].visible"
                :params="{ sortId: '139', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                sortable
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns2['140'].field"
                :title="tableColumns2['140'].title"
                :width="tableColumns2['140'].width"
                :visible="tableColumns2['140'].visible"
                :params="{ sortId: '140', summary: false }"
                tree-node
                resizable
                min-width="160px"
                align="center"
                header-align="center"
                sortable
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
              <ux-table-column
                :field="tableColumns2['141'].field"
                :title="tableColumns2['141'].title"
                :width="tableColumns2['141'].width"
                :visible="tableColumns2['141'].visible"
                :params="{ sortId: '141', summary: false }"
                tree-node
                resizable
                min-width="180px"
                align="center"
                header-align="center"
                sortable
                show-overflow
                class="dc-ux-table-column_ElTableColumn"
              ></ux-table-column>
<!--              <ux-table-column-->
<!--                title="操作"-->
<!--                :params="{ sortId: '114' }"-->
<!--                tree-node-->
<!--                resizable-->
<!--                width="140px"-->
<!--                min-width="140px"-->
<!--                fixed="right"-->
<!--                align="center"-->
<!--                header-align="center"-->
<!--                class="dc-ux-table-column_ElTableOptColumn"-->
<!--              >-->
<!--                <template v-slot:header="scope">-->
<!--                  <span>操作</span>-->
<!--                  <el-popover ref="popover" width="10" trigger="click" placement="bottom" offset="10" class="dc-el-popover_ElPopover">-->
<!--                    <view-columns-select-->
<!--                      v-model="tableColumns2"-->
<!--                      ref="viewColumnsSelect"-->
<!--                      v-on:save-column-view="saveColumn($event, 'table')"-->
<!--                      v-on:show-all-column="showAllColumn($event, 'table')"-->
<!--                      v-on:show-default-column="showDefaultColumn('table')"-->
<!--                      class="dc-view-columns-select_ViewColumnsSelect"-->
<!--                    ></view-columns-select>-->
<!--                    <template slot="reference">-->
<!--                      <OperationIcon type="primary" content="自定义列" placement="top" icon-name="el-icon-tickets"></OperationIcon>-->
<!--                    </template>-->
<!--                  </el-popover>-->
<!--                  <OperationIcon-->
<!--                    v-on:click="onExport(tableData)"-->
<!--                    type="primary"-->
<!--                    v-show="permission.export"-->
<!--                    content="导出excel文件"-->
<!--                    placement="top"-->
<!--                    icon-name="el-icon-download"-->
<!--                    class="dc-OperationIcon_IconButton"-->
<!--                  ></OperationIcon>-->
<!--                </template>-->
<!--                <template slot-scope="scope">-->
<!--                  <OperationIcon-->
<!--                    v-on:click="onView(scope)"-->
<!--                    type="info"-->
<!--                    v-show="permission.view"-->
<!--                    content="查看"-->
<!--                    placement="top"-->
<!--                    icon-name="el-icon-view"-->
<!--                    class="dc-OperationIcon_IconButton"-->
<!--                  ></OperationIcon>-->
<!--                  <OperationIcon-->
<!--                    v-on:click="onEdit2(scope)"-->
<!--                    type="primary"-->
<!--                    content="编辑"-->
<!--                    placement="top"-->
<!--                    icon-name="el-icon-edit"-->
<!--                    class="dc-OperationIcon_IconButton"-->
<!--                  ></OperationIcon>-->
<!--                  <OperationIcon-->
<!--                    v-on:click="onCopy(scope)"-->
<!--                    type="primary"-->
<!--                    v-show="permission.add"-->
<!--                    content="复制"-->
<!--                    placement="top"-->
<!--                    icon-name="el-icon-document"-->
<!--                    class="dc-OperationIcon_IconButton"-->
<!--                  ></OperationIcon>-->
<!--                  <OperationIcon-->
<!--                    v-on:click="onDelete2(scope)"-->
<!--                    type="danger"-->
<!--                    content="删除"-->
<!--                    placement="top"-->
<!--                    icon-name="el-icon-delete"-->
<!--                    class="dc-OperationIcon_IconButton"-->
<!--                  ></OperationIcon>-->
<!--                  <OperationIcon-->
<!--                    v-on:click="onShowHistory(scope)"-->
<!--                    type="info"-->
<!--                    v-show="permission.view"-->
<!--                    content="历史记录"-->
<!--                    placement="top"-->
<!--                    icon-name="el-icon-info"-->
<!--                    class="dc-OperationIcon_IconButton"-->
<!--                  ></OperationIcon>-->
<!--                </template>-->
<!--              </ux-table-column>-->
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
import {exportByObjectId } from '@/api/sys/sysFile'

import { validatenull } from '@/utils/validate'
/** 根据用户界面配置import组件 结束 */
import { listDictItemAll } from '@/api/sys/dictItem.js'
import uploadFile from '@/views/components/uploadFile'
/** 根据用户界面配置import组件 结束 */
import { getCrmCluesById, saveCrmClues } from '@/api/myClue/crmClues'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'
import { listMyClueCrmCommunicatePage,deleteMyClueCrmCommunicate} from '@/api/myClue/crmCommunicate'
import { listCrmMyclueProductsPage, deleteCrmMyclueProducts } from '@/api/myClue/crmMyclueProducts'
import EditForm from "./form";
import top1EditForm from "./crmCommunicate/form";
import top2EditForm from "./crmMyclueProducts/form";
export default {
  extends: BaseUI,
  name: 'crmClues-form',
  components: {
    /** 根据用户界面配置组件 开始 */
    uploadFile,
    /** 根据用户界面配置组件 结束 */
    EditForm,
    top1EditForm,
    top2EditForm,
    OperationIcon
  },
  data() {
    return {
      // 性别选项
      genderOptions1: [],
      cluesStateOptions1:[],
      cluesSourcesOptions1: [],
      selectOptions1: [],
      cntTypeOptions: [],
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
          field: 'pdZname',
          visible: true,
          order: 1
        },
        131: {
          title: '产品分类',
          field: 'pdClassifyId',
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
          title: '库存数量',
          field: 'pdTotal',
          visible: true,
          order: 5
        },
        135: {
          title: '产品类型',
          field: 'pdTypeId',
          visible: true,
          order: 6
        },
        136: {
          title: '状态',
          field: 'pdStatus',
          visible: true,
          order: 7
        },
        137: {
          title: '创建者',
          field: 'createBy',
          visible: false,
          order: 8
        },
        138: {
          title: '创建时间',
          field: 'createDate',
          visible: false,
          order: 9
        },
        139: {
          title: '更新者',
          field: 'updateBy',
          visible: false,
          order: 10
        },
        140: {
          title: '更新时间',
          field: 'updateDate',
          visible: false,
          order: 11
        },
        141: {
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
      //沟通记录
      tableData: [],
      tableData2: [],
      // 查询表格列头
      tableColumns: {
        141: {
          isShow: true,
          visible: true,
          order: 0
        },
        142: {
          title: '序号',
          isShow: true,
          visible: true,
          order: 1
        },
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
          title: '更新者',
          field: 'updateBy',
          visible: false,
          order: 10
        },
        136: {
          title: '更新时间',
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
      searchList: [],
      tableId: '1720018472291172356',
      schemeId: '1720018472291172354',
      // 选项变量
      fromId:"",
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
      readonlys:false,
      dialogTitle: '对话框标题',
      editForm128Data: {
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
        updateBy: '', // 最新跟进人
        // position: '', // 最新跟进时间
        remarks: '' // 备注
      },
      // 线索状态选项
      cluesStateOptions: [],
      table196Data: [],
      // 查询表格列头
      table196Columns: {
        200: {
          isShow: true,
          visible: true,
          order: 0
        },
        202: {
          title: '序号',
          isShow: true,
          visible: true,
          order: 1
        },
        197: {
          title: '沟通类型',
          visible: true,
          order: 2
        },
        201: {
          title: '沟通人名字',
          visible: true,
          order: 3
        },
        198: {
          title: '沟通时间',
          visible: true,
          order: 4
        },
        203: {
          title: '沟通内容',
          visible: true,
          order: 5
        },
        204: {
          title: '下次沟通时间',
          visible: true,
          order: 6
        },
        205: {
          title: '跟进人',
          visible: true,
          order: 7
        },
        206: {
          title: '跟进提醒',
          visible: true,
          order: 8
        },
        208: {
          title: '附件信息',
          width: '160px',
          isShow: true,
          visible: true,
          order: 9
        }
      },
      editForm216Data: {},
      table250Data: [],
      // 查询表格列头
      table250Columns: {
        251: {
          isShow: true,
          visible: true,
          order: 0
        },
        252: {
          title: '序号',
          isShow: true,
          visible: true,
          order: 1
        },
        253: {
          title: '产品编号',
          visible: true,
          order: 2
        },
        254: {
          title: '产品名称',
          visible: true,
          order: 3
        },
        255: {
          title: '产品分类',
          visible: true,
          order: 4
        },
        256: {
          title: '指导价',
          visible: true,
          order: 5
        },
        257: {
          title: '成本价',
          visible: true,
          order: 6
        },
        258: {
          title: '产品类型',
          visible: true,
          order: 7
        },
        259: {
          title: '备注',
          visible: true,
          order: 8
        }
      },
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
      // 沟通类型选项
      // selectOptions: [],
      // 选项
      // selectOptions: [],
      tabName: 'editForm', // tab标签页
      // 窗口操作类型 view/edit/add
      action: '',
      activeName:'tab1',
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
    listCluesStateOptions() {
      let search_List = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "CRM_DICT_PRODUCT_TYPE"}]
      }
      listDictItemAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.cluesSourcesOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
      search_List = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "GENDER"}]
      };
      listDictItemAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.cluesStateOptions1 = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
      let dictItem = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "CLUE_SOURCE"}]
      };
      listDictItemAll(dictItem).then((responseData) => {
        if (responseData.code == 100) {
          this.cluesSourcesOptions1 = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
      dictItem = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "AREA"}]
      };
      listDictItemAll(dictItem).then((responseData) => {
        if (responseData.code == 100) {
          this.selectOptions1 = responseData.data
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
    handleClick(tab, event){
      debugger
     if (this.activeName=='tab1'){

       this.getList()
     }else {

       this.getList2()
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
      // 数据权限: 我的线索意向产品crm_myclue_products
      this.search2.params.push({'columnName':'cnt_myclue_id', 'queryType': '=', 'value':this.fromId});
      this.pushDataPermissions(this.search2.params, this.$route.meta.routerId, this.tableId)
      listCrmMyclueProductsPage(this.search2)
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
    downloads(scope){
      debugger
      exportByObjectId(scope.row.id).then(responseData => {
        const content = responseData;
        this.download(content, scope.row.cntPname+'.zip');
      }).catch(error => {
        this.outputError(error)
      });
    },
    //下载方法
    download(content, fileName) {
      const blob = new Blob([content]);
      const url = window.URL.createObjectURL(blob);
      let dom = document.createElement("a");
      dom.style.display = "none";
      dom.href = url;
      dom.setAttribute("download", fileName);
      document.body.appendChild(dom);
      dom.click();
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
          deleteMyClueCrmCommunicate(scope.row)
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
        .catch(() => {})
    },
    onDelete2(scope) {
      this.$confirm('确定删除吗？', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.setLoad()
          deleteCrmMyclueProducts(scope.row)
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
        .catch(() => {})
    },
    onEdit2(scope) {
      this.$refs.top2EditForm.$emit('openEditDialog', scope.row.id)
    },
    onEdit1(scope) {
      this.$refs.top1EditForm.$emit('openEditDialog', scope.row.id)
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
      this.search.params.push({'columnName':'cnt_myclue_id', 'queryType': '=', 'value':this.fromId});
      listMyClueCrmCommunicatePage(this.search)
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
    tab2onCreate() {
      this.$refs.top2EditForm.$emit('openAddDialog',this.fromId)
    },
    tab1onCreate() {
      this.$refs.top1EditForm.$emit('openAddDialog',this.fromId)
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

      saveCrmClues(this.editFormData)
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
        getCrmCluesById(id)
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
    getCntType(e){
      for (var i = 0; i < this.cntTypeOptions.length; i++) {
        if (this.cntTypeOptions[i].value == e) { //dictValue，dictLabel保持和上面定义一致
          return this.cntTypeOptions[i].name;
        }
      }
    },
    listSelectOptions() {
      let search_List = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "CLUE_STATUS"}]
      }
      listDictItemAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.cluesStateOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
      search_List = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "CRM_DICT_CNT_TYPE"}]
      }
      listDictItemAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.cntTypeOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })

    },

    // listSelectOptions() {
    //   let search_List = {
    //     params: []
    //   }
    //   // filter条件
    //   search_List.params.push.apply(search_List.params, [])
    //
    //   listDictItemAll(search_List).then((responseData) => {
    //     if (responseData.code == 100) {
    //       this.selectOptions = responseData.data
    //     } else {
    //       this.showMessage(responseData)
    //     }
    //   })
    // },

    initOptions(This) {
      // 初始化自定义类型选择框选项变量

      this.listSelectOptions()

      this.listSelectOptions()
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
        updateBy: '', // 最新跟进人
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
        this.fromId=id;
        //这个id应该也要加入的
        this.getList()
        this.listCluesStateOptions()
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
