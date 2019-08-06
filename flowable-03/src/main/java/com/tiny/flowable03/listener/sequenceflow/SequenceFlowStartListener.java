package com.tiny.flowable03.listener.sequenceflow;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author tiny lin
 * @date 2019/8/6
 */
@Slf4j
@Component("sequenceFlowStartListener")

public class SequenceFlowStartListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) {
        log.info("# sequence flow start listener......");
    }
}
