package com.yeahzee.lab.leave.infrastructure.repository.person.mapper;

import com.yeahzee.lab.leave.infrastructure.repository.person.po.PersonPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<PersonPO, String> {

    @Query(value = "select p from PersonPO  p where p.relationshipPO.personId=?1")
    PersonPO findLeaderByPersonId(String personId);
}
