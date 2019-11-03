package br.com.four.cadastrodeclientes.dominio.entidades;

import java.io.Serializable;

/**
 * Created by leandro on 03/11/2019.
 */

public class Cliente implements Serializable{

    public int codigo;
    public String nome;
    public String endereco;
    public String email;
    public String telefone;

    public Cliente(){
        codigo = 0;
    }

}
