package site.metacoding.blogv4junit.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv4junit.domain.book.Book;
import site.metacoding.blogv4junit.domain.book.BookRepository;
import site.metacoding.blogv4junit.web.dto.BookRespDto;
import site.metacoding.blogv4junit.web.dto.BookSaveReqDto;

@RequiredArgsConstructor
@Service
public class BookService {
 
    //di
    private final BookRepository bookRepository;

    //title, author 받아야한다. id를 받을 필요는 없다. dto를 만들어서 컨트롤러에서 받아야한다. 
    //위 두개를 갖고 있는게 BookSaveReqDto이다.
       @Transactional(rollbackFor = RuntimeException.class)
   public BookRespDto 책등록하기(BookSaveReqDto reqDto) {

       Book bookEntity = bookRepository.save(reqDto.toEntity());
       return new BookRespDto().toDto(bookEntity);
       }


       public BookRespDto 책한건가져오기(Long id) {
           Optional<Book> bookOp = bookRepository.findById(id);
       
           if (bookOp.isPresent()) {
               Book bookEntity = bookOp.get();
               return new BookRespDto().toDto(bookEntity);
           } else {
               throw new RuntimeException("해당 책을 갖고 올 수 없다");
           }
    }
}
