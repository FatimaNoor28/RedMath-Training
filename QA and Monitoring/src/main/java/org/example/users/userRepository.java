package org.example.users;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<user, Long> {

    user findByUserName(String userName);

    @Query(value = "SELECT u FROM Users u WHERE u.userName = ?1")
    user findByUserName2(String userName);

    @Query(value = "SELECT * FROM users WHERE user_name = ?", nativeQuery = true)
    user findByUserName3(String userName);
}
