package com.codingdojo.joybundler.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.joybundler.models.Name;

@Repository
public interface NameRepository extends CrudRepository<Name, Long> {
	List<Name> findAll();
	Optional<Name> findByName(String nameIn);
}
