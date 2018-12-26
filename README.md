# EmallSystem

## 数据库设计示例
> ### em_user_info(用户信息表)
| SQL 字段名 | 对应中文名   | 键类型                        |
| ---------- | ------------ | ----------------------------- |
| user_id    | 用户 id      | 主键（雪花码生成 数据中心：1） |
| user_name  | 用户名       |                               |
| telephone  | 用户手机号   | 唯一索引                      |
| address    | 居住地       |                               |
| email      | 用户邮箱     | 唯一索引                      |
| gender     | 性别         | 0:保密 1:男 2:女              |
| avatar_url | 头像图片地址 | 默认为："空白头像地址"        |
| birthday   | 用户生日     | TimeStamp                     |
| hometown   | 家乡         |                               |

> ### em_user_password (用户密码表)
| SQL 字段名      | 对应中文名 | 键类型                        |
| --------------- | ---------- | ----------------------------- |
| id              | 密码表 id  | 主键（自增）                  |
| user_id         | 用户 id    | 外键（emall_user_info.user_id |
| encrpt_password | 用户密码   | md5 加密字段                  |

> ### em_address （收货地址表）
| SQL 字段名     | 对应中文名   | 键类型                          |
| -------------- | ------------ | ------------------------------- |
| address_id     | 地址 id      | 主主键（雪花码生成 数据中心：2）   |
| user_id        | 用户 id      | 外键（emall_user_info.user_id） |
| receiver_name  | 收件人姓名   |                                 |
| receiver_phone | 收货人手机号 |                                 |
| address        | 地址信息     |                                 |
| address_detail | 详细地址     |                                 |
| postal_code    | 邮政编码     | 默认值为：000000                |
| is_default     | 默认地址     | 1-默认地址                      |

> ### em_item (商品信息表)
| SQL 字段名        | 对应中文名   | 键类型                        |
| ----------------- | ------------ | ----------------------------- |
| item_id           | 商品 id      | 主键（雪花码生成 数据中心：3） |
| category_id       | 分类 id      |                              |
| item_title        | 商品标题     |                               |
| item_sales        | 销量         |                              |
| item_main_image   | 商品主图片   |                               |
| item_detail_image | 商品详情图片 |                               |
| item_intro_image  | 商品介绍图片 |                               |
| create_time       | 商品创建时间 |                               |
| update_time       | 商品更新时间 |                               |

> ### em_item_attr_key（商品属性名表）
| SQL 字段名  | 对应中文名  | 键类型                  |
| ----------- | ----------- | ----------------------- |
| attr_key_id | 商品属性 id | 主键（自增）            |
| item_id     | 商品 id     | 外键（em_item.item_id） |
| attr_name   | 属性名称    | 尺寸、颜色……            |

> ### em_item_attr_val (商品属性值表)
| SQL 字段名  | 对应中文名  | 键类型                               |
| ----------- | ----------- | ------------------------------------ |
| attr_key_id | 商品属性 id | 外键（em_item_attr_key.attr_key_id） |
| item_id     | 商品 id     | 外键（em_item.item_id）              |
| symbol      | 属性编码    |                                 |
| attr_value  | 属性值      |                                      |
| attr_img    | 属性商品图片 |                                      |

> ### em_item_stock (商品库存表)
| SQL 字段名       | 对应中文名       | 键类型               |
| ---------------- | ---------------- | -------------------- |
| stock_id         | 库存 id          | 主键                 |
| item_id          | 商品 id          | 外键                 |
| attr_symbol_path | 商品规格搭配方式 | 外键组合             |
| price            | 所选对应的价格   |                      |
| item_status      | 商品状态         | 1:在售 2:下架 3:删除 |
| stock            | 所选规格剩余库存 |                      |


