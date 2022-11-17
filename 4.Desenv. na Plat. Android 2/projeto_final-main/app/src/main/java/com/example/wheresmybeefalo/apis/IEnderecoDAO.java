package com.example.wheresmybeefalo.apis;

import com.example.wheresmybeefalo.models.Endereco;

import java.util.List;

public interface IEnderecoDAO {
    public boolean salvar(Endereco endereco);
    public boolean deletar(Endereco endereco);
    public List<Endereco> listar();
}
