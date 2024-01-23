package com.idfc.bootcamp.bookstore.util;

import com.idfc.bootcamp.bookstore.dto.PagedResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MapperUtility {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <S, T> List<T> mapList(Collection<S> source, Class<T> destinationType) {
        return source.stream()
                .map(item -> modelMapper.map(item, destinationType))
                .collect(Collectors.toList());
    }

    public static <S, T> T convertClass(S source, Class<T> destinationType) {
        if (Objects.isNull(source)) {
            return null;
        }
        return modelMapper.map(source, destinationType);
    }

    public static <S, T> PagedResponse<T> convertPageToList(Page<S> sourcePage, Class<T> destinationType) {
        if (Objects.isNull(sourcePage)) {
            return null;
        }
        List<T> content = sourcePage.getContent().stream()
                .map(item -> modelMapper.map(item, destinationType))
                .collect(Collectors.toList());

        PagedResponse<T> pagedResponse = new PagedResponse<>();
        pagedResponse.setContent(content);
        pagedResponse.setCurrentPage(sourcePage.getNumber() + 1);
        pagedResponse.setOffset((int) sourcePage.getSize());
        pagedResponse.setCount((int) sourcePage.getNumberOfElements());
        pagedResponse.setTotalElements(sourcePage.getTotalElements());
        pagedResponse.setTotalPages(sourcePage.getTotalPages());

        return pagedResponse;
    }
}