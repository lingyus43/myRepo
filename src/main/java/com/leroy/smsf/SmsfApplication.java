package com.leroy.smsf;

import com.leroy.smsf.exception.FormattingException;
import com.leroy.smsf.service.AllocationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmsfApplication {
	@Value("${path}")
	private static String path;
	public static void main(String[] args) throws FormattingException {
		SpringApplication.run(SmsfApplication.class, args);
		AllocationService allocationService = new AllocationService();
		allocationService.allocateProfitAndTaxPayable();
	}


}

