package dao;

import model.Event;
import model.Subscriber;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by srapion.hunanyan on 12/1/2016.
 */
@Repository
public interface EventDao extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

    @Query(countQuery = "select e from events e where start_date =:startDate")
    List<Event> findEventByStartDate(@Param("startDate") Date startDate);

    @Query(countQuery = "select e from events e where title like '%:title%'")
    List<Event> findEventsByTitle(@Param("title") String title);


}
