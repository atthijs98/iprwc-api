package com.hsleiden.iprwc.api;

import com.hsleiden.iprwc.api.model.*;
import com.hsleiden.iprwc.api.service.ProductService;
import com.hsleiden.iprwc.api.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, ProductService productService) {
		return args -> {
//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//
//			userService.saveUser(new User(null, "Azariah Lynch", "azariah@gmail.com", "password", new ArrayList<Role>(),true));
//			userService.saveUser(new User(null, "Matthijs van Eijk", "matthijs@gmail.com", "password", new ArrayList<Role>(),true));
//
//			userService.addRoleToUser("azariah@gmail.com", "ROLE_USER");
//			userService.addRoleToUser("matthijs@gmail.com", "ROLE_USER");
//			userService.addRoleToUser("matthijs@gmail.com", "ROLE_ADMIN");
//
//			ProductImage image = productService.saveProductImage(new ProductImage(null, "https://criterion-production.s3.amazonaws.com/carousel-files/f7600f81bf5d789361dd7945c5082ae7.jpeg", "family"));
//			ProductDirector director= productService.saveProductDirector(new ProductDirector(null, "Bong Joon Ho"));
//
//			Product parasite = productService.saveProduct(new Product(null,
//					"Parasite",
//					"기생충" ,
//					"Gisaengchung",
//					"2019",
//					"133",
//					"All unemployed, Ki-taek’s family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.",
//					"https://s3.amazonaws.com/criterion-production/films/640531357bc7916fa876ccfc7942fda3/7R4fM1suypYVLJvGioVEqHFqwEu1yM_large.jpg",
//					"5xH0HfJHsaY",
//					23.99,
//					null,
//					null));
//
//			productService.addImageToProduct(parasite.getId(), image.getId());
//			productService.addDirectorToProduct(parasite.getId(), director.getId());

		};
	}

}
