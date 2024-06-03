package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    private String email;

    private String telefone;

    private String crm;

    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico cadastro){
         this.ativo = true;
         this.email = cadastro.email();
         this.endereco = new Endereco(cadastro.dadosCadastroEndereco());
         this.especialidade = cadastro.especialidade();
         this.telefone = cadastro.telefone();
         this.crm = cadastro.crm();
         this.nome = cadastro.nome();
    }

    public void atualizarDados(DadosAtuliazarMedico dados) {
        if(this.ativo){
            if(dados.nome() != null){
                this.nome = dados.nome();
            }
            if(dados.telefone() != null){
                this.telefone = dados.telefone();
            }
            if(dados.email() != null){
                this.email = dados.email();
            }
            if (dados.dadosCadastroEndereco() != null){
                endereco.atualizarDados(dados.dadosCadastroEndereco());
            }
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}

