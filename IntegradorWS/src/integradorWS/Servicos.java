package integradorWS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import DAO.DAOEntity;
import DAO.DAOUsuario;
import Model.Usuario;

@Path("/servicos")
public class Servicos {
	
	@Path("/cadastro")
	@POST
	public String cadastrar(@FormParam("nome") @DefaultValue("nomevazio") String nome, @FormParam("login") @DefaultValue("loginvazio") String Userlogin,
			@FormParam("senha") @DefaultValue("senhavazia") String senha){
		
		System.out.println(nome +" | "+Userlogin+" | "+senha);
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setLogin(Userlogin);
		usuario.setSenha(senha);
		DateFormat dt = new SimpleDateFormat("MM/dd/YYY");
		Date datadehoje = new Date();
		usuario.setDataUltimoLogin(datadehoje);
		
		DAOEntity daoUsuario = new DAOUsuario();
		
		daoUsuario.save(usuario);
		System.out.println("cadastrou");
		return "cadastrou";
		
	}

	
	
	@Path("/login")
	@POST
	public String logar (@FormParam("login") @DefaultValue("loginvazio") String usuario, @FormParam("senha") @DefaultValue("senhavazia") String senha){
		
		DAOUsuario daoUsuario = new DAOUsuario();
		Usuario usu1 = daoUsuario.efetuarLogin(usuario,senha);
		
		if(usu1!=null){
			System.out.println("oi"+usuario+"|"+senha);

			System.out.println(usu1.getId());
			return "logado;"+usu1.getId();
		}
		else{
			System.out.println("nao existe");
			return "nao existe";
		}
	}
	
	@Path("/consulta")
	@GET
	public String consultar(@QueryParam("id") @DefaultValue("69") int id){
	
		DAOEntity daoUsuario = new DAOUsuario();
		
		Usuario usuario = (Usuario) daoUsuario.findById(id);
		
		Date dataultimoLogin = usuario.getDataUltimoLogin();
		
		//************* Atualiza Ultimo Login do Usuario ************* 
		
		DateFormat dt = new SimpleDateFormat("MM/dd/YYY HH:mm:ss");
		Date datadehoje = new Date();
		
		usuario.setDataUltimoLogin(datadehoje);
		daoUsuario.save(usuario);
		 
		System.out.println(usuario.getDataUltimoLogin() + " | "+dataultimoLogin);
		//************************************************************
		
	
		return "Informacoes do Usuario logado \n Id:"+usuario.getId()+" | Nome: "+usuario.getNome()+" | Login: "+usuario.getLogin()+
				" | Senha: "+usuario.getSenha()+" | Ultima vez logado: "+dataultimoLogin;
	}
}
