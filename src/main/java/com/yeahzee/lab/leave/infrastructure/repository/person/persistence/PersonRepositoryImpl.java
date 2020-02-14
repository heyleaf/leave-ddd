package com.yeahzee.lab.leave.infrastructure.repository.person.persistence;

import com.yeahzee.lab.leave.domain.person.entity.Person;
import com.yeahzee.lab.leave.domain.person.repository.IPersonRepository;
import com.yeahzee.lab.leave.infrastructure.repository.person.mapper.PersonDao;
import com.yeahzee.lab.leave.infrastructure.repository.person.po.PersonPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl implements IPersonRepository {

    @Autowired
    PersonDao personDao;

    @Autowired
    PersonFactory personFactory;

    @Override
    public void insert(Person person) {
        personDao.save(personFactory.createPersonPO(person));
    }

    @Override
    public void update(Person person) {
        personDao.save(personFactory.createPersonPO(person));
    }

    @Override
    public Person findById(String id) {
        PersonPO personPO = personDao.findById(id).orElseThrow(() -> new RuntimeException("未找到用户"));
        return personFactory.createPerson(personPO);
    }

    @Override
    public Person findLeaderByPersonId(String personId) {
        PersonPO personPO = personDao.findLeaderByPersonId(personId);
        return personFactory.createPerson(personPO);
    }

}