package it.unimi.di.vec20.pactexample.consumer;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import java.util.*;
import org.json.JSONObject;

public class Client {
  private final String url;

  public Client(String url) {
    this.url = url;
  }

  private Optional<JsonNode> loadProviderJson(int month, int day) throws UnirestException {

    HashMap<String, Object> queryS = new HashMap<>();
    queryS.put("month", month);
    queryS.put("day", day);

    HttpRequest getRequest = Unirest.get(url + "/provider.json");

    getRequest = getRequest.queryString(queryS);

    HttpResponse<JsonNode> jsonNodeHttpResponse = getRequest.asJson();
    if (jsonNodeHttpResponse.getStatus() == 200) {
      return Optional.of(jsonNodeHttpResponse.getBody());
    } else {
      return Optional.empty();
    }
  }

  public List<ConsumerEmployee> fetchAndProcessData(int month, int day) throws UnirestException {
    Optional<JsonNode> data = loadProviderJson(month, day);
    System.out.println("data=" + data);

    if (data != null && data.isPresent()) {
      JSONObject jsonObject = data.get().getObject();
      System.out.println(jsonObject.toString());
      String name = jsonObject.getString("name");
      String email = jsonObject.getString("emailaddress");
      String surname = jsonObject.getString("surname");

      return Arrays.asList(new ConsumerEmployee(surname,name,email));
    } else {
      return null;
    }
  }

  public int getTest() throws UnirestException {
    HttpRequest getRequest = Unirest.get(url + "/provider.json");
    HttpResponse<JsonNode> jsonNodeHttpResponse = getRequest.asJson();
    return jsonNodeHttpResponse.getStatus();
  }
}
