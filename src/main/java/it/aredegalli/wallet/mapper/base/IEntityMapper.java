package it.aredegalli.wallet.mapper.base;

import java.util.List;

public interface IEntityMapper<D, E> {

    E toEntity(D dto);

    default List<E> toEntity(List<D> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }
}
