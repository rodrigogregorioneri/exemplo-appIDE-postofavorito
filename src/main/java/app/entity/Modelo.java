package app.entity;

/**
 * Classe que representa ...
 * 
 * @author Rodrigo Gregorio Neri
 * @version 1.0
 * @since 2017-08-01
 *
 */

public class Modelo {

	private String fipe_marca;
	private String fipe_codigo;
	private String name;
	private String marca;
	private String key;
	private String veiculo;
	private String id;

	public Modelo(String fipe_marca, String fipe_codigo, String name, String marca, String key, String veiculo, String id) {
	  this.fipe_marca = fipe_marca;
	  this.fipe_codigo = fipe_codigo;
	  this.name = name;
	  this.marca = marca;
	  this.key = key;
	  this.veiculo = veiculo;
	  this.id = id;
	}

	public String getFipe_marca() {
		return fipe_marca;
	}

	public void setFipe_marca(String fipe_marca) {
		this.fipe_marca = fipe_marca;
	}

	public String getFipe_codigo() {
		return fipe_codigo;
	}

	public void setFipe_codigo(String fipe_codigo) {
		this.fipe_codigo = fipe_codigo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
