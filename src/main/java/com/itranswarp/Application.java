package com.itranswarp;

import com.itranswarp.web.support.MvcConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Instant;

/**
 * Application entry.
 * 
 * @author liaoxuefeng
 */
@SpringBootApplication
public class Application {

	static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static final String VERSION = Application.class.getPackage().getImplementationVersion() == null
			? String.valueOf(Instant.now().getEpochSecond())
			: Application.class.getPackage().getImplementationVersion();

	public static void main(String[] args) {
		logger.info("start application version {}...", VERSION);
		SpringApplication.run(Application.class, args);
	}

}
