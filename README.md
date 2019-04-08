# 淘淘商城

### 功能架构：
 - 前台：
    - 门户
    - 商品搜索
    - 商品展示
    - 购物车
    - 注册&登陆
    - 订单提交
    - 支付
 - 后台：
    - 商品管理
    - 商品推荐
    - 订单管理
    - CMS
    - CRM

### 技术选型：
 - 数据库：MySQL
 - Dao 层：mybatis、druid
 - 缓存：redis
 - 搜索：solr
 - Service 层：spring
 - 表现层：springmvc、jstl、EasyUI、jsp、freemaker
 - 图片服务器：FastDFS
 - 反向代理服务器：Nginx
 - Web 服务器：tomcat
 - 工程管理：maven

### 分布式架构的优点：
 - 降低模块之间的耦合度
 - 方便不同团队同时开发
 - 系统扩展性高
 - 灵活的进行分布式部署

### 分布式架构缺点：
 - 各个模块之间需要远程通信，接口开发增加工作量