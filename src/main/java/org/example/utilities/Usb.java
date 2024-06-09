package org.example.utilities;

public class Usb {
    private String nome;
    private String idproduto;
    private String fornecedor;
    private String idfornecedor;

    public Usb(String nome, String idproduto, String fornecedor, String idfornecedor) {
        this.nome = nome;
        this.idproduto = idproduto;
        this.fornecedor = fornecedor;
        this.idfornecedor = idfornecedor;
    }

    public Usb() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(String idproduto) {
        this.idproduto = idproduto;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(String idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    @Override
    public String toString() {
        return """
           Usb : {
           nome : '%s'
           idproduto : '%s'
           fornecedor : '%s'
           idfornecedor : '%s'
           }
           """.formatted(nome, idproduto, fornecedor, idfornecedor);
    }
}
