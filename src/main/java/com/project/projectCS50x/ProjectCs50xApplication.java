package com.project.projectCS50x;

import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
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
@StyleSheet("https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")
@JavaScript("https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js")
public class ProjectCs50xApplication implements AppShellConfigurator {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectCs50xApplication.class, args);
    }
}
