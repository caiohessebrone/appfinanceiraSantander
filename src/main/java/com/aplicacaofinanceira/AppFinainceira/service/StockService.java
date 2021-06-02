package com.aplicacaofinanceira.AppFinainceira.service;

import com.aplicacaofinanceira.AppFinainceira.exceptions.BusinessException;
import com.aplicacaofinanceira.AppFinainceira.exceptions.NotFoundExceptions;
import com.aplicacaofinanceira.AppFinainceira.mapper.StockMapper;
import com.aplicacaofinanceira.AppFinainceira.model.Stock;
import com.aplicacaofinanceira.AppFinainceira.model.dto.StockDTO;
import com.aplicacaofinanceira.AppFinainceira.repository.StockRepository;
import com.aplicacaofinanceira.AppFinainceira.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;

    @Transactional
    public StockDTO save(StockDTO dto) {

        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate());
        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_AREADY_EXISTS);
        }

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);
    }

    @Transactional
    public StockDTO update(StockDTO dto) {

        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());
        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_AREADY_EXISTS);
        }

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        List<Stock> list = repository.findAll();
        return mapper.toDto(list);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundExceptions::new);
    }

    @Transactional
    public StockDTO delete(Long id) {
        StockDTO dto = this.findById(id);
        repository.deleteById(dto.getId());
        return dto;
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<StockDTO> findByToDay() {
        return repository
                .findByToDay(LocalDate.now())
                .map(mapper::toDto)
                .orElseThrow(NotFoundExceptions::new);
    }
}
