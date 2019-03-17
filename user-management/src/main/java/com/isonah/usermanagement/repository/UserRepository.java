package com.isonah.usermanagement.repository;

import com.isonah.usermanagement.model.MystoryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ielksseyer
 */

@Repository
public interface UserRepository extends JpaRepository<MystoryUser, String> {

}
