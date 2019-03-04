package com.tiny.springbootflowable.repository;

import com.tiny.springbootflowable.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author tiny lin
 * @date 2019/2/11
 */
public interface PersonRepository extends JpaRepository<Person,Long> {
    Person findByUsername(String username);
    Person findById(String id);
}
