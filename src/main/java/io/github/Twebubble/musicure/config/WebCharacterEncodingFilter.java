package io.github.Twebubble.musicure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/*解决问题：因使用WebMvcConfigurer加载静态时 url encode编码无法解析为正常中文问题*/
@EnableWebMvc
@Configuration
public class WebCharacterEncodingFilter implements WebMvcConfigurer {

    /**
     * 创建 UTF-8 编码的字符串消息转换器，处理乱码
     */
    public HttpMessageConverter<String> responseBodyConverter() {
        final StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        converter.setWriteAcceptCharset(false);
        return converter;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        if (!converters.isEmpty()) {
            converters.add(converters.get(0));
            converters.set(0, responseBodyConverter());
        } else {
            converters.add(responseBodyConverter());
        }
    }
}
