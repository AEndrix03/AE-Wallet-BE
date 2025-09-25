package it.aredegalli.wallet.mapper.base;

import java.util.List;

public interface IDtoMapper<D, E> {

    D toDto(E entity);

    default List<D> toDto(List<E> entities) {
        return entities.stream().map(this::toDto).toList();
    }
}
