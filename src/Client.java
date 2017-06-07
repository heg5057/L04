
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {
    
    public static void main(String[] args) throws Exception {
        Client http = new Client();
        http.sendGet("https://www.google.com/");
    }
    
    private void sendGet(String query) throws Exception {
        URL url = new URL(query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        if (responseCode == 200) {
            String response = getResponse(connection);
            System.out.println("Response: " + response);
        } else {
            System.out.println("Bad Response Code: " + responseCode);
        }
    }
    
    private String getResponse(HttpURLConnection connect) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connect.getInputStream()));) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
