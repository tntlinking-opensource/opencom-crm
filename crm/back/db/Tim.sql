-- create by chaoxindev@yeah.net
-- 2023年8月30日
SET
    SESSION FOREIGN_KEY_CHECKS = 0;

-- 创建表结构

-- 产品
CREATE TABLE IF NOT EXISTS crm_products (
    id bigint(21) unsigned NOT NULL COMMENT '标识',
    pd_no varchar(255) NOT NULL UNIQUE COMMENT '产品编号',
    pd_name varchar(255) NOT NULL COMMENT '产品名称',
    pd_classify_id varchar(255) NOT NULL COMMENT '产品分类',
    pd_guide_price decimal(10, 2) NOT NULL COMMENT '指导价',
    pd_cost_price decimal(10, 2) NOT NULL COMMENT '成本价',
    pd_total int NOT NULL COMMENT '库存数量',
    pd_type_id varchar(255) NOT NULL COMMENT '产品类型',
    pd_status varchar(1) NOT NULL COMMENT '状态（0：启用；1：禁用）' DEFAULT '0',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除） ' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '产品';

-- 产品分类
CREATE TABLE IF NOT EXISTS crm_products_classify (
    id bigint(21) unsigned NOT NULL COMMENT '标识',
    parent_id bigint(21) NOT NULL COMMENT '父级类型' DEFAULT -1,
    pdc_code varchar(100) NOT NULL UNIQUE COMMENT '节点编号',
    pdc_name varchar(100) NOT NULL COMMENT '节点名称',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '产品分类';


-- 沟通
CREATE TABLE IF NOT EXISTS crm_communicate (
    id bigint(21) unsigned NOT NULL COMMENT '标识',
    cnt_type_id varchar(100) NOT NULL COMMENT '沟通类型',
    cnt_pname varchar(100) NOT NULL COMMENT '沟通人姓名',
    cnt_date varchar(24) NOT NULL COMMENT '沟通时间',
    cnt_content varchar(255) COMMENT '沟通内容',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '沟通';

-- 客户管理
CREATE TABLE IF NOT EXISTS crm_customer (
    id bigint(21) UNSIGNED NOT NULL COMMENT '标识',
    parent_id varchar(50) NOT NULL COMMENT '上级客户',
    cus_name varchar(300) NOT NULL COMMENT '客户名称',
    cus_type varchar(60) NOT NULL COMMENT '客户类型',
    cus_source varchar(60) NOT NULL COMMENT '客户来源',
    cus_contacts varchar(50)  COMMENT '联系人',
    cus_mobile varchar(30)  COMMENT '联系方式',
    cus_region varchar(100)  COMMENT '所在区域',
    cus_address varchar(100)  COMMENT '详细地址',
    cus_responsible varchar(64) NOT NULL COMMENT '负责人',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '客户管理';

-- 客户管理-沟通记录
CREATE TABLE IF NOT EXISTS crm_customer_cnt (
    id bigint(21) UNSIGNED NOT NULL COMMENT '标识',
    cus_id varchar(64) NOT NULL COMMENT '客户Id',
    cnt_id varchar(64) NOT NULL COMMENT '沟通记录Id',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '客户管理-沟通记录';

-- 客户管理-产品
CREATE TABLE IF NOT EXISTS crm_customer_product (
    id bigint(21) UNSIGNED NOT NULL COMMENT '标识',
    cus_id varchar(64) NOT NULL COMMENT '客户Id',
    product_id varchar(64) NOT NULL COMMENT '产品Id',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '客户管理-产品';

-- 客户管理-商机
CREATE TABLE IF NOT EXISTS crm_customer_opp (
    id bigint(21) UNSIGNED NOT NULL COMMENT '标识',
    cus_id varchar(64) NOT NULL COMMENT '客户Id',
    opp_id varchar(64) NOT NULL COMMENT '商机Id',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '客户管理-商机';

-- 客户管理-合同
CREATE TABLE IF NOT EXISTS crm_customer_cot (
    id bigint(21) UNSIGNED NOT NULL COMMENT '标识',
    cus_id varchar(64) NOT NULL COMMENT '客户Id',
    cot_id varchar(64) NOT NULL COMMENT '合同Id',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '客户管理-合同';

-- 客户管理-回款
CREATE TABLE IF NOT EXISTS crm_customer_pay (
    id bigint(21) UNSIGNED NOT NULL COMMENT '标识',
    cus_id varchar(64) NOT NULL COMMENT '客户Id',
    pay_id varchar(64) NOT NULL COMMENT '回款Id',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '客户管理-回款';

-- 客户管理-联系人
CREATE TABLE IF NOT EXISTS crm_customer_link (
    id bigint(21) UNSIGNED NOT NULL COMMENT '标识',
    cus_id varchar(64) NOT NULL COMMENT '客户Id',
    link_id varchar(64) NOT NULL COMMENT '联系人Id',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '客户管理-联系人';


-- 商机管理
CREATE TABLE IF NOT EXISTS crm_opportunity (
    id bigint(21) UNSIGNED NOT NULL COMMENT '标识',
    customer_id varchar(300) NOT NULL COMMENT '客户',
    opp_name varchar(300) NOT NULL COMMENT '商机名称',
    opp_source varchar(2) NOT NULL COMMENT '商机来源',
    opp_contacts varchar(50) NOT NULL COMMENT '联系人',
    opp_department varchar(100) NOT NULL COMMENT '所属部门',
    opp_responsible varchar(64) NOT NULL COMMENT '负责人',
    opp_respondepartId varchar(64) NOT NULL COMMENT '负责人部门id',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '商机管理';

-- 商机管理-产品
CREATE TABLE IF NOT EXISTS crm_opportunity_product (
    id bigint(21) UNSIGNED NOT NULL COMMENT '标识',
    opp_id varchar(64) NOT NULL COMMENT '商机Id',
    product_id varchar(64) NOT NULL COMMENT '产品Id',
    sigle_num bigint(21) NOT NULL COMMENT '数量',
    discount decimal(10, 4) NOT NULL COMMENT '折扣',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '商机管理-产品';

-- 商机管理-合同
CREATE TABLE IF NOT EXISTS crm_opportunity_cot (
    id bigint(21) UNSIGNED NOT NULL COMMENT '标识',
    opp_id varchar(64) NOT NULL COMMENT '商机Id',
    cot_id varchar(64) NOT NULL COMMENT '合同Id',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '商机管理-合同';

-- 商机管理-回款
CREATE TABLE IF NOT EXISTS crm_opportunity_pay (
    id bigint(21) UNSIGNED NOT NULL COMMENT '标识',
    opp_id varchar(64) NOT NULL COMMENT '商机Id',
    pay_id varchar(64) NOT NULL COMMENT '回款Id',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '商机管理-回款';

-- 商机管理-联系人
CREATE TABLE IF NOT EXISTS crm_opportunity_link (
    id bigint(21) UNSIGNED NOT NULL COMMENT '标识',
    opp_id varchar(64) NOT NULL COMMENT '商机Id',
    link_id varchar(64) NOT NULL COMMENT '联系人Id',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '商机管理-联系人';


-- 线索池管理
CREATE TABLE IF NOT EXISTS crm_clue_pool (
    id bigint(21) UNSIGNED NOT NULL COMMENT '标识',
    cpl_name varchar(300) NOT NULL COMMENT '线索池名称',
    cpl_responsible varchar(300) NOT NULL COMMENT '线索池负责人',
    cpl_rule_type varchar(60) NOT NULL COMMENT '分配规则 手动、自动',
    -- 手动规则
    cpl_member_claim_rules varchar(2) COMMENT '成员领取规则',
    cpl_member_claim_limit bigint(21) COMMENT '分配领取上限',
    -- 自动规则
    cpl_auto_where_1 varchar(300) COMMENT '自动分配规则1',
    cpl_auto_where_2 varchar(300) COMMENT '自动分配规则2',
    cpl_auto_num varchar(300) COMMENT '自动分配数量',
    -- 自动回收
    cpl_recycle_day varchar(60) NOT NULL COMMENT '自动回收天',
    cpl_recycle_status varchar(60) NOT NULL COMMENT '自动回收状态',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '线索池管理';

-- 线索池管理-成员
CREATE TABLE IF NOT EXISTS crm_clue_pool_member (
    id bigint(21) UNSIGNED NOT NULL COMMENT '标识',
    cpl_id varchar(64) NOT NULL COMMENT '商机Id',
    member_id varchar(64) NOT NULL COMMENT '联系人Id',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL COMMENT '删除标记（0：正常；1：删除）' DEFAULT '0',
    remarks varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '线索池管理-成员';


-- 线索
CREATE TABLE IF NOT EXISTS crm_clues (
    id bigint(21) UNSIGNED NOT NULL COMMENT '标识',
    clues_pool_id varchar(255) NOT NULL COMMENT '所属线索池',
    clues_responsible varchar(50) NOT NULL COMMENT '线索负责人',
    clues_state varchar(50) NOT NULL COMMENT '线索状态 公海 个人 回收',
    clues_name varchar(255) NOT NULL COMMENT '线索名称',
    clues_sources varchar(60) NOT NULL COMMENT '线索来源',
    clues_contacts varchar(50) NOT NULL COMMENT '线索联系人',
    phone_number varchar(15) NOT NULL COMMENT '手机号',
    email varchar(60)  COMMENT '邮箱',
    company varchar(100)  COMMENT '公司',
    department varchar(100)  COMMENT '所属部门',
    position varchar(60) COMMENT '职位',
    region varchar(100)  COMMENT '所在区域',
    gender varchar(60) COMMENT '性别（0：男；1：女；9：未知）',
    birth_date varchar(60)  COMMENT '出生日期',
    wechat varchar(60)  COMMENT '微信',
    microblog varchar(60)  COMMENT '微博',
    qq varchar(60)  COMMENT 'QQ',
    addr varchar(200) COMMENT '详细地址',
    create_by varchar(64) NOT NULL COMMENT '创建者',
    create_date varchar(24) NOT NULL COMMENT '创建时间',
    update_by varchar(64) NOT NULL COMMENT '更新者',
    update_date varchar(24) NOT NULL COMMENT '更新时间',
    del_flag varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0：正常；1：逻辑删除）',
    remarks varchar(500) COMMENT '备注',
    PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '线索';











-- 建表模板
-- CREATE TABLE IF NOT EXISTS  xxx
-- (
--     -- 标识
--     id bigint(21) unsigned NOT NULL COMMENT '标识',
--     -- 备注信息
--     remarks varchar(255) COMMENT '备注信息',
--     -- 创建者
--     create_by varchar(64) NOT NULL COMMENT '创建者',
--     -- 创建时间
--     create_date varchar(24) NOT NULL COMMENT '创建时间',
--     -- 更新者
--     update_by varchar(64) NOT NULL COMMENT '更新者',
--     -- 更新时间
--     update_date varchar(24) NOT NULL COMMENT '更新时间',
--     -- 删除标记（0：正常；1：删除）
--     del_flag varchar(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
--     PRIMARY KEY (id)
-- ) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 更新表结构模板
-- ALTER TABLE xxx
-- 	ADD FOREIGN KEY (router_id)
-- 	REFERENCES sys_router (id)
-- 	ON UPDATE RESTRICT
-- 	ON DELETE RESTRICT
-- ;

-- 创建索引模板
-- CREATE INDEX send_id USING BTREE ON notice_receive_site (send_id ASC);