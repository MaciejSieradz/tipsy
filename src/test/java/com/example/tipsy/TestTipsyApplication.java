package com.example.tipsy;

import org.springframework.boot.SpringApplication;

public class TestTipsyApplication {

	public static void main(String[] args) {
		SpringApplication.from(TipsyApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
