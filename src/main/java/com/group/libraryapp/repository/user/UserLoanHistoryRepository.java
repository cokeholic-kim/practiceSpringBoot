package com.group.libraryapp.repository.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory,Long> {
    //
    boolean existsByBookNameAndIsReturn(String name, boolean isReturn);
    Optional<UserLoanHistory> findByUserIdAndBookName(long userId,String bookName);
}
