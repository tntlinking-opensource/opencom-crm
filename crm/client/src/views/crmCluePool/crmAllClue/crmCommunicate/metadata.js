const conditions = [
  {
    name: 'id',
    comments: '标识',
    tag: 'el-input',
    queryType: 'like',
    operations: [
      { value: 'like', label: '包含' },
      { value: 'not like', label: '不包含' },
      { value: '=', label: '等于' },
      { value: '<>', label: '不等于' }
    ],
    attribute: {}
  },

  {
    name: 'cnt_type_id',
    comments: '沟通类型',
    tag: 'el-input',
    queryType: 'like',
    operations: [
      { value: 'like', label: '包含' },
      { value: 'not like', label: '不包含' },
      { value: '=', label: '等于' },
      { value: '<>', label: '不等于' }
    ],
    attribute: {}
  },

  {
    name: 'cnt_pname',
    comments: '沟通人姓名',
    tag: 'el-input',
    queryType: 'like',
    operations: [
      { value: 'like', label: '包含' },
      { value: 'not like', label: '不包含' },
      { value: '=', label: '等于' },
      { value: '<>', label: '不等于' }
    ],
    attribute: {}
  },

  {
    name: 'cnt_date',
    comments: '沟通时间',
    tag: 'el-input',
    queryType: 'like',
    operations: [
      { value: 'like', label: '包含' },
      { value: 'not like', label: '不包含' },
      { value: '=', label: '等于' },
      { value: '<>', label: '不等于' }
    ],
    attribute: {}
  },

  {
    name: 'cnt_content',
    comments: '沟通内容',
    tag: 'el-input',
    queryType: 'like',
    operations: [
      { value: 'like', label: '包含' },
      { value: 'not like', label: '不包含' },
      { value: '=', label: '等于' },
      { value: '<>', label: '不等于' }
    ],
    attribute: {}
  },

  {
    name: 'create_by',
    comments: '创建者',
    tag: 'el-input',
    queryType: 'like',
    operations: [
      { value: 'like', label: '包含' },
      { value: 'not like', label: '不包含' },
      { value: '=', label: '等于' },
      { value: '<>', label: '不等于' }
    ],
    attribute: {}
  },

  {
    name: 'create_date',
    comments: '创建时间',
    tag: 'el-date-picker',
    queryType: '=',
    operations: [
      { value: '=', label: '等于' },
      { value: '<>', label: '不等于' },
      { value: 'between', label: '介于' },
      { value: 'not between', label: '不介于' }
    ],
    attribute: { type: 'datetime', 'value-format': 'yyyy-MM-dd HH:mm:ss' }
  },

  {
    name: 'update_by',
    comments: '更新者',
    tag: 'el-input',
    queryType: 'like',
    operations: [
      { value: 'like', label: '包含' },
      { value: 'not like', label: '不包含' },
      { value: '=', label: '等于' },
      { value: '<>', label: '不等于' }
    ],
    attribute: {}
  },

  {
    name: 'update_date',
    comments: '更新时间',
    tag: 'el-date-picker',
    queryType: '=',
    operations: [
      { value: '=', label: '等于' },
      { value: '<>', label: '不等于' },
      { value: 'between', label: '介于' },
      { value: 'not between', label: '不介于' }
    ],
    attribute: { type: 'datetime', 'value-format': 'yyyy-MM-dd HH:mm:ss' }
  },

  {
    name: 'remarks',
    comments: '备注信息',
    tag: 'el-input',
    queryType: 'like',
    operations: [
      { value: 'like', label: '包含' },
      { value: 'not like', label: '不包含' },
      { value: '=', label: '等于' },
      { value: '<>', label: '不等于' }
    ],
    attribute: {}
  },

  {
    name: 'cut_next_date',
    comments: '下次沟通时间',
    tag: 'el-input',
    queryType: 'like',
    operations: [
      { value: 'like', label: '包含' },
      { value: 'not like', label: '不包含' },
      { value: '=', label: '等于' },
      { value: '<>', label: '不等于' }
    ],
    attribute: {}
  },

  {
    name: 'cut_remind',
    comments: '跟进提醒',
    tag: 'el-input',
    queryType: 'like',
    operations: [
      { value: 'like', label: '包含' },
      { value: 'not like', label: '不包含' },
      { value: '=', label: '等于' },
      { value: '<>', label: '不等于' }
    ],
    attribute: {}
  },

  {
    name: 'cut_remind_type',
    comments: '跟进提醒时间类别',
    tag: 'el-input',
    queryType: 'like',
    operations: [
      { value: 'like', label: '包含' },
      { value: 'not like', label: '不包含' },
      { value: '=', label: '等于' },
      { value: '<>', label: '不等于' }
    ],
    attribute: {}
  }
]

const metadata = [
  {
    id: '1720018472291172356',
    schemeId: '1720018472291172354',
    name: '沟通',
    conditionPanel: conditions,
    type: 'main',
    dataRules: []
  }
]
export default metadata
