package com.test.eventtracker.rest.model;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sbenner
 * Date: 10/17/17
 * Time: 1:45 AM
 */
@Data
public class Message {
    Type type;

    String messageId;

    Date timestamp;

    Map<String, Object> context;

    String anonymousId;
    String userId;

    Map<String, Object> integrations;

    public enum Type {
        identify, group, track, screen, page, alias
    }
}
