package ukim.finki.mk.lab1.dto;



import ukim.finki.mk.lab1.model.domain.WishList;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public record WishListDto(
        Long id,
        UpdateUserDto user,
        List<UpdateBookDto> books


) {

    public static WishListDto from(WishList wishList) {
        return new WishListDto(
                wishList.getId(),
                UpdateUserDto.from(wishList.getUser()),
                 UpdateBookDto.fromBook(wishList.getBooks())

        );
    }

}

