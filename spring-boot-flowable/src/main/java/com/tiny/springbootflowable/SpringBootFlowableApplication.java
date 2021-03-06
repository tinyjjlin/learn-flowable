package com.tiny.springbootflowable;

import com.tiny.springbootflowable.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Slf4j
@SpringBootApplication(scanBasePackages = {"com.tiny.conf", "com.tiny.springbootflowable"})
@EnableTransactionManagement
public class SpringBootFlowableApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFlowableApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(final MyService myService) {

        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                log.info("#.....add person....................");
                myService.createDemoUsers();
            }
        };
    }
//
//    @Bean
//    public CommandLineRunner init(final RepositoryService repositoryService,
//                                  final RuntimeService runtimeService,
//                                  final TaskService taskService) {
//
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... strings) throws Exception {
//                System.out.println("Number of process definitions : "
//                        + repositoryService.createProcessDefinitionQuery().count());
//                System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
//                runtimeService.startProcessInstanceByKey("oneTaskProcess");
//                System.out.println("Number of tasks after process start: "
//                        + taskService.createTaskQuery().count());
//            }
//        };
//    }
}

