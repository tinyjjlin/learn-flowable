package com.tiny.flowalbe02.controller;

import com.tiny.flowalbe02.service.ProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author tiny lin
 * @date 2019/8/5
 */
@Slf4j
@RestController
@Api
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @ApiOperation("startProcess")
    @PostMapping("/rest/startProcess/{processDefinitionKey}")
    public String startProcess(@PathVariable(value = "processDefinitionKey") String processDefinitionKey){
        log.info("## processDefinitionKey:"+processDefinitionKey);

       String procId = processService.startProcess(processDefinitionKey);

       return procId;
    }

    @GetMapping("/rest/fetchVar")
    public String fetchVar(@RequestParam("procId") String procId,@RequestParam("varName")String varName){
        return processService.queryVariable(procId,varName);
    }
}
