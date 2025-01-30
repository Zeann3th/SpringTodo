package dev.zeann3th.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dev.zeann3th.todo.config.converter.EnumConverter;

@Configuration
public class ApplicationConfiguration implements WebMvcConfigurer {
  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new EnumConverter());
  }
}
