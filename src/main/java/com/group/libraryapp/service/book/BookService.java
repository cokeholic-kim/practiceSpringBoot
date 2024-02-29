package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import com.group.libraryapp.repository.book.BookRepository;
import com.group.libraryapp.repository.user.UserLoanHistoryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BookService {

    private final BookRepository repository;
    private final UserLoanHistoryRepository loanRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository repository, UserLoanHistoryRepository loanRepository,
                       UserRepository userRepository) {
        this.repository = repository;
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        repository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        // 책 정보를 가져온다
        Book book = repository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        // 대출 기록정보를 확인해서 대출중인지 확인한다.
        if (loanRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            // 대출중이면 예외를 발생시킨다.
            throw new IllegalArgumentException("대출되어있는 책입니다.");
        }

        // 유저 정보를 가져온다
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.loanBook(book.getName());
//        // 유저 정보와 책 정보를 기반으로 UserLoanHistory를 저장
//        loanRepository.save(new UserLoanHistory(user, book.getName()));
    }
    @Transactional
    public void returnBook(BookReturnRequest request) {
        // 유저 정보를 가져온다
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.returnBook(request.getBookName());

//        UserLoanHistory result = loanRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
//                .orElseThrow(IllegalArgumentException::new);
//        result.doReturn();
        //영속성컨텍스트에 의해서 객체가 변경되면서 변경된객체를 자동으로 업데이트 해준다.
    }
}
