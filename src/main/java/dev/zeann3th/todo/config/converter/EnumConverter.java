package dev.zeann3th.todo.config.converter;

import org.springframework.core.convert.converter.Converter;

import dev.zeann3th.todo.domain.Status;
import dev.zeann3th.todo.exception.AppException;

public class EnumConverter implements Converter<String, Status> {
  @Override
  public Status convert(String source) {
    try {
      return Status.valueOf(source.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new AppException(400, "Invalid status: " + source);
    }
  }
}
