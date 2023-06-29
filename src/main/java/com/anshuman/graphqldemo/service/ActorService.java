package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.entity.view.ActorInfo;
import com.anshuman.graphqldemo.model.mapper.ActorInfoMapper;
import com.anshuman.graphqldemo.model.repository.ActorRepository;
import com.anshuman.graphqldemo.model.repository.view.ActorInfoRepository;
import com.anshuman.graphqldemo.resource.dto.ActorInfoRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActorService {

    private final ActorInfoRepository actorInfoRepository;
    private final ActorRepository actorRepository;
    private final ActorInfoMapper actorInfoMapper;

    public List<ActorInfoRecord> getActorInfoByName(int pageNumber, int pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<ActorInfo> actorInfoList =
                actorInfoRepository.findDistinctByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(pageable, name,
                name)
                .getContent();
        return actorInfoMapper.toDtoList(actorInfoList);
    }
}
