package com.tiny.flowalbe02;

import com.tiny.flowalbe02.service.LoanFormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author tiny lin
 * @date 2019/8/5
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.tiny.*"})
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public CommandLineRunner init(final LoanFormService myService) {
//
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... strings) throws Exception {
//                log.info("#.....add person....................");
//                myService.createDemoUsers();
//            }
//        };
//    }
}
