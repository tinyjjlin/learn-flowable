package com.tiny.demo01.service;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tiny lin
 * @date 2019/3/4
 */
@Service
public class ProcessService {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    public void getStartProcessUser(String userId){
        List<ProcessDefinition> list =repositoryService.createProcessDefinitionQuery().startableByUser(userId).list();
        System.out.println("process definition...."+list.size());
    }

    public void startProcess(String assingee){
        Map<String, Object> variables = new HashMap<String, Object>(8);
        variables.put("creator", assingee);
        runtimeService.startProcessInstanceByKey("financialReport",variables);
    }

    public List<Task> getCandidateUserTask(String assignee){
        List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(assignee).list();
        System.out.println("task.......count..."+tasks.size());
        return tasks;
    }

    public List<Task> getAssigneeTask(String assignee){
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
        System.out.println("task.......count..."+tasks.size());
        return tasks;
    }
    public List<Task> getGroupTask(String group){
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(group).list();
        System.out.println("task.......count..."+tasks.size());
        return tasks;
    }

    public void claimTask(String assignee,String taskId) {
        // 申领任务
        taskService.claim(assignee,taskId);
    }

    public void completeTask(String taskId){
        taskService.complete(taskId);
    }
}
