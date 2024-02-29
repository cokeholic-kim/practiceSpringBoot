package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @Column(nullable = false,length = 20) // name varchar 20
    private String name;
    private Integer age;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();
    //지울수도 있음
//    @OneToMany(mappedBy = "user")
//    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    protected User(){}

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public User(String name, Integer age) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다",name));
        }
        this.name = name;
        this.age = age;
    }

    public void updateName(String name){
        this.name = name;
    }

    public void loanBook(String bookName){
        this.userLoanHistories.add(new UserLoanHistory(this,bookName));
    }

    public void returnBook(String bookName){
        UserLoanHistory target = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        target.doReturn();
    }
}
