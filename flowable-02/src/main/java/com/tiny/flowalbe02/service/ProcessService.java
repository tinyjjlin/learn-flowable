package com.tiny.flowalbe02.service;

import com.alibaba.fastjson.JSONObject;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tiny lin
 * @date 2019/8/5
 */
@Service
@Transactional
public class ProcessService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    public String startProcess(String processDefinitonKey){
        Map<String,Object> var = new HashMap<>();
        var.put("username","jjlin" );
       ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitonKey,var);
       return processInstance.getId();
    }

    public String queryVariable(String procId,String varName){
        Object loanRequest = runtimeService.getVariable(procId, varName);
        return JSONObject.toJSON(loanRequest).toString();
    }

}
