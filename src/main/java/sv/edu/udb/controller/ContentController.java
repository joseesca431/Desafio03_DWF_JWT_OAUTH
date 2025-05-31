package sv.edu.udb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.controller.request.ContentRequest;
import sv.edu.udb.controller.response.ContentResponse;
import sv.edu.udb.service.ContentService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    @ResponseStatus(CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ContentResponse createContent(@Valid @RequestBody ContentRequest contentRequest) {
        return contentService.createContent(contentRequest);
    }

    @GetMapping
    public List<ContentResponse> getAllContents(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String type) {
            return contentService.getAllContents(title, type);
    }
}