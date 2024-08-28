<template>
  <el-row class="dc-container" v-loading="loading">
    <history ref="history" :bussObject="curentRow"></history>
    <edit-form
      ref="editForm"
      v-on:save-finished="getList"
      v-on:before-load="setLoad"
      v-on:after-load="resetLoad"
      :permission="permission"
    ></edit-form>
    <edit-s-form
      ref="editSForm"
      v-on:save-finished="getList"
      v-on:before-load="getList"
      v-on:after-load="getList"
      :permission="permission"
    ></edit-s-form>
    <el-row gutter="0" type="flex" justify="start" align="top" class="dc-el-row_ElRow">
      <el-col :span="24">
        <query-tag
          ref="queryTag"
          v-model="queryEditObject"
          :moreParm="moreParm"
          :routerId="$route && $route.meta.routerId"
          :isQueryConditionPanel="isQueryConditionPanel"
          v-on:queryTagPanel="onQueryTagPanel"
          v-on:queryTagClick="onQueryTagClick"
          v-on:search="onSearch"
          class="dc-query-tag_ElConditionTag"
        ></query-tag>
      </el-col>
      <el-col :span="6">
        <div style="text-align: right">
          <el-button-group class="dc-el-button-group_ButtonGroup">
            <el-tooltip effect="dark" content="搜索" placement="top">
              <el-button v-on:click="onSearch" type="primary" icon="el-icon-search"></el-button>
            </el-tooltip>
            <el-tooltip effect="dark" content="重置" placement="top">
              <el-button
                v-on:click="handleReset"
                type="primary"
                icon="el-icon-refresh-right"
              ></el-button>
            </el-tooltip>
            <el-tooltip effect="dark" content="更多" placement="top">
              <el-button v-on:click="onQueryConditionPanel('query')" type="primary" icon="el-icon-d-arrow-right"></el-button>
            </el-tooltip>
          </el-button-group>
        </div>
      </el-col>
    </el-row>
    <query-condition-panel
      v-if="isQueryConditionPanel"
      ref="conditionPanel"
      :conditionColumns="
        metadata.find((item) => {
          return item.type == 'main'
        }).conditionPanel
      "
      v-model="moreParm"
      :routerId="$route.meta.routerId"
      class="dc-query-condition-panel_ElConditionPanel"
    ></query-condition-panel>
    <el-form
      ref="queryForm"
      :model="queryFormData"
      label-width="100px"
      v-if="!isQueryConditionPanel"
      class="dc-el-form_ElQueryForm"
    >
      <el-row>
        <el-col :span="8">
          <el-form-item prop="cluesName" label="线索名称" class="dc-el-form-item_SingleInput">
            <el-input
              v-model="queryFormData.cluesName"
              :maxLength="255"
              placeholder="请输入线索名称"
              clearable
              class="dc-el-input_SingleInput"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="clues_pool_id" label="所属线索池" class="dc-el-form-item_SelectInput">
            <el-select
              v-model="queryFormData.clues_pool_id"
              :style="{ width: '100%' }"
              placeholder="请选择"
              clearable
              filterable
              class="dc-el-select_SelectInput"
            >
              <el-option
                v-for="(item, index) in selectOptions"
                :key="index"
                :label="item.cplName"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="cluesSources" label="线索来源" class="dc-el-form-item_SelectInput">
            <el-select
              v-model="queryFormData.cluesSources"
              :style="{ width: '100%' }"
              placeholder="请选择"
              clearable
              filterable
              class="dc-el-select_SelectInput"
            >
              <el-option
                v-for="(item, index) in cluesSourcesOptions"
                :key="index"
                :label="item.name"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item prop="cluesState" label="线索状态" class="dc-el-form-item_SelectInput">
            <el-select
              v-model="queryFormData.cluesState"
              :style="{ width: '100%' }"
              placeholder="请选择线索状态"
              clearable
              filterable
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
      </el-row>
    </el-form>
    <el-row gutter="0" type="default" class="dc-el-row_ElRow">
      <el-tooltip  disabled class="dc-el-tooltip_Button">
        <el-button type="primary" icon="el-icon-plus" v-on:click="onCreate">新增</el-button>
      </el-tooltip>
      <el-tooltip disabled class="dc-el-tooltip_Button">
        <el-button type="primary" icon="el-icon-edit-outline" v-on:click="onEdit">编辑</el-button>
      </el-tooltip>
      <el-tooltip disabled class="dc-el-tooltip_Button">
        <el-button type="danger" icon="el-icon-delete" v-on:click="onDelete">删除</el-button>
      </el-tooltip>
      <el-tooltip style="margin-left:10px"  disabled class="dc-el-tooltip_Button">
        <el-dropdown @command="handleCommand">
        <el-button type="primary" icon="el-icon-s-operation" >导入/导出</el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="im">导入线索</el-dropdown-item>
          <input ref="fileinput" id="fileinput"  type="file" style="display: none" @change="uploadExcel"
                 accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel "/>
          <el-dropdown-item command="do">导出线索</el-dropdown-item>
        </el-dropdown-menu>
        </el-dropdown>
      </el-tooltip>
      <el-tooltip style="margin-left:10px" disabled class="dc-el-tooltip_Button">
        <el-dropdown @command="handleCommand">
        <el-button type="primary" icon="el-icon-s-operation">其他操作</el-button>

          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="1">转换</el-dropdown-item>
            <el-dropdown-item command="2">转移</el-dropdown-item>
            <el-dropdown-item command="3">合并</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-tooltip>
    </el-row>
    <ux-grid
      column-key
      ref="table"
      :data="tableData"
      border
      v-on:sort-change="onSortChange"
      v-on:header-dragend="onWidthChange($refs.table)"
      :tree-config="{
                children: '',
                indent: 20,
                accordion: false,
                line: false,
                showIcon: true,
                iconOpen: '',
                iconClose: ''
              }"
      @selection-change="handleSelectionChange"
      class="dc-ux-grid_QueryTable"
    >
      <ux-table-column
        :params="{ sortId: '251', summary: false }"
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
        :field="tableColumns['121'].field"
        :title="tableColumns['121'].title"
        :width="tableColumns['121'].width"
        :visible="tableColumns['121'].visible"
        :params="{ sortId: '121', summary: false }"
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
        :field="tableColumns['141'].field"
        :title="tableColumns['141'].title"
        :width="tableColumns['141'].width"
        :visible="tableColumns['141'].visible"
        :params="{ sortId: '141', summary: false }"
        tree-node
        resizable
        min-width="120px"
        align="center"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['123'].field"
        :title="tableColumns['123'].title"
        :width="tableColumns['123'].width"
        :visible="tableColumns['123'].visible"
        :params="{ sortId: '123', summary: false }"
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
        :field="tableColumns['124'].field"
        :title="tableColumns['124'].title"
        :width="tableColumns['124'].width"
        :visible="tableColumns['124'].visible"
        :params="{ sortId: '124', summary: false }"
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
        :field="tableColumns['125'].field"
        :title="tableColumns['125'].title"
        :width="tableColumns['125'].width"
        :visible="tableColumns['125'].visible"
        :params="{ sortId: '125', summary: false }"
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
        :field="tableColumns['122'].field"
        :title="tableColumns['122'].title"
        :width="tableColumns['122'].width"
        :visible="tableColumns['122'].visible"
        :params="{ sortId: '122', summary: false }"
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
          {{getCluesSources(scope.row.cluesSources)}}<!--调用getChangeType方法-->
        </template>
      </ux-table-column>
      <ux-table-column
        :field="tableColumns['120'].field"
        :title="tableColumns['120'].title"
        :width="tableColumns['120'].width"
        :visible="tableColumns['120'].visible"
        :params="{ sortId: '120', summary: false }"
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
          {{getCluesState(scope.row.cluesState)}}<!--调用getChangeType方法-->
        </template>
      </ux-table-column>
      <ux-table-column
        :field="tableColumns['137'].field"
        :title="tableColumns['137'].title"
        :width="tableColumns['137'].width"
        :visible="tableColumns['137'].visible"
        :params="{ sortId: '137', summary: false }"
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
        :field="tableColumns['136'].field"
        :title="tableColumns['136'].title"
        :width="tableColumns['136'].width"
        :visible="tableColumns['136'].visible"
        :params="{ sortId: '136', summary: false }"
        tree-node
        resizable
        min-width="180px"
        align="left"
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
        align="left"
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
        min-width="160px"
        align="right"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
      <ux-table-column
        :field="tableColumns['140'].field"
        :title="tableColumns['140'].title"
        :width="tableColumns['140'].width"
        :visible="tableColumns['140'].visible"
        :params="{ sortId: '140', summary: false }"
        tree-node
        resizable
        min-width="180px"
        align="left"
        header-align="center"
        sortable
        show-overflow
        class="dc-ux-table-column_ElTableColumn"
      ></ux-table-column>
    </ux-grid>
    <el-pagination
      :total="tableDataTotal"
      :page-size="search.limit"
      background
      :current-page="tableDataPage"
      :page-sizes="[10, 20, 30, 40, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      v-on:size-change="onSizeChange"
      v-on:current-change="onCurrentChange"
      class="dc-el-pagination_ElPagination"
    ></el-pagination>
    <el-dialog
      width="70%"
      :fullscreen="false"
      :title="dialog143Props.title"
      :visible.sync="dialog143Props.visible"
      :close-on-click-modal="false"
      class="dc-el-dialog_ElDialog"
    >
      <el-row>
        <el-form ref="editForm146" :model="editForm146Data" label-width="100px" class="dc-el-form_ElEditForm">
          <el-row>
            <el-form-item
              prop=""
              label="客户名称"
              :rules="[{ required: true, message: '客户名称不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_SingleInput"
            >
              <el-input
                v-model="editForm146Data.cusName"
                :maxLength="-1"
                placeholder="请输入"
                clearable
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop=""
              label="客户类型"
              :rules="[{ required: true, message: '客户类型不能为空', trigger: 'change' }]"
              class="dc-el-form-item_SelectInput"
            >
              <el-select
                v-model="editForm146Data.cusType"
                :style="{ width: '100%' }"
                placeholder="请选择"
                clearable
                filterable
                class="dc-el-select_SelectInput"
              >
                <el-option
                  v-for="(item, index) in customOptions"
                  :key="index"
                  :label="item.name"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item prop="" label="所在区域" class="dc-el-form-item_SingleInput">
              <el-select
                v-model="editForm146Data.cusRegion"
                :style="{ width: '100%' }"
                placeholder="请选择"
                clearable
                filterable
                class="dc-el-select_SelectInput"
              >
                <el-option
                  v-for="(item, index) in selectOptions4"
                  :key="index"
                  :label="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item prop="" label="详细地址" class="dc-el-form-item_SingleInput">
              <el-input
                v-model="editForm146Data.cusAddress"
                :maxLength="-1"
                placeholder="请输入"
                clearable
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
            <el-form-item prop="null" class="dc-el-form-item_ElCheckbox">
              <el-checkbox v-model="editForm146Data.CREATE" class="dc-el-checkbox_ElCheckbox">创建商机</el-checkbox>
            </el-form-item>
            <el-form-item
              prop=""
              label="机会名称"
              :rules="[{ required: true, message: '机会名称不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_SingleInput"
            >
              <el-input
                v-model="editForm146Data.name"
                :maxLength="-1"
                placeholder="请输入"
                clearable
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop=""
              label="预计销售金额"
              :rules="[{ required: true, message: '预计销售金额不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_SingleInput"
            >
              <number-input
                v-model="editForm146Data.money"
                :maxLength="-1"
                :min="0"
                :max="999999999999.99"
                :precision="2"
                placeholder="请输入"
                clearable
                class="dc-el-input_SingleInput"
              ></number-input>
            </el-form-item>
          </el-row>
        </el-form>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button @click="CustomClose">取消</el-button>
        <el-button @click="addCustom" type="primary">确认</el-button>
      </span>
    </el-dialog>
    <el-dialog
      width="70%"
      :fullscreen="false"
      :title="dialog188Props.title"
      :visible.sync="dialog188Props.visible"
      :close-on-click-modal="false"
      class="dc-el-dialog_ElDialog"
    >
      <el-form
        ref="queryForm"
        :model="queryFormData3"
        label-width="100px"
        v-if="!isQueryConditionPanel"
        class="dc-el-form_ElQueryForm"
      >
        <el-row>
          <el-col :span="6">
            <el-form-item prop="department.id" label="部门" class="dc-el-form-item_CascaderInput">
              <el-cascader
                ref="cascader145"
                :options="departmentOptions"
                v-model="queryFormData3.department.id"
                :style="{ width: '100%' }"
                placeholder="请选择部门"
                :props="{ label: 'name', value: 'id', children: 'children', checkStrictly: true, emitPath: false }"
                clearable
                filterable
                separator="/"
                class="dc-el-cascader_CascaderInput"
              ></el-cascader>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item prop="name" label="用户名" class="dc-el-form-item_SingleInput">
              <el-input
                v-model="queryFormData3.name"
                :maxLength="128"
                placeholder="请输入用户名"
                clearable
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-tooltip effect="dark" content="搜索" placement="top">
              <el-button v-on:click="onSearch3" type="primary" icon="el-icon-search"></el-button>
            </el-tooltip>
            <el-tooltip effect="dark" content="重置" placement="top">
              <el-button
                v-on:click="isQueryConditionPanel ? $refs.conditionPanel.reset() : $refs.queryForm.resetFields()"
                type="primary"
                icon="el-icon-refresh-right"
              ></el-button>
            </el-tooltip>
          </el-col>
        </el-row>
      </el-form>
      <ux-grid
        column-key
        ref="table"
        :data="tableData3"
        border
        v-on:sort-change="onSortChange3"
        @current-change="handleSelectionChange3"
        v-on:header-dragend="onWidthChange($refs.table)"
        :tree-config="{
              children: '',
              indent: 20,
              accordion: false,
              line: false,
              showIcon: true,
              iconOpen: '',
              iconClose: ''
            }"
        class="dc-ux-grid_QueryTable"
      >
        <ux-table-column
          :field="tableColumns3['126'].field"
          :title="tableColumns3['126'].title"
          :width="tableColumns3['126'].width"
          :visible="tableColumns3['126'].visible"
          :params="{ sortId: '126' }"
          tree-node
          resizable
          min-width="120px"
          align="left"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        ></ux-table-column>
        <ux-table-column
          :field="tableColumns3['127'].field"
          :title="tableColumns3['127'].title"
          :width="tableColumns3['127'].width"
          :visible="tableColumns3['127'].visible"
          :params="{ sortId: '127' }"
          tree-node
          resizable
          min-width="120px"
          align="left"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        ></ux-table-column>
        <ux-table-column
          :field="tableColumns3['128'].field"
          :title="tableColumns3['128'].title"
          :width="tableColumns3['128'].width"
          :visible="tableColumns3['128'].visible"
          :params="{ sortId: '128' }"
          tree-node
          resizable
          min-width="120px"
          align="left"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        ></ux-table-column>
        <ux-table-column
          :field="tableColumns3['129'].field"
          :title="tableColumns3['129'].title"
          :width="tableColumns3['129'].width"
          :visible="tableColumns3['129'].visible"
          :params="{ sortId: '129' }"
          tree-node
          resizable
          min-width="120px"
          align="left"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        ></ux-table-column>
        <ux-table-column
          :field="tableColumns3['130'].field"
          :title="tableColumns3['130'].title"
          :width="tableColumns3['130'].width"
          :visible="tableColumns3['130'].visible"
          :params="{ sortId: '130' }"
          tree-node
          resizable
          min-width="120px"
          align="left"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        ></ux-table-column>
        <ux-table-column
          :field="tableColumns3['131'].field"
          :title="tableColumns3['131'].title"
          :width="tableColumns3['131'].width"
          :visible="tableColumns3['131'].visible"
          :params="{ sortId: '131' }"
          tree-node
          resizable
          min-width="180px"
          align="left"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        ></ux-table-column>
        <ux-table-column
          :field="tableColumns3['132'].field"
          :title="tableColumns3['132'].title"
          :width="tableColumns3['132'].width"
          :visible="tableColumns3['132'].visible"
          :params="{ sortId: '132' }"
          tree-node
          resizable
          min-width="180px"
          align="left"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        ></ux-table-column>
        <ux-table-column
          :field="tableColumns3['133'].field"
          :title="tableColumns3['133'].title"
          :width="tableColumns3['133'].width"
          :visible="tableColumns3['133'].visible"
          :params="{ sortId: '133' }"
          tree-node
          resizable
          min-width="180px"
          align="left"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        ></ux-table-column>
        <ux-table-column
          :field="tableColumns3['134'].field"
          :title="tableColumns3['134'].title"
          :width="tableColumns3['134'].width"
          :visible="tableColumns3['134'].visible"
          :params="{ sortId: '134' }"
          tree-node
          resizable
          min-width="180px"
          align="center"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        >
          <template slot-scope="{ row, rowIndex }">
            <div
              v-html="
                    ((cellValue, row, index) => {
                      switch (cellValue) {
                        case '1':
                          return '<span style=' + 'color:red' + '>√</span>'
                        case '0':
                          return ''
                        default:
                          return ''
                      }
                    })(row.isLocked, row, rowIndex)
                  "
            ></div>
          </template>
        </ux-table-column>
        <ux-table-column
          :field="tableColumns3['135'].field"
          :title="tableColumns3['135'].title"
          :width="tableColumns3['135'].width"
          :visible="tableColumns3['135'].visible"
          :params="{ sortId: '135' }"
          tree-node
          resizable
          min-width="180px"
          align="left"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        ></ux-table-column>
        <ux-table-column
          :field="tableColumns3['143'].field"
          :title="tableColumns3['143'].title"
          :width="tableColumns3['143'].width"
          :visible="tableColumns3['143'].visible"
          :params="{ sortId: '143' }"
          tree-node
          resizable
          min-width="160px"
          align="right"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        ></ux-table-column>
        <ux-table-column
          :field="tableColumns3['139'].field"
          :title="tableColumns3['139'].title"
          :width="tableColumns3['139'].width"
          :visible="tableColumns3['139'].visible"
          :params="{ sortId: '139' }"
          tree-node
          resizable
          min-width="180px"
          align="left"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        ></ux-table-column>
        <ux-table-column
          :field="tableColumns3['136'].field"
          :title="tableColumns3['136'].title"
          :width="tableColumns3['136'].width"
          :visible="tableColumns3['136'].visible"
          :params="{ sortId: '136' }"
          tree-node
          resizable
          min-width="180px"
          align="left"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        ></ux-table-column>
        <ux-table-column
          :field="tableColumns3['140'].field"
          :title="tableColumns3['140'].title"
          :width="tableColumns3['140'].width"
          :visible="tableColumns3['140'].visible"
          :params="{ sortId: '140' }"
          tree-node
          resizable
          min-width="180px"
          align="left"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        ></ux-table-column>
        <ux-table-column
          :field="tableColumns3['141'].field"
          :title="tableColumns3['141'].title"
          :width="tableColumns3['141'].width"
          :visible="tableColumns3['141'].visible"
          :params="{ sortId: '141' }"
          tree-node
          resizable
          min-width="160px"
          align="right"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        ></ux-table-column>
        <ux-table-column
          :field="tableColumns3['142'].field"
          :title="tableColumns3['142'].title"
          :width="tableColumns3['142'].width"
          :visible="tableColumns3['142'].visible"
          :params="{ sortId: '142' }"
          tree-node
          resizable
          min-width="180px"
          align="left"
          header-align="center"
          sortable
          show-overflow
          class="dc-ux-table-column_ElTableColumn"
        ></ux-table-column>
      </ux-grid>
      <el-pagination
        :total="tableDataTotal3"
        :page-size="search3.limit"
        background
        :current-page="tableDataPage3"
        :page-sizes="[10, 20, 30, 40, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        v-on:size-change="onSizeChange3"
        v-on:current-change="onCurrentChange3"
        class="dc-el-pagination_ElPagination"
      ></el-pagination>
      <span slot="footer" class="dialog-footer">
        <el-button @click="transferClose">取消</el-button>
        <el-button @click="editCrmClues" type="primary">确认</el-button>
      </span>
    </el-dialog>
    <el-dialog
      :visible.sync="dialog189Props.visible"
      :close-on-click-modal="false"
      class="dc-el-dialog_ElDialog"
      width="70%"
      title="合并线索">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-form-item label="线索名称" prop="cluesName">
          <el-radio-group v-model="formData.cluesName" size="medium">
            <el-radio v-for="(item, index) in cluesNameOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="所属线索池" prop="cluesPool.id">
          <el-radio-group v-model="formData.cluesPool.id" size="medium">
            <el-radio v-for="(item, index) in cluesPoolOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="线索来源" prop="cluesSources">
          <el-radio-group v-model="formData.cluesSources" size="medium">
            <el-radio v-for="(item, index) in cluesSources1Options" :key="index" :label="item.value"
                      :disabled="item.disabled"> {{getCluesSources(item.label)==""?"无数据":getCluesSources(item.label)}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="线索联系人" prop="cluesContacts">
          <el-radio-group v-model="formData.cluesContacts" size="medium">
            <el-radio v-for="(item, index) in cluesContactsOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号" prop="phoneNumber">
          <el-radio-group v-model="formData.phoneNumber" size="medium">
            <el-radio v-for="(item, index) in phoneNumberOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="公司" prop="company">
          <el-radio-group v-model="formData.company" size="medium">
            <el-radio v-for="(item, index) in companyOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="所属部门" prop="department">
          <el-radio-group v-model="formData.department" size="medium">
            <el-radio v-for="(item, index) in department1Options" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="职位" prop="position">
          <el-radio-group v-model="formData.position" size="medium">
            <el-radio v-for="(item, index) in positionOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="所在区域" prop="region">
          <el-radio-group v-model="formData.region" size="medium">
            <el-radio v-for="(item, index) in regionOptions" :key="index" :label="item.value"
                      :disabled="item.disabled"> {{getRegion(item.label)==""?"无数据":getRegion(item.label)}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="详细地址" prop="addr">
          <el-radio-group v-model="formData.addr" size="medium">
            <el-radio v-for="(item, index) in addrOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="formData.gender" size="medium">
            <el-radio v-for="(item, index) in genderOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{getGender(item.label)==""?"无数据":getGender(item.label)}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="出生日期" prop="birthDate">
          <el-radio-group v-model="formData.birthDate" size="medium">
            <el-radio v-for="(item, index) in birthDateOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="电话" prop="telephone">
          <el-radio-group v-model="formData.telephone" size="medium">
            <el-radio v-for="(item, index) in telephoneOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-radio-group v-model="formData.email" size="medium">
            <el-radio v-for="(item, index) in emailOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="微信" prop="wechat">
          <el-radio-group v-model="formData.wechat" size="medium">
            <el-radio v-for="(item, index) in wechatOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="QQ" prop="qq">
          <el-radio-group v-model="formData.qq" size="medium">
            <el-radio v-for="(item, index) in qqOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="微博" prop="microblog">
          <el-radio-group v-model="formData.microblog" size="medium">
            <el-radio v-for="(item, index) in microblogOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-radio-group v-model="formData.remarks" size="medium">
            <el-radio v-for="(item, index) in remarksOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="意向产品" prop="myclueProducts">
          <el-radio-group v-model="formData.myclueProducts" size="medium">
            <el-radio v-for="(item, index) in myclueProductsOptions" :key="index" :label="item.value"
                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>

<!--        <el-form-item label="沟通记录" prop="remarks">-->
<!--          <el-radio-group v-model="formData.remarks" size="medium">-->
<!--            <el-radio v-for="(item, index) in remarksOptions" :key="index" :label="item.value"-->
<!--                      :disabled="item.disabled">{{item.label==""?"无数据":item.label}}</el-radio>-->
<!--          </el-radio-group>-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer">
        <el-button @click="closeIncorporate">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
    <el-dialog
      :visible.sync="dialog281Props.visible"
      :close-on-click-modal="false"
      :title="dialog281Props.title">
      <el-form ref="elForm" :model="formData3" size="medium" label-width="100px">
        <el-row>
          <el-col :span="18">
            <el-form-item label="文件路径" prop="pathinfo">
              <el-input v-model="formData3.pathinfo" placeholder="请选择文件文件路径" readonly :disabled='true' clearable
                        :style="{width: '100%'}">
                <el-button @click="excelExcel" slot="append" icon="el-icon-search">选择文件</el-button>
<!--                <template @click="excelExcel" slot="append">选择文件</template>-->

              </el-input>
            </el-form-item>
          </el-col>
          <el-col style=" margin-left:-82px" :span="6">
            <el-form-item style="margin-left:25px" label="" prop="field106">
              <el-button type="primary" @click="updateExcel"  size="small"> 上传 </el-button>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item style="margin-left:25px" label="" prop="field106">
              <el-button type="primary" v-on:click="exportTemplate('myClueModel')" size="small"> 下载导入模板 </el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer">
        <el-button @click="close3">取消</el-button>
        <el-button type="primary" @click="handelConfirm3">确定</el-button>
      </div>
    </el-dialog>
  </el-row>
</template>
<script>
  import { getCrmMyclueProductsById, saveCrmMyclueProducts } from '@/api/myClue/crmMyclueProducts'
  import {validatenull} from '@/utils/validate'
import {getCrmCustomerById, saveCrmCustomer} from '@/api/crmCustomer/crmCustomer'
import { listCrmCluesPage,listCrmCluesAll, deleteCrmClues,bulkDeleteCrmClues,saveCrmClues,importExport } from '@/api/myClue/crmClues'
  import { listCrmMyclueProductsAll } from '@/api/myClue/crmMyclueProducts'
import { listResourcePermission } from '@/api/admin/common/permission'
  import { listUserPage, deleteUser } from '@/api/admin/user'
  import { saveCrmOpportunity } from '@/api/crmBusiness/crmOpportunity'
/** 根据用户界面配置import组件 开始 */
import History from '@/views/components/history'
import EditForm from './form'
import EditSForm from './edit'
import QueryTag from '@/views/components/queryTag'
import QueryConditionPanel from '@/views/components/queryConditionPanel'
// import {} from ''
import { listDictItemAll } from '@/api/sys/dictItem.js'
import ViewColumnsSelect from '@/views/components/DcViewColumnsSelect'
/** 根据用户界面配置import组件 结束 */
import metadata from './metadata'
import ExportExcelButton from '@/components/ExportExcelButton'
import DcMain from '@/views/components/dcMain'
import OperationIcon from '@/components/OperationIcon'
  import { listMyCrmCluePoolAll,listMyCrmCluePoolPage } from '@/api/myClue/crmCluePool.js'
export default {
  extends: DcMain,
  components: {
    /** 根据用户界面配置组件 开始 */
    History,
    EditForm,
    EditSForm,
    QueryTag,
    QueryConditionPanel,
    ViewColumnsSelect,
    /** 根据用户界面配置组件 结束 */
    ExportExcelButton,
    OperationIcon
  },
  data() {
    return {
      files:[],
      //合并线索
      formData: {
        cluesName: "",
        cluesPool: "",
        cluesSources: "",
        cluesContacts: "",
        phoneNumber: "",
        company: "",
        department: "",
        position: "",
        region: "",
        addr: "",
        gender: "",
        birthDate: "",
        telephone: "",
        email: "",
        wechat: "",
        qq: "",
        microblog: "",
        remarks: "",
        myclueProducts: "",
      },
      formData3:{
        pathinfo:"",
      },
      // rules: {
      //   cluesName: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个线索名称',
      //     trigger: 'change'
      //   }],
      //   cluesPool: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个所属线索池',
      //     trigger: 'change'
      //   }],
      //   cluesSources: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个线索来源',
      //     trigger: 'change'
      //   }],
      //   cluesContacts: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个线索联系人',
      //     trigger: 'change'
      //   }],
      //   phoneNumber: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个手机号',
      //     trigger: 'change'
      //   }],
      //   company: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个公司',
      //     trigger: 'change'
      //   }],
      //   department: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个所属部门',
      //     trigger: 'change'
      //   }],
      //   position: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个职位',
      //     trigger: 'change'
      //   }],
      //   region: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个所在区域',
      //     trigger: 'change'
      //   }],
      //   addr: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个详细地址',
      //     trigger: 'change'
      //   }],
      //   gender: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个性别',
      //     trigger: 'change'
      //   }],
      //   birthDate: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个出生日期',
      //     trigger: 'change'
      //   }],
      //   telephone: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个电话',
      //     trigger: 'change'
      //   }],
      //   email: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个邮箱',
      //     trigger: 'change'
      //   }],
      //   wechat: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个微信',
      //     trigger: 'change'
      //   }],
      //   qq: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个QQ',
      //     trigger: 'change'
      //   }],
      //   microblog: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个微博',
      //     trigger: 'change'
      //   }],
      //   remarks: [{
      //     required: true,
      //     type: 'array',
      //     message: '请至少选择一个备注',
      //     trigger: 'change'
      //   }],
      // },
      genderOptionsS:[],



      cluesNameOptions: [],
      cluesPoolOptions: [],
      cluesSources1Options: [],
      cluesContactsOptions: [],
      phoneNumberOptions: [],
      companyOptions: [],
      department1Options: [],
      positionOptions: [],
      regionOptions: [],
      addrOptions: [],
      genderOptions: [],
      birthDateOptions: [],
      telephoneOptions: [],
      emailOptions: [],
      wechatOptions: [],
      qqOptions: [],
      microblogOptions: [],
      remarksOptions: [],
      myclueProductsOptions: [],



      multipleSelection: [],
      multipleSelection3: [],
      selectOptions4: [],
      permission: {
        view: false,
        add: false,
        edit: false,
        remove: false,
        export: false
      },
      metadata,

      /** 根据用户界面配置生成data数据 开始 */
      queryFormData: {
        cluesName: '', // 线索名称
        clues_pool_id: '', // 线索名称
        cluesPool: {
          id: null,
          cplName: null
        },
        cluesSources: null, // 线索来源
        cluesState: null // 线索状态
      },
      tableData: [],
      tableData3: [],
      tableColumns3: {
        126: {
          title: '公司.全称',
          field: 'company.fullName',
          visible: false,
          order: 0
        },
        127: {
          title: '公司.编号',
          field: 'company.code',
          visible: false,
          order: 1
        },
        128: {
          title: '公司.名称',
          field: 'company.name',
          visible: false,
          order: 2
        },
        129: {
          title: '部门.编号',
          field: 'department.code',
          visible: false,
          order: 3
        },
        130: {
          title: '部门.名称',
          field: 'department.name',
          visible: false,
          order: 4
        },
        131: {
          title: '用户名',
          field: 'name',
          visible: true,
          order: 5
        },
        132: {
          title: '登录账号',
          field: 'loginName',
          visible: false,
          order: 6
        },
        133: {
          title: '密码',
          field: 'loginPassword',
          visible: false,
          order: 7
        },
        134: {
          title: '禁用',
          field: 'isLocked',
          visible: true,
          order: 8
        },
        135: {
          title: '手机号',
          field: 'phone',
          visible: false,
          order: 9
        },
        143: {
          title: '更新时间',
          field: 'updateDate',
          visible: true,
          order: 10
        },
        139: {
          title: '备注信息',
          field: 'remarks',
          visible: true,
          order: 11
        },
        136: {
          title: '邮箱地址',
          field: 'email',
          visible: false,
          order: 12
        },
        140: {
          title: '创建者',
          field: 'createBy',
          visible: false,
          order: 15
        },
        141: {
          title: '创建时间',
          field: 'createDate',
          visible: false,
          order: 16
        },
        142: {
          title: '更新者',
          field: 'updateBy',
          visible: false,
          order: 17
        },
        117: {
          title: '操作',
          width: '160px',
          visible: true,
          order: 18
        }
      },
      // 查询表格列头
      tableColumns: {
        251: {
          isShow: true,
          visible: true,
          order: 0
        },
        142: {
          title: '序号',
          isShow: true,
          visible: true,
          order: 0
        },
        121: {
          title: '线索名称',
          field: 'cluesName',
          visible: true,
          order: 1
        },
        141: {
          title: '所属线索池',
          field: 'cluesPool.cplName',
          visible: true,
          order: 2
        },
        123: {
          title: '线索联系人',
          field: 'cluesContacts',
          visible: true,
          order: 3
        },
        124: {
          title: '手机号',
          field: 'phoneNumber',
          visible: true,
          order: 4
        },
        125: {
          title: '邮箱',
          field: 'email',
          visible: true,
          order: 5
        },
        122: {
          title: '线索来源',
          field: 'cluesSources',
          visible: true,
          order: 6
        },
        120: {
          title: '线索状态',
          field: 'cluesState',
          visible: true,
          order: 7
        },
        137: {
          title: '创建时间',
          field: 'createDate',
          visible: true,
          order: 8
        },
        136: {
          title: '创建人',
          field: 'createBy',
          visible: true,
          order: 9
        },
        138: {
          title: '更新者',
          field: 'updateBy',
          visible: false,
          order: 10
        },
        139: {
          title: '更新时间',
          field: 'updateDate',
          visible: false,
          order: 11
        },
        140: {
          title: '下次跟进时间',
          field: 'remarks',
          visible: true,
          order: 12
        },
        113: {
          title: '操作',
          width: '160px',
          visible: true,
          order: 13
        }
      },
      // 分页属性
      tableDataPage: 1,
      tableDataTotal: 0,
      // 分页属性
      tableDataPage3: 1,
      tableDataTotal3: 0,
      // 对话框属性变量
      dialog143Props: {
        visible: false,
        title: '线索转换'
      },
      dialogTitle: '线索转换',
      editForm146Data: {
        cusName: '', // 客户名称
        CREATE: '', // 是否创建商机
        cusStage: '01', // 客户阶段
        cusType: null, // 客户类型
        cusSource: '01', // 客户来源
        cusMobile: '', // 联系方式
        // 上级客户
        cusContacts: '', // 联系人
        cusAddress: '', // 详细地址
        cusRegion: null, // 所在区域
        remarks: '', // 备注信息
        name: '' ,// 备注信息
        money: '' // 备注信息
      },
      // 对话框属性变量
      dialog188Props: {
        visible: false,
        title: '线索转移'
      },
      dialog189Props: {
        visible: false,
        title: '合并线索'
      },
      dialog281Props: {
        visible: false,
        title: '线索导入'
      },
      // dialogTitle: '合并线索',
      // 选项变量
      // 所属线索池选项
      // selectOptions: [],
      // 线索来源选项
      cluesSourcesOptions: [],
      // 线索状态选项
      cluesStateOptions: [],
      customOptions: [],
      // 客户类型选项
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
      // undefined选项
      radioGroupOptions: [
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
      version: 37,
      search: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
      // 部门选项
      departmentOptions: [],
      queryFormData3: {
        // 部门
        department: {
          id: null,
          name: null,
          code: null,
          ids: null
        },
        name: '' // 用户名
      },
      search3: {
        // 查询条件   业务表设置的筛选条件
        params: [],

        offset: 0,
        limit: 10,
        columnName: '', // 排序字段名
        order: '' // 排序
      },
      searchList: [],
      tableId: '1709759135450792837',
      schemeId: '1709759135450792835'
    }
  },
  computed: {},
  methods: {
    handleReset () {
      if (this.isQueryConditionPanel ? this.$refs.conditionPanel.reset() : this.$refs.queryForm.resetFields()) {

      }
      this.onSearch();
    },

    exportTemplate(fileName) {
      let a = document.createElement("a");
      a.href = 'static/template/' + fileName + '.xls'
      a.download = fileName + ".xls"
      a.style.display = "none"
      document.body.appendChild(a)
      a.click()
      a.remove()
    },
    updateExcel(){
      const files =  this.files;
      if (files == null || files.length == 0) {
        this.showMessage({
          type: 'success',
          msg: '请选择文件'
        })
        return
      }
      let formData = new FormData()
      formData.append('file', files[0])
      this.setLoad()
      importExport(formData).then((responseData) => {
        if (responseData.code == 100) {
          this.getList()
          this.showMessage({
            type: 'success',
            msg: '导入成功'
          })
          this.dialog281Props.visible=false
        } else {
          this.showMessage({
            type: responseData.type,
            msg: responseData.data.errorLocation
          })
        }
        this.$refs.fileinput.value = ''
        this.resetLoad()
      }).catch((error) => {
        this.$refs.fileinput.value = ''
        this.outputError(error)
      })
    },
    excelExcel(){
      this.upExports()
    },
    close3(){
      this.dialog281Props.visible=false
    },
    handelConfirm3(){
      this.dialog281Props.visible=false
    },
    async onExports() {
      let tableData = []
      if (this.multipleSelection.length != 0) {
        tableData = this.multipleSelection
      } else {
        tableData = await this.getListAll()
      }
      // 没有数据时，提示
      if (validatenull(tableData)) {
        this.$alert('没有导出数据！', '提示', {
          type: 'info'
        })
        return
      }
      for (let i = 0; i < tableData.length; i++) {
        tableData[i].cluesSources = this.getCluesSources(tableData[i].cluesSources)
        tableData[i].orderNumber=i+1
      }
      this.loading = true
      import('@/vendor/Export2Excel').then(excel => {
        var filterVal = []
        filterVal.push("orderNumber")
        filterVal.push("cluesName")
        filterVal.push("cluesPool.cplName")
        filterVal.push("cluesSources")
        filterVal.push("cluesContacts")
        filterVal.push("phoneNumber")
        filterVal.push("email")
        filterVal.push("")
        filterVal.push("createBy")
        filterVal.push("createDate")
        var heads = []
        heads.push("序号")
        heads.push("线索名称")
        heads.push("所属线索池")
        heads.push("线索来源")
        heads.push("线索联系人")
        heads.push("手机号")
        heads.push("邮箱")
        heads.push("下次跟进时间")
        heads.push("创建人")
        heads.push("创建时间")
        const data = this.formatJson(filterVal, tableData)
        excel.export_json_to_excel({
          header: heads,
          data,
          filename: 'export',
          autoWidth: true
        })
        this.loading = false
      }).catch(error => {
        console.log('-----------export excel error-------------')
        console.error(error)
        this.loading = false
      })
    },
    handleCommand(command) {
      if(command=="do"){
        this.onExports()
        }else if (command=="1"){
        this.transformCustom()
      }else if (command=="2"){
        this.transfer()
      }else if (command=="3"){
        this.incorporate()
      }else if(command=="im"){
        this.dialog281Props.visible=true
      }
      // this.$message('click on item ' + command);
    },
    uploadExcel(evt) {
      const files = evt.target.files
      this.files=files;
      this.formData3.pathinfo="";
      debugger
      if (files == null || files.length == 0) {
        return
      }
      this.formData3.pathinfo = files[0].name
    },
    upExports(){
      document.querySelector('#fileinput').click()
    },
    async incorporate() {
      if (this.multipleSelection.length < 2) {
        this.showMessage({
          type: 'success',
          msg: '请选择多条数据数据'
        })
        return
      }
      if (this.multipleSelection.length > 3) {
        this.showMessage({
          type: 'success',
          msg: '最多选择三条'
        })
        return
      }
      let cluesState = 0
      for (let i = 0; i < this.multipleSelection.length; i++) {
        var multipleSelectionElement = this.multipleSelection[i];

        if (multipleSelectionElement.cluesState == "0" || multipleSelectionElement.cluesState == "1" || multipleSelectionElement.cluesState == "3") {
          if (cluesState < parseInt(multipleSelectionElement.cluesState)) {
            cluesState = parseInt(multipleSelectionElement.cluesState)
          }

        } else {
          this.showMessage({
            type: 'success',
            msg: '选中数据目前无法合并'
          })
          return
        }
      }
      this.formData.cluesState = cluesState

      this.cluesNameOptions = []
      this.cluesPoolOptions = []
      this.cluesSources1Options = []
      this.cluesContactsOptions = []
      this.phoneNumberOptions = []
      this.companyOptions = []
      this.department1Options = []
      this.positionOptions = []
      this.regionOptions = []
      this.addrOptions = []
      this.genderOptions = []
      this.birthDateOptions = []
      this.telephoneOptions = []
      this.emailOptions = []
      this.wechatOptions = []
      this.qqOptions = []
      this.microblogOptions = []
      this.remarksOptions = []
      this.myclueProductsOptions = []

      //匹配对，进行合并
      for (let i = 0; i < this.multipleSelection.length; i++) {
        var multipleSelectionElement = this.multipleSelection[i];
        this.cluesNameOptions.push({
          "label": multipleSelectionElement.cluesName,
          "value": multipleSelectionElement.cluesName
        })
        this.cluesPoolOptions.push({
          "label": multipleSelectionElement.cluesPool.cplName,
          "value": multipleSelectionElement.cluesPool.id
        })
        this.cluesSources1Options.push({
          "label": multipleSelectionElement.cluesSources,
          "value": multipleSelectionElement.cluesSources
        })
        this.cluesContactsOptions.push({
          "label": multipleSelectionElement.cluesContacts,
          "value": multipleSelectionElement.cluesContacts
        })
        this.phoneNumberOptions.push({
          "label": multipleSelectionElement.phoneNumber,
          "value": multipleSelectionElement.phoneNumber
        })
        this.companyOptions.push({
          "label": multipleSelectionElement.company,
          "value": multipleSelectionElement.company
        })
        this.department1Options.push({
          "label": multipleSelectionElement.department,
          "value": multipleSelectionElement.department
        })
        this.positionOptions.push({
          "label": multipleSelectionElement.position,
          "value": multipleSelectionElement.position
        })
        this.regionOptions.push({
          "label": multipleSelectionElement.region,
          "value": multipleSelectionElement.region
        })
        this.addrOptions.push({
          "label": multipleSelectionElement.addr,
          "value": multipleSelectionElement.addr
        })
        this.genderOptions.push({
          "label": multipleSelectionElement.gender,
          "value": multipleSelectionElement.gender
        })
        this.birthDateOptions.push({
          "label": multipleSelectionElement.birthDate,
          "value": multipleSelectionElement.birthDate
        })
        this.telephoneOptions.push({
          "label": multipleSelectionElement.telephone,
          "value": multipleSelectionElement.telephone
        })
        this.emailOptions.push({
          "label": multipleSelectionElement.email,
          "value": multipleSelectionElement.email
        })
        this.wechatOptions.push({
          "label": multipleSelectionElement.wechat,
          "value": multipleSelectionElement.wechat
        })
        this.qqOptions.push({
          "label": multipleSelectionElement.qq,
          "value": multipleSelectionElement.qq
        })
        this.microblogOptions.push({
          "label": multipleSelectionElement.microblog,
          "value": multipleSelectionElement.microblog
        })
        this.remarksOptions.push({
          "label": multipleSelectionElement.remarks,
          "value": multipleSelectionElement.remarks
        })


        let search_List = {
          params: [{'columnName': 'cnt_myclue_id', 'queryType': '=', 'value': multipleSelectionElement.id}]
        }
        await listCrmMyclueProductsAll(search_List)
          .then((responseData) => {
            if (responseData.code == 100) {
              let label = ""
              for (let j = 0; j < responseData.data.length; j++) {
                if (j + 1 == responseData.data.length) {
                  label += responseData.data[i].pdZname
                } else {
                  label += responseData.data[i].pdZname + ","
                }

              }
              this.myclueProductsOptions.push({
                "label": label,
                "value": multipleSelectionElement.id,
                "data":responseData.data
              })
            } else {
              this.showMessage(responseData)
            }
            this.resetLoad()
          })
          .catch((error) => {
            this.outputError(error)
          })


      }
      //这里为什么要给- -一个无作用的参数是为了直接赋值后参数少了出错
      this.multipleSelection[0].myclueProducts=""
      this.formData = JSON.parse(JSON.stringify(this.multipleSelection[0]));
      this.formData.id = ''
      this.formData.myclueProducts = JSON.parse(JSON.stringify(this.multipleSelection[0])).id
      this.dialog189Props.visible = true
    },
    IncorporatesaveCrmClues(){
      saveCrmClues(this.formData)
        .then((responseData) => {
          if (responseData.code == 100) {
            //开始添加意向产品
            var myclueProducts = this.formData.myclueProducts;
            for (let i = 0; i < this.myclueProductsOptions.length; i++) {
              if (myclueProducts==this.myclueProductsOptions[i].value&&this.myclueProductsOptions[i].data.length!=0){
                for (let j = 0; j < this.myclueProductsOptions[i].data.length; j++) {
                  var datum = this.myclueProductsOptions[i].data[j];
                  datum.id=''
                  datum.cntMyclueId = responseData.data
                  saveCrmMyclueProducts(datum)
                    .then((responseData) => {
                      if (responseData.code == 100) {
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
                }

              }
            }
            for (let i = 0; i < this.multipleSelection.length; i++) {
              var multipleSelectionElement = this.multipleSelection[i];
              multipleSelectionElement.cluesState="4"
              saveCrmClues(multipleSelectionElement)
                .then((responseData) => {
                  if (responseData.code == 100) {
                    this.showMessage({
                      type: 'success',
                      msg: multipleSelectionElement.cluesName+'修改状态成功'
                    })
                  } else {
                    this.showMessage(responseData)
                  }
                  this.resetLoad()
                  this.getList()

                })
                .catch((error) => {
                  this.outputError(error)
                })

            }
          } else {
            this.showMessage(responseData)
          }
          this.dialog188Props.visible=false
          this.resetLoad()
          this.getList()

        })
        .catch((error) => {
          this.outputError(error)
        })
      this.dialog189Props.visible=false
    },
    handelConfirm(){
      this.$confirm('确认将选择的线索合并？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.IncorporatesaveCrmClues();
      }).catch(() => {
        this.dialog189Props.visible=false
      });
    },
    closeIncorporate(){
      this.dialog189Props.visible=false
    },
    transfer(){

      if (this.multipleSelection.length>1){
        this.showMessage({
          type: 'success',
          msg: '请选择单条数据'
        })
        return
      }else if (this.multipleSelection.length==0){
        this.showMessage({
          type: 'success',
          msg: '请选择单条数据'
        })
        return
      }
      if (this.multipleSelection[0].cluesState== "0"||this.multipleSelection[0].cluesState== "1"||this.multipleSelection[0].cluesState== "2"){
        this.showMessage({
          type: 'success',
          msg: '选中数据目前无法转移'
        })
        return
      }
      this.getList3()
      this.dialog188Props.visible=true
    },
    CustomClose(){
      this.dialog143Props.visible=false
    },
    transferClose(){
      this.multipleSelection3=[]
      this.dialog188Props.visible=false
    },
    editCrmClues(){
      if (this.multipleSelection3.length>1){
        this.showMessage({
          type: 'success',
          msg: '请选择单条数据'
        })
        return
      }else if (this.multipleSelection3.length==0){
        this.showMessage({
          type: 'success',
          msg: '请选择单条数据'
        })
        return
      }
      this.$confirm('确认将'+this.multipleSelection[0].cluesName+'转移给'+this.multipleSelection3.name+'', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.multipleSelection[0].cluesResponsible=this.multipleSelection3.id
        saveCrmClues(this.multipleSelection[0])
          .then((responseData) => {
            if (responseData.code == 100) {
              this.showMessage({
                type: 'success',
                msg: '保存成功'
              })
            } else {
              this.showMessage(responseData)
            }
            this.dialog188Props.visible=false
            this.resetLoad()
            this.getList()

          })
          .catch((error) => {
            this.outputError(error)
          })
      }).catch(() => {

      });
    },
    addCustom(){
      this.setLoad()
      debugger
      if (this.editForm146Data.CREATE){
        if (this.editForm146Data.nmae==''||this.editForm146Data.money==''){
          this.showMessage({
            type: 'success',
            msg: '请填写完整'
          })
        return
        }
        this.editForm146Data.cusStage=="02"
      }
      saveCrmCustomer(this.editForm146Data)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.multipleSelection[0].cluesState=3
            saveCrmClues(this.multipleSelection[0])
              .then((responseData) => {
                if (responseData.code == 100) {
                  this.showMessage({
                    type: 'success',
                    msg: '保存成功'
                  })
                } else {
                  this.showMessage(responseData)
                }
                this.resetLoad()
              })
              .catch((error) => {
                this.outputError(error)
              })
            if (this.editForm146Data.CREATE){
              let editss={
                customer:{
                  id:responseData.data,
                },
                oppName:this.editForm146Data.name,
                oppSource:"市场活动",
                oppStage:"沟通",
                oppContacts:currentUser.name,
                oppPay:this.editForm146Data.money,
              }
              saveCrmOpportunity(editss)
                .then((responseData) => {
                  if (responseData.code == 100) {
                    this.showMessage({
                      type: 'success',
                      msg: '保存成功'
                    })
                  } else {
                    this.showMessage(responseData)
                  }
                  this.resetLoad()
                })
                .catch((error) => {
                  this.outputError(error)
                })
            }

          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
      this.dialog143Props.visible=false
    },
    transformCustom(){
      if (this.multipleSelection.length>1){
        this.showMessage({
          type: 'success',
          msg: '转换只能选中一个'
        })
        return
      }else if (this.multipleSelection.length==0){
         this.showMessage({
          type: 'success',
          msg: '请选择一个转换'
        })
        return
      }
      if (this.multipleSelection[0].cluesState!= "1"){
        this.showMessage({
          type: 'success',
          msg: '选中数据目前无法转换'
        })
        return
      }
     this.dialog143Props.visible=true
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleSelectionChange3(val) {
      this.multipleSelection3 = val;
    },
    getCluesState(e) {
      for (var i = 0; i < this.cluesStateOptions.length; i++) {
        if (this.cluesStateOptions[i].value == e) { //dictValue，dictLabel保持和上面定义一致
          return this.cluesStateOptions[i].name;
        }
      }
    },
    getCluesSources(e) {
      for (var i = 0; i < this.cluesSourcesOptions.length; i++) {
        if (this.cluesSourcesOptions[i].value == e) { //dictValue，dictLabel保持和上面定义一致
          return this.cluesSourcesOptions[i].name;
        }
      }
    },
    getRegion(e) {
      for (var i = 0; i < this.selectOptions4.length; i++) {
        if (this.selectOptions4[i].value == e) { //dictValue，dictLabel保持和上面定义一致
          return this.selectOptions4[i].name;
        }
      }
    },
    getGender(e) {
      for (var i = 0; i < this.genderOptionsS.length; i++) {
        if (this.genderOptionsS[i].value == e) { //dictValue，dictLabel保持和上面定义一致
          return this.genderOptionsS[i].name;
        }
      }
    },
    getList() {
      this.setLoad()
      this.multipleSelection=[]
      /* 查询参数 和数据权限 */
      this.search.params = []
      if (this.isQueryConditionPanel) {
        this.search.params = this.search.params.concat(this.compositeCondition())
      } else {
        this.search.params.push({
          columnName: 'clues_name',

          queryType: 'like',

          value: !validatenull(this.queryFormData.cluesName) ? this.queryFormData.cluesName : null
        })

        this.search.params.push({
          columnName: 'clues_pool_id',

          queryType: '=',

          value: !validatenull(this.queryFormData.clues_pool_id) ? this.queryFormData.clues_pool_id : null
        })
debugger
        this.search.params.push({
          columnName: 'clues_sources',

          queryType: '=',

          value: !validatenull(this.queryFormData.cluesSources) ? this.queryFormData.cluesSources : null
        })

        this.search.params.push({
          columnName: 'clues_state',

          queryType: '=',

          value: !validatenull(this.queryFormData.cluesState) ? this.queryFormData.cluesState : null
        })
      }
      this.search.params.push({
        columnName: 'clues_responsible',

        queryType: '=',

        value: currentUser.id
      })
      this.search.params.push({
        columnName: 'del_flag',

        queryType: '=',

        value: "0"
      })
      // 数据权限: 线索crm_clues
      this.pushDataPermissions(this.search.params, this.$route.meta.routerId, this.tableId)
      listCrmCluesPage(this.search)
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
     async getListAll() {
       let tableData = []
       this.setLoad()
       this.multipleSelection = []
       /* 查询参数 和数据权限 */
       this.search.params = []
       if (this.isQueryConditionPanel) {
         this.search.params = this.search.params.concat(this.compositeCondition())
       } else {
         this.search.params.push({
           columnName: 'clues_name',

           queryType: 'like',

           value: !validatenull(this.queryFormData.cluesName) ? this.queryFormData.cluesName : null
         })

         this.search.params.push({
           columnName: 'clues_pool_id',

           queryType: '=',

           value: !validatenull(this.queryFormData.clues_pool_id) ? this.queryFormData.clues_pool_id : null
         })
debugger
         this.search.params.push({
           columnName: 'clues_sources',

           queryType: '=',

           value: !validatenull(this.queryFormData.cluesSources) ? this.queryFormData.cluesSources : null
         })

         this.search.params.push({
           columnName: 'clues_state',

           queryType: '=',

           value: !validatenull(this.queryFormData.cluesState) ? this.queryFormData.cluesState : null
         })
       }

       this.search.params.push({
         columnName: 'clues_responsible',

         queryType: '=',

         value: currentUser.id
       })
       this.search.params.push({
         columnName: 'del_flag',

         queryType: '=',

         value: "0"
       })
       // 数据权限: 线索crm_clues
       this.pushDataPermissions(this.search.params, this.$route.meta.routerId, this.tableId)
       await listCrmCluesAll(this.search)
         .then((responseData) => {
           if (responseData.code == 100) {
             tableData = responseData.data
           } else {
             this.showMessage(responseData)
           }
           this.resetLoad()
         })
         .catch((error) => {
           this.outputError(error)
         })
       return tableData;
     },
    onSearch() {
      if (this.isQueryConditionPanel) {
        this.search.offset = 0
        this.tableDataPage = 1
        this.getList()
      } else {
        this.$refs['queryForm'].validate((valid) => {
          if (valid) {
            this.search.offset = 0
            this.tableDataPage = 1
            this.getList()
          } else {
            return false
          }
        })
      }
    },
    onSearch3() {
      if (this.isQueryConditionPanel) {
        this.search3.offset = 0
        this.tableDataPage3 = 1
        this.getList3()
      } else {
        this.$refs['queryForm'].validate((valid) => {
          if (valid) {
            this.search3.offset = 0
            this.tableDataPage3 = 1
            this.getList3()
          } else {
            return false
          }
        })
      }
    },
    onSizeChange(val) {
      this.tableDataPage = 1
      this.search.limit = val
      this.search.offset = (this.tableDataPage - 1) * val
      this.getList()
    },
    onSizeChange3(val) {
      this.tableDataPage3 = 1
      this.search3.limit = val
      this.search3.offset = (this.tableDataPage3 - 1) * val
      this.getList3()
    },
    onCurrentChange(val) {
      this.search.offset = (val - 1) * this.search.limit
      this.tableDataPage = val
      this.getList()
    },
    onCurrentChange3(val) {
      this.search3.offset = (val - 1) * this.search3.limit
      this.tableDataPage3 = val
      this.getList3()
    },
    getList3() {
      this.setLoad()
      /* 查询参数 和数据权限 */
      this.search3.params = []

      this.search3.params.push({
        columnName: 'company_id',
        queryType: '=',
        value: currentUser.company.id
      })

      if (this.isQueryConditionPanel) {
        this.search3.params = this.search3.params.concat(this.compositeCondition())
      } else {
        this.search3.params.push({
          columnName: 'department_id',
          queryType: '=',

          value: !validatenull(this.queryFormData3.department.id) ? this.queryFormData3.department.id : null
        })

        this.search3.params.push({
          columnName: 'name',
          queryType: 'like',

          value: !validatenull(this.queryFormData3.name) ? this.queryFormData3.name : null
        })
      }
      // 数据权限: 用户sys_user
      listUserPage(this.search3)
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
    async pageInit() {
      this.setLoad()
      this.initOptions(this.queryModel)
      this.onSearch()
      listResourcePermission(this.$route.meta.routerId)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.permission.view = responseData.data.find((item) => {
              return item.permission === 'crmClues:read'
            })
            this.permission.export = responseData.data.find((item) => {
              return item.permission === 'crmClues:export'
            })

            this.permission.add = responseData.data.find((item) => {
              return item.permission === 'crmClues:create'
            })
            this.permission.edit = responseData.data.find((item) => {
              return item.permission === 'crmClues:update'
            })
            this.permission.remove = responseData.data.find((item) => {
              return item.permission === 'crmClues:delete'
            })
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
      this.$refs.editForm.$emit('openViewDialog', scope.row.id)
    },

    onCreate() {
      this.$refs.editForm.$emit('openAddDialog')
    },
    onEdit() {
      if (this.multipleSelection.length>1){
         this.showMessage({
          type: 'success',
          msg: '编辑只能选中一个'
        })
        return
      }else if (this.multipleSelection.length==0){
        return this.showMessage({
          type: 'success',
          msg: '请选择一个编辑'
        })
        return
      }
      if (this.multipleSelection[0].cluesState== "0"||this.multipleSelection[0].cluesState== "1"){
        this.$refs.editSForm.$emit('openEditDialog', this.multipleSelection[0].id)


      }else {
        this.showMessage({
          type: 'success',
          msg: '当前数据状态无法编辑数据'
        })
        return
      }

    },
    onCopy(scope) {
      this.$refs.editForm.$emit('openCopyDialog', scope.row.id)
    },
    onDelete() {
      if (this.multipleSelection.length==0){
        this.showMessage({
          type: 'success',
          msg: '请选择数据'
        })
        return
      }
      this.$confirm('确认删除该信息？', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.setLoad()
          bulkDeleteCrmClues(this.multipleSelection)
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

    // listSelectOptions() {
    //   let search_List = {
    //     params: []
    //   }
    //   // filter条件
    //   search_List.params.push.apply(search_List.params, [])
    //
    //   // 数据权限: 线索池管理crm_clue_pool
    //   this.pushDataPermissions(
    //     search_List.params,
    //     this.$route.meta.routerId,
    //     '1709759135450792838'
    //   )
    //   (search_List).then((responseData) => {
    //     if (responseData.code == 100) {
    //       this.selectOptions = responseData.data
    //     } else {
    //       this.showMessage(responseData)
    //     }
    //   })
    // },

    listSelectOptions() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])

      listMyCrmCluePoolAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.selectOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
    },

    listCluesStateOptions() {
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
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "CLUE_SOURCE"}]
      }
      listDictItemAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.cluesSourcesOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
      search_List = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "CRM_DICT_CUSTOMER_TYPE"}]
      }
      listDictItemAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.customOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
      search_List = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "AREA"}]
      }
      listDictItemAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.selectOptions4 = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
      search_List = {
        params: [{'columnName':'dictType.code', 'queryType': '=', 'value': "GENDER"}]
      }
      listDictItemAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.genderOptionsS = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
    },

    // 初始化自定义类型选择框选项变量
    initOptions(This) {
      this.listSelectOptions()

      // this.listSelectOptions()

      this.listCluesStateOptions()
    }
  },
  watch: {},
  mounted() {
    this.pageInit()

    this.columnDrop(this.$refs.table)
  }
}
</script>
