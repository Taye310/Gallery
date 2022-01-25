package com.providence.gallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalleryApplication.class, args);
	}

}
