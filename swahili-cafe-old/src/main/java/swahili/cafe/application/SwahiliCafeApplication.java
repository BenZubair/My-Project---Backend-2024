package swahili.cafe.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import swahili.cafe.application.model.User;
import swahili.cafe.application.repository.UserRepository;


import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SwahiliCafeApplication {
	public static void main(String[] args) {
		SpringApplication.run(SwahiliCafeApplication.class, args);
	}

	//@Bean
	public User insertAdmin(UserRepository userRepository) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User user = new User();
		user.setFirstName("Moh'd");
		user.setSecondName("Zubeir");
		user.setLastName("Juma");
		user.setDateOfBirth("07-06-1997");
		user.setUsername("BenZubairy");
		user.setPassword(passwordEncoder.encode("benzubair"));
		user.setEmail("benzubair91@gmail.com");
		user.setPhoneNumber("0777 683 028");
		user.setAddress("Mbuzini");
		user.setGender("Male");
		user.setRole("ROLE_ADMIN");
		user.setActive(true);
		user.setNotLocked(true);
		userRepository.save(user);
		return user;
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList(
				"Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin", "Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"
		));
		corsConfiguration.setExposedHeaders(Arrays.asList(
				"Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization", "Access-Control-Allow-Origin",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"
		));
		corsConfiguration.setAllowedMethods(Arrays.asList(
				"GET", "POST", "PUT", "DELETE", "OPTIONS"
		));

		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
