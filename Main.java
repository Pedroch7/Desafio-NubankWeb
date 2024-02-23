package SistemaBanco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
		public static void main(String[] args) {
        List<ContaBancaria> contas = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        int escolha;
        do {
            System.out.println("\nMenu Principal:\n");
            System.out.println("1. Cadastrar Nova Conta\n");
            System.out.println("2. Excluir Conta Existente\n");
            System.out.println("3. Editar Conta Existente\n");
            System.out.println("4. Realizar Operações em Contas Existentes\n");
            System.out.println("5. Sair\n");
            System.out.print("Escolha uma opção: \n ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    cadastrarNovaConta(contas);
                    break;
                case 2:
                    excluirConta(contas);
                    break;
                case 3:
                    editarConta(contas);
                    break;
                case 4:
                    realizarOperacoes(contas);
                    break;
                case 5:
                    System.out.println("Encerrando o programa. \n ");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente. \n ");
            }
        } while (escolha != 5);

        scanner.close();
    }

    private static void cadastrarNovaConta(List<ContaBancaria> contas) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nTipo de Conta:\n");
        System.out.println("1. Conta Corrente\n");
        System.out.println("2. Conta Poupança \n");
        System.out.print("Escolha o tipo de conta: \n");
        int tipoConta = scanner.nextInt();

        System.out.print("Informe o número da conta: \n");
        int numeroConta = scanner.nextInt();
        System.out.print("Informe a agência: \n");
        String agencia = scanner.next();

        if (tipoConta == 1) {
            System.out.print("Informe o limite de crédito da Conta Corrente: \n ");
            double limiteCredito = scanner.nextDouble();
            contas.add(new ContaCorrente(numeroConta, agencia, limiteCredito));
        } else if (tipoConta == 2) {
            System.out.print("Informe o dia de aniversário da Conta Poupança: \n");
            int diaAniversario = scanner.nextInt();
            System.out.print("Informe a taxa de juros da Conta Poupança: \n");
            double taxaJuros = scanner.nextDouble();
            contas.add(new ContaPoupanca(numeroConta, agencia, diaAniversario, taxaJuros));
        } else {
            System.out.println("Opção inválida.\n");
        }

        System.out.println("Conta cadastrada com sucesso! \n");
    }

    private static void excluirConta(List<ContaBancaria> contas) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o número da conta a ser excluída: \n");
        int numeroConta = scanner.nextInt();

        contas.removeIf(conta -> conta.getNumero() == numeroConta);

        System.out.println("Conta excluída com sucesso ou não encontrada. \n");
    }

    private static void editarConta(List<ContaBancaria> contas) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o número da conta a ser editada: \n");
        int numeroConta = scanner.nextInt();

        for (ContaBancaria conta : contas) {
            if (conta.getNumero() == numeroConta) {
                System.out.println("Opções de Edição:\n");
                System.out.println("1. Adicionar Titular \n");
                System.out.println("2. Excluir Titular \n");
                System.out.println("3. Editar Titular \n");
                System.out.println("4. Voltar ao Menu Principal \n");
                System.out.print("Escolha uma opção de edição: \n ");
                int opcaoEdicao = scanner.nextInt();

                switch (opcaoEdicao) {
                    case 1:
                        adicionarTitular(conta);
                        break;
                    case 2:
                        excluirTitular(conta);
                        break;
                    case 3:
                        editarTitular(conta);
                        break;
                    case 4:
                        System.out.println("Voltando ao Menu Principal.");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
                return;
            }
        }

        System.out.println("Conta não encontrada. :( \n ");
    }

    private static void realizarOperacoes(List<ContaBancaria> contas) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o número da conta para realizar operações: \n ");
        int numeroConta = scanner.nextInt();

        for (ContaBancaria conta : contas) {
            if (conta.getNumero() == numeroConta) {
                System.out.println("Opções de Operações: \n");
                System.out.println("1. Sacar");
                System.out.println("2. Depositar");
                System.out.println("3. Transferir");
                System.out.println("4. Ver Saldo");
                System.out.println("5. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção de operação: ");
                int opcaoOperacao = scanner.nextInt();

                switch (opcaoOperacao) {
                    case 1:
                        realizarSaque(conta);
                        break;
                    case 2:
                        realizarDeposito(conta);
                        break;
                    case 3:
                        realizarTransferencia(contas, conta);
                        break;
                    case 4:
                        verSaldo(conta);
                        break;
                    case 5:
                        System.out.println("Voltando ao Menu Principal.");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
                return;
            }
        }

        System.out.println("Conta não encontrada. :(");
    }

    private static void realizarSaque(ContaBancaria conta) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o valor para sacar:) ");
        double valor = scanner.nextDouble();
        conta.sacar(valor);
    }

    private static void realizarDeposito(ContaBancaria conta) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o valor para depositar: ");
        double valor = scanner.nextDouble();
        conta.depositar(valor);
    }

    private static void realizarTransferencia(List<ContaBancaria> contas, ContaBancaria contaOrigem) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o número da conta de destino: ");
        int numeroContaDestino = scanner.nextInt();

        for (ContaBancaria contaDestino : contas) {
            if (contaDestino.getNumero() == numeroContaDestino) {
                System.out.print("Informe o valor para transferir: ");
                double valor = scanner.nextDouble();
                contaOrigem.transferir(contaDestino, valor);
                return;
            }
        }

        System.out.println("Conta de destino não encontrada.");
    }

    private static void verSaldo(ContaBancaria conta) {
        System.out.println("Saldo atual: " + conta.getSaldo());
    }

    private static void adicionarTitular(ContaBancaria conta) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o nome do novo titular: ");
        String nome = scanner.nextLine();
        System.out.print("Informe o CPF do novo titular: ");
        String cpf = scanner.nextLine();

        conta.adicionarTitular(new Pessoa(nome, cpf));
        System.out.println("Titular adicionado com sucesso!");
    }

    private static void excluirTitular(ContaBancaria conta) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o CPF do titular a ser excluído: ");
        String cpf = scanner.nextLine();

        conta.excluirTitular(cpf);
        System.out.println(" /n Titular excluído! :)");
    }

    private static void editarTitular(ContaBancaria conta) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o CPF do titular a ser editado: ");
        String cpf = scanner.nextLine();

        for (Pessoa titular : conta.getTitulares()) {
            if (titular.getCpf().equals(cpf)) {
                System.out.print("Informe o novo nome do titular: ");
                String novoNome = scanner.nextLine();
                titular.setNome(novoNome);
                System.out.println(" /n O Titular foi editado!  :) ");
                return;
            }
        }

        System.out.println("Titular não encontrado.");
    }
}
