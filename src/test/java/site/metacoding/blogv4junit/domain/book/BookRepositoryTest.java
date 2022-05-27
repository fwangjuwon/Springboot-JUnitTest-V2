package site.metacoding.blogv4junit.domain.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest // db와 관련된 컴포넌트만 메모리에 로딩한다. //h2가 동작 //자동 롤백된다.
public class BookRepositoryTest {

    @Autowired
    private BookRespository bookRespository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void db_init() {
        bookRespository.deleteAll();
        em
            .createNativeQuery("ALTER TABLE book ALTER COLUMN id RESTART WITH 1") // auto increment 초기화
            .executeUpdate();
    }

    //무결성 제약조건은 컨트롤러에서 잡자
    @Test
    @Order(1)
    public void save_test() { // 괄호안에 뭐 넣으면 안돼

        //given 
        String title = "스프링부트";
        String author = "황주원";
        Book book = new Book(title, author);

        //when
        Book bookEntity = bookRespository.save(book);

        //verify
        assertEquals(title, bookEntity.getTitle());
        assertEquals(author, bookEntity.getAuthor());
        assertEquals(1, bookEntity.getId());
    }

    @Test
        @Order(2)
    public void findById_test() {

        //given
        String title = "스프링부트";
        String author = "황주원";
        Book book = new Book(title, author);
        bookRespository.save(book);

        Long id = 1L; //long 타입은 뒤에 l을 붙인다 

        //when
        Optional<Book> bookOp = bookRespository.findById(id);
        
        //then
       if (bookOp.isPresent()) {
           Book bookEntity = bookOp.get();
                   assertEquals(title, bookEntity.getTitle());
        assertEquals(author, bookEntity.getAuthor());
        assertEquals(1, bookEntity.getId());
        } else {
            assertNotNull(bookOp.get());  //널 아니지?? -> 널이야!!! false 뜬다.
        }
    }

}

