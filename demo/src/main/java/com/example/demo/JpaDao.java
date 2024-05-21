package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface JpaDao extends JpaRepository<Person,Integer>{

	@Transactional
	@Modifying
	@Query(value = "UPDATE person SET name = :pname, age = :page, address = :paddress WHERE id = :pid", nativeQuery = true)
	void updateDb(@Param("pname") String pname, @Param("page") int page, @Param("paddress") String paddress, @Param("pid") int pid);
	
}
