package med.voll.api.domain.paciente;


import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "peciente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;

    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dados){
        this.ativo = true;
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizar(DadosAtualizarPaciente paciente) {
        if(this.ativo){
            if(paciente.email() != null){
                this.email = paciente.email();
            }
            if(paciente.nome() != null){
                this.nome = paciente.nome();
            }
            if(paciente.endereco() != null){
                this.endereco = new Endereco(paciente.endereco());
            }
        }
    }

    public void excluir(){
       this.ativo = false;
    }
}
