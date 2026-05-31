# 社区内容管理系统

## 技术栈
- Spring Boot 3.1.5
- MyBatis-Plus 3.5.3.1
- Spring Security + JWT
- MySQL 8.0
- BCrypt 加密

## 功能模块
- 用户注册（BCrypt 加密）
- 用户登录（JWT Token）
- 文章发布、查询、更新、删除
- 统一返回格式、全局异常处理

## 接口列表
| 接口 | 方法 | 描述 |
|------|------|------|
| /user/register | POST | 用户注册 |
| /user/login | POST | 用户登录 |
| /article/publish | POST | 发布文章 |
| /article/my-list | GET | 我的文章列表 |
| /article/{id} | GET | 文章详情 |
| /article/{id} | PUT | 更新文章 |
| /article/{id} | DELETE | 删除文章 |

## 运行方式
1. 创建 MySQL 数据库 `community`
2. 修改 `application.yml` 中的数据库密码
3. 运行 `CommunityApplication.java`
4. 使用 `test.http` 测试接口
