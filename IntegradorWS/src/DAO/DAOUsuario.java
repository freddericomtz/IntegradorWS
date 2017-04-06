package DAO;

import Model.Usuario;

public class DAOUsuario extends DAOEntity<Usuario> {
	public void salvar(Usuario usuario){
		save(usuario);
	}
	
	public void update(Usuario usuario){
		save(usuario);
	}
}