> ### em_shopping_cart 购物车表
| SQL 字段名 | 对应中文名   | 键类型                   |
| ---------- | ------------ | ------------------------ |
| cart_id    | 购物车 id    | 主键（雪花码生成 数据中心：4） |
| item_id    | 商品 id      | 外键                     |
| item_title | 商品标题      | 来自 em_item             |
| user_id    | 用户 id      | 外键（user_info.user_id) |
| stock_id   | 库存 id      | 外键                     |
| attr_vals  | 属性值       | 组合属性字段             |
| attr_img   | 属性商品图片  | 来自 em_item_attr_val   |
| price      | 价格         | 来自 em_item_stock      |
| amount     | 数量         |                         |

> ### em_category 商品分类表
| SQL 字段名    | 对应中文名 | 键类型                                 |
| ------------- | ---------- | -------------------------------------- |
| cat_id        | 类别 id    |                                        |
| cat_parent_id | 父类别 id  | 父类别id当id=0时说明是根结点，以及类别 |
| cat_name      | 类别名称   |                                        |
| cat_status    | 类别名称   | 类别状态 1-正常，2-已废弃              |
| sort_order    | 排序编号   | 同类展示顺序，数值相等则自然排序       |
| create_time   | 创建时间   |                                        |
| update_time   | 更新时间   |                                        |

> ### em_order 订单信息表
| SQL 字段名   | 对应中文名   | 键类型                                                                     |
| ------------ | ------------ | -------------------------------------------------------------------------- |
| order_id     | 订单号       | 主键（雪花码生成 数据中心：5）                                                  |
| user_id      | 用户 id      | 外键（user_info.user_id)                                                   |
| payment      | 实际支付金额 | 单位为元，保留两位小数                                                     |
| postage      | 运费         | 单位为元                                                                   |
| order_status | 订单状态     | 0-已取消，1-未付款，2-已付款，3-已发货，4-交易成功，5-交易成功，6-交易关闭 |
| total_price  | 订单总价     |                                                                            |
| payment_time | 支付时间     |                                                                            |
| send_time    | 发货时间     |                                                                            |
| end_time     | 交易完成时间 |                                                                            |
| close_time   | 交易关闭时间 |                                                                            |
| create_time  | 创建时间     |                                                                            |
| update_time  | 更新时间     |                                                                            |

> ### em_order_item 订单项表
| SQL 字段名         | 对应中文名     | 键类型 |
| ------------------ | -------------- | ------ |
| order_item_id      | 订单子项 id    | 主键（雪花码生成 数据中心：5）|
| user_id            | 用户 id        |        |
| order_id           | 订单号         |        |
| item_id            | 商品 id        |        |
| item_title         | 商品标题       |        |
| item_main_image    | 商品图片地址   |        |
| current_unit_price | 生成订单时单价 |        |
| amount             | 数量           |        |
| total_price        | 商品总价       |        |
| create_time        | 创建时间       |        |
| update_time        | 更新时间       |        |


> ### em_buyer_comment (买家评论表)
| SQL 字段名       | 对应中文名  | 键类型                         |
| ---------------- | ----------- | ------------------------------ |
| buyer_comment_id | 买家评论 id | 主键（雪花码生成 数据中心：6）      |
| order_item_id    | 所购商品订单 id |                                |
| user_id          | 买家 id     |                                |
| comment          | 评论内容    |                                |
| comment_type     | 评论类型    | 0-差评 1-中评 2-好评           |
| comment_img_url  | 评论图片    |                                |
| create_time      | 创建时间  |                               |
| update_time      | 更新时间  |                               |

> ### em_seller_comment (卖家评论表)
| SQL 字段名       | 对应中文名  | 键类型                         |
| --------------- | ---------- | ------------------------------ |
| seller_comment_id | 卖家评论 id | 主键（雪花码生成 数据中心：6） |
| order_item_id    | 所购商品订单 id |                                |
| user_id          | 买家 id    |                                |
| comment          | 评论内容    |                                |
| comment_type     | 评论类型    | 0-差评 1-中评 2-好评            |
| create_time      | 创建时间    |                               |
| update_time      | 更新时间    |                               |

