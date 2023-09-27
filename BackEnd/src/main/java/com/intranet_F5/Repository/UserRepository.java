package com.intranet_F5.Repository;

import com.intranet_F5.Model.UserModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUserNif(String Nif);
}
