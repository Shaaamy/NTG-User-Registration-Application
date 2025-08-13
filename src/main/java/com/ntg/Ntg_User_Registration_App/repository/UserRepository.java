package com.ntg.Ntg_User_Registration_App.repository;

import com.ntg.Ntg_User_Registration_App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
