package com.github.okam.escpos;

import com.google.common.io.BaseEncoding;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLParserTest {
  private final String TEST_DIR = "src/test/resources/";

  private static final Logger LOGGER = LoggerFactory.getLogger(XMLParser.class);

  @BeforeEach
  void setup() {}

  @Test
  void parse_validXMLTemplate_succeeds() throws IOException, TemplateException {
    TestTemplateModel model = new TestTemplateModel();
    Configuration config = new Configuration();
    Template template = config.getTemplate(TEST_DIR + "sample_template.xml", "UTF-8");

    String xml = TemplateParser.parseTemplate(template, model, null, null);
    BufferBuilder bufferBuilder = XMLParser.parse(xml, "UTF-8");

    String actual = BaseEncoding.base64().encode(bufferBuilder.build());
    LOGGER.info(actual);

    String expected = new String(Files.readAllBytes(Paths.get(TEST_DIR + "sample_template.pos")));
    Assertions.assertEquals(expected, actual);
  }
}
