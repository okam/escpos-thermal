package com.github.okam.escpos;

import com.google.common.base.Strings;
import freemarker.core.Environment;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.TimeZone;

public class TemplateParser {
  public static String parseTemplate(
      Template template, Object model, Locale locale, String timeZone)
      throws IOException, TemplateException {
    StringWriter writer = new StringWriter();

    Environment env = template.createProcessingEnvironment(model, writer);
    env.setOutputEncoding("UTF-8");

    if (locale != null) env.setLocale(locale);

    if (!Strings.isNullOrEmpty(timeZone)) env.setTimeZone(TimeZone.getTimeZone(timeZone));

    env.process();
    return writer.toString();
  }
}
