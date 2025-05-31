package sv.edu.udb.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sv.edu.udb.controller.request.ContentRequest;
import sv.edu.udb.controller.response.ContentResponse;
import sv.edu.udb.controller.response.UserSummaryResponse;
import sv.edu.udb.persistence.domain.Content;
import sv.edu.udb.persistence.domain.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContentMapper {

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    Content toContent(ContentRequest contentRequest);

    @Mapping(source = "createdBy", target = "createdBy")
    ContentResponse toContentResponse(Content content);

    List<ContentResponse> toContentResponseList(List<Content> contents);

    // Mapper para el usuario resumido
    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    UserSummaryResponse toUserSummary(User user);
}