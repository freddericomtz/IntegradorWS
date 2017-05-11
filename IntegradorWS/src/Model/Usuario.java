package Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GeneratorType;

import java.util.List;


@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer Id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="login",unique=true)
	private String login;

	@Column(name="senha")
	private String senha;
	
	@Column(name="telefone")
	private String telefone;
	
	@Column(name="dataultimologin")
	private Date dataUltimoLogin;
	
	@OneToMany(mappedBy = "usuario", targetEntity = Denuncia.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Denuncia> denuncia;
	
	@Column(name="likes", columnDefinition = "int default 0")
	private Integer likes;

	@Column(name="dislikes", columnDefinition = "int default 0")
	private Integer dislikes;

		
	public List<Denuncia> getDenuncia() {
		return denuncia;
	}

	public void setDenuncia(List<Denuncia> denuncia) {
		this.denuncia = denuncia;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataUltimoLogin() {
		return dataUltimoLogin;
	}

	public void setDataUltimoLogin(Date dataUltimoLogin) {
		this.dataUltimoLogin = dataUltimoLogin;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getDislikes() {
		return dislikes;
	}

	public void setDislikes(Integer dislikes) {
		this.dislikes = dislikes;
	}	
}

