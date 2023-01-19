/*
 Navicat Premium Data Transfer

 Source Server         : 121.5.79.74
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : 121.5.79.74:3306
 Source Schema         : thyo_blogs

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 19/01/2023 10:19:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '创建用户',
  `creation_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `view_num` int(11) NULL DEFAULT NULL COMMENT '查看人数',
  `brief` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '简介',
  `class_one_id` int(11) NULL DEFAULT NULL COMMENT '一级id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `class_two_id` int(11) NULL DEFAULT NULL COMMENT '二级id',
  `preview` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '展示图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (16, '如何创建一个JAVA', 1, '2023-01-13 13:30:31', 110, 'Vue (读音 /vjuː/，类似于 view)是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。 Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。', 22, '<div><span>Vue (读音 /vjuː/，类似于 view)</span><span style=\"color: var(--el-text-color-regular); font-size: var(--font-size); text-align: initial;\">是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue</span><span style=\"font-size: var(--font-size); color: var(--el-text-color-regular); text-align: initial;\">&nbsp;被设计</span><span style=\"font-size: var(--font-size); color: var(--el-text-color-regular); text-align: initial;\">Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。 Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。</span></div>', 24, 'http://image.thyo.xyz/img/f4239328-b716-7242-c78b-505cdcc25800');
INSERT INTO `article` VALUES (17, 'Vue教程2', 1, '2023-01-20 13:30:31', 108, 'Vue (读音 /vjuː/，类似于 view)是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。 Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。', 21, '<div><span>Vue (读音 /vjuː/，类似于 view)</span><span style=\"color: var(--el-text-color-regular); font-size: var(--font-size); text-align: initial;\">是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue</span><span style=\"font-size: var(--font-size); color: var(--el-text-color-regular); text-align: initial;\">&nbsp;被设计</span><span style=\"font-size: var(--font-size); color: var(--el-text-color-regular); text-align: initial;\">Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。 Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。</span></div>', 20, 'http://image.thyo.xyz/img/380a267e-35a6-09bf-306a-88fd08da9b8a');
INSERT INTO `article` VALUES (18, '如何创建一个VueCli4脚手架', 1, '2023-01-20 13:30:32', 121, 'Vue (读音 /vjuː/，类似于 view)是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。 Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。', 21, '<p>Vue 是一个框架，也是一个生态。其功能覆盖了大部分前端开发常见的需求。但 Web 世界是十分多样化的，不同的开发者在 Web 上构建的东西可能在形式和规模上会有很大的不同。考虑到这一点，Vue 的设计非常注重灵活性和“可以被逐步集成”这个特点。根据你的需求场景，你可以用不同的方式使用 Vue：</p><ul><li>无需构建步骤，渐进式增强静态的 HTML</li><li>在任何页面中作为 Web Components 嵌入</li><li>单页应用 (SPA)</li><li>全栈 / 服务端渲染 (SSR)</li><li>Jamstack / 静态站点生成 (SSG)</li><li>开发桌面端、移动端、WebGL，甚至是命令行终端中的界面</li></ul><p>如果你是初学者，可能会觉得这些概念有些复杂。别担心！理解教程和指南的内容只需要具备基础的 HTML 和 JavaScript 知识。即使你不是这些方面的专家，也能够跟得上。</p><p>如果你是有经验的开发者，希望了解如何以最合适的方式在项目中引入 Vue，或者是对上述的这些概念感到好奇，我们在<a href=\"https://cn.vuejs.org/guide/extras/ways-of-using-vue.html\"><font color=\"#8baa4a\">使用 Vue 的多种方式</font></a>中讨论了有关它们的更多细节。</p><p>无论再怎么灵活，Vue 的核心知识在所有这些用例中都是通用的。即使你现在只是一个初学者，随着你的不断成长，到未来有能力实现更复杂的项目时，这一路上获得的知识依然会适用。如果你已经是一个老手，你可以根据实际场景来选择使用 Vue 的最佳方式，在各种场景下都可以保持同样的开发效率。这就是为什么我们将 Vue 称为“渐进式框架”：它是一个可以与你共同成长、适应你不同需求的框架。</p>', 20, 'http://image.thyo.xyz/img/380a267e-35a6-09bf-306a-88fd08da9b8a');
INSERT INTO `article` VALUES (19, 'Vue教程2', 1, '2023-01-20 13:30:31', 108, 'Vue (读音 /vjuː/，类似于 view)是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。 Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。', 21, '<div><span>Vue (读音 /vjuː/，类似于 view)</span><span style=\"color: var(--el-text-color-regular); font-size: var(--font-size); text-align: initial;\">是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue</span><span style=\"font-size: var(--font-size); color: var(--el-text-color-regular); text-align: initial;\">&nbsp;被设计</span><span style=\"font-size: var(--font-size); color: var(--el-text-color-regular); text-align: initial;\">Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。 Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。</span></div>', 20, 'http://image.thyo.xyz/img/380a267e-35a6-09bf-306a-88fd08da9b8a');
INSERT INTO `article` VALUES (20, 'Vue教程', 1, '2023-01-20 13:30:31', 108, 'Vue (读音 /vjuː/，类似于 view)是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。 Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。', 21, '<div><span>Vue (读音 /vjuː/，类似于 view)</span><span style=\"color: var(--el-text-color-regular); font-size: var(--font-size); text-align: initial;\">是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue</span><span style=\"font-size: var(--font-size); color: var(--el-text-color-regular); text-align: initial;\">&nbsp;被设计</span><span style=\"font-size: var(--font-size); color: var(--el-text-color-regular); text-align: initial;\">Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。 Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。</span></div>', 20, 'http://image.thyo.xyz/img/380a267e-35a6-09bf-306a-88fd08da9b8a');
INSERT INTO `article` VALUES (21, '如何创建一个JAVA', 1, '2023-01-13 13:30:31', 108, 'Vue (读音 /vjuː/，类似于 view)是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。 Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。', 22, '<div><span>Vue (读音 /vjuː/，类似于 view)</span><span style=\"color: var(--el-text-color-regular); font-size: var(--font-size); text-align: initial;\">是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue</span><span style=\"font-size: var(--font-size); color: var(--el-text-color-regular); text-align: initial;\">&nbsp;被设计</span><span style=\"font-size: var(--font-size); color: var(--el-text-color-regular); text-align: initial;\">Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。 Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。</span></div>', 24, 'http://image.thyo.xyz/img/f4239328-b716-7242-c78b-505cdcc25800');
INSERT INTO `article` VALUES (22, '如何创建一个JAVA', 1, '2023-01-13 13:30:31', 108, 'Vue (读音 /vjuː/，类似于 view)是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。 Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。', 22, '<div><span>Vue (读音 /vjuː/，类似于 view)</span><span style=\"color: var(--el-text-color-regular); font-size: var(--font-size); text-align: initial;\">是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue</span><span style=\"font-size: var(--font-size); color: var(--el-text-color-regular); text-align: initial;\">&nbsp;被设计</span><span style=\"font-size: var(--font-size); color: var(--el-text-color-regular); text-align: initial;\">Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。 Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。</span></div>', 25, 'http://image.thyo.xyz/img/f4239328-b716-7242-c78b-505cdcc25800');
INSERT INTO `article` VALUES (23, '如何创建一个JAVA1', 1, '2023-01-13 13:30:31', 108, 'Vue (读音 /vjuː/，类似于 view)是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。 Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。', 22, '<div><span>Vue (读音 /vjuː/，类似于 view)</span><span style=\"color: var(--el-text-color-regular); font-size: var(--font-size); text-align: initial;\">是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue</span><span style=\"font-size: var(--font-size); color: var(--el-text-color-regular); text-align: initial;\">&nbsp;被设计</span><span style=\"font-size: var(--font-size); color: var(--el-text-color-regular); text-align: initial;\">Vue (读音 /vjuː/，类似于 view) 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。 Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。它为现代前端工作流提供了功能齐备的构建设置。</span></div>', 24, 'http://image.thyo.xyz/img/f4239328-b716-7242-c78b-505cdcc25800');

-- ----------------------------
-- Table structure for article_comment
-- ----------------------------
DROP TABLE IF EXISTS `article_comment`;
CREATE TABLE `article_comment`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '所属文章',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人昵称',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `creation_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '上级评论Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article_comment
-- ----------------------------
INSERT INTO `article_comment` VALUES (6, 18, 'string', '游客', 2, '2023-01-17 11:43:35', '172.16.12.251', NULL);
INSERT INTO `article_comment` VALUES (7, 18, '<p>useUnicode=true&amp;characterEncoding=UTF-8<br></p>', '游客', 2, '2023-01-17 14:23:07', '172.16.12.251', NULL);
INSERT INTO `article_comment` VALUES (10, 18, '<p>123</p>', '游客', 2, '2023-01-17 14:54:08', '172.16.12.251', NULL);
INSERT INTO `article_comment` VALUES (11, 18, '<p>4</p>', '游客', 2, '2023-01-17 14:58:16', '172.16.12.251', NULL);
INSERT INTO `article_comment` VALUES (12, 18, '<p>5</p>', '游客', 2, '2023-01-17 14:58:41', '172.16.12.251', NULL);
INSERT INTO `article_comment` VALUES (13, 18, '<p>h啊哈哈太棒了！</p>', '游客', 2, '2023-01-17 14:59:09', '172.16.12.251', NULL);
INSERT INTO `article_comment` VALUES (14, 18, '<p>真的太棒了这文章，学习到了非常多的新知识真的是太感谢了！</p>', '游客', 2, '2023-01-17 15:08:16', '172.16.12.251', NULL);
INSERT INTO `article_comment` VALUES (16, 18, '<p>对对对</p>', '游客', 2, '2023-01-17 15:14:53', '172.16.12.251', 14);
INSERT INTO `article_comment` VALUES (19, 16, '<p>我草牛皮！</p>', '游客', 2, '2023-01-17 15:29:06', '172.16.12.251', NULL);
INSERT INTO `article_comment` VALUES (20, 18, '回复标签测试', '游客', 2, '2023-01-17 16:17:59', '172.16.12.251', 14);
INSERT INTO `article_comment` VALUES (21, 18, '<p>正常模式测试</p>', '游客', 2, '2023-01-17 16:18:19', '172.16.12.251', NULL);
INSERT INTO `article_comment` VALUES (22, 18, '<p>c而是</p>', '游客', 2, '2023-01-17 16:29:56', '172.16.12.251', NULL);
INSERT INTO `article_comment` VALUES (23, 18, '哈哈哈', '游客', 2, '2023-01-17 16:30:04', '172.16.12.251', 22);
INSERT INTO `article_comment` VALUES (24, 18, '123123123123', '游客', 2, '2023-01-17 16:34:05', '172.16.12.251', 22);
INSERT INTO `article_comment` VALUES (25, 17, '<p>哥哥真棒</p>', '游客', 2, '2023-01-17 16:49:41', '121.5.79.74', NULL);
INSERT INTO `article_comment` VALUES (26, 18, '<p>前端我只认TH</p>', '游客', 2, '2023-01-17 16:49:57', '121.5.79.74', NULL);
INSERT INTO `article_comment` VALUES (27, 18, 'qio实qio实', '游客', 2, '2023-01-17 16:50:28', '121.5.79.74', 21);
INSERT INTO `article_comment` VALUES (28, 18, '要的哥哥', '游客', 2, '2023-01-17 17:01:04', '121.5.79.74', 26);
INSERT INTO `article_comment` VALUES (50, 18, '<p>大家好</p>', '游客', 2, '2023-01-18 15:31:54', '172.16.12.251', NULL);

-- ----------------------------
-- Table structure for article_home_recommend
-- ----------------------------
DROP TABLE IF EXISTS `article_home_recommend`;
CREATE TABLE `article_home_recommend`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '所属用户id',
  `creation_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article_home_recommend
-- ----------------------------
INSERT INTO `article_home_recommend` VALUES (36, 16, 1, '2023-01-15 10:29:46');
INSERT INTO `article_home_recommend` VALUES (37, 18, 1, '2023-01-16 13:51:30');

-- ----------------------------
-- Table structure for article_label
-- ----------------------------
DROP TABLE IF EXISTS `article_label`;
CREATE TABLE `article_label`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `label_id` int(11) NULL DEFAULT NULL COMMENT '标签id',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article_label
-- ----------------------------
INSERT INTO `article_label` VALUES (92, 14, 17);
INSERT INTO `article_label` VALUES (93, 21, 17);
INSERT INTO `article_label` VALUES (94, 19, 17);
INSERT INTO `article_label` VALUES (96, 21, 16);
INSERT INTO `article_label` VALUES (97, 20, 16);
INSERT INTO `article_label` VALUES (98, 13, 22);
INSERT INTO `article_label` VALUES (99, 13, 23);
INSERT INTO `article_label` VALUES (100, 20, 23);
INSERT INTO `article_label` VALUES (110, 19, 18);
INSERT INTO `article_label` VALUES (111, 13, 18);
INSERT INTO `article_label` VALUES (112, 21, 18);

-- ----------------------------
-- Table structure for article_label_info
-- ----------------------------
DROP TABLE IF EXISTS `article_label_info`;
CREATE TABLE `article_label_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `creation_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '所属用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article_label_info
-- ----------------------------
INSERT INTO `article_label_info` VALUES (13, 'Vue', '2023-01-10 13:13:46', 1);
INSERT INTO `article_label_info` VALUES (14, 'CSS', '2023-01-15 15:18:55', 1);
INSERT INTO `article_label_info` VALUES (15, 'HTML', '2023-01-15 15:18:59', 1);
INSERT INTO `article_label_info` VALUES (16, 'React', '2023-01-15 15:19:09', 1);
INSERT INTO `article_label_info` VALUES (17, 'Nuxt', '2023-01-15 15:19:14', 1);
INSERT INTO `article_label_info` VALUES (18, 'VantUI', '2023-01-15 15:19:31', 1);
INSERT INTO `article_label_info` VALUES (19, 'ElementUI', '2023-01-15 15:19:43', 1);
INSERT INTO `article_label_info` VALUES (20, 'Umi', '2023-01-15 15:19:49', 1);
INSERT INTO `article_label_info` VALUES (21, 'JavaScript', '2023-01-15 15:20:13', 1);

-- ----------------------------
-- Table structure for article_one_class
-- ----------------------------
DROP TABLE IF EXISTS `article_one_class`;
CREATE TABLE `article_one_class`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '所属用户id',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字体图标',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `creation_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article_one_class
-- ----------------------------
INSERT INTO `article_one_class` VALUES (21, 1, 'earth', '前端技术栈', '2023-01-10 13:14:11');
INSERT INTO `article_one_class` VALUES (22, 1, 'houduankaifa', '后端技术栈', '2023-01-15 10:25:26');

-- ----------------------------
-- Table structure for article_two_class
-- ----------------------------
DROP TABLE IF EXISTS `article_two_class`;
CREATE TABLE `article_two_class`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `one_class_id` int(11) NULL DEFAULT NULL COMMENT '一级id',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字体图标',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `creation_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article_two_class
-- ----------------------------
INSERT INTO `article_two_class` VALUES (20, 21, 'vue', 'Vue3', '2023-01-10 13:14:21');
INSERT INTO `article_two_class` VALUES (21, 21, 'HTML', 'HTML5', '2023-01-10 13:14:35');
INSERT INTO `article_two_class` VALUES (22, 21, 'css3', 'CSS3', '2023-01-15 10:24:50');
INSERT INTO `article_two_class` VALUES (23, 21, 'bxl-javascript', 'JavaScript', '2023-01-15 10:25:03');
INSERT INTO `article_two_class` VALUES (24, 22, 'java', 'Java', '2023-01-15 10:25:36');
INSERT INTO `article_two_class` VALUES (25, 22, 'bxl-spring-boot', 'SpringBoot2', '2023-01-15 10:26:11');
INSERT INTO `article_two_class` VALUES (26, 22, 'integral-fill1', 'MyBatis', '2023-01-15 10:26:31');

-- ----------------------------
-- Table structure for system_imgs
-- ----------------------------
DROP TABLE IF EXISTS `system_imgs`;
CREATE TABLE `system_imgs`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `creation_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_imgs
-- ----------------------------
INSERT INTO `system_imgs` VALUES (33, 'archive-1.jpg', 'http://image.thyo.xyz/img/8bbc9637-81ea-8da8-7a2e-359c0f38d368', 'jpg', '2022-10-21 08:03:27');
INSERT INTO `system_imgs` VALUES (34, 'home-bakck.png', 'http://image.thyo.xyz/img/df3c311d-5b82-78c1-454e-02e8e9b60fe0', 'png', '2022-10-21 08:03:27');
INSERT INTO `system_imgs` VALUES (35, 'img-1.jpg', 'http://image.thyo.xyz/img/380a267e-35a6-09bf-306a-88fd08da9b8a', 'jpg', '2022-10-21 08:03:27');
INSERT INTO `system_imgs` VALUES (36, 'img-2.jpg', 'http://image.thyo.xyz/img/f4239328-b716-7242-c78b-505cdcc25800', 'jpg', '2022-10-21 08:03:27');
INSERT INTO `system_imgs` VALUES (37, 'img-3.jpg', 'http://image.thyo.xyz/img/e58ea33a-1ec8-ac49-b634-3f0318bb1088', 'jpg', '2022-10-21 08:03:27');
INSERT INTO `system_imgs` VALUES (38, 'img-4.jpg', 'http://image.thyo.xyz/img/e3996e4c-fa43-11f2-ce67-063b050224c2', 'jpg', '2022-10-21 08:03:27');
INSERT INTO `system_imgs` VALUES (39, 'img-5.jpg', 'http://image.thyo.xyz/img/27482807-d03d-5546-c185-6a7078a20a7d', 'jpg', '2022-10-21 08:03:27');
INSERT INTO `system_imgs` VALUES (40, 'thyo-photo-1.jpg', 'http://image.thyo.xyz/img/679eac68-ad79-a547-99b4-15d870568286', 'jpg', '2022-10-21 08:03:27');
INSERT INTO `system_imgs` VALUES (41, 'img_1.jpg', 'http://image.thyo.xyz/img/c2ea7202-932e-e0e8-3393-53c8e69e6aa8', 'jpg', '2022-12-23 10:44:46');
INSERT INTO `system_imgs` VALUES (42, 'img-2.png', 'http://image.thyo.xyz/img/185a0f18-3c83-5e68-1264-ba464ff85a93', 'png', '2022-12-23 10:45:42');
INSERT INTO `system_imgs` VALUES (43, '猫咪1.png', 'http://image.thyo.xyz/img/c941b0f4-743b-ee95-890e-4a2803a8f434', 'png', '2023-01-12 14:47:55');

-- ----------------------------
-- Table structure for system_menus
-- ----------------------------
DROP TABLE IF EXISTS `system_menus`;
CREATE TABLE `system_menus`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `router` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单前端路径',
  `router_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由名称',
  `file_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端页面文件地址',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '上级菜单id',
  `creation_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_menus
-- ----------------------------
INSERT INTO `system_menus` VALUES (15, '首页', 'House', '/admin', 'admin-home', 'adminViews/home.vue', NULL, '2022-10-17 07:00:24', 1, 0);
INSERT INTO `system_menus` VALUES (16, '文章管理', 'Notebook', '/article', 'article-index', '', NULL, '2022-10-17 07:02:36', 1, 1);
INSERT INTO `system_menus` VALUES (17, '文章列表', '', 'list', 'article-list', 'adminViews/article/list.vue', 16, '2022-10-17 07:03:54', 1, 0);
INSERT INTO `system_menus` VALUES (18, '文章分类', '', 'class', 'article-class', 'adminViews/article/class.vue', 16, '2022-10-17 07:04:35', 1, 1);
INSERT INTO `system_menus` VALUES (19, '文章标签', '', 'label', 'article-label', 'adminViews/article/label.vue', 16, '2022-10-17 07:05:39', 1, 2);
INSERT INTO `system_menus` VALUES (20, '用户管理', 'User', '/user', 'users-index', '', NULL, '2022-10-17 07:07:15', 1, 2);
INSERT INTO `system_menus` VALUES (21, '用户列表', '', 'list', 'user-list', 'adminViews/user/list.vue', 20, '2022-10-17 07:08:25', 1, 0);
INSERT INTO `system_menus` VALUES (22, '角色设置', '', 'authority', 'user-authority', 'adminViews/user/authority.vue', 20, '2022-10-17 07:08:50', 1, 1);
INSERT INTO `system_menus` VALUES (23, '网站设置', 'Setting', '/system', 'system-index', '', NULL, '2022-10-17 07:11:01', 1, 3);
INSERT INTO `system_menus` VALUES (24, '菜单设置', '', 'menus', 'menus-list', 'adminViews/system/menus.vue', 23, '2022-10-17 07:11:47', 1, 0);
INSERT INTO `system_menus` VALUES (25, '网站资源', '', 'resource', 'resource', '', 23, '2022-10-19 09:33:04', 1, 1);
INSERT INTO `system_menus` VALUES (26, '网站信息', '', 'webInfo', 'webInfo', '', 23, '2022-10-24 14:00:55', 1, 3);

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `menus_id` int(11) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 458 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色绑定菜单列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES (443, 1, 15);
INSERT INTO `system_role` VALUES (444, 1, 16);
INSERT INTO `system_role` VALUES (445, 1, 17);
INSERT INTO `system_role` VALUES (446, 1, 18);
INSERT INTO `system_role` VALUES (447, 1, 19);
INSERT INTO `system_role` VALUES (448, 1, 20);
INSERT INTO `system_role` VALUES (449, 1, 21);
INSERT INTO `system_role` VALUES (450, 1, 22);
INSERT INTO `system_role` VALUES (451, 1, 23);
INSERT INTO `system_role` VALUES (452, 1, 24);
INSERT INTO `system_role` VALUES (453, 1, 25);
INSERT INTO `system_role` VALUES (454, 1, 26);
INSERT INTO `system_role` VALUES (455, 7, 15);
INSERT INTO `system_role` VALUES (456, 7, 16);
INSERT INTO `system_role` VALUES (457, 7, 17);

-- ----------------------------
-- Table structure for system_role_info
-- ----------------------------
DROP TABLE IF EXISTS `system_role_info`;
CREATE TABLE `system_role_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色昵称',
  `creation_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_role_info
-- ----------------------------
INSERT INTO `system_role_info` VALUES (1, '超级管理员', '2022-09-27 13:12:03', 1);
INSERT INTO `system_role_info` VALUES (7, '注册会员', '2023-01-10 13:17:55', 1);
INSERT INTO `system_role_info` VALUES (8, '游客', '2023-01-17 11:17:44', 1);

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NOT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (1, 'TH', 18);
INSERT INTO `test` VALUES (2, 'dj', 20);
INSERT INTO `test` VALUES (3, 'yoyo', 20);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名',
  `head` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '头像',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '帐号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '秘钥',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `creation_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `last_time` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'THyo', 29, '简介简介简介简介', 'http://image.thyo.xyz/img/c568965f-8a5d-40d4-3a37-917b6d6cf2c2', '522307026', 'qq1212', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpcCI6IjE3Mi4xNi4xMi4yNTEiLCJpc3MiOiJTT0ZUSVNMQU5ELkFDQ09VTlQiLCJleHAiOjE2NzQ2Mjg2MjUsImlhdCI6MTY3NDAyMzgyNSwidXNlcklkIjoiMSJ9.F9vTdNGPjuVc7z3MjRfn2J-SWNzUaN2AYQPiC8BE_x0', '1', '2022-09-01 15:18:04', '2023-01-18 14:37:06', 1);
INSERT INTO `user` VALUES (2, '游客', 20, '访问游客', 'http://image.thyo.xyz/img/49644a67-6633-a90e-76ee-0cac4571cb9e', 'guest', 'guest', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpcCI6IjE3Mi4xNi4xMi4yNTEiLCJpc3MiOiJTT0ZUSVNMQU5ELkFDQ09VTlQiLCJleHAiOjE2NzQ1MzA3NDIsImlhdCI6MTY3MzkyNTk0MiwidXNlcklkIjoiMiJ9.DBXRdUxq2VlQaaCcUx9LIIOVbCbOYxm53p-ut3Uik_E', '', '2023-01-10 13:18:41', '2023-01-17 11:25:42', 8);

-- ----------------------------
-- Table structure for web_config
-- ----------------------------
DROP TABLE IF EXISTS `web_config`;
CREATE TABLE `web_config`  (
  `id` int(11) NULL DEFAULT NULL COMMENT 'id',
  `titlte` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网站标题',
  `signature` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `user_img` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '个人头像',
  `brief` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '关于简介',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人昵称',
  `wechat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信',
  `github` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'github',
  `weibo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微博',
  `qq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `index_back` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '首页背景图',
  `archive_back` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归档背景图',
  `aboutme_back` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关于我背景图',
  `messageboard_back` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言板背景图'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of web_config
-- ----------------------------
INSERT INTO `web_config` VALUES (1, 'THYO\'S BLOG', '我知你饱览过名山大川,而我只是江南一座低矮的青山,但轻舟过万重,青山依旧在\n', 'http://image.thyo.xyz/img/c2ea7202-932e-e0e8-3393-53c8e69e6aa8', '<p>关于内容编辑关于内容编辑<br></p>', 'THyo', 'THyoyoyo', 'https://github.com/THyoyoyo', 'https://www.sina.com.cn/', '522307026', 'http://image.thyo.xyz/img/e3996e4c-fa43-11f2-ce67-063b050224c2', 'http://image.thyo.xyz/img/f4239328-b716-7242-c78b-505cdcc25800', 'http://image.thyo.xyz/img/27482807-d03d-5546-c185-6a7078a20a7d', 'http://image.thyo.xyz/img/e58ea33a-1ec8-ac49-b634-3f0318bb1088');

SET FOREIGN_KEY_CHECKS = 1;
