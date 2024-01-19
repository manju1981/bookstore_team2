package com.idfc.bootcamp.bookstore.util;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
public class MapperUtility {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <S, T> List<T> mapList(Collection<S> source, Class<T> destinationType) {
        return source.stream()
                .map(item -> modelMapper.map(item, destinationType))
                .collect(Collectors.toList());
    }

    public static <S, T> T convertClass(S source, Class<T> destinationType) {
        if (source == null) {
            return null;
        }
        return modelMapper.map(source, destinationType);
    }

    public static <S, T> List<T> convertPageToList(Page<S> sourcePage, Class<T> destinationType) {
        return sourcePage.getContent().stream()
                .map(item -> modelMapper.map(item, destinationType))
                .collect(Collectors.toList());
    }
}