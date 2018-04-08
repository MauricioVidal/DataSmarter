package br.com.vidal.datasmarter.model;

import java.util.Date;

/**
 * Created by mauricio on 4/8/18.
 */

public class Rota {

    private String descricao;
    private Coordenada origem, destino;
    private float preco;
    private int integracao;
    private Date ini, fim;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Coordenada getOrigem() {
        return origem;
    }

    public void setOrigem(Coordenada origem) {
        this.origem = origem;
    }

    public Coordenada getDestino() {
        return destino;
    }

    public void setDestino(Coordenada destino) {
        this.destino = destino;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getIntegracao() {
        return integracao;
    }

    public void setIntegracao(int integracao) {
        this.integracao = integracao;
    }

    public Date getIni() {
        return ini;
    }

    public void setIni(Date ini) {
        this.ini = ini;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }
}
