import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.util.Map;
import java.util.ArrayList;
import java.io.*;
import java.lang.reflect.Array;

public class obstacleAPI {

  private static final OkHttpClient client = new OkHttpClient().newBuilder().build();

  public ArrayList<Obstacle> getObstacles() {
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    try {
      MediaType mediaType = MediaType.parse("text/plain");
      RequestBody body = RequestBody.create(mediaType, "");
      Request request = new Request.Builder().url("http://127.0.0.1:5000/obstacle").method("GET", null).build();
      Response response = client.newCall(request).execute();
      if (response.isSuccessful()) {
        String responseBody = response.body().string();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(responseBody);
        for (JsonNode node : rootNode) {
          double id = node.get("id").asDouble();
          double x = node.get("xPos").asDouble();
          double y = node.get("yPos").asDouble();
          double radius = node.get("radius").asDouble();
          obstacles.add(new Obstacle(id, x, y, radius));
        }
          System.out.println("Obstacles fetched successfully.");
      } else {
        System.out.println("Failed to fetch obstacles: " + response.message());
      }
      response.close();
    } catch (Exception ex) {
      System.out.println("Error fetching obstacles: " + ex.getMessage());
  }
  return obstacles;
  }

  public void removeObstacleAPI(double id) throws IOException {
    try {
      String url = "http://127.0.0.1:5000/obstacle/" + id;
      MediaType mediaType = MediaType.parse("text/plain");
      RequestBody body = RequestBody.create(mediaType, "");
      Request request = new Request.Builder().url(url).method("POST", body).build();
      Response response = client.newCall(request).execute();
      System.out.println(response.body().string());
      response.close();
    }
    catch(Exception ex) {
      System.out.println("There was an error.");
    }
  }

  public void addObstacleAPI(double id, double xPos, double yPos, double radius) throws IOException {
    try {
      MediaType mediaType = MediaType.parse("application/json");
      RequestBody body = RequestBody.create(mediaType, "{\r\n    \"id\": " + id + ",\r\n    \"radius\": " + radius +",\r\n    \"xPos\": " + xPos + ",\r\n    \"yPos\": " + yPos + "\r\n}");
      Request request = new Request.Builder().url("http://127.0.0.1:5000/obstacle").method("POST", body).addHeader("Content-Type", "application/json").build();
      Response response = client.newCall(request).execute();
      response.close();
    }
    catch (Exception ex) {
      System.out.println("There was an error.");
    }
  }
}