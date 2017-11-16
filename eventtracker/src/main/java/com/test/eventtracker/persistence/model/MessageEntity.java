package com.test.eventtracker.persistence.model;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sbenner
 * Date: 7/19/17
 * Time: 1:21 PM
 */
@Entity
@Cache(usage= CacheConcurrencyStrategy.READ_ONLY, region="events")
@Table(name = "event")
@Data
public class MessageEntity extends BaseEntity {

    String type;

    String messageId;

    Date timestamp;

    String context;

    String anonymousId;
    String userId;

    String integrations;


}
