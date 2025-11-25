package com.duocuc.one_tech.services.UserRole;

import com.duocuc.one_tech.dto.userRole.UserRoleAssignmentDTO;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRoleService {

    UserRoleAssignmentDTO assignRoleToUser(UserRoleAssignmentDTO dto);

    List<UserRoleAssignmentDTO> findAllAssignments();

    Optional<UserRoleAssignmentDTO> findAssignmentByIds(Long idUser, Long idRole);

    UserRoleAssignmentDTO updateGrantedAt(Long idUser, Long idRole, OffsetDateTime newDate);

    void deleteAssignment(Long idUser, Long idRole);
}
