package com.tiny.flowable03.listener.task;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/**
 * @author tiny lin
 * @date 2019/8/6
 */
@Slf4j
@Component("taskCreateListener")
public class TaskCreateListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("# task create listener.......");
    }
}
