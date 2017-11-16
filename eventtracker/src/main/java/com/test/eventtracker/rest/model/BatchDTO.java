package com.test.eventtracker.rest.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sbenner
 * Date: 10/17/17
 * Time: 1:43 AM
 */
@Data
public class BatchDTO {


    public List<Message> batch;

    public Date sentAt;

    public Map<String, Object> context;

    public int sequence;

}
