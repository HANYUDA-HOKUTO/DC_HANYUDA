package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

	@Autowired
	PersonRepository personRepository;

	public ArrayList<Person> getPersonList(){
		
		ArrayList<Person> list = new ArrayList<Person>();
		
	    list.add(new Person("徳川家康" , 5 , "江戸城"));
		list.add(new Person("織田信長" , 10 , "安土城"));
		list.add(new Person("松永久秀" , 15 , "多聞山城"));
		list.add(new Person("豊臣秀吉" , 20 , "大阪城"));
		list.add(new Person("伊達政宗" , 25 , "仙台城"));
		list.add(new Person("最上義光" , 30 , "山形城"));
		list.add(new Person("真田幸村" , 35 , "上田城"));
		list.add(new Person("武田信玄" , 40 , "甲府城"));

		return list;
	}
	
	public List<Person> getPersonList(String keyword){	
		
		List<Person> list = personRepository.findByNameContaining(keyword);
		
		return list;
		
	}
	
	
//	public void updatePerson(int id, String name, int age, String address) {
//		Person person = personRepository.findById(id);
//		if(person != null) {
//			person.setName(name);
//			person.setAge(age);
//			person.setAddress(address);
//			personRepository.save(person);
//		}
//	}

}