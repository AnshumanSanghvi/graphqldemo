package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.entity.Actor;
import com.anshuman.graphqldemo.model.entity.view.ActorInfo;
import com.anshuman.graphqldemo.model.mapper.ActorInfoMapper;
import com.anshuman.graphqldemo.model.mapper.ActorMapper;
import com.anshuman.graphqldemo.model.repository.ActorRepository;
import com.anshuman.graphqldemo.model.repository.view.ActorInfoRepository;
import com.anshuman.graphqldemo.resource.dto.ActorInfoRecord;
import com.anshuman.graphqldemo.resource.dto.ActorRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
public class ActorService {

    private final ActorInfoRepository actorInfoRepository;
    private final ActorInfoMapper actorInfoMapper;
    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    @Cacheable(value = "actorInfos", key = "#name")
    public List<ActorInfoRecord> getActorInfoByName(int pageNumber, int pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<ActorInfo> actorInfoList =
                actorInfoRepository.findDistinctByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(pageable, name,
                name)
                .getContent();
        return actorInfoMapper.toDtoList(actorInfoList);
    }

    @Cacheable(value = "actors", key = "#actorId")
    public ActorRecord getByActorId(int actorId) {
        return actorRepository.findById(actorId)
                .map(actorMapper::toDto)
                .orElse(null);
    }

    @Async("APIThreadExecutor")
    public CompletableFuture<Set<Actor>> getActorsByActorId(Set<Short> actorIds) {
        return actorRepository.findDistinctActorByIdIn(actorIds);
    }
}
