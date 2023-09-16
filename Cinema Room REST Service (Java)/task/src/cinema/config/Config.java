package cinema.config;

import cinema.model.Hall;
import cinema.model.Seat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
public class Config {

    @Bean
    public Hall hall(){
       return new Hall(9, 9, new CopyOnWriteArrayList<Seat>());
    }
}
