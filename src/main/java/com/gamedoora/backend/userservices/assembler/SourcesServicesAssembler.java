package com.gamedoora.backend.userservices.assembler;

import com.gamedoora.backend.userservices.dto.SourceDTO;
import com.gamedoora.backend.userservices.repository.SourcesRepository;
import com.gamedoora.model.dao.Sources;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SourcesServicesAssembler {

  @Autowired private SourcesRepository sourcesRepository;

  public SourceDTO createSources(SourceDTO sourcesDto) {

    Sources sources = new Sources();
    sources.setName(sourcesDto.getName());
    sources.setCreatedBy("GameDoora");
    sources.setUpdateBy("GameDoora");
    sources.setCreatedOn(new Date());
    sources.setUpdateOn(new Date());

    sourcesRepository.save(sources);
    return sourcesDto;
  }

  public SourceDTO updateSources(long id, SourceDTO sourcesDto) {

    Optional<Sources> sourcesRes = sourcesRepository.findById(id);
    if (!sourcesRes.isPresent()) {
      return null;
    }
    Sources sources = sourcesRes.get();
    sources.setName(sourcesDto.getName());

    sourcesRepository.save(sources);
    return sourcesDto;
  }

  public void deleteSources(long id) {
    sourcesRepository.deleteById(id);
  }

  public void deleteAllSources() {
    sourcesRepository.deleteAll();
  }

  public List<Sources> getAllSources(String name) {
    List<Sources> sources = new ArrayList<>();
    if (name == null) {
      sourcesRepository.findAll().forEach(sources::add);
      return sources;
    }
    sourcesRepository.findByNameContaining(name).forEach(sources::add);
    return sources;
  }
}
