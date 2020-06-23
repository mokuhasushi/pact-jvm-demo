package it.unimi.di.vec20.pactexample.provider;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Path("/provider.json")
@Produces(MediaType.APPLICATION_JSON)
public class RootResource {

    @GET
    public Map<String, Serializable> providerJson(@QueryParam("month") Optional<Integer> month, @QueryParam("day") Optional<Integer> day) {

        if (day.isPresent() && month.isPresent()) {
            if (EmployeeRepositoryDataStore.INSTANCE.findEmployeesBornOn(month.get(), day.get()).size() > 0) {
                    Map<String, Serializable> result = new HashMap<>(4);
                    for (Employee e : EmployeeRepositoryDataStore.INSTANCE.findEmployeesBornOn(month.get(), day.get())) {
                    result.put("name", e.getName());
                    result.put("emailaddress", e.getEmailAddress());}
                    return result; //TODO Check valid date?
            } else {
                throw new NoDataException();
                //TODO discutibile
            }
        } else {
            throw new QueryParameterRequiredException("valid month and day are required");
        }
    }

/* TODO?
    private boolean dayMonthAreValid(Optional<Integer> day, Optional<Integer> month) {
    }
*/
}
