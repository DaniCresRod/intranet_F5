package com.intranet_F5.Repository;

import com.intranet_F5.Model.SchoolModel;
import com.intranet_F5.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SchoolRepository extends JpaRepository<SchoolModel, Long> {
    @Query(value="SELECT * FROM school WHERE address=:#{#mySchool.getSchoolAddress()} AND name=:#{#mySchool.getSchoolName()}",  nativeQuery = true)
    SchoolModel findDuplicatedSchool(@Param("mySchool")SchoolModel mySchool);

}
