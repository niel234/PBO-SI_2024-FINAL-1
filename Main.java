import Service.LibraryService;
import Service.LibraryServiceInterface;
import View.LibraryView;
import View.LibraryViewInterface;
import repository.RepositoryImpl;
import repository.RepositoryInterface;

public class Main {
    public static void main(String[] args) {
        RepositoryInterface repository = new RepositoryImpl();
        LibraryServiceInterface service = new LibraryService(repository);
        LibraryViewInterface view = new LibraryView(service);

        view.showMenu();
    }
}
