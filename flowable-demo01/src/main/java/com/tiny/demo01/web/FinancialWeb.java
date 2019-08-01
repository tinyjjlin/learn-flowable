package com.tiny.demo01.web;

import com.tiny.demo01.service.ProcessService;
import io.swagger.annotations.Api;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tiny lin
 * @date 2019/3/4
 */
@RestController
@Api
public class FinancialWeb {

    @Autowired
    private ProcessService processService;

    @PostMapping("/startProcess")
    public void startProcessInstance(String assignee) {
        System.out.println("assignee........" + assignee);
        processService.startProcess(assignee);
    }


    @RequestMapping(value = "/assigneeTask", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List <TaskRepresentation> getTaskAssignee(@RequestParam String assignee) {

        System.out.println("assignee........" + assignee);
        List <Task> tasks = processService.getAssigneeTask(assignee);
        List <TaskRepresentation> dtos = new ArrayList <TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    @RequestMapping(value = "/candidateUserTask", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List <TaskRepresentation> getTask(@RequestParam String assignee) {

        System.out.println("assignee........" + assignee);
        List <Task> tasks = processService.getCandidateUserTask(assignee);
        List <TaskRepresentation> dtos = new ArrayList <TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    @RequestMapping(value = "/groupTask", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List <TaskRepresentation> getTasksGroup(@RequestParam String group) {

        System.out.println("group........" + group);
        List <Task> tasks = processService.getGroupTask(group);
        List <TaskRepresentation> dtos = new ArrayList <TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    @GetMapping("/claimTask")
    public void claimTask(@RequestParam("assignee") String assignee, @RequestParam("taskId") String taskId) {
        processService.claimTask(taskId, assignee);
    }

    @GetMapping("/completeTask")
    public void completeTask(@RequestParam String taskId) {
        processService.completeTask(taskId);
    }

    static class StartProcessRepresentation {

        private String assignee;

        public String getAssignee() {
            return assignee;
        }

        public void setAssignee(String assignee) {
            this.assignee = assignee;
        }
    }


    static class TaskRepresentation {

        private String id;
        private String name;

        public TaskRepresentation(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
