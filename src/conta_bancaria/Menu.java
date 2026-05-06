package conta_bancaria;

import java.util.InputMismatchException;
import java.util.Scanner;

import conta_bancaria.controller.ContaController;
import conta_bancaria.model.Conta;
import conta_bancaria.model.ContaCorrente;
import conta_bancaria.model.ContaPoupanca;
import conta_bancaria.util.Cores;

public class Menu {

	private static final Scanner input = new Scanner(System.in);
	private static final ContaController accountController = new ContaController();

	public static void main(String[] args) {
		
		createTestAccounts();

		int option;

		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            0 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);

			try {
				option = input.nextInt();
				input.nextLine();
			} catch (InputMismatchException e) {
				option = -1;
				System.out.println("\nDigite um número inteiro!");
				input.nextLine();
			}

			if (option == 0) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil com Z - O seu Futuro começa aqui!");
				about();
				input.close();
				System.exit(0);
			}

			switch (option) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");
				
				registerAccount();

				keyPress();
				break;
			case 2:
				System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");

				listAccounts();

				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n");
				
				searchAccountByNumber();				

				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");
				
				editAccount();

				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");
				
				deleteAccount();

				keyPress();
				break;
			case 6:
				System.out.println(Cores.TEXT_WHITE + "Saque\n\n");
				
				withdraw();

				keyPress();
				break;
			case 7:
				System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");
				
				deposit();

				keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");
				
				transfer();

				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
				;
				keyPress();
				break;
			}
		}
	}

	public static void about() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: ");
		System.out.println("Vinicius Vicente - viniciusr1@genstudents.org");
		System.out.println("https://github.com/viniovicente99");
		System.out.println("*********************************************************");
	}

	private static void listAccounts() {

		accountController.listAll();
	}
	
	public static void registerAccount() {
		
		System.out.println("Digite o número da Agência: ");
		int branch = input.nextInt();
		
		System.out.println("Digite o nome do Titular: ");
		input.skip("\\R");
		String holder = input.nextLine();
		
		System.out.println("Digite o tipo da conta (1 - CC | 2 - CP): ");
		int type = input.nextInt();
		
		System.out.println("Digite o Saldo inicial da conta: ");
		float balance = input.nextFloat();
		
		switch (type) {
		case 1 -> {
			System.out.println("Digite o limite da conta: ");
			float limit = input.nextFloat();
			accountController.register(new ContaCorrente(accountController.generateNumber(), branch, type, holder, balance, limit));
		}
		case 2 -> {
			System.out.println("Digite o dia do aniversário da conta: ");
			int anniversary = input.nextInt();
			accountController.register(new ContaPoupanca(accountController.generateNumber(), branch, type, holder, balance, anniversary));	
		}
		default -> System.out.println(Cores.TEXT_RED_BOLD + "Tipo de conta inválido!" + Cores.TEXT_RESET);
		}
	}
	
	private static void createTestAccounts() {
	    accountController.register(
	        new ContaCorrente(accountController.generateNumber(), 123, 1, "João da Silva", 1000.00f, 100.00f));
	    accountController.register(
	        new ContaCorrente(accountController.generateNumber(), 456, 1, "Maria dos Santos", 2000.00f, 200.00f));
	    accountController.register(
	        new ContaPoupanca(accountController.generateNumber(), 789, 2, "Mariana Hernandez", 10000.00f, 12));
	    accountController.register(
	        new ContaPoupanca(accountController.generateNumber(), 123, 2, "Giovanna Giunchetti", 8000.00f, 23));
	}
	
	public static void searchAccountByNumber() {
		
		System.out.print("Digite o número da conta: ");
		int number = input.nextInt();
		input.nextLine();
		
		accountController.searchByNumber(number);
	}
	
	public static void editAccount() {
		
		System.out.println("Digite o número da conta: ");
		int number = input.nextInt();
		input.nextLine();
		
		Conta account = accountController.searchInCollection(number);
		
		if(account != null) {
			
			int branch = account.getBranch();
			String holder = account.getHolder();
			float balance = account.getBalance();
			int type = account.getType();
			
			System.out.printf("Agência atual: %d\nNova Agência (pressione ENTER para manter): ", branch);
			String userInput = input.nextLine();
			branch = userInput.isEmpty() ? branch : Integer.parseInt(userInput);
			
			System.out.printf("Titular atual: %s\nNovo Titular (pressione ENTER para manter): ", holder);
			userInput = input.nextLine();
			holder = userInput.isEmpty() ? holder : userInput;
			
			System.out.printf("Saldo atual: %s\nNovo Saldo (pressione ENTER para manter): ", balance);
			userInput = input.nextLine();
			balance = userInput.isEmpty() ? balance : Float.parseFloat(userInput.replace(',', '.'));
			
			switch(type) {
			case 1 -> {
				float limit = ((ContaCorrente) account).getLimit();
				
				System.out.printf("Limite atual: R$ %.2f\nNovo Limite (pressione ENTER para manter): ", limit);
				userInput = input.nextLine();
				limit = userInput.isEmpty() ? limit : Float.parseFloat(userInput.replace(',', '.'));
				
				accountController.edit(new ContaCorrente(number, branch, type, holder, balance, limit));
			}
			case 2 -> {
				int anniversary = ((ContaPoupanca) account).getAnniversary();
				
				System.out.printf("Aniversário atual: %d\nNovo Aniversário (pressione ENTER para manter): ", anniversary);
				userInput = input.nextLine();
				anniversary = userInput.isEmpty() ? anniversary : Integer.parseInt(userInput);
				
				accountController.edit(new ContaPoupanca(number, branch, type, holder, balance, anniversary));
			}
			default -> System.out.println(Cores.TEXT_RED_BOLD + "Tipo de conta inválido!" + Cores.TEXT_RESET);			
			}
			
		} else {
			System.out.printf("\nA conta número %d não foi encontrada!\n", number);
		}
		
	}
	
	public static void deleteAccount() {
		
		System.out.print("Digite o número da conta: ");
		int number = input.nextInt();
		input.nextLine();
		
		System.out.print("\nTem certeza que deseja excluir esta conta? (S/N): ");
		String confirmation = input.nextLine();
		
		if(confirmation.equalsIgnoreCase("S")) {
			accountController.delete(number);
		} else {
			System.out.print("\nOperação cancelada.");
		}
	}
	
	public static void withdraw() {
		
		System.out.print("Digite o número da conta: ");
		int number = input.nextInt();
		
		System.out.print("Digite o valor do saque: ");
		float value = input.nextFloat();
		
		accountController.withdraw(number, value);
	}
	
	public static void deposit() {
		
		System.out.print("Digite o número da conta: ");
		int number = input.nextInt();
		
		System.out.print("Digite o valor do depósito: ");
		float value = input.nextFloat();
		
		accountController.deposit(number, value);
	}
	
	public static void transfer() {
		
		System.out.print("Digite o número da conta de origem: ");
		int originNumber = input.nextInt();
		
		System.out.print("Digite o número da conta de destino: ");
		int destinationNumber = input.nextInt();
		
		System.out.println("Digite o valor da transferência: ");
		float value = input.nextFloat();
		
		accountController.transfer(originNumber, destinationNumber, value);
		
	}

	public static void keyPress() {
		System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
		input.nextLine();
	}
}
