package com.mycard.auth.service.impl;

import com.mycard.auth.dto.UserDescriptionDTO;
import com.mycard.auth.entity.UserDescription;
import com.mycard.auth.repository.UserDescriptionRepository;
import com.mycard.auth.service.UserDescriptionService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = "UserDescriptionService")
public class UserDescriptionServiceImpl implements UserDescriptionService {

    private final ModelMapper modelMapper;
    private final UserDescriptionRepository userDescriptionRepository;

    public UserDescriptionServiceImpl(ModelMapper modelMapper, UserDescriptionRepository userDescriptionRepository) {
        this.modelMapper = modelMapper;
        this.userDescriptionRepository = userDescriptionRepository;
    }

    public Optional<UserDescription> findUserDescriptionById(Long id) {
        return userDescriptionRepository.findById(id);
    }

    public Optional<UserDescription> updateUserDescription(UserDescription userDescription) {
        final Optional<UserDescription> optionalUserDescription = findUserDescriptionById(userDescription.getId());

        if (optionalUserDescription.isEmpty()) {
            return optionalUserDescription;
        }

        final UserDescription userDescriptionFromDB = optionalUserDescription.get();
        userDescriptionFromDB.setFirstName(userDescription.getFirstName());
        userDescriptionFromDB.setLastName(userDescription.getLastName());
        userDescriptionFromDB.setCcInvoiceDt(userDescription.getCcInvoiceDt());
        userDescriptionFromDB.setAddressStreet(userDescription.getAddressStreet());
        userDescriptionFromDB.setAddressNumber(userDescription.getAddressNumber());
        userDescriptionFromDB.setAddressComplement(userDescription.getAddressComplement());

        return Optional.of(userDescriptionRepository.save(userDescriptionFromDB));
    }

    @Cacheable(key = "{#id}")
    @HystrixCommand(threadPoolKey = "findUserDescriptionDTOByIdThreadPool")
    public Optional<UserDescriptionDTO> findUserDescriptionDTOById(Long id) {
        return findUserDescriptionById(id)
                .map(this::transformUserDescriptionToUserDescriptionDTO);
    }

    @CachePut(key = "{#userDescriptionDTO.id}")
    @HystrixCommand(threadPoolKey = "findUserDescriptionDTOByIdThreadPool")
    public Optional<UserDescriptionDTO> updateUserDescriptionDTO(UserDescriptionDTO userDescriptionDTO) {
        return updateUserDescription(modelMapper.map(userDescriptionDTO, UserDescription.class))
                .map(this::transformUserDescriptionToUserDescriptionDTO);
    }

    private UserDescriptionDTO transformUserDescriptionToUserDescriptionDTO(UserDescription userDescription) {
        return modelMapper.map(userDescription, UserDescriptionDTO.class);
    }
}
