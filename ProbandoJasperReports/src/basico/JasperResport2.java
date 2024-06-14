package basico;

import net.sf.jasperreports.engine.JasperCompileManager;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class JasperResport2 {

	public static void main(String[] args) {
		try {
			// 1. Se compila el informe JasperCompileManager.
			JasperCompileManager.compileReportToFile("./informe/tercerInforme.jrxml");

			// 2. Se llena el informe con la informacion necesaria
			Map<String, String> pars = new HashMap<String, String>();
			pars.put("P_INSTITUCION", "CIFO La Violeta");
			pars.put("P_CURSO_ANY", "Curso Marzo 2009");
			pars.put("P_CURSO_NOM", "Analista-Programador Java");
			
			JasperPrint jasperPrint = JasperFillManager.fillReport("informes/segundoInforme.jasper", pars, new JREmptyDataSource());
			
			// 3. Se exporta a PDF
			JasperExportManager.exportReportToPdfFile(jasperPrint,"segundoInforme.pdf");
			System.out.println("Informe generado.");
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

}
