package conta_bancaria.controller;

import java.util.ArrayList;
import java.util.List;

import conta_bancaria.model.Conta;
import conta_bancaria.repository.ContaRepository;

public class ContaController implements ContaRepository {

	private List<Conta> accountsList = new ArrayList<Conta>();
	int number = 0;

	@Override
	public void listAll() {
		for (var account : accountsList) {
			account.view();
		}

	}

	@Override
	public void register(Conta account) {
		accountsList.add(account);
		System.out.printf("A conta número %d foi criada com sucesso!%n", account.getNumber());

	}

	@Override
	public void searchByNumber(int number) {
		var account = searchInCollection(number);
		
		if(account != null) {
			account.view();
		} else {
			System.out.printf("\nA Conta número: %d não foi encontrada!%n", number);
		}
		

	}

	@Override
	public void edit(Conta account) {
		
		var searchAccount = searchInCollection(account.getNumber());
		
		if(searchAccount != null) {
			accountsList.set(accountsList.indexOf(searchAccount), account);
		} else {
			System.out.printf("A Conta número: %d não foi encontrada!%n", number);
		}

	}

	@Override
	public void delete(int number) {
		
		var account = searchInCollection(number);
		
		if(account != null) {
			if(accountsList.remove(account) == true) {
				System.out.printf("A Conta número: %d foi deletada com sucesso!%n", number);
			} 
		} else {
			System.out.printf("A Conta número: %d não foi encontrada!%n", number);
		}

	}

	@Override
	public void withdraw(int number, float value) {
		
		var account = searchInCollection(number);
		
		if(account != null) {
			
			if(account.withdraw(value) == true) {
				System.out.printf("\nO Saque na Conta número: %d foi efetuado com sucesso!", number);
			}
		} else {
			System.out.printf("\nA Conta número: %d não foi encontrada!%n", number);
		}

	}

	@Override
	public void deposit(int number, float value) {
		
		var account = searchInCollection(number);
		
		if(account != null) {
			account.deposit(value);
			System.out.printf("\nO Depósito na Conta número: %d foi efetuado com sucesso!", number);
		} else {
			System.out.printf("\nA Conta número: %d não foi encontrada!%n", number);
		}

	}

	@Override
	public void transfer(int originNumber, int destinationNumber, float value) {
		
		var originAccount = searchInCollection(originNumber);
		var destinationAccount = searchInCollection(destinationNumber);
		
		if(originAccount != null && destinationAccount != null) {
			
			if(originAccount.withdraw(value) == true) {
				destinationAccount.deposit(value);
				System.out.printf(
						"\nA Transferência da conta: %d, para a conta: %d foi efetuada com sucesso!",
						originNumber,
						destinationNumber
				  );
			}	
			
		} else {
			System.out.println("\nA Conta de Origem e/ou Destino não foram encontradas!");
		}	

	}

	public int generateNumber() {
		return ++number;
	}

	public Conta searchInCollection(int number) {
		for (var account : accountsList) {
			if (account.getNumber() == number) {
				return account;
			}
		}

		return null;
	}

}
