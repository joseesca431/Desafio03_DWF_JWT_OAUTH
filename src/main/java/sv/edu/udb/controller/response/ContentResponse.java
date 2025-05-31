package sv.edu.udb.controller.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentResponse {
    private Long id;
    private String title;
    private String type;
    private String description;
    private LocalDate creationDate;

    private UserSummaryResponse createdBy;
}
