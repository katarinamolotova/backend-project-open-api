package edu.school21.backend.utils;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MappingUtil {

    private final ModelMapper modelMapper;

    public <S, T> List<T> mapList(final List<S> source, final Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .toList();
    }

    public <S, T> T map(final S source, final Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}
