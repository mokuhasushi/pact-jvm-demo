package it.unimi.di.vec20.pactexample.provider;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/provider.json")
@Produces(MediaType.APPLICATION_JSON)
public class RootResource {

  @GET
  public Map<String, Serializable> providerJson(
      @QueryParam("month") Optional<Integer> month, @QueryParam("day") Optional<Integer> day) {

    if (day.isPresent() && month.isPresent()) {
      if (EmployeeRepositoryDataStore.INSTANCE.findEmployeesBornOn(month.get(), day.get()).size()
          > 0) {
        Map<String, Serializable> result = new HashMap<>(4);
        for (Employee e :
            EmployeeRepositoryDataStore.INSTANCE.findEmployeesBornOn(month.get(), day.get())) {
          result.put("name", e.getName());
          result.put("surname", e.getSurname());
          result.put("emailaddress", e.getEmailAddress());
        }
        return result;
      } else {
        throw new NoDataException();
      }
    } else {
      throw new QueryParameterRequiredException("valid month and day are required");
    }
  }
}
