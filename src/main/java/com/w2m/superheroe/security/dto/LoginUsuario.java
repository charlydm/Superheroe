package com.w2m.superheroe.security.dto;

public class LoginUsuario {
    
    private String nombreUsuario;
    private String password;
    
    public LoginUsuario(String nombreUsuario, String password) {
		this.nombreUsuario = nombreUsuario;
		this.password = password;
	}

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
	
}
