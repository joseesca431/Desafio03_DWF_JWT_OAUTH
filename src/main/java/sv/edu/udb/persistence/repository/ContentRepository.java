package sv.edu.udb.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sv.edu.udb.persistence.domain.Content;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    boolean existsByTitle(String title);

    @Query("""
        SELECT c FROM Content c
        WHERE (:title IS NULL OR LOWER(c.title) LIKE LOWER(CONCAT('%', :title, '%')))
        AND (:type IS NULL OR c.type = :type)
    """)
    List<Content> findByFilters(
            @Param("title") String title,
            @Param("type") String type
    );
}
