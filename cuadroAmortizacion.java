package com.macrotd.intro;
import java.util.logging.Logger;
import java.text.DecimalFormat;
import java.io.*;

public class cuadroAmortizacion {

	private int tipoAmortizacion;
	private float intAmor;
	private double cuotaBase;
	private double cuotaBase2d;
	
	private float[] cuota = new float[320];
	private float[] interes = new float[320];
	private float[] capital = new float[320];
	private float[] capitalPte = new float[320];

	private static DecimalFormat df2 = new DecimalFormat("#.###");	
	
	/**
	 * @return the cuota
	 */
	public float[] getCuota() {
		return cuota;
	}

	/**
	 * @param cuota the cuota to set
	 */
	public void setCuota(float[] cuota) {
		this.cuota = cuota;
	}

	/**
	 * @return the interes
	 */
	public float[] getInteres() {
		return interes;
	}

	/**
	 * @param interes the interes to set
	 */
	public void setInteres(float[] interes) {
		this.interes = interes;
	}

	/**
	 * @return the capital
	 */
	public float[] getCapital() {
		return capital;
	}

	/**
	 * @param capital the capital to set
	 */
	public void setCapital(float[] capital) {
		this.capital = capital;
	}

	/**
	 * @return the capitalPte
	 */
	public float[] getCapitalPte() {
		return capitalPte;
	}

	/**
	 * @param capitalPte the capitalPte to set
	 */
	public void setCapitalPte(float[] capitalPte) {
		this.capitalPte = capitalPte;
	}

	/**
	 * @return the cuotaBase2d
	 */
	public double getCuotaBase2d() {
		return cuotaBase2d;
	}

	/**
	 * @param cuotaBase2d the cuotaBase2d to set
	 */
	public void setCuotaBase2d(double cuotaBase2d) {
		this.cuotaBase2d = cuotaBase2d;
	}

	/**
	 * @return the cuotaBase
	 */
	public double getCuotaBase() {
		return cuotaBase;
	}

	/**
	 * @param cuotaBase the cuotaBase to set
	 */
	public void setCuotaBase(double cuotaBase) {
		this.cuotaBase = cuotaBase;
	}

	/**
	 * @return the periodicidad
	 */
	public int getPeriodicidad() {
		return periodicidad;
	}

	/**
	 * @param periodicidad the periodicidad to set
	 */
	public void setPeriodicidad(int periodicidad) {
		this.periodicidad = periodicidad;
	}

	/**
	 * @return the intAmor
	 */
	public float getIntAmor() {
		return intAmor;
	}

	/**
	 * @param intAmor the intAmor to set
	 */
	public void setIntAmor(float intAmor) {
		this.intAmor = intAmor;
	}

	private int mesesAnno;
	private float nominal;
	private float CAE;
	private float TIN;
	private float plazos;
	private float TAE;
	private int periodicidad;
			
	/**
	 * @return the tipoAmortizacion
	 */
	public int getTipoAmortizacion() {
		return tipoAmortizacion;
	}

	/**
	 * @param tipoAmortizacion the tipoAmortizacion to set
	 */
	public void setTipoAmortizacion(int tipoAmortizacion) {
		this.tipoAmortizacion = tipoAmortizacion;
	}

	/**
	 * @return the mesesAnno
	 */
	public int getMesesAnno() {
		return mesesAnno;
	}

	/**
	 * @param mesesAnno the mesesAnno to set
	 */
	public void setMesesAnno(int mesesAnno) {
		this.mesesAnno = mesesAnno;
	}

	/**
	 * @return the nominal
	 */
	public float getNominal() {
		return nominal;
	}

	/**
	 * @param nominal the nominal to set
	 */
	public void setNominal(float nominal) {
		this.nominal = nominal;
	}

	/**
	 * @return the cAE
	 */
	public float getCAE() {
		return CAE;
	}

	/**
	 * @param cae the cAE to set
	 */
	public void setCAE(float cae) {
		CAE = cae;
	}

