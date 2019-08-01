# flowable 源码分析

## 1 flowable 如何实现代码创建（create）删除（drop）升级（upgrade）数据库？
### 设计
采用模版设计模式，可以根据变量值的不同，加载不同的配置文件。

![01](img/db_create_01.png)

### 1.2 分析
1.VariableDbSchemaManager 实现类，指定表，sql数据所在文件路径。
2. ServiceSqlScriptBasedDbSchemaManager