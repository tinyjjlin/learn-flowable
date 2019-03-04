package com.tiny.demo01.service;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tiny lin
 * @date 2019/3/4
 */
public class AssignmentHandler implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        List<String>users = new ArrayList <>();
        users.add("jjlin");
        users.add("tiny");
            delegateTask.addCandidateUsers(users);
    }
}
