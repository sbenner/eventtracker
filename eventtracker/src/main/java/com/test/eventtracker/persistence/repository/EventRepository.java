package com.test.eventtracker.persistence.repository;


import com.test.eventtracker.persistence.model.MessageEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created with IntelliJ IDEA.
 * User: sbenner
 * Date: 5/4/16
 * Time: 6:26 AM
 */
@Repository
@Qualifier("eventRepository")
public interface EventRepository extends JpaRepository<MessageEntity, Long> {
    MessageEntity findById(Long id);
}
