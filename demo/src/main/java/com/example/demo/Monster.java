package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Monster {
	
	public Monster(String name, int exp, String lineage) {
		super();
		this.name = name;
		this.exp = exp;
		this.lineage = lineage;
	}
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;

    @Min(value = 0, message = "Exp must be positive")
    private int exp;

    @NotBlank(message = "Lineage cannot be empty")
    private String lineage;
    
}
