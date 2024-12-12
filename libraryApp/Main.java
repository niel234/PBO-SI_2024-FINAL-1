package libraryApp;

import libraryApp.repository.RepositoryImpl;
import libraryApp.Service.LibraryService;
import libraryApp.Service.LibraryServiceInterface;
import libraryApp.View.LibraryView;
import libraryApp.View.LibraryViewInterface;
import libraryApp.congfig.Database;
import libraryApp.repository.RepositoryInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "libraryApp")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        LibraryView libraryView = context.getBean(LibraryView.class);
        libraryView.showMenu();
    }
    @Bean
    Database database() {
        Database database = new Database("db_perpustakaan", "root", "", "localhost", "3306");
        database.setup();  // Set up the database connection
        return database;
    }

}