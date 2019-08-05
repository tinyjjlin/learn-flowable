package com.tiny.flowalbe02.controller;

import com.tiny.flowalbe02.model.TaskRepresentation;
import com.tiny.flowalbe02.service.OrderProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tiny lin
 * @date 2019/8/5
 */
@Slf4j
@RestController
@Api
public class OrderProcessController {
    @Autowired
    private OrderProcessService orderProcessService;

    @ApiOperation("startProcess")
    @PostMapping("/rest/order/startProcess/{processDefinitionKey}")
    public String startProcess(@PathVariable(value = "processDefinitionKey") String processDefinitionKey){
        log.info("## processDefinitionKey:"+processDefinitionKey);

        String procId = orderProcessService.startProcess();

        return procId;
    }

    @RequestMapping(value = "/assigneeTask", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List <TaskRepresentation> getTaskAssignee(@RequestParam String assignee) {

        System.out.println("assignee........" + assignee);
        List<Task> tasks = orderProcessService.getAssigneeTask(assignee);
        List <TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    @PostMapping("/rest/executeTask")
    public String executeTask(@RequestBody TaskRepresentation taskRepresentation){
        orderProcessService.completeTask(taskRepresentation.getId(),taskRepresentation.getName());
        return "complete task";
    }

}
