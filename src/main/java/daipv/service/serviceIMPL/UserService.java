package daipv.service.serviceIMPL;

import daipv.DTO.request.ChangeRoleDTO;
import daipv.DTO.response.ResponseMessage;
import daipv.model.RoleName;
import daipv.model.Roles;
import daipv.model.Users;
import daipv.repository.IRoleRepo;
import daipv.repository.IUserRepo;
import daipv.service.Iservice.IRoleService;
import daipv.service.Iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepo repo;

    @Autowired
    IRoleService roleService;

    @Override
    public Page<Users> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public boolean save(Users o) {
        try {
            repo.save(o);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            repo.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Users findById(Long id) {
        try {
            return repo.findById(id).get();
        } catch (Exception e) {
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

    @Override
    public ResponseMessage setRole(ChangeRoleDTO changeRoleDTO) {
        try {
            Users users = findById(changeRoleDTO.getIdUser());
            if (users == null) {
                return new ResponseMessage("user not found");
            } else if (users.getRoles().contains(roleService.findByRoleName(RoleName.ADMIN))) {
                return new ResponseMessage("cannot change the role of administrator");
            } else  {
                Set<Roles> rolesSet = new HashSet<>();
                switch (changeRoleDTO.getRoleName()) {
                    case "admin":
                        rolesSet.add(roleService.findByRoleName(RoleName.ADMIN));
                    case "pm":
                        rolesSet.add(roleService.findByRoleName(RoleName.PM));
                    case "user":
                        rolesSet.add(roleService.findByRoleName(RoleName.USER));
                        break;
                    default:
                        rolesSet.add(roleService.findByRoleName(RoleName.USER));
                }
                users.setRoles(rolesSet);
                repo.save(users);
                return new ResponseMessage("changed role success");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("encountered some errors during role change");
        }
    }
}
