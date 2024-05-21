package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
//5行目がclassだった場合でもinterfaceに書き直す事で解決できる。
public interface PersonRepository extends JpaRepository<Person, Integer>{

	List<Person> findByAge(int age);
	List<Person> findByAgeLessThan(int age);
	List<Person> findByAgeGreaterThan(int age);
	List<Person> findByAgeBetween(int age1, int age2);
	
	List<Person> findByName(String name);
	List<Person> findByNameLike(String name);
	List<Person> findByNameContaining(String name);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE person SET name = :pname, age = :page, address = :paddress WHERE id = :pid", nativeQuery = true)
	void updateDb(@Param("pname") String pname, @Param("page") int page, @Param("paddress") String paddress, @Param("pid") int pid);
	
	Person findById(int id);
}
