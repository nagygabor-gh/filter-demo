# Filters

## What are filters?

Filters are pluggable components used to intercept (and possibly modify) HTTP requests and responses of your application.
By using filters, we can for example:

 - log requests and responses
 - log request processing time
 - manipulate / format request and response body or headers
 - authentication & authorization, verification of authentication tokens
 - compress response before sending to client
 - adding cookies to response
 - etc.

A very common requirement is to add / read a header to the response / request. For example, if you would like to add a 
custom header to your responses, instead of adding that in each resource method you implement, you could just add a 
response filter and add the custom header you'd like to use. 

There are filters on the server side and the client side as well.

**Server side filters:**
- **ContainerRequestFilter**
  - must be registered as a provider
  - executed before the resource method is run, before the response is created
  - possible to manipulate request parameters, including headers or entity itself
- **ContainerResponseFilter**
  - must be registered as provider
  - executed for every response after the resource method is executed
  - executed even if resource method is not run, e.g. resource method is not found and 404 is returned

**Client side filters:**
- **ClientRequestFilter**
  - request can also be aborted here, in this case no request will be sent to the server
- **ClientResponseFilter**
  - if request is sent to the server, the response will be processed here

It is possible to have multiple filters for the same resource, of course, in this case a chain of filters is applied. 
Ordering can be specified in two ways:
  - via the @Priority annotation
  - via registration: ResourceConfig#register(Class<?> componentClass, int bindingPriority)

## Post-matching and pre-matching (container request) filters

These filters are **by default post-matching filters**. What it means is that filters are **applied after a matching 
resource method has been selected to process the request**, i.e. after request matching happens. Request matching is 
the process of finding a resource method that should be executed based on the request path and other request parameters.
Since post-matching request filters are invoked when a particular resource method has already been selected, such 
filters **cannot influence the resource method matching process**.

To overcome this, **it is possible to mark a container request filter as pre-matching filter, with the 
@PreMatching annotation**. Pre-matching filters are request filters that are executed before the request matching is 
started and have the possibility to influence which method will be matched. You can change the HTTP method, modify the 
request URI etc.

## Order

**Request** &rarr; Pre Matching Container Request Filter &rarr; Resource matching &rarr;  
Container Request Filter &rarr; Resource processing / logic &rarr; Container Response Filter &rarr; 
**Response**

# Defining filters in Spring Boot

 - Either **implement the interface *jakarta.servlet.Filter***

   - **ordering with @Order** annotation

 - Or **define a *FilterRegistrationBean*** and register the filter with it

    - **ordering** with FilterRegistrationBean#setOrder
    - **conditional filtering** with FilterRegistrationBean#addUrlPatterns

## Important methods of the Filter

### init(FilterConfig filterConfig)

It is called when the filter is added to the services. The container calls this method only once, upon initialization
of the container. This method must complete successfully before the filter can be used. The filter cannot be used if:
 - init throws a ServletException
 - init does not return in a timely manner

### doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)

The container calls this method every time a client sends request or the applications sends a response. Here you should 
define your custom logic. The application will not run if:
 - doFilter throws an unhandled exception
 - the doFilter method of the next filter in the chain is not called

### destroy()

The container calls it to indicate to a filter that it is being taken out of service. It is called only once during the 
lifecycle of the filter. It gives the filter opportunity to clean up any resources that are being held, e.g. memory, 
file handles.

## What is JAX-RS?

Java API for RESTful Web Services (JAX-RS) is a specification that supports creating web services according to the REST 
architectural pattern, defined by [JSR-370](https://jcp.org/en/jsr/detail?id=370). JAX-RS uses annotations to simplify 
the development and deployment of web service clients and endpoints. JAX-RS is an official part of Java EE, defined by
[JSR-366](https://jcp.org/en/jsr/detail?id=366).

## What is Jersey?

Jersey is an open source framework for developing RESTful Web Services in Java, shipped with Glassfish / Payara. 
It is an implementation of the JAX-RS specification, there are others as well: RESTEasy (shipped with JBoss and 
WildFly), Apache CXF etc. Jersey is also an alternative to Spring RESTFul applications created with @RestController. 
The REST capabilities of Spring are provided by the Spring MVC module, and it is not a JAX-RS implementation, it is an 
alternative to the JAX-RS standard. 

Spring Boot provides the spring-boot-starter-jersey dependency that allows you to use the JAX-RS programming model
instead of Spring MVC.

## Should I use JAX-RS or Spring Boot?

**Spring:**

  - non-standard API implementation
  - frequently changes
  - less / no backward compatibility
  - full stack implementation, not just REST
  - comes with other services such as ORM, Spring Security Module, etc.
  - use it when you are already in the Spring world / using things like dependency injection, AOP, transactions etc.

**JAX-RS:**

  - follows standard API implementation
  - specifically designed for REST
  - backward compatibility (e.g.: when a new JDK version gets released)
  - part of Java EE, which can be used with other Java EE technologies
  - use it if you need a small REST only application / are already in Java EE

## References

* [How to define a Spring Boot filter](http://www.baeldung.com/spring-boot-add-filter)
* [Jersey REST API with Spring](https://www.baeldung.com/jersey-rest-api-with-spring)
* [Jersey filters and interceptors](https://www.baeldung.com/jersey-filters-interceptors)
* [JAX-RS client with Jersey](https://www.baeldung.com/jersey-jax-rs-client)
* [Filters and interceptors](https://eclipse-ee4j.github.io/jersey.github.io/documentation/latest31x/filters-and-interceptors.html)
* [Java servlet filters](https://www.digitalocean.com/community/tutorials/java-servlet-filter-example-tutorial)
* [Spring Boot Jersey](https://zetcode.com/springboot/jersey)
* [JAX-RS vs Spring Boot](https://saikomalpendela.medium.com/jax-rs-vs-spring-boot-a980a1b99b43)
