
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class DetailHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        Headers requestHeaders = he.getRequestHeaders();
        Set<String> keySet = requestHeaders.keySet();
        for (String key : keySet) {
            List values = requestHeaders.get(key);
            String header = key + " = " + values.toString() + "\n";
            System.out.println(header);
        }
    }
    
}
