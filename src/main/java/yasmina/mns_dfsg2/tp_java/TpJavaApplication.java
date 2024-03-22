package yasmina.mns_dfsg2.tp_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TpJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpJavaApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}

}