	/**
	 * @return the tIN
	 */
	public float getTIN() {
		return TIN;
	}

	/**
	 * @param tin the tIN to set
	 */
	public void setTIN(float tin) {
		TIN = tin;
	}

	/**
	 * @return the plazos
	 */
	public float getPlazos() {
		return plazos;
	}

	/**
	 * @param plazos the plazos to set
	 */
	public void setPlazos(float plazos) {
		this.plazos = plazos;
	}

	/**
	 * @return the tAE
	 */
	public float getTAE() {
		return TAE;
	}

	/**
	 * @param tae the tAE to set
	 */
	public void setTAE(float tae) {
		TAE = tae;
	}

	public cuadroAmortizacion(float nominal, float cae, float tin, float plazos) {
		super();
		
		double razon;
		float CNVR;
		double nominalTrabajo;
		double amortizado;
		double interesesRend;
		double pendienteAmor;
		double cuotaFinal;
		double totalIntereses;
		double totalCuota=0;
		double totalCapital=0;
		
		this.nominal = nominal;
		CAE = cae;
		TIN = tin;
		this.plazos = plazos;
		periodicidad=12;
		
		this.setTipoAmortizacion(12);
		this.setMesesAnno(12/tipoAmortizacion);
	
		this.intAmor=(this.getTIN() * this.getMesesAnno())/1200;
		
		CNVR=getTipoAmortizacion()/periodicidad;
		
		razon = 1 / (1 + Math.pow(this.getIntAmor(), CNVR));

		this.setCuotaBase(getNominal()/		
		((razon * (Math.pow(razon, getPlazos()) - 1) / (razon - 1))));
	
		this.setCuotaBase2d(Math.round(this.getCuotaBase()));
		
		nominalTrabajo= this.getNominal();
		amortizado=0;
		pendienteAmor=nominalTrabajo;
		totalIntereses=0;
		
		for (int i=0;i<this.getPlazos();i++){
			nominalTrabajo = nominalTrabajo - amortizado;
			interesesRend = nominalTrabajo * this.getIntAmor();
			if (i==(plazos -1)){
				amortizado=pendienteAmor;
				if (amortizado >= this.getCuotaBase()) {
					cuotaFinal = amortizado + interesesRend;
					System.out.println("Ult " + cuotaFinal);
				}
				else {
					interesesRend = this.getCuotaBase() - amortizado;
				}
				pendienteAmor=0;
			}
			else{
				amortizado = this.getCuotaBase() - interesesRend;
				pendienteAmor = pendienteAmor - amortizado;
			}
			totalIntereses = totalIntereses + interesesRend;
			totalCuota = totalCuota + this.getCuotaBase();
			totalCapital = totalCapital + amortizado;
//			System.out.println("Plazo :" + i + "Nominal : " + nominalTrabajo + "Intereses : " + interesesRend + "Amortizado : " + amortizado);
			System.out.println("Plazo :" + i + "Nominal : " + df2.format(nominalTrabajo) + "Intereses : " + df2.format(interesesRend) + "Amortizado : " + df2.format(amortizado));
		}
		System.out.println("Total intereses: " + df2.format(totalIntereses));
		System.out.println("Total cuota: " + df2.format(totalCuota));		
		System.out.println("Total capital: " + df2.format(totalCapital));
//		return(totalIntereses);
	}
	
