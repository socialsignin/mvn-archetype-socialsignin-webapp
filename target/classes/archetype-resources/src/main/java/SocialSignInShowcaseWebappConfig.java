/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ${package};

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class SocialSignInShowcaseWebappConfig {


	#if(${useInMemoryDataSource.equals('true')})
		
		@Bean
	    public DataSource dataSource() {
	        return new EmbeddedDatabaseBuilder()
	            .setType(EmbeddedDatabaseType.HSQL)
	            .addScript("classpath:org/springframework/social/connect/jdbc/JdbcUsersConnectionRepository.sql")
	            .build();
	    }
	
	#end

	@Bean
	public HandlerExceptionResolver defaultHandlerExceptionResolver()
	{
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.put("org.socialsignin.springframework.social.security.signin.NonUniqueConnectionException", "connect/providerConnect");

		resolver.setDefaultErrorView("exception");
		
		resolver.setExceptionMappings(mappings);
		return resolver;
	}
	
	
	
	

	@Bean
	public DefaultAnnotationHandlerMapping handlerMapping() throws Exception {

		DefaultAnnotationHandlerMapping mapping = new DefaultAnnotationHandlerMapping();
		return mapping;
	}
	

	@Bean
	public AnnotationMethodHandlerAdapter handlerAdapter() throws Exception {

		AnnotationMethodHandlerAdapter mapping = new AnnotationMethodHandlerAdapter();
		return mapping;
	}

}
