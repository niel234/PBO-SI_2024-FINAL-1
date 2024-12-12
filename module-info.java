module libraryApp {
    requires java.sql;
    requires spring.beans;
    requires spring.context;

    opens libraryApp;
    opens libraryApp.Entity;
    opens libraryApp.repository;
    opens libraryApp.Service;
    opens libraryApp.View;
    opens libraryApp.congfig;
}
