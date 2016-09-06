package game.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import factory.ConnectionNodeBuilder;
import factory.EventBuilder;
import game.connection.ConnectionPool;
import game.datahandler.AIHandler;
import game.datahandler.AmmoPool;
import game.datahandler.HighScoreTable;
import game.datahandler.ItemHandler;
import game.datahandler.ItemPool;
import game.datahandler.PlayerPool;
import game.scheduler.TaskScheduler;
import game.service.AmmoDataProcessor;
import game.service.CoordinateHandler;
import game.service.ItemProcessor;
import game.service.PlayerDataProcessor;
import game.transformer.PlayerDataToRegistrationAnswerTransformer;
import game.transformer.PlayerDataToSentPlayerDataTransformer;
import game.transformer.ReceivedPlayerDataToPlayerDataTransformer;
import game.transformer.RegistrationDataToPlayerDataTransformer;
import game.transformer.ShipConfigToShipsDetailsTransformer;

@Configuration
public class SpringConfig {

    @Bean
    public TaskScheduler taskScheduler() {
        return new TaskScheduler();
    }

    @Bean
    public AmmoPool ammmoPool() {
        return new AmmoPool();
    }

    @Bean
    public HighScoreTable highScoreTable() {
        return new HighScoreTable();
    }

    @Bean
    public ItemHandler itemHandler() {
        return new ItemHandler();
    }

    @Bean
    public ItemPool itemPool() {
        return new ItemPool();
    }

    @Bean
    public PlayerPool playerPool() {
        return new PlayerPool();
    }

    @Bean
    public AIHandler aIHandler() {
        return new AIHandler();
    }

    @Bean
    public AmmoDataProcessor ammoDataProcessor() {
        return new AmmoDataProcessor();
    }

    @Bean
    public CoordinateHandler coordinateHandler() {
        return new CoordinateHandler();
    }

    @Bean
    public ItemProcessor itemProcessor() {
        return new ItemProcessor();
    }

    @Bean
    public PlayerDataProcessor playerDataProcessor() {
        return new PlayerDataProcessor();
    }

    @Bean
    public ConnectionPool connectionPool() {
        return new ConnectionPool();
    }

    @Bean
    public EventBuilder eventBuilder() {
        return new EventBuilder();
    }

    @Bean
    public ConnectionNodeBuilder connectionNodeBuilder() {
        return new ConnectionNodeBuilder();
    }

    @Bean
    public PlayerDataToRegistrationAnswerTransformer playerDataToRegistrationAnswerTransformer() {
        return new PlayerDataToRegistrationAnswerTransformer();
    }

    @Bean
    public PlayerDataToSentPlayerDataTransformer playerDataToSentPlayerDataTransformer() {
        return new PlayerDataToSentPlayerDataTransformer();
    }

    @Bean
    public ReceivedPlayerDataToPlayerDataTransformer receivedPlayerDataToPlayerDataTransformer() {
        return new ReceivedPlayerDataToPlayerDataTransformer();
    }

    @Bean
    public RegistrationDataToPlayerDataTransformer registrationDataToPlayerDataTransformer() {
        return new RegistrationDataToPlayerDataTransformer();
    }

    @Bean
    public ShipConfigToShipsDetailsTransformer shipConfigToShipsDetailsTransformer() {
        return new ShipConfigToShipsDetailsTransformer();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Bean(name = "localeResolver")
    public LocaleResolver sessionLocaleResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("en"));

        return localeResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:localization/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
