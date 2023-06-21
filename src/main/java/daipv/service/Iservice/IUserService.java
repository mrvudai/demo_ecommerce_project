package daipv.service.Iservice;

import daipv.model.Users;

public interface IUserService extends BaseService<Users, Long>{
    Users findByUserName(String username);
    boolean existsByUserName(String username);
    boolean existsByEmail(String email);

}
