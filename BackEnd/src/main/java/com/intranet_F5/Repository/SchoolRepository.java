package com.intranet_F5.Repository;

import com.intranet_F5.Model.SchoolModel;
import com.intranet_F5.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<SchoolModel, Long> {
}
