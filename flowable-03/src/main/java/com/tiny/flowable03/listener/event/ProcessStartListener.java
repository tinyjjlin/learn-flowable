package com.tiny.flowable03.listener.event;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.common.api.delegate.event.FlowableEvent;
import org.flowable.engine.common.api.delegate.event.FlowableEventListener;
import org.flowable.engine.delegate.event.FlowableProcessEngineEvent;
import org.springframework.stereotype.Component;

/**
 * @author tiny lin
 * @date 2019/8/6
 */
@Slf4j
@Component("processStartListener")
public class ProcessStartListener implements FlowableEventListener {
    @Override
    public void onEvent(FlowableEvent event) {
        FlowableProcessEngineEvent engineEvent = (FlowableProcessEngineEvent)event;
       String procId = engineEvent.getProcessInstanceId();
       log.info("# process start listener.....procId:{}",procId);
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }

    @Override
    public boolean isFireOnTransactionLifecycleEvent() {
        return false;
    }

    @Override
    public String getOnTransaction() {
        return null;
    }
}
