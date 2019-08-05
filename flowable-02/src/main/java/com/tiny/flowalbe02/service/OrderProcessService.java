package com.tiny.flowalbe02.service;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstanceQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tiny lin
 * @date 2019/8/5
 */

@Service
@Transactional
public class OrderProcessService {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;



    public String startProcess(){
        Map<String,Object> var = new HashMap <>();
        var.put("creator","tiny" );
      ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("orderProcess",var);
      String procId = processInstance.getRootProcessInstanceId();
      return procId;
    }

    public List<Task> getAssigneeTask(String assignee) {
        List <Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
        System.out.println("task.......count..." + tasks.size());
        return tasks;
    }

    public void completeTask(String taskId,String taskName){
        Map<String,Object> var = new HashMap <>();
        if(taskName.equals("enterOrderInfo")){
            taskService.setVariable(taskId, "orderStatus","new" );
        }else{
            taskService.setVariable(taskId,"orderStatus" ,"checked" );
            taskService.setVariableLocal(taskId,"checkStatus" , true);
        }


        taskService.complete(taskId);
    }

    public void queryVar(){
        //historicTaskInstanceQuery.
    }
}
