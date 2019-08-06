package com.tiny.flowable03.controller;

import com.sun.scenario.effect.impl.prism.PrCropPeer;
import com.tiny.flowable03.model.ProcessExecutionParams;
import com.tiny.flowable03.model.TakeOrderApprovalParams;
import com.tiny.flowable03.model.TaskRepresentation;
import com.tiny.flowable03.service.TakeOrderService;
import io.swagger.annotations.Api;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author tiny lin
 * @date 2019/8/6
 */
@RestController
@Api
public class TakeOrderController {

    @Autowired
    private TakeOrderService takeOrderService;

    @PostMapping("/rest/takeOrder/startProcess")
    public String startProcess(){

      String procId =  takeOrderService.startProcess();
      return procId;
    }



    @GetMapping("/rest/takeOrder/taskList")
    public List<Task> getAssigneeTask(@RequestParam("procId")String procId,@RequestParam("userId")String userId) {
        List <Task> tasks = takeOrderService.getAssigneeTaskList(procId,userId );
        List list =tasks.stream().map(item->new TaskRepresentation(item.getId(),item.getName())).collect(Collectors.toList());
        return list;

    }

    @GetMapping("/rest/takeOrder/candidateGroupTaskList")
    public List<Task> getCandidateGroupTaskList(@RequestParam("procId")String procId,@RequestParam("groupId")String groupId) {
            List <Task> tasks = takeOrderService.getCandidateGroupTaskList(procId,groupId );
        List list =tasks.stream().map(item->new TaskRepresentation(item.getId(),item.getName())).collect(Collectors.toList());
        return list;

    }

    @PostMapping("/rest/takeOrder/clientCompleteTask")
    public void clientCompleteTakeOrderTask(@RequestBody String taskId){
        Map<String,String> var = new HashMap <>(1);
        var.put("orderStatus","新订单" );
        takeOrderService.completeTask(taskId,var );
    }

    @PostMapping("/rest/takeOrder/receiverApprovalTask")
    public void receiverApprovalTakeOrder(@RequestBody TakeOrderApprovalParams approvalParams){
        takeOrderService.approvalTask(approvalParams.getTaskId(), approvalParams.getAssignee(), approvalParams.getApprovalCondition());
    }
    @PostMapping("/rest/takeOrder/cancelTask")
    public void  cancelTakeOrder(@RequestBody String procId){
        takeOrderService.jumpTask(procId, "tiny");
    }

    @PostMapping("/rest/takeOrder/cancelProcess")
    public void cancelProcess(@RequestBody ProcessExecutionParams processExecutionParams){

        takeOrderService.cancelProcess(processExecutionParams.getProcId(), processExecutionParams.getReason());
    }

}
