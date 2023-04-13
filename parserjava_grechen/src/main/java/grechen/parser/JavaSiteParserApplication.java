package grechen.parser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaSiteParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSiteParserApplication.class, args);
        WebDriverManager.chromedriver().setup();
    }

}