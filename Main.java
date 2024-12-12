import repository.RepositoryImpl;
import Service.LibraryService;
import Service.LibraryServiceInterface;
import View.LibraryView;
import View.LibraryViewInterface;
import congfig.Database;
import repository.RepositoryInterface;

public class Main {
    public static void main(String[] args) {


        Database database = new Database("db_perpustakaan", "root", "", "localhost", "3306");
        database.setup();  // Set up the database connection

        // RepositoryImpl now expects a Database object, not a direct Connection
        RepositoryInterface repository = new RepositoryImpl(database);

        LibraryServiceInterface service = new LibraryService(repository);
        LibraryViewInterface view = new LibraryView(service);
        view.showMenu();

        // Connection will be closed inside the RepositoryImpl or Database class as per your design
    }
}