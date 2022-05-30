package site.metacoding.blogv4junit.web.dto;

import lombok.Getter;
import site.metacoding.blogv4junit.domain.book.Book;

@Getter //응답할 때는 getter필요하다. 
public class BookRespDto {
    private Long id;
    private String title;
    private String author;

    //엔티티를 dto로 변환 
    public BookRespDto toDto(Book bookEntity) {
        this.id = bookEntity.getId();
        this.title = bookEntity.getTitle();
        this.author = bookEntity.getAuthor();
        return this;
    }

    //만약 유저 있으면, user respdto따로 만들어서 따로 적어야한다. 
    //dto안에는 entity를 넣으면 안된다!! 
}
