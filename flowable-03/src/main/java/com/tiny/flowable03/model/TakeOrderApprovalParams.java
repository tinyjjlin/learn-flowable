package com.tiny.flowable03.model;

import lombok.Data;

/**
 * @author tiny lin
 * @date 2019/8/6
 */
@Data
public class TakeOrderApprovalParams {
    private String taskId;
    private String assignee;
    private Integer approvalCondition;
}
