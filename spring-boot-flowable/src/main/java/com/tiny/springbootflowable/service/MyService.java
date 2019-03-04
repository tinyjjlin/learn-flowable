package com.tiny.springbootflowable.service;

import com.tiny.springbootflowable.entity.Person;
import com.tiny.springbootflowable.repository.PersonRepository;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tiny lin
 * @date 2019/2/11
 */
@Service
@Transactional
public class MyService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private PersonRepository personRepository;

    public void startProcess(String assignee) {
        Person person = personRepository.findByUsername(assignee);
        System.out.println("person..........."+person.getId());
        Map<String, Object> variables = new HashMap<String, Object>(8);
        variables.put("person", person);
        runtimeService.startProcessInstanceByKey("oneTaskProcess",variables);
    }

    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    public void createDemoUsers() {
        if (personRepository.findAll().size() == 0) {
            personRepository.save(new Person("jjlin", "jj", "lin", new Date()));
            personRepository.save(new Person("tinyjjlin", "tiny", "lin", new Date()));
        }
    }

}
