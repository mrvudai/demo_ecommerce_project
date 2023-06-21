package daipv.service.serviceIMPL;

import daipv.model.Users;
import daipv.repository.IUserRepo;
import daipv.service.Iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepo repo;
    @Override
    public Page<Users> findAll(Long aLong, Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public boolean save(Users o) {
        try {
            repo.save(o);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            repo.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Users findById(Long id) {
        try {
            return repo.findById(id).get();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Users findByUserName(String username) {
        return repo.findByUserName(username);
    }

    @Override
    public boolean existsByUserName(String username) {
        return repo.existsByUserName(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }
}
