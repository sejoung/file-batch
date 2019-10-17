package com.github.sejoung.repositorys.jpa;

import com.github.sejoung.model.jpa.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Long, Test> {

}
