# learn-flowable

## REFERENCE
1. [flowable官网](https://www.flowable.org/)
2. [flowable docs](https://www.flowable.org/docs/userguide/index.html#uiAppInstallation)
3. [flowable docs 中文文档](https://tkjohn.github.io/flowable-userguide/#_getting_started)
4. [flowable源码](git@github.com:flowable/flowable-engine.git)
5. [mybatis官方说明文档](http://www.mybatis.org/mybatis-3/zh/index.html)  

  ## what's workflow?
  
  **definition**
  
    工作流是指一类能够完全自动执行的经营过程，根据一系列过程规则，将文档、信息或任务在不同的执行者之间进行传递与执行。说的直白一点工作流就是封装好的一种框架，我们利用这种框架来解决需要多个人或者多个部门协同完成的某项工作。
  
  ## BMP Business Process Management 业务流程管理
  
    JBPM的另一个特色是它使用Hibernate来管理它的数据库。Hibernate是目前Java领域非常流行的一种数据存储层解决方案,只要是 Hibernate 支持的数据库， JBPM 也就支持。通过Hibernate，JBPM将数据的管理职能分离出去，自己专注于业务逻辑的处理。
  
  
  ## 1 what's BPMN, CMMN ,DMN,APPS?
  1. BPMN
  2. CMMN
  3. DMN
  4. APPS
 
  
  ## 2 主要类说明
  service  FormRepositoryService,
  
  FormService,
  
  DmnRepositoryService,
  
  DmnRuleService,
  
  IdmIdentityService,
  
  ContentService
  
  ### 2.1 flowable DmnEngine 动态引擎
  
  ### 2.2 flowalbe DmnRuleService 规则文件部署
  
  ### 2.3 flowable 非中断子流程
  
  ### 2.4 flowable 整合modeler
  
  ### 2.5 flowable BpmnModel
  
  ### 2.6 flowable 设置流程发起人
  
  ## 3 BPMN 流程
  
  ---
  ### 3.1 
  assignee /esai'ni/ 代理人，处理的人，表示任务指派到具体的人。
  Element variable
  
  会签: 分配到多个人
  
  collection
  
  会签应该是某一群人共同去执行同一个任务多个副本，每个人应该都能执行一份任务，至于最后完成的条件可以根据业务设定，不是非得所有全部完成。如果对于所有任务实例都设置候选用户或候选组，就意味着每个任务实例同时生成后需要候选用户去认领，这里就有两个可能： 1. 有的用户不去认领，这个任务别人也可以做；2. 一个用户可以认领多个任务并完成。
  
  user
  
  candidate user
  
  candidate group
  
  
  
  ### 3.2 flowable userTask 添加任务监听器
  ```
      <extensionElements>
          <!-- <flowable:taskListener event="create" class="cn.flowable.listener.MyListener"></flowable:taskListener> -->
          <flowable:taskListener event="create" delegateExpression="${myListener}"></flowable:taskListener>
          <modeler:initiator-can-complete xmlns:modeler="http://flowable.org/modeler"><![CDATA[false]]></modeler:initiator-can-complete>
        </extensionElements>
  
  @Component(value="myListener"
  public class MyListener implements TaskListener {
  @Autowired
  private BusLineService busLineService;
  @Override
  public void notify(DelegateTask delegateTask) {
  //写自己具体的逻辑
  }
  
  }
  ```
  
  
  ### 3.3 BPMN2.0 结构
  
  事件 Event
  
  开始事件
  ```
  # 空的开始时间
  <startEvent id="start" name="开始"></startEvent>
  ```
  
  条件顺序流
  ```
  <sequenceFlow id="flow1" sourceRef="theStart" targetRef="theTask" />
  ```
  
  
  ## 启动流程实例
  
  ```
  ## 获取流程实例
  ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("financialReport");
  ```
  
  flowable引擎存储任务，对应的用户或组。 用户任务活动是一个等待状态（wait stae）