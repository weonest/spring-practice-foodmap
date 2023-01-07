package naver.map.service;

import naver.map.repository.JpaMapRepository;
import naver.map.repository.JpaMapRepository2;
import naver.map.repository.MapRepository;
import naver.map.repository.MapRepository2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MapService mapService(){
        return new MapService(mapRepository());
    }

    @Bean
    public MapService2 mapService2(){
        return new MapService2(mapRepository2());
    }

    @Bean
    public MapRepository mapRepository() {
        return new JpaMapRepository(em);
    }

    @Bean
    public MapRepository2 mapRepository2() {
        return new JpaMapRepository2(em);
    }
}
