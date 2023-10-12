package com.intranet_F5.Repository;

import com.intranet_F5.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUserNif(String Nif);
    @Query("SELECT u FROM UserModel u WHERE u.userName = :userName")
    Optional<UserModel> findByUsername(@Param("userName") String userName);

    @Query(value = "SELECT name FROM users WHERE email = :myEmail", nativeQuery = true)
    String findUserNameByEmail(@Param("myEmail") String searchedEmail);


}
