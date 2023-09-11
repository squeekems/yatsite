//package com.squeekems.yat.config;
//
//import com.squeekems.yat.entities.Event;
//import com.squeekems.yat.entities.Item;
//import com.squeekems.yat.entities.Option;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
//import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//
//@Configuration
//public class MyDataRestConfig implements RepositoryRestConfigurer {
//
//  @Override
//  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
//                                                   CorsRegistry cors) {
//    HttpMethod[] theUnsupportedActions = {
//        HttpMethod.POST,
//        HttpMethod.PATCH,
//        HttpMethod.DELETE,
//        HttpMethod.PUT};
//    config.exposeIdsFor(Event.class);
//    disableHttpMethods(Event.class, config, theUnsupportedActions);
//    config.exposeIdsFor(Option.class);
//    disableHttpMethods(Option.class, config, theUnsupportedActions);
//    config.exposeIdsFor(Item.class);
//    disableHttpMethods(Item.class, config, theUnsupportedActions);
//
//    // Configure CORS Mapping //
//    String theAllowedOrigins = "http://localhost:3000";
//    cors.addMapping(config.getBasePath() + "/**")
//        .allowedOrigins(theAllowedOrigins);
//  }
//
//  private void disableHttpMethods(Class<?> theClass, RepositoryRestConfiguration config, HttpMethod... theUnsupportedActions) {
//    config.getExposureConfiguration()
//        .forDomainType(theClass)
//        .withItemExposure((metdata, httpMethods) ->
//            httpMethods.disable(theUnsupportedActions))
//        .withCollectionExposure((metdata, httpMethods) ->
//            httpMethods.disable(theUnsupportedActions));
//  }
//
//}
