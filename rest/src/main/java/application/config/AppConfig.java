package application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.ItemService;
import service.ItemServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public ItemService createItemService(){
        return new ItemServiceImpl();
    }
}
