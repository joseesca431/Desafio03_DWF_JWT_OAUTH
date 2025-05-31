package sv.edu.udb.service;

import sv.edu.udb.controller.request.ContentRequest;
import sv.edu.udb.controller.response.ContentResponse;

import java.util.List;

public interface ContentService {

    ContentResponse createContent(ContentRequest contentRequest);

    List<ContentResponse> getAllContents(final String title, final String type);
}
