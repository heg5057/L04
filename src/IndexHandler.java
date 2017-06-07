
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

public class IndexHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        System.out.println(he.getRemoteAddress());
        String response = getResponse();
        he.sendResponseHeaders(200, response.length());
        OutputStream out = he.getResponseBody();
        out.write(response.getBytes());
        out.close();
    }
    
    public String getResponse() {
        StringBuilder responseBuffer = new StringBuilder();
        responseBuffer
            .append(
                "<html><h1>TEST SERVER</h1><br>");
        return responseBuffer.toString();  
    }   
}
