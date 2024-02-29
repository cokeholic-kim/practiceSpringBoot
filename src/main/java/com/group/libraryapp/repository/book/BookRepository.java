package com.group.libraryapp.repository.book;

import com.group.libraryapp.domain.book.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByName(String name);
}
