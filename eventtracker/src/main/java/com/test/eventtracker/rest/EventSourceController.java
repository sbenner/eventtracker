package com.test.eventtracker.rest;

import com.test.eventtracker.service.KafkaService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 *
 */
@RestController
@CrossOrigin(allowedHeaders = "*", maxAge = 3600, origins = "*", allowCredentials = "true")
@RequestMapping("/import")
public class EventSourceController {
    private static final Logger logger = LoggerFactory.getLogger(EventSourceController.class);

    @Autowired
    private KafkaService service;

//    @Autowired
//    CacheManager cacheManager;


//    @RequestMapping(
//            method = RequestMethod.POST,
//            consumes = {APPLICATION_JSON_VALUE},
//            produces = {APPLICATION_JSON_VALUE}
//    )
//    @ResponseBody
//    public ResponseEntity getEventBatch(
//            @RequestBody BatchDTO batchDTO
//    ) {
//        logger.info("batch size " + batchDTO.getBatch().size());
//        service.createEvents(batchDTO);
//
//        return new ResponseEntity(HttpStatus.ACCEPTED);
//    }

    //
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/{schema}/{id}",
            produces = {APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public ResponseEntity uploadEvent(
            @RequestBody String dto
    ) {
        System.out.println(new JSONObject(dto));
        try {
            service.sendMsgToKafka(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }


}
