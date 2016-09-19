package blamehub;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import javax.annotation.Resource;

@SpringBootApplication
@PropertySource("server.properties")
@EnableSolrRepositories(basePackages={"blamehub.services.solr"}, multicoreSupport=true)
public class Application extends SpringBootServletInitializer {

    @Resource
    private Environment environment;

    public Application(){}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    public SolrClient solrClient() throws Exception {
        String solrServer = environment.getRequiredProperty("solr.url");
        return new HttpSolrClient(solrServer);
    }

}
