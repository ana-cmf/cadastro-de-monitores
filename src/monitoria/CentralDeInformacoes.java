package monitoria;

import java.util.ArrayList;

public class CentralDeInformacoes {
	private ArrayList <Aluno>todosOsAlunos = new ArrayList<Aluno>();
	private ArrayList <EditalDeMonitoria>todosOsEditais = new ArrayList<EditalDeMonitoria>();
	
	public ArrayList<EditalDeMonitoria> getTodosOsEditais() {
		return todosOsEditais;
	}
	
	public void setTodosOsEditais(ArrayList<EditalDeMonitoria> todosOsEditais) {
		this.todosOsEditais = todosOsEditais;
	}
	
	public ArrayList<Aluno> getTodosOsAlunos() {
		return todosOsAlunos;
	}
	public void setTodosOsAlunos(ArrayList<Aluno> a) {
		todosOsAlunos = a;
	}
	
	public boolean  adicionarAluno(Aluno aluno) {
		if(todosOsAlunos.contains(recuperarAlunoPorMatricula(aluno.getMatricula()))) {
			System.out.println("Matricula duplicada");
			return false;
		}
		
		todosOsAlunos.add(aluno);
		System.out.println("Matricula adicionada");
		return true;
	}
	
	public Aluno  recuperarAlunoPorMatricula(String matricula) {
		for(Aluno a: todosOsAlunos) {
			if (a.getMatricula().equals(matricula)) {
               return a;
			}
		}
		return null;
	}
	
	public boolean adicionarEdital(EditalDeMonitoria edital) {
		if (!todosOsEditais.contains(recuperarEditalPeloId(edital.getId()))) {
			todosOsEditais.add(edital);
		}
		return false;
	}
	
	public EditalDeMonitoria recuperarEditalPeloId(long id) {
		
		for (EditalDeMonitoria ed: todosOsEditais) {
			if (ed.getId() == id) {
				return ed;
			}
		}
		return null;
	}
	
	public ArrayList<Vaga> recuperarInscricoesDeUmAlunoEmUmEdital(String matricula, long idDoEdital) {
		
		ArrayList<Vaga> vagasComInscricaoDoAluno = new ArrayList<Vaga>();
		EditalDeMonitoria edital = recuperarEditalPeloId(idDoEdital);
		Aluno aluno = recuperarAlunoPorMatricula(matricula);
		
		if (edital != null && aluno != null) {
			
			for(Vaga vaga: edital.getVagas()) {
				
				if(vaga.getListaDeAlunosInscritos().contains(aluno)) {
						vagasComInscricaoDoAluno.add(vaga);
				}
			}
		
			return vagasComInscricaoDoAluno;
		}
		
		return null;
	}

	public void listarTodosOsAlunos() {
		if (todosOsAlunos.isEmpty()) {
			System.out.println("Nenhum aluno cadastrado.");
		} else {
			System.out.println("Lista de todos os alunos:");
			for (Aluno aluno : todosOsAlunos) {
				System.out.println("Nome: " + aluno.getNome()+
				"Matrícula: " + aluno.getMatricula()+
				"Sexo: " + aluno.getSexo() + "\n");
			}
		}
	}

}

