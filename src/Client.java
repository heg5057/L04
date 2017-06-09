
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) throws Exception {
        Client http = new Client();
        http.sendGet("https://www.apple.com");
        http.sendPost("https://www.apple.com");
    }
    
    private void sendGet(String query) throws Exception {
        System.out.println("Get Request");
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
    private void sendPost(String query) throws Exception{
        String userInput;
        System.out.println("Post Request");
        URL url = new URL(query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        
        FileWriter fw = new FileWriter("diary.txt", true);
        BufferedWriter buffWriter = new BufferedWriter(fw);
        Scanner scnr = new Scanner(System.in);
            
        System.out.print("Post parameters: ");
        userInput = scnr.nextLine();
        buffWriter.write(userInput);
        buffWriter.close();
        fw.close();
          
        String urlParameters = userInput;
        connection.setDoOutput(true);
        DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
        writer.writeBytes(urlParameters);
        writer.flush();
        writer.close();
        
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        if (responseCode == 200) {
            String response = getResponse(connection);
            System.out.println("Response: " + response);
        } else {
            System.out.println("Bad Response Code: " + responseCode);
        }
        
        
        
        
    }
}
