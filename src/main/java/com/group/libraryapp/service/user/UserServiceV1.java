package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepositorySQL;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceV1 {

    private final UserRepositorySQL userRepositorySQL;

    public UserServiceV1(UserRepositorySQL userRepositorySQL) {
        this.userRepositorySQL = userRepositorySQL;
    }

    public void saveUser(UserCreateRequest request) {
        userRepositorySQL.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers() {
        return userRepositorySQL.getUsers();
    }

    public void updateUser(UserUpdateRequest request) {
        if (userRepositorySQL.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException("수정할 자료가 없습니다.");
        }
        userRepositorySQL.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        if (userRepositorySQL.isUserNotExist(name)) {
            throw new IllegalArgumentException("삭제할 자료가 없습니다.");
        }
        userRepositorySQL.deleteUser(name);
    }

}
