package com.hsleiden.iprwc.api.service;

import com.hsleiden.iprwc.api.model.Item;
import com.hsleiden.iprwc.api.repo.ItemRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ItemServiceImplementation implements ItemService {
    private final ItemRepo itemRepo;

    @Override
    public Item create(Item item) {
        return this.itemRepo.save(item);
    }
}
