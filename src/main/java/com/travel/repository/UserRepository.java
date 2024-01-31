//package com.travel.repository;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.travel.entity.User;
//
//public interface UserRepository extends JpaRepository<User, Long> {
//
//    @Query("SELECT u FROM User u WHERE u.email = :email")
//    Optional<User> findUserByEmail(@Param("email") String email);
//
//    @Query("SELECT u FROM User u WHERE u.mobile = :mobile")
//    Optional<User> findUserByMobile(@Param("mobile") String mobile);
//
//	Optional<User> findUserByEmailAndIdNot(String email, Long excludeUserId);
//
//	Optional<User> findUserByMobileAndIdNot(String mobile, Long excludeUserId);
//
//	User findByEmailOrMobile(String value, String value2);
//
//    // Other CRUD methods for the User entity are inherited from JpaRepository.
//}
