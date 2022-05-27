package site.metacoding.blogv4junit.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRespository  extends JpaRepository<Book, Long>{
    
}
