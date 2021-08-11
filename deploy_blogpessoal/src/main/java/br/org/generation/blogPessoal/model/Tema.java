package br.org.generation.blogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
//Atribuindo um nome para a tabela
@Table(name = "tb_temas")
public class Tema {

	// Primary key
	@Id
	/* Definindo que o campo será gerado automaticamente pelo banco de dados e o identity indica que o campo seguirá
	 * com base no último registro. 
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	// Indicando que o campo não pode ser nulo
	@NotNull
	@Size(min = 5, max = 25)
	private String descricao;
	
	// Relacionando a entidade Tema como one (um tema) com a entidade Postagem como many (muitas postagens)
	// mappedBy ???
	/* Permite excluir ou atualizar os registros relacionados presentes na tabela filha automaticamente, quando
	 * um registro da tabela pai for atualizado (ON UPDATE) ou excluído (ON DELETE). 
	 */
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("tema")
	// Cria uma lista de postagens
	private List<Postagem> postagem;
	
	// Getters and Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Postagem> getPostagem() {
		return postagem;
	}
	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
}
