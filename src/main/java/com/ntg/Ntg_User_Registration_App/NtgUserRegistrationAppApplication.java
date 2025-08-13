package com.ntg.Ntg_User_Registration_App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NtgUserRegistrationAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NtgUserRegistrationAppApplication.class, args);
	}

}
