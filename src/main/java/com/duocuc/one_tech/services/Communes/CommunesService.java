package com.duocuc.one_tech.services.Communes;


import com.duocuc.one_tech.dto.communes.CommunesDTO;
import com.duocuc.one_tech.dto.communes.CreateCommunesDTO;

import java.util.List;

public interface CommunesService {

    CommunesDTO findById(Long id);

    List<CommunesDTO> findAll();

    CommunesDTO create(CreateCommunesDTO dto);

    CommunesDTO update(Long id, CreateCommunesDTO dto);

    void delete(Long id);

}

