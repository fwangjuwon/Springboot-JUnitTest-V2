package site.metacoding.blogv4junit.domain.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode //주소 비교 안함. 값만 비교한다. 
@NoArgsConstructor
@Getter
@Entity
public class Book {
    
    // 래핑타입을 쓰는게 좋다. (wrapping)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String title;

    private String author;

    @Builder //생성자의 단점: 순서에 맞게 넣어야한다. builder쓰면 순서 안맞아도 된다.
    public Book(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

}
