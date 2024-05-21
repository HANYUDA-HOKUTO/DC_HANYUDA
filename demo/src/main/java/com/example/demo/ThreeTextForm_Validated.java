package com.example.demo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ThreeTextForm_Validated {
	
	@NotBlank(message="何か入力してください")
	private String paramA = "";

	@Size(min=3, max=10, message="3～10文字以内にしてください" )
	private String paramB = "";
	
	@NotBlank(message="何か入力してください")
	@Email(message="メールアドレスを入力してください") 
	private String paramC = "";

	public String getParamA() {
		return paramA;
	}

	public void setParamA(String paramA) {
		this.paramA = paramA;
	}

	public String getParamB() {
		return paramB;
	}

	public void setParamB(String paramB) {
		this.paramB = paramB;
	}

	public String getParamC() {
		return paramC;
	}

	public void setParamC(String paramC) {
		this.paramC = paramC;
	}
}
