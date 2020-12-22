package curso.springframework.mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @ApiModelProperty(value="this is the first name javier",required = false)
    private String firstName;

    @ApiModelProperty(required = true)
    private String lastName;


    @JsonProperty("customer_url")
    private String url;
}
