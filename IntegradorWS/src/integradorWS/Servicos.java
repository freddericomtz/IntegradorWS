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
	
	@Path("/consultaMapaCalor")
	@GET
	public String calor(){
	
		String jeison = "[\n  {\"lat\" : -25.5469, \"lng\" : -54.5882 } ,\n  {\"lat\" : -25.5300, \"lng\" : -54.5700 } ,\n  {\"lat\" : -25.5100, \"lng\" : -54.5600 } ,\n  {\"lat\" : -25.5400, \"lng\" : -54.5872 } ,\n  {\"lat\" : -25.5310, \"lng\" : -54.5720 } ,\n  {\"lat\" : -25.5100, \"lng\" : -54.5600 },\n  {\"lat\" : -25.5000, \"lng\" : -54.5700 } ,\n  {\"lat\" : -25.4900, \"lng\" : -54.5600 } ,\n  {\"lat\" : -25.4900, \"lng\" : -54.5600 } ,\n  {\"lat\" : -25.5000, \"lng\" : -54.5610 } ,\n  {\"lat\" : -25.5000, \"lng\" : -54.5540 } ,\n  {\"lat\" : -25.5000, \"lng\" : -54.5611 } ,\n  {\"lat\" : -25.5470, \"lng\" : -54.5882 } ,\n  {\"lat\" : -25.5371, \"lng\" : -54.5700 } ,\n  {\"lat\" : -25.5450, \"lng\" : -54.5882 } ,\n  {\"lat\" : -25.5320, \"lng\" : -54.5700 } ,\n  {\"lat\" : -25.5479, \"lng\" : -54.5882 } ,\n  {\"lat\" : -25.5330, \"lng\" : -54.5700 } ,\n  {\"lat\" : -25.5459, \"lng\" : -54.5650 } ,\n  {\"lat\" : -25.5649, \"lng\" : -54.5600 } ,\n  {\"lat\" : -25.5446, \"lng\" : -54.5550 } ,\n  {\"lat\" : -25.5649, \"lng\" : -54.5500 } ,\n  {\"lat\" : -25.5369, \"lng\" : -54.5450 } ,\n  {\"lat\" : -25.5249, \"lng\" : -54.5400 } ,\n\n\n\n  {\"lat\" : -25.5470, \"lng\" : -54.589 }\n]";
		System.out.println("Carregando informacoes do mapa de caloura!");
		

		return jeison;
	}
	
	
}
