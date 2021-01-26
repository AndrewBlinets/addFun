package ru.bgerp.backend.demo.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.bgerp.backend.demo.entity.base.BaseEntity;

import java.util.List;

public interface BaseEntityService<T extends BaseEntity> {

  Page<T> findPagingRecords(Pageable page);

  T findById(Long id);

  List<T> findAll();

  T create(T t);

  T update(T t);

  boolean delete(T t);
}
