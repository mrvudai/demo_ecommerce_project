package daipv.DTO.request;

import lombok.Data;

@Data
public class ChangeRoleDTO {
    private Long idUser;

    private String roleName;
}
