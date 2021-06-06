package com.paw.hrmApp.repository;

import com.paw.hrmApp.model.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    @Query(value = "SELECT city_name as cityName, count(*) as departmentCount FROM department d " +
            "JOIN location l ON d.location_id = l.location_id GROUP BY city_name;", nativeQuery = true)
    List<Map<String, Object>> findLocationStats();
}
