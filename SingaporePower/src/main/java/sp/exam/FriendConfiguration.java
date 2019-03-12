package sp.exam;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class FriendConfiguration implements WebMvcConfigurer {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		WebMvcConfigurer.super.configureContentNegotiation(configurer);
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
	}
	
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		for (HttpMessageConverter<?> converter : converters) {
//			if (converter instanceof MappingJackson2HttpMessageConverter) {
//				MappingJackson2HttpMessageConverter jacksonConv = (MappingJackson2HttpMessageConverter) converter;
//				jacksonConv.setObjectMapper(objectMapper());
//			}
//		}
//		WebMvcConfigurer.super.configureMessageConverters(converters);
//	}
//	
//	@Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
//
//        return mapper;
//    }
	
}
