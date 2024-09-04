package com.homeboard.homeboard.Repository;

import org.hibernate.internal.util.type.PrimitiveWrapperHelper.IntegerDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeboard.homeboard.model.User;

@Repository
public interface UsrRepository extends JpaRepository<User, Integer> {

}
