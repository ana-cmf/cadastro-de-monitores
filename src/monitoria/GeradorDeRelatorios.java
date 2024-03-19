package monitoria;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDeRelatorios  {
	
	public  static void obterComprovanteDeInscricoesAluno(String matricula, long id, CentralDeInformacoes central)  {
		Document documento = new Document(PageSize.A4);

		try {
			OutputStream file = new FileOutputStream ("relatorio.pdf");
			PdfWriter.getInstance(documento, file);

			documento.open();
			
			String cabecalho = "Relatório de inscrição no edital de Monitoria";
			Paragraph prg = new Paragraph(cabecalho);
			prg.setAlignment(Element.TITLE);
			documento.add(prg);
			
			Aluno alunoInscrito = central.recuperarAlunoPorMatricula(matricula);
			
			String disciplinas = "Disciplinas: \n";
			
			for (Vaga vaga : central.recuperarInscricoesDeUmAlunoEmUmEdital(matricula, id)) {
				for (Aluno aluno : vaga.getListaDeAlunosInscritos()) {
					
					if (aluno.getMatricula().equals(matricula)) {
						
						disciplinas += vaga.getDisciplina() + "\n";
						
					
					}
				}
			}
			
			String infoAluno = "Nome: " + alunoInscrito.getNome() + "\n"+
			"Matricula: " + alunoInscrito.getMatricula() + "\n"+
			"Sexo: " + alunoInscrito.getSexo() + "\n"+
			"Email: " + alunoInscrito.getEmail() + "\n"+
			disciplinas;
			
			
			prg = new Paragraph(infoAluno);
			prg.setAlignment(Element.ALIGN_LEFT);
			documento.add(prg);

			documento.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}

