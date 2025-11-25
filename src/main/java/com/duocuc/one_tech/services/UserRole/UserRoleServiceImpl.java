package com.duocuc.one_tech.services.UserRole;

import com.duocuc.one_tech.dto.userRole.UserRoleAssignmentDTO;
import com.duocuc.one_tech.exceptions.ResourceNotFoundException;
import com.duocuc.one_tech.models.Role;
import com.duocuc.one_tech.models.User;
import com.duocuc.one_tech.models.UserRole;
import com.duocuc.one_tech.models.UserRoleId;
import com.duocuc.one_tech.repositories.UserRoleRepository;
import com.duocuc.one_tech.services.Role.RoleService;
import com.duocuc.one_tech.services.User.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserService userService;
    private final RoleService roleService;

    // Mapper a DTO
    private UserRoleAssignmentDTO toDTO(UserRole userRole) {
        return new UserRoleAssignmentDTO(
                userRole.getUser().getIdUsers(),
                userRole.getRole().getIdRole()
        );
    }

    @Override
    @Transactional
    public UserRoleAssignmentDTO assignRoleToUser(UserRoleAssignmentDTO dto) {
        // Obtener usuario
        User user = userService.findById(dto.getIdUser())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado con ID: " + dto.getIdUser()
                ));

        // Obtener rol
        Role role = roleService.findById(dto.getIdRole());
        if (role == null) {
            throw new ResourceNotFoundException(
                    "Rol no encontrado con ID: " + dto.getIdRole()
            );
        }

        // Crear ID compuesto
        UserRoleId id = new UserRoleId(user.getIdUsers(), role.getIdRole());

        // Evitar duplicados
        if (userRoleRepository.existsById(id)) {
            throw new RuntimeException("El usuario ya tiene asignado este rol.");
        }

        // Crear y guardar asignación
        UserRole userRole = new UserRole();
        userRole.setRoleId(id);
        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setGrantedAt(OffsetDateTime.now());

        UserRole savedUserRole = userRoleRepository.save(userRole);
        return toDTO(savedUserRole);
    }

    @Override
    public List<UserRoleAssignmentDTO> findAllAssignments() {
        return userRoleRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserRoleAssignmentDTO> findAssignmentByIds(Long idUser, Long idRole) {
        UserRoleId id = new UserRoleId(idUser, idRole);
        return userRoleRepository.findById(id).map(this::toDTO);
    }

    @Override
    @Transactional
    public UserRoleAssignmentDTO updateGrantedAt(Long idUser, Long idRole, OffsetDateTime newDate) {
        UserRoleId id = new UserRoleId(idUser, idRole);

        UserRole userRole = userRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Asignación de rol no encontrada con User ID: " + idUser + " y Role ID: " + idRole
                ));

        assert userRole != null;
        userRole.setGrantedAt(newDate);
        return toDTO(userRoleRepository.save(userRole));
    }

    @Override
    @Transactional
    public void deleteAssignment(Long idUser, Long idRole) {
        UserRoleId id = new UserRoleId(idUser, idRole);

        if (!userRoleRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Asignación de rol no encontrada con User ID: " + idUser + " y Role ID: " + idRole
            );
        }

        userRoleRepository.deleteById(id);
    }
}
