package integradorWS;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.glass.ui.Pixels.Format;

import DAO.DAODenuncia;
import DAO.DAOEntity;
import DAO.DAOUsuario;
import Model.Denuncia;
import Model.Usuario;
import sun.misc.BASE64Decoder;

@Path("/servicos")
public class Servicos {
	
	@Path("/cadastro")
	@POST
	public String cadastrar(@FormParam("nome")String nome, @FormParam("login")String Userlogin,
			@FormParam("senha") String senha, @FormParam("telefone") String telefone){
		
		Usuario usuario = new Usuario();
		DAOEntity daoUsuario = new DAOUsuario();
		Date datadehoje = new Date();
		JsonObject rsp = new JsonObject();
		try{		
			System.out.println(nome +" | "+Userlogin+" | "+senha);
			
			usuario.setNome(nome);
			usuario.setLogin(Userlogin);
			usuario.setSenha(senha);
			usuario.setDataUltimoLogin(datadehoje);	
			usuario.setTelefone(telefone);				
			daoUsuario.save(usuario);
		}catch (Exception e) {
			rsp.addProperty("resposta", false );
			return rsp.toString();
		}		
		rsp.addProperty("resposta", true );		
		return rsp.toString();		
	}
	
	@Path("/editar")
	@POST
	public String editar(@FormParam("nome")String nome, @FormParam("login")String Userlogin,
			@FormParam("senha") String senha, @FormParam("telefone") String telefone,@FormParam("id") int id){
		
		DAOEntity daoUsuario = new DAOUsuario();		
		Usuario usuario = (Usuario) daoUsuario.findById(id);
		Date datadehoje = new Date();
		JsonObject rsp = new JsonObject();
		try{		
			System.out.println(nome +" | "+Userlogin+" | "+senha);
			
			usuario.setNome(nome);
			usuario.setLogin(Userlogin);
			usuario.setSenha(senha);
			usuario.setDataUltimoLogin(datadehoje);	
			usuario.setTelefone(telefone);				
			daoUsuario.update(usuario);
		}catch (Exception e) {
			rsp.addProperty("resposta", false );
			return rsp.toString();
		}		
		rsp.addProperty("resposta", true );	
		rsp.addProperty("editou", true );		
		return rsp.toString();		
	}

	
	
	@Path("/login")
	@POST
	public String logar (@FormParam("login") @DefaultValue("loginvazio") String usuario, @FormParam("senha") @DefaultValue("senhavazia") String senha){
		
		DAOUsuario daoUsuario = new DAOUsuario();
		Usuario usu1 = daoUsuario.efetuarLogin(usuario,senha);
		JsonObject rsp = new JsonObject();
		
		if(usu1!=null){
			System.out.println("oi"+usuario+"|"+senha);

			System.out.println(usu1.getId());

			rsp.addProperty("resposta", true);
			rsp.addProperty("idUsuario", usu1.getId());
		}
		else{
			rsp.addProperty("resposta", false);			
		}
		
		return rsp.toString();
	}
	
	@Path("/consulta")
	@POST
	public String consultar(@FormParam("id") int id){
	

        System.out.println(">>>>>>>>>>>>>>>>>>>....."+id);
		
		DAOEntity daoUsuario = new DAOUsuario();		
		Usuario usuario = (Usuario) daoUsuario.findById(id);
		
		Date dataultimoLogin = usuario.getDataUltimoLogin();
		Date datadehoje = new Date();
		JsonObject rsp = new JsonObject();
		
		//************* Atualiza Ultimo Login do Usuario ************* 
						
		usuario.setDataUltimoLogin(datadehoje);
		daoUsuario.save(usuario);
		//************************************************************
		
		
		if(usuario!=null){
			rsp.addProperty("resposta", true);
			rsp.addProperty("nome", usuario.getNome());
			rsp.addProperty("login", usuario.getLogin());
			rsp.addProperty("senha", usuario.getSenha());
			rsp.addProperty("telefone", usuario.getTelefone());
		}
		else{
			rsp.addProperty("resposta", false);			
		}
		
		return rsp.toString();
	}
	
