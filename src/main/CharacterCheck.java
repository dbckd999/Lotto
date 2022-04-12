package main;

import java.util.regex.Pattern;

public class CharacterCheck {
	String string;
	
	public CharacterCheck(String s) {
		// TODO Auto-generated constructor stub
		this.string = s;
		
		
	}
	
	static boolean effectivenessCheck(String string) {
		
		if(string.equals("")
			| string.contains(" ")
			| string.contains(".")
			| string.contains("&")
			| string.contains("*")
			| englishCheck(string)
			) {
			return false;
		}else {
			return true;
		}
	}
	
	//영어 숫자 검사
	static boolean englishCheck(String s) {
		if(Pattern.matches("[^a-zA-Z0-9]", s)){
			System.out.println("영어 숫자만 입력해야 합니다.");
			return false;
		}else {
			return true;
		}
	}
	
}
