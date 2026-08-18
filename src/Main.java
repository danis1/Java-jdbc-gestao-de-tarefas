import br.edu.fatecpg.JavaJDBC.banco.Banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\n==== BEM VINDO ===");
            System.out.println("==== DIGITE A OPÇÃO DESEJADA PARA CURSO ===");
            System.out.println("1 - INSERIR");
            System.out.println("2 - LISTAR");
            System.out.println("3 - ATUALIZAR");
            System.out.println("4 - DELETAR");
            System.out.println("0 - SAIR\n");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do curso: ");
                    String nome = scanner.nextLine();
                    System.out.print("Período EXEMPLO: Noturno: ");
                    String periodo = scanner.nextLine();

                    String sqlInsert = "INSERT INTO tb_curso (nome, periodo) VALUES (?, ?)";

                    try (Connection conn = Banco.connect();
                         PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                        stmt.setString(1, nome);
                        stmt.setString(2, periodo);
                        stmt.execute();

                        System.out.println("O curso foi inserido");
                    } catch (SQLException e) {
                        System.out.println("Erro ao inserir: " + e.getMessage());
                    }
                    break;

                case 2:
                    String sqlSelect = "SELECT * FROM tb_curso ORDER BY id";

                    try (Connection conn = Banco.connect();
                         PreparedStatement stmt = conn.prepareStatement(sqlSelect);
                         ResultSet rs = stmt.executeQuery()) {

                        System.out.println("\n--- LISTA DE CURSOS ---");
                        boolean temRegistros = false;

                        while (rs.next()) {
                            temRegistros = true;
                            int id = rs.getInt("id");
                            String nomeCurso = rs.getString("nome");
                            String periodoCurso = rs.getString("periodo");

                            System.out.println("ID: " + id + " | Nome: " + nomeCurso + " | Período: " + periodoCurso);
                        }

                        if (!temRegistros) {
                            System.out.println("Nenhum curso cadastrado.");
                        }

                    } catch (SQLException e) {
                        System.out.println("Erro ao listar: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID a ser alterado: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Limpeza de buffer

                    System.out.print("Digite o novo nome do curso: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Digite o novo período: ");
                    String novoPeriodo = scanner.nextLine();

                    String sqlUpdate = "UPDATE tb_curso SET nome = ?, periodo = ? WHERE id = ?";

                    try (Connection conn = Banco.connect();
                         PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {

                        stmt.setString(1, novoNome);
                        stmt.setString(2, novoPeriodo);
                        stmt.setInt(3, idAtualizar);

                        int linhas = stmt.executeUpdate();
                        if (linhas > 0) {
                            System.out.println("Curso atualizado com sucesso");
                        } else {
                            System.out.println("ID não encontrado");
                        }
                    } catch (SQLException e) {
                        System.out.println("A atualização falhou: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Digite ID que gostaria de excluir: ");
                    int idDeletar = scanner.nextInt();
                    scanner.nextLine(); // Limpeza de buffer

                    String sqlDelete = "DELETE FROM tb_curso WHERE id = ?";

                    try (Connection conn = Banco.connect();
                         PreparedStatement stmt = conn.prepareStatement(sqlDelete)) {

                        stmt.setInt(1, idDeletar);

                        int linhas = stmt.executeUpdate();
                        if (linhas > 0) {
                            System.out.println("-> Curso deletado com sucesso!");
                        } else {
                            System.out.println("-> ID não encontrado.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao deletar: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }

        } while (opcao != 0);

        scanner.close();
    }
}