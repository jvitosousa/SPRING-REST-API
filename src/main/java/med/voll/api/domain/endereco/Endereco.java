package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Endereco {
    private String CEP;
    private String UF;
    private String cidade;
    private String logradouro;
    private String bairro;
    private String numero;
    private String complemento;

    public Endereco(DadosCadastroEndereco dados){
        this.bairro = dados.bairro();
        this.CEP = dados.CEP();
        this.UF = dados.UF();
        this.cidade = dados.cidade();
        this.complemento = dados.complemento();
        this.logradouro = dados.logradouro();
        this.numero = dados.numero();
    }

    public Endereco() {

    }

    public void atualizarDados(DadosCadastroEndereco dados) {
        if(this.bairro != null){
            this.bairro = dados.bairro();
        }
        if(this.CEP != null){
            this.CEP = dados.CEP();
        }
        if(this.UF != null){
            this.UF = dados.UF();
        }
        if(this.cidade != null){
            this.cidade = dados.cidade();
        }
        if (this.complemento != null) {
            this.complemento = dados.complemento();
        }
        if(this.logradouro != null){
            this.logradouro = dados.logradouro();
        }
        if(this.numero != null){
            this.numero = dados.numero();
        }
    }
}
