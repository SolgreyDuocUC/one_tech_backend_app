package com.duocuc.one_tech.services.Communes;

import com.duocuc.one_tech.dto.communes.CommunesDTO;
import com.duocuc.one_tech.dto.communes.CreateCommunesDTO;
import com.duocuc.one_tech.exceptions.NotFoundException;
import com.duocuc.one_tech.models.Communes;
import com.duocuc.one_tech.models.Region;
import com.duocuc.one_tech.repositories.CommunesRepository;
import com.duocuc.one_tech.repositories.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommunesServiceImpl implements CommunesService {

    private final CommunesRepository communesRepository;
    private final RegionRepository regionRepository;

    private CommunesDTO toDTO(Communes entity) {
        return new CommunesDTO(
                entity.getId(),
                entity.getName(),
                entity.getRegion().getIdRegion()
        );
    }

    @Override
    public CommunesDTO findById(Long id) {
        Communes commune = communesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comuna no encontrada: " + id));
        assert commune != null;
        return toDTO(commune);
    }

    @Override
    public List<CommunesDTO> findAll() {
        return communesRepository.findAll()
                .stream().filter(Objects::nonNull)
                .map(this::toDTO)
                .toList();
    }

    @Override
    public CommunesDTO create(CreateCommunesDTO dto) {
        Region region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(() -> new NotFoundException("Región no encontrada: " + dto.getRegionId()));

        Communes commune = new Communes();
        commune.setName(dto.getName());
        commune.setRegion(region);

        return toDTO(communesRepository.save(commune));
    }

    @Override
    public CommunesDTO update(Long id, CreateCommunesDTO dto) {
        Communes commune = communesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comuna no encontrada: " + id));

        Region region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(() -> new NotFoundException("Región no encontrada: " + dto.getRegionId()));

        assert commune != null;
        commune.setName(dto.getName());
        commune.setRegion(region);

        return toDTO(communesRepository.save(commune));
    }

    @Override
    public void delete(Long id) {
        Communes commune = communesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comuna no encontrada: " + id));

        assert commune != null;
        communesRepository.delete(commune);
    }
}