	public int cuadroAAmortizacion(float nominal, float cae, float tin, float plazos) {
//		super();
		
		try{
//		FileOutputStream fout=new FileOutputStream("C:\\users/s237402/Documents/salidaCuadro2.txt"); 		
		    
		
		double razon;
		float CNVR;
		double nominalTrabajo;
		double amortizado;
		double interesesRend;
		double pendienteAmor;
		double cuotaFinal;
		double totalIntereses;
		double totalCuota=0;
		double totalCapital=0;
		
		this.nominal = nominal;
		CAE = cae;
		TIN = tin;
		this.plazos = plazos;
		periodicidad=12;
		
		this.setTipoAmortizacion(12);
		this.setMesesAnno(12/tipoAmortizacion);
	
		this.intAmor=(this.getTIN() * this.getMesesAnno())/1200;
		
		CNVR=getTipoAmortizacion()/periodicidad;
		
		razon = 1 / (1 + Math.pow(this.getIntAmor(), CNVR));

		this.setCuotaBase(getNominal()/		
		((razon * (Math.pow(razon, getPlazos()) - 1) / (razon - 1))));
	
		this.setCuotaBase2d(Math.round(this.getCuotaBase()));
		
		nominalTrabajo= this.getNominal();
		amortizado=0;
		pendienteAmor=nominalTrabajo;
		totalIntereses=0;
		
		for (int i=0;i<this.getPlazos();i++){
			nominalTrabajo = nominalTrabajo - amortizado;
			interesesRend = nominalTrabajo * this.getIntAmor();
			if (i==(plazos -1)){
				amortizado=pendienteAmor;
				if (amortizado >= this.getCuotaBase()) {
					cuotaFinal = amortizado + interesesRend;
					System.out.println("Ult " + cuotaFinal);
				}
				else {
					interesesRend = this.getCuotaBase() - amortizado;
				}
				pendienteAmor=0;
			}
			else{
				amortizado = this.getCuotaBase() - interesesRend;
				pendienteAmor = pendienteAmor - amortizado;
			}
			totalIntereses = totalIntereses + interesesRend;
			totalCuota = totalCuota + this.getCuotaBase();
			totalCapital = totalCapital + amortizado;
//			System.out.println("Plazo :" + i + "Nominal : " + nominalTrabajo + "Intereses : " + interesesRend + "Amortizado : " + amortizado);
			System.out.println("Plazo :" + i + "Nominal : " + df2.format(nominalTrabajo) + "Intereses : " + df2.format(interesesRend) + "Amortizado : " + df2.format(amortizado));
//			String s="Plazo :" + i + "Nominal : " + df2.format(nominalTrabajo) + "Intereses : " + df2.format(interesesRend) + "Amortizado : " + df2.format(amortizado);    
		}
		System.out.println("Total intereses: " + df2.format(totalIntereses));
		System.out.println("Total cuota: " + df2.format(totalCuota));		
		System.out.println("Total capital: " + df2.format(totalCapital));
        String s="Hola mundo";
		
		byte b[]=s.getBytes(s);//converting string into byte array    			
		fout.write(b);  	
        fout.close();   			
		
		}catch(Exception e){System.out.println(e);}
		return(0);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BufferedReader buff = new BufferedReader( new InputStreamReader( System.in));		

		float capitalInicial=0;
		float comisionApertura=0;
		float tipoDeInteres=0;
		float plazosPrestamo=0;

//		cuadroAmortizacion p = new cuadroAmortizacion(1000,0,5,12);

		System.out.println("Importe: "); 
		try {
			capitalInicial = Integer.parseInt( buff.readLine());
			
		} catch (Exception e) { e.printStackTrace(); }
		
		
		System.out.println("Plazos: "); 
		try {
			plazosPrestamo = Integer.parseInt( buff.readLine());
		} catch (Exception e) { e.printStackTrace(); }

		System.out.println("TIN: "); 
		try {
			tipoDeInteres = Float.parseFloat( buff.readLine());
		} catch (Exception e) { e.printStackTrace(); }

		
		cuadroAmortizacion p = new cuadroAmortizacion(capitalInicial,comisionApertura,tipoDeInteres,plazosPrestamo);

		Logger l = Logger.getLogger(cuadroAmortizacion.class.getName());
		l.info("Cuota base:" + p.getCuotaBase() + "Redondeada: " + p.getCuotaBase2d());
	
		System.out.println("Cuota base:" + df2.format(p.getCuotaBase()) + "Redondeada: " + p.getCuotaBase2d());

	}

}
