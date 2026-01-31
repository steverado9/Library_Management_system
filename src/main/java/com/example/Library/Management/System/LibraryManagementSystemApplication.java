package com.example.Library.Management.System;

import com.example.Library.Management.System.entity.User;
import com.example.Library.Management.System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class LibraryManagementSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		String encodedPassword = passwordEncoder.encode("stephen123");
		User user1 = new User(
				"Isaac Stephen", "isaac.stephen@library.com", encodedPassword, "+1234567890", "10 Library Lane, Lagos", "LIBRARIAN"
		);
		userRepository.save(user1);
	}
}
