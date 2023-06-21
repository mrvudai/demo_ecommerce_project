package daipv.service.Iservice;

import daipv.model.RoleName;
import daipv.model.Roles;

public interface IRoleService extends BaseService<Roles, Long> {
    Roles findByRoleName(RoleName roleName);

}
