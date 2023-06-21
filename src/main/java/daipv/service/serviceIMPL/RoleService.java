package daipv.service.serviceIMPL;

import daipv.model.RoleName;
import daipv.model.Roles;
import daipv.repository.IRoleRepo;
import daipv.service.Iservice.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    @Autowired
    IRoleRepo repo;
    @Override
    public Page<Roles> findAll(Long aLong, Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public boolean save(Roles roles) {
        try {
            repo.save(roles);
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
    public Roles findById(Long id) {
        try {
            return repo.findById(id).get();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Roles findByRoleName(RoleName roleName) {
        try {
            return repo.findByRoleName(roleName).get();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
