# 用户表
DROP TABLE IF EXISTS user_details;
CREATE TABLE user_details
(
    user_id         INT UNSIGNED   NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    username        VARCHAR(16)             DEFAULT '' COMMENT '用户名',
    password        VARCHAR(16)             DEFAULT '' COMMENT '密码',
    nickname        VARCHAR(16)             DEFAULT '' COMMENT '昵称',
    phone           VARCHAR(11)             DEFAULT '' COMMENT '手机',
    email           VARCHAR(20)             DEFAULT '' COMMENT '邮箱',
    create_time     DATETIME                DEFAULT NULL COMMENT '创建时间',
    update_time     DATETIME                DEFAULT NULL COMMENT '修改时间',
    expire_time     DATETIME                DEFAULT NULL COMMENT '登录过期时间',
    last_login_time DATETIME                DEFAULT NULL COMMENT '上次登录时间',
    deleted         ENUM ('0','1') NOT NULL DEFAULT '0' COMMENT '0=正常,1=逻辑删除',
    status          ENUM ('1','0') NOT NULL DEFAULT '1' COMMENT '0=冻结,1=正常',
    PRIMARY KEY (user_id),
    UNIQUE KEY (username) USING BTREE
) COMMENT ='用户表';
# 管理员用户
INSERT INTO user_details
VALUES (1, 'admin', 'Admin', 'Admin', NULL, NULL, NOW(), NOW(), NULL, NULL, '0', '1');
COMMIT;

# 角色表
DROP TABLE IF EXISTS role;
CREATE TABLE role
(
    role_id     INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    role_name   VARCHAR(16)  DEFAULT 'USER' COMMENT '角色名称',
    comments    VARCHAR(100) DEFAULT NULL COMMENT '备注',
    create_time DATETIME     DEFAULT NULL COMMENT '创建时间',
    update_time DATETIME     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (role_id),
    UNIQUE KEY (role_name)
) COMMENT ='角色表';
# 基本角色
INSERT INTO role
VALUES (1, 'Admin', '管理员', NOW(), NOW());
INSERT INTO role(role_name, comments, create_time, update_time)
VALUES ('User', '基本用户', NOW(), NOW());
COMMIT;

# 用户-角色表
DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role
(
    user_role_id INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户角色ID',
    user_id      INT UNSIGNED NOT NULL COMMENT '用户ID',
    role_id      INT UNSIGNED NOT NULL COMMENT '角色ID',
    create_time  DATETIME DEFAULT NULL COMMENT '创建时间',
    update_time  DATETIME DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (user_role_id)
) COMMENT ='用户-角色表';
# 管理员用户-管理员角色
INSERT INTO user_role
VALUES (1, 1, 1, NOW(), NOW());
COMMIT;

# 权限菜单表
DROP TABLE IF EXISTS menu;
CREATE TABLE menu
(
    menu_id     INT UNSIGNED   NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    parent_id   INT UNSIGNED   NOT NULL DEFAULT 0 COMMENT '上级菜单，0是顶级',
    title       VARCHAR(16)    NOT NULL DEFAULT '' COMMENT '标题',
    path        varchar(100)   NOT NULL DEFAULT '' COMMENT '路由路径',
    component   varchar(100)   NOT NULL DEFAULT '' COMMENT '组件路径',
    menu_type   ENUM ('0','1') NOT NULL DEFAULT '0' COMMENT '0=菜单,1=按钮',
    arrangement INT UNSIGNED   NOT NULL DEFAULT 0 COMMENT '排序',
    authority   TEXT           NOT NULL COMMENT '权限',
    target      varchar(100)   NOT NULL DEFAULT '' COMMENT '目标',
    icon        varchar(50)    NOT NULL DEFAULT '' COMMENT '图标',
    create_time DATETIME                DEFAULT NULL COMMENT '创建时间',
    update_time DATETIME                DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (menu_id)
) COMMENT ='权限菜单表';
# 示例权限菜单
INSERT INTO menu
VALUES (1, 0, '用户管理', '', '', '0', 0, 'sys:user:list', '', '', NOW(), NOW());
INSERT INTO menu(parent_id, title, path, component, menu_type, arrangement, authority, target, icon, create_time, update_time)
VALUES (0, '菜单管理', '', '', '0', 0, 'sys:menu:list', '', '', NOW(), NOW()),
(0, '角色管理', '', '', '0', 0, 'sys:role:list', '', '', NOW(), NOW()),

(1, '增加用户', '', '', '0', 0, 'sys:user:save', '', '', NOW(), NOW()),
(1, '删除用户', '', '', '0', 1, 'sys:user:remove', '', '', NOW(), NOW()),
(1, '查询用户', '', '', '0', 2, 'sys:user:list', '', '', NOW(), NOW()),
(1, '修改用户', '', '', '0', 3, 'sys:user:update', '', '', NOW(), NOW()),

(2, '增加菜单', '', '', '0', 0, 'sys:menu:save', '', '', NOW(), NOW()),
(2, '删除菜单', '', '', '0', 1, 'sys:menu:remove', '', '', NOW(), NOW()),
(2, '查询菜单', '', '', '0', 2, 'sys:menu:list', '', '', NOW(), NOW()),
(2, '修改菜单', '', '', '0', 3, 'sys:menu:update', '', '', NOW(), NOW()),

(3, '增加角色', '', '', '0', 0, 'sys:role:save', '', '', NOW(), NOW()),
(3, '删除角色', '', '', '0', 1, 'sys:role:remove', '', '', NOW(), NOW()),
(3, '查询角色', '', '', '0', 2, 'sys:role:list', '', '', NOW(), NOW()),
(3, '修改角色', '', '', '0', 3, 'sys:role:update', '', '', NOW(), NOW());
COMMIT;

# 角色-菜单表
DROP TABLE IF EXISTS role_menu;
CREATE TABLE role_menu
(
    role_menu_id INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色-菜单ID',
    role_id      INT UNSIGNED NOT NULL COMMENT '角色ID',
    menu_id      INT UNSIGNED NOT NULL COMMENT '菜单ID',
    create_time  DATETIME DEFAULT NULL COMMENT '创建时间',
    update_time  DATETIME DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (role_menu_id)
) COMMENT ='角色-菜单表';
# 示例角色-菜单
INSERT INTO role_menu
VALUES (1, 1, 1, NOW(), NOW());
COMMIT;