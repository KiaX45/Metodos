package Metodos.vol.api.Cors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Esto aplica la política a todas las rutas
                .allowedOrigins(
                        "http://localhost:53300", // Añade aquí las URLs permitidas
                        "http://localhost:8080",
                        "https://sweet-capybara-7f5d4c.netlify.app/"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}