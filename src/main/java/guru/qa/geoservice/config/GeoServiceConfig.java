package guru.qa.geoservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
public class GeoServiceConfig {

    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return builder.dateFormat(df).build();
    }
}
