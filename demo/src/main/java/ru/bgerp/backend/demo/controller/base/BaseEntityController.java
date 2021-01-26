package ru.bgerp.backend.demo.controller.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bgerp.backend.demo.entity.base.BaseEntity;

import java.util.List;

public interface BaseEntityController<T extends BaseEntity> {

  @GetMapping(value = "/{entity}")
  ResponseEntity<T> get(@PathVariable T entity);

  @GetMapping
  ResponseEntity<Page<T>> getAll(
      @PageableDefault()
              Pageable pageable);

  @GetMapping(value = "/all")
  @ResponseBody
  ResponseEntity<List<T>> getAll();

  @PostMapping
  ResponseEntity<T> create(@RequestBody T entity);

  @PutMapping
  ResponseEntity<T> update(@RequestBody T entity);

  @DeleteMapping(value = "/{id}")
  ResponseEntity<Boolean> remove(@PathVariable T id);
}
