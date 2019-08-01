package com.tiny.demo01.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


/**
 * @author tiny lin
 * @date 2019/3/4
 */
@Service
public class AssignService {
    public List <String> findManagers() {
        return Arrays.asList("jjlin", "lin", "tiny");
    }
}
