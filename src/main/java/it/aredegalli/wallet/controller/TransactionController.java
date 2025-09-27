package it.aredegalli.wallet.controller;

import it.aredegalli.wallet.dto.transaction.TransactionCategoryDto;
import it.aredegalli.wallet.dto.transaction.TransactionDto;
import it.aredegalli.wallet.dto.transaction.TransactionTypeDto;
import it.aredegalli.wallet.dto.transaction.filter.TransactionFilterDto;
import it.aredegalli.wallet.service.transaction.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("category")
    public List<TransactionCategoryDto> getAllTransactionCategories() {
        return this.transactionService.getAllTransactionCategories();
    }

    @GetMapping("user")
    public Page<TransactionDto> getUserTransactionsFiltered(@RequestParam() @NotNull UUID userId,
                                                            @ModelAttribute @Valid TransactionFilterDto filter,
                                                            @ModelAttribute Pageable pageable) {
        return this.transactionService.getUserTransactionsFiltered(userId, filter, pageable);
    }

    @GetMapping("portfolio")
    public Page<TransactionDto> getPortfolioTransactions(@RequestParam() @NotNull UUID portfolioId,
                                                         @ModelAttribute Pageable pageable) {
        return this.transactionService.getPortfolioTransactions(portfolioId, pageable);
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
