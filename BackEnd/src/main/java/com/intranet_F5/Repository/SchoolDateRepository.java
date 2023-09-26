package com.intranet_F5.Repository;

import com.intranet_F5.Model.SchoolDateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolDateRepository extends JpaRepository <SchoolDateModel, Long>{
}
