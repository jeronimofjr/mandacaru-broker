// package com.mandacarubroker.service.;

import com.mandacarubroker.service.StockService;
import com.mandacarubroker.domain.stock.RequestStockDto;
import com.mandacarubroker.domain.stock.Stock;
import com.mandacarubroker.domain.stock.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;


    @Test
    void getAllStocksTest() {
        Stock stock1 = new Stock();
        Stock stock2 = new Stock();
        when(stockRepository.findAll()).thenReturn(Arrays.asList(stock1, stock2));

        List<Stock> stocks = stockService.getAllStocks();

        assertNotNull(stocks);
        assertEquals(3, stocks.size());
        verify(stockRepository, times(1)).findAll();
    }

    @Test
    void getStockByIdTestWithValidId() {
        String id = "1";
        Stock stock = new Stock();
        when(stockRepository.findById(id)).thenReturn(Optional.of(stock));

        Optional<Stock> response = stockService.getStockById(id);

        assertTrue(response.isPresent());
        assertEquals(stock, response.get());
        verify(stockRepository, times(1)).findById(id);
    }

    @Test
    void getStockByIdTestWithInvalidId() {
        String id = "1a";
        when(stockRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Stock> response = stockService.getStockById(id);

        assertFalse(response.isPresent());
        verify(stockRepository, times(1)).findById(id);
    }


    @Test
    void updateStockTestWithValidIdAndData() {
        String validId = "1";
        Stock existingStock = new Stock(); 
        Stock updatedStock = new Stock();
        when(stockRepository.findById(validId)).thenReturn(Optional.of(existingStock));
        when(stockRepository.save(any(Stock.class))).thenReturn(updatedStock);

        Optional<Stock> result = stockService.updateStock(validId, updatedStock);

        assertTrue(result.isPresent());
        assertEquals(updatedStock, result.get());
        verify(stockRepository, times(1)).findById(validId);
        verify(stockRepository, times(1)).save(any(Stock.class));
    }
    
    @Test
    void deleteStockTest() {
        String id = "1";

        stockService.deleteStock(id);

        verify(stockRepository, times(1)).deleteById(id);
    }

}