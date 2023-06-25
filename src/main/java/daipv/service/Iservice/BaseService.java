package daipv.service.Iservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<O, T> {
    Page<O> findAll(Pageable pageable);

    boolean save(O o);

    boolean deleteById(T t);

    O findById(T t);
}
