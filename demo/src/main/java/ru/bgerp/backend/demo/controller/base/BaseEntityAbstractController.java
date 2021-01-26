package ru.bgerp.backend.demo.controller.base;



import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.bgerp.backend.demo.entity.base.BaseEntity;
import ru.bgerp.backend.demo.service.base.BaseEntityService;

import javax.transaction.Transactional;
import java.util.List;

@Log4j2
@Transactional
public abstract class BaseEntityAbstractController<
        T extends BaseEntity, S extends BaseEntityService<T>> implements BaseEntityController<T> {

    protected final S baseEntityService;

    protected BaseEntityAbstractController(S s, ModelMapper modelMapper) {
        this.baseEntityService = s;
        this.modelMapper = modelMapper;
    }

    protected ModelMapper modelMapper;

    @Override
    public ResponseEntity<T> get(T entity) {
        return new ResponseEntity<>(entity, entity != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Page<T>> getAll(Pageable pageable) {
        Page<T> page = baseEntityService.findPagingRecords(pageable);
        return new ResponseEntity<>(page, page != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<T>> getAll() {
        List<T> ts = baseEntityService.findAll();
        return new ResponseEntity<>(ts, ts != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<T> create(T entity) {
        T saved = baseEntityService.create(entity);
        return new ResponseEntity<>(saved, saved != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<T> update(T entity) {
        T oldEntity = baseEntityService.findById(entity.getId());
        T saved = baseEntityService.update(entity);
        return new ResponseEntity<>(saved, saved != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Boolean> remove(T id) {
        boolean flag = baseEntityService.delete(id);
        return new ResponseEntity<>(flag ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
