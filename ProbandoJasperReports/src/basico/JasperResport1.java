package basico;

import java.util.HashMap;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class JasperResport1 {

	public static void main(String[] args) {
		JasperReport jasperReport;
		JasperPrint jasperPrint;

		// 1-Compilamos el archivo XML y lo cargamos en
		try {
			jasperReport = JasperCompileManager.compileReport("informes/primerInforme.jrxml");

			// 2-Llenamos el informe con la información yparámetros necesarios
			// (En este caso nada)
			jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), new JREmptyDataSource());

			// 3-Exportamos el reporte a pdf y lo guardamos en disco
			JasperExportManager.exportReportToPdfFile(jasperPrint, "holaMundo.pdf");
			System.out.println("Informe generado.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
