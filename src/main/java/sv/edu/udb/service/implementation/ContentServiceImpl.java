package sv.edu.udb.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sv.edu.udb.controller.request.ContentRequest;
import sv.edu.udb.controller.response.ContentResponse;
import sv.edu.udb.persistence.domain.Content;
import sv.edu.udb.persistence.domain.User;
import sv.edu.udb.persistence.repository.ContentRepository;
import sv.edu.udb.persistence.repository.UserRepository;
import sv.edu.udb.service.ContentService;
import sv.edu.udb.service.mapper.ContentMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;
    private final UserRepository userRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ContentResponse createContent(ContentRequest contentRequest) {
        // 1. Obtener usuario autenticado
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User creador = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (contentRepository.existsByTitle(contentRequest.getTitle())) {
            throw new IllegalArgumentException("El contenido con ese título ya existe");
        }

        // 2. Mapear request a entidad
        Content content = contentMapper.toContent(contentRequest);

        // 3. Asignar campos adicionales
        content.setCreatedBy(creador);
        content.setCreationDate(LocalDate.now());

        // 4. Guardar y retornar respuesta
        Content savedContent = contentRepository.save(content);
        return contentMapper.toContentResponse(savedContent);
    }

    @Override
    public List<ContentResponse> getAllContents(String title, String type) {
        List<Content> contents = contentRepository.findByFilters(title, type);
        return contentMapper.toContentResponseList(contents);
    }
}