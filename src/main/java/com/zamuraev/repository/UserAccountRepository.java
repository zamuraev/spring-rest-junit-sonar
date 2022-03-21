package com.zamuraev.repository;

import com.zamuraev.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    @Query("SELECT u FROM UserAccount u WHERE u.age=:age and u.city=:city and u.country=:country and u.id!=:id")
    List<UserAccount> findMatches(
            @Param("age") int age,
            @Param("city") String city,
            @Param("country") String country,
            @Param("id") int id);

}
