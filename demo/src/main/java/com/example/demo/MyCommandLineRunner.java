package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

	@Autowired
	PersonRepository personRepository;
//	MonsterRepository monsterRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("コマンドラインランナー実行開始");
		
		//Person person = new Person("徳川家康" , 5 , "江戸城");
		
		//Person p = new Person();
		
		ArrayList<Person> persons = new ArrayList<Person>();
		persons.add(new Person("徳川家康" , 5 , "江戸城"));
		persons.add(new Person("織田信長" , 10 , "安土城"));
		persons.add(new Person("松永久秀" , 15 , "多聞山城"));
		persons.add(new Person("豊臣秀吉" , 20 , "大阪城"));
		persons.add(new Person("伊達政宗" , 25 , "仙台城"));
		persons.add(new Person("最上義光" , 30 , "山形城"));
		persons.add(new Person("真田幸村" , 35 , "上田城"));
		persons.add(new Person("武田信玄" , 40 , "甲府城"));
		
		Person p1 = new Person("徳川家康", 5 ,"江戸城");
		Person p2 = new Person("織田信長" , 10 , "安土城");
		Person p3 = new Person("松永久秀" , 15 , "多聞山城");
		Person p4 = new Person("豊臣秀吉" , 20 , "大阪城");
		Person p5 = new Person("伊達政宗" , 25 , "仙台城");
		Person p6 = new Person("最上義光" , 30 , "山形城");
		Person p7 = new Person("真田幸村" , 35 , "上田城");
		Person p8 = new Person("武田信玄" , 40 , "甲府城");

		personRepository.save(p1);
		personRepository.save(p2);
		personRepository.save(p3);
		personRepository.save(p4);
		personRepository.save(p5);
		personRepository.save(p6);	
		personRepository.save(p7);
		personRepository.save(p8);
		
		//List<Person> list = personRepository.findByAge(20);
		//List<Person> list = personRepository.findByAgeLessThan(20);
		//List<Person> list = personRepository.findByAgeGreaterThan(20);
		//List<Person> list = personRepository.findByAgeBetween(10, 30);
		
		//List<Person> list = personRepository.findByName("松永久秀");
		//List<Person> list = personRepository.findByNameLike("%秀%");
		List<Person> list = personRepository.findByNameContaining("秀");
		
//		//全検索
//		
		for(Person p : list) {
			System.out.println(p.toString());
		}
		
//		//データを消去
//		
//		for(Person p : list) {
//			
//			if(p.getId()==1) {
//			personRepository.delete(p);//ID1の人だけ消す。
//			}
//			
//		}
		
		//データを消去
//		personRepository.deleteById(1);
		
//		
		
		//データのアップデート
//		for(Person p : list) {
//			if(p.getName().equals("徳川家康")) {
//				p.setName("竹千代");
//				personRepository.save(p);
//			}
//		}
//		
		
//		Monster m1 = new Monster("スライム", 2 ,"スライム系");
//		Monster m2 = new Monster("モーモン" , 3 , "悪魔系");
//		Monster m3 = new Monster("キラーマシン" , 516 , "マシン系");
//		Monster m4 = new Monster("デビルアーマー" , 527 , "物質系");
//		Monster m5 = new Monster("キラーパンサー" , 692 , "獣系");
//		Monster m6 = new Monster("キラークリムゾン" , 9600 , "マシン系");
//		Monster m7 = new Monster("ドラゴンゾンビ・強" , 24914 , "ドラゴン系");
//		Monster m8 = new Monster("プラチナキング" , 60300 , "スライム系");
//
//		monsterRepository.save(m1);
//		monsterRepository.save(m2);
//		monsterRepository.save(m3);
//		monsterRepository.save(m4);
//		monsterRepository.save(m5);
//		monsterRepository.save(m6);	
//		monsterRepository.save(m7);
//		monsterRepository.save(m8);
//		
//		List<Monster> list = monsterRepository.findAll();
//		
//		for(Monster p : list) {
//		System.out.println(p.toString());
//		}
		
		System.out.println("コマンドラインランナー実行終了");
	}
	
}
