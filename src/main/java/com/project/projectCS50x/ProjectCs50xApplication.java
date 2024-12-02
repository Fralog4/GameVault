package com.project.projectCS50x;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@SpringBootApplication
@Theme("my-theme")
@Push
public class ProjectCs50xApplication implements AppShellConfigurator {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectCs50xApplication.class, args);
    }
}
