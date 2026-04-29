package conta_bancaria.model;

public class Conta {

	private int number;
	private int agency;
	private int type;
	private String holder;
	private float balance;

	public Conta(int number, int agency, int type, String holder, float balance) {
		this.number = number;
		this.agency = agency;
		this.type = type;
		this.holder = holder;
		this.balance = balance;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getAgency() {
		return agency;
	}

	public void setAgency(int agency) {
		this.agency = agency;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public boolean withdraw(float value) {

		if (this.balance < value) {

			System.out.println("\n Saldo insuficiente!");
			return false;
		}

		this.balance = this.balance - value;
		return true;
	}

	public void deposit(float value) {

		this.balance = this.balance + value;

	}

	public void view() {

		String type = "";

		switch (this.type) {
		case 1:
			type = "Conta Corrente";
			break;
		case 2:
			type = "Conta Poupança";
			break;
		}

		System.out.println("\n\n***********************************************************");
		System.out.println("Dados da Conta:");
		System.out.println("***********************************************************");
		System.out.println("Numero da Conta: " + this.number);
		System.out.println("Agência: " + this.agency);
		System.out.println("Tipo da Conta: " + type);
		System.out.println("Titular: " + this.holder);
		System.out.printf("Saldo: R$ %.2f%n", this.balance);

	}
}
