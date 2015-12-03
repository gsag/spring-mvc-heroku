package config;

import com.google.common.cache.CacheBuilder;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/*
 * Created by guilherme on 22/11/15.
 */
@Configuration
@EnableWebMvc
@EnableCaching
@ComponentScan(basePackages = {"controller", "util"})
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    public static final String DEFAULT_CHAR_ENCODING = "UTF-8";
    private static final String TEMPLATE_RESOLVER_PREFIX = "/WEB-INF/views/";
    private static final String TEMPLATE_RESOLVER_SUFFIX = ".html";
    private static final String TEMPLATE_RESOLVER_TEMPLATE_MODE = "HTML5";
    private static final String TEMPLATE_RESOLVER_CHAR_ENCODING = DEFAULT_CHAR_ENCODING;

    /*
     * Configure Template Resolver - Thymeleaf
     */
    @Bean
    public TemplateResolver templateResolver() {
        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix(TEMPLATE_RESOLVER_PREFIX);
        templateResolver.setSuffix(TEMPLATE_RESOLVER_SUFFIX);
        templateResolver.setTemplateMode(TEMPLATE_RESOLVER_TEMPLATE_MODE);
        templateResolver.setCharacterEncoding(TEMPLATE_RESOLVER_CHAR_ENCODING);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    /*
     * Configure Template Engine - Thymeleaf
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

    /*
     * Configure View Resolver
     */
    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding(TEMPLATE_RESOLVER_CHAR_ENCODING);
        return viewResolver;
    }

    /*
     * Configure Locale Resolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new FixedLocaleResolver(new Locale("pt", "BR"));
    }

    /*
     * Configure Default Servlet Handling
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /*
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript
     * etc...
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    }

    /*
     * Configure MessageSource to provide internationalized messages
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
        bundle.setBasename("messages");
        bundle.setDefaultEncoding(TEMPLATE_RESOLVER_CHAR_ENCODING);
        bundle.setCacheSeconds(5);
        return bundle;
    }

    /*
     * Configure caching manager
     */
    @Bean
    public CacheManager cacheManager() {
        CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder()
                .maximumSize(100).expireAfterAccess(5, TimeUnit.MINUTES);
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        cacheManager.setCacheBuilder(builder);
        return cacheManager;
    }

    /*
     * Configure cors mapping for cross origin resource sharing
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowCredentials(false)
                .maxAge(3600);
    }
}