	@Path("/consultaMapaCalor")
	@GET
	public String calor(){
	
		String jeison = "[\n  {\"lat\" : -25.5469, \"lng\" : -54.5882 } ,\n  {\"lat\" : -25.5300, \"lng\" : -54.5700 } ,\n  {\"lat\" : -25.5100, \"lng\" : -54.5600 } ,\n  {\"lat\" : -25.5400, \"lng\" : -54.5872 } ,\n  {\"lat\" : -25.5310, \"lng\" : -54.5720 } ,\n  {\"lat\" : -25.5100, \"lng\" : -54.5600 },\n  {\"lat\" : -25.5000, \"lng\" : -54.5700 } ,\n  {\"lat\" : -25.4900, \"lng\" : -54.5600 } ,\n  {\"lat\" : -25.4900, \"lng\" : -54.5600 } ,\n  {\"lat\" : -25.5000, \"lng\" : -54.5610 } ,\n  {\"lat\" : -25.5000, \"lng\" : -54.5540 } ,\n  {\"lat\" : -25.5000, \"lng\" : -54.5611 } ,\n  {\"lat\" : -25.5470, \"lng\" : -54.5882 } ,\n  {\"lat\" : -25.5371, \"lng\" : -54.5700 } ,\n  {\"lat\" : -25.5450, \"lng\" : -54.5882 } ,\n  {\"lat\" : -25.5320, \"lng\" : -54.5700 } ,\n  {\"lat\" : -25.5479, \"lng\" : -54.5882 } ,\n  {\"lat\" : -25.5330, \"lng\" : -54.5700 } ,\n  {\"lat\" : -25.5459, \"lng\" : -54.5650 } ,\n  {\"lat\" : -25.5649, \"lng\" : -54.5600 } ,\n  {\"lat\" : -25.5446, \"lng\" : -54.5550 } ,\n  {\"lat\" : -25.5649, \"lng\" : -54.5500 } ,\n  {\"lat\" : -25.5369, \"lng\" : -54.5450 } ,\n  {\"lat\" : -25.5249, \"lng\" : -54.5400 } ,\n\n\n\n  {\"lat\" : -25.5470, \"lng\" : -54.589 }\n]";
		System.out.println("Carregando informacoes do mapa de caloura!");
		

		return jeison;
	}
	
	
	@POST
	@Path("/consultaDenuncias")
	public String consultaDenuncias(@FormParam("id") String id){
		System.out.println(id);
		DAODenuncia daoDen = new DAODenuncia();
		List<Denuncia> lDen = daoDen.findByUsuario(Integer.parseInt(id));
		JsonObject rsp = null;
		JsonArray arr = new JsonArray();
		
		for(Denuncia den: lDen) {
			rsp = new JsonObject();
			rsp.addProperty("id",den.getId());
			rsp.addProperty("descricao",den.getDescricao());
			System.out.println(den.getId()+den.getDescricao());
			arr.add(rsp);
			rsp = null;
		}
		
		System.out.println(arr.toString());
		return arr.toString();
	}
	
	@POST
	@Path("/pegaDenuncia")
	public String pegaDenuncia(@FormParam("id") String id){
		System.out.println(id);
		DAODenuncia daoDen = new DAODenuncia();
		Denuncia den = daoDen.findById(Integer.parseInt(id));
		JsonObject rsp = null;
		final ByteArrayOutputStream os = new ByteArrayOutputStream();


		
		if(den != null) {
			BufferedImage image;
			try {
				image = ImageIO.read(new File(den.getPath()));
				ImageIO.write(image,"PNG", os);
				rsp = new JsonObject();
				rsp.addProperty("lat",den.getLatitude());
				rsp.addProperty("log",den.getLongitude());
				rsp.addProperty("descricao",den.getDescricao());	
				rsp.addProperty("foto",Base64.getEncoder().encodeToString(os.toByteArray()));	
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			System.out.println(rsp.toString());						
		}		
		
		return rsp.toString();
	}
	
	
	@POST
	@Path("/uploadDenuncia")
	public String uploadDenuncia(@FormParam("descricao") String descricao,
			@FormParam("imgb64") String imgb64,
			@FormParam("latitude") Double latitude,
			@FormParam("longitude") Double longitude, @FormParam("idUsuario") int idUsuario){
		Denuncia den = new Denuncia();
		DAODenuncia daoDen = new DAODenuncia();
		Usuario user = new Usuario();
		DAOUsuario daoUser = new DAOUsuario();
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		JsonObject rsp = new JsonObject();
		
		try{			
			System.out.println("desc: " + descricao);
			System.out.println("lat: " + latitude);
			System.out.println("lng: " + longitude);
			System.out.println("photo: " + imgb64.length());
			System.out.println("idUsuario: " + idUsuario);
			
			user = daoUser.findById(idUsuario);
			den.setAvaliado(false);
			den.setDescricao(descricao);
			den.setLatitude(latitude.toString());
			den.setLongitude(longitude.toString());
			den.setDataDenuncia(new Date());
			den.setPath("c:/imagens/"+timeStamp+idUsuario+"imgUploaded.jpeg");
			den.setUsuario(user);
			
			System.out.println(user.getId());
			
			BufferedImage image = null;
			byte[] imageByte;
	
			BASE64Decoder decoder = new BASE64Decoder();
			imageByte = decoder.decodeBuffer(imgb64);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
			bis.close();
				 
			// write the image to a file
			File outputfile = new File("c:/imagens/"+timeStamp+idUsuario+"imgUploaded.jpeg");

			
			ImageIO.write(image, "jpeg", outputfile);

			daoDen.salvar(den);
			
			System.out.println(idUsuario);						
		}catch(Exception e){
			rsp.addProperty("resposta", false );
			return rsp.toString();
		}		
		rsp.addProperty("resposta", true );		
		return rsp.toString();		
	}	
}
