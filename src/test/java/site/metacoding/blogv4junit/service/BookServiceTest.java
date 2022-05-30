package site.metacoding.blogv4junit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import site.metacoding.blogv4junit.domain.book.Book;
import site.metacoding.blogv4junit.domain.book.BookRepository;
import site.metacoding.blogv4junit.web.dto.BookRespDto;
import site.metacoding.blogv4junit.web.dto.BookSaveReqDto;

@ExtendWith(MockitoExtension.class) //스프링 컨테이너로 들어가는..
public class BookServiceTest {

    //서비스 레이어에서는 인젝트 모크를 해야한다. 
    @InjectMocks //다른 모키토로 뜬 애들을  띄워주는 애 
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void 책등록하기_테스트() {
        // given
        //매개변수에 주입되는게 given = dto
        BookSaveReqDto reqDto = new BookSaveReqDto();
        reqDto.setTitle("스프링");
        reqDto.setAuthor("황주원");

        //stub (행동정의 - 가설)
        Mockito.when(bookRepository.save(reqDto.toEntity())).thenReturn(new Book(1L, "스프링", "황주원"));

        //when 서비스테스트에는 실제 서비스 메소드를 때려야한다. 
        BookRespDto respDto = bookService.책등록하기(reqDto);

        // then
        assertEquals(1L, respDto.getId());
        assertEquals("스프링1강", respDto.getTitle());
        assertEquals("메타코딩", respDto.getAuthor());
    }
    
    
    @Test
    public void 책한건가져오기_테스트() {

        Optional<Book> bookOp = Optional.of(new Book(1L,
                "스프링1강", "메타코딩"));

        // given
        Long id = 1L;

        // stub (행동 정의 = 가설)
        Mockito.when(bookRepository.findById(id)).thenReturn(bookOp);

        // when
        BookRespDto respDto = bookService.책한건가져오기(id);

        // then
        assertEquals(1L, respDto.getId());
        assertEquals("스프링1강", respDto.getTitle());
        assertEquals("메타코딩", respDto.getAuthor());
    }

}
