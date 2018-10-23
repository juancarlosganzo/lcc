package me.juancarlosganzo.lcc;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import me.juancarlosganzo.lcc.dto.InformacionPago;

/**
 * 
 * @author jcganzo Generacion una linea de captura de 20 digitos
 */
public class LineaCapturaCerrada {

	/**
	 * Genera una linea de captura a partir de la información de un pago.
	 * 
	 * @param infoPago
	 * @return
	 * @throws LinecaCapturaCerradaException
	 *             en caso que no se cumpla con alguna de las validaciones.
	 */
	public String generar(InformacionPago infoPago) throws LinecaCapturaCerradaException {
		String lcc = "";

		lcc = validarServicio(infoPago.getServicio()) + CondensarFechaOperacion(infoPago.getFechaOperacion())
				+ validadConsecutivo(infoPago.getConsecutivo())
				+ condensarFechaVencimiento(infoPago.getFechaExpiracion()) + condensarImporte(infoPago.getMonto());
		lcc = lcc +2;
		lcc = lcc + ValidarLineaCaptura(lcc);
		return lcc;
	}

	/**
	 * Enmascara el importe de la linea de captura.
	 * 
	 * @param importe
	 * @return
	 */
	private String condensarImporte(BigDecimal importe) {

		int[] serie = { 7, 3, 1 };
		int suma = 0;
		int k = 0;
		int j;

		String convert = String.valueOf(importe);
		char[] importTexto = convert.toCharArray();
		for (int i = (convert.length()) - 1; i >= 0; i--) {

			if (Character.isDigit(importTexto[i]) == true) {

				j = Character.getNumericValue(importTexto[i]);
				suma += j * serie[k];
				k = k == 2 ? 0 : k + 1;
			}

		}

		return String.valueOf(suma % 10);
	}

	private String ValidarLineaCaptura(String lc) {
		
		String serie = "1113171923";
		long suma = 0;
		int k = 0;
		long llave;
		String llaves;
		String sub;
		int substr;
		long a;
		char[] lcc = lc.toCharArray();
		for (int i = lcc.length - 1; i >= 0; i--) {
			if (Character.isDigit(lcc[i])) {
				sub = serie.substring(k, k + 2);
				substr = Integer.parseInt(sub);
				a = Character.getNumericValue(lcc[i]);
				suma += (a * substr);
				k = k == 8 ? 0 : k + 2;
			}
		}
		llave = suma % 97;
		llave = llave + 1;
		llaves = String.valueOf(llave);
		if (llaves.length() < 2) {
			
			return "0" + llaves;
		}
		
		return llaves;

	}

	/**
	 * Enmascara la fecha de vencimiento.
	 * 
	 * @param fechaVencimiento
	 * @return
	 */
	private String condensarFechaVencimiento(Date fechaVencimiento) {
		GregorianCalendar calendario = new GregorianCalendar();
		calendario.setTime(fechaVencimiento);
		int anyo = calendario.get(Calendar.YEAR);
		int mes = calendario.get(Calendar.MONTH) + 1;
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		int llaveAnyo = (anyo - 2013) * 372;
		int llaveMes = (mes - 1) * 31;
		int llaveDia = (dia - 1);		
		return String.valueOf(llaveAnyo + llaveMes + llaveDia);
	}

	/**
	 * Le da formato a la fecha
	 * 
	 * @param fechaOperacion
	 * @return
	 */
	private String CondensarFechaOperacion(Date fechaOperacion) {
		String fechaCondensada = new SimpleDateFormat("yyMM").format(fechaOperacion);
		return fechaCondensada;
	}

	/**
	 * Valida los parametros del consecutivo y lo formatea a 6 digitos.
	 * 
	 * @param consecutivo
	 * @return
	 * @throws LinecaCapturaCerradaException
	 */
	private String validadConsecutivo(int consecutivo) throws LinecaCapturaCerradaException {

		if (consecutivo < 0) {
			throw new LinecaCapturaCerradaException("El valor minimo para el servicio es de 0");
		}
		if (consecutivo > 999999) {
			throw new LinecaCapturaCerradaException("El valor maximo para el servicio es de 99");
		}
		String consecutivoValor = String.valueOf(consecutivo);

		int digitosPendientes = 6 - consecutivoValor.length();
		for (int i = 0; i < digitosPendientes; i++) {
			consecutivoValor = "0" + consecutivoValor;
		}

		return consecutivoValor;
	}

	/**
	 * Valida que el servicio se encuentre dentro de los parametros establecidos.
	 * 
	 * @param servicio
	 * @return
	 * @throws LinecaCapturaCerradaException
	 */
	private String validarServicio(int servicio) throws LinecaCapturaCerradaException {

		if (servicio < 0) {
			throw new LinecaCapturaCerradaException("El valor minimo para el servicio es de 0");
		}
		if (servicio > 99) {
			throw new LinecaCapturaCerradaException("El valor maximo para el servicio es de 99");
		}

		String idServicio = String.valueOf(servicio);

		if (idServicio.length() < 2) {
			idServicio = "0" + idServicio;
		}

		return idServicio;
	}
}
