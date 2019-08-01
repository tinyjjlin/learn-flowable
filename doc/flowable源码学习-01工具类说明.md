# flowable 源码学习-01 公共类，模块说明

 ## flowable-engine-common-api
 定义了flowable流程引擎所有接口
 1. 事件处理， 方法代理 delegate event
 2. identity 身份认证
 3. 数据库表查询抽象 TableMetaData，TablePage,TablePageQUery
 4. 查询抽象 query,queryProperty,NativeQuery
 5. variable 变量 引擎变量容器
 6. 定义所有异常
 7. 
## flowable-common-rest 
主要对rest 请求，响应部分作处理。
api 中：

1. 分页请求，分页响应数据结构 DataResponse
2. RequestUtil 请求数据 类型转换
3. RestError 响应错误信息。

exception :

1. 错误请求处理 @ControllerAdvice

filter:

RestAUthenticator api是否需要AUthentication 认证

resolver:

处理资源，文件类型

variable:
 
 请求变量类型转换

 util:

 RestUrlBuilder 构建路径

 ##flowable-engine-common

 1. agenda 日程
 
 维护操作多线程 
 ```
   protected CommandContext commandContext;
   protected LinkedList<Runnable> operations = new LinkedList<>();
   
 ```
 2. callback
 CallbackData, RuntimeInstanceStateChangeCallback 状态改变回调方法

 3. conext 
 Context 

 ```
 protected static ThreadLocal<Stack<CommandContext>> commandContextThreadLocal = new ThreadLocal<>();
 ```

4 entity
 Entity   persistentState:String 保存数据库对象，可以通过比较判断数据是否更新，不用再次查询数据库。

5 Uuid
StrongUuidGenerator ，volatile

6. util

CollectionUtil  singletonMap,isEmpty,isNotEmpty, map 
```
    /**
     * Helper method to easily create a map.
     * 
     * Takes as input a varargs containing the key1, value1, key2, value2, etc. Note: although an Object, we will cast the key to String internally.
     */
    public static Map<String, Object> map(Object... objects) {

        if (objects.length % 2 != 0) {
            throw new FlowableIllegalArgumentException("The input should always be even since we expect a list of key-value pairs!");
        }

        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < objects.length; i += 2) {
            map.put((String) objects[i], objects[i + 1]);
        }

        return map;
    }
```

ReflectUtil 反射工具类

cache 缓存
实现实体对象数据的缓存，保存旧值，可以实现新旧对比。



## Flowable engine

taskService
 1. 添加附加，流的方式
 2. 添加评论 ,给task processInstance
 3. 子任务   List<Task> getSubTasks(String parentTaskId);
 4. 任务事件 event
 5. 任务owner,可以转让
 6. 任务assignee 处理人也可转让
 7. 