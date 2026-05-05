package conta_bancaria.repository;

import conta_bancaria.model.Conta;

public interface ContaRepository {
	
	public void listAll();
	public void register(Conta account);
	public void searchByNumber(int number);
	public void edit(Conta account);
	public void delete(int number);
	
	public void withdraw(int number, float value);
	public void deposit(int number, float value);
	public void transfer(int numberOrigin, int numberDestination, float value);

}
