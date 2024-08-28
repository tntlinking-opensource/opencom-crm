            const conditions = [
              {
                name: 'id',
                comments: 'id',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'contract_id',
                comments: '合同id',
                tag: 'el-select',
                queryType: '=',
                operations: [{"value":"=","label":"等于"},{"value":"<>","label":"不等于"},{"value":"in","label":"在列表"},{"value":"not in","label":"不在列表"}],
                attribute: {"labelField":"name","className":"CrmContract","fieldId":"id","tableId":"1740772218121937029","apiPath":"contract/crmContract","filter":""}
              },
              
              {
                name: 'sign_comp',
                comments: '签约方公司',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'contract_unit',
                comments: '合同单位',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'par_cust_unit',
                comments: '票面客户单位',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'item_name',
                comments: '项目名称',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'item_code',
                comments: '项目编号',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'invoice_type',
                comments: '发票类型',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'tax_num',
                comments: '税号',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'invoice_address_tel',
                comments: '地址、电话',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'invoice_bank_account',
                comments: '开户银行名称及账户 ',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'invoice_amount',
                comments: '发票金额',
                tag: 'number-input',
                queryType: '=',
                operations: [{"value":"=","label":"等于"},{"value":"<>","label":"不等于"},{"value":"between","label":"介于"},{"value":"not between","label":"不介于"}],
                attribute: {":min":0,":max":999999999999,":precision":2}
              },
              
              {
                name: 'tax_rate',
                comments: '税率',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'taxes',
                comments: '税金',
                tag: 'number-input',
                queryType: '=',
                operations: [{"value":"=","label":"等于"},{"value":"<>","label":"不等于"},{"value":"between","label":"介于"},{"value":"not between","label":"不介于"}],
                attribute: {":min":0,":max":999999999999,":precision":2}
              },
              
              {
                name: 'exclud_tax',
                comments: '不含税价',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'status',
                comments: '申请状态',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'invoice_print',
                comments: '票面打印内容',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'invoice_remark',
                comments: '发票票面备注',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'applicant_desc',
                comments: '申请人说明',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'company_name',
                comments: '公司名称',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'address',
                comments: '公司地址',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'recipient',
                comments: '收件人',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'phone',
                comments: '联系电话',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'postal_code',
                comments: '邮编',
                tag: 'number-input',
                queryType: '=',
                operations: [{"value":"=","label":"等于"},{"value":"<>","label":"不等于"},{"value":"between","label":"介于"},{"value":"not between","label":"不介于"}],
                attribute: {":min":0,":max":999999999999,":precision":0}
              },
              
              {
                name: 'invoice_content',
                comments: '发票内容',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'proc_inst',
                comments: '流程实例id',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'creator',
                comments: '创建人',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'create_time',
                comments: '创建时间',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'updator',
                comments: '修改人',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'update_time',
                comments: '修改时间',
                tag: 'el-input',
                queryType: 'like',
                operations: [{"value":"like","label":"包含"},{"value":"not like","label":"不包含"},{"value":"=","label":"等于"},{"value":"<>","label":"不等于"}],
                attribute: {}
              },
              
              {
                name: 'proc_status',
                comments: '流程状态',
                tag: 'el-select',
                queryType: '=',
                operations: [{"value":"=","label":"等于"},{"value":"<>","label":"不等于"},{"value":"in","label":"在列表"},{"value":"not in","label":"不在列表"}],
                attribute: {"labelField":"name","className":"DictItem","fieldId":"id","tableId":"1740772218121937029","apiPath":"sys/dictItem","filter":""}
              },
              
            
            ]
            
      
      
          
        
          
            
      
        
          
            
      
        
          
            
      
      
          
        
      
      
          
        
          
            
      
        
          
            
      
      
          
        
      
      
          
        
          
            
      
        
          
            
      
      
          
        
      
      
          
        
          
            
      
        
          
            
      
      
          
        
      
      
          
        
          
            
      
        
          
            
      
      
          
        
      
      
          
        
      
      
          
        
          
            
      
        
          
            
      
      
          
        
      
      
          
        
          
            
      
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
        
          
            
      
        
          
            
      
        
          
            
      
      
          
        
      
      
          
        
          
            
      
      
          
        
      
      
          
        
          
            
      
        
          
            
      
      
          
        
          
            
      
      
          
        
      
      
          
        
      
      
          
        
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
          
            
      
      
          
        
      
      
const metadata = [
	{
    id: '1740772218121937029',
    schemeId: '1740772218121937027',
    name: '发票管理',
    conditionPanel: conditions,
    type: 'main',
    dataRules:[]
  }
]
export default metadata