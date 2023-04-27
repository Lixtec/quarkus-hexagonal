package fr.lixtec.hexagonal.account.bootstrap;

import fr.lixtec.hexagonal.account.application.port.driven.CreateAccountUseCase;
import fr.lixtec.hexagonal.account.application.port.driven.DepositMoneyUseCase;
import fr.lixtec.hexagonal.account.application.port.driven.TransferMoneyUseCase;
import fr.lixtec.hexagonal.account.application.port.driven.WithdrawMoneyUseCase;
import fr.lixtec.hexagonal.account.application.port.driving.LoadAccountPort;
import fr.lixtec.hexagonal.account.application.port.driving.ManageAccountPort;
import fr.lixtec.hexagonal.account.application.service.AccountServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@ApplicationScoped
public class CdiProducer
{
    @Inject AccountServiceImpl accountService; 
    
    
    
    @Produces @Named("CreateAccountUseCase") CreateAccountUseCase getCreateAccountUseCase() {
        return accountService;
    }
    
    
    
    @Produces @Named("DepositMoneyUseCase") DepositMoneyUseCase getDepositMoneyUseCase() {
        return accountService;
    }
    
    
    
    @Produces @Named("TransferMoneyUseCase") TransferMoneyUseCase getTransferMoneyUseCase() {
        return accountService;
    }
    
    
    
    @Produces @Named("WithdrawMoneyUseCase") WithdrawMoneyUseCase getWithdrawMoneyUseCase() {
        return accountService;
    }
    
    
    
    @Produces @Named("LoadAccountPort") LoadAccountPort getLoadAccountPort() {
        return accountService;
    }
    
    
    
    @Produces @Named("ManageAccountPort") ManageAccountPort getManageAccountPort() {
        return accountService;
    }
}
