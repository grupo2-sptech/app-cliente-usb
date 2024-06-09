package org.example.dao;

import org.example.database.DatabaseExeption;
import org.example.entities.Maquina;
import org.example.entities.Usuario;
import org.example.utilities.Usb;

import java.sql.SQLException;

public interface DaoRegistro {
    void inserirRegistroTempoReal(Maquina maquina);

    void registroEntrada(Usuario usuario, Maquina maquina) throws DatabaseExeption, SQLException;

    void dispositivosUsb(Usb usb, Maquina maquina);

    Usb buscarDispositivoUsb(String idProduto, int idMaquina);
}
