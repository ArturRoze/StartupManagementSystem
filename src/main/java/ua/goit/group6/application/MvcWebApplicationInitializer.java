package ua.goit.group6.application;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ua.goit.group6.configuration.DatabaseConfiguration;
import ua.goit.group6.configuration.MvcConfiguration;
import ua.goit.group6.configuration.SecurityConfiguration;

public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] {DatabaseConfiguration.class, SecurityConfiguration.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] {MvcConfiguration.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] {"/"};
  }
}
