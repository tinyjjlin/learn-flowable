package com.tiny.flowable03.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tiny lin
 * @date 2019/8/6
 */
@Slf4j
@Service
public class TakeOrderService {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    public String startProcess(){
        //TODO
        // order form info
        Map<String,Object> var = new HashMap <>(1);
        var.put("creator","tiny" );
       ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("takeOrderProcess",var );
       return processInstance.getProcessInstanceId();
    }

    /**
     * 获取负责人用户任务列表
     * @param procId
     * @param assignee
     * @return
     */
    public List<Task> getAssigneeTaskList(String procId,String assignee) {
        List <Task> tasks = taskService.createTaskQuery().processInstanceId(procId).taskAssignee(assignee).list();
        if(tasks != null){
            log.info("# assignee task list......."+ tasks.size());
        }
        return tasks;
    }

    /**
     * 获取候选组用户任务列表
     * @param procId
     * @param candidateGroup
     * @return
     */
    public List<Task> getCandidateGroupTaskList(String procId,String candidateGroup) {
        List <Task> tasks = taskService.createTaskQuery().processInstanceId(procId).taskCandidateGroup(candidateGroup).list();
        if(tasks != null){
            log.info("# candidateGroup task list......."+ tasks.size());
        }
        return tasks;
    }

    /**
     * 完成用户任务
     * @param taskId
     */
    public void completeTask(String taskId,Map<String,? extends  Object> var){
        taskService.setVariables(taskId,var );
        taskService.complete(taskId);
    }

    public void approvalTask(String taskId,String assignee,Integer approvalCondition){
        //TODO
        // 认领任务是否改变任务id?
        taskService.claim(taskId,assignee );
        if(approvalCondition == 0){
            taskService.setVariable(taskId,"orderStatus","已打回" );
        }else if(approvalCondition == 1){
            taskService.setVariable(taskId,"orderStatus","已签单" );
        }else{
            taskService.setVariable(taskId,"orderStatus","已拒签" );
        }

        taskService.complete(taskId);
    }


    public void jumpTask(String procId,String assignee){
        List <Task> tasks = taskService.createTaskQuery().processInstanceId(procId).active().list();
        log.info(" # jump task...."+ tasks);
        if(tasks != null && tasks.size()>0){
            for (Task task : tasks) {
                if("receiverApprovalOrderTask".equals(task.getName())){
                    runtimeService.createChangeActivityStateBuilder()
                            .processInstanceId(procId).moveActivityIdTo("receiverApprovalOrderTask", "takeOrderEndEvent").localVariable("receiverApprovalOrderTask","orderStatus","已撤销")
//                .moveActivityIdsToSingleActivityId(currTaskKeys, "takeOrderEndEvent")

                            .changeState();
                }else if("reTakeOrderTask".equals(task.getName())){
                    runtimeService.createChangeActivityStateBuilder()
                            .processInstanceId(procId).moveActivityIdTo("reTakeOrderTask", "takeOrderEndEvent").localVariable("receiverApprovalOrderTask","orderStatus","已撤销")
//                .moveActivityIdsToSingleActivityId(currTaskKeys, "takeOrderEndEvent")

                            .changeState();
                }
            }
        }
        log.info("jump task...................");
        /**
         * 实现活动的自动跳转
         */


    }

    /**
     * 取消流程
     * @param procId
     * @param reason
     */
    public void cancelProcess(String procId,String reason){
        List <Task> tasks = taskService.createTaskQuery().processInstanceId(procId).active().list();
        if(tasks != null && tasks.size()>0){
            runtimeService.setVariable(tasks.get(0).getExecutionId(), "orderStatus","已取消" );
        }
        runtimeService.deleteProcessInstance(procId, reason);

    }


}
