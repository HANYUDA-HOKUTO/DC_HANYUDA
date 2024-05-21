package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="profile")
public class Profile {
	
	public Profile(String name, int age, String profession, String hobby) {
		super();
		this.name = name;
		this.age = age;
		this.profession = profession;
		this.hobby = hobby;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "名前を入力して下さい!!")
    @Size(min = 2, max = 30, message = "名前は2文字以上～30文字以内で入力して下さい!!")
    private String name;

    @Min(value = 0, message = "年齢が有効ではありません!!")
    private int age;

    @NotBlank(message = "職業を入力して下さい!!")
    private String profession;
    
    @NotBlank(message = "趣味を入力して下さい!!")
    private String hobby;
    
}
