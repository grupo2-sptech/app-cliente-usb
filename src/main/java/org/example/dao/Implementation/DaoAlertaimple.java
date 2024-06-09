package org.example.dao.Implementation;

import org.example.database.ConexaoSQLServer;
import org.example.entities.Maquina;
import org.example.utilities.log.Log;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoAlertaimple implements org.example.dao.DaoAlerta {

    Log logTeste = new Log();
    private Connection conn = null;
    private PreparedStatement st = null;
    private ResultSet rs = null;


    public Double buscarMediaUsoRam(Maquina maquina) {
        Double mediaUso = 0.0;

        try {
            if(conn == null){
                conn = ConexaoSQLServer.getConection();
            }
            st = conn.prepareStatement("""
                    SELECT
                        AVG(ram_ocupada) AS Media_RAM_Ocupada
                    FROM
                        historico_hardware
                    WHERE
                        data_hora >= DATEADD(MINUTE, - 5, GETDATE()) and fk_maquina = ?;
                    """);
            st.setInt(1, maquina.getId());
            rs = st.executeQuery();
            if (rs.next()) {
                mediaUso = (rs.getDouble("Media_RAM_Ocupada"));
            }
        } catch (Exception e) {
            try {
                logTeste.geradorLog("[" + logTeste.fomatarHora() + "] Erro: " + "Erro ao buscar média de uso de CPU: " + e.getMessage(), "erro de conexao alerta");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return mediaUso;
    }


    public Double buscarMediaUsoCpu(Maquina maquina) {
        Double mediaUso = 0.0;

        try {
            if (conn == null) {
                conn = ConexaoSQLServer.getConection();
            }
            st = conn.prepareStatement("""
                    SELECT
                        AVG(cpu_ocupada) AS Media_CPU_Ocupada
                    FROM
                        historico_hardware
                    WHERE
                        data_hora >= DATEADD(MINUTE, - 2, GETDATE()) and fk_maquina = ?;
                    """);
            st.setInt(1, maquina.getId());
            rs = st.executeQuery();
            if (rs.next()) {
                mediaUso = (rs.getDouble("Media_CPU_Ocupada"));
            }
        } catch (Exception e) {
            try {
                logTeste.geradorLog("[" + logTeste.fomatarHora() + "] Erro: " + "Erro ao buscar média de uso de CPU: " + e.getMessage(), "erro de conexao alerta");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
        return mediaUso;
    }


    public void inserirAlertaRam(Double usoRam, Maquina maquina) {
        try {
            if (conn == null) {
                conn = ConexaoSQLServer.getConection();
            }
            st = conn.prepareStatement("""
                    INSERT INTO alerta (fk_maquina, percentagem_uso, descricao_alerta, titulo)
                    VALUES (?, ?, 'Atenção! Uso de RAM acima de 80% por 5 minutos. Por favor, verifique a atividade da máquina.', 'Informações da Memória Ram');
                    """);
            st.setInt(1, maquina.getId());
            st.setDouble(2, usoRam);
            st.executeUpdate();
        } catch (Exception e) {
            try {
                logTeste.geradorLog("[" + logTeste.fomatarHora() + "] Erro: " + "Erro ao inserir alerta: " + e.getMessage(), "erro de conexao alerta");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            conn = null;
        }
    }

    public void inserirAlertaCpu(Double usoCpu, Maquina maquina) {
        try {
            if (conn == null) {
                conn = ConexaoSQLServer.getConection();
            }
            st = conn.prepareStatement("""
                    INSERT INTO alerta (fk_maquina, percentagem_uso, descricao_alerta, titulo)
                    VALUES (?, ?, 'Atenção! Uso da CPU acima de 70% por 2 minutos. Verifique as informações do processador');
                    """);
            st.setInt(1, maquina.getId());
            st.setDouble(2, usoCpu);
            st.executeUpdate();
        } catch (Exception e) {
            try {
                logTeste.geradorLog("[" + logTeste.fomatarHora() + "] Erro: " + "Erro ao inserir alerta: " + e.getMessage(), "erro de conexao alerta");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            conn = null;
        }
    }
}
