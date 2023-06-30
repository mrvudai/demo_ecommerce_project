package daipv.service.Iservice;

import daipv.DTO.request.ChangeRoleDTO;
import daipv.DTO.response.ResponseMessage;
import daipv.model.Users;

public interface IUserService extends BaseService<Users, Long>{
    Users findByUserName(String username);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);

    ResponseMessage setRole(ChangeRoleDTO changeRoleDTO);

}
