Ex 12 
package Hello;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyService extends Remote {
    String processRequest(String message) throws RemoteException;
}
package Hello;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; // 🛑 NEW IMPORT

public class MyServiceImpl extends UnicastRemoteObject implements MyService {
	private static final long serialVersionUID = 1L; // ✅ Add this line
    // 🛑 NEW: Define a fixed-size thread pool (e.g., 4 threads)
    private final ExecutorService executorService; 

    protected MyServiceImpl() throws RemoteException {
        super();
        // 🛑 NEW: Initialize the thread pool
        // This pool will only create up to 4 worker threads total.
        this.executorService = Executors.newFixedThreadPool(4); 
        System.out.println("Server initialized with a 4-thread pool.");
    }

    @Override
    public String processRequest(String message) throws RemoteException {
        
        // 🛑 CHANGE: Submit the work to the thread pool instead of spawning a new Thread
        executorService.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("[" + LocalDateTime.now() + "] Worker " + threadName + " started for: " + message);
            try {
                // simulate work
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("[" + LocalDateTime.now() + "] Worker " + threadName + " finished for: " + message);
        });
        // ----------------------------------------------------------------------------------------

        // return immediate acknowledgement to client
        return "ACK: " + message + " (handled by server thread: " + Thread.currentThread().getName() + ")";
    }
    
    // Optional: Add a shutdown method to close the pool when the server is stopped gracefully
    public void shutdown() {
        executorService.shutdown();
        System.out.println("Thread pool shut down.");
    }
}
package Hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        MyServiceImpl service = null;
        try {
            // 1. create and export service
            service = new MyServiceImpl(); // 🛑 Now initializes the thread pool

            // 2. create registry on default port 1099 
            Registry registry = LocateRegistry.createRegistry(1079);

            // 3. bind service
            registry.rebind("MyService", service);

            System.out.println("RMI Server started and bound 'MyService' on port 1099.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        } 
        
        // Note: In Eclipse, simply stopping the process will usually shut down the JVM, 
        // but adding a shutdown hook is the proper way to ensure cleanup.
        if (service != null) {
            final MyServiceImpl finalService = service;
            Runtime.getRuntime().addShutdownHook(new Thread(finalService::shutdown));
        }
    }
}
package Hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        String serverHost = "localhost"; // change if server on other machine
        try {
            // 1. Get the RMI Registry reference
            Registry registry = LocateRegistry.getRegistry(serverHost, 1079);
            
            // 2. Attempt to look up the service using the WRONG name
            System.out.println("Client attempting to look up service 'BadName'...");
            MyService stub = (MyService) registry.lookup("BadName"); // 🛑 THIS WILL FAIL
            
            // 3. This line will not be reached due to the exception above
            String resp = stub.processRequest("Hello from client at " + System.currentTimeMillis());
            System.out.println("Client received: " + resp);

        } catch (Exception e) {
            // 4. The client catches the java.rmi.NotBoundException
            System.err.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
