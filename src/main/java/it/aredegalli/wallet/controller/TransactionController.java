package it.aredegalli.wallet.controller;

import it.aredegalli.wallet.dto.transaction.TransactionDto;
import it.aredegalli.wallet.dto.transaction.TransactionTypeDto;
import it.aredegalli.wallet.dto.transaction.filter.TransactionFilterDto;
import it.aredegalli.wallet.service.transaction.TransactionService;
import it.aredegalli.wallet.util.PaginationUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("types")
    public List<TransactionTypeDto> getAllTransactionTypes() {
        return this.transactionService.getAllTransactionTypes();
    }

    @GetMapping("user")
    public Page<TransactionDto> getUserTransactionsFiltered(@RequestParam() @NotNull UUID userId,
                                                            @ModelAttribute @Valid TransactionFilterDto filter,
                                                            @RequestParam(required = false, defaultValue = "0") Integer page,
                                                            @RequestParam(required = false, defaultValue = "10") Integer size,
                                                            @RequestParam(required = false) String sort,
                                                            @RequestParam(required = false, defaultValue = "asc") String direction) {
        return this.transactionService.getUserTransactionsFiltered(userId, filter, PaginationUtils.of(page, size, sort, direction));
    }

    @GetMapping("portfolio")
    public Page<TransactionDto> getPortfolioTransactions(@RequestParam() @NotNull UUID portfolioId,
                                                         @RequestParam(required = false, defaultValue = "0") Integer page,
                                                         @RequestParam(required = false, defaultValue = "10") Integer size,
                                                         @RequestParam(required = false) String sort,
                                                         @RequestParam(required = false, defaultValue = "asc") String direction) {
        return this.transactionService.getPortfolioTransactions(portfolioId, PaginationUtils.of(page, size, sort, direction));
    }

    @PatchMapping()
    public UUID saveTransaction(@RequestBody TransactionDto save) {
        return this.transactionService.saveTransaction(save);
    }

    @DeleteMapping()
    public UUID deleteTransaction(@RequestParam() @NotNull UUID id) {
        return this.transactionService.deleteTransaction(id);
    }

}
