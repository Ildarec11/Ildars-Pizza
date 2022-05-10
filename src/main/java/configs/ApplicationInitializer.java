package configs;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import security.filter.CookieAuthFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(configs.ApplicationConfig.class);
        context.setConfigLocation("configs");

        ContextLoaderListener listener = new ContextLoaderListener(context);
        servletContext.addListener(listener);

        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet(
                "dispatcherServlet",
                new DispatcherServlet(context));

        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");

        servletContext.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");
        servletContext.addFilter("cookieAuthFilter", CookieAuthFilter.class);
    }
}