## 数据库创建语句

```
DROP DATABASE IF EXISTS `db_emall`;
CREATE DATABASE `db_emall`;

USE `db_emall`;
DROP TABLE IF EXISTS `em_user_info`;
CREATE TABLE `em_user_info` (
  `user_id` VARCHAR(32) NOT NULL,
  `user_name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `telephone` VARCHAR(32) NOT NULL DEFAULT '',
  `address` VARCHAR(32) NOT NULL DEFAULT '',
  `email` VARCHAR(256) NOT NULL DEFAULT '',
  `gender` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '0-保密,1-男,2-女',
  `avatar_url` VARCHAR(256) NOT NULL DEFAULT '' COMMENT '默认为：空白头像地址',
  `birthday` DATETIME DEFAULT NULL,
  `hometown` VARCHAR(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_index` (`user_name`) USING BTREE,
  UNIQUE KEY `telephone_index` (`telephone`) USING BTREE,
  UNIQUE KEY `email_index` (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `em_user_password`;
CREATE TABLE `em_user_password` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(32) NOT NULL,
  `encrpt_password` VARCHAR(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `em_address`;
CREATE TABLE `em_address` (
  `address_id` VARCHAR(32) NOT NULL,
  `user_id` VARCHAR(32) NOT NULL,
  `receiver_name` VARCHAR(32) NOT NULL DEFAULT '',
  `receiver_phone` VARCHAR(32) NOT NULL DEFAULT '',
  `address` VARCHAR(32) NOT NULL DEFAULT '',
  `address_detail` VARCHAR(256) NOT NULL DEFAULT '',
  `postal_code` VARCHAR(10) NOT NULL DEFAULT '',
  `is_default` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `em_item`;
CREATE TABLE `em_item` (
  `item_id` VARCHAR(32) NOT NULL,
  `category_id` VARCHAR(32) NOT NULL,
  `item_title` VARCHAR(256) NOT NULL DEFAULT '',
  `item_sales` INT(11) NOT NULL DEFAULT '0',
  `item_main_image` VARCHAR(256) NOT NULL DEFAULT '',
  `item_detail_image` TEXT,
  `item_intro_image` VARCHAR(256) NOT NULL DEFAULT '',
  `create_time` DATETIME DEFAULT NULL,
  `update_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `em_item_attr_key`;
CREATE TABLE `em_item_attr_key` (
  `attr_key_id` INT(11) NOT NULL AUTO_INCREMENT,
  `item_id` VARCHAR(32) NOT NULL,
  `attr_name` VARCHAR(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`attr_key_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `em_item_attr_val`;
CREATE TABLE `em_item_attr_val` (
  `attr_key_id` INT(11) NOT NULL,
  `item_id` VARCHAR(32) NOT NULL,
  `symbol` INT(11) NOT NULL,
  `attr_value` VARCHAR(256) NOT NULL DEFAULT '',
  `attr_img` VARCHAR(256) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `em_item_stock`;
CREATE TABLE `em_item_stock` (
  `stock_id` INT(11) NOT NULL AUTO_INCREMENT,
  `item_id` VARCHAR(32) NOT NULL,
  `attr_symbol_path` VARCHAR(256) NOT NULL DEFAULT '',
  `price` DECIMAL(15, 2) NOT NULL DEFAULT '0.00',
  `item_status` TINYINT NOT NULL DEFAULT '1',
  `stock` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `em_shopping_cart`;
CREATE TABLE `em_shopping_cart` (
  `cart_id`  VARCHAR(32) NOT NULL,
  `item_id` VARCHAR(32) NOT NULL,
  `item_title` VARCHAR(256) NOT NULL DEFAULT '',
  `user_id` VARCHAR(32) NOT NULL,
  `stock_id` INT(11) NOT NULL,
  `attr_vals` TEXT COMMENT '尺寸:XL 颜色:红色……',
  `attr_img` VARCHAR(256) NOT NULL DEFAULT '',
  `price` DECIMAL(15, 2) NOT NULL DEFAULT '0.00',
  `amount` INT(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `em_category`;
CREATE TABLE `em_category` (
  `cat_id` INT(11) NOT NULL AUTO_INCREMENT,
  `cat_parent_id` INT(11) NOT NULL DEFAULT '0',
  `cat_name` VARCHAR(50) NOT NULL DEFAULT '',
  `cat_status` TINYINT(1) NOT NULL DEFAULT '1',
  `sort_order` INT(4) DEFAULT NULL,
  `create_time` DATETIME DEFAULT NULL,
  `update_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `em_order`;
CREATE TABLE `em_order` (
  `order_id` VARCHAR(32) NOT NULL,
  `user_id` VARCHAR(32) NOT NULL,
  `payment` DECIMAL(15,2) NOT NULL DEFAULT '0.00',
  `payment_type` INT(4) NOT NULL DEFAULT '1',
  `postage` DECIMAL(15,2) NOT NULL DEFAULT '0.00',
  `order_status` INT(10) NOT NULL DEFAULT '1',
  `total_price` DECIMAL(15,2) DEFAULT '0.00' COMMENT '商品总价,单位是元,保留两位小数',
  `payment_time` DATETIME DEFAULT NULL,
  `send_time` DATETIME DEFAULT NULL,
  `end_time` DATETIME DEFAULT NULL,
  `close_time` DATETIME DEFAULT NULL,
  `create_time` DATETIME DEFAULT NULL,
  `update_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `em_order_item`;
CREATE TABLE `em_order_item` (
  `order_item_id` VARCHAR(32) NOT NULL,
  `user_id` VARCHAR(32) NOT NULL,
  `order_id` VARCHAR(32) NOT NULL,
  `item_id` VARCHAR(32) NOT NULL,
  `item_title` VARCHAR(256) NOT NULL DEFAULT '',
  `item_main_image` VARCHAR(256) NOT NULL DEFAULT '',
  `current_unit_price` DECIMAL(15,2) DEFAULT '0.00' COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
  `amount` INT(11) NOT NULL DEFAULT '1',
  `total_price` DECIMAL(15,2) DEFAULT '0.00' COMMENT '商品总价,单位是元,保留两位小数',
  `create_time` DATETIME DEFAULT NULL,
  `update_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `order_id_index` (`order_id`) USING BTREE,
  KEY `order_id_user_id_index` (`user_id`,`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `em_buyer_comment`;
CREATE TABLE `em_buyer_comment` (
  `buyer_comment_id` VARCHAR(32) NOT NULL,
  `order_item_id` VARCHAR(32) NOT NULL,
  `user_id` VARCHAR(32) NOT NULL,
  `comment` VARCHAR(500) NOT NULL DEFAULT '',
  `comment_type` TINYINT NOT NULL DEFAULT '1',
  `comment_img_url` TEXT,
  `create_time` DATETIME DEFAULT NULL,
  `update_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`buyer_comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `em_seller_comment`;
CREATE TABLE `em_seller_comment` (
  `seller_comment_id` VARCHAR(32) NOT NULL,
  `order_item_id` VARCHAR(32) NOT NULL,
  `user_id` VARCHAR(32) NOT NULL,
  `comment` VARCHAR(500) NOT NULL DEFAULT '',
  `comment_type` TINYINT NOT NULL DEFAULT '1',
  `create_time` DATETIME DEFAULT NULL,
  `update_time` DATETIME DEFAULT NULL,
  PRIMARY KEY (`seller_comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

```
<font style="color:red;"> 注意：具体字段类型自己设定，个别设置遵照上述12345条，最终设计文档数据库设计遵照这份文档，所以请务必确保自己维护的数据库部分与这份文档保持一致，数据库修改与文档修改要同步！ </font>
