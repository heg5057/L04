
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class Server {

    public static void main(String[] args) throws Exception {
        System.out.println("HTTP Server Started");
        HttpServer server = HttpServer.create(
            new InetSocketAddress(80), 0);
        //server.createContext("/index", new IndexHandler());
        server.createContext("/index", new DetailHandler());
        server.start();
    }
}
