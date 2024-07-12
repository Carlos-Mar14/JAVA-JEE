package com.stucom.prac3.servlets;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.stucom.prac3.jdbc.Conexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import net.sf.jasperreports.web.util.WebHtmlResourceHandler;

public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			// Localizar el informe compilado
			File informeCompilado = new File(
					getServletContext().getRealPath("/WEB-INF/informes/alumnos/Alumnos.jasper"));

			// Cargar el informe compilado
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(informeCompilado.getPath()));

			// Actualizarlo con los datos de la BD
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String, Object>(0),
					Conexion.getConexion());

			/*
			 * Ahora ejecutamos un metodo especifico segun el informe que haya seleccionado
			 * el usuario
			 */

			// Obtener el tipo de informe seleccionado por el usuario
			String tipoInforme = request.getParameter("tipo");

			try {

				response.setContentType(tipoInforme);

				if ("application/pdf".equals(tipoInforme)) {

					JRPdfExporter exporter = new JRPdfExporter();

					exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
					exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
					SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
					exporter.setConfiguration(configuration);

					exporter.exportReport();

				} else if ("application/vnd.ms-excel".equals(tipoInforme)) {

					response.setHeader("Content-Disposition", "inline; filename=informe.xls");
					JRXlsExporter exporter = new JRXlsExporter();

					exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

					exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

					SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
					exporter.setConfiguration(configuration);

					exporter.exportReport();

				} else if ("application/msword".equals(tipoInforme)) {

					response.setHeader("Content-Disposition", "inline; filename=informe.doc");
					JRDocxExporter exporter = new JRDocxExporter();

					exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
					exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

					exporter.exportReport();

				} else {

					request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);

					HtmlExporter exporter = new HtmlExporter();

					exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

					SimpleHtmlExporterOutput exporterOutput = new SimpleHtmlExporterOutput(response.getOutputStream());
					exporterOutput.setImageHandler(new WebHtmlResourceHandler("image?image={0}"));

					exporter.setExporterOutput(exporterOutput);

					exporter.exportReport();
				}
			} catch (RuntimeException e) {
				throw e;
			} catch (Exception e) {
				throw new ServletException(e);
			}
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
