package com.almostkbal.web.services.workflow.configurations;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.MediaType;

@Configuration
public class RepositoryRestDataConfiguration implements RepositoryRestConfigurer{
	@Autowired
    private EntityManager entityManager;
	@Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//        System.out.println("\n\n RepositoryRestDataConfiguration :: configureRepositoryRestConfiguration");
		config.exposeIdsFor(
                entityManager.getMetamodel().getEntities().stream()
                .map(Type::getJavaType)
                .toArray(Class[]::new));
		config.setDefaultPageSize(1000);
//		config.setDefaultMediaType(MediaType.APPLICATION_JSON);
    }
}
