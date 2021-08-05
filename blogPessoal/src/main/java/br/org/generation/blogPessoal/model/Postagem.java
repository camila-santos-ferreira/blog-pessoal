package br.org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Informando que uma classe também é uma entidade. 
 * A JPA estabelecerá a ligação entre a entidade e uma tabela de mesmo nome no banco de dados, onde os dados de
 * objetos desse tipo poderão ser persistidos.
 * Uma entidade representa, na Orientação a Objetos, uma tabela do banco de dados, e cada instância dessa entidade
 * representa uma linha dessa tabela.
 */
@Entity
// Atribuindo um nome para a tabela
@Table(name = "postagem")
public class Postagem {
	
	// Primary key
	@Id
	/* Definindo que o campo será gerado automaticamente pelo banco de dados e o identity indica que o campo seguirá
	 * com base no último registro. 
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	// Indicando que o campo não pode ser nulo
	@NotNull
	// Definindo o tamanho mínimo e máximo de um campo
	@Size(min = 5, max = 100)
	private String titulo;
	
	// Indicando que o campo não pode ser nulo
	@NotNull
	// Definindo o tamanho mínimo e máximo de um campo
	@Size(min = 10, max = 500)
	private String texto;
	
	// Indica que o atributo receberá uma data e hora do sistema
	@Temporal(TemporalType.TIMESTAMP)
												// Insere o milisegundos		
	private Date date = new java.sql.Date(System.currentTimeMillis());
	
	// Relacionando a entidade Postagem como many (muitas postagens) com a entidade Tema como one (um tema)
	@ManyToOne	
	// Serve para ignorar campos que contém em nossa resposta porém não existe em nosso modelo
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	// Métodos getters and setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}

}
