package com.yeahzee.lab.leave.domain.person.repository;


import com.yeahzee.lab.leave.domain.person.entity.Person;

public interface IPersonRepository {

    void save(Person person);

    void update(Person person);

    Person findById(String id);

    Person findLeaderByPersonId(String personId);

}