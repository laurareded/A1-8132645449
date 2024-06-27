package br.edu.up;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String alunosFilePath = "Alunos.csv";
        String resumoFilePath = "Resumo.csv";

        List<String> alunosData = List.of(
            "1;Alan Oliveira Dias;10",
            "2;Alexandre da Silva;3",
            "3;Amanda Cristina;3",
            "4;Anderson Santana;6",
            "6;Antonio Luiz;0",
            "7;Arlon Rodrigues;7,5",
            "8;Beatriz Fonseca;0",
            "9;Bruna da Silva;7",
            "10;Bruno Santos;10",
            "11;Bruno Rodrigues;2",
            "12;Caroline de Lima;1,5",
            "13;Cristiano Augusto;4",
            "14;Cibele de Souza Teixeira;7,5",
            "15;Cleber Batista;4,5",
            "16;Cleber do Amaral;10",
            "17;Cleiton Antonio ;9",
            "18;Daniel Correa ;6",
            "21;Diane Ferreira;8,5",
            "22;Diego Barbosa;0",
            "23;Diego Rafael;3",
            "24;Diogo Carlos;8",
            "25;Diogo dos Santos;6,5",
            "26;Douglas de Lima;5",
            "27;Eduardo Cardoso;1",
            "28;Eduardo dos Santos;5",
            "29;Eduardo Fonseca;1",
            "30;Ericksson de Souza;2,5",
            "31;Erica Cristine;6",
            "32;Fabio Almeida;8,5",
            "33;Fabio Furtado;6",
            "34;Fernanda Ferreira;2",
            "35;Flavio Afonso;7,5",
            "36;Franciela de Almeida;2",
            "37;Francielle Cristina;7",
            "38;Francisco da Costa;3",
            "39;Gabriel Carlos;9,5",
            "40;Gabriel de Freitas;0",
            "41;Gabriel Dias;6",
            "42;Gabriel Franco;4,5",
            "44;Gabriel Rodrigues;5,5",
            "45;Geovani Moraes;9,5",
            "46;Giovane Carvalho;6",
            "47;Giovanna de Almeida ;8,5",
            "48;Guilherme Neto;8,5",
            "49;Guilherme Luis;6",
            "50;Guilherme Roberto;5",
            "51;Gustavo Anderson;7",
            "52;Henrique Santos de Souza;5,5",
            "53;Isaias Ribeiro;8",
            "54;Jonatan Paulo;8,5",
            "55;Jordan Ribas;9",
            "56;Julia Carvalho;10",
            "57;Karen de Lima;3",
            "58;Kathelin Ribeiro;6,5",
            "59;Lucas Martins;8,4",
            "60;Maria Eduarda;8",
            "61;Michael da Costa;2",
            "62;Nathalia de Lima;8,5",
            "63;Pedro Henrique;5",
            "64;Pietro Rodrigues;1",
            "65;Rafael de Oliveira;8,5",
            "66;Rafael Ribeiro;6",
            "67;Renan de Souza;2",
            "68;Renan Alves Santos;1",
            "69;Tiago Carneiro;5,5",
            "70;Wagner Leonardo;9,5"
        );

        writeCSV(alunosFilePath, alunosData);

        List<Aluno> alunos = readAlunos(alunosFilePath);

        gerarResumo(alunos, resumoFilePath);
    }

    public static List<Aluno> readAlunos(String filePath) {
        List<Aluno> alunos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                int matricula = Integer.parseInt(data[0]);
                String nome = data[1];
                double nota = Double.parseDouble(data[2].replace(",", "."));
                alunos.add(new Aluno(matricula, nome, nota));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public static void writeCSV(String filePath, List<String> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String record : data) {
                bw.write(record);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void gerarResumo(List<Aluno> alunos, String filePath) {
        int totalAlunos = alunos.size();
        int aprovados = 0;
        int reprovados = 0;
        double menorNota = Double.MAX_VALUE;
        double maiorNota = Double.MIN_VALUE;
        double somaNotas = 0.0;

        for (Aluno aluno : alunos) {
            double nota = aluno.getNota();
            if (nota >= 6.0) {
                aprovados++;
            } else {
                reprovados++;
            }
            if (nota < menorNota) {
                menorNota = nota;
            }
            if (nota > maiorNota) {
                maiorNota = nota;
            }
            somaNotas += nota;
        }

        double mediaNotas = somaNotas / totalAlunos;

        List<String> resumo = new ArrayList<>();
        resumo.add("Quantidade de alunos na turma: " + totalAlunos);
        resumo.add("Quantidade de alunos aprovados: " + aprovados);
        resumo.add("Quantidade de alunos reprovados: " + reprovados);
        resumo.add("Menor nota: " + menorNota);
        resumo.add("Maior nota: " + maiorNota);
        resumo.add("Média geral: " + mediaNotas);

        writeCSV(filePath, resumo);
    }
    
        
}




