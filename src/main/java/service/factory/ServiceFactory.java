package service.factory;

import service.ClientService;
import service.LibraryService;
import service.impl.ClientServiceImpl;
import service.impl.LibraryServiceImpl;

/**
 * The type Service factory.
 */
public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private static final ClientService clientService = new ClientServiceImpl();
    private static final LibraryService libraryService = new LibraryServiceImpl();

    private ServiceFactory() {

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

    /**
     * Gets client service.
     *
     * @return the client service
     */
    public ClientService getClientService() {
        return clientService;
    }

    /**
     * Gets library service.
     *
     * @return the library service
     */
    public LibraryService getLibraryService() {
        return libraryService;
    }

}