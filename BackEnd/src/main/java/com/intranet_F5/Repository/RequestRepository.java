package com.intranet_F5.Repository;

import com.intranet_F5.Model.UserRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<UserRequestModel, Long> {
    @Query(value="SELECT * FROM user_request WHERE user_id=:#{#myRequest.getUserId().getId()} start_date BETWEEN :#{#myRequest.getStartDate()} " +
            "AND :#{#myRequest.getEndDate()} LIMIT 1", nativeQuery = true)
    UserRequestModel findRepeatedDateRequests(@Param("myRequest") UserRequestModel myRequest);

    @Query(value="SELECT * FROM user_request WHERE user_id IN " +
            "(SELECT id FROM users WHERE school_id IN " +
            "(SELECT id FROM school WHERE id =:schoolId))", nativeQuery = true)
    List<UserRequestModel> findBySchoolId(@Param("schoolId") long schoolId);
}
