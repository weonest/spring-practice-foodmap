package naver.map.service;

import lombok.RequiredArgsConstructor;
import naver.map.repository.JpaMapRepository;
import naver.map.repository.MapRepository;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.EntityManager;
import javax.sql.DataSource;


@Configuration
@PropertySource("classpath:/application.properties")
@RequiredArgsConstructor
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;
    private final ApplicationContext context;


//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }

    @Bean
    public MapService mapService(){
        return new MapService(mapRepository());
    }

    @Bean
    public MapRepository mapRepository() {
        return new JpaMapRepository(em);
    }


    // 페이지네이션 1
    //DB의 커넥션과, SQL 실행에 대한 모든 것을 갖는 객체
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(context.getResources("classpath:/mappers/**/*Mapper.xml"));
        return factoryBean.getObject();
    }

    //SQL 실행에 필요한 모든 메서드(INSERT, UPDATE, DELETE, SELECT)를 갖는 객체
    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
