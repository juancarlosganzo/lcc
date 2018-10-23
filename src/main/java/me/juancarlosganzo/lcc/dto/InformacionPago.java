package me.juancarlosganzo.lcc.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author jcganzo
 * Información general del pago.
 */
public class InformacionPago implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9170687345560298512L;
	
	private int servicio;
	
	private Date fechaOperacion;
	
	private int consecutivo;
	
	private Date fechaExpiracion;
	
	private BigDecimal monto;

	/**
	 * @return el tipo servicio representado por dos digitos con un valor maximo de 99
	 */
	public int getServicio() {
		return servicio;
	}

	/**
	 * @param el tipo servicio representado por dos digitos con un valor maximo de 99
	 */
	public void setServicio(int servicio) {
		this.servicio = servicio;
	}

	/**
	 * @return  fechaOperacion
	 */
	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * @param fechaOperacion la fecha en la que se realiza la operaci�n.
	 */
	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	/**
	 * @return consecutivo numero consecutivo de la operación
	 */
	public int getConsecutivo() {
		return consecutivo;
	}

	/**
	 * @param consecutivo número consecutivo de la operación con un valor maximo de 999999
	 */
	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
	}

	/**
	 * @return  fechaExpiracion
	 */
	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}

	/**
	 * @param fechaExpiracion fecha de vencimiento de la operación
	 */
	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	/**
	 * @return the monto
	 */
	public BigDecimal getMonto() {
		return monto;
	}

	/**
	 * @param monto el monto total de la operación
	 */
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	
	


}
