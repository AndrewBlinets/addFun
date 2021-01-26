package ru.bgerp.backend.demo.service.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.bgerp.backend.demo.entity.base.BaseEntity;
import ru.bgerp.backend.demo.repository.BaseEntityRepository;

import java.util.List;

@Slf4j
public class BaseEntityServiceImpl<T extends BaseEntity, R extends BaseEntityRepository<T>>
    implements BaseEntityService<T> {

  protected final R repository;

  public BaseEntityServiceImpl(R repository) {
    this.repository = repository;
  }

  @Override
  public Page<T> findPagingRecords(Pageable page) {
    return repository.findAll(page);
  }

  @Override
  public T findById(Long id) {
    return repository.getOne(id);
  }

  @Override
  public List<T> findAll() {
    return repository.findAll();
  }

  @Override
  public T create(T t) {
    return repository.saveAndFlush(t);
  }

  @Override
  public T update(T t) {
    return repository.saveAndFlush(t);
  }

  @Override
  public boolean delete(T t) {
    try {
      repository.delete(t);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
