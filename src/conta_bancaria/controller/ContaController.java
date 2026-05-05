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
		// TODO Auto-generated method stub

	}

	@Override
	public void edit(Conta account) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int number) {
		// TODO Auto-generated method stub

	}

	@Override
	public void withdraw(int number, float value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deposit(int number, float value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transfer(int numberOrigin, int numberDestination, float value) {
		// TODO Auto-generated method stub

	}

	public int generateNumber() {
		return ++ number;
	}

}
