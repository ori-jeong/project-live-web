package com.onlive.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@MapperScan(basePackages = {"com.onlive"})
@EnableTransactionManagement
public class DatabaseConfig {
//sqlSessionFactoryRef = "oracleOneSqlSessionFactory"
    //@Bean(name= "oracleOneSqlSessionFactory")
    
    /* 데이터베이스 연결과 sql 실행 */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        /* 세션 생성 시, 빌드된 dataSource를 세팅하고 SQL문을 관리할 mapper.xml 경로를 지정해준다 */
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        /* mapper.xml 경로 지정 */
        PathMatchingResourcePatternResolver res = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(res.getResources("classpath*:mybatis/mapper/*.xml"));
        SqlSessionFactory sqlSessionFactory = sessionFactory.getObject();
        sqlSessionFactory.getConfiguration().setCacheEnabled(false); // Query Cache 여부
        sqlSessionFactory.getConfiguration().setUseGeneratedKeys(true); // Insert Generate Key 사용여부
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true); // CamelCase 변환 여부
        //실제 DB 컬럼명 스네이크 표기법 = 카멜케이스 표기법 팹핑
//        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
//        configuration.setMapUnderscoreToCamelCase(true);
        return sqlSessionFactory;
    }
    
    @Bean(name="sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception{
        //SqlSession을 구현하고 SqlSession을 대체하는 역할로 Mybatis 쿼리문을 수행해준다
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    //jpa 사용을 위한 빈 설정    
    //LocalContainerEntityManagerFactoryBean: 스프링부트에서 제공하는 컨테이너 관리 EntityManager를 위한 EntityManagerFactory를 만든다
    //          javaEE 서버에 배치하지 않아도 JPA 기능 활용 가능, 일관성 있는 데이터 엑세스 기술 접근 방식 적용, JPA 확장 기능 활용 가능    
    //HibernateJpaVendorAdapter: JPA 구현체인 Hibernate를 이용하려면 등록
    //setPackageToScan : @EntityScan과 같은 역할, entity들을 스캐닝 함
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
        
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        //vendorAdapter.setGenerateDdl(true);
        //vendorAdapter.setShowSql(true);
        //vendorAdapter.setDatabasePlatform("oracle");
        
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.onlive.common.entity");    //entity 패키지 주소
        factory.setDataSource(dataSource);
        return factory;
    }
    
    
    
    
    
    
//    @Bean
//    public DataSourceTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }


//    @Bean(name = "batchSqlSessionTemplate")
//    public SqlSessionTemplate batchSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH); // ExecuteType 을 Batch 로 지정
//    }
}
