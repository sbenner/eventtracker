package com.test.eventtracker.service;


import com.test.eventtracker.persistence.model.MessageEntity;
import com.test.eventtracker.persistence.repository.EventRepository;
import com.test.eventtracker.rest.model.BatchDTO;
import com.test.eventtracker.rest.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sbenner
 * Date: 3/2/17
 * Time: 12:46 PM
 */
@Service
public class EventService {
    final static Logger logger = LoggerFactory.getLogger(EventService.class);


    @Autowired
    private EventRepository eventRepository;


    @Autowired
    ApplicationContext applicationContext;


    public EventService() {
    }


    public MessageEntity save(MessageEntity messageEntity) {

        return eventRepository.save(messageEntity);

    }

    public void createEvents(BatchDTO batchDTO) {
        List<MessageEntity> messageList = mapMessages(batchDTO);
        eventRepository.save(messageList);
    }


    private List<MessageEntity> mapMessages(BatchDTO batchDTO) {

        List<MessageEntity> list = new ArrayList<>();

        for (Message message : batchDTO.getBatch()) {
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setUserId(message.getUserId());
            messageEntity.setAnonymousId(message.getAnonymousId());

            StringBuilder sb = new StringBuilder();
            if(message.getContext()!=null) {
                for (Map.Entry e : message.getContext().entrySet()) {
                    sb.append(e.getKey()).append(e.getValue());

                }
                messageEntity.setContext(sb.toString());
            }
            if(message.getIntegrations()!=null) {
                sb = new StringBuilder();
                for (Map.Entry e : message.getIntegrations().entrySet()) {
                    sb.append(e.getKey()).append(e.getValue());
                }
                messageEntity.setIntegrations(sb.toString());
            }
            messageEntity.setType(message.getType().name());
            list.add(messageEntity);
        }

        return list;

    }

    public MessageEntity getOrderById(Long orderId) {
        return eventRepository.findById(orderId);
    }


